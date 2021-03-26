package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Bruker;
import database.BrukerDAO;
import database.Resultat;
import database.ResultatDAO;
import yatzy.YatzySpill;

@WebServlet("/Spill")
public class Spill extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private BrukerDAO brukerDAO;
	@EJB
	private ResultatDAO resultatDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Sjekker om innlogget.
		Cookie loggetinn = null;
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn"))
					.findAny().get();
		} catch (Throwable e) {
		}
		
		// Henviser til innlogging-servlet.
		if (loggetinn == null)
			response.sendRedirect("Innlogging");
		
		// Henviser til hovedmeny-servlet.
		else if (!brukerDAO.hentBruker(loggetinn.getValue()).erISpill())
			response.sendRedirect("Hovedmeny");
					
		// Henviser til spill-side.
		else {
			request.getSession().invalidate();
			YatzySpill yatzy = new YatzySpill();
			Bruker spiller = brukerDAO.hentBruker(loggetinn.getValue());
			Resultat resultat = resultatDAO.hentResultat(spiller.aktivtSpill().getId());
			boolean sinTur = yatzy.spillerPos(resultat, spiller) == (resultat.getTur() / 3);
			request.getSession().setAttribute("sinTur", sinTur);
			if (sinTur) {
				int kastenummer = resultat.getTur() % 3;
				request.getSession().setAttribute("kastenummer", kastenummer);
				if (kastenummer > 0) {
					String forrigeKast = yatzy.forrigeKast(resultat, spiller);
					request.getSession().setAttribute("terning1", forrigeKast.substring(0,1));
					request.getSession().setAttribute("terning2", forrigeKast.substring(1,2));
					request.getSession().setAttribute("terning3", forrigeKast.substring(2,3));
					request.getSession().setAttribute("terning4", forrigeKast.substring(3,4));
					request.getSession().setAttribute("terning5", forrigeKast.substring(4));
				}
			}		
			request.getRequestDispatcher("WEB-INF/spill.jsp").forward(request, response);
			
		}
				
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String terning1 = request.getParameter("terning1");
		String terning2 = request.getParameter("terning2");
		String terning3 = request.getParameter("terning3");
		String terning4 = request.getParameter("terning4");
		String terning5 = request.getParameter("terning5");
		
		List<Boolean> behold = new ArrayList<Boolean>();
		if (terning1 != null)
			behold.add(true);
		else
			behold.add(false);
		if (terning2 != null)
			behold.add(true);
		else
			behold.add(false);
		if (terning3 != null)
			behold.add(true);
		else
			behold.add(false);
		if (terning4 != null)
			behold.add(true);
		else
			behold.add(false);
		if (terning5 != null)
			behold.add(true);
		else
			behold.add(false);
		
		String brukernavn = null;
		try {
			brukernavn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn"))
					.findAny().get().getValue();
		} catch (Throwable e) {
		}
		
		YatzySpill yatzy = new YatzySpill();
		Bruker spiller = brukerDAO.hentBruker(brukernavn);
		Resultat resultat = resultatDAO.hentResultat(spiller.aktivtSpill().getId());
		yatzy.spillTur(resultat, spiller, behold);
		resultatDAO.oppdaterResultat(resultat);
		response.sendRedirect("Spill");
		
	}

}

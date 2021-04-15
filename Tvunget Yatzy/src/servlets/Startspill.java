package servlets;

import java.io.IOException;
import java.util.Arrays;
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
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
/**
 * Servlet implementation class Hovedmeny
 */
@WebServlet("/Startspill")
public class Startspill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;
	@EJB
	private ResultatDAO resultatDAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
		else {
			
			Bruker spiller = brukerDAO.hentBruker(loggetinn.getValue());
			Resultat resultat = resultatDAO.hentResultat(spiller.aktivtSpill().getId());
			if (resultat.getSpillere().size() > 1) {
				resultat.start();
				resultatDAO.oppdaterResultat(resultat);
			}
			response.sendRedirect("Spill");
			
		}
		
	}

}

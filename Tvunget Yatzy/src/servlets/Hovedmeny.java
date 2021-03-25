package servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class Hovedmeny
 */
@WebServlet("/Hovedmeny")
public class Hovedmeny extends HttpServlet {
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
		
		// Henviser til hovedmeny-side.
		else {
			
			Bruker bruker = brukerDAO.hentBruker(loggetinn.getValue());
			boolean erISpill = bruker.erISpill();
			request.setAttribute("erISpill", erISpill);
			List<Resultat> resultater = resultatDAO.hentAlleResultat().stream()
					.filter(a -> !a.isStartet()).collect(Collectors.toList());
			request.setAttribute("resultater", resultater);		
			
			request.getRequestDispatcher("WEB-INF/hovedmeny.jsp").forward(request, response);
			
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

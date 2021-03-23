package servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.ejb.EJB;

import database.Bruker;
import database.BrukerDAO;
/**
 * 
 * @author Prosjekt 2
 *
 */
@WebServlet("/Innlogging")
public class Innlogging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// Henviser til hovedmeny-servlet dersom innlogget.
		Cookie loggetinn = null;
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn"))
					.findAny().get();
		} catch (Throwable e) {
		}
		if (loggetinn != null)
			getServletContext().getRequestDispatcher("/Hovedmeny").forward(request, response);

		// Henviser til innlogging-side.
		request.getRequestDispatcher("WEB-INF/innlogging.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Henter info fra bruker.
		String brukernavn = request.getParameter("brukernavn");
		String passord = request.getParameter("passord");
		
		// Fjerner tidligere feilmeldinger.
		request.getSession().invalidate();
		
		// Sjekker for brukernavn/passord.
		boolean logginn = false;
		Bruker bruker = null;
		if (brukernavn != null)
			bruker = brukerDAO.hentBruker(brukernavn);
		if (bruker != null) {
				logginn = passord.equals(bruker.getPassord());
		}

		// Logger inn og henviser til hovedmeny-servlet.
		if (logginn) {
			Cookie innlogget = new Cookie("brukernavn", brukernavn);
			innlogget.setMaxAge(60*60*24*30);
			response.addCookie(innlogget);
			response.sendRedirect("Hovedmeny");
		} 
		
		// Henviser til innlogging-servlet.
		else {
			request.getSession().setAttribute("feilmelding", "Ugyldig brukernavn og/eller passord");
			response.sendRedirect("Innlogging");
		}
	}

}

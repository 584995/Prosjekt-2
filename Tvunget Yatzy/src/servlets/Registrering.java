package servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;

import database.Bruker;
import database.BrukerDAO;
/**
 * 
 * @author Prosjekt 2
 *
 */
@WebServlet("/Registrering")
public class Registrering extends HttpServlet {
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

		// Henviser til registrering-side.
		request.getRequestDispatcher("WEB-INF/registrering.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Henter info fra bruker.
		String brukernavn = request.getParameter("brukernavn");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String mobil = request.getParameter("mobil");
		String epost = request.getParameter("epost");

		// Fjerner tidligere feilmeldinger.
		request.getSession().invalidate();

		// Lagrer bruker-info i sesjonen.
		request.getSession().setAttribute("brukernavn", brukernavn);
		request.getSession().setAttribute("passord", passord);
		request.getSession().setAttribute("passordRepetert", passordRepetert);
		request.getSession().setAttribute("mobil", mobil);
		request.getSession().setAttribute("epost", epost);

		// Lagrer ny bruker og logger inn, om mulig.
		Boolean feil = false;
		Bruker nyBruker = new Bruker(brukernavn, passord, Integer.parseInt(mobil), epost);
		try {
			brukerDAO.lagreNyBruker(nyBruker);
			Cookie innlogget = new Cookie("brukernavn", brukernavn);
			innlogget.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(innlogget);
		} catch (Throwable e) {
			feil = true;
			request.getSession().setAttribute("brukernavnFeilmelding",
					"Bruker med dette brukernavnet er allerede registrert");
		}

		// Henviser til registrering-servlet.
		if (feil)
			response.sendRedirect("Registrering");
		// Henviser til registreringbekreftelse-side.
		else
			request.getRequestDispatcher("WEB-INF/registreringbekreftelse.jsp").forward(request, response);

	}

}

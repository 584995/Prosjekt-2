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
import validering.Validator;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
@WebServlet("/Registrering")
public class Registrering extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;
	Validator validator = new Validator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Sjekker om brukeren er innlogget.
		Cookie loggetinn = null;
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn"))
					.findAny().get();
		} catch (Throwable e) {
		}
		
		// Henviser til hovedmeny-servlet.
		if (loggetinn != null)
			response.sendRedirect("Hovedmeny");

		// Henviser til registrering-side.
		else
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
		Boolean feil = false;
		
		if(!validator.brukernavnSjekk(brukernavn)) {
			request.getSession().setAttribute("brukernavnFeilmelding", "Ugyldig brukernavn.");
			feil = true;
		} else {
			request.getSession().setAttribute("brukernavn", brukernavn);			
		}
		
		if(!validator.passordSjekk(passord)) {
			//Skriver ingen feilmelding nå
			request.getSession().setAttribute("passordFeilmelding", "Ugyldig passord.");
			feil = true;
		} else {
			request.getSession().setAttribute("passord", passord);
		}
		
		if(!passordRepetert.equals(passord)) {
			request.getSession().setAttribute("passordRepetertFeilmelding", "Passord må være likt.");
			feil = true;
		} else {
			request.getSession().setAttribute("passordRepetert", passordRepetert);
		}
		
		if(!validator.mobilSjekk(mobil)) {
			request.getSession().setAttribute("mobilFeilmelding", "Ugyldig mobilnummer.");
			feil = true;
		} else {
			request.getSession().setAttribute("mobil", mobil);
		}
		
		if(!validator.epostSjekk(epost)) {
			request.getSession().setAttribute("epostFeilmelding", "Ugyldig epost.");
			feil = true;
		} else {
			request.getSession().setAttribute("epost", epost);
		}
		
		
		// Lagrer ny bruker og logger inn, om mulig.	
		if(!feil) {
			Bruker nyBruker = new Bruker(brukernavn, passord, Integer.parseInt(mobil), epost);
		
		try {
			brukerDAO.lagreNyBruker(nyBruker);
			Cookie innlogget = new Cookie("brukernavn", brukernavn);
			innlogget.setMaxAge(60*60);
			response.addCookie(innlogget);
		} catch (Throwable e) {
			feil = true;
			request.getSession().setAttribute("brukernavnFeilmelding",
					"Bruker med dette brukernavnet er allerede registrert");
		}
	}

		// Henviser til registrering-servlet.
		if (feil)
			response.sendRedirect("Registrering");
		// Henviser til registreringbekreftelse-side.
		else
			request.getRequestDispatcher("WEB-INF/registreringbekreftelse.jsp").forward(request, response);

	}

}

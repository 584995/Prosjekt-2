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

@WebServlet("/Registrering")
public class Registrering extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BrukerDAO brukerDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		boolean feil = true;
		Cookie loggetinn = null;
		
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn")).findAny().get();
		}
		catch(Throwable e) {}
		
		try {
			feil = (Boolean)request.getSession().getAttribute("feil");
		}
		catch(Throwable e) {}
		
		if(feil || loggetinn == null) {
			request.getRequestDispatcher("WEB-INF/registreringskjema.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/registreringbekreftelse.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String brukernavn = request.getParameter("brukernavn");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String mobil = request.getParameter("mobil");
		String epost = request.getParameter("epost");
		
		request.getSession().invalidate();
		
		request.getSession().setAttribute("brukernavn", brukernavn);
		request.getSession().setAttribute("passord", passord);
		request.getSession().setAttribute("passordRepetert", passordRepetert);
		request.getSession().setAttribute("mobil", mobil);
		request.getSession().setAttribute("epost", epost);
		
		Boolean feil = false;
		
		if(!feil) {
			Bruker nyBruker = new Bruker(brukernavn,passord,Integer.parseInt(mobil),epost);

			try {
				brukerDAO.lagreNyBruker(nyBruker);
				Cookie innlogget = new Cookie("brukernavn",mobil);
				innlogget.setMaxAge(300);
				response.addCookie(innlogget);
			}
			catch(Throwable e) {
				feil = true;
				request.getSession().setAttribute("brukernavnFeilmelding", "Bruker med dette brukernavnet er allerede registrert");
				request.getSession().setAttribute("brukernavn", "");
			}
		}
		
		request.getSession().setAttribute("feil", feil);
		response.sendRedirect("Registrering");
	}

}

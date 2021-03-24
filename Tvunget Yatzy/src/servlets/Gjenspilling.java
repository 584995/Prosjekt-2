package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BrukerDAO;
import database.Resultat;

/**
 * Servlet implementation class Gjenspilling
 */

@WebServlet("/Gjenspilling")
public class Gjenspilling extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Henviser til innlogging-servlet dersom utlogget.
		Cookie loggetinn = null;
		try {
			loggetinn = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("brukernavn"))
					.findAny().get();
		} catch (Throwable e) {
		}
		if (loggetinn == null)
			getServletContext().getRequestDispatcher("/Innlogging").forward(request, response);
		
		int resId = 0;
		try {
			resId = Integer.parseInt((String) request.getAttribute("resPos"));
		} catch (Throwable e) {
		}
		request.getSession().invalidate();
		List<Resultat> resultater = brukerDAO.hentBruker(loggetinn.getValue()).getResultater();
		request.setAttribute("resultater", resultater);		
		if (resId < 1)
			request.getRequestDispatcher("WEB-INF/gjenspillingmeny.jsp").forward(request, response);
		
		
		Resultat resultat = brukerDAO.hentBruker(loggetinn.getValue()).getResultatById(resId);
		List<String> resultatListe = resultat.lagListe();
		request.setAttribute("resultatListe", resultatListe);
		request.getRequestDispatcher("WEB-INF/gjenspilling.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String resId = request.getParameter("resId");
		request.setAttribute("resId", resId);	
		response.sendRedirect("Gjenspilling");
		
	}

}
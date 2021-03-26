package Vebj�rnTesting;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Bruker;
import database.BrukerDAO;
import database.Resultat;
import database.ResultatDAO;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;
	@EJB
	private ResultatDAO resultatDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bruker bruker = brukerDAO.hentBruker("Vebbis");
		Resultat resultat = resultatDAO.hentResultat(1);
		List<Bruker> brukere = brukerDAO.hentAlleBrukere();
		List<Resultat> resultater = resultatDAO.hentAlleResultat();
		
		request.getSession().setAttribute("bruker", bruker);
		request.getSession().setAttribute("resultat", resultat);
		request.getSession().setAttribute("resultatEnere", resultat.getEnere());
		request.getSession().setAttribute("brukere", brukere);
		request.getSession().setAttribute("resultater", resultater);
		
		request.getRequestDispatcher("WEB-INF/test.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bruker bruker = brukerDAO.hentBruker("Vebbis");
		Resultat resultat = resultatDAO.hentResultat(1);
		resultat.setEnere("fisk");
		resultatDAO.oppdaterResultat(resultat);
		response.sendRedirect("Test");
	}

}

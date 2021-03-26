package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Prosjekt 2
 *
 */
@WebServlet("/Utlogging")
public class Utlogging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		request.getSession().invalidate();
		Cookie utlogget = new Cookie("brukernavn", "");
		utlogget.setMaxAge(0);
		response.addCookie(utlogget);
		
		response.sendRedirect("Innlogging");
		
	}

}
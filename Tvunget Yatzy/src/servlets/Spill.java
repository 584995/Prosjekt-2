package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Bruker;
import database.Resultat;

@WebServlet("/spill")
public class Spill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Spill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

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
			
		}
				
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

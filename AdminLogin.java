package com.rvce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogin extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter print=resp.getWriter();
		print.println("<html>");
		print.println("<body bgcolor='powderblue'>");
		print.println("<center>");		
		print.println("<form action='./hview' method='post'");
		print.println("<h1> LOGIN </h1><br/><br/>");
		print.println("<table><tr><td>Enter Name:</td>");
		print.println("<td><input type='text' name='nme'></td></tr>");
		print.println("<tr><td>Enter Password:</td>");
		print.println("<td><input type='password' name='pwd'></td></tr>");
		print.println("<tr><td>Enter College name from which you need the external to be allotted:</td>");
		print.println("<td><input type='text' name='collname'></td></tr><br/><br/>");
		print.println("<tr><td><input type='submit' value='Login'></td>");
		print.println("<td><input type='reset' value='Reset'></td></tr>");
		print.println("</form>");
		print.println("</center>");
		print.println("</body>");
		print.println("</html>");
		
	}
	
	

}

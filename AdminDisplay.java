package com.rvce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDisplay extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		String strNme = req.getParameter("nme");
		String strPwd = req.getParameter("pwd");
		String collname= req.getParameter("collname");
		System.out.println("Value of nme is " + strNme);
		System.out.println("Value of pwd is " + strPwd);
		System.out.println("Value of college is " + collname);
		RequestDispatcher rd=null;
		if(strNme.equalsIgnoreCase("cauvery") && strPwd.equalsIgnoreCase("cauvery")){
			rd=req.getRequestDispatcher("./jsp/printdata.jsp");
			rd.forward(req, res);
		}
		else{
			rd=req.getRequestDispatcher("./jsp/failure.jsp");
			rd.forward(req, res);
		}
	
			
	}
}
	
	
	



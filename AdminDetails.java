package com.rvce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.rvce.util.DBUtil;

public class AdminDetails extends HttpServlet{
	Connection con;

	public void init() throws ServletException {
		try {
			con = DBUtil.getOracleConnection();
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.println("inside dopost");
		PreparedStatement pst = null;
		ResultSet rs = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean flag=false;
		
		try {
		
			pst = con.prepareStatement("select f.fname,f.specialization,f.phonenum,f.email,c.collname from faculty f,college c,cred cr where f.collid=c.collid and cr.username=? and cr.password=? and cr.username=cr.password");
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				pw.println("inside try before getting all parameters");
				pw.println("<html>");
				pw.println("<form action='./faculty/edit' method='post'>");
				String username1 = rs.getString("username");
				String password1=rs.getString("password");
				String fname = rs.getString("fname");
				String specialization = rs.getString("specialization");
				String phonenum = rs.getString("phonenum");
				String email= rs.getString("email");
				pw.println("inside try after getting all parameters");
				if(username1.equalsIgnoreCase(username)&&password1.equalsIgnoreCase(password))
				{
					pw.println("<body bgcolor='wheat'>");	
					pw.println("Faculty name is:" + fname + "<br/>"
							+ "Area of Specialization is:" + specialization + "<br/>"
							+ "Phone Number:"+ phonenum + "<br/>" 
							+ "Email Id:" + email+ "<br/>"
							+ "<br/>" + "<br/>");
					pw.println("<input type='submit' value='editing option'>");
				}
				
				rs.close();
				if(!flag)
					{
							pw.println("<head>");
							pw.println("<title>Faculty login error</title>");
							pw.println("<body bgcolor='red'>");
							pw.println("<h1>Invalid Username or Password Please try again</h1>");
							pw.println("</body>");
							pw.println("</head>");
					}		
					
					
			}

			
		} catch (SQLException sqle) {
			
			pw.println("Expection"+sqle);
		
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void destroy() {
		try {
			con.close();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
	}
}

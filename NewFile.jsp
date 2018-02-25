<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>


<html>
<body bgcolor="pink">
<%

PrintWriter pw = response.getWriter();

		    Connection con = null;
			PreparedStatement pst = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("+++++++ Driver is loaded");
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE", "allah",
						"allah");
				System.out.println("Got database connection " + con);

				// step 3: create the statement
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				ResultSet rs = null;


				// step 4: execute the sql query
				
				pst = con.prepareStatement("select f.fname,f.specialization,f.phonenum,f.email,c.collname from faculty f,college c,cred cr where cr.username=? and cr.password=?");
				pst.setString(1, username);
				pst.setString(2, password);
				rs = pst.executeQuery();
				
				if(username.equalsIgnoreCase(username)&&password.equalsIgnoreCase(password)){
				while (rs.next())
				{
						
						String fname = rs.getString("fname");
						String specialization = rs.getString("specialization");
						String phonenum= rs.getString("phonenum");
						String email = rs.getString("email");
						pw.println("<body bgcolor='wheat'>");	
						pw.println("FACULTY NAME IS:" + fname + "<br/>"
								+ "AREA OF SPECIALIZATION:        " + specialization + "<br/>"
								+ "PHONE NUMBER IS:           "+ phonenum + "<br/>" 
							    + "EMAIL ID IS:          " + email+ "<br/>"
								+ "<br/>" + "<br/>");
						pw.println("</body>");
						}
							}
				else
				{
					pw.println("The data from the requested college is not available");
				}
	
				rs.close();

			}

			catch (ClassNotFoundException e) {
				System.out.println("Exception caught " + e);
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Exception caught " + e);
				e.printStackTrace();
			}

			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException e) {
					System.out.println("Exception Caught in finally block " + e);
				}
			}
		
%>

</body>
</html>


import java.io.IOException;
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentAdd
 */
@WebServlet("/StudentAdd")
public class StudentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		int stdid = Integer.parseInt(request.getParameter("t1"));
		String name = request.getParameter("t2");
		try {
			// 1. Loading Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("Driver Loaded");
			// 2. Establishing a connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","Pr@neeth@123");
			pw.println("Connected to DB");
			// 3. Prepare a statement
			PreparedStatement ps = con.prepareStatement("insert into student values(?,?)");
			ps.setInt(1, stdid);
			ps.setString(2, name);
			// 4. Execute a statement
			int a = ps.executeUpdate();
			if (a > 0) {
				pw.println("record inserted");
			} else {
				pw.println("error occured");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentDis
 */
@WebServlet("/StudentDis")
public class StudentDis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDis() {
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
		try {
			// 1. Loading Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("Driver Loaded");
			// 2. Establishing a connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","Pr@neeth@123");
			pw.println("Connected to DB");
			// 3. Prepare a statement
			PreparedStatement ps = con.prepareStatement("select * from student where stdid=?");
			ps.setInt(1, stdid);
			// 4. Execute a statement
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pw.println("student id: "+rs.getInt(1)+" student name: "+rs.getString(2));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

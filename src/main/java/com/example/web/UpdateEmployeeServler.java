package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.EmployeeDao;
import com.example.dao.EmployeeException;
import com.example.dao.EmployeeFactory;
import com.example.pojos.Employee;

@WebServlet("/update")
public class UpdateEmployeeServler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EmployeeDao dao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Employee emp = new Employee( 3,"Micheal", 5000.00, 102,2);
		
		dao = EmployeeFactory.get();
		
		System.out.println("Update employee:");
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		try {
			String message = dao.update(emp);
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h2> Update Employee</h2>");
			pw.println("<p>" + message + "</p>" );
			
			pw.println("<table style=\"border: 1px solid black;\">");
			pw.println("<tr>");
			pw.println("<th style=\"border: 1px solid black;\"> EmployeeID </th>");
			pw.println("<th style=\"border: 1px solid black;\"> Name </th>");
			pw.println("<th style=\"border: 1px solid black;\">Salary </th>");
			pw.println("<th style=\"border: 1px solid black;\">DepartmentId </th>");
			pw.println("<th style=\"border: 1px solid black;\">MannagerId </th>");
			pw.println("</tr>");
			
			pw.println("<tr>");
				//pw.println("<p><b>Employee Id : </b>"+emp.getId());
				pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + emp.getId() + "</td>");
				//pw.println("<p><b>Name        : </b>"+emp.getName());
				pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + emp.getName()  + "</td");
				//pw.println("<p><b>Salary      : </b>"+emp.getSalary());
				pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + emp.getSalary() +"</td");
				//pw.println("<p><b>Dept Id : </b>"+emp.getDeptId());
				pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + emp.getDeptId()+ "</td>");
				//pw.println("<p><b>ManagerId : </b>"+emp.getManager_id());
				pw.print("<p></p>");
		
			pw.println("<td style=\"border: 1px solid black;\"> " + emp.getManager_id() + "</td>");
			pw.println("</tr>");
			
			
			pw.println("</table>");
			pw.println("</body>");
			pw.println("</html>");
			
		} catch (EmployeeException ex) {
			// TODO Auto-generated catch block
			System.out.println(emp);
			ex.printStackTrace();
		}
	
			
		pw.close();
	}
	
}

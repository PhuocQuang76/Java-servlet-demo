package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.EmployeeDao;
import com.example.dao.EmployeeException;
import com.example.dao.EmployeeFactory;
import com.example.pojos.Employee;

@WebServlet("/all-employee-service")
public class AllEmployeeServlet extends HttpServlet{
	private EmployeeDao dao;

	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		dao=EmployeeFactory.get();
		res.setContentType("text/html");
		
		List<Employee> eList = new ArrayList<>();
		
		PrintWriter pw=res.getWriter();

		try {
			
			eList = dao.findAll();
			
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<table style=\"border: 1px solid black;\">");
			pw.println("<tr>");
			pw.println("<th style=\"border: 1px solid black;\"> EmployeeID </th>");
			pw.println("<th style=\"border: 1px solid black;\"> Name </th>");
			pw.println("<th style=\"border: 1px solid black;\">Salary </th>");
			pw.println("<th style=\"border: 1px solid black;\">DepartmentId </th>");
			pw.println("<th style=\"border: 1px solid black;\">MannagerId </th>");
			pw.println("</tr>");
			eList.forEach(e-> {
				System.out.println(e);
				
				if(e!=null) {
					pw.println("<tr>");
						//pw.println("<p><b>Employee Id : </b>"+e.getId());
					pw.print("<p></p>");
					pw.println("<td style=\"border: 1px solid black;\"> " + e.getId() + "</td>");
						//pw.println("<p><b>Name        : </b>"+e.getName());
					pw.print("<p></p>");
					pw.println("<td style=\"border: 1px solid black;\"> " + e.getName()  + "</td");
						//pw.println("<p><b>Salary      : </b>"+e.getSalary());
					pw.print("<p></p>");
					pw.println("<td style=\"border: 1px solid black;\"> " + e.getSalary() +"</td");
						//pw.println("<p><b>Dept Id : </b>"+e.getDeptId());
					pw.print("<p></p>");
					pw.println("<td style=\"border: 1px solid black;\"> " + e.getDeptId()+ "</td>");
						//pw.println("<p><b>Mannager Id : </b>"+e.getManager_id());
					pw.print("<p></p>");
					pw.println("<td style=\"border: 1px solid black;\"> " + e.getManager_id() + "</td>");
					
					
	
					
				}else {
					pw.println("<p><b> Employees doesnt exist");
				}
			});
			
			pw.println("</table>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		
		
		pw.close();
	}
}






package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.EmployeeDao;
import com.example.dao.EmployeeException;
import com.example.dao.EmployeeFactory;
import com.example.pojos.Employee;



@WebServlet("/employee-service")
public class EmployeeServlet extends HttpServlet {
	private EmployeeDao dao;
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		dao=EmployeeFactory.get();
		System.out.println("Nitin1");
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		Employee e=null;
		try {
			e=dao.findById(id);
			
		} catch (EmployeeException ex) {
			// TODO Auto-generated catch block
			System.out.println(e);
			ex.printStackTrace();
		}
		//print the response in HTML
		PrintWriter pw=res.getWriter();
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<table>");
		pw.println("<h2> Find an Employee with Id:" + id + "</h2>");
		
		pw.println("<table style=\"border: 1px solid black;\">");
		pw.println("<tr>");
		pw.println("<th style=\"border: 1px solid black;\"> EmployeeID </th>");
		pw.println("<th style=\"border: 1px solid black;\"> Name </th>");
		pw.println("<th style=\"border: 1px solid black;\">Salary </th>");
		pw.println("<th style=\"border: 1px solid black;\">DepartmentId </th>");
		pw.println("<th style=\"border: 1px solid black;\">MannagerId </th>");
		pw.println("</tr>");
		
		
		if(e!=null) {
//			pw.println("<p><b>Employee Id : </b>"+e.getId());
//			pw.println("<p><b>Name        : </b>"+e.getName());
//			pw.println("<p><b>Salary      : </b>"+e.getSalary());
//			pw.println("<p><b>Dept Id : </b>"+e.getDeptId());
			
			
			pw.println("<tr>");
			//pw.println("<p><b>Employee Id : </b>"+emp.getId());
			pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + e.getId() + "</td>");
			//pw.println("<p><b>Name        : </b>"+emp.getName());
			pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + e.getName()  + "</td");
			//pw.println("<p><b>Salary      : </b>"+emp.getSalary());
			pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + e.getSalary() +"</td");
			//pw.println("<p><b>Dept Id : </b>"+emp.getDeptId());
			pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + e.getDeptId()+ "</td>");
			//pw.println("<p><b>ManagerId : </b>"+emp.getManager_id());
			pw.print("<p></p>");
			pw.println("<td style=\"border: 1px solid black;\"> " + e.getManager_id() + "</td>");
			pw.println("</tr>");
		}else {
			pw.println("<p><b> Employee with Id "+ id+" doesnt exist");
		}
		
		
		
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
	

}




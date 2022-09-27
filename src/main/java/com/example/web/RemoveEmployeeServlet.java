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

@WebServlet("/remove")
public class RemoveEmployeeServlet extends HttpServlet{
	private EmployeeDao dao;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	dao=EmployeeFactory.get();
	
	System.out.println("Add employee:");
	res.setContentType("text/html");
	int id=Integer.parseInt(req.getParameter("id"));
	PrintWriter pw=res.getWriter();
	
		try {
			
			int returnValue = dao.remove(id);
			
			if(returnValue != 0) {
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<h2> Remmove an Employee Record</h2>");
				
				pw.println("The employee with ID number: " + id + " was remove from the databbase");
				pw.println("</body>");
				pw.println("</html>");
			}
			
			
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

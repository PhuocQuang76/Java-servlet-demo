package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.pojos.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	 public Connection getConnection() throws SQLException {
	        String jdbcURL = "jdbc:mysql://localhost:3306/javaoursoul2";
	        Connection con = DriverManager.getConnection(jdbcURL, "root", "NHOel@2804");
	        return con;
	    }

	
	    @Override
		public Employee findById(int searchId) throws EmployeeException {
			Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;
	        Employee e = null;
	        try {
	            con = getConnection();
	            st = con.createStatement();
	            rs = st.executeQuery("Select * from employee where EMP_ID =" + searchId);
	            while (rs.next()) {
	                int id = rs.getInt("EMP_ID");
	                String name = rs.getString("name");
	                float salary = rs.getFloat("salary");
	                int did = rs.getInt("DEPT_ID");
	                int mgrid = rs.getInt("MGR_ID");
	                e = new Employee(id, name, salary, did, mgrid);
	            }
	        } catch (SQLException s) {
	           throw new EmployeeException(s.getMessage());
	        } finally {
	            try {
	                rs.close();
	                st.close();
	                con.close();
	            } catch (SQLException s) {
	                System.out.println(s); // logger to store it in file.
	            }
	        }
			return e;
		}

	    
	    @Override
	    public List<Employee> findAll() throws EmployeeException {
	        List<Employee> empList = new ArrayList<>();
	        Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;

	        try {
	            con = getConnection();
	            st = con.createStatement();
	            rs = st.executeQuery("Select * from employee");
	            while (rs.next()) {
	                int id = rs.getInt("EMP_ID");
	                String name = rs.getString("name");
	                float salary = rs.getFloat("salary");
	                int did = rs.getInt("DEPT_ID");
	                int mgrid = rs.getInt("MGR_ID");
	                empList.add(new Employee(id, name, salary, did, mgrid));
	            }
	        } catch (SQLException e) {
	            throw new EmployeeException(e.getMessage(), e);
	        } finally {
	            try {
	                rs.close();
	                st.close();
	                con.close();
	            } catch (SQLException s) {
	                System.out.println(s); // logger to store it in file.
	            }
	        }
	        return empList;
	    }
	    
	    @Override
	    public String register(Employee emp) throws EmployeeException {
	        Connection con=null;
	        Statement st=null;
	        String message="";
	        try {
	            con=getConnection();
	            st=con.createStatement();
	            String query="insert into employee values("+emp.getId()+",'"+emp.getName()+"',"+emp.getSalary()+","+emp.getDeptId()+","+emp.getManager_id()+")";
	            System.out.println(query);
	            int n=st.executeUpdate(query);
	            if(n==1) {
	                message="Hi "+emp.getName()+ ", You are registred successfully";
	            }else {
	            	message= emp.getName()+ " got Error while inserting the record";
	            }
	        }catch(SQLException e) {
	        	String messageE= emp.getName()+ " got Error while inserting the record";
	            throw new EmployeeException(messageE);
	        }finally {
	            try {
	                st.close();
	                con.close();
	            }catch(SQLException s) {
	                System.out.println(s); // logger to store it in file.
	            }
	        }
	        return message;
	    }

	    
	    
	    

	    @Override
	    public int remove(int id) throws EmployeeException {
	        Connection con = null;
	        Statement st = null;

	        String message="";
	        int returnValue = 0;
	        try {
	            con=getConnection();
	            st=con.createStatement();
	            String query= ("delete from employee where EMP_ID =" + id);
	            int n=st.executeUpdate(query);
	            if(n==1) {
	                message= (" Employee with id number: " + id + " was deleted ");
	                returnValue = n;

	            }
	        }catch(SQLException s) {
	            message="Error while removinng the emoloyee with id number: " + id;
	            throw new EmployeeException(message);
	        }finally {
	            try {
	                st.close();
	                con.close();
	            }catch(SQLException s) {
	                System.out.println(s); // logger to store it in file.
	            }
	        }
	        return returnValue;
	    }

	    
	    
	    @Override
	    public String update(Employee e) throws EmployeeException{
	        String updateString = "";
	        Connection con = null;
	        PreparedStatement st = null;
	        String query ="update employee set NAME=?, SALARY=?, DEPT_ID=?, MGR_ID=? where EMP_ID=? ";

	        try {
	            con = getConnection();
	            st = con.prepareStatement(query);
	            st.setString(1, e.getName());
	            st.setDouble(2, e.getSalary());
	            st.setInt(3, e.getDeptId());
	            st.setInt(4,e.getManager_id());
	            st.setInt(5,e.getId());
	           
	            int n= st.executeUpdate();
	            if(n == 1){
	                updateString =  "Employee with id numbber: " + e.getId() + " was updated successfully!";
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }finally {
	            try {
	                st.close();
	                con.close();
	            }catch(SQLException s) {
	                System.out.println(s); // logger to store it in file.
	            }
	        }

	        return updateString;
	    }

}

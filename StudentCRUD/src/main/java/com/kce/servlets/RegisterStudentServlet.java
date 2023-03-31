package com.kce.servlets;

import java.io.IOException;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.List;

import com.kce.dao.StudentDAO;
import com.kce.entities.StudentBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;

@MultipartConfig
@WebServlet("/register")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public RegisterStudentServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		StudentDAO studentDAO=new StudentDAO();
		List<StudentBean> students=studentDAO.getAllData();
		
		//int id=Integer.parseInt(request.getParameter("id"));
		//if(id==0) {
			if(students.size()==0) {
				response.setContentType("application/json");
				response.setStatus(404);
				out.print("Record Not Found");
			}
			else {
				response.setContentType("application/json");
				response.setStatus(200);
				
				out.print(new Gson().toJson(students));
			}
		//}
		/*else {
			StudentBean studentBean=studentDAO.getById(id);
			if(studentBean!=null) {
				response.setContentType("application/json");
				response.setStatus(200);
				
				out.print(new Gson().toJson(studentBean));
			}
			else {
				response.setContentType("application/json");
				response.setStatus(404);
				out.print("Record Not Found");
			}
		}*/
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String rollno=request.getParameter("rollno");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		String cgpa=request.getParameter("cgpa");
		System.out.println(name+" "+ rollno);
		StudentBean studentBean=new StudentBean(0, name, rollno, email, department, cgpa);
		StudentDAO studentDAO=new StudentDAO();
		boolean isSaved=studentDAO.save(studentBean);
		if(isSaved) {
			out.print("Done");
		}
		else {
			out.print("failed");
		}
		
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		StudentDAO studentDAO=new StudentDAO();
		boolean isDeleted=studentDAO.delete(id);
		if(isDeleted) {
			out.print("deleted");
		}
		else {
			out.print("deletion failed");
		}
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String rollno=request.getParameter("rollno");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		String cgpa=request.getParameter("cgpa");
		StudentBean studentBean=new StudentBean(id, name, rollno, email, department, cgpa);
		StudentDAO studentDAO=new StudentDAO();
		boolean isUpdated=studentDAO.update(studentBean);
		if(isUpdated) {
			out.print("updated");
			response.setContentType("application/json");
			response.setStatus(200);
			
			out.print(new Gson().toJson(studentBean));
		}
		else {
			out.print("updation failed");
			response.setContentType("application/json");
			response.setStatus(404);
			out.print("Record Not Found");
		}
	}

}

package com.kce.dao;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kce.db.DbConfiguration;
import com.kce.entities.StudentBean;


public class StudentDAO {
	static Connection con=DbConfiguration.getConnection();
	public boolean save(StudentBean studentBean) {
		
		try {
			PreparedStatement statement=con.prepareStatement("INSERT INTO students (name, rollno, email, department, cgpa) VALUES (?, ?, ?, ?, ?)");
			statement.setString(1,  studentBean.getName());
			statement.setString(2,  studentBean.getRollno());
			statement.setString(3,  studentBean.getEmail());
			statement.setString(4,  studentBean.getDepartment());
			statement.setString(5,  studentBean.getCgpa());
			
			int inserted=statement.executeUpdate();
			System.out.println(inserted);
			if(inserted>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<StudentBean> getAllData() {
		List<StudentBean> students=new ArrayList<StudentBean>();
		try {
			PreparedStatement statement=con.prepareStatement("SELECT * FROM students");
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				StudentBean studentBean=new StudentBean(id, rs.getString("name"), rs.getString("rollno"), rs.getString("email"), rs.getString("department"), rs.getString("cgpa"));
				students.add(studentBean);
			}
			return students;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public StudentBean getById(int id) {
		try {
			PreparedStatement statement=con.prepareStatement("SELECT * FROM students WHERE id=?");
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
			StudentBean studentBean=new StudentBean(id, rs.getString("name"), rs.getString("rollno"), rs.getString("email"), rs.getString("department"), rs.getString("cgpa"));
			return studentBean;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(int id) {
		try {
			PreparedStatement statement=con.prepareStatement("DELETE FROM students WHERE id=?");
			statement.setInt(1, id);
			
			int deleted=statement.executeUpdate();
			System.out.println(deleted);
			if(deleted==1) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(StudentBean studentBean) {
		try {
			PreparedStatement statement=con.prepareStatement("UPDATE students SET name=?, rollno=?, email=?, department=?, cgpa=? WHERE id=?");
			statement.setString(1, studentBean.getName());
			statement.setString(2, studentBean.getRollno());
			statement.setString(3, studentBean.getEmail());
			statement.setString(4, studentBean.getDepartment());
			statement.setString(5, studentBean.getCgpa());
			statement.setInt(6, studentBean.getId());
			
			int updated=statement.executeUpdate();
			System.out.println(updated);
			if(updated==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}

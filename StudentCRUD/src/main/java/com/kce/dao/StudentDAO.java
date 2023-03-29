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

}

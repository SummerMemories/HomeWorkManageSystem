package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Course;

public class CourseDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";

	// Ìí¼Ó¿Î³Ì
	public boolean addCourse(Course course) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Course(cour_name, cour_credit, cour_id, cour_t_id, cour_count) VALUES(?,?,?,?,?) ";
		try {
			int num = this.executeUpdate(sql,
					new String[] { course.getCour_Name(), String.valueOf(course.getCour_Credit()),course.getCour_ID(),
								   course.getCour_t_ID(), String.valueOf(course.getCour_Count()) });
			if (num > 0) {
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return r;
	}

	// É¾³ý¿Î³Ì
	public boolean delCourse(String cour_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Course WHERE cour_num = ? ";
		try {
			int num = this.executeUpdate(sql, new String[] { cour_num });
			if (num > 0) {
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return r;
	}
	
	public List<Course> getCourseByTeacher(String cour_t_id){
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Course> courseList = new ArrayList<Course>();
		Course course = null;
		String sql = "SELECT * FROM Course WHERE cour_t_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_t_id });
			while (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return courseList;
	}
	
	
	// ²éÑ¯¿Î³ÌºÅ
	public Course getCourseNumByCourName(String cour_name) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Course course = null;
		String sql = "SELECT cour_num From Course WHERE cour_name = ?,";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_name });
			if (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return course;
	}
	
	public Course getCourseNameByCourseID(String cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Course course = null;
		String sql = "SELECT * From Course WHERE cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_id });
			if (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return course;
	}

	public List<Course> getAllCourse() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Course> courseList = new ArrayList<Course>();
		Course course = null;
		String sql = "SELECT * FROM Course";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return courseList;
	}

	public boolean updateCourse(Course course) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Course SET cour_name = ?,cour_credit = ?,cour_id = ?,cour_count = ?,cour_t_id = ? WHERE cour_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { course.getCour_Name(),String.valueOf(course.getCour_Credit()),
															 course.getCour_ID(),String.valueOf(course.getCour_Count()),
															 course.getCour_t_ID(),String.valueOf(course.getCour_Num()) });
			if (num > 0) {
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return r;
	}
	
	public Course getCourseByNum(String cour_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Course course = null;
		String sql = "SELECT * From Course WHERE cour_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_num });
			if (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return course;
	}

	public Course getCourseByCourseID(String cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Course course = null;
		String sql = "SELECT * FROM Course WHERE cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_id });
			while (rs.next()) {
				course = new Course();
				course.setCour_Num(rs.getInt("cour_num"));
				course.setCour_Name(rs.getString("cour_name"));
				course.setCour_Credit(rs.getInt("cour_credit"));
				course.setCour_ID(rs.getString("cour_id"));
				course.setCour_t_ID(rs.getString("cour_t_id"));
				course.setCour_Count(rs.getInt("cour_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return course;
	}

}

package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.StudentCourse;

public class StudentCourseDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";

	// 获取课程列表
	public List<StudentCourse> getCourseByStudent(String s_Account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<StudentCourse> studentcourseList = new ArrayList<StudentCourse>();
		StudentCourse studentcourse = null;
		String sql = "SELECT * FROM StudentCourse WHERE sc_s_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { s_Account });
			while (rs.next()) {
				studentcourse = new StudentCourse();
				studentcourse.setSc_s_Account(rs.getString("sc_s_account"));
				studentcourse.setSc_cour_ID(rs.getString("sc_cour_ID"));
				studentcourseList.add(studentcourse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return studentcourseList;
	}

	// 获取学生列表
	public List<StudentCourse> getStudentByCourse(String cour_ID) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<StudentCourse> studentcourseList = new ArrayList<StudentCourse>();
		StudentCourse studentcourse = null;
		String sql = "SELECT * FROM StudentCourse WHERE sc_cour_ID = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_ID });
			while (rs.next()) {
				studentcourse = new StudentCourse();
				studentcourse.setSc_s_Account(rs.getString("sc_s_account"));
				studentcourse.setSc_cour_ID(rs.getString("sc_cour_ID"));
				studentcourseList.add(studentcourse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return studentcourseList;
	}

	public List<StudentCourse> getAllStudentCourse() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<StudentCourse> studentcourseList = new ArrayList<StudentCourse>();
		StudentCourse studentcourse = null;
		String sql = "SELECT * FROM StudentCourse";
		try {
			ResultSet rs = this.executeQuery(sql,null);
			while (rs.next()) {
				studentcourse = new StudentCourse();
				studentcourse.setSc_Num(rs.getInt("sc_num"));
				studentcourse.setSc_s_Account(rs.getString("sc_s_account"));
				studentcourse.setSc_cour_ID(rs.getString("sc_cour_ID"));
				studentcourseList.add(studentcourse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return studentcourseList;
	}

	public boolean addStudentCourse(StudentCourse studentcourse) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO studentcourse(sc_s_account,sc_cour_id) VALUES(?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] { studentcourse.getSc_s_Account(),
															 studentcourse.getSc_cour_ID()});
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

	public boolean delStudentCourse(String sc_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM StudentCourse WHERE sc_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { sc_num });
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
}

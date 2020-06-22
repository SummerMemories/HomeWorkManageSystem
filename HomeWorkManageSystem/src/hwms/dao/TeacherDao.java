package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Teacher;

public class TeacherDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";
	
	// 获取教师列表
	public List<Teacher> getTeacher() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Teacher> teacherList = new ArrayList<Teacher>();
		Teacher teacher = null;
		String sql = "SELECT * FROM Teacher";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setT_Num(rs.getInt("t_num"));
				teacher.setT_Account(rs.getString("t_account"));
				teacher.setT_Name(rs.getString("t_name"));
				teacher.setT_Password(rs.getString("t_password"));
				teacherList.add(teacher);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return teacherList;
	}

	// 根据用户名获取用户
	public Teacher getTeacherByAccount(String account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Teacher teacher = null;
		String sql = "SELECT * FROM Teacher WHERE t_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { account });
			if (rs.next()) {
				teacher = new Teacher();
				teacher.setT_Num(rs.getInt("t_num"));
				teacher.setT_Name(rs.getString("t_name"));
				teacher.setT_Account(rs.getString("t_account"));
				teacher.setT_Password(rs.getString("t_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return teacher;
	}
	
	public boolean updateTeacherName(Teacher teacher) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Teacher SET t_name = ? WHERE t_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { teacher.getT_Name(), teacher.getT_Account()});
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
	
	public boolean updateTeacherPassword(Teacher teacher) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Teacher SET t_password = ? WHERE t_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] {teacher.getT_Password(),teacher.getT_Account()});
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
	
	public boolean updateTeacher(Teacher teacher) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Teacher SET t_account = ? , t_name = ? , t_password = ? WHERE t_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] {teacher.getT_Account(), teacher.getT_Name(), teacher.getT_Password(),String.valueOf(teacher.getT_Num()) });
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

	// 添加教师
	public boolean addTeacher(Teacher teacher) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Teacher(t_account, t_name, t_password) VALUES(?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] { teacher.getT_Account(), teacher.getT_Name(), 
														teacher.getT_Password() });
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

	// 删除指定教师
	public boolean delTeacher(String account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Teacher WHERE t_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { account });
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

	// 教师登录
	public boolean loginTeacher(Teacher teacher) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "SELECT t_account,t_password FROM Teacher WHERE t_account = ? AND t_password = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { teacher.getT_Account(), teacher.getT_Password() });
			if (rs != null && rs.next()) {
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

package hwms.dao;

import JDBCUtils.DBOper;
import hwms.entity.CheckWork;
import hwms.entity.Student;
import hwms.entity.Work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";

	// 获取学生列表
	public List<Student> getStudent() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
		String sql = "SELECT * FROM Student";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				student = new Student();
				student.setS_Num(rs.getInt("s_num"));
				student.setS_Name(rs.getString("s_name"));
				student.setS_Class(rs.getString("s_class"));
				student.setS_Account(rs.getString("s_account"));
				student.setS_Password(rs.getString("s_password"));
				studentList.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return studentList;
	}

	// 根据用户名获取用户
	public Student getStudentByAccount(String Account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Student student = null;
		String sql = "SELECT * FROM Student WHERE s_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { Account });
			if (rs.next()) {
				student = new Student();
				student.setS_Num(rs.getInt("s_num"));
				student.setS_Account(rs.getString("s_account"));
				student.setS_Name(rs.getString("s_name"));
				student.setS_Class(rs.getString("s_class"));
				student.setS_Account(rs.getString("s_account"));
				student.setS_Password(rs.getString("s_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return student;
	}

	// 修改学生姓名
	public boolean editStudentName(Student student) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Student SET s_name = ? WHERE s_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { student.getS_Name(), student.getS_Account() });
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

	// 修改学生密码
	public boolean editStudentPassword(Student student) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Student SET s_password = ? WHERE s_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { student.getS_Password(), student.getS_Account() });
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

	// 添加学生
	public boolean addStudent(Student student) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO student(s_account,s_name,s_class,s_password) VALUES(?,?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] { student.getS_Account(), student.getS_Name(),
					student.getS_Class(), student.getS_Password() });
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

	// 删除指定学生
	public boolean delStudent(String Account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Student WHERE s_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { Account });
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

	// 学生登录
	public boolean loginStudent(Student student) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "SELECT s_account,s_password FROM Student WHERE s_account = ? AND s_password = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { student.getS_Account(), student.getS_Password() });
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

	// 
	public Work getWorkBycheckwork(int ch_w_Num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { String.valueOf(ch_w_Num) });
			while (rs.next()) {
				work = new Work();
				work.setW_Num(rs.getInt("w_num"));
				work.setW_Title(rs.getString("w_title"));
				work.setW_Content(rs.getString("w_content"));
				work.setW_Deadline(rs.getString("w_deadline"));
				work.setW_Course(rs.getString("w_course"));
				work.setW_t_ID(rs.getString("w_t_id"));
				work.setW_t_Name(rs.getString("w_t_name"));
				work.setW_cour_ID(rs.getString("w_cour_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return work;
	}
	
	public boolean getCheckWorkStatus(int w_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "SELECT ch_w_num FROM CheckWork WHERE ch_w_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { String.valueOf(w_num)});
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

	// 获取作业列表
	public List<Work> getStudentWork(String w_cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Work> workList = new ArrayList<Work>();
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { w_cour_id });
			while (rs.next()) {
				work = new Work();
				work.setW_Title(rs.getString("w_title"));
				work.setW_t_ID(rs.getString("w_t_id"));
				workList.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return workList;
	}

	// 获取作业审核列表
	public List<CheckWork> getStudentCheckWork(String s_account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<CheckWork> checkworkList = new ArrayList<CheckWork>();
		CheckWork checkwork = null;
		String sql = "SELECT * FROM CheckWork WHERE ch_s_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { s_account });
			while (rs.next()) {
				checkwork = new CheckWork();
				checkwork.setCh_Num(rs.getInt("ch_num"));
				checkwork.setCh_s_Account(rs.getString("ch_s_account"));
				checkwork.setCh_cour_ID(rs.getString("ch_cour_id"));
				checkwork.setCh_w_Num(rs.getInt("ch_w_num"));
				checkwork.setCh_Score(rs.getInt("ch_score"));
				checkwork.setCh_Mark(rs.getString("ch_mark"));
				checkwork.setCh_CheckTime(rs.getString("ch_checkTime"));
				checkwork.setCh_UpTime(rs.getString("ch_upTime"));
				checkwork.setCh_Path(rs.getString("ch_path"));
				checkworkList.add(checkwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return checkworkList;
	}

	public boolean updateStudent(Student student) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Student SET s_account = ?, s_class = ?, s_name = ?, s_password = ? WHERE s_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { student.getS_Account(), student.getS_Class(),
								student.getS_Name(),student.getS_Password(),String.valueOf(student.getS_Num()) });
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

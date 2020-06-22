package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Work;

public class WorkDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";

	// 获取学生作业列表
	public List<Work> getStudentWork(String w_cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Work> workList = new ArrayList<Work>();
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { w_cour_id });
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
				workList.add(work);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return workList;
	}

	// 获取教师作业列表
	public List<Work> getTeacherWork(String w_t_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Work> workList = new ArrayList<Work>();
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_t_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { w_t_id });
			while (rs.next()) {
				work = new Work();
				work.setW_Num(rs.getInt("w_num"));
				work.setW_Title(rs.getString("w_title"));
				work.setW_Content(rs.getString("w_content"));
				work.setW_Deadline(rs.getString("w_deadline"));
				work.setW_Course(rs.getString("w_course"));
				work.setW_t_ID(rs.getString("w_t_id"));
				work.setW_cour_ID(rs.getString("w_cour_id"));
				work.setW_t_Name(rs.getString("w_t_name"));
				workList.add(work);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return workList;
	}

	public Work getWorkBytitle(String w_title) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_title = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { w_title });
			if (rs.next()) {
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

	// 修改作业信息
	public boolean editWork(Work work) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Work SET w_title = ?,w_content = ?,w_deadline = ? WHERE nt_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { work.getW_Title(), work.getW_Content(),
					work.getW_Deadline(), String.valueOf(work.getW_Num()) });
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

	// 添加作业
	public boolean addWork(Work work) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Work(w_title, w_content, w_deadline, w_course, w_t_id, w_cour_id, w_t_name) VALUES(?,?,?,?,?,?,?)";
		try {
			int num = this.executeUpdate(sql, new String[] { work.getW_Title(), work.getW_Content(),
					work.getW_Deadline(), work.getW_Course(), work.getW_t_ID(), work.getW_cour_ID(), work.getW_t_Name() });
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

	// 删除指定作业
	public boolean delWork(int w_Num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Work WHERE w_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { String.valueOf(w_Num) });
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

	public List<Work> getWorkByCourseID(String in_cour_ID) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Work> workList = new ArrayList<Work>();
		Work work = null;
		String sql = "SELECT * FROM Work WHERE w_cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { in_cour_ID });
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
				workList.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return workList;
	}
}

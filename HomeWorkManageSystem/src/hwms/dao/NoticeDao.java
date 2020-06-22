package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Notice;


public class NoticeDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";
	
	// 获取学生公告列表
	public List<Notice> getStudentNotice(String nt_cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Notice> noticeList = new ArrayList<Notice>();
		Notice notice = null;
		String sql = "SELECT * FROM Notice WHERE nt_cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] {nt_cour_id});
			while (rs.next()) {
				notice = new Notice();
				notice.setNt_Num(rs.getInt("nt_num"));
				notice.setNt_Title(rs.getString("nt_title"));
				notice.setNt_Content(rs.getString("nt_content"));
				notice.setNt_Time(rs.getString("nt_time"));
				notice.setNt_t_Name(rs.getString("nt_t_name"));
				notice.setNt_t_ID(rs.getString("nt_t_id"));
				notice.setNt_cour_ID(rs.getString("nt_cour_id"));
				noticeList.add(notice);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return noticeList;
	}

	// 获取教师公告列表
	public List<Notice> getTeacherNotice(String nt_t_id) {
		this.getConn(server, dbname, dbuser, dbpwd);	
		List<Notice> noticeList = new ArrayList<Notice>();
		Notice notice = null;
		String sql = "SELECT * FROM Notice WHERE nt_t_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] {nt_t_id});
			while (rs.next()) {
				notice = new Notice();
				notice.setNt_Num(rs.getInt("nt_num"));
				notice.setNt_Title(rs.getString("nt_title"));
				notice.setNt_Content(rs.getString("nt_content"));
				notice.setNt_Time(rs.getString("nt_time"));
				notice.setNt_t_Name(rs.getString("nt_t_name"));
				notice.setNt_t_ID(rs.getString("nt_t_id"));
				notice.setNt_cour_ID(rs.getString("nt_cour_id"));
				noticeList.add(notice);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return noticeList;
	}

	// 修改公告信息
	public boolean editNotice(Notice notice) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Notice SET nt_title = ?,nt_content = ?,nt_time = ?,nt_t_id = ? WHERE nt_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { notice.getNt_Title(), notice.getNt_Content(),
					notice.getNt_Time(), notice.getNt_t_ID(), String.valueOf(notice.getNt_Num()) });
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

	// 添加公告
	public boolean addNotice(Notice notice) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Notice(nt_title ,nt_content, nt_time, nt_t_id, nt_t_name, nt_cour_id) VALUES(?,?,?,?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] { notice.getNt_Title(), notice.getNt_Content(),
					notice.getNt_Time(), notice.getNt_t_ID(), notice.getNt_t_Name(), notice.getNt_cour_ID()});
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

	// 删除指定公告
	public boolean delNotice(int nt_Num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Notice WHERE nt_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { String.valueOf(nt_Num) });
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

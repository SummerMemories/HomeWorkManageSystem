package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.CheckWork;

public class CheckWorkDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";
	
	//Ìí¼Ó×÷ÒµÉóºË
	public boolean addCheckWork(CheckWork checkwork) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO checkwork(ch_s_account, ch_cour_id, ch_w_num, ch_score) VALUES(?,?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] {checkwork.getCh_s_Account(),
															checkwork.getCh_cour_ID(),
															String.valueOf( checkwork.getCh_w_Num()),
															String.valueOf( checkwork.getCh_Score())});
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
	
	public CheckWork getCheckWorkBySnoWno(String ch_s_account, int ch_w_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		CheckWork checkwork = null;
		String sql = "SELECT * FROM Checkwork WHERE ch_s_account = ? AND ch_w_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { ch_s_account,String.valueOf(ch_w_num)});
			if (rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return checkwork;
	}
	
	//
	public boolean updateCheckWork(String ch_upTime, String ch_path,String ch_s_account,String ch_cour_id, int ch_w_num ) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE CheckWork SET ch_upTime = ?, ch_path = ? WHERE ch_s_account = ? AND ch_cour_id = ? AND ch_w_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { ch_upTime, ch_path,ch_s_account,ch_cour_id,String.valueOf(ch_w_num)});
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

	public List<CheckWork> getCheckWorkByCourIDWNum(String ch_cour_id, String ch_w_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<CheckWork> checkworkList = new ArrayList<CheckWork>();
		CheckWork checkwork = null;
		String sql = "SELECT * FROM Checkwork WHERE ch_cour_id = ? AND ch_w_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { ch_cour_id,String.valueOf(ch_w_num)});
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

	public boolean deleteCheckWork(String w_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM CheckWork WHERE ch_w_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { w_num });
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

	public boolean updateCheckWorkTeacher(String ch_num, String ch_score, String ch_mark, String ch_checkTime) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE CheckWork SET ch_score = ?, ch_mark = ?, ch_checkTime = ? WHERE ch_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { ch_score, ch_mark, ch_checkTime, ch_num });
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

	public CheckWork getCheckWorkByNum(String ch_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		CheckWork checkwork = null;
		String sql = "SELECT * FROM Checkwork WHERE ch_num = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { ch_num });
			if (rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return checkwork;
	}
	
}

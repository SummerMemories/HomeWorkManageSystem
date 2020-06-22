package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Manage;

public class ManageDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";
	// 获取管理员列表
	public List<Manage> getManage() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Manage> manageList = new ArrayList<Manage>();
		Manage manage = null;
		String sql = "SELECT * FROM Manage";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				manage = new Manage();
				manage.setM_Num(rs.getInt("m_num"));
				manage.setM_Account(rs.getString("m_account"));
				manage.setM_Name(rs.getString("m_name"));
				manage.setM_ID(rs.getString("m_id"));
				manage.setM_Password(rs.getString("m_password"));
				manageList.add(manage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return manageList;
	}

	// 根据用户名获取用户
	public Manage getManageByAccount(String account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Manage manage = null;
		String sql = "SELECT * FROM Manage WHERE m_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { account });
			if (rs.next()) {
				manage = new Manage();
				manage.setM_Num(rs.getInt("m_num"));
				manage.setM_Account(rs.getString("m_account"));
				manage.setM_Name(rs.getString("m_name"));
				manage.setM_ID(rs.getString("m_id"));
				manage.setM_Password(rs.getString("m_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return manage;
	}
	
	// 管理员登录
	public boolean loginManage(Manage manage) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "SELECT m_account,m_password FROM Manage WHERE m_account = ? AND m_password = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { manage.getM_Account(), manage.getM_Password() });
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
	
	// 修改管理员账号
	public boolean updateManageAccount(String newAccount,String oldAccount) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Manage SET m_account = ? WHERE m_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { newAccount, oldAccount });
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
	
	// 修改管理员姓名
	public boolean updateManageName(Manage manage) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Manage SET m_name = ? WHERE m_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { manage.getM_Name(), manage.getM_Account() });
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
	
	// 修改管理员密码
	public boolean updateManagePassword(Manage manage) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Manage SET m_password = ? WHERE m_account = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { manage.getM_Password(), manage.getM_Account() });
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

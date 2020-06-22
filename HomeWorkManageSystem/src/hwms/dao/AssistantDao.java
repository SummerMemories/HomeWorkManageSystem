package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Assistant;

public class AssistantDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";
	// 获取助教列表
	public List<Assistant> getAssistant() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Assistant> assistantList = new ArrayList<Assistant>();
		Assistant assistant = null;
		String sql = "SELECT * FROM Assistant";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				assistant = new Assistant();
				assistant.setA_Num(rs.getInt("a_num"));
				assistant.setA_Name(rs.getString("a_name"));
				assistant.setA_Account(rs.getString("a_account"));
				assistant.setA_Password(rs.getString("a_password"));
				assistantList.add(assistant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return assistantList;
	}

	// 根据用户名获取用户
	public Assistant getAssistantByAccount(String Account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		Assistant assistant = null;
		String sql = "SELECT * FROM Assistant WHERE a_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { Account });
			if (rs.next()) {
				assistant = new Assistant();
				assistant.setA_Num(rs.getInt("a_num"));
				assistant.setA_Account(rs.getString("a_account"));
				assistant.setA_Name(rs.getString("a_name"));
				assistant.setA_Password(rs.getString("a_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return assistant;
	}
	
	// 修改助教信息
	public boolean updateAssistant(Assistant assistant) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Assistant SET a_account = ? , a_name = ?, a_password = ? WHERE a_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] {assistant.getA_Account(), assistant.getA_Name(),
					assistant.getA_Password(), String.valueOf(assistant.getA_Num())});
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

	// 添加助教
	public boolean addAssistant(Assistant assistant) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Assistant(a_account, a_name, a_password) VALUES(?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] {assistant.getA_Account(), assistant.getA_Name(), assistant.getA_Password() });
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

	// 删除指定助教
	public boolean delAssistant(String Account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Assistant WHERE a_Account = ?";
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

	// 助教登录
	public boolean loginAssistant(Assistant assistant) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "SELECT a_account,a_password FROM Assistant WHERE a_Account = ? AND a_password = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { assistant.getA_Account(), assistant.getA_Password() });
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

	public boolean updateAssistantName(Assistant assistant) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Assistant SET  a_name = ? WHERE a_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { assistant.getA_Name(), String.valueOf(assistant.getA_Num())});
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

	public boolean updateAssistantPassword(Assistant assistant) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Assistant SET a_password = ? WHERE a_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { assistant.getA_Password(), String.valueOf(assistant.getA_Num())});
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

package hwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtils.DBOper;
import hwms.entity.Invitation;

public class InvitationDao extends DBOper {
	private String server = "localhost";
	private String dbname = "hwms";
	private String dbuser = "root";
	private String dbpwd = "123456";

	// 获取邀请列表
	public List<Invitation> getInvitation() {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Invitation> invitationList = new ArrayList<Invitation>();
		Invitation invitation = null;
		String sql = "SELECT * FROM Invitation";
		try {
			ResultSet rs = this.executeQuery(sql, null);
			while (rs.next()) {
				invitation = new Invitation();
				invitation.setIn_Num(rs.getInt("in_num"));
				invitation.setIn_a_Account(rs.getString("in_a_account"));
				invitation.setIn_cour_ID(rs.getString("in_cour_id"));
				invitation.setIn_Accept(rs.getString("in_accept"));
				invitationList.add(invitation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return invitationList;
	}

	// 添加邀请
	public boolean addInvitation(Invitation invitation) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "INSERT INTO Invitation(in_a_account,in_cour_id,in_accept) VALUES(?,?,?) ";
		try {
			int num = this.executeUpdate(sql, new String[] { invitation.getIn_a_Account(),
								invitation.getIn_cour_ID(),invitation.getIn_Accept()});
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

	// 删除指定邀请
	public boolean delInvitation(String in_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "DELETE FROM Invitation WHERE in_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { in_num });
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

	public List<Invitation> getInvitationByAccount(String account) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Invitation> invitationList = new ArrayList<Invitation>();
		Invitation invitation = null;
		String sql = "SELECT * FROM Invitation WHERE in_a_account = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { account });
			while (rs.next()) {
				invitation = new Invitation();
				invitation.setIn_Num(rs.getInt("in_num"));
				invitation.setIn_a_Account(rs.getString("in_a_account"));
				invitation.setIn_cour_ID(rs.getString("in_cour_id"));
				invitation.setIn_Accept(rs.getString("in_accept"));
				invitationList.add(invitation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return invitationList;
	}

	public boolean updateInvitation(String in_accept,String in_num) {
		this.getConn(server, dbname, dbuser, dbpwd);
		boolean r = false;
		String sql = "UPDATE Invitation SET in_accept = ? WHERE in_num = ?";
		try {
			int num = this.executeUpdate(sql, new String[] { in_accept, in_num});
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

	public List<Invitation> getInvitationByCourID(String cour_id) {
		this.getConn(server, dbname, dbuser, dbpwd);
		List<Invitation> invitationList = new ArrayList<Invitation>();
		Invitation invitation = null;
		String sql = "SELECT * FROM Invitation WHERE in_cour_id = ?";
		try {
			ResultSet rs = this.executeQuery(sql, new String[] { cour_id });
			while (rs.next()) {
				invitation = new Invitation();
				invitation.setIn_Num(rs.getInt("in_num"));
				invitation.setIn_a_Account(rs.getString("in_a_account"));
				invitation.setIn_cour_ID(rs.getString("in_cour_id"));
				invitation.setIn_Accept(rs.getString("in_accept"));
				invitationList.add(invitation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return invitationList;
	}
}

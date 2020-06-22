package hwms.service;

import java.util.ArrayList;
import java.util.List;

import hwms.dao.InvitationDao;
import hwms.entity.Invitation;


public class InvitationManage {
	private InvitationDao invitationDao = new InvitationDao();	//引入学生dao层接口

	public void setInvitationDao(InvitationDao invitationDao) {
		this.invitationDao = invitationDao;
	}
	
	public List<Invitation> getInvitation() {
		return invitationDao.getInvitation();
	}
	
	public List<Invitation> getInvitationByAccountN(String account) {
		List<Invitation> invitationList = new ArrayList<Invitation>();
		invitationList = invitationDao.getInvitationByAccount(account);
		List<Invitation> invitationList1 = new ArrayList<Invitation>();
		for (int i = 0; i < invitationList.size(); i++) {
			if (invitationList.get(i).getIn_Accept().equals("是")) {
				invitationList1.add(invitationList.get(i));
			}
		}

		return invitationList1;
	}
	
	public List<Invitation> getInvitationByAccount(String account) {
		return invitationDao.getInvitationByAccount(account);
	}
	
	public boolean addInvitation(Invitation invitation) {
		return invitationDao.addInvitation(invitation);
	}
	
	public boolean delInvitation(String in_num) {
		return invitationDao.delInvitation(in_num);
	}
	
	public boolean updateInvitation(String in_accept,String in_num) {
		return invitationDao.updateInvitation(in_accept,in_num);
	}
	
	public List<Invitation> getInvitationByCourID(String cour_id) {
		return invitationDao.getInvitationByCourID(cour_id);
	}
}

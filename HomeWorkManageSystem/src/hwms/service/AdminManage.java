package hwms.service;

import java.util.List;

import hwms.dao.ManageDao;
import hwms.entity.Manage;

public class AdminManage {
	private ManageDao manageDao = new ManageDao();	//引入学生dao层接口
		
	public void setManageDao(ManageDao manageDao) {
		this.manageDao = manageDao;
	}

	public List<Manage> getAllManage() {
		return manageDao.getManage();
	}

	public boolean getManageLogin(Manage manage) {
		return manageDao.loginManage(manage);
	}

	public Manage getManageByAccount(String m_account) {
		return manageDao.getManageByAccount(m_account);
	}
	
	public boolean updateManageAccount(String newAccount, String oldAccount) {
		return manageDao.updateManageAccount(newAccount, oldAccount);
	}
	
	public boolean updateManageName(Manage manage) {
		return manageDao.updateManageName(manage);
	}
	
	public boolean updateManagePassword(Manage manage) {
		return manageDao.updateManagePassword(manage);
	}

	/*
	public boolean updateManagePassword(Manage Manage) {
		return ManageDao.updateManagePassword(Manage);
	}
	
	public List<Notice> getAllNotice(String c_id) {
		return ManageDao.getAllNotice(c_id);
	}

	@Override
	public List<Work> getAllWork(String w_c_id) {
		return ManageDao.getAllWork(w_c_id);
	}
	*/
}

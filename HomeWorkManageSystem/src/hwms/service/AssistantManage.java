package hwms.service;

import java.util.List;

import hwms.dao.AssistantDao;
import hwms.entity.Assistant;

public class AssistantManage {
	private AssistantDao assistantDao = new AssistantDao();	//引入学生dao层接口
	
	public void setAssistantDao(AssistantDao assistantDao) {
		this.assistantDao = assistantDao;
	}

	public boolean addAssistant(Assistant assistant) {
		return assistantDao.addAssistant(assistant);
	}

	public boolean deleteAssistant(String account) {
		return assistantDao.delAssistant(account);
	}

	public boolean updateAssistant(Assistant assistant) {
		return assistantDao.updateAssistant(assistant);
	}

	public List<Assistant> getAllAssistant() {
		return assistantDao.getAssistant();
	}

	public boolean getAssistantLogin(Assistant assistant) {
		return assistantDao.loginAssistant(assistant);
	}

	public Assistant getAssistantByAccount(String account) {
		return assistantDao.getAssistantByAccount(account);
	}

	public boolean updateAssistantName(Assistant assistant) {
		return assistantDao.updateAssistantName(assistant);
	}

	
	public boolean updateAssistantPassword(Assistant Assistant) {
		return assistantDao.updateAssistantPassword(Assistant);
	}
	/*
	public List<Notice> getAllNotice(String c_id) {
		return AssistantDao.getAllNotice(c_id);
	}

	@Override
	public List<Work> getAllWork(String w_c_id) {
		return AssistantDao.getAllWork(w_c_id);
	}
	*/
}

package hwms.service;

import java.util.List;

import hwms.dao.CheckWorkDao;
import hwms.entity.CheckWork;

public class CheckWorkManage {
	private CheckWorkDao checkworkDao = new CheckWorkDao();
	
	public void setCheckWorkDao(CheckWorkDao checkworkDao) {
		this.checkworkDao = checkworkDao;
	}
	
	public boolean addCheckWork(String ch_s_account,String ch_cour_id, int ch_w_num, int ch_score) {
		CheckWork checkwork = new CheckWork();
		checkwork.setCh_s_Account(ch_s_account);
		checkwork.setCh_cour_ID(ch_cour_id);
		checkwork.setCh_w_Num(ch_w_num);
		checkwork.setCh_Score(ch_score);
		return checkworkDao.addCheckWork(checkwork);
	}
	
	public CheckWork getCheckWorkBySnoWno(String ch_s_account, int ch_w_num){
		return checkworkDao.getCheckWorkBySnoWno(ch_s_account,ch_w_num);
	}
	
	public List<CheckWork> getCheckWorkByCourIDWNum(String ch_cour_id, String ch_w_num){
		return checkworkDao.getCheckWorkByCourIDWNum(ch_cour_id,ch_w_num);
	}
	
	public boolean updateCheckWork(String ch_upTime, String ch_path,String ch_s_account,String ch_cour_id, int ch_w_num ) {
		return checkworkDao.updateCheckWork(ch_upTime,ch_path,ch_s_account,ch_cour_id,ch_w_num);
	}
	
	public boolean updateCheckWorkByTeacher(String ch_num,String ch_score,String ch_mark,String ch_checkTime) {
		return checkworkDao.updateCheckWorkTeacher(ch_num, ch_score, ch_mark,ch_checkTime);
	}
	
	public boolean deleteCheckWork(String w_num) {
		return checkworkDao.deleteCheckWork(w_num);
	}

	public CheckWork getCheckWorkByNum(String ch_num) {
		return checkworkDao.getCheckWorkByNum(ch_num);
	}
}

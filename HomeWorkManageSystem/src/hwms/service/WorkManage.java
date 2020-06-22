package hwms.service;

import java.util.List;

import hwms.dao.WorkDao;
import hwms.entity.Work;

public class WorkManage {
	private WorkDao workDao = new WorkDao();	//引入作业dao层接口

	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}
	
	public Work getWorkByWtitle(String w_title)
	{
		return workDao.getWorkBytitle(w_title);
	}
	
	public List<Work> getStudentWork(String nt_c_id){
		return workDao.getStudentWork(nt_c_id);
	}
	
	public List<Work> getTeacherWork(String w_t_id){
		return workDao.getTeacherWork(w_t_id);
	}
	
	public boolean addWork(Work work) {
		return workDao.addWork(work);
	}

	public boolean deleteWork(int w_Num) {
		return workDao.delWork(w_Num);
	}

	public boolean updateWork(Work work) {
		return workDao.editWork(work);
	}

	public List<Work> getWorkByCourseID(String in_cour_ID) {
		return workDao.getWorkByCourseID(in_cour_ID);
	}

}

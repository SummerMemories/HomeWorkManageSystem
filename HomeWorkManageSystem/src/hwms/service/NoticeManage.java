package hwms.service;

import java.util.List;

import hwms.dao.NoticeDao;
import hwms.entity.Notice;


public class NoticeManage {
	
	private NoticeDao noticeDao = new NoticeDao();	//引入公告dao层接口
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public List<Notice> getStudentNotice(String nt_c_id){
		return noticeDao.getStudentNotice(nt_c_id);
	}
	
	public List<Notice> getTeacherNotice(String nt_t_id){
		return noticeDao.getTeacherNotice(nt_t_id);
	}
	
	
	public boolean addNotice(Notice notice) {
		return noticeDao.addNotice(notice);
	}

	public boolean deleteNotice(int id) {
		return noticeDao.delNotice(id);
	}

	public boolean updateNotice(Notice notice) {
		return noticeDao.editNotice(notice);
	}

	
}

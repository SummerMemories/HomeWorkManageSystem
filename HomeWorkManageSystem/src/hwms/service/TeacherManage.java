package hwms.service;

import java.util.List;

import hwms.dao.TeacherDao;
import hwms.entity.Teacher;

public class TeacherManage {
	private TeacherDao teacherDao = new TeacherDao();	//引入教师dao层接口

	public void setStudentDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	public Teacher getTeacherByAccount(String account)
	{
		return teacherDao.getTeacherByAccount(account);
	}

	public boolean addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}

	public boolean deleteTeacher(String account) {
		return teacherDao.delTeacher(account);
	}
	
	public boolean updateTeacherName(Teacher teacher) {
		return teacherDao.updateTeacherName(teacher);
	}
	
	public boolean updateTeacherPassword(Teacher teacher) {
		return teacherDao.updateTeacherPassword(teacher);
	}
	public boolean updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher);
	}

	public List<Teacher> getAllTeacher() {
		return teacherDao.getTeacher();
	}

	public boolean getTeacherLogin(Teacher teacher) {
		return teacherDao.loginTeacher(teacher);
	}

	/*
	public boolean updateStudentPassword(Student student) {
		return studentDao.updateStudentPassword(student);
	}
	
	public List<Notice> getAllNotice(String c_id) {
		return studentDao.getAllNotice(c_id);
	}

	@Override
	public List<Work> getAllWork(String w_c_id) {
		return studentDao.getAllWork(w_c_id);
	}
	*/
}

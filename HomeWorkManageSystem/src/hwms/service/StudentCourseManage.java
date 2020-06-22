package hwms.service;

import java.util.List;

import hwms.dao.StudentCourseDao;
import hwms.entity.StudentCourse;

public class StudentCourseManage {
	private StudentCourseDao studentcourseDao = new StudentCourseDao();
	
	public List<StudentCourse> getCourseByStudent(String s_Account){
		return studentcourseDao.getCourseByStudent(s_Account);
	}
	
	public List<StudentCourse> getStudentByCourse(String cour_ID){
		return studentcourseDao.getStudentByCourse(cour_ID);
	}

	public List<StudentCourse> getAllStudentCourse() {
		return studentcourseDao.getAllStudentCourse();
	}

	public boolean addStudentCourse(StudentCourse studentcourse) {
		return studentcourseDao.addStudentCourse(studentcourse);
	}

	public boolean deleteStudentCourse(String sc_num) {
		return studentcourseDao.delStudentCourse(sc_num);
	}
}

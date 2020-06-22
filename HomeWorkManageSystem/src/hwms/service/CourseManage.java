package hwms.service;

import java.util.List;

import hwms.dao.CourseDao;
import hwms.entity.Course;

public class CourseManage {
	CourseDao courseDao = new CourseDao();

	//��ӿγ�
	public boolean addCourse(Course course) {
		return courseDao.addCourse(course);
	}

	// ɾ���γ�
	public boolean deleteCourse(String cour_num) {
		return courseDao.delCourse(cour_num);
	}
	
	//��ȡ�γ̺�
	public List<Course> getCourseByTeacher(String cour_t_id){
		return courseDao.getCourseByTeacher(cour_t_id);
	}

	//��ȡ�γ̺�
	public Course getCourseNameByCourseID(String cour_id) {
		return courseDao.getCourseNameByCourseID(cour_id);
	}

	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

	public boolean updateCourse(Course course) {
		return courseDao.updateCourse(course);
	}
	
	public Course getCourseByNum(String cour_num) {
		return courseDao.getCourseByNum(cour_num);
	}
	
	//��ȡ�γ̺�
	public Course getCourseByCourseID(String cour_id) {
		return courseDao.getCourseByCourseID(cour_id);
	}
}

package hwms.service;

import java.util.List;

import hwms.dao.CourseDao;
import hwms.entity.Course;

public class CourseManage {
	CourseDao courseDao = new CourseDao();

	//添加课程
	public boolean addCourse(Course course) {
		return courseDao.addCourse(course);
	}

	// 删除课程
	public boolean deleteCourse(String cour_num) {
		return courseDao.delCourse(cour_num);
	}
	
	//获取课程号
	public List<Course> getCourseByTeacher(String cour_t_id){
		return courseDao.getCourseByTeacher(cour_t_id);
	}

	//获取课程号
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
	
	//获取课程号
	public Course getCourseByCourseID(String cour_id) {
		return courseDao.getCourseByCourseID(cour_id);
	}
}

package hwms.service;

import java.util.List;

import hwms.dao.StudentDao;
import hwms.entity.CheckWork;
import hwms.entity.Student;
import hwms.entity.Work;

public class StudentManage {
	private StudentDao studentDao = new StudentDao();	//引入学生dao层接口

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public Student getStudentByAccount(String account) {
		return studentDao.getStudentByAccount(account);
	}
	
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
	}

	public boolean deleteStudent(String account) {
		return studentDao.delStudent(account);
	}

	public boolean updateStudentName(Student student) {
		return studentDao.editStudentName(student);
	}
	
	public boolean updateStudentPassword(Student student) {
		return studentDao.editStudentPassword(student);
	}

	public List<Student> getAllStudent() {
		return studentDao.getStudent();
	}
	
	public List<Work> getStudentWork(String w_c_id){
		return studentDao.getStudentWork(w_c_id);
	}
	
	public List<CheckWork> getStudentCheckWork(String s_account){
		return studentDao.getStudentCheckWork(s_account);
	}

	public boolean getStudentLogin(Student student) {
		return studentDao.loginStudent(student);
	}
	
	public Work getWorkBycheckwork(int ch_w_num) {
		return studentDao.getWorkBycheckwork(ch_w_num);
	}

	public boolean getCheckWorkStatus(int w_num) {
		return studentDao.getCheckWorkStatus(w_num);
	}

	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
}

package com.android.studentfaculty.context;

import java.util.ArrayList;

import android.app.Application;

import com.android.studentfaculty.bean.AttendanceBean;
import com.android.studentfaculty.bean.AttendanceSessionBean;
import com.android.studentfaculty.bean.FacultyDTO;
import com.android.studentfaculty.bean.StudentDTO;

public class ApplicationContext extends Application {
	private FacultyDTO facultyBean;
	private AttendanceSessionBean attendanceSessionBean;
	private ArrayList<StudentDTO> studentBeanList;
	private ArrayList<AttendanceBean> attendanceBeanList;
	
	public FacultyDTO getFacultyBean() {
		return facultyBean;
	}
	public void setFacultyBean(FacultyDTO facultyBean) {
		this.facultyBean = facultyBean;
	}
	public AttendanceSessionBean getAttendanceSessionBean() {
		return attendanceSessionBean;
	}
	public void setAttendanceSessionBean(AttendanceSessionBean attendanceSessionBean) {
		this.attendanceSessionBean = attendanceSessionBean;
	}
	public ArrayList<StudentDTO> getStudentBeanList() {
		return studentBeanList;
	}
	public void setStudentBeanList(ArrayList<StudentDTO> studentBeanList) {
		this.studentBeanList = studentBeanList;
	}
	public ArrayList<AttendanceBean> getAttendanceBeanList() {
		return attendanceBeanList;
	}
	public void setAttendanceBeanList(ArrayList<AttendanceBean> attendanceBeanList) {
		this.attendanceBeanList = attendanceBeanList;
	}
	
	

}

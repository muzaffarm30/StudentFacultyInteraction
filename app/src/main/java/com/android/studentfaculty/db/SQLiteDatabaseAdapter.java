package com.android.studentfaculty.db;

import java.util.ArrayList;

import com.android.studentfaculty.bean.AttendanceBean;
import com.android.studentfaculty.bean.AttendanceSessionBean;
import com.android.studentfaculty.bean.FacultyDTO;
import com.android.studentfaculty.bean.MaterialsDTO;
import com.android.studentfaculty.bean.StudentDTO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseAdapter extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "studentFaculty";

	// Contacts table name
	private static final String FACULTY_INFO_TABLE = "faculty_table";
	private static final String STUDENT_INFO_TABLE = "student_table";
	private static final String MATERIALS_INFO_TABLE = "materials_table";
	private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
	private static final String ATTENDANCE_TABLE = "attendance_table";


	// Contacts Table Columns names
	private static final String KEY_FACULTY_ID = "faculty_id";
	private static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";
	private static final String KEY_FACULTY_LASTNAME = "faculty_Lastname";
	private static final String KEY_FACULTY_MO_NO = "faculty_mobilenumber";
	private static final String KEY_FACULTY_ADDRESS = "faculty_address";
	private static final String KEY_FACULTY_USERNAME = "faculty_username";
	private static final String KEY_FACULTY_PASSWORD = "faculty_password";

	private static final String KEY_STUDENT_ID = "student_id";
	private static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
	private static final String KEY_STUDENT_LASTNAME = "student_lastname";
	private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
	private static final String KEY_STUDENT_ADDRESS = "student_address";
	private static final String KEY_STUDENT_DEPARTMENT = "student_department";
	private static final String KEY_STUDENT_CLASS = "student_class";
	private static final String KEY_STUDENT_USERNAME = "student_username";
	private static final String KEY_STUDENT_PASSWORD = "student_password";

	private static final String KEY_MATERIALS_ID = "materials_id";
	private static final String KEY_MATERIALS_NAME = "materials_name";
	private static final String KEY_MATERIALS_LOCATION = "materials_location";
	private static final String KEY_LANG= "lang";

	private static final String KEY_ATTENDANCE_SESSION_ID = "attendance_session_id";
	private static final String KEY_ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
	private static final String KEY_ATTENDANCE_SESSION_DEPARTMENT = "attendance_session_department";
	private static final String KEY_ATTENDANCE_SESSION_CLASS = "attendance_session_class";
	private static final String KEY_ATTENDANCE_SESSION_DATE = "attendance_session_date";
	private static final String KEY_ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";

	private static final String KEY_SESSION_ID = "attendance_session_id";
	private static final String KEY_ATTENDANCE_STUDENT_ID = "attendance_student_id";
	private static final String KEY_ATTENDANCE_STATUS = "attendance_status";


	public SQLiteDatabaseAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override

	public void onCreate(SQLiteDatabase db) {
		String queryFaculty="CREATE TABLE "+ FACULTY_INFO_TABLE +" (" +
				KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_FACULTY_FIRSTNAME + " TEXT, " + 
				KEY_FACULTY_LASTNAME + " TEXT, " +
				KEY_FACULTY_MO_NO + " TEXT, " +
				KEY_FACULTY_ADDRESS + " TEXT," +
				KEY_FACULTY_USERNAME + " TEXT," +
				KEY_FACULTY_PASSWORD + " TEXT " + ")";
		Log.d("queryFaculty",queryFaculty);


		String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
				KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_STUDENT_FIRSTNAME + " TEXT, " + 
				KEY_STUDENT_LASTNAME + " TEXT, " +
				KEY_STUDENT_MO_NO + " TEXT, " +
				KEY_STUDENT_ADDRESS + " TEXT," +
				KEY_STUDENT_DEPARTMENT + " TEXT," +
				KEY_STUDENT_CLASS + " TEXT,"
				+KEY_STUDENT_USERNAME + " TEXT,"
				+KEY_STUDENT_PASSWORD + " TEXT" + ")";
		Log.d("queryStudent",queryStudent );

		String queryMaterials="CREATE TABLE "+ MATERIALS_INFO_TABLE +" (" +
				KEY_MATERIALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_MATERIALS_NAME + " TEXT, " +
				KEY_MATERIALS_LOCATION + " TEXT, "+
				KEY_LANG + " TEXT "+ ")";
		Log.d("queryStudent",queryMaterials );


		String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
				KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " + 
				KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT, " +
				KEY_ATTENDANCE_SESSION_CLASS + " TEXT, " +
				KEY_ATTENDANCE_SESSION_DATE + " DATE," +
				KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";
		Log.d("queryAttendanceSession",queryAttendanceSession );


		String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
				KEY_SESSION_ID + " INTEGER, " +
				KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
				KEY_ATTENDANCE_STATUS + " TEXT " + ")";
		Log.d("queryAttendance",queryAttendance );


		try
		{
			db.execSQL(queryFaculty);
			db.execSQL(queryStudent);
			db.execSQL(queryAttendanceSession);
			db.execSQL(queryAttendance);
			db.execSQL(queryMaterials);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
		}

	} 


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String queryFaculty="CREATE TABLE "+ FACULTY_INFO_TABLE +" (" +
				KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_FACULTY_FIRSTNAME + " TEXT, " + 
				KEY_FACULTY_LASTNAME + " TEXT, " +
				KEY_FACULTY_MO_NO + " TEXT, " +
				KEY_FACULTY_ADDRESS + " TEXT," +
				KEY_FACULTY_USERNAME + " TEXT," +
				KEY_FACULTY_PASSWORD + " TEXT " + ")";
		Log.d("queryFaculty",queryFaculty);


		String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
				KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_STUDENT_FIRSTNAME + " TEXT, " + 
				KEY_STUDENT_LASTNAME + " TEXT, " +
				KEY_STUDENT_MO_NO + " TEXT, " +
				KEY_STUDENT_ADDRESS + " TEXT," +
				KEY_STUDENT_DEPARTMENT + " TEXT," +
				KEY_STUDENT_CLASS + " TEXT, "
				+KEY_STUDENT_USERNAME + " TEXT, "
				+KEY_STUDENT_PASSWORD + " TEXT " + ")";
		Log.d("queryStudent",queryStudent );

		String queryMaterials="CREATE TABLE "+ MATERIALS_INFO_TABLE +" (" +
				KEY_MATERIALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_MATERIALS_NAME + " TEXT, " +
				KEY_MATERIALS_LOCATION + " TEXT, "+
				KEY_LANG + " TEXT "+ ")";
		Log.d("queryStudent",queryMaterials );


		String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
				KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " + 
				KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT, " +
				KEY_ATTENDANCE_SESSION_CLASS + " TEXT, " +
				KEY_ATTENDANCE_SESSION_DATE + " TEXT," +
				KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" +")";
		Log.d("queryAttendanceSession",queryAttendanceSession );


		String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
				KEY_SESSION_ID + " INTEGER, " +
				KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
				KEY_ATTENDANCE_STATUS + " TEXT " + ")";
		Log.d("queryAttendance",queryAttendance );

		try
		{
			db.execSQL(queryFaculty);
			db.execSQL(queryStudent);
			db.execSQL(queryAttendanceSession);
			db.execSQL(queryAttendance);
			db.execSQL(queryMaterials);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
		}		
	}

	//facult crud
	public void addFaculty(FacultyDTO facultyBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO faculty_table (faculty_firstname,faculty_Lastname,faculty_mobilenumber,faculty_address,faculty_username,faculty_password) values ('"+ 
				facultyBean.getFaculty_firstname()+"', '"+
				facultyBean.getFaculty_lastname()+"', '"+
				facultyBean.getFaculty_mobilenumber()+"', '"+
				facultyBean.getFaculty_address()+"', '"+
				facultyBean.getFaculty_username()+"', '"+
				facultyBean.getFaculty_password()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	public FacultyDTO validateFaculty(String userName, String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		String query = "SELECT * FROM faculty_table where faculty_username='"+userName+"' and faculty_password='"+password+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			
				FacultyDTO facultyBean = new FacultyDTO();
				facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
				facultyBean.setFaculty_firstname(cursor.getString(1));
				facultyBean.setFaculty_lastname(cursor.getString(2));
				facultyBean.setFaculty_mobilenumber(cursor.getString(3));
				facultyBean.setFaculty_address(cursor.getString(4));
				facultyBean.setFaculty_username(cursor.getString(5));
				facultyBean.setFaculty_password(cursor.getString(6));
				return facultyBean;
		}
		return null;
	}
	public StudentDTO validate_student(String userName, String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM student_table where student_username='"+userName+"' and student_password='"+password+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{

				StudentDTO facultyBean = new StudentDTO();
				facultyBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
				facultyBean.setStudent_firstname(cursor.getString(1));
				facultyBean.setStudent_lastname(cursor.getString(2));
				facultyBean.setStudent_mobilenumber(cursor.getString(3));
				facultyBean.setStudent_address(cursor.getString(4));
				return facultyBean;
		}
		return null;
	}

	public ArrayList<FacultyDTO> getAllFaculty()
	{
		Log.d("in get all","in get all" );
		ArrayList<FacultyDTO> list = new ArrayList<FacultyDTO>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM faculty_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				FacultyDTO facultyBean = new FacultyDTO();
				facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
				facultyBean.setFaculty_firstname(cursor.getString(1));
				facultyBean.setFaculty_lastname(cursor.getString(2));
				facultyBean.setFaculty_mobilenumber(cursor.getString(3));
				facultyBean.setFaculty_address(cursor.getString(4));
				facultyBean.setFaculty_username(cursor.getString(5));
				facultyBean.setFaculty_password(cursor.getString(6));
				list.add(facultyBean);

			}while(cursor.moveToNext());
		}
		return list;
	}

	public void deleteFaculty(int facultyId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM faculty_table WHERE faculty_id="+facultyId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	//student crud
	public void addStudent(StudentDTO studentBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO student_table (student_firstname,student_lastname,student_mobilenumber,student_address,student_department,student_class,student_username,student_password) values ('"+
				studentBean.getStudent_firstname()+"', '"+
				studentBean.getStudent_lastname()+"','"+
				studentBean.getStudent_mobilenumber()+"', '"+
				studentBean.getStudent_address()+"', '"+
				studentBean.getStudent_department()+"', '"+
				studentBean.getStudent_class()+"', '"+
				studentBean.getStudent_username()+"', '"+
				studentBean.getStudent_password()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	//student crud
	public void addMaterials(MaterialsDTO materialsBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO materials_table (materials_name,materials_location,lang) values ('"+
				materialsBean.getMaterial_name()+"', '"+
				materialsBean.getMaterial_location()+"', '"+
				materialsBean.getLang()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	public ArrayList<MaterialsDTO> getAllMaterials(String langNew)
	{
		ArrayList<MaterialsDTO> list = new ArrayList<MaterialsDTO>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM materials_table where lang='"+langNew+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do{
				MaterialsDTO materialsBean = new MaterialsDTO();
				materialsBean.setMaterials_id(Integer.parseInt(cursor.getString(0)));
				materialsBean.setMaterial_name(cursor.getString(1));
				materialsBean.setMaterial_location(cursor.getString(2));

				list.add(materialsBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public ArrayList<StudentDTO> getAllStudent()
	{
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM student_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				StudentDTO studentBean = new StudentDTO();
				studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
				studentBean.setStudent_firstname(cursor.getString(1));
				studentBean.setStudent_lastname(cursor.getString(2));
				studentBean.setStudent_mobilenumber(cursor.getString(3));
				studentBean.setStudent_address(cursor.getString(4));
				studentBean.setStudent_department(cursor.getString(5));
				studentBean.setStudent_class(cursor.getString(6));
				list.add(studentBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public ArrayList<StudentDTO> getAllStudentByBranchYear()
	{
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM student_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				StudentDTO studentBean = new StudentDTO();
				studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
				studentBean.setStudent_firstname(cursor.getString(1));
				studentBean.setStudent_lastname(cursor.getString(2));
				studentBean.setStudent_mobilenumber(cursor.getString(3));
				studentBean.setStudent_address(cursor.getString(4));
				studentBean.setStudent_department(cursor.getString(5));
				studentBean.setStudent_class(cursor.getString(6));
				list.add(studentBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public StudentDTO getStudentById(int studentId)
	{
		StudentDTO studentBean = new StudentDTO();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM student_table where student_id="+studentId;
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				
				studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
				studentBean.setStudent_firstname(cursor.getString(1));
				studentBean.setStudent_lastname(cursor.getString(2));
				studentBean.setStudent_mobilenumber(cursor.getString(3));
				studentBean.setStudent_address(cursor.getString(4));
				studentBean.setStudent_department(cursor.getString(5));
				studentBean.setStudent_class(cursor.getString(6));
				
			}while(cursor.moveToNext());
		}
		return studentBean;
	}

	public void deleteStudent(int studentId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM student_table WHERE student_id="+studentId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}

	//attendance session Table crud
	public int addAttendanceSession(AttendanceSessionBean attendanceSessionBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id,attendance_session_department,attendance_session_class,attendance_session_date,attendance_session_subject) values ('"+ 
				attendanceSessionBean.getAttendance_session_faculty_id()+"', '"+
				attendanceSessionBean.getAttendance_session_department()+"','"+
				attendanceSessionBean.getAttendance_session_class()+"', '"+
				attendanceSessionBean.getAttendance_session_date()+"', '"+
				attendanceSessionBean.getAttendance_session_subject()+"')";
		Log.d("query", query);
		db.execSQL(query);

		String query1= "select max(attendance_session_id) from attendance_session_table";
		Cursor cursor = db.rawQuery(query1, null);
		
		if(cursor.moveToFirst())
		{
			int sessionId = Integer.parseInt(cursor.getString(0));
			
			return sessionId;
		}
			
		
		db.close();
		return 0;
	}

	public ArrayList<AttendanceSessionBean> getAllAttendanceSession()
	{
		ArrayList<AttendanceSessionBean> list = new ArrayList<AttendanceSessionBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM attendance_session_table";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				attendanceSessionBean.setAttendance_session_id(Integer.parseInt(cursor.getString(0)));
				attendanceSessionBean.setAttendance_session_faculty_id(Integer.parseInt(cursor.getString(1)));
				attendanceSessionBean.setAttendance_session_department(cursor.getString(2));
				attendanceSessionBean.setAttendance_session_class(cursor.getString(3));
				attendanceSessionBean.setAttendance_session_date(cursor.getString(4));
				attendanceSessionBean.setAttendance_session_subject(cursor.getString(5));
				list.add(attendanceSessionBean);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public void deleteAttendanceSession(int attendanceSessionId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM attendance_session_table WHERE attendance_session_id="+attendanceSessionId ;

		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	//attendance crud
	public void addNewAttendance(AttendanceBean attendanceBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO attendance_table values ("+ 
				attendanceBean.getAttendance_session_id()+", "+
				attendanceBean.getAttendance_student_id()+", '"+
				attendanceBean.getAttendance_status()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	
	
	public ArrayList<AttendanceBean> getAttendanceBySessionID(AttendanceSessionBean attendanceSessionBean)
	{
		int attendanceSessionId=0;
		ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id="+attendanceSessionBean.getAttendance_session_faculty_id()+""
				+" AND attendance_session_department='"+attendanceSessionBean.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSessionBean.getAttendance_session_class()+"'" +
						" AND attendance_session_date='"+attendanceSessionBean.getAttendance_session_date()+"' AND attendance_session_subject='"+attendanceSessionBean.getAttendance_session_subject()+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				attendanceSessionId=(Integer.parseInt(cursor.getString(0)));
			}while(cursor.moveToNext());
		}
		
		String query1="SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
		Cursor cursor1 = db.rawQuery(query1, null);
		if(cursor1.moveToFirst()) 
		{
			do{
				AttendanceBean attendanceBean = new AttendanceBean();
				attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
				attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
				attendanceBean.setAttendance_status(cursor1.getString(2));
				list.add(attendanceBean);

			}while(cursor1.moveToNext());
		}
		return list;
	}
	
	public ArrayList<AttendanceBean> getTotalAttendanceBySessionID(AttendanceSessionBean attendanceSessionBean)
	{
		int attendanceSessionId=0;
		ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id="+attendanceSessionBean.getAttendance_session_faculty_id()+""
				+" AND attendance_session_department='"+attendanceSessionBean.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSessionBean.getAttendance_session_class()+"'" +
						" AND attendance_session_subject='"+attendanceSessionBean.getAttendance_session_subject()+"'";
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				attendanceSessionId=(Integer.parseInt(cursor.getString(0)));
				
				String query1="SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
				Cursor cursor1 = db.rawQuery(query1, null);
				if(cursor1.moveToFirst()) 
				{
					do{
						AttendanceBean attendanceBean = new AttendanceBean();
						attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
						attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
						attendanceBean.setAttendance_status(cursor1.getString(2));
						list.add(attendanceBean);

					}while(cursor1.moveToNext());
				}
				
				AttendanceBean attendanceBean = new AttendanceBean();
				attendanceBean.setAttendance_session_id(0);
				attendanceBean.setAttendance_status("Date : " + cursor.getString(4));
				list.add(attendanceBean);
				
			}while(cursor.moveToNext());
		}
		
		
		return list;
	}
	
	public ArrayList<AttendanceBean> getAllAttendanceByStudent()
	{
		ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT attendance_student_id,count(*) FROM attendance_table where attendance_status='P' group by attendance_student_id";
		
		Log.d("query", query);
		
		Cursor cursor = db.rawQuery(query, null);
		
		

		if(cursor.moveToFirst()) 
		{
			do{
				Log.d("studentId","studentId:"+cursor.getString(0)+", Count:"+cursor.getString(1));
				AttendanceBean attendanceBean = new AttendanceBean();
				attendanceBean.setAttendance_student_id(Integer.parseInt(cursor.getString(0)));
				attendanceBean.setAttendance_session_id(Integer.parseInt(cursor.getString(1)));
				list.add(attendanceBean);

			}while(cursor.moveToNext());
		}
		return list;
	}

}
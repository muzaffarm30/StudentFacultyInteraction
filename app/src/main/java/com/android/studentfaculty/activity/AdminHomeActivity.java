package com.android.studentfaculty.activity;

import java.util.ArrayList;

import com.android.studentfaculty.bean.AttendanceBean;
import com.android.studentfaculty.context.ApplicationContext;
import com.android.studentfaculty.db.SQLiteDatabaseAdapter;
import com.android.studentfaculty.utils.SharedPref;
import com.example.androidattendancesystem.R;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {

	Button addStudent;
	Button addFaculty;
	Button viewStudent;
	Button viewFaculty;
	Button logout;
	Button attendancePerStudent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		SharedPref.init(this);

		addStudent =(Button)findViewById(R.id.buttonaddstudent);
		addFaculty =(Button)findViewById(R.id.buttonaddfaculty);
		viewStudent =(Button)findViewById(R.id.buttonViewstudent);
		viewFaculty =(Button)findViewById(R.id.buttonviewfaculty);
		logout =(Button)findViewById(R.id.buttonlogout);
		
		addStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(AdminHomeActivity.this, StudentAddActivity.class);
				startActivity(intent);
			}
		});
		
		addFaculty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(AdminHomeActivity.this,AddFacultyActivity.class);
				startActivity(intent);
			}
		});
		
		viewFaculty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(AdminHomeActivity.this, FacultyViewActivity.class);
				startActivity(intent);
			}
		});


		viewStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(AdminHomeActivity.this, StudentViewActivity.class);
				startActivity(intent);
			}
		});
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SharedPref.removePref();

				Intent intent =new Intent(AdminHomeActivity.this,MainActivity.class);
				startActivity(intent);
				finishAffinity();
			}
		});
		attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
		attendancePerStudent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				SQLiteDatabaseAdapter dbAdapter = new SQLiteDatabaseAdapter(AdminHomeActivity.this);
				ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
				((ApplicationContext) AdminHomeActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

				
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

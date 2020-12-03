package com.android.studentfaculty.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.studentfaculty.bean.StudentDTO;
import com.android.studentfaculty.db.SQLiteDatabaseAdapter;
import com.example.androidattendancesystem.R;

import java.util.ArrayList;

public class StudentViewActivity extends AppCompatActivity {

    ArrayList<StudentDTO> studentBeanList;
    String branch;
    String year;
    SQLiteDatabaseAdapter dbAdapter = new SQLiteDatabaseAdapter(this);
    private ListView listView;
    private TextView noDataTv;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listview);
        noDataTv = findViewById(R.id.noDataTv);
        final ArrayList<String> studentList = new ArrayList<String>();


        studentBeanList = dbAdapter.getAllStudentByBranchYear();

        if (studentBeanList.size() == 0) {
            noDataTv.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }

        for (StudentDTO studentBean : studentBeanList) {
            String users = studentBean.getStudent_firstname() + " " + studentBean.getStudent_lastname();

            studentList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_student_list, R.id.label, studentList);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StudentViewActivity.this);
                alertDialogBuilder.setMessage("Are you sure you want to delete student?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        studentList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteStudent(studentBeanList.get(position).getStudent_id());
                        studentBeanList = dbAdapter.getAllStudentByBranchYear();

                        for (StudentDTO studentBean : studentBeanList) {
                            String users = "FirstName: " + studentBean.getStudent_firstname() + "\nLastname:" + studentBean.getStudent_lastname();
                            studentList.add(users);
                            Log.d("users: ", users);

                        }
                    }

                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();


                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

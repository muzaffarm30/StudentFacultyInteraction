package com.android.studentfaculty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.studentfaculty.bean.StudentDTO;
import com.android.studentfaculty.db.DBAdapter;
import com.example.androidattendancesystem.R;

public class StudentAddActivity extends AppCompatActivity {

    Button registerButton;
    EditText textFirstName;
    EditText textPassword;
    EditText textUsername;
    EditText textLastName;

    EditText textcontact;
    EditText textaddress;
    Spinner spinnerbranch, spinneryear;
    String userrole, branch, year;
    private String[] branchString = new String[]{"CSE", "ECE", "EEE", "IT", "others"};
    private String[] yearString = new String[]{"1st", "2nd", "3rd", "4th"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        spinnerbranch = findViewById(R.id.spinnerdept);
        spinneryear = findViewById(R.id.spinneryear);
        textFirstName = findViewById(R.id.editTextFirstName);
        textLastName = findViewById(R.id.editTextLastName);
        textcontact = findViewById(R.id.editTextPhone);
        textaddress = findViewById(R.id.editTextaddr);
        textUsername = findViewById(R.id.editTextUserName);
        textPassword = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.RegisterButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                branch = (String) spinnerbranch.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);

        ///......................spinner2

        spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year = (String) spinneryear.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);


        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //......................................validation
                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                } else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("please enter lastname");
                } else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneno");
                } else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("enter address");
                } else if (TextUtils.isEmpty(username)) {
                    textUsername.setError("enter username");
                } else if (TextUtils.isEmpty(password)) {
                    textPassword.setError("enter password");
                } else {

                    StudentDTO studentBean = new StudentDTO();

                    studentBean.setStudent_firstname(first_name);
                    studentBean.setStudent_lastname(last_name);
                    studentBean.setStudent_mobilenumber(phone_no);
                    studentBean.setStudent_address(address);
                    studentBean.setStudent_department(branch);
                    studentBean.setStudent_class(year);
                    studentBean.setStudent_username(username);
                    studentBean.setStudent_password(password);

                    DBAdapter dbAdapter = new DBAdapter(StudentAddActivity.this);
                    dbAdapter.addStudent(studentBean);


                    Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

                    if (getIntent().hasExtra("login")) {
                        Intent intent = new Intent(StudentAddActivity.this, StudentMaterialsViewActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        finish();
                    }


                }
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

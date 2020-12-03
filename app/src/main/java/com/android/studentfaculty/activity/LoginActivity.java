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

import com.android.studentfaculty.bean.FacultyDTO;
import com.android.studentfaculty.bean.StudentDTO;
import com.android.studentfaculty.context.ApplicationContext;
import com.android.studentfaculty.db.SQLiteDatabaseAdapter;
import com.android.studentfaculty.utils.SharedPref;
import com.example.androidattendancesystem.R;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signup;
    EditText username, password;
    Spinner spinnerloginas;
    String userrole;
    private String[] userRoleString = new String[]{"admin", "faculty", "student"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPref.init(LoginActivity.this);

        login = findViewById(R.id.buttonlogin);
        signup = findViewById(R.id.signUpBt);
        username = findViewById(R.id.editTextusername);
        password = findViewById(R.id.editTextpassword);
        spinnerloginas = findViewById(R.id.spinnerloginas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, StudentAddActivity.class)
                        .putExtra("login", "login"));

            }
        });

        spinnerloginas.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                userrole = (String) spinnerloginas.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloginas.setAdapter(adapter_role);

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (userrole.equals("admin")) {

                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid User Name");
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    } else {
                        SharedPref.write("type","admin");
                        if (user_name.equals("admin") & pass_word.equals("admin123")) {
                            Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                            finishAffinity();
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (userrole.equals("student")) {
                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid User Name");
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    }
                    SQLiteDatabaseAdapter dbAdapter = new SQLiteDatabaseAdapter(LoginActivity.this);
                    StudentDTO studentBean = dbAdapter.validate_student(user_name, pass_word);

                    if (studentBean != null) {
                        SharedPref.write("type","student");
                        Intent intent = new Intent(LoginActivity.this, StudentMaterialsViewActivity.class);
                        startActivity(intent);
                        finishAffinity();
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid User Name");
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    }
                    SQLiteDatabaseAdapter dbAdapter = new SQLiteDatabaseAdapter(LoginActivity.this);
                    FacultyDTO facultyBean = dbAdapter.validateFaculty(user_name, pass_word);

                    if (facultyBean != null) {
                        SharedPref.write("type","faculty");
                        Intent intent = new Intent(LoginActivity.this, FacultyHomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                        ((ApplicationContext) LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
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

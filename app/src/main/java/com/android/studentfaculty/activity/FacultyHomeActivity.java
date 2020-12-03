package com.android.studentfaculty.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.studentfaculty.bean.AttendanceBean;
import com.android.studentfaculty.bean.AttendanceSessionBean;
import com.android.studentfaculty.bean.FacultyDTO;
import com.android.studentfaculty.bean.MaterialsDTO;
import com.android.studentfaculty.bean.StudentDTO;
import com.android.studentfaculty.context.ApplicationContext;
import com.android.studentfaculty.db.DBAdapter;
import com.android.studentfaculty.utils.SharedPref;
import com.android.studentfaculty.utils.UploadFilePath;
import com.example.androidattendancesystem.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FacultyHomeActivity<AddAttandanceActivity> extends AppCompatActivity {

    private static final int EXTERNAL_PERMISSION = 321;
    private static final int PICK_FILE_RESULT_CODE = 405;
    Button submit;
    Button viewAttendance;
    Button logoutBT;
    Button uploadBT;
    Button viewTotalAttendance;
    Spinner spinnerbranch, spinneryear, spinnerSubject, spinnerLang;
    String branch = "CSE";
    String lang = "Select Subject";
    String year = "1st";
    String subject = "SC";
    AttendanceSessionBean attendanceSessionBean;
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    private String[] branchString = new String[]{"CSE", "ECE", "EEE", "IT", "others"};
    private String[] spinnerSubjects = new String[]{"Select Subject", "PHY", "CHE", "MATHS", "ENGLISH"};
    private String[] yearString = new String[]{"1st", "2nd", "3rd", "4th"};
    private String[] subjectSEString = new String[]{"SC", "MC"};
    private String[] subjectTEString = new String[]{"GT", "CN"};
    private String[] subjectBEString = new String[]{"DS", "NS"};
    private String[] subjectFinal = new String[]{"M3", "DS", "M4", "CN", "M5", "NS"};
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
    private Long expectedSizeInBytes = Long.valueOf(1024 * 1024 * 9);
    private Long selectedFileSizesInBytes = Long.valueOf(0);
    private Long selectedFileSizeInBytes = Long.valueOf(0);
    private HashMap<String, File> fileList = new HashMap();
    private ArrayList<String> fileNamesList = new ArrayList();
    private File selectedFile;
    private String selectedFileName;

    private static void loadFileFromGallery(Activity activity) {
        int permission = PermissionChecker.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_PERMISSION);
        } else {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            activity.startActivityForResult(chooseFile, PICK_FILE_RESULT_CODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);

        //Assume subject will be SE
        //subjectFinal = subjectSEString;

        spinnerbranch = findViewById(R.id.spinner1);
        spinneryear = findViewById(R.id.spinneryear);
        spinnerSubject = findViewById(R.id.spinnerSE);
        spinnerLang = findViewById(R.id.spinnerSubjects);
        logoutBT = findViewById(R.id.logoutBT);


        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);
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
        ArrayAdapter<String> adapter_Subjects = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerSubjects);
        adapter_Subjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapter_Subjects);
        spinnerLang.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                lang = (String) spinnerLang.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        logoutBT.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.removePref();
                Intent intent = new Intent(FacultyHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finishAffinity();
            }
        });

        ///......................spinner2
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);
        spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year = (String) spinneryear.getSelectedItem();

				/*if(year.equalsIgnoreCase("se"))
				{
					subjectFinal = subjectSEString;
				}
				else if(year.equalsIgnoreCase("te"))
				{
					subjectFinal = subjectTEString;
				}
				else if(year.equalsIgnoreCase("be"))
				{
					subjectFinal = subjectBEString;
				}*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                subject = (String) spinnerSubject.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        date = findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = findViewById(R.id.DateEditText);
        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);

            }
        });

        submit = findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyDTO bean = ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(subject);

                DBAdapter dbAdapter = new DBAdapter(FacultyHomeActivity.this);
                int sessionId = dbAdapter.addAttendanceSession(attendanceSessionBean);

                ArrayList<StudentDTO> studentBeanList = dbAdapter.getAllStudentByBranchYear();
                ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);
            }
        });

        uploadBT = findViewById(R.id.uploadBT);
        uploadBT.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lang.equalsIgnoreCase("Select Subject")) {
                    loadFileFromGallery(FacultyHomeActivity.this);
                } else {
                    Toast.makeText(FacultyHomeActivity.this, "Please select any Subject", Toast.LENGTH_SHORT).show();
                }


            }
        });
        viewAttendance = findViewById(R.id.viewAttendancebutton);
        viewAttendance.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyDTO bean = ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(subject);

                DBAdapter dbAdapter = new DBAdapter(FacultyHomeActivity.this);

                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);



            }
        });

        viewTotalAttendance = findViewById(R.id.viewTotalAttendanceButton);
        viewTotalAttendance.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyDTO bean = ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_subject(subject);

                DBAdapter dbAdapter = new DBAdapter(FacultyHomeActivity.this);

                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext) FacultyHomeActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);


            }
        });
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 405) {
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {

                if (selectedFileUri.getScheme().equalsIgnoreCase("file")) {
                    File file = new File(selectedFileUri.toString());
                    int fileSize = (int) file.length();
                    //println("This is the file size: $fileSize")
                    selectedFileSizeInBytes = Long.valueOf(fileSize);
                    setFileData(selectedFileUri);
                } else if (selectedFileUri.getScheme().equalsIgnoreCase("content")) {
                    Cursor returnCursor =
                            this.getContentResolver().query(selectedFileUri, null, null, null, null);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    String fileSize = returnCursor.getString(sizeIndex);
                    //println("This is the file size: $fileSize")
                    selectedFileSizeInBytes = Long.valueOf(fileSize);
                    setFileData(selectedFileUri);
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private final void setFileData(Uri selectedFileUri) {
        UploadFilePath filePathClass = new UploadFilePath(FacultyHomeActivity.this);
        String selectedFilePath = filePathClass.getFilePath(selectedFileUri);
        if (selectedFilePath != null && !selectedFilePath.trim().isEmpty()) {
            File file = new File(selectedFilePath);// path = your file path
            int lastSlash = file.toString().lastIndexOf('/');
            if (lastSlash >= 0) {
                String fileName = selectedFilePath.substring(lastSlash + 1);
                if (fileList.containsKey(fileName)) {
                    Toast.makeText(this, "file already exists", Toast.LENGTH_SHORT).show();
                } else {
                    selectedFileSizesInBytes += selectedFileSizeInBytes;
                    if (selectedFileSizesInBytes > expectedSizeInBytes) {
                        selectedFileSizesInBytes -= selectedFileSizeInBytes;
                        Toast.makeText(this, "Total attachments size must be under 9MB", Toast.LENGTH_SHORT).show();
                    } else {
                        ((Map) this.fileList).put(fileName, file);
                        fileNamesList.add(fileName);
                        selectedFile = file;
                        selectedFileName = fileName;

                        MaterialsDTO materialsBean = new MaterialsDTO();
                        materialsBean.setMaterial_name(fileName);
                        materialsBean.setMaterial_location(file.toString());
                        materialsBean.setLang(lang);

                        DBAdapter dbAdapter = new DBAdapter(FacultyHomeActivity.this);
                        dbAdapter.addMaterials(materialsBean);


                        Toast.makeText(getApplicationContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
                       /* DoGoodGrantDynamicUploadButton.setFilesList(fileNamesList, fileList);
                        doGoodGrantReportViewModel.fileUpload(this, file, fileName);*/
                    }
                }
            }
        }

    }

}

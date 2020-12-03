package com.android.studentfaculty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.studentfaculty.adapter.MaterialsAdapter;
import com.android.studentfaculty.bean.MaterialsDTO;
import com.android.studentfaculty.db.DBAdapter;
import com.android.studentfaculty.utils.SharedPref;
import com.example.androidattendancesystem.R;

import java.util.ArrayList;

public class StudentMaterialsViewActivity extends AppCompatActivity {
    DBAdapter dbAdapter = new DBAdapter(this);
    private RecyclerView materialsRecycler;
    private TextView noAttachementsTv;
    private TextView selectSubjectTv;
    private Spinner spinnerLang;
    private Button buttonlogout;
    private ArrayList<MaterialsDTO> materialsList;
    private MaterialsAdapter materialsAdapter;
    private String[] spinnerSubjects = new String[]{"Select Subject","PHY","CHE","MATHS","ENGLISH"};
    String lang = "Select Subject";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_studnet_materials);
        SharedPref.init(StudentMaterialsViewActivity.this);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        init();
    }

    private void init() {
        materialsRecycler = findViewById(R.id.materialsRecycler);
        noAttachementsTv = findViewById(R.id.noAttachementsTv);
        buttonlogout = findViewById(R.id.buttonlogout);
        spinnerLang = findViewById(R.id.spinnerSubjects);
        selectSubjectTv = findViewById(R.id.selectSubjectTv);


        ArrayAdapter<String> adapter_Subjects = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerSubjects);
        adapter_Subjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapter_Subjects);
        spinnerLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                lang = (String) spinnerLang.getSelectedItem();
                materialsList = dbAdapter.getAllMaterials(lang);
                if (materialsList != null && materialsList.size() > 0) {
                    materialsRecycler.setVisibility(View.VISIBLE);
                    noAttachementsTv.setVisibility(View.GONE);
                    selectSubjectTv.setVisibility(View.GONE);

                    materialsAdapter = new MaterialsAdapter(StudentMaterialsViewActivity.this, materialsList);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(StudentMaterialsViewActivity.this);
                    materialsRecycler.setLayoutManager(manager);
                    materialsRecycler.setAdapter(materialsAdapter);
                } else {
                    materialsRecycler.setVisibility(View.GONE);
                    noAttachementsTv.setVisibility(View.VISIBLE);
                    selectSubjectTv.setVisibility(View.GONE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        materialsList = dbAdapter.getAllMaterials(lang);
        if (materialsList != null && materialsList.size() > 0) {
            materialsRecycler.setVisibility(View.VISIBLE);
            noAttachementsTv.setVisibility(View.GONE);
            selectSubjectTv.setVisibility(View.GONE);


            materialsAdapter = new MaterialsAdapter(this, materialsList);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
            materialsRecycler.setLayoutManager(manager);
            materialsRecycler.setAdapter(materialsAdapter);
        } else {
            materialsRecycler.setVisibility(View.GONE);
            noAttachementsTv.setVisibility(View.VISIBLE);
            selectSubjectTv.setVisibility(View.GONE);
        }
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.removePref();
                Intent intent = new Intent(StudentMaterialsViewActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finishAffinity();
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
}

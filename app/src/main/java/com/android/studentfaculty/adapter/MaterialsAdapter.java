package com.android.studentfaculty.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.studentfaculty.bean.MaterialsDTO;
import com.example.androidattendancesystem.R;

import java.io.File;
import java.util.List;
public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.MyViewHolder> {
   List<MaterialsDTO> materialsDataList;
   private Activity activity;
   public MaterialsAdapter(Activity activity,List<MaterialsDTO> materialsDataList) {
      this.materialsDataList=materialsDataList;
      this.activity=activity;
   }
   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View itemView = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.item_materials, viewGroup, false);
      return new MyViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(MyViewHolder viewHolder, int i) {
      final MaterialsDTO data=materialsDataList.get(i);
      viewHolder.name.setText(data.getMaterial_name());
      viewHolder.viewIv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);

            if(data.getMaterial_location().toLowerCase().contains("pdf")){
               Uri uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", new File(data.getMaterial_location()));
               intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // GRANT TEMPORARY READ PERMISSION
               intent.setDataAndType(uri,"application/pdf");
            }else if(data.getMaterial_location().toLowerCase().contains("jpg")||data.getMaterial_location().toLowerCase().contains("png")||data.getMaterial_location().toLowerCase().contains("jpeg")){
               Uri uri = Uri.parse( data.getMaterial_location());
               intent.setDataAndType(uri,"image/*");
            }else if(data.getMaterial_location().toLowerCase().contains("mp4")){
               Uri uri = Uri.parse( data.getMaterial_location());
               intent.setDataAndType(uri,"video/mp4");
            }else {
               Uri uri = Uri.parse( data.getMaterial_location());
               intent.setDataAndType(uri,"image/*");
            }

            activity.startActivity(intent);
            /*activity.startActivity(new Intent(activity, MaterialNavigationActivity.class)
            .putExtra("material_name",data.getMaterial_name())
            .putExtra("material_location",data.getMaterial_location())
            );*/
         }
      });
   }

   @Override
   public int getItemCount() {
      return materialsDataList.size();
   }
   class MyViewHolder extends RecyclerView.ViewHolder {
      TextView name;
      ImageView viewIv;
      public MyViewHolder(View itemView) {
         super(itemView);
         name=itemView.findViewById(R.id.name);
         viewIv=itemView.findViewById(R.id.viewIv);
      }
   }
}
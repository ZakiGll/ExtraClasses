package com.example.extraclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdabter extends RecyclerView.Adapter<StudentAdabter.StudentsViewHolder>{


    Context context;
    ArrayList<StudentNameForList> list;

    public StudentAdabter(Context context, ArrayList<StudentNameForList> data) {
        this.context = context;
        this.list = data;

    }


    @NonNull
    @Override
    public StudentAdabter.StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_students_info_viewer,parent,false);

        return new StudentAdabter.StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdabter.StudentsViewHolder holder, int position) {

        StudentNameForList studentNameForList = list.get(position);
        holder.StudentName.setText(studentNameForList.getStudentName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StudentsViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName;
        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.StudentNameInList);
        }

    }
}

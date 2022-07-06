package com.example.extraclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesAdabter extends RecyclerView.Adapter<CoursesAdabter.CoursesViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<Cources> list;

    public CoursesAdabter(Context context, ArrayList<Cources> data,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = data;
        this.recyclerViewInterface=recyclerViewInterface;
    }


    @NonNull
    @Override
    public CoursesAdabter.CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_viewer,parent,false);

        return new CoursesAdabter.CoursesViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdabter.CoursesViewHolder holder, int position) {

        Cources course = list.get(position);
        holder.ClassName.setText(course.getCname());
        holder.ClassModule.setText(course.getModule());
        holder.ClassAddress.setText(course.getAdrs());
        holder.ClassPrice.setText(course.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CoursesViewHolder extends RecyclerView.ViewHolder{
        TextView ClassName,ClassModule,ClassAddress,ClassPrice;
        public CoursesViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            ClassName = itemView.findViewById(R.id.CourseNameInList);
            ClassModule = itemView.findViewById(R.id.CourseModuleInList);
            ClassAddress = itemView.findViewById(R.id.CourseAdressInList);
            ClassPrice = itemView.findViewById(R.id.DCourcePrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onClickItem(position);
                        }

                    }
                }
            });

        }
    }
}

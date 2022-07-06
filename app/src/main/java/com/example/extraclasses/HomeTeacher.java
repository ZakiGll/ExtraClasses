package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeTeacher extends AppCompatActivity implements RecyclerViewInterface{
    DatabaseReference databaseReference;
    RecyclerView recyclerViewT;
    ArrayList<Cources> arrayList ;
    CoursesAdabter adabter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        recyclerViewT = findViewById(R.id.recyclerViewTeacher);
        recyclerViewT.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid =user.getUid().toString();
        arrayList = new ArrayList<>();
        Query query =FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course").orderByChild("teacher").equalTo(uid);
        adabter = new CoursesAdabter(this,arrayList,this);
        recyclerViewT.setAdapter(adabter);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cources cources = dataSnapshot.getValue(Cources.class);
                  /*  cources.Cname= dataSnapshot.child("cname").getValue().toString();
                    cources.Cyear= dataSnapshot.child("cyear").getValue().toString();
                    cources.adrs= dataSnapshot.child("adrs").getValue().toString();
                    cources.time= dataSnapshot.child("time").getValue().toString();
                    cources.price= dataSnapshot.child("price").getValue().toString();
                    cources.module= dataSnapshot.child("module").getValue().toString();
                    cources.description= dataSnapshot.child("description").getValue().toString();
                    cources.date= dataSnapshot.child("date").getValue().toString();*/
                    arrayList.add(cources);
                }
                adabter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void add (View v) {
        Intent a = new Intent(getApplicationContext(),AddCourse.class);
        startActivity(a);
    }
    public void Toprofile (View v) {
        Intent a = new Intent(getApplicationContext(),ProfileTeacher.class);
        startActivity(a);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getApplicationContext(),DetailedCoursesTeacher.class);
        intent.putExtra("id",arrayList.get(position).getId());
        intent.putExtra("Cname",arrayList.get(position).getCname());
        intent.putExtra("Cyear",arrayList.get(position).getCyear());
        intent.putExtra("Cprice",arrayList.get(position).getPrice());
        intent.putExtra("Cmodule",arrayList.get(position).getModule());
        intent.putExtra("Cdisc",arrayList.get(position).getDescription());
        intent.putExtra("Cdate",arrayList.get(position).getDate());
        intent.putExtra("Ctime",arrayList.get(position).getTime());
        intent.putExtra("Cadrs",arrayList.get(position).getAdrs());
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
        startActivity(intent);
    }
}
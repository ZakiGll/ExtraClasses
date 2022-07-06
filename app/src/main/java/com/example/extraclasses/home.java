package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home extends AppCompatActivity implements RecyclerViewInterface {
    LinearLayout profileS,Favorite,filterBtn;
    String res;


    DatabaseReference databaseReference;
   // ListView cources;
    ArrayList<Cources> arrayList ;
    CoursesAdabter adabter;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.courceslist);
        Favorite = findViewById(R.id.FavoriteHomeBtn);
        profileS = findViewById(R.id.ToProfileS);
        filterBtn = findViewById(R.id.FilterBtnStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course");
        arrayList = new ArrayList<>();

       adabter = new CoursesAdabter(this,arrayList,this);
       recyclerView.setAdapter(adabter);


       databaseReference.addValueEventListener(new ValueEventListener() {
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
       Favorite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),FavoritesClasses.class);
               startActivity(intent);
           }
       });
        profileS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileStudent.class);
                startActivity(intent);
            }
        });
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder filterDialog = new AlertDialog.Builder(home.this);
                filterDialog.setTitle("Choose a year");
                String [] choices = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
                filterDialog.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        res = choices[position];
                        Toast.makeText(home.this,choices[position], Toast.LENGTH_SHORT).show();

                    }
                });
                filterDialog.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Apply your filter logic
                        arrayList.clear();
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    Cources cources = dataSnapshot.getValue(Cources.class);
                                    if (cources.getCyear().equals(res))
                                        arrayList.add(cources);
                  /*  cources.Cname= dataSnapshot.child("cname").getValue().toString();
                    cources.Cyear= dataSnapshot.child("cyear").getValue().toString();
                    cources.adrs= dataSnapshot.child("adrs").getValue().toString();
                    cources.time= dataSnapshot.child("time").getValue().toString();
                    cources.price= dataSnapshot.child("price").getValue().toString();
                    cources.module= dataSnapshot.child("module").getValue().toString();
                    cources.description= dataSnapshot.child("description").getValue().toString();
                    cources.date= dataSnapshot.child("date").getValue().toString();*/

                                }
                                adabter.notifyDataSetChanged();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
                filterDialog.show();

            }
        });




    }


    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getApplicationContext(),DetailedCourseStudent.class);
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
        Intent intent = new Intent(getApplicationContext(),home.class);
        startActivity(intent);
    }
}


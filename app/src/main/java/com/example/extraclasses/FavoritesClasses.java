package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoritesClasses extends AppCompatActivity implements RecyclerViewInterface{
    DatabaseReference databaseReference,databaseReference1;
    ArrayList<Cources> arrayList ;
    CoursesAdabter adabter;
    RecyclerView recyclerView;
    String uid;
    TextView textView;
    LinearLayout homebtn,profilebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_classes);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid().toString();
        recyclerView = findViewById(R.id.Favoritecourceslist);
        textView = findViewById(R.id.FavClassNames);
        homebtn = findViewById(R.id.FromFavToHome);
        profilebtn = findViewById(R.id.FromFavToProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course");
        arrayList = new ArrayList<>();

        adabter = new CoursesAdabter(this,arrayList,this);
        recyclerView.setAdapter(adabter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String studentids =dataSnapshot.getKey();
                    databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course").child(studentids).child("students");
                   //
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                            for (DataSnapshot dataSnapshot1 : snapshot1.getChildren()){
                                //textView.setText(dataSnapshot1.getKey());
                                if(dataSnapshot1.getKey().equals(uid)){
                                    Cources cources = dataSnapshot.getValue(Cources.class);

                                    arrayList.add(cources);
                                }
                            }
                            adabter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                   /* if (dataSnapshot.child(dataSnapshot.getKey()).child("students").getKey()==uid){

                    }*/

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),home.class);
                startActivity(intent);
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileStudent.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getApplicationContext(),DetailedFavoriteCourse.class);
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
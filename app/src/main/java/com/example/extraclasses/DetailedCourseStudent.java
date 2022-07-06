package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailedCourseStudent extends AppCompatActivity {
    TextView name;
    TextView year;
    TextView price;
    TextView module;
    TextView desc;
    TextView date;
    TextView time;
    TextView adrs;
    String uid;
    String id;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    Button register;
    String nameS;
    Button map;
    LinearLayout home,fav,profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course_student);
         id = getIntent().getStringExtra("id");
        String Cyear = getIntent().getStringExtra("Cyear");
        String Cname = getIntent().getStringExtra("Cname");
        String Cprice = getIntent().getStringExtra("Cprice");
        String Cmodule = getIntent().getStringExtra("Cmodule");
        String Cdisc = getIntent().getStringExtra("Cdisc");
        String Cdate = getIntent().getStringExtra("Cdate");
        String Ctime = getIntent().getStringExtra("Ctime");
        String Cadrs = getIntent().getStringExtra("Cadrs");

        name = findViewById(R.id.DClassName);
        module = findViewById(R.id.DclassModule);
        year = findViewById(R.id.DclassYear);
        price = findViewById(R.id.DCourcePrice);
        desc = findViewById(R.id.DclassDescription);
        date = findViewById(R.id.DclassDate);
        time = findViewById(R.id.DclassTime);
        adrs = findViewById(R.id.DclassAdrs);
        register = findViewById(R.id.RegisterInCourseBtn);
        map = findViewById(R.id.ViewOnMapSBtn);
        home = findViewById(R.id.DetailedToHome);
        fav = findViewById(R.id.DetailedToFav);
        profile = findViewById(R.id.DetailedToProfile);


        name.setText(Cname);
        year.setText(Cyear);
        price.setText(Cprice);
        module.setText(Cmodule);
        desc.setText(Cdisc);
        date.setText(Cdate);
        time.setText(Ctime);
        adrs.setText(Cadrs);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid().toString();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                nameS = snapshot.child(uid).child("name").getValue().toString();
                //String ageD = snapshot.child(uid).child("age").getValue().toString();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

       register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course");
                databaseReference1.child(id).child("students").child(uid).setValue(nameS);
                Toast.makeText(DetailedCourseStudent.this, "Registred to course",
                        Toast.LENGTH_SHORT).show();
            }
        });
       map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gps = Cadrs.replace(" ","+");
                // Map point based on address
                Uri location = Uri.parse("geo:0,0?q="+gps);
// Or map point based on latitude/longitude
// Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),home.class);
               startActivity(intent);
           }
       });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FavoritesClasses.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileStudent.class);
                startActivity(intent);
            }
        });


    }


}
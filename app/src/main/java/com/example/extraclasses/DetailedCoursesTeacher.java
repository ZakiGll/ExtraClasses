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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailedCoursesTeacher extends AppCompatActivity {
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
    Button sup;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    Button register;
    LinearLayout ToHome,ToAdd,ToProfile;
    String nameS;
    RecyclerView recyclerView;
    ArrayList<StudentNameForList> arrayList1;
    StudentAdabter studentAdabter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_courses_teacher);
        id = getIntent().getStringExtra("id");
        String Cyear = getIntent().getStringExtra("Cyear");
        String Cname = getIntent().getStringExtra("Cname");
        String Cprice = getIntent().getStringExtra("Cprice");
        String Cmodule = getIntent().getStringExtra("Cmodule");
        String Cdisc = getIntent().getStringExtra("Cdisc");
        String Cdate = getIntent().getStringExtra("Cdate");
        String Ctime = getIntent().getStringExtra("Ctime");
        String Cadrs = getIntent().getStringExtra("Cadrs");
        name = findViewById(R.id.DClassNameT);
        module = findViewById(R.id.DclassModuleT);
        year = findViewById(R.id.DclassYearT);
        price = findViewById(R.id.DCourcePriceT);
        desc = findViewById(R.id.DclassDescriptionT);
        date = findViewById(R.id.DclassDateT);
        time = findViewById(R.id.DclassTimeT);
        adrs = findViewById(R.id.DclassAdrsT);
        register = findViewById(R.id.DeleteBtn);
        ToProfile = findViewById(R.id.AccountBtnDT);
        ToAdd = findViewById(R.id.AddBtnDT);
        ToHome = findViewById(R.id.HomeBtnDT);
        sup = findViewById(R.id.DeleteBtn);


        name.setText(Cname);
        year.setText(Cyear);
        price.setText(Cprice);
        module.setText(Cmodule);
        desc.setText(Cdisc);
        date.setText(Cdate);
        time.setText(Ctime);
        adrs.setText(Cadrs);
        recyclerView = findViewById(R.id.StudentlistTeacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList1 = new ArrayList<>();
        studentAdabter = new StudentAdabter(this,arrayList1);
        recyclerView.setAdapter(studentAdabter);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users");
        databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Course").child(id).child("students");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String Sid = dataSnapshot.getKey();

                    StudentNameForList studentNameForList = new StudentNameForList(snapshot.child(Sid).getValue().toString());

                                  // String nameStudent = dataSnapshot.getValue().toString();
                    arrayList1.add(studentNameForList);




                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        ToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
                startActivity(intent);
            }
        });
        ToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCourse.class);
                startActivity(intent);
            }
        });
        ToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileTeacher.class);
                startActivity(intent);
            }
        });

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailedCoursesTeacher.this);
                builder.setTitle("Are you sure ?");
                builder.setMessage("Deleted data can't be undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("Course").child(id).removeValue();
                        Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
                        startActivity(intent);
                        Toast.makeText(DetailedCoursesTeacher.this, "Course Deleted", Toast.LENGTH_SHORT).show();


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DetailedCoursesTeacher.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });







    }
}
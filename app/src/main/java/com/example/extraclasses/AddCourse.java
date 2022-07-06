package com.example.extraclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddCourse extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String date;

    String nameD;

    EditText className ;
    EditText classyear ;
    EditText classModule ;
    EditText classDesciption;
    EditText classAdress;
    EditText classTime ;
    EditText classPrice;
    String uid;
    private DatabaseReference databaseReference;
    DatabaseReference databaseReference1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        uid =user.getUid().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://extra-classes-e11d1-default-rtdb.firebaseio.com/Users");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Course");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameD = snapshot.child(uid).child("name").getValue().toString();
                //String ageD = snapshot.child(uid).child("age").getValue().toString();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
         className = findViewById(R.id.Classname);
         classyear = findViewById(R.id.ClassYear);
         classModule = findViewById(R.id.ClassModule);
         classDesciption = findViewById(R.id.ClasssDesciption);
         classAdress = findViewById(R.id.ClassAdress);
         classTime = findViewById(R.id.ClassTime);
        classPrice = findViewById(R.id.ClassPrice);



    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                 date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + "/ " + day + "/ " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


    public void AddCourse(View v){
       String name = className.getText().toString();
        String module = classModule.getText().toString();
        String year = classyear.getText().toString();
        String description = classDesciption.getText().toString();
        String adress = classAdress.getText().toString();
        String time = classTime.getText().toString();
        String price = classPrice.getText().toString();


        if(name.isEmpty()){
            className.setError("Enter the name of the class !");
            className.requestFocus();
            return;
        }
        if(module.isEmpty()){
            classModule.setError("Enter the module !");
            classModule.requestFocus();
            return;
        }
        if(year.isEmpty()){
            classyear.setError("Enter the year !");
            classyear.requestFocus();
            return;
        }
        if(adress.isEmpty()){
            classAdress.setError("Enter the address !");
            classAdress.requestFocus();
            return;
        }
        if(time.isEmpty()){
            classTime.setError("Enter the time !");
            classTime.requestFocus();
            return;
        }
        if(description.isEmpty()){
            classDesciption.setError("Enter the description !");
            classDesciption.requestFocus();
            return;
        }
        if(price.isEmpty()){
            classPrice.setError("Enter the price !");
            classPrice.requestFocus();
            return;
        }


        String id = databaseReference1.push().getKey();

        Cources course =  new Cources(name,year,module,description,adress,time,date,price,uid,id);
        databaseReference1.child(id).setValue(course);
        Toast.makeText(AddCourse.this, "Class added",
                Toast.LENGTH_SHORT).show();
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
        startActivity(intent);
    }

}
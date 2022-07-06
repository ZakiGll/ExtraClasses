package com.example.extraclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailedFavoriteCourse extends AppCompatActivity {
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
    Button map;
    LinearLayout home,profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_favorite_course);
        String Cyear = getIntent().getStringExtra("Cyear");
        String Cname = getIntent().getStringExtra("Cname");
        String Cprice = getIntent().getStringExtra("Cprice");
        String Cmodule = getIntent().getStringExtra("Cmodule");
        String Cdisc = getIntent().getStringExtra("Cdisc");
        String Cdate = getIntent().getStringExtra("Cdate");
        String Ctime = getIntent().getStringExtra("Ctime");
        String Cadrs = getIntent().getStringExtra("Cadrs");

        name = findViewById(R.id.DClassNameF);
        module = findViewById(R.id.DclassModuleF);
        year = findViewById(R.id.DclassYearF);
        price = findViewById(R.id.DCourcePriceF);
        desc = findViewById(R.id.DclassDescriptionF);
        date = findViewById(R.id.DclassDateF);
        time = findViewById(R.id.DclassTimeF);
        adrs = findViewById(R.id.DclassAdrsF);
        home = findViewById(R.id.FToHome);
        profil = findViewById(R.id.FToProfileS);

        map = findViewById(R.id.ViewOnMapFavBtn);


        name.setText(Cname);
        year.setText(Cyear);
        price.setText(Cprice);
        module.setText(Cmodule);
        desc.setText(Cdisc);
        date.setText(Cdate);
        time.setText(Ctime);
        adrs.setText(Cadrs);
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
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileStudent.class);
                startActivity(intent);

            }
        });

    }


}
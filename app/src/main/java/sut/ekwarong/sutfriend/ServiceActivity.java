package sut.ekwarong.sutfriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    // Explicit
    private TextView textView;
    private ListView listView;
    private String loginString;
    private String[] nameStrings, imageStrings, genderStrings,
            addressStrings, phoneStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Bind Widget
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);

        // Get Value from Intent
        loginString = getIntent().getStringExtra("Login");
        nameStrings = getIntent().getStringArrayExtra("Name");
        imageStrings = getIntent().getStringArrayExtra("Image");
        genderStrings = getIntent().getStringArrayExtra("Gender");
        addressStrings = getIntent().getStringArrayExtra("Address");
        phoneStrings = getIntent().getStringArrayExtra("Phone");

        // Show Text
        textView.setText("Welcome " + loginString);

        // Show ListView
        MyAdapter myAdapter = new MyAdapter(this, nameStrings, genderStrings, imageStrings);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
                intent.putExtra("Image", imageStrings[i]);
                intent.putExtra("Name", nameStrings[i]);
                intent.putExtra("Gender", genderStrings[i]);
                intent.putExtra("Address", addressStrings[i]);
                intent.putExtra("Phone", phoneStrings[i]);
                startActivity(intent);

            }   // OnItemClick
        });

    }   // Main Method
}   // Main Class

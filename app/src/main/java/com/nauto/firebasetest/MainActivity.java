package com.nauto.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {
    Button sendCalories;
    TextView totalCals;
    EditText calsEntered;
    Firebase mRefTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefTotal = new Firebase("https://flickering-fire-6281.firebaseio.com/totalCal");
        sendCalories = (Button) findViewById(R.id.sendCalories);
        totalCals = (TextView) findViewById(R.id.fireBaseText);
        calsEntered= (EditText) findViewById(R.id.fireBaseTextToSend);
        mRefTotal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                if (data != null)
                    totalCals.setText(data);
                else
                    totalCals.setText("no data");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        sendCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int total=0;
                    total= Integer.parseInt(calsEntered.getText().toString())+ parseInt(totalCals.getText().toString());

                    mRefTotal.setValue(""+total);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}

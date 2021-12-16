package com.example.hashtagmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText input, output;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI Initializations
        input = (EditText) findViewById(R.id.input);
        output = findViewById(R.id.output);
        submit = findViewById(R.id.submit);

//        String test = "Hello Test";
//        String[] splitText = test.split("\\s+");


        submit.setOnClickListener(view -> {
            String outputText = createHashtags();

            output.setText(outputText);
            output.setTextColor(ContextCompat.getColor(this, R.color.blue));

            
        });

    }

    private String createHashtags() {
        String value = input.getText().toString().trim();
        String[] splitText = value.split("\\s+");
        String outputTextView = "";

        ArrayList<String> outputList = new ArrayList<String>();

        for (String s : splitText) {
            String outputText = "#" + s.toString();
            outputList.add(outputText);
        }

        for(String s : outputList){
            outputTextView += s + " ";
        }

        return outputTextView;

    }
}
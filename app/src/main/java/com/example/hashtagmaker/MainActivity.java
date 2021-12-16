package com.example.hashtagmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView output;
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

        //Submit button on click listener
        submit.setOnClickListener(view -> {
            String value = input.getText().toString().trim();

            //Checking if Edit Text field is empty
            if(TextUtils.isEmpty(value)){
                input.setError("Text Field is Empty");
                Toast.makeText(this, "Text Field is Empty", Toast.LENGTH_SHORT).show();
                return;
            }

            String outputText = createHashtags(value); //Function that takes text from edit text and splits it into hashtags

            //Displaying the split text with prepended hashtags
            output.setText(outputText);
            output.setTextColor(ContextCompat.getColor(this, R.color.blue)); //UI Gimmick

            //Copying the text to clip board
            copyToClipBoard(outputText);
        });

    }

    private void copyToClipBoard(String outputText) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("hashtags", outputText);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Hashtags Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    private String createHashtags(String value) {
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
package com.example.dan.questclient;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.attr.id;

public class CreatorActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    public int nr = 1;
    public TextView nameText;
    public TextView timeText;
    public TextView edit_text;
    public TextView edit_text2;
    public  View rowView;
    LayoutInflater inflater;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        nameText = (TextView)findViewById(R.id.nameText);
        timeText = (TextView)findViewById(R.id.timeText);

    }
    public void onAddField(View v) {
         inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

        nr++;


    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        nr--;
    }
    public void onCreate(View v) {
        System.out.println(nr);
        System.out.println(parentLinearLayout.getChildCount());
        System.out.println(nameText.getText());
        System.out.println(timeText.getText());

        if (nr >= 1){
            for (int i = 3; i < nr+3; i++){
                edit_text = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text);//aista lucreaza
                edit_text2 = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text2);//aista lucreaza
                System.out.println(edit_text.getText());
                System.out.println(edit_text2.getText());
            }
//            edit_text = (EditText) parentLinearLayout.getChildAt(3).findViewById(R.id.edit_text);//aista lucreaza
//            System.out.println(edit_text.getText());
//            edit_text = (EditText) parentLinearLayout.getChildAt(4).findViewById(R.id.edit_text);//aista lucreaza
//            System.out.println(edit_text.getText());
//            edit_text = (EditText) parentLinearLayout.getChildAt(5).findViewById(R.id.edit_text);//aista lucreaza
//            System.out.println(edit_text.getText());
        }else if(nr == 1) {
            edit_text = (EditText)findViewById(R.id.edit_text);
            System.out.println(edit_text.getText());
            System.out.println(edit_text2.getText());
        }else {
            System.out.println("Eroare");
        }







    }



}

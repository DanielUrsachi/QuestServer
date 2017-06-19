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

import java.io.Serializable;

import static android.R.attr.id;

public class CreatorActivity extends AppCompatActivity implements Serializable {

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
        Event event = new Event();
        System.out.println(nr);
        System.out.println(parentLinearLayout.getChildCount());
        System.out.println(nameText.getText());
        System.out.println(timeText.getText());
        ///
        String name = String.valueOf(nameText.getText());
        event.setName(String.valueOf(nameText.getText()));
        event.setTime(Integer.parseInt(String.valueOf(timeText.getText())));
        event.setNr(nr);

        if (nr >= 1){
            for (int i = 3; i < nr+3; i++){
                edit_text = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text);//aista lucreaza
                edit_text2 = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text2);//aista lucreaza
                System.out.println(edit_text.getText());
                System.out.println(edit_text2.getText());
                ///
                event.setHint(String.valueOf(edit_text.getText()));
                event.setPass(String.valueOf( edit_text2.getText()));
            }
//
        }else if(nr == 1) {
            edit_text = (EditText)findViewById(R.id.edit_text);
            System.out.println(edit_text.getText());
            System.out.println(edit_text2.getText());
            ///
            event.setHint(String.valueOf(edit_text.getText()));
            event.setPass(String.valueOf( edit_text2.getText()));
        }else {
            System.out.println("Eroare");
        }

        if(nr>=1){
            //ClientCreator.sendCreator(event);
            //ClientCreator.sendCreator(event);
            ClientCreator clientCreator = (ClientCreator) new ClientCreator(event).execute("");
            //nu primeste rs
            if (clientCreator.getrequest() == -1){
                System.out.println("UI: ocupat!");
            }if (clientCreator.getrequest() == 1){
                System.out.println("UI: inregistrat");
            }


        }









    }



}

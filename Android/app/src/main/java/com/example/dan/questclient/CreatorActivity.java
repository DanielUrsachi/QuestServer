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
import android.widget.Toast;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

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
    public void onCreateRoom(View v) {
        Event event = new Event();

        event.setName(String.valueOf(nameText.getText()));
        event.setTime(Integer.parseInt(String.valueOf(timeText.getText())));
        event.setNr(nr);

        if (nr >= 1){
            for (int i = 3; i < nr+3; i++){
                edit_text = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text);
                edit_text2 = (EditText) parentLinearLayout.getChildAt(i).findViewById(R.id.edit_text2);

                event.setHint(String.valueOf(edit_text.getText()));
                event.setPass(String.valueOf( edit_text2.getText()));
            }

        }else if(nr == 1) {
            edit_text = (EditText)findViewById(R.id.edit_text);

            event.setHint(String.valueOf(edit_text.getText()));
            event.setPass(String.valueOf( edit_text2.getText()));
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Adaugati macar un element", Toast.LENGTH_SHORT);
            toast.show();
        }

        //final
        if(nr>=1){
            ClientCreator clientCreator = new ClientCreator();
            clientCreator.setEvent(event);

            try {
                clientCreator.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(clientCreator.getRequest() == 1){
                Toast toast = Toast.makeText(getApplicationContext(), "Grupa a fost inregistrata cu succes", Toast.LENGTH_SHORT);
                toast.show();
            }else if(clientCreator.getRequest() == 0){
                Toast toast = Toast.makeText(getApplicationContext(), "Acest nume este deja ocupat", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                Toast toast = Toast.makeText(getApplicationContext(), "Grupa nu este inregistrata, avem o problema de retea", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

}

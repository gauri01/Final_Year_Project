package com.gauri.alertsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ShowListActivity extends AppCompatActivity {
    EditText etname1,etname2,etname3,etname4,etname5,etname6,etname7,etname8,
    etname9,etname10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        initialise();
    }

    private void initialise() {
        etname1 = (EditText) findViewById(R.id.editText11);
        etname2 = (EditText) findViewById(R.id.editText2);
        etname3 = (EditText) findViewById(R.id.editText3);
        etname4 = (EditText) findViewById(R.id.editText4);
        etname5 = (EditText) findViewById(R.id.editText5);
        etname6 = (EditText) findViewById(R.id.editText6);
        etname7 = (EditText) findViewById(R.id.editText7);
        etname8 = (EditText) findViewById(R.id.editText8);
        etname9 = (EditText) findViewById(R.id.editText9);
        etname10 = (EditText) findViewById(R.id.editText10);

        etname1.setText(etname1.getText().toString());
        etname2.setText(etname2.getText().toString());
        etname3.setText(etname3.getText().toString());
        etname4.setText(etname4.getText().toString());
        etname5.setText(etname5.getText().toString());
        etname6.setText(etname6.getText().toString());
        etname7.setText(etname7.getText().toString());
        etname8.setText(etname8.getText().toString());
        etname9.setText(etname9.getText().toString());
        etname10.setText(etname10.getText().toString());
    }
}

package com.gauri.alertsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editTextAnswer, editTextEmail;
    Spinner spinnerQuestion;
    Button btnCreateAccount;
    Context context = this;
    DBManager DBManager;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        DBManager = new DBManager(this);
        DBManager = DBManager.open();

        initialise();
    }

    private void initialise() {
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        spinnerQuestion = (Spinner) findViewById(R.id.spinnerQuestion);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String answer = editTextAnswer.getText().toString();
                String email = editTextEmail.getText().toString();
                String question = spinnerQuestion.getSelectedItem().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("answer", answer);
                editor.commit();

                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")||email.equals("")|| answer.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if(!emailValidator(email)){
                    Toast.makeText(getApplicationContext(),
                            "Invalid email", Toast.LENGTH_LONG)
                            .show();
                    return;
                }else {

                    DBManager.insertLogin(userName, password, email, question, answer);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(RegistrationActivity.this,
                            HomeActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }
    /**
     * validate email address format.
     */
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        DBManager.close();
        finish();
    }
}

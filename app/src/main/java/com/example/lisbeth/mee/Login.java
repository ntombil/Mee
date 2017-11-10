package com.example.lisbeth.mee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    Button btnCreateAccount;
    Context context = this;
    Person loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginDataBaseAdapter = new Person(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText()
                        .toString();
                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(Login.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();

    }
}

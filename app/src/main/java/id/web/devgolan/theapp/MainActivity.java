package id.web.devgolan.theapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//classpath 'com.android.tools.build:gradle:2.1.1'

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUpact;
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText editTextUserName;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignUpact = (Button) findViewById(R.id.btnSignup);

        editTextUserName= (EditText) findViewById(R.id.editTextUserNameToLogin);
        editTextPassword= (EditText) findViewById(R.id.editTextPasswordToLogin);
        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if (loginDataBaseAdapter.Login(userName,password)) {
                 Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
//                    Intent intent2 = new Intent(MainActivity.this, HomeActivity.class);
//                  startActivity(intent2);


                }
                else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnSignUpact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
        @Override
        protected void onDestroy () {
            // TODO Auto-generated method stub
            super.onDestroy();

            loginDataBaseAdapter.close();
        }

    } //endest curly brackets



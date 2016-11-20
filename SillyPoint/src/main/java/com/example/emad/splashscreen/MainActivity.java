package com.example.emad.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.*;


public class MainActivity extends ActionBarActivity {
    MyDBHandler dbHandler;
    @BindView(R.id.input_email) android.widget.EditText _emailText;
    @BindView(R.id.input_password) android.widget.EditText _passwordText;

    @BindView(R.id.btn_login) android.support.v7.widget.AppCompatButton _loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dbHandler = new MyDBHandler(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();

            }
        });

    }

    public void login() {


        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        //Display Authentication Dialog for about 3 seconds
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run()
                    {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    public void onLoginSuccess()
    {
        _loginButton.setEnabled(true);
        WelcomePage();
        finish();
    }


    public void onLoginFailed()
    {
        android.widget.Toast.makeText(getBaseContext(), "Login failed", android.widget.Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate()
    {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        boolean check = dbHandler.UserExists(email,password);

        if (check)
        {
            android.widget.Toast.makeText(getBaseContext(), "User Exists!", android.widget.Toast.LENGTH_LONG).show();
            valid = true;

        }
        else
        {
            valid = false;
        }

        if (email.isEmpty()) {
            _emailText.setError("enter a valid Username");
            valid = false;
        }
        else if (!email.isEmpty() && check)
        {
            _emailText.setError(null);
            valid = true;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10)
        {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (!password.isEmpty() && check)
        {
            _passwordText.setError(null);
            valid = true;
        }
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connec != null && (
                (connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) ||
                        (connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED))) {

            //You are connected, do something online.
            valid = true;
        } else if (connec != null && (
                (connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) ||
                        (connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED ))) {

            //Not connected.
            valid = false;
            android.widget.Toast.makeText(getBaseContext(), "No Internet Connection!", android.widget.Toast.LENGTH_LONG).show();

        }

            return valid;

    }


    public void SecondPage(View v)
    {
       Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void WelcomePage()
    {
        Intent intent = new Intent(this, WelcomeMenu.class);
        startActivity(intent);
        finish();
    }


}

package com.example.retrofitappdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface RetrofitInterface;
    private String BASE_URL = "https://ytamitxspectre.run.goorm.io";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface = retrofit.create(RetrofitInterface.class);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginDialog();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignupDialog();
            }
        });
    }

    private void handleLoginDialog() {
        View v = getLayoutInflater().inflate(R.layout.login_details, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(v).show();

        Button loginBtn = v.findViewById(R.id.login);
        final EditText emailedit = v.findViewById(R.id.emailfield);
        final EditText passwordedit = v.findViewById(R.id.passwordfield);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("username", emailedit.getText().toString());
                map.put("password", passwordedit.getText().toString());

                Call<LoginResult> call = RetrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.code() == 200) {

                            LoginResult result = response.body();

                            //AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            //builder1.setTitle(result.getName());
                            //builder1.setMessage(result.getEmail());

                            //builder1.show();
                            Toast.makeText(MainActivity.this, "Welcome " + result.getUsername(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this,SearchDevice.class);
                            startActivity(i);
                        } else if (response.code() == 400) {
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void  handleSignupDialog(){
        View v = getLayoutInflater().inflate(R.layout.signup_details, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(v).show();

        Button signUpBtn = v.findViewById(R.id.signup);
        final EditText emailedit = v.findViewById(R.id.emailsignup);
        final EditText passwordedit = v.findViewById(R.id.passwordsignup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map = new HashMap<>();
                map.put("username",emailedit.getText().toString());
                map.put("password",passwordedit.getText().toString());

                Call<Void> call = RetrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code()==200) {
                            Toast.makeText(MainActivity.this, "Signed Up successfully!", Toast.LENGTH_SHORT).show();
                        } else if (response.code()==400){
                            Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

    public void compare(View view) {
        Intent i = new Intent(MainActivity.this, Compare.class);
        startActivity(i);
    }

    public void displaynews(View view) {
        Intent i = new Intent(MainActivity.this, Newspage.class);
        startActivity(i);
    }
}

package com.example.retrofitdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofitdemo.API.GitAPI;
import com.example.retrofitdemo.model.GitModel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_ENDPOINT = "https://api.github.com";

    private TextView userInfoTextView;
    private EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //show userInfo after fetch
        userInfoTextView = (TextView) findViewById(R.id.userInfoTextView);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);

        Button fetchUserInfoButton = (Button) findViewById(R.id.fetchUserInfo);
        fetchUserInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1. Create rest adapter with the API class
                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_ENDPOINT).build();
                GitAPI gitAPI = restAdapter.create(GitAPI.class);

                //2. call api method from interface
                gitAPI.getFeedFromUser(userNameEditText.getText().toString(), new Callback<GitModel>() {
                    @Override
                    public void success(GitModel gitModel, Response response) {
                        userInfoTextView.setText("Github Name : "+ gitModel.getName() +
                                "\nRepo Url : " + gitModel.getRepos_url() + "\nLocation : " + gitModel.getLocation());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        userInfoTextView.setText("Error: " + error.getMessage());
                    }
                });

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

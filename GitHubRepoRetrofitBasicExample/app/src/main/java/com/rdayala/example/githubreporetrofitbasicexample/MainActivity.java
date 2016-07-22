package com.rdayala.example.githubreporetrofitbasicexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("Repo" , "Calling Retrofit call");

        // Change base url to GitHub API
        ServiceGenerator.changeApiBaseUrl("https://api.github.com/");

        // Create a simple REST adapter which points to GitHub’s API
        GitHubService service = ServiceGenerator.createService(GitHubService.class);

        // Fetch and print a list of repositories for user “rdayala”
        Call<List<GitHubRepo>> call = service.reposForUser("rdayala");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                if(response.isSuccessful()) {
                    for ( GitHubRepo repo : response.body())
                    Log.d("Repo : " , repo.getName() + " (ID : " + repo.getId() + ") ");
                }
                else
                {
                    Log.e("Request failed: " , "Cannot request GitHub repositories");
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.e("Error fetching repos", t.getMessage());
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

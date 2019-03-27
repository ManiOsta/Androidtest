package com.example.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResponse();
    }

    private void getResponse() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviePojo> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<MoviePojo>() {
            @Override
            public void onResponse(Call<MoviePojo> call, Response<MoviePojo> response) {
                List<MoviePojo.Movie> movies = response.body().getMovies();
              //  recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_list_row, getApplicationContext()));
                Log.d("TAGA", "Number of movies received: " + movies.get(0).getTitle());
                Log.d("TAGA", "OnResponse");
            }

            @Override
            public void onFailure(Call<MoviePojo> call, Throwable t) {
                Log.e("TAGA", t.toString());
                Log.d("TAGA", "OnError");
                //throw new RuntimeException("on error");
            }
        });
    }
}

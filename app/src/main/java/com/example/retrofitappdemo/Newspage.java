package com.example.retrofitappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Newspage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<NewsData> my_list;
    private Retrofit retrofit;
    private RetrofitInterface RetrofitInterface;
    private String TAG;
    private TextView loading;
    private String BASE_URL = "https://ytamitxspectre.run.goorm.io";
    List<NewsData> str = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspage);
        gridLayoutManager = new GridLayoutManager(this,1);
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        loading = findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                RetrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<NewsData>> call = RetrofitInterface.executeNews();
        call.enqueue(new Callback<List<NewsData>>() {
            @Override
            public void onResponse(Call<List<NewsData>> call, Response<List<NewsData>> response) {
                if (response.code() == 200) {
                    str = new ArrayList<>(response.body());
                    recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new CustomAdapter(Newspage.this,str);
                    progressBar.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<NewsData>> call, Throwable t) {
                Toast.makeText(Newspage.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.retrofitappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDevice extends AppCompatActivity {
    public TextView mdev;
    public TextView mos;
    public TextView sversion;
    public TextView mprocessor;
    public TextView mcpu;
    public TextView mgpu;
    public TextView mdisplay;
    public TextView mstorage;
    public TextView mmaincamera;
    public TextView mfrontcamera;
    public TextView mvideo;
    public TextView msensors;
    public TextView mbattery;
    public ImageView devimg;
    private Retrofit retrofit;
    private RetrofitInterface RetrofitInterface;
    private String TAG;
    private String BASE_URL = "https://ytamitxspectre.run.goorm.io";
    public Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_device);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface = retrofit.create(RetrofitInterface.class);

        mdev = findViewById(R.id.devname);
        sversion = findViewById(R.id.softwareversion);
        mos = findViewById(R.id.operatingsystem);
        mprocessor = findViewById(R.id.processor);
        mcpu = findViewById(R.id.CPU);
        mgpu = findViewById(R.id.GPU);
        mdisplay = findViewById(R.id.display);
        mstorage = findViewById(R.id.storage_ram);
        mmaincamera = findViewById(R.id.maincamera);
        mfrontcamera = findViewById(R.id.frontcamera);
        mvideo = findViewById(R.id.videorecording);
        msensors = findViewById(R.id.sensors);
        mbattery = findViewById(R.id.battery);
        devimg = findViewById(R.id.devimg);


        final AutoCompleteTextView autocomplete = findViewById(R.id.searchdev);
        Call<List<DeviceData>> call = RetrofitInterface.executeDevname();
        call.enqueue(new Callback<List<DeviceData>>() {
            @Override
            public void onResponse(Call<List<DeviceData>> call, Response<List<DeviceData>> response) {
                if (response != null && response.isSuccessful()) {
                    List<String> str = new ArrayList<String>();
                    for (DeviceData d : response.body()) {
                        str.add(d.getDevname());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (SearchDevice.this, android.R.layout.simple_list_item_1, str.toArray(new String[0]));
                    autocomplete.setThreshold(2);
                    autocomplete.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DeviceData>> call, Throwable t) {
                Toast.makeText(SearchDevice.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });

        search = findViewById(R.id.searchbtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText devicename = findViewById(R.id.searchdev);

                HashMap<String,String> map = new HashMap<>();
                map.put("devname",devicename.getText().toString());

                Toast.makeText(SearchDevice.this, ""+devicename.getText().toString(), Toast.LENGTH_SHORT).show();

                Call<List<DeviceData>> call = RetrofitInterface.executeSearch(map);
                call.enqueue(new Callback<List<DeviceData>>() {
                    @Override
                    public void onResponse(Call<List<DeviceData>> call, Response<List<DeviceData>> response) {
                        if(response.code() == 200){
                            List<String> str = new ArrayList<String>();
                            for (DeviceData d : response.body()) {
                                str.add(d.getDevname());
                                str.add(d.getSoftwareversion());
                                str.add(d.getOperatingsystem());
                                str.add(d.getProcessor());
                                str.add(d.getCPU());
                                str.add(d.getGPU());
                                str.add(d.getDisplay());
                                str.add(d.getStorage_ram());
                                str.add(d.getMaincamera());
                                str.add(d.getFrontcamera());
                                str.add(d.getVideorecording());
                                str.add(d.getSensors());
                                str.add(d.getBattery());
                                str.add(d.getImgurl());
                            }
                                mdev.setText(str.get(0));
                                sversion.setText(str.get(1));
                                mos.setText(str.get(2));
                                mprocessor.setText(str.get(3));
                                mcpu.setText(str.get(4));
                                mgpu.setText(str.get(5));
                                mdisplay.setText(str.get(6));
                                mstorage.setText(str.get(7));
                                mmaincamera.setText(str.get(8));
                                mfrontcamera.setText(str.get(9));
                                mvideo.setText(str.get(10));
                                msensors.setText(str.get(11));
                                mbattery.setText(str.get(12));
                                if (str.get(13) == null) {
                                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_device.png?alt=media&token=422f7c1b-7761-4c5e-95f2-b05bbb5939ad").resize(250, 250)
                                            .centerCrop().into(devimg);
                                } else {
                                    Picasso.get().load(str.get(13)).into(devimg);
                                }
                        }else{
                            Toast.makeText(SearchDevice.this, "Device not found! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DeviceData>> call, Throwable t) {
                        Toast.makeText(SearchDevice.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}


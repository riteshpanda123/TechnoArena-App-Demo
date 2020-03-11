package com.example.retrofitappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Compare extends AppCompatActivity {
    public TextView mdev,mdev1;
    public TextView mos,mos1;
    public TextView sversion,sversion1;
    public TextView mprocessor,mprocessor1;
    public TextView mcpu,mcpu1;
    public TextView mgpu,mgpu1;
    public TextView mdisplay,mdisplay1;
    public TextView mstorage,mstorage1;
    public TextView mmaincamera,mmaincamera1;
    public TextView mfrontcamera,mfrontcamera1;
    public TextView mvideo,mvideo1;
    public TextView msensors,msensors1;
    public TextView mbattery,mbattery1;
    public ImageView devimg,devimg1;
    private Retrofit retrofit;
    private RetrofitInterface RetrofitInterface;
    private String TAG;
    private String BASE_URL = "https://ytamitxspectre.run.goorm.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface = retrofit.create(RetrofitInterface.class);
        mdev=findViewById(R.id.compdevname);
        mdev1=findViewById(R.id.compdevname1);
        mos=findViewById(R.id.compoperatingsystem);
        mos1=findViewById(R.id.compoperatingsystem1);
        sversion=findViewById(R.id.compsoftwareversion);
        sversion1=findViewById(R.id.compsoftwareversion1);
        mprocessor=findViewById(R.id.compprocessor);
        mprocessor1=findViewById(R.id.compprocessor1);
        mcpu=findViewById(R.id.compCPU);
        mcpu1=findViewById(R.id.compCPU1);
        mgpu=findViewById(R.id.compGPU);
        mgpu1=findViewById(R.id.compGPU1);
        mdisplay=findViewById(R.id.compdisplay);
        mdisplay1=findViewById(R.id.compdisplay1);
        mstorage=findViewById(R.id.compstorage_ram);
        mstorage1=findViewById(R.id.compstorage_ram1);
        mmaincamera=findViewById(R.id.compmaincamera);
        mmaincamera1=findViewById(R.id.compmaincamera1);
        mfrontcamera=findViewById(R.id.compfrontcamera);
        mfrontcamera1=findViewById(R.id.compfrontcamera1);
        mvideo=findViewById(R.id.compvideorecording);
        mvideo1=findViewById(R.id.compvideorecording1);
        msensors=findViewById(R.id.compsensors);
        msensors1=findViewById(R.id.compsensors1);
        mbattery=findViewById(R.id.compbattery);
        mbattery1=findViewById(R.id.compbattery1);
        devimg=findViewById(R.id.compdevimg);
        devimg1=findViewById(R.id.compcompdevimg1);

        final AutoCompleteTextView autocomplete1 = findViewById(R.id.compdev1);
        final AutoCompleteTextView autocomplete2 = findViewById(R.id.compdev2);
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
                            (Compare.this, android.R.layout.simple_list_item_1, str.toArray(new String[0]));
                    autocomplete1.setThreshold(2);
                    autocomplete1.setAdapter(adapter);
                    autocomplete2.setThreshold(2);
                    autocomplete2.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DeviceData>> call, Throwable t) {
                Toast.makeText(Compare.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void comparedev(View view) {
        EditText devicename1 = findViewById(R.id.compdev1);
        EditText devicename2 = findViewById(R.id.compdev2);

        HashMap<String,String> map = new HashMap<>();
        map.put("devname",devicename1.getText().toString());
        map.put("devname1",devicename2.getText().toString());

        Call<List<DeviceData>> call = RetrofitInterface.executeCompare(map);
        call.enqueue(new Callback<List<DeviceData>>() {
            @Override
            public void onResponse(Call<List<DeviceData>> call, Response<List<DeviceData>> response) {
                if(response.code()==200){
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
                    mdev1.setText(str.get(14));
                    sversion1.setText(str.get(15));
                    mos1.setText(str.get(16));
                    mprocessor1.setText(str.get(17));
                    mcpu1.setText(str.get(18));
                    mgpu1.setText(str.get(19));
                    mdisplay1.setText(str.get(20));
                    mstorage1.setText(str.get(21));
                    mmaincamera1.setText(str.get(22));
                    mfrontcamera1.setText(str.get(23));
                    mvideo1.setText(str.get(24));
                    msensors1.setText(str.get(25));
                    mbattery1.setText(str.get(26));
                    if (str.get(27) == null) {
                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_device.png?alt=media&token=422f7c1b-7761-4c5e-95f2-b05bbb5939ad").resize(250, 250)
                                .centerCrop().into(devimg);
                    } else {
                        Picasso.get().load(str.get(27)).into(devimg1);
                    }

                }
                else{
                    Toast.makeText(Compare.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DeviceData>> call, Throwable t) {
                Toast.makeText(Compare.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

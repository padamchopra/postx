package com.practikality.postx;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MobileAds.initialize(this, "ca-app-pub-2979566945991409~2513692248");
        //MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");
        startActivity(new Intent(MainActivity.this,newTextPost.class));
    }

    public void createPost(View view){
        Intent intent = new Intent(MainActivity.this,newTextPost.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(findViewById(R.id.main_small_rellayout),"BackgroundOpen"));
        startActivity(intent, activityOptions.toBundle());
    }
}

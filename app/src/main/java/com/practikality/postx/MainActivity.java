package com.practikality.postx;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void createPost(View view){
        Intent intent = new Intent(MainActivity.this,newTextPost.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(findViewById(R.id.main_small_rellayout),"BackgroundOpen"));
        startActivity(intent, activityOptions.toBundle());
    }
}

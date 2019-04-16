package com.practikality.postx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class newTextPost extends AppCompatActivity {

    public Button headingal1;
    public Button headingal2;
    public Button headingal3;
    public Button contental1;
    public Button contental2;
    public Button contental3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text_post);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //heading switch listener
        Switch headingSwitch = (Switch) findViewById(R.id.text_post_heading_switch);
        final TextInputLayout textInputLayoutHeading = (TextInputLayout) findViewById(R.id.text_post_heading_etl);
        final RelativeLayout relativeLayoutHeading = (RelativeLayout) findViewById(R.id.text_post_heading_alignment_layout);
        headingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textInputLayoutHeading.setVisibility(View.VISIBLE);
                    relativeLayoutHeading.setVisibility(View.VISIBLE);
                }else {
                    textInputLayoutHeading.setVisibility(View.GONE);
                    relativeLayoutHeading.setVisibility(View.GONE);
                }
            }
        });

        //byline switch listener
        Switch bylineSwitch = (Switch) findViewById(R.id.text_post_byline_switch);
        final TextInputLayout textInputLayoutByLine = (TextInputLayout) findViewById(R.id.text_post_byline_etl);
        bylineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textInputLayoutByLine.setVisibility(View.VISIBLE);
                }else {
                    textInputLayoutByLine.setVisibility(View.GONE);
                }
            }
        });


        //heading alignment button group management
        headingal1 = (Button) findViewById(R.id.text_post_heading_al_1);
        headingal2 = (Button) findViewById(R.id.text_post_heading_al_2);
        headingal3 = (Button) findViewById(R.id.text_post_heading_al_3);
        headingalignment(1);
        headingal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headingalignment(1);
            }
        });
        headingal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headingalignment(2);
            }
        });
        headingal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headingalignment(3);
            }
        });

        //content alignment button group management
        contental1 = (Button) findViewById(R.id.text_post_content_al_1);
        contental2 = (Button) findViewById(R.id.text_post_content_al_2);
        contental3 = (Button) findViewById(R.id.text_post_content_al_3);
        contentalignment(1);
        contental1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentalignment(1);
            }
        });
        contental2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentalignment(2);
            }
        });
        contental3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentalignment(3);
            }
        });
    }

    public void headingalignment(int buttonClicked){
        switch (buttonClicked){
            case 1:
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 2:
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 3:
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
        }
    }

    public void contentalignment(int buttonClicked){
        switch (buttonClicked){
            case 1:
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 2:
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 3:
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(139,195,74)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        /*findViewById(R.id.text_post_root_card_view).animate().alpha(0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                goBack();
            }
        });*/
        goBack();
    }

    public void goBack(){
        Intent intent = new Intent(newTextPost.this,MainActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(findViewById(R.id.new_post_root_view),"BackgroundOpen"));
        startActivity(intent, activityOptions.toBundle());
        finish();
    }
}

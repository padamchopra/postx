package com.practikality.postx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class newTextPost extends AppCompatActivity {

    public final int PICK_FROM_GALLERY = 2;

    public Button headingal1;
    public Button headingal2;
    public Button headingal3;
    public Button contental1;
    public Button contental2;
    public Button contental3;
    public boolean imageAlreadySet;
    public Uri mBackgroundImageUri;

    public int headingAlignment;
    public int textAlignment;

    public MaterialButton mBackgroundColorPicker;
    public MaterialButton mTextColorPicker;
    public ImageButton mBackgroundImagePicker;

    public int mRedBG;
    public int mGreenBG;
    public int mBlueBG;
    public int mRedT;
    public int mGreenT;
    public int mBlueT;
    public int redTemp;
    public int greenTemp;
    public int blueTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text_post);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setting views
        Switch headingSwitch = (Switch) findViewById(R.id.text_post_heading_switch);
        final TextInputLayout textInputLayoutHeading = (TextInputLayout) findViewById(R.id.text_post_heading_etl);
        final RelativeLayout relativeLayoutHeading = (RelativeLayout) findViewById(R.id.text_post_heading_alignment_layout);
        Switch bylineSwitch = (Switch) findViewById(R.id.text_post_byline_switch);
        final TextInputLayout textInputLayoutByLine = (TextInputLayout) findViewById(R.id.text_post_byline_etl);
        Switch bgImageSwitch = (Switch) findViewById(R.id.text_post_bgimage_switch);
        headingal1 = (Button) findViewById(R.id.text_post_heading_al_1);
        headingal2 = (Button) findViewById(R.id.text_post_heading_al_2);
        headingal3 = (Button) findViewById(R.id.text_post_heading_al_3);
        contental1 = (Button) findViewById(R.id.text_post_content_al_1);
        contental2 = (Button) findViewById(R.id.text_post_content_al_2);
        contental3 = (Button) findViewById(R.id.text_post_content_al_3);
        mBackgroundImagePicker = (ImageButton) findViewById(R.id.text_post_background_image_button);
        mBackgroundColorPicker = findViewById(R.id.text_post_background_color_picker_button);
        mTextColorPicker = findViewById(R.id.text_post_text_color_picker_button);


        //heading switch listener
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

        //bgimage switch listener
        bgImageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mBackgroundColorPicker.setVisibility(View.GONE);
                    mBackgroundImagePicker.setVisibility(View.VISIBLE);
                }else {
                    mBackgroundColorPicker.setVisibility(View.VISIBLE);
                    mBackgroundImagePicker.setVisibility(View.GONE);
                }
            }
        });

        //setting rgb values for default green and black
        mRedBG = 139;
        mGreenBG = 195;
        mBlueBG = 74;
        mRedT = 0;
        mGreenT = 0;
        mBlueT = 0;

        //alignment defaults
        headingAlignment = 1;
        textAlignment = 1;

        //heading alignment button group management
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

        //listening to color picker click
        mBackgroundColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker(1);
            }
        });
        mTextColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker(2);
            }
        });

        imageAlreadySet = false;
    }

    public void headingalignment(int buttonClicked){
        switch (buttonClicked){
            case 1:
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG,mGreenBG,mBlueBG)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 2:
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG,mGreenBG,mBlueBG)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 3:
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
        }
        headingAlignment = buttonClicked;
    }

    public void contentalignment(int buttonClicked){
        switch (buttonClicked){
            case 1:
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG,mGreenBG,mBlueBG)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 2:
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG,mGreenBG,mBlueBG)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
            case 3:
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG,mGreenBG,mBlueBG)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207,207,207)));
                break;
        }
        textAlignment = buttonClicked;
    }


    public void colorPicker(final int colorOf){
        if(colorOf==1){
            redTemp = mRedBG;
            greenTemp = mGreenBG;
            blueTemp = mBlueBG;
        }else{
            redTemp = mRedT;
            greenTemp = mGreenT;
            blueTemp = mBlueT;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(newTextPost.this);
        LayoutInflater layoutInflater = newTextPost.this.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_color_selector, null));
        final AlertDialog alertDialog = builder.create();
        try {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        alertDialog.show();

        //assigning views
        SeekBar redSeekbar = alertDialog.findViewById(R.id.dialog_color_picker_red);
        SeekBar greenSeekbar = alertDialog.findViewById(R.id.dialog_color_picker_green);
        SeekBar blueSeekbar = alertDialog.findViewById(R.id.dialog_color_picker_blue);
        MaterialButton cancelButton = alertDialog.findViewById(R.id.dialog_cancel_button);
        MaterialButton applyButton = alertDialog.findViewById(R.id.dialog_apply_button);
        final TextView dialogColorPreview = alertDialog.findViewById(R.id.dialog_color_chooser_preview);

        redSeekbar.setProgress(redTemp);
        greenSeekbar.setProgress(greenTemp);
        blueSeekbar.setProgress(blueTemp);
        dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp,greenTemp,blueTemp)));

        redSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redTemp = progress;
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp,greenTemp,blueTemp)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenTemp = progress;
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp,greenTemp,blueTemp)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueTemp = progress;
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp,greenTemp,blueTemp)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorOf==1){
                    mRedBG = redTemp;
                    mGreenBG = greenTemp;
                    mBlueBG = blueTemp;
                    refreshBackgroundColor();
                }else {
                    mRedT = redTemp;
                    mGreenT = greenTemp;
                    mBlueT = blueTemp;
                    refreshTextColor();
                }
                alertDialog.dismiss();
            }
        });
    }

    public void refreshTextColor(){
        TextView title = (TextView) findViewById(R.id.new_post_title);
        title.setTextColor(Color.rgb(mRedT,mGreenT,mBlueT));
        findViewById(R.id.text_post_text_color_picker_button).setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedT,mGreenT,mBlueT)));
    }

    public void refreshBackgroundColor(){
        int ColorToSet = Color.rgb(mRedBG,mGreenBG,mBlueBG);
        findViewById(R.id.new_post_root_view).setBackground(new ColorDrawable(ColorToSet));
        findViewById(R.id.text_post_background_color_picker_button).setBackgroundTintList(ColorStateList.valueOf(ColorToSet));
        headingalignment(headingAlignment);
        contentalignment(textAlignment);
    }

    public void selectImage(View view){
        if(!imageAlreadySet){
            startImageIntent();
        }
    }

    public void startImageIntent(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case PICK_FROM_GALLERY:
                    try{
                        Uri imageSelected = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageSelected);
                        cropImage(bitmap);
                    }catch (Exception e){
                        makeSnackbar("Could not load image.");
                    }
                    /*TODO: Convert URI to bitmap
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);
                    text_post_image_background.setImageBitmap(bitmap);*/
                    break;
            }
        }else{
            makeSnackbar("An error occured. Try again.");
        }
    }

    public void cropImage(Bitmap imageToCrop){
        AlertDialog.Builder builder = new AlertDialog.Builder(newTextPost.this);
        LayoutInflater layoutInflater = newTextPost.this.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_color_selector, null));
        final AlertDialog alertDialog = builder.create();
        try {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        alertDialog.show();

    }

    public Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void onBackPressed() {
        goBack((View) mTextColorPicker);
    }

    public void goBack(View view){
        Intent intent = new Intent(newTextPost.this,MainActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(findViewById(R.id.new_post_root_view),"BackgroundOpen"));
        startActivity(intent, activityOptions.toBundle());
        finish();
    }

    public void makeSnackbar(String message){
        Snackbar.make(mTextColorPicker,message,Snackbar.LENGTH_LONG).show();
    }
}

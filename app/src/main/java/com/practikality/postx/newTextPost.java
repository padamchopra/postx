package com.practikality.postx;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.gson.Gson;
import com.practikality.postx.models.TextPostDetails;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class newTextPost extends AppCompatActivity implements RewardedVideoAdListener, AdapterView.OnItemSelectedListener {

    public final int PICK_FROM_GALLERY = 2;

    public Button headingal1;
    public Button headingal2;
    public Button headingal3;
    public Button contental1;
    public Button contental2;
    public Button contental3;
    public MaterialButton mBackgroundColorPicker;
    public MaterialButton mTextColorPicker;
    public ImageButton mBackgroundImagePicker;
    public Switch bgImageSwitch;
    public Switch headingSwitch;
    public Switch bylineSwitch;
    public RewardedVideoAd rewardedVideoAd;
    public ArrayList<String> CustomFonts;
    public ArrayAdapter<String> mSpinnerAdapter;
    public Spinner mSpinner;

    public int mRedBG;
    public int mGreenBG;
    public int mBlueBG;
    public int mRedT;
    public int mGreenT;
    public int mBlueT;
    public int redTemp;
    public int greenTemp;
    public int blueTemp;
    public boolean imageAlreadySet;
    public Uri mBackgroundImageUri;
    public int headingAlignment;
    public int textAlignment;
    public Bitmap bitmapToShare;
    public String fontFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text_post);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //for writing into external storage
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        //setting views
        headingSwitch = (Switch) findViewById(R.id.text_post_heading_switch);
        final TextInputLayout textInputLayoutHeading = (TextInputLayout) findViewById(R.id.text_post_heading_etl);
        final RelativeLayout relativeLayoutHeading = (RelativeLayout) findViewById(R.id.text_post_heading_alignment_layout);
        bylineSwitch = (Switch) findViewById(R.id.text_post_byline_switch);
        final TextInputLayout textInputLayoutByLine = (TextInputLayout) findViewById(R.id.text_post_byline_etl);
        bgImageSwitch = (Switch) findViewById(R.id.text_post_bgimage_switch);
        headingal1 = (Button) findViewById(R.id.text_post_heading_al_1);
        headingal2 = (Button) findViewById(R.id.text_post_heading_al_2);
        headingal3 = (Button) findViewById(R.id.text_post_heading_al_3);
        contental1 = (Button) findViewById(R.id.text_post_content_al_1);
        contental2 = (Button) findViewById(R.id.text_post_content_al_2);
        contental3 = (Button) findViewById(R.id.text_post_content_al_3);
        mBackgroundImagePicker = (ImageButton) findViewById(R.id.text_post_background_image_button);
        mBackgroundColorPicker = findViewById(R.id.text_post_background_color_picker_button);
        mTextColorPicker = findViewById(R.id.text_post_text_color_picker_button);
        mSpinner = findViewById(R.id.text_post_spinner);
        mSpinner.setOnItemSelectedListener(this);

        //heading switch listener
        headingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textInputLayoutHeading.setVisibility(View.VISIBLE);
                    relativeLayoutHeading.setVisibility(View.VISIBLE);
                } else {
                    textInputLayoutHeading.setVisibility(View.GONE);
                    relativeLayoutHeading.setVisibility(View.GONE);
                }
            }
        });

        //byline switch listener
        bylineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textInputLayoutByLine.setVisibility(View.VISIBLE);
                } else {
                    textInputLayoutByLine.setVisibility(View.GONE);
                }
            }
        });

        //bgimage switch listener
        bgImageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBackgroundColorPicker.setVisibility(View.GONE);
                    mBackgroundImagePicker.setVisibility(View.VISIBLE);
                } else {
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

        //fonts
        CustomFonts = new ArrayList<>();
        CustomFonts.add("Product Sans");
        CustomFonts.add("Caviar Dreams");
        CustomFonts.add("Helvetica Neue");
        CustomFonts.add("HoneyScript");
        CustomFonts.add("Oswald");
        CustomFonts.add("Raleway");
        CustomFonts.add("Coolvetica");
        CustomFonts.add("Impact");
        CustomFonts.add("Stilu");
        CustomFonts.add("Vag. Repulsive");
        fontFace = "Product Sans";
        loadSpinner();

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
        makeSnackbar("Saving templates, coming soon!");
    }

    public void loadSpinner(){
        mSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CustomFonts){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface productsans = Typeface.createFromAsset(getAssets(), "fonts/productsans.ttf");
                ((TextView) v).setTypeface(productsans);
                ((TextView) v).setTextSize(18);
                ((TextView) v).setTextColor(Color.BLACK);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                Typeface productsans = Typeface.createFromAsset(getAssets(), "fonts/productsans.ttf");
                ((TextView) v).setTypeface(productsans);
                ((TextView) v).setTextSize(18);
                ((TextView) v).setTextColor(Color.BLACK);
                return v;
            }
        };
        mSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);
    }

    public void headingalignment(int buttonClicked) {
        switch (buttonClicked) {
            case 1:
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
            case 2:
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
            case 3:
                headingal3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                headingal2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                headingal1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
        }
        headingAlignment = buttonClicked;
    }

    public void contentalignment(int buttonClicked) {
        switch (buttonClicked) {
            case 1:
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
            case 2:
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
            case 3:
                contental3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedBG, mGreenBG, mBlueBG)));
                contental2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                contental1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(207, 207, 207)));
                break;
        }
        textAlignment = buttonClicked;
    }

    public void colorPicker(final int colorOf) {
        if (colorOf == 1) {
            redTemp = mRedBG;
            greenTemp = mGreenBG;
            blueTemp = mBlueBG;
        } else {
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
        dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp, greenTemp, blueTemp)));

        redSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redTemp = progress;
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp, greenTemp, blueTemp)));
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
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp, greenTemp, blueTemp)));
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
                dialogColorPreview.setBackground(new ColorDrawable(Color.rgb(redTemp, greenTemp, blueTemp)));
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
                if (colorOf == 1) {
                    mRedBG = redTemp;
                    mGreenBG = greenTemp;
                    mBlueBG = blueTemp;
                    refreshBackgroundColor();
                } else {
                    mRedT = redTemp;
                    mGreenT = greenTemp;
                    mBlueT = blueTemp;
                    refreshTextColor();
                }
                alertDialog.dismiss();
            }
        });
    }

    public void refreshTextColor() {
        TextView title = (TextView) findViewById(R.id.new_post_title);
        title.setTextColor(Color.rgb(mRedT, mGreenT, mBlueT));
        findViewById(R.id.text_post_text_color_picker_button).setBackgroundTintList(ColorStateList.valueOf(Color.rgb(mRedT, mGreenT, mBlueT)));
    }

    public void refreshBackgroundColor() {
        int ColorToSet = Color.rgb(mRedBG, mGreenBG, mBlueBG);
        findViewById(R.id.new_post_root_view).setBackground(new ColorDrawable(ColorToSet));
        findViewById(R.id.text_post_background_color_picker_button).setBackgroundTintList(ColorStateList.valueOf(ColorToSet));
        headingalignment(headingAlignment);
        contentalignment(textAlignment);
    }

    public void selectImage(View view) {
        if (!imageAlreadySet) {
            startImageIntent();
        }
    }

    public void startImageIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_FROM_GALLERY:
                    try {
                        Uri imageSelected = data.getData();
                        mBackgroundImageUri = imageSelected;
                    } catch (Exception e) {
                        makeSnackbar("Could not load image.");
                    }
                    break;
            }
        } else {
            makeSnackbar("An error occured. Try again.");
        }
    }

    public void generateImage(View view) {
        boolean allOkay = true;
        String errorMessage = "An error occured. Try again!";
        TextPostDetails textPostDetails = new TextPostDetails();
        if (bgImageSwitch.isChecked()) {
            textPostDetails.setBackgroundImageUsed(true);
            if (mBackgroundImageUri.toString().length() > 0) {
                textPostDetails.setBackgroundImageUri(mBackgroundImageUri);
            } else {
                allOkay = false;
                errorMessage = "Background image not set";
            }
        } else {
            textPostDetails.setBackgroundImageUsed(false);
            textPostDetails.setBackgroundColor(Color.rgb(mRedBG, mGreenBG, mBlueBG));
        }
        textPostDetails.setTextColor(Color.rgb(mRedT, mGreenT, mBlueT));
        if (headingSwitch.isChecked()) {
            textPostDetails.setHeadingUsed(true);
            TextInputEditText headingInputEditText = findViewById(R.id.heading_edit_text);
            String heading = String.valueOf(headingInputEditText.getText());
            if (heading.length() > 0) {
                textPostDetails.setHeading(heading);
                textPostDetails.setHeadingAlignment(headingAlignment);
            } else {
                allOkay = false;
                errorMessage = "Please enter a heading";
            }
        } else {
            textPostDetails.setHeadingUsed(false);
        }
        TextInputEditText contentInputEditText = findViewById(R.id.content_edit_text);
        String content = String.valueOf(contentInputEditText.getText());
        if (content.length() > 0) {
            textPostDetails.setContent(content);
            textPostDetails.setContentAlignment(textAlignment);
        } else {
            allOkay = false;
            errorMessage = "Please enter some content";
        }
        if (bylineSwitch.isChecked()) {
            textPostDetails.setByLineUsed(true);
            TextInputEditText bylineInputEditText = findViewById(R.id.byline_edit_text);
            String byline = String.valueOf(bylineInputEditText.getText());
            if (byline.length() > 0) {
                textPostDetails.setByLine(byline);
            } else {
                allOkay = false;
                errorMessage = "Please enter a by-line";
            }
        } else {
            textPostDetails.setByLineUsed(false);
        }
        if (allOkay) {
            createPostDialog(textPostDetails);
        } else {
            makeSnackbar(errorMessage);
        }
    }

    public void createPostDialog(TextPostDetails textPostDetails) {
        AlertDialog.Builder builder = new AlertDialog.Builder(newTextPost.this);
        LayoutInflater layoutInflater = newTextPost.this.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_generated_post, null));
        final AlertDialog alertDialog = builder.create();
        try {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        alertDialog.show();

        //setting views
        final RelativeLayout mainRelativeLayout = alertDialog.findViewById(R.id.dialog_generated_post_layout);
        final TextView headingTV = alertDialog.findViewById(R.id.generated_post_heading_tv);
        final TextView contentTV = alertDialog.findViewById(R.id.generated_post_content_tv);
        TextView byLineTV = alertDialog.findViewById(R.id.generated_post_byline_tv);
        LinearLayout headingFontLayout = alertDialog.findViewById(R.id.heading_font_layout);
        SeekBar headingSeekbar = alertDialog.findViewById(R.id.heading_font_seekbar);
        SeekBar contentSeekbar = alertDialog.findViewById(R.id.content_font_seekbar);
        ImageButton closeDialog = alertDialog.findViewById(R.id.dialog_close_post_button);
        MaterialButton shareButton = alertDialog.findViewById(R.id.dialog_share_button);
        ImageView postImageView = alertDialog.findViewById(R.id.generated_post_image_view);

        //setting background
        if (textPostDetails.isBackgroundImageUsed()) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), textPostDetails.getBackgroundImageUri());
                postImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                makeSnackbar("Could not load background image.");
            }
        } else {
            mainRelativeLayout.setBackground(new ColorDrawable(textPostDetails.getBackgroundColor()));
        }

        //setting the heading and bylines visibility
        if (textPostDetails.isHeadingUsed()) {
            headingTV.setTextAlignment(getAlignment(textPostDetails.getHeadingAlignment()));
            headingTV.setText(textPostDetails.getHeading());
            headingTV.setTextColor(textPostDetails.getTextColor());
            headingTV.setTypeface(getFontApplied(fontFace));
            headingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    headingTV.setTextSize(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } else {
            headingTV.setVisibility(View.GONE);
            headingFontLayout.setVisibility(View.GONE);
        }

        contentTV.setText(textPostDetails.getContent());
        contentTV.setTextAlignment(getAlignment(textPostDetails.getContentAlignment()));
        contentTV.setTextColor(textPostDetails.getTextColor());
        contentTV.setTypeface(getFontApplied(fontFace));
        contentSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                contentTV.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (textPostDetails.isByLineUsed()) {
            byLineTV.setText(textPostDetails.getByLine());
            byLineTV.setTextColor(textPostDetails.getTextColor());
        } else {
            byLineTV.setVisibility(View.GONE);
        }

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap finalBitmap = Bitmap.createBitmap(mainRelativeLayout.getWidth(),mainRelativeLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(finalBitmap);
                mainRelativeLayout.draw(canvas);
                bitmapToShare = finalBitmap;
                makeSnackbar("Generating...");
                checkPermission();
            }
        });
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            shareBitmap();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 99);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 99:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareBitmap();
                }
                break;
            default:
                break;
        }
    }

    public void shareBitmap(){
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        //rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        rewardedVideoAd.loadAd("ca-app-pub-2979566945991409/5941446144", new AdRequest.Builder().build());
    }

    public void finallyShareBitmap(){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapToShare.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String path = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";
        File f = new File(path);
        try{
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(byteArrayOutputStream.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        Uri pathu = Uri.fromFile(f);
        share.putExtra(Intent.EXTRA_STREAM, pathu);
        share.putExtra(Intent.EXTRA_TEXT, "Shared with #PostX");
        share.putExtra(Intent.EXTRA_SUBJECT, "Shared with #PostX");
        startActivity(Intent.createChooser(share, "Share Image"));
    }

    public int getAlignment(int alignment) {
        int toReturn = View.TEXT_ALIGNMENT_TEXT_START;
        switch (alignment) {
            case 1:
                toReturn = View.TEXT_ALIGNMENT_TEXT_START;
                break;
            case 2:
                toReturn = View.TEXT_ALIGNMENT_CENTER;
                break;
            case 3:
                toReturn = View.TEXT_ALIGNMENT_TEXT_END;
                break;
        }
        return toReturn;
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

    public void goBack(View view) {
        Intent intent = new Intent(newTextPost.this, MainActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(findViewById(R.id.new_post_root_view), "BackgroundOpen"));
        startActivity(intent, activityOptions.toBundle());
        finish();
    }

    public void makeSnackbar(String message) {
        Snackbar.make(mTextColorPicker, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        rewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        makeSnackbar("Video closed in between!");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        finallyShareBitmap();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        makeSnackbar("Application closed in between!");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        makeSnackbar("Video failed to load!");
        finallyShareBitmap();
    }

    @Override
    public void onRewardedVideoCompleted() {
        makeSnackbar("Video watched!");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fontFace = CustomFonts.get(position);
        ((TextView) findViewById(R.id.new_post_title)).setTypeface(getFontApplied(fontFace));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Typeface getFontApplied(String fontName){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/productsans.ttf");

        if(fontName.equals("Caviar Dreams")){
            System.out.println("caviar");
            typeface = Typeface.createFromAsset(getAssets(), "fonts/caviardreams.ttf");
        } else if(fontName.equals("Helvetica Neue")){
            System.out.println("hell");
            typeface = Typeface.createFromAsset(getAssets(), "fonts/helveticaneue.ttf");
        } else if(fontName.equals("HoneyScript")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/honeyscript.ttf");
        } else if(fontName.equals("Oswald")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/oswald.ttf");
        } else if(fontName.equals("Raleway")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/raleway.ttf");
        } else if(fontName.equals("Coolvetica")){
            System.out.println("cool");
            typeface = Typeface.createFromAsset(getAssets(), "fonts/coolvetica.ttf");
        } else if(fontName.equals("Impact")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/impact.ttf");
        } else if(fontName.equals("Stilu")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/stilu.otf");
        } else if(fontName.equals("Vag. Repulsive")){
            typeface = Typeface.createFromAsset(getAssets(), "fonts/vaguelyrepulsive.ttf");
        }

        return typeface;
    }
}

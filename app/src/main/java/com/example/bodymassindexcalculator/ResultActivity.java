// - Package & Libraries
package com.example.bodymassindexcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

// - Class
public class ResultActivity extends AppCompatActivity {

    // - Attributes
    private ImageView bmiIMG;
    private TextView bmiNumericalResult;
    private TextView bmiResult;
    private ActionBar actionbar;
    private ProgressBar progressBar;
    private int progressStatus = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.setTitle("BMI Result");
        actionbar = (ActionBar) getSupportActionBar();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // - Set Data
        bmiIMG     = (ImageView) findViewById(R.id.result_IMG);
        bmiResult   = (TextView) findViewById(R.id.result_TW);
        bmiNumericalResult = (TextView) findViewById(R.id.numericalResult_TW);

        // Fonts
        bmiNumericalResult.setTypeface(ResourcesCompat.getFont(ResultActivity.this, R.font.learner));
        bmiResult.setTypeface(ResourcesCompat.getFont(ResultActivity.this, R.font.moonlight));

        // Get Data
        Intent intent = getIntent();
        String str = intent.getStringExtra("bmiResult");
        String gender = intent.getStringExtra("gender");
        bmiNumericalResult.setText(str);

        // - Control (Null or Not)
        double bmi = Double.parseDouble(str);

        progressStatus = Math.round((float)(bmi / 40) *100);
        updateProgressBar(progressStatus);

        // Underweight
        if(bmi < 18.5f) {
            // - Gender
            if(gender.equals("male")) {
                bmiIMG.setImageResource(R.drawable.underweightmale);
            }
            else {
                bmiIMG.setImageResource(R.drawable.underweightfemale);
            }
            bmiResult.setText("Underweight");
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6200EE")));
            bmiResult.setTextColor(Color.parseColor("#F4D648"));

        }

        // Normal or Healthy Weight
        else if((bmi >= 18.5f) && (bmi < 24.99f)) {
            // - Gender
            if(gender.equals("male")) {
                bmiIMG.setImageResource(R.drawable.healthymale);
            }
            else {
                bmiIMG.setImageResource(R.drawable.healthyfemale);
            }
            bmiResult.setText("Normal Weight");

            // - ActionBar
            bmiResult.setTextColor(Color.parseColor("#2abb9a"));
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2abb9a")));
        }

        // Overweight
        else if((bmi >= 25) && (bmi < 29.99f)) {
            // - Gender
            if(gender.equals("male")) {
                bmiIMG.setImageResource(R.drawable.overweightmale);
            }
            else {
                bmiIMG.setImageResource(R.drawable.overweightfemale);
            }
            bmiResult.setText("OverWeight");

            // - ActionBar
            bmiResult.setTextColor(Color.parseColor("#FFA500"));
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));

        }

        // Obesity
        else {
            // - Gender
            if(gender.equals("male")) {
                bmiIMG.setImageResource(R.drawable.obesitymale);
            }
            else {
                bmiIMG.setImageResource(R.drawable.obesityfemale);
            }
            bmiResult.setText("Obesity Class");

            // - ActionBar
            bmiResult.setTextColor(Color.parseColor("#F44B3E"));
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44B3E")));

        }
    }

    // Update ProgressBar
    private void updateProgressBar(int value) {
        progressBar.setProgress(value);
    }
}
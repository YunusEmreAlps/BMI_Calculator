// - Package & Libraries
package com.example.bodymassindexcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

// - Class
public class MainActivity extends AppCompatActivity {

    // - Attributes
    private EditText age;
    private EditText height;
    private EditText weight;
    private Button buttonConfirm;

    public RadioButton male;
    public RadioButton female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // - Set Data
        male    = (RadioButton) findViewById(R.id.male_Btn);
        female  = (RadioButton) findViewById(R.id.female_Btn);
        age     = (EditText) findViewById(R.id.age_ET);
        height  = (EditText) findViewById(R.id.height_ET);
        weight  = (EditText) findViewById(R.id.weight_ET);
        buttonConfirm = (Button)findViewById(R.id.showResult);

        // - Control (Null or Not)
        age.addTextChangedListener(calculatebmi);
        height.addTextChangedListener(calculatebmi);
        weight.addTextChangedListener(calculatebmi);
    }

    // Null or Not
    private TextWatcher calculatebmi = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // - Data
            String ageInput = age.getText().toString().trim();
            String heightInput = height.getText().toString().trim();
            String weightInput = weight.getText().toString().trim();

            if((!ageInput.isEmpty()) && (!heightInput.isEmpty()) && (!weightInput.isEmpty())) {
                buttonConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      moveToResultActivity();
                    }
                });
            }

            // buttonConfirm.setEnabled((!ageInput.isEmpty()) && (!heightInput.isEmpty()) && (!weightInput.isEmpty()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // - Result Page
    private void moveToResultActivity()
    {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("bmiResult", String.valueOf(calculateBMI( (Double.parseDouble(height.getText().toString())) / 100, Double.parseDouble(weight.getText().toString()))));
        if(male.isChecked()) {
            intent.putExtra("gender", "male");
        }
        else {
            intent.putExtra("gender", "female");
        }

        startActivity(intent);
    }

    public String calculateBMI(double heightData, double weightData) {
        double result = weightData / (heightData * heightData);
        return String.format("%.2f", result);
    }

}
package com.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.parceler.Parcels;

public class DemographicActivity extends AppCompatActivity {

    private MaterialButton continueButton;
    private RadioGroup sexRadGroup;
    private RadioButton selectedRadButton;
    private AutoCompleteTextView ageAutoComplete, regionAutoComplete;
    private TextInputLayout ageTf, regionTf;
    private String gender, ageGroup, region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_demographic);

        continueButton = findViewById(R.id.dem_act_cont_btn);
        sexRadGroup = findViewById(R.id.dem_act_sex_rg);
        ageAutoComplete = findViewById(R.id.dem_act_age_actv);
        regionAutoComplete = findViewById(R.id.dem_act_region_actv);
        ageTf = findViewById(R.id.dem_act_age_tf);
        regionTf = findViewById(R.id.dem_act_region_tf);

        String[] ageGroupItems = {"below 24", "25 to 34", "35 to 44", "45 to 54", "55 to 64", "65 above"};
        ArrayAdapter<String> ageGroupAdapter = new ArrayAdapter<>(this, R.layout.list_item, ageGroupItems);
        ageAutoComplete.setAdapter(ageGroupAdapter);

        String[] regionGroupItems = {
                "I: Ilocos Region", "II: Cagayan Valley", "III: Central Luzon", "IV‑A: CALABARZON",
                "IV‑B: MIMAROPA", "V: Bicol Region", "VI: Western Visayas", "VII: Central Visayas",
                "VIII: Eastern Visayas", "IX: Zamboanga Peninsula", "X: Northern Mindanao",
                "XI: Davao Region", "XII: SOCCSKSARGEN", "XIII: Caraga", "NCR", "CAR", "BARMM"};
        ArrayAdapter<String> regionGroupAdapter = new ArrayAdapter<>(this, R.layout.list_item, regionGroupItems);
        regionAutoComplete.setAdapter(regionGroupAdapter);

        ageAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ageGroup = adapterView.getItemAtPosition(i).toString();
            }
        });

        regionAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                region = adapterView.getItemAtPosition(i).toString();
            }
        });

        continueButton.setOnClickListener(view -> {

            int selectedId = sexRadGroup.getCheckedRadioButtonId();
            selectedRadButton = (RadioButton)findViewById(selectedId);
            gender = selectedRadButton.getText().toString();

            if(!validateAgeGroup() | !validateRegion()) {
                return;
            } else {
                Intent diagnose = new Intent(this, DiagnoseActivity.class);
                diagnose.putExtra("user", Parcels.wrap(new User(gender, ageGroup, region)));
                startActivity(diagnose);
            }
        });
    }

    private boolean validateAgeGroup() {

        if(ageGroup == null) {
            ageTf.setError("Please select age group");
            return false;
        } else {
            ageTf.setError(null);
            return true;
        }
    }

    private boolean validateRegion() {

        if(region == null) {
            regionTf.setError("Please select region");
            return false;
        } else {
            regionTf.setError(null);
            return true;
        }
    }
}

        /**
          * <summary>
          *     Debug where it needs to display error if incomplete information.
          * </summary>
          */
//        continueButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view){
//                if(ageGroupAdapter != null || regionAutoComplete != null){
//                    goToDiagnoseActivity();
//                }
//                else{
//                    warningDialog();
//                }
//            }
//        });

      // A button direct to the next page, WITH COMPLETE INPUT INFORMATION
//    public void goToDiagnoseActivity(){
//        Intent diagnose = new Intent(this, DiagnoseActivity.class);
//        startActivity(diagnose);
//    }
//
//    public void warningDialog(){
//        WarningDialog warningDialog = new WarningDialog();
//        warningDialog.show(getSupportFragmentManager(), "");
//    }


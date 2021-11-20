package com.covidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DiagnoseActivity extends AppCompatActivity {

    // Variable declaration
    private MaterialCardView feverCardView, coughCardView, fatigueCardView,
                             acheCardView, runny_noseCardView, sore_throatCardView,
                             diff_breathCardView, diarrheaCardView, headacheCardView,
                             loss_taste_smellCardView,contactView, travelView, frontlineView;
    private MaterialButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_diagnose);

        // Initialization of variables
        feverCardView = findViewById(R.id.dia_act_fvr_cv);
        coughCardView = findViewById(R.id.dia_act_cough_cv);
        fatigueCardView = findViewById(R.id.dia_act_ftg_cv);
        acheCardView = findViewById(R.id.dia_act_ache_cv);
        runny_noseCardView = findViewById(R.id.dia_act_rn_cv);
        sore_throatCardView = findViewById(R.id.dia_act_st_cv);
        diff_breathCardView = findViewById(R.id.dia_act_db_cv);
        diarrheaCardView = findViewById(R.id.dia_act_diar_cv);
        headacheCardView = findViewById(R.id.dia_act_hdache_cv);
        loss_taste_smellCardView = findViewById(R.id.dia_act_lts_cv);
//        contactView     = findViewById(R.id.dia2_act_contact_cv);
//        travelView      = findViewById(R.id.dia2_act_travel_cv);
//        frontlineView   = findViewById(R.id.dia2_act_frontliner_cv);
        submitButton    = findViewById(R.id.dia_act_sbmt_btn);

        // Set onClickListener() to variables
        feverCardView.setOnClickListener(view -> feverCardView.toggle());
        coughCardView.setOnClickListener(view -> coughCardView.toggle());
        coughCardView.setOnClickListener(view -> coughCardView.toggle());
        fatigueCardView.setOnClickListener(view -> fatigueCardView.toggle());
        acheCardView.setOnClickListener(view -> acheCardView.toggle());
        runny_noseCardView.setOnClickListener(view -> runny_noseCardView.toggle());
        sore_throatCardView.setOnClickListener(view -> sore_throatCardView.toggle());
        diff_breathCardView.setOnClickListener(view -> diff_breathCardView.toggle());
        diarrheaCardView.setOnClickListener(view -> diarrheaCardView.toggle());
        headacheCardView.setOnClickListener(view -> headacheCardView.toggle());
        loss_taste_smellCardView.setOnClickListener(view -> loss_taste_smellCardView.toggle());
//        contactView.setOnClickListener(view -> contactView.toggle());
//        travelView.setOnClickListener(view -> travelView.toggle());
//        frontlineView.setOnClickListener(view -> frontlineView.toggle());


        submitButton.setOnClickListener(view -> {

            ArrayList<String> selectedItems = new ArrayList<>();
            ArrayList<String> notSelectedItems = new ArrayList<>();

            User user = null;

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
            }

            String sex = user.gender;
            String age = user.ageGroup;

            switch (age) {
                case "below 24": age = "below24"; break;
                case "25 to 34": age = "Age25to34"; break;
                case "35 to 44": age = "Age35to44"; break;
                case "45 to 54": age = "Age45to54"; break;
                case "55 to 64": age = "Age55to64"; break;
                case "65 above": age = "Above65"; break;
                default: throw new IllegalArgumentException();
            }
            Log.i("AGE", age);

            if(feverCardView.isChecked()) {
                selectedItems.add("SYM_FEVER");
            } else {
                notSelectedItems.add("SYM_FEVER");
            }

            if(coughCardView.isChecked()) {
                selectedItems.add("SYM_COUGH");
            } else {
                notSelectedItems.add("SYM_COUGH");
            }

            if(fatigueCardView.isChecked()) {
                selectedItems.add("SYM_FATIGUE");
            } else {
                notSelectedItems.add("SYM_FATIGUE");
            }

            if(acheCardView.isChecked()) {
                selectedItems.add("SYM_MUSCLEPAIN");
            } else {
                notSelectedItems.add("SYM_MUSCLEPAIN");
            }

            if(runny_noseCardView.isChecked()) {
                selectedItems.add("SYM_RUNNY_NOSE");
            } else {
                notSelectedItems.add("SYM_RUNNY_NOSE");
            }

            if(sore_throatCardView.isChecked()) {
                selectedItems.add("SYM_THROAT");
            } else {
                notSelectedItems.add("SYM_THROAT");
            }

            if(diff_breathCardView.isChecked()) {
                selectedItems.add("SYM_SHORT_BREATH");
            } else {
                notSelectedItems.add("SYM_SHORT_BREATH");
            }

            if(diarrheaCardView.isChecked()) {
                selectedItems.add("SYM_DIARRHEA");
            } else {
                notSelectedItems.add("SYM_DIARRHEA");
            }

            if(headacheCardView.isChecked()) {
                selectedItems.add("SYM_HEADACHE");
            } else {
                notSelectedItems.add("SYM_HEADACHE");
            }

            if(loss_taste_smellCardView.isChecked()) {
                selectedItems.add("SYM_SMELL_TASTE");
            } else {
                notSelectedItems.add("SYM_SMELL_TASTE");
            }

//            if(travelView.isChecked()) selectedItems.add("TravelledOutside");
//            if(contactView.isChecked()) selectedItems.add("CloseContact");
//            if(frontlineView.isChecked()) selectedItems.add("Frontliner");

            Context context = DiagnoseActivity.this;
            InferenceEngine inferenceEngine = new InferenceEngine(context);
            double res = inferenceEngine.getProbability(selectedItems, notSelectedItems, sex, age) * 100;

            Intent result = new Intent(this, ResultActivity.class);
            result.putExtra("result", res);
            Log.i("RESULT", String.valueOf(res));
            result.putExtra("user", Parcels.wrap(user));
            startActivity(result);
        });
    }
}

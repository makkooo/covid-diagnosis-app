package com.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringJoiner;

public class DiagnoseActivity extends AppCompatActivity {

    // Variable declaration
    private MaterialCardView feverCardView, coughCardView, fatigueCardView,
                             acheCardView, runny_noseCardView, sore_throatCardView,
                             diff_breathCardView, diarrheaCardView, headacheCardView,
                             loss_taste_smellCardView;
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

        submitButton.setOnClickListener(view -> {

            ArrayList<String> selectedItems = new ArrayList<>();
            ArrayList<String> notSelectedItems = new ArrayList<>();
            StringJoiner joiner = new StringJoiner("\t");
            User user = null;
            int age, sex;
            double res;

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
            }

            switch (user.gender) {
                case "Female": sex = 0; break;
                case "Male": sex = 1; break;
                default: throw new IllegalArgumentException();
            }
            joiner.add(String.valueOf(sex));

            switch (user.ageGroup) {
                case "below 24": age = 0; break;
                case "25 to 34": age = 1; break;
                case "35 to 44": age = 2; break;
                case "45 to 54": age = 3; break;
                case "55 to 64": age = 4; break;
                case "65 above": age = 5; break;
                default: throw new IllegalArgumentException();
            }
            joiner.add(String.valueOf(age+1));

            if(feverCardView.isChecked()) {
                selectedItems.add("SYM_FEVER");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_FEVER");
                joiner.add("0");
            }

            if(coughCardView.isChecked()) {
                selectedItems.add("SYM_COUGH");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_COUGH");
                joiner.add("0");
            }

            if(diff_breathCardView.isChecked()) {
                selectedItems.add("SYM_SHORT_BREATH");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_SHORT_BREATH");
                joiner.add("0");
            }

            if(headacheCardView.isChecked()) {
                selectedItems.add("SYM_HEADACHE");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_HEADACHE");
                joiner.add("0");
            }

            if(fatigueCardView.isChecked()) {
                selectedItems.add("SYM_FATIGUE");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_FATIGUE");
                joiner.add("0");
            }

            if(acheCardView.isChecked()) {
                selectedItems.add("SYM_MUSCLEPAIN");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_MUSCLEPAIN");
                joiner.add("0");
            }

            if(sore_throatCardView.isChecked()) {
                selectedItems.add("SYM_THROAT");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_THROAT");
                joiner.add("0");
            }

            if(loss_taste_smellCardView.isChecked()) {
                selectedItems.add("SYM_SMELL_TASTE");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_SMELL_TASTE");
                joiner.add("0");
            }

            if(diarrheaCardView.isChecked()) {
                selectedItems.add("SYM_DIARRHEA");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_DIARRHEA");
                joiner.add("0");
            }

            if(runny_noseCardView.isChecked()) {
                selectedItems.add("SYM_RUNNY_NOSE");
                joiner.add("1");
            } else {
                notSelectedItems.add("SYM_RUNNY_NOSE");
                joiner.add("0");
            }

            if(selectedItems.isEmpty()) {
                res = 0.02587925091 * 100;
            } else {
                InferenceEngine inferenceEngine = new InferenceEngine(this);
                res = inferenceEngine.getProbability(selectedItems, notSelectedItems, sex, age) * 100;

                if(res>=50) {
                    joiner.add("1");
                } else {
                    joiner.add("2");
                }

                String data = joiner.toString() + "\n";
                try {
                    addToDataset(data);
                } catch (IOException e) {
                    Log.e("FILE-ERR1", e.getMessage());
                }
            }

            Intent result = new Intent(this, ResultActivity.class);
            result.putExtra("result", res);
            result.putExtra("user", Parcels.wrap(user));
            startActivity(result);
        });
    }

    private void addToDataset (String data) throws IOException {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        File file = new File(getFilesDir() + "/dataset.txt");

        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            osw.append(data);
            osw.close();
            fos.close();
        } catch (IOException e) {
            Log.e("FILE-ERR2", e.getMessage());
        } finally {
            if(osw != null) {
                osw.close();
                osw = null;
            }
            if(fos != null) {
                fos.close();
                fos = null;
            }
        }
    }
}

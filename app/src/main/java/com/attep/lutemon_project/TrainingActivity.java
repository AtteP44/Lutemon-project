package com.attep.lutemon_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrainingActivity extends AppCompatActivity {

    private int progress;
    private static int complete = 100;
    ImageView lutemonImg;
    TextView lutemonName, lutemonLvl, progressTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent oldIntent = getIntent();
         int position = oldIntent.getIntExtra("chosenOne",0);
         Lutemon trainingOne = Storage.getInstance().getLutemonsByLocation("Training").get(position);
         lutemonImg = findViewById(R.id.TrainableLutemonIv);
         lutemonName = findViewById(R.id.TrainingLutemonName);
         lutemonLvl = findViewById(R.id.TrainingLutemonLvl);



         lutemonImg.setImageResource(trainingOne.getImage());
         lutemonName.setText(trainingOne.getName());
         lutemonLvl.setText("Lvl: "+trainingOne.getLevel());



        ImageView returnBtn = findViewById(R.id.ReturnBtn);

        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });


    }

    public void trainLutemon(View view){
        progressTxt = findViewById(R.id.TrainingProgressTxt);
        lutemonLvl = findViewById(R.id.TrainingLutemonLvl);
        Intent oldIntent = getIntent();
        int position = oldIntent.getIntExtra("chosenOne",0);
        Lutemon trainingOne = Storage.getInstance().getLutemonsByLocation("Training").get(position);
        progress++;
        progressTxt.setText(progress+ "/" + complete);
        if(progress == complete){
            trainingOne.levelUp();
            lutemonLvl.setText("Lvl: "+trainingOne.getLevel());
            Toast.makeText(this, "Level up!", Toast.LENGTH_SHORT).show();

            progress = 0;

        }
    }


}
package com.attep.lutemon_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArenaActivity extends AppCompatActivity {
    private int lutemon1Id;
    private int lutemon2Id;
    private  Lutemon l1;
    private  Lutemon l2;
    private ImageView lutemon1Img;
    private ImageView lutemon2Img;
    private TextView lutemon1Hp;
    private TextView lutemon2Hp;
    private TextView lutemon1Info;
    private TextView lutemon2Info;
    private TextView lutemon1Name;
    private TextView lutemon2Name;
    private TextView battleInfoTxt;
    private Button continueBtn;
    private boolean firstContinue = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_arena);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        continueBtn = findViewById(R.id.arenaContinueBtn);
        continueBtn.setOnClickListener(v -> continueFight());
        Button fleeBtn = findViewById(R.id.arenaFleeBtn);
        fleeBtn.setOnClickListener(v -> fleeFight());

        lutemon1Img = findViewById(R.id.lutemon1Img);
        lutemon2Img = findViewById(R.id.lutemon2Img);
        lutemon1Hp   = findViewById(R.id.lutemon1HpTxt);
        lutemon2Hp   = findViewById(R.id.lutemon2HpTxt);
        lutemon1Info = findViewById(R.id.lutemon1InfoTxt);
        lutemon2Info = findViewById(R.id.lutemon2InfoTxt);
        lutemon1Name = findViewById(R.id.lutemon1Name);
        lutemon2Name = findViewById(R.id.lutemon2Name);
        battleInfoTxt = findViewById(R.id.battleInfoTxt);

        lutemon1Id = getIntent().getIntExtra("lutemon1_id", -1);
        lutemon2Id = getIntent().getIntExtra("lutemon2_id", -1);

        l1 = Storage.getInstance().getLutemonById(lutemon1Id);
        l2 = Storage.getInstance().getLutemonById(lutemon2Id);
        lutemon1Img.setImageResource(l1.getImage());
        lutemon2Img.setImageResource(l2.getImage());
        lutemon1Hp.setText(String.valueOf(l1.getHealth()));
        lutemon2Hp.setText(String.valueOf(l2.getHealth()));
        lutemon1Info.setText(l1.getInfoString());
        lutemon2Info.setText(l2.getInfoString());
        lutemon1Name.setText(l1.getName());
        lutemon2Name.setText(l2.getName());
    }
    public void updateLutemons(){
        lutemon1Img.setImageResource(l1.getImage());
        lutemon2Img.setImageResource(l2.getImage());
        lutemon1Hp.setText(String.valueOf(l1.getHealth()));
        lutemon2Hp.setText(String.valueOf(l2.getHealth()));
        lutemon1Info.setText(l1.getInfoString());
        lutemon2Info.setText(l2.getInfoString());
        lutemon1Name.setText(l1.getName());
        lutemon2Name.setText(l2.getName());
    }
    public void continueFight(){
        if(!firstContinue){
            Lutemon temporary = l1;
            l1 = l2;
            l2 = temporary;
        }
        else { firstContinue = false;}
        String fightStr = l2.defense(l1);
        battleInfoTxt.setText(fightStr);
        updateLutemons();
        if (l2.getHealth() == 0){
            continueBtn.setVisibility(View.GONE);
            l1.levelUp();
        }
    }
    public void fleeFight(){
        finish();
    }
}
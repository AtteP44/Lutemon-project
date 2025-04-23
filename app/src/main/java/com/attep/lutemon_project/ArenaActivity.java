package com.attep.lutemon_project;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ArenaActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.getMainLooper());

    private int lutemon1Id;
    private int lutemon2Id;
    private  Lutemon l1;
    private  Lutemon l2;

    private boolean l1AttacksNext = true;
    private ImageView lutemon1Img;
    private ImageView lutemon2Img;
    private TextView lutemon1Hp;
    private TextView lutemon2Hp;
    private TextView lutemon1Name;
    private TextView lutemon2Name;
    private TextView battleInfoTxt;

    private TextView dmgIndicator1;

    private TextView dmgIndicator2;

    private TextView finalDmgIndicator;
    private Button continueBtn, fleeBtn, exitBtn;

    private  ImageView fightImg1, fightImg2;



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
        fleeBtn = findViewById(R.id.arenaFleeBtn);
        fleeBtn.setOnClickListener(v -> fleeFight());
        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(v -> exitFight());

        lutemon1Img = findViewById(R.id.lutemon1Img);
        lutemon2Img = findViewById(R.id.lutemon2Img);
        fightImg1 = findViewById(R.id.arenaStatusImg1);
        fightImg2 = findViewById(R.id.arenaStatusImg2);
        lutemon1Hp   = findViewById(R.id.lutemon1HpTxt);
        lutemon2Hp   = findViewById(R.id.lutemon2HpTxt);
        lutemon1Name = findViewById(R.id.lutemon1Name);
        lutemon2Name = findViewById(R.id.lutemon2Name);
        battleInfoTxt = findViewById(R.id.battleInfoTxt);
        dmgIndicator1 = findViewById(R.id.arenaDmgIndicator1);
        dmgIndicator2 = findViewById(R.id.arenaDmgIndicator2);
        finalDmgIndicator = findViewById(R.id.finalDmgIndicator);

        lutemon1Id = getIntent().getIntExtra("lutemon1_id", -1);
        lutemon2Id = getIntent().getIntExtra("lutemon2_id", -1);

        l1 = Storage.getInstance().getLutemonById(lutemon1Id);
        l2 = Storage.getInstance().getLutemonById(lutemon2Id);
        lutemon1Img.setImageResource(l1.getImage());
        lutemon2Img.setImageResource(l2.getImage());
        lutemon1Hp.setText(l1.getHealthStatus());
        lutemon2Hp.setText(l2.getHealthStatus());
        lutemon1Name.setText(l1.getArenaString());
        lutemon2Name.setText(l2.getArenaString());
    }
    public void updateLutemons(){
        lutemon1Img.setImageResource(l1.getImage());
        lutemon2Img.setImageResource(l2.getImage());
        lutemon1Hp.setText(l1.getHealthStatus());
        lutemon2Hp.setText(l2.getHealthStatus());
        lutemon1Name.setText(l1.getArenaString());
        lutemon2Name.setText(l2.getArenaString());
    }
    public void continueFight(){
        Lutemon attacker = l1AttacksNext ? l1 : l2;
        Lutemon defender = l1AttacksNext ? l2 : l1;
        ImageView swordImg = l1AttacksNext ? fightImg1 : fightImg2;
        ImageView shieldImg = l1AttacksNext ? fightImg2 : fightImg1;
        TextView damageText = l1AttacksNext ? dmgIndicator1 : dmgIndicator2;
        TextView defenseText = l1AttacksNext ? dmgIndicator2 : dmgIndicator1;

        int attackValue = attacker.getAttack();
        int defenseValue = defender.getDefence();
        int finalDmg = attackValue-defenseValue;

        swordImg.setScaleX(1f);
        swordImg.setScaleY(1f);
        shieldImg.setScaleX(1f);
        shieldImg.setScaleY(1f);
        dmgIndicator1.setAlpha(0f);
        dmgIndicator2.setAlpha(0f);
        finalDmgIndicator.setAlpha(0f);
        damageText.setTextColor(0xFFF44336);
        defenseText.setTextColor(0xFF8BC34A);


        handler.post(() -> {
            swordImg.animate()
                    .scaleX(1.5f).scaleY(1.5f)
                    .setDuration(300)
                    .withEndAction(() -> {
                        damageText.setText("-" + attackValue);
                        damageText.animate()
                                .alpha(1f)
                                .setDuration(200)
                                .start();
                    })
                    .start();
        });
        handler.postDelayed(() -> {
            shieldImg.animate()
                    .scaleX(1.5f).scaleY(1.5f)
                    .setDuration(300)
                    .withEndAction(() -> {
                        defenseText.setText("+" + defenseValue);
                        defenseText.animate()
                                .alpha(1f)
                                .setDuration(200)
                                .start();
                    })
                    .start();
        }, 1000);
        handler.postDelayed(() -> {
            finalDmgIndicator.setText("-" + finalDmg);
            finalDmgIndicator.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .start();

            String fightStr = defender.defense(attacker);
            battleInfoTxt.setText(fightStr);
            updateLutemons();
        }, 2000);


        l1AttacksNext = !l1AttacksNext;
        swordImg.setImageResource(R.drawable.lutemon_sword);
        shieldImg.setImageResource(R.drawable.lutemon_shield);


        if (l2.getHealth() == 0){
            continueBtn.setVisibility(View.GONE);
            fleeBtn.setVisibility(View.GONE);
            exitBtn.setVisibility(View.VISIBLE);

            l1.levelUp();
        }
    }
    public void fleeFight(){
        Random random = new Random();
        if(random.nextBoolean()){
            finish();
        } else{
            continueFight();
        }
    }
    public void exitFight(){
        finish();
    }
}
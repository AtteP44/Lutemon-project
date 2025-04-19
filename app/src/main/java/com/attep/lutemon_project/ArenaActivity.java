package com.attep.lutemon_project;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ArenaActivity extends AppCompatActivity {

    ImageView ownImage, opponentImage;
    TextView ownText, opponentText;

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
        Intent oldIntent = getIntent();
        int position = oldIntent.getIntExtra("chosenOne",0);
        Lutemon ArenaOne = Storage.getInstance().getLutemonsByLocation("Arena").get(position);
        ownImage = findViewById(R.id.ownLutemonImg);
        ownText = findViewById(R.id.ownLutemonTxt);
        opponentImage = findViewById(R.id.opponentLutemonImg);
        opponentText = findViewById(R.id.opponentInfoTxt);

        ownImage.setImageResource(ArenaOne.getImage());
        ownText.setText(ArenaOne.getName() +" Hp: "+ ArenaOne.getHealth()+ "/"+ ArenaOne.getMaxHealth());
    }


    public void fleeArena(View view){
        Random random = new Random();
        boolean b = random.nextBoolean();

        if(b == true){
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Failed to flee the fight", Toast.LENGTH_SHORT).show();
        }
    }
}
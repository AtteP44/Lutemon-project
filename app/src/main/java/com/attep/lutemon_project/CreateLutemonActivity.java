package com.attep.lutemon_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateLutemonActivity extends AppCompatActivity {

    private EditText lutemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView returnBtn = findViewById(R.id.ReturnBtn);

        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });


    }

    public void createLutemon(View view){


        RadioGroup lutemonTypeRg = findViewById(R.id.LutemonTypeRg);
        lutemonName = findViewById(R.id.ChooseNameTxt);
        if(String.valueOf(lutemonName.getText()).isEmpty()){
            Toast.makeText(this, "Give a name for lutemon" ,Toast.LENGTH_SHORT).show();
        } else {
            String name = String.valueOf(lutemonName.getText());

            if (lutemonTypeRg.getCheckedRadioButtonId() == R.id.WhiteRb) {
                String type = "White";
                Lutemon newLutemon = new White(name, type);
                Storage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, "Lutemon created" ,Toast.LENGTH_SHORT).show();
            } else if (lutemonTypeRg.getCheckedRadioButtonId() == R.id.BlackRb) {
                String type = "Black";
                Lutemon newLutemon = new Black(name, type);
                Storage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, "Lutemon created" ,Toast.LENGTH_SHORT).show();
            } else if (lutemonTypeRg.getCheckedRadioButtonId() == R.id.OrangeRb) {
                String type = "Orange";
                Lutemon newLutemon = new Orange(name, type);
                Storage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, "Lutemon created" ,Toast.LENGTH_SHORT).show();
            } else if (lutemonTypeRg.getCheckedRadioButtonId() == R.id.GreenRb) {
                String type = "Green";
                Lutemon newLutemon = new Green(name, type);
                Storage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, "Lutemon created" ,Toast.LENGTH_SHORT).show();
            } else if (lutemonTypeRg.getCheckedRadioButtonId() == R.id.PinkRb) {
                String type = "Pink";
                Lutemon newLutemon = new Pink(name, type);
                Storage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, "Lutemon created" ,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Select a type for lutemon", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
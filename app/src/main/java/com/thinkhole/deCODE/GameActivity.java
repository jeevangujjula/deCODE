package com.thinkhole.deCODE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button button_newgame, button_instructions, button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void onclicknewgame(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void onclickexit(View view) {
        this.finish();
    }

    public void onclickinstructions(View view) {
        Intent instructionsintent = new Intent(this, instructions.class);
        startActivity(instructionsintent);
    }

}

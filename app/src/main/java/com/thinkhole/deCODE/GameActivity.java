package com.thinkhole.deCODE;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button button_newgame, button_instructions, button_exit;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onclicknewgame(View view) {
        vib.vibrate(50);
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void onclickexit(View view) {
        vib.vibrate(50);
        this.finish();
    }

    public void onclickinstructions(View view) {
        vib.vibrate(50);
        Intent instructionsintent = new Intent(this, instructions.class);
        startActivity(instructionsintent);
    }

}

package com.thinkhole.deCODE;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jeevan on 2/6/17.
 */

public class instructions  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);
    }
    public void onclickclose(View view){
        this.finish();
    }

}

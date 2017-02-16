package com.thinkhole.deCODE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

	final Context context = this;
	String input;
	int loop = 6; //maximum guesses for a game is 6.

    protected void onCreate(Bundle savedInstanceState) {

        //When the app is run for the first time, Flash message will be displayed.
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(this, PlayActivity.class));
            Toast.makeText(this, "Welcome !!", Toast.LENGTH_LONG).show();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Button btnA = (Button) findViewById(R.id.buttonA);  //Button A
        Button btnB = (Button) findViewById(R.id.buttonB);  //Button B
        Button btnC = (Button) findViewById(R.id.buttonC);  //Button C
        Button btnD = (Button) findViewById(R.id.buttonD);  //Button D
        Button btnE = (Button) findViewById(R.id.buttonE);  //Button E
        Button btnF = (Button) findViewById(R.id.buttonF);  //Button F
        Button btnClear = (Button) findViewById(R.id.button_clear); //Button Clear
        Button btnCheck = (Button) findViewById(R.id.button_check); //Button Check
        final TextView text = (TextView) findViewById(R.id.editText);//Input text field.

        //on click button A, append character A to the input text field
        btnA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("A");
            }
        });
        //on click button B, append character B to the input text field
        btnB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("B");
            }
        });
        //on click button C, append character C to the input text field
        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("C");
            }
        });
        //on click button D, append character D to the input text field
        btnD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("D");
            }
        });
        //on click button E, append character E to the input text field
        btnE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("E");
            }
        });
        //on click button F, append character F to the input text field
        btnF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                text.append("F");
            }
        });

        // Funtioning of clear button
        if (btnClear != null) {
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textString = text.getText().toString();
                    if (textString.length() > 0) {
                        text.setText(textString.substring(0, textString.length() - 1));
                    }
                }
            });
        }
        // Set a random code from A,B,C,D,E,F
        char[] letters = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newArray = new char[4];
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            char p = letters[rand.nextInt(letters.length)];
            newArray[i] = p;
            for (int j = i - 1; j != i && j > -1; j--) {
                if (newArray[i] == newArray[j]) {
                    i--;
                }
            }
        }

        final String flag = String.valueOf(newArray);  // Code to be guessed.
        btnCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                input = text.getText().toString();
                char[] n = new char[4];
                int c = 0, j = 0, x = 0, q = 0;
                // If input code length is not 4 letters, then alert will be popped
                if (input.length() != 4) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1
                            .setTitle("Incorrect entry !")
                            .setMessage("Please enter a 4 letter code")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    text.hasFocus();
                                }
                            })
                            .show();
                }
                // If all the six chances to guess the code are done,
                // loop will get value 1 and hence terminate the play with an alert message,
                if (loop == 1) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                    builder2
                            .setTitle("You Lose !")
                            .setMessage("The CODE is : " + flag + "\n\n Play again?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Intent loseintent = getIntent();
                                    startActivity(loseintent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    PlayActivity.this.finish();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                // If guess is correct, then the game is won
                if (flag.equals(input)) {
                    won(input); //Calling method won
                }
                // if input is incorrect and valid
                else if (!flag.equals(input) && input.length() == 4) {
                    for (int k = 0; k < input.length(); k++) {
                        char l = input.charAt(k);
                        char m = flag.charAt(k);
                        // checks if caharater postions of the code and input guess by user is same
                        if (l == m) {
                            c++; // if same, then increments Correct position of letters
                        } else {

                            // forxloop is to check if any other letter in
                            // a different position matches the letter in the code
                            forxloop:
                            for (q = flag.length() - 1; q > -1; q--) {
                                if ((l == flag.charAt(q))) {
                                    j++;
                                    break forxloop; // if matches, then exit out of forxloop
                                }
                                if ((q != k) && (l == flag.charAt(q))) {
                                    // do nothing
                                }
                            }
                            // if input letter in the user guess doesnt match with any letter in the code,
                            // incorrect position is incremented.
                            if (q == -1) {
                                n[k] = l;
                                x++;
                            }
                        }
                    }
                    init(c, j, x, loop, input); // display hints for user input
                    text.setText(""); // After every guess, input textfield is set to clear.
                    loop--; // decrement loop variable to count on number of guesses.
                }
            }
        });
    }

	public void init(int c, int j, int x, int loop, String text) {
		TableLayout stk = (TableLayout) findViewById(R.id.table_main);
		TableRow tbrow = new TableRow(this);
		TextView t1v = new TextView(this);	t1v.setWidth(260);	t1v.setHeight(150);	t1v.setTextColor(Color.rgb(215,204,200));	t1v.setGravity(Gravity.CENTER); t1v.setTextSize(16);
		TextView t2v = new TextView(this);	t2v.setWidth(270);	t2v.setHeight(150);	t2v.setTextColor(Color.rgb(215,204,200));	t2v.setGravity(Gravity.CENTER); t2v.setTextSize(16);
		TextView t3v = new TextView(this);	t3v.setWidth(170);	t3v.setHeight(150);	t3v.setTextColor(Color.rgb(215,204,200));	t3v.setGravity(Gravity.CENTER); t3v.setTextSize(16);
		TextView t4v = new TextView(this);	t4v.setWidth(290);	t4v.setHeight(150);	t4v.setTextColor(Color.rgb(215,204,200));	t4v.setGravity(Gravity.CENTER); t4v.setTextSize(16);
		TextView t5v = new TextView(this);	t5v.setWidth(260);	t5v.setHeight(150);	t5v.setTextColor(Color.rgb(215,204,200));	t5v.setGravity(Gravity.CENTER); t5v.setTextSize(16);
		t1v.setText("" + loop);		tbrow.addView(t1v);
		t2v.setText(" " + input);	tbrow.addView(t2v);
		t3v.setText("" + c);		tbrow.addView(t3v);
		t4v.setText("" + j);		tbrow.addView(t4v);
		t5v.setText("" + x);		tbrow.addView(t5v);
		HashSet<String> set = new HashSet<String>();
		stk.addView(tbrow);

        // if the input letters doesnt match any letter in the code
		if(x==4){
			AlertDialog.Builder builder_gutter = new AlertDialog.Builder(context);
			builder_gutter
					.setTitle("Oops !")
					.setMessage("GUTTER !!")
					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					})
					.setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
	}

    // Alert box after winning the game .
	public void won(String code) {
		AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
		builder3
				.setTitle("You Won !")
				.setMessage("That's awesome !! The CODE is : " + code + "\n\n Play Again?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// continue with delete
                        Intent wonintent = getIntent();
                        startActivity(wonintent);
                        wonintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					}
				}).setNegativeButton("No",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                PlayActivity.this.finish();
            }
        })
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

    public void onclickhowtoplay(View view){
        Intent instructionsintent = new Intent(this,instructions.class);
        startActivity(instructionsintent);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, GameActivity.class));
        finish();

    }
}

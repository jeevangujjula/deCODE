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
	int loop = 6;
	protected void onCreate(Bundle savedInstanceState) {
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
		Button btnA = (Button) findViewById(R.id.buttonA);
		Button btnB = (Button) findViewById(R.id.buttonB);
		Button btnC = (Button) findViewById(R.id.buttonC);
		Button btnD = (Button) findViewById(R.id.buttonD);
		Button btnE = (Button) findViewById(R.id.buttonE);
		Button btnF = (Button) findViewById(R.id.buttonF);
		Button btnClear = (Button) findViewById(R.id.button_clear);
		Button btnCheck = (Button) findViewById(R.id.button_check);
		final TextView text = (TextView) findViewById(R.id.editText);
		btnA.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("A");
			}
		});
		btnB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("B");
			}
		});
		btnC.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("C");
			}
		});
		btnD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("D");
			}
		});
		btnE.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("E");
			}
		});
		btnF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				text.append("F");
			}
		});
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
		char[] letters = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
		char[] newArray = new char[4];
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			char c = letters[rand.nextInt(letters.length)];
			newArray[i] = c;
			for (int j = i - 1; j != i && j > -1; j--) {
				if (newArray[i] == newArray[j]) {
					i--;
				}
			}
		}

		final String flag = String.valueOf(newArray);
		btnCheck.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				input = text.getText().toString();
				char[] n = new char[4];
				int p = 0;
				int q = 0;
				int r = 0;
				int x = 0;
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
				if (loop == 1) {
					AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
					builder2
							.setTitle("You Lose !")
							.setMessage("The CODE is : " + flag+"\n\n Play again?")
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									// continue with delete
                                    Intent loseintent = getIntent();
                                    startActivity(loseintent);
                                }
							})
                            .setNegativeButton("No",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            PlayActivity.this.finish();
                        }
                    })
							.setIcon(android.R.drawable.ic_dialog_alert)
							.show();
				}
				if (flag.equals(input)) {
					won(input);
				} else if (!flag.equals(input) && input.length() == 4) {
					for (int k = 0; k < input.length(); k++) {
						char l = input.charAt(k);
						char m = flag.charAt(k);
						if (l == m) {
							p++;
						} else {
							forxloop:
							for (x = flag.length() - 1; x > -1; x--) {
								if ((l == flag.charAt(x))) {
									r++;
									break forxloop;
								}
								if ((x != k) && (l == flag.charAt(x))) {
								}
							}
							if (x == -1) {
								n[k] = l;
								q++;
							}
						}
					}
					init(p, r, q, loop, input);
					text.setText("");
					loop--;
				}
			}
		});
	}

	public void init(int p, int r, int q, int loop, String text) {
		TableLayout stk = (TableLayout) findViewById(R.id.table_main);
		TableRow tbrow = new TableRow(this);
		TextView t1v = new TextView(this);	t1v.setWidth(260);	t1v.setHeight(150);	t1v.setTextColor(Color.rgb(215,204,200));	t1v.setGravity(Gravity.CENTER); t1v.setTextSize(16);
		TextView t2v = new TextView(this);	t2v.setWidth(270);	t2v.setHeight(150);	t2v.setTextColor(Color.rgb(215,204,200));	t2v.setGravity(Gravity.CENTER); t2v.setTextSize(16);
		TextView t3v = new TextView(this);	t3v.setWidth(170);	t3v.setHeight(150);	t3v.setTextColor(Color.rgb(215,204,200));	t3v.setGravity(Gravity.CENTER); t3v.setTextSize(16);
		TextView t4v = new TextView(this);	t4v.setWidth(290);	t4v.setHeight(150);	t4v.setTextColor(Color.rgb(215,204,200));	t4v.setGravity(Gravity.CENTER); t4v.setTextSize(16);
		TextView t5v = new TextView(this);	t5v.setWidth(260);	t5v.setHeight(150);	t5v.setTextColor(Color.rgb(215,204,200));	t5v.setGravity(Gravity.CENTER); t5v.setTextSize(16);
		t1v.setText("" + loop);		tbrow.addView(t1v);
		t2v.setText(" " + input);	tbrow.addView(t2v);
		t3v.setText("" + p);		tbrow.addView(t3v);
		t4v.setText("" + r);		tbrow.addView(t4v);
		t5v.setText("" + q);		tbrow.addView(t5v);
		HashSet<String> set = new HashSet<String>();
		stk.addView(tbrow);

		if(q==4){
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
	public void won(String code) {
		AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
		builder3
				.setTitle("You Won !")
				.setMessage("That's awesome !! The CODE is : " + code + "\n\n Play Again?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// continue with delete
                        Intent loseintent = getIntent();
                        startActivity(loseintent);
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
}

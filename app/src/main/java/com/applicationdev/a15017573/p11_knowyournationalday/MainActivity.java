package com.applicationdev.a15017573.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFacts;
    ArrayList<String> alFacts = new ArrayList<String>();
    ArrayAdapter<String> aaFacts;

//    protected void onResume(){
//        super.onResume();
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String code = preferences.getString("code", "Default value");
//        if (code.equals("738964")) {
//            Toast.makeText(this, "Logged in", Toast.LENGTH_LONG).show();
//        } else {
//
//        }


    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFacts = (ListView)findViewById(R.id.listViewFacts);
        alFacts.add(0, "Singapore National Day is on 9 Aug");
        alFacts.add(1, "Singapore is 52 years old on 2017");
        alFacts.add(2, "The theme is '#OneNationTogether'");

        aaFacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alFacts);
        lvFacts.setAdapter(aaFacts);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout accessCode = (LinearLayout) inflater.inflate(R.layout.access_code, null);
        final EditText etAccessCode = (EditText) accessCode.findViewById(R.id.editTextAccessCode);
        final String code = etAccessCode.getText().toString();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please login").setView(accessCode).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (etAccessCode.getText().toString().equals("738964")) {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//                    SharedPreferences.Editor prefEdit = preferences.edit();
//                    prefEdit.putString("code", code);

                } else {

                    Toast.makeText(MainActivity.this, "You entered the wrong access code", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        }).setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "No access code", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.itemSendToFriend) {
            final String [] list = new String[] {"Email", "SMS"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    .setItems(list, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {

                                    Intent email = new Intent(Intent.ACTION_SEND);
                                    email.putExtra(Intent.EXTRA_EMAIL,
                                            new String[]{"suhailizxc@gmail.com"});
                                    email.putExtra(Intent.EXTRA_SUBJECT, "National Day Facts");
                                    email.putExtra(Intent.EXTRA_TEXT, alFacts.get(0) + "\n" + alFacts.get(1) + "\n" + alFacts.get(2));
                                    email.setType("message/rfc822");
                                try {
                                    startActivity(Intent.createChooser(email, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }

                            } else if (which == 1) {
                                Intent sms = new Intent(Intent.ACTION_SEND);
                                sms.setType("text/plain");
                                sms.putExtra(Intent.EXTRA_TEXT, alFacts.get(0) + "\n" + alFacts.get(1) + "\n" + alFacts.get(2));
                                try {
                                    startActivity(Intent.createChooser(sms, "Send sms"));
                                    finish();
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There is no SMS client installed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.itemQuiz) {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout quiz = (LinearLayout) inflater.inflate(R.layout.quiz, null);
            final RadioGroup rg1 = (RadioGroup)quiz.findViewById(R.id.rg1);
            final RadioGroup rg2 = (RadioGroup)quiz.findViewById(R.id.rg2);
            final RadioGroup rg3 = (RadioGroup)quiz.findViewById(R.id.rg3);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself").setView(quiz).setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    int score = 0;
                    if (rg1.getCheckedRadioButtonId() == R.id.rb1Yes) {
                        score += 0;
                    } else if (rg1.getCheckedRadioButtonId() == R.id.rb1No) {
                        score += 1;
                    }
                    if (rg2.getCheckedRadioButtonId() == R.id.rb2Yes) {
                        score += 1;
                    } else if (rg2.getCheckedRadioButtonId() == R.id.rb2No) {

                    }
                    if (rg3.getCheckedRadioButtonId() == R.id.rb3Yes) {
                        score += 1;
                    } else if (rg3.getCheckedRadioButtonId() == R.id.rb3No) {

                    }
                    Toast.makeText(MainActivity.this, "Score: " + score, Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("I don't know", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(MainActivity.this, "No access code", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();



        } else if (item.getItemId() == R.id.itemQuit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

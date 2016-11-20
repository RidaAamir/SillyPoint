package com.example.emad.splashscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Team extends AppCompatActivity {
    ImageView Allrounder;
    ImageView Batsmen;
    ImageView Bowlers;
    ImageView Wicketkeepers;
    EditText input;
    EditText input1;
    EditText input2;
    EditText input3;
    final String[] FormA = new String[1];
    final String[] FormB = new String[1];
    final String[] FormC = new String[1];
    final String[] FormD = new String[1];
    int a,b,c,d;
    private TextView txtTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        txtTemp =(TextView) findViewById(R.id.teamnames);
        Allrounder = (ImageView) findViewById(R.id.allrounder);
        Batsmen = (ImageView) findViewById(R.id.batsmen);
        Bowlers = (ImageView) findViewById(R.id.bowlers);
        Wicketkeepers = (ImageView) findViewById(R.id.wicketkeeper);


        Intent intent = getIntent();

        String strTemp = intent.getStringExtra("Teams");
        txtTemp.setText(strTemp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Formation");
        builder.setMessage("What Formation do you want?");

        input = new EditText(this);
        builder.setView(input);


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Set Formation");
        builder1.setMessage("What Formation do you want?");

        input1 = new EditText(this);
        builder1.setView(input1);

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Set Formation");
        builder2.setMessage("What Formation do you want?");

        input2 = new EditText(this);
        builder2.setView(input2);

        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setTitle("Set Formation");
        builder3.setMessage("What Formation do you want?");

        input3 = new EditText(this);
        builder3.setView(input3);

        //For First Builder
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + input.getText().toString(), android.widget.Toast.LENGTH_LONG).show();
                a = Integer.parseInt(input.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad  = builder.create();

        //For Second Builder
        builder1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + input1.getText().toString(), android.widget.Toast.LENGTH_LONG).show();
                b = Integer.parseInt(input1.getText().toString());
            }
        });

        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        final AlertDialog ad1  = builder1.create();

        //For Third Builder
        builder2.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + input2.getText().toString(), android.widget.Toast.LENGTH_LONG).show();
                c = Integer.parseInt(input2.getText().toString());
            }
        });

        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad2  = builder2.create();


        //For Fourth Builder
        builder3.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + input3.getText().toString(), android.widget.Toast.LENGTH_LONG).show();
                d = Integer.parseInt(input3.getText().toString());
            }
        });

        builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad3  = builder3.create();





        Allrounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.setTitle("Set AllRounders Formation");
                ad.setMessage("What Formation do you want?");
                //FormA[0] = input.getText().toString();

                ad.show();

            }
        });

        Batsmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad1.setTitle("Set Batsmen Formation");
                ad1.setMessage("What Formation do you want?");
                //FormB[0] = input1.getText().toString();

                ad1.show();


            }
        });

        Bowlers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad2.setTitle("Set Bowlers Formation");
                ad2.setMessage("What Formation do you want?");
                //FormC[0] = input.getText().toString();
                ad2.show();

            }
        });

        Wicketkeepers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad3.setTitle("Set Wicket Keepers Formation");
                ad3.setMessage("What Formation do you want?");
//                FormD[0] = input.getText().toString();
                ad3.show();

            }
        });


    }

public void GoBack(View v)
{
    Intent intent = new Intent(this, WelcomeMenu.class);
    startActivity(intent);
    finish();
}


    public void GoForward(View v)
    {

        Intent intent = new Intent(this, MyFormation.class);
        Bundle extras = new Bundle();
        Bundle extras1 = new Bundle();
        extras.putString("AllRounders",FormA[0]);
        extras.putString("Batsman",FormB[0]);
        extras.putString("Bowlers",FormC[0]);
        extras1.putString("WicketKeepers",FormD[0]);


        intent.putExtra("AllRounders",a);
        intent.putExtra("Batsman",b);
        intent.putExtra("Bowlers",c);
        intent.putExtra("WicketKeepers",d);
        //intent.putExtras(extras1);

        startActivity(intent);
        finish();
    }
}

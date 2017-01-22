package com.example.emad.splashscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;

public class SelectTeam extends AppCompatActivity {
    MyDBHandler dbhandler;
    String team1,team2;
    String UserName = "";
    public TextView a;
    ArrayList<String> Team12 = new ArrayList<>();
    ArrayList<String> TeamId = new ArrayList<>();
    Player p= new Player();
    String[] data = {"0"};
    String[] data11 = {"0"};
    Player[] squad_team1 = new Player[70];
    Player[] squad_team2 = new Player[70];
    //int index =0;
    private TextView output;
    //Stats c = new Stats();

    ImageView Bowlers;
    ListView listview = null;
    ArrayList<String> Teams = new ArrayList<>();//creating new generic arraylist
    ArrayList<String> SelectedBowlers = new ArrayList<>();
    int index =0;
    ArrayList<String> PlayerType = new ArrayList<>();
    ArrayList<String> PlayerId = new ArrayList<>();
    ArrayList<String> PlayerInfo = new ArrayList<>();
    String MatchName = "";
    String Id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
        dbhandler = new MyDBHandler(this);
        Bowlers = (ImageView) findViewById(R.id.bowlers);
        //output = (TextView)findViewById(R.id.emad);
        listview = new ListView(this);
        Intent intent = getIntent();
        MatchName = intent.getStringExtra("Teams");
        Id = intent.getStringExtra("Tid");
        UserName = intent.getStringExtra("User");
        // http://cricapi.com/api/playerStats?pid=
        JSONTask b = new JSONTask();
           b.execute("http://cricapi.com/api/fantasySummary?unique_id="+ Id +"&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
        //b.execute("http://cricapi.com/api/fantasySummary?unique_id=1050227&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
    }

    public class JSONTask extends AsyncTask<String,String,String>
    {


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null)
                    {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String response2_ = s;

            for(int i=0;i<70;i++){
                squad_team1[i]=new Player();
                squad_team2[i]=new Player();
            }
            //team1,team2;
            // response_='{'+response_;
            final JSONObject obj1;
            try {
                obj1 = new JSONObject(response2_);

                final JSONObject test=obj1.getJSONObject("data");
                final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
                System.out.println(geodata1+"\n\n\n");
                final JSONObject team1_=geodata1.getJSONObject(0);
                final JSONObject team2_=geodata1.getJSONObject(1);
                team1=team1_.getString("name");
                team2=team2_.getString("name");
                // Country names----------------
                System.out.println(team1+"-------------\n");


                //Putting Players in Array---------------
                final JSONArray players1=team1_.getJSONArray("players");
                final JSONArray players2=team2_.getJSONArray("players");
                //System.out.println(players1);
                //System.out.println(players2);

                int n1=players1.length();


                AsyncTask a;
                //PlayerType.add("All-Rounder");
                String id = "";
                for(int i=0;i<n1;i++) {    //Team1 k players initiate horahe hen
                    final JSONObject player = players1.getJSONObject(i);
                    squad_team1[i].setName(player.getString("name"));
                    p = squad_team1[i];
                    id = player.getString("pid");
                    PlayerId.add(id);

                }

                int n2=players2.length();
                for(int i=0;i<n2;i++) {    //Team2 k players initiate horahe hen
                    final JSONObject player1 = players2.getJSONObject(i);
                    squad_team2[i].setName(player1.getString("name"));
                    p = squad_team2[i];
                    String id1 = player1.getString("pid");
                    PlayerId.add(id1);
                }

                int n3 = squad_team1.length;
                for(int i=0;i<n1;i++){
                    // System.out.println(i+" --");
                    //data[i] = squad_team1[i].name;//.Display_Player();//har player ki info print hogii!!
                    Team12.add(squad_team1[i].name);

                }


                for(int i=0;i<n2;i++){
                    // System.out.println(i+" --");
                    //data[i] = squad_team1[i].name;//.Display_Player();//har player ki info print hogii!!
                    Team12.add(squad_team2[i].name);

                }

                data = new String[Team12.size()];
                data11 = new String[Team12.size()];

                for (int i=0;i< Team12.size();i++)
                {
                    data[i] = Team12.get(i).toString();// + "      " + PlayerType.get(i).toString();
                }

                //CountDownLatch doneSignal = new CountDownLatch(Team12.size());
                for (int i=0;i< Team12.size();i++)
                {
                    //    data[i] = data[i]+ " "+ PlayerType.get(i).toString();// + "      " + PlayerType.get(i).toString();
                    //synchronized (new Stats().execute("http://cricapi.com/api/playerStats?pid="+PlayerId.get(i).toString()+ "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2")) {
                    new Stats().execute("http://cricapi.com/api/playerStats?pid="+PlayerId.get(i).toString()+ "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
                    //doneSignal.await();
                    //}

                    //data[i] = data[i]+ " "+ PlayerType.get(i).toString();
                }

                final ListView listV = (ListView)findViewById(R.id.ListView201);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectTeam.this, R.layout.blayout,data);
                adapter.notifyDataSetChanged();
                final android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(SelectTeam.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Getting Team Players... Sabr Kijye");
                progressDialog.show();


                // TODO: Implement your own authentication logic here.

                //Display Authentication Dialog for about 60 seconds
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run()
                            {

                                adapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        }, 60000);

                listV.setAdapter(adapter);
                listV.setItemsCanFocus(true);

                listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        CheckedTextView ctv = (CheckedTextView)view;
                        // android.widget.Toast.makeText(getBaseContext(), "Item Clicked!", android.widget.Toast.LENGTH_LONG).show();
                        String selectedbowler = ((TextView)view).getText().toString();

                        if (SelectedBowlers.contains(selectedbowler))
                        {
                            SelectedBowlers.remove(selectedbowler);
                            ctv.setChecked(false);
                           // view.setBackgroundColor(Color.TRANSPARENT);
                            //listV.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                        }
                        else
                        {

                            //listV.setItemChecked(position,true);
                            ctv.setChecked(true);
                            //view.setBackgroundColor(Color.BLUE);
                            // listV..setBackgroundColor(Color.BLUE);
                            SelectedBowlers.add(selectedbowler);
                            String Bowlers = "";
                            for (String Bowler:SelectedBowlers)
                            {
                                Bowlers += "-"+Bowler + "\n";
                            }
                            android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + Bowlers, android.widget.Toast.LENGTH_LONG).show();
                        }
                        /*Intent intent = new Intent(MainActivity.this,Team.class); //TeamPlayers.java
                        //android.support.v7.widget.AppCompatButton b = (android.support.v7.widget.AppCompatButton)findViewById(R.id.button);


                        TextView b = (TextView) view;
                        String Text = b.getText().toString();
                        String id2 = "";
                        for (int i=0;i<TeamId.size();i++)
                        {
                            if (Text.equals(Teams.get(i).toString()))
                            {
                                id2 = TeamId.get(i).toString();
                            }
                        }

                        intent.putExtra("Teams", Text);
                        intent.putExtra("Tid",id2);
                        startActivity(intent);
                        finish();*/
                    }
                });

               /* listview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                listview.requestLayout();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Set Formation");
                builder.setMessage("What Formation do you want?");
                //String [] data = {"Zulfiqar Babar","Sohail Khan", "Shoaib Malik","Shahid Afridi","Yasir Shah","Mohammad Nawaz"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.blayout,data);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedbowler = ((TextView)view).getText().toString();

                        if (SelectedBowlers.contains(selectedbowler))
                        {
                            SelectedBowlers.remove(selectedbowler);
                        }
                        else
                        {
                            listview.setItemChecked(position,true);
                            SelectedBowlers.add(selectedbowler);
                        }
                    }
                });
                //input = new EditText(this);
                builder.setView(listview);
                // new JSONTask().execute("http://cricapi.com/api/cricket");
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Bowlers = "";
                        for (String Bowler:SelectedBowlers)
                        {
                            Bowlers += "-"+Bowler + "\n";
                        }
                        android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + Bowlers, android.widget.Toast.LENGTH_LONG).show();
                        //a = Integer.parseInt(input.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                final AlertDialog ad  = builder.create();

                Bowlers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.setTitle("Squads");
                        ad.setMessage("Select Bowlers: ");
                        //FormC[0] = input.getText().toString();
                        ad.show();

                    }
                });


*/

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    public class Stats extends AsyncTask<String,ArrayList<String>,String> {
        /*private final CountDownLatch doneSignal;

        public Stats(CountDownLatch doneSignal)
        {

            this.doneSignal = doneSignal;
        }
*/
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            //          doneSignal.countDown();
            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                String response2_ = s;
                Double[] BattingAvg = new Double[3];
                Double[] BowlingAvg = new Double[3];
                final JSONObject obj1 = new JSONObject(response2_);
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException e)
                {
                    System.out.println(e);
                }
                if (!obj1.isNull("data")) {
                    final JSONObject data1 = obj1.getJSONObject("data");
                    // System.out.println(data);
                    if (obj1.has("playingRole")) {
                        p.setType(obj1.getString("playingRole"));
                        ;
                    } else {
                        p.setType("TBD");
                    }

                    // System.out.println(data);
                    // GETTING Batting AVERAGES
                    final JSONObject batting = data1.getJSONObject("batting");
                    final JSONObject ListA = batting.getJSONObject("listA");
                    final JSONObject FC = batting.getJSONObject("firstClass");
                    final JSONObject tests = batting.getJSONObject("tests");
                    //GETTING BOWLING AVERAGE NOW
                    final JSONObject bowling = data1.getJSONObject("bowling");
                    final JSONObject ListA_ = bowling.getJSONObject("listA");
                    final JSONObject FC_ = bowling.getJSONObject("firstClass");
                    final JSONObject tests_ = bowling.getJSONObject("tests");
                    String[] batAverages = new String[3];
                    String[] bowlAverages = new String[3];

                    //SOME INPUT HAVE "-" instead of numbers...
                    batAverages[0] = (String) FC.get("Ave");
                    batAverages[1] = (String) ListA.get("Ave");
                    batAverages[2] = (String) tests.get("Ave");
                    bowlAverages[0] = (String) FC_.get("Ave");
                    bowlAverages[1] = (String) ListA_.get("Ave");
                    bowlAverages[2] = (String) tests_.get("Ave");

                    for (int i = 0; i < 3; i++) {
                        // System.out.println(batAverages[i]);
                        if (batAverages[i].equals("-")) {
                            BattingAvg[i] = 0.0;
                        } else {
                            BattingAvg[i] = (Double.parseDouble(batAverages[i]));
                        }
                    }

                    //System.out.println(a+"DDDDDDDDDDDDDDDDDDD");
                    for (int i = 0; i < 3; i++) {
                        //System.out.println(bowlAverages[i]);
                        if (bowlAverages[i].equals("-")) {
                            //	  System.out.println("DDDDDDDDDDDDDDDDDDD");
                            BowlingAvg[i] = 0.0;
                        } else {
                            BowlingAvg[i] = (Double.parseDouble(bowlAverages[i]));
                        }
                    }
                    // System.out.println("\n ----------- Checking--\n");
                    p.setcost("", BattingAvg, BowlingAvg);
                    double a = p.printCost();
                    PlayerType.add(p.getType());
                    //for (int i=0;i< Team12.size();i++)
                    //if (p.getType().equals("bowler"))
                    {
                        data[index] = data[index] + " " + p.getType() + " " + p.cost;// + "      " + PlayerType.get(i).toString();

                    }
                    index++;
                }
            }
            catch(JSONException e){

            }


        }

    }


    public void ShowFormation(View view)
    {
         /*if(SelectedBowlers.isEmpty())
        {
            android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }
            else*/
       /* if(SelectedBowlers.size() > 0)
        {

            android.widget.Toast.makeText(getBaseContext(), "I'm not empty :) I passed this stage", android.widget.Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DisplayTeam.class);
            ArrayList <String> OnlyBowlers = new ArrayList<>();

            for (int i = 0;i< SelectedBowlers.size();i++)
            {
                if (SelectedBowlers.get(i).contains("Bowler") || SelectedBowlers.get(i).contains("bowling") || SelectedBowlers.get(i).contains("Bowling"))
                {
                    OnlyBowlers.add(SelectedBowlers.get(i).toString());
                }
            }

            intent.putStringArrayListExtra("Teams", OnlyBowlers);
            startActivity(intent);
            finish();
        }
        else {
            android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }*/
    }

    public void userCreated(View v)
    {
        /*if(SelectedBowlers.isEmpty())
        {
            android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }
            else*/
         a = (TextView)findViewById(R.id.teamsquad);
        String TeamName = a.getText().toString();
        boolean CheckName;
        if (TeamName != null || !TeamName.equals(""))
        {
            CheckName = true; // TeamName is given by star
        }
        else
        {
            CheckName = false;
        }


        if(SelectedBowlers.size() > 0 && SelectedBowlers.size() == 11 && CheckName)
        {

            android.widget.Toast.makeText(getBaseContext(), "I'm not empty :) I passed this stage", android.widget.Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Team.class);
            ArrayList <String> OnlyBowlers = new ArrayList<>();
            ArrayList <String> OnlyAlrounder = new ArrayList<>();
            ArrayList <String> OnlyBatsman = new ArrayList<>();
            ArrayList <String> OnlyWicketKeeper = new ArrayList<>();
            dbhandler.createTeam();
            for (int j=0;j<SelectedBowlers.size();j++)
            {
                dbhandler.setTeam(UserName,TeamName, MatchName,Id,SelectedBowlers.get(j).toString());
            }


            for (int i = 0;i< SelectedBowlers.size();i++)
            {
                if (SelectedBowlers.get(i).contains("Bowler") || SelectedBowlers.get(i).contains("bowling") || SelectedBowlers.get(i).contains("Bowling"))
                {
                    OnlyBowlers.add(SelectedBowlers.get(i).toString());
                }

                if (SelectedBowlers.get(i).contains("allrounder") || SelectedBowlers.get(i).contains("Allrounder") || SelectedBowlers.get(i).contains("All-Rounder") || SelectedBowlers.get(i).contains("Bowling allrounder"))
                {
                    OnlyAlrounder.add(SelectedBowlers.get(i).toString());
                }

                if (SelectedBowlers.get(i).contains("batsman") || SelectedBowlers.get(i).contains("Batsman"))
                {
                    OnlyBatsman.add(SelectedBowlers.get(i).toString());
                }

                if (SelectedBowlers.get(i).contains("Wicketkeeper") || SelectedBowlers.get(i).contains("WicketKeeper") || SelectedBowlers.get(i).contains("Wicketkeeper batsman"))
                {
                    OnlyWicketKeeper.add(SelectedBowlers.get(i).toString());
                }

            }

            intent.putExtra("Tid",Id);
            intent.putExtra("User",UserName);
            intent.putExtra("TeamName",TeamName);
            intent.putStringArrayListExtra("Bowlers", OnlyBowlers);
            intent.putStringArrayListExtra("Batman", OnlyBatsman);
            intent.putStringArrayListExtra("Allrounders", OnlyAlrounder);
            intent.putStringArrayListExtra("Wicketkeeper", OnlyWicketKeeper);

            startActivity(intent);
            finish();
        }
        else if (SelectedBowlers.size() > 0 && SelectedBowlers.size() < 11) {
            android.widget.Toast.makeText(getBaseContext(), "You have selected"+ SelectedBowlers.size() +" Players.. Select " + (11-SelectedBowlers.size())+" Players to complete your team", android.widget.Toast.LENGTH_LONG).show();
        }else
        {
            android.widget.Toast.makeText(getBaseContext(), "Khali hoon bhai kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }

        if (TeamName.equals("") || TeamName == null)
        {
            android.widget.Toast.makeText(getBaseContext(), "Please Enter a Team Name (At the Top!)", android.widget.Toast.LENGTH_LONG).show();
        }
    }
    public void SelectTeam(View view)
    {
        Intent intent = new Intent(this,WelcomeMenu.class);
        startActivity(intent);
        finish();
    }


}

package com.example.emad.splashscreen;

/**
 * Created by عماد شیخ on 11/17/2016.
 */
public class Player {
    public String type;
    public Double cost;
    public String name;


    void setName(String Name_){
        name=Name_;

    }



    void setcost(String name_,Double[]batAVG,Double[]bowlAVG){
        Double bat_avg=0.0;
        Double bowl_avg=0.0;
        for(int i=0;i<3;i++){
            bat_avg=bat_avg+batAVG[i];
            //System.out.println("bat_avg--- "+bat_avg);
            bowl_avg=bowl_avg+bowlAVG[i];
            //System.out.println("bowl_avg--- "+bowl_avg);
        }
        //TAKING AVERAGES
        bat_avg=bat_avg/3;
        bowl_avg=bowl_avg/3;

        //System.out.println("Final Batting average : "+bat_avg+"\n Final bowling average  :  "+bowl_avg);
        //SETTING COST

        if(bowl_avg>0 &&bat_avg>=50.0 && bowl_avg>45 && bowl_avg<15){		//BAtting
            cost=10.0;
            type="batter";
        }
        else if(bowl_avg>0 &&bat_avg<50.0 && bat_avg>=48.0 && bowl_avg>45 ){
            cost=9.0;
            type="batter";}
        else if(bowl_avg>0 &&bat_avg>=44.0 && bat_avg<48.0 && bowl_avg>45 && bowl_avg<15){
            cost=8.0;
            type="batter";
        }
        else if(bowl_avg>0 &&bat_avg>=40.0 && bat_avg<44.0 && bowl_avg>45 && bowl_avg<15){
            cost=7.5;
            type="batter";
        }
        else if(bowl_avg>0 &&bat_avg<44.0 && bat_avg>=35.0 && bowl_avg>45 && bowl_avg<15){
            cost=7.0;
            type="batter";}
        else if(bowl_avg>0 &&bat_avg<40.0 && bat_avg>=28.0 && bowl_avg<40 ){
            cost=8.0;
            type="alrounder";
        }
        else if(bowl_avg>0 &&bat_avg<40.0 && bat_avg>=35.0 && bowl_avg<40){
            cost=7.0;
            type="alrounder";
        }
        else if (bowl_avg>0 &&bat_avg>25 && bat_avg<31 && bowl_avg<15 )
        {
            cost=6.0;
            type="batter";
        }
        else {
            cost=6.5;
            type="alrounder";
        }
//-----------------------------------
        if(bowl_avg>=40.0 && bat_avg<13.0){		//BOWLING
            cost=6.0;
        }
        else if(bowl_avg>0 &&bowl_avg<30.0 && bowl_avg>=28.0 && bat_avg<20.0 ){
            cost=7.0;
            type="bowler";}
        else if(bowl_avg>0 &&bowl_avg>=26.0 && bowl_avg<28.0 && bat_avg<25.0 ){
            cost=8.0;
            type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<32.0 && bowl_avg>26.0 && bat_avg<20.0 && bat_avg>15 ){
            cost=7.5;
            type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<33.0 && bowl_avg>27.0 && bat_avg<15.0 ){
            cost=7.5;
            type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<30 && bat_avg>15.0 ){
            cost=8.5;
            type="bowler";
        }
        else if(bowl_avg>0 && bowl_avg>25.0 && bowl_avg<=27.0 && bat_avg<35.0 && bat_avg>20){
            cost=9.0;
            type="alrounder";}
        else if(bowl_avg>0 &&bowl_avg<25.0 && bowl_avg>=24.0 && bat_avg<35.0 && bat_avg>20){
            cost=10.0;
            type="alrounder";}
        else if(bowl_avg>0 &&bowl_avg>=30.0 && bowl_avg<55.0 && bat_avg>28.0 && bat_avg<35 ){
            cost=8.0;
            type="alrounder";
        }
    }



    String getType()
    {
        return type;
    }


    public void Display_Player(){
        System.out.println("Player Name:  "+name);
        System.out.println("Player Cost:  "+cost);
        System.out.println("Player Type:  "+type);
        System.out.println("\n ------  ");



    }


    //cost=cost_;

    double printCost()
    {
        return cost;
    }
    String printType()
    {
        return type;
    }
}

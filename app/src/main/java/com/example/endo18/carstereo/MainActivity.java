package com.example.endo18.carstereo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    // instance variables
    private ToggleButton changePowerButton;
    private TextView StationDisplay;
    private Switch AM_FM;
    private TextView CD;
    private Button Preset1;
    private Button Preset2;
    private Button Preset3;
    private Button Preset4;
    private Button Preset5;
    private Button Pause;
    private Button Stop;
    private Button Eject;
    private Button Forward;
    private Button Back;
    private double FM_Value;
    private int AM_Value;
    private boolean AM_FM_Tracker;
    private SeekBar Tuner;
    private int[] AM_Presets = {550,600,650,700,750};
    private double[] FM_Presets = {90.9,92.9,94.9,96.9,98.9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fetch power button
        changePowerButton = (ToggleButton)findViewById(R.id.PowerButton);
        StationDisplay = (TextView) findViewById(R.id.StationDisplay);
        CD = (TextView) findViewById(R.id.CD);
        AM_FM = (Switch) findViewById(R.id.AM_FM);
        Preset1 = (Button) findViewById(R.id.Preset1);
        Preset1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!AM_FM_Tracker) {
                    FM_Presets[0] = FM_Value;
                }
                else{
                    AM_Presets[0] = AM_Value;
                }
                return false;
            }
        });
        Preset2 = (Button) findViewById(R.id.Preset2);
        Preset2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!AM_FM_Tracker) {
                    FM_Presets[1] = FM_Value;
                }
                else{
                    AM_Presets[1] = AM_Value;
                }
                return false;
            }
        });
        Preset3 = (Button) findViewById(R.id.Preset3);
        Preset3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!AM_FM_Tracker) {
                    FM_Presets[2] = FM_Value;
                }
                else{
                    AM_Presets[2] = AM_Value;
                }
                return false;
            }
        });
        Preset4 = (Button) findViewById(R.id.Preset4);
        Preset4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!AM_FM_Tracker) {
                    FM_Presets[3] = FM_Value;
                }
                else{
                    AM_Presets[3] = AM_Value;
                }
                return false;
            }
        });
        Preset5 = (Button) findViewById(R.id.Preset5);
        Preset5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!AM_FM_Tracker) {
                    FM_Presets[4] = FM_Value;
                }
                else{
                    AM_Presets[4] = AM_Value;
                }
                return false;
            }
        });
        Pause = (Button) findViewById(R.id.Pause);
        Stop = (Button) findViewById(R.id.Stop);
        Eject = (Button) findViewById(R.id.Eject);
        Forward = (Button) findViewById(R.id.Forward);
        Back = (Button) findViewById(R.id.Back);
        FM_Value = 88.1;
        AM_Value = 530;
        AM_FM_Tracker = false;

        // fetch Tuner seekbar
        Tuner = (SeekBar)findViewById(R.id.Tuner);
        Tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_tracker;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!AM_FM_Tracker){
                   /* if(progress > progress_tracker) {
                        FM_Value = (FM_Value + .2);
                        if(FM_Value > 107.9) {
                            FM_Value = 88.1;
                        }
                    } else {
                        FM_Value = FM_Value - 0.2;
                        if(FM_Value < 88.1) {
                            FM_Value = 107.9;
                        }
                    }
                    progress_tracker = progress; */

                    FM_Value = (107.9 - 88.1)*(progress/100.0)+88.1;
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    String s = "" + df.format(FM_Value)+" FM";
                    StationDisplay.setText(s);
                }
                else {
                    AM_Value = (int)((1700 - 530)*(progress/100.0)+530);
                    String s = "" + AM_Value+ " AM";
                    StationDisplay.setText(s);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void changePowerButton(View v) {
        if(changePowerButton.getText()== changePowerButton.getTextOff()) {
            StationDisplay.setBackgroundColor(Color.BLACK);
            StationDisplay.setTextColor(Color.BLACK);
            CD.setTextColor(Color.rgb(51,51,51));
            AM_FM.setEnabled(false);
            Preset1.setEnabled(false);
            Preset2.setEnabled(false);
            Preset3.setEnabled(false);
            Preset4.setEnabled(false);
            Preset5.setEnabled(false);
            Pause.setEnabled(false);
            Stop.setEnabled(false);
            Eject.setEnabled(false);
            Forward.setEnabled(false);
            Back.setEnabled(false);
        }
        else {
            StationDisplay.setBackgroundColor(Color.rgb(170,170,170));
            StationDisplay.setTextColor(Color.rgb(255,160,0));
            CD.setTextColor(Color.WHITE);
            AM_FM.setEnabled(true);
            Preset1.setEnabled(true);
            Preset2.setEnabled(true);
            Preset3.setEnabled(true);
            Preset4.setEnabled(true);
            Preset5.setEnabled(true);
            Pause.setEnabled(true);
            Stop.setEnabled(true);
            Eject.setEnabled(true);
            Forward.setEnabled(true);
            Back.setEnabled(true);
        }
    }
    public void AM_FM_Pressed(View v) {
        if(AM_FM_Tracker){
            AM_FM_Tracker = false;
        }
        else {
            AM_FM_Tracker = true;
        }
        if(!AM_FM_Tracker) { // getTextOff: FM
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Value)+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Value+ " AM");
        }
    }
    public void Preset1(View v){
        if(!AM_FM_Tracker){
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Presets[0])+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Presets[0] + " AM");
        }
    }
    public void Preset2(View v){
        if(!AM_FM_Tracker){
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Presets[1])+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Presets[1] + " AM");
        }
    }
    public void Preset3(View v){
        if(!AM_FM_Tracker){
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Presets[2])+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Presets[2] + " AM");
        }
    }
    public void Preset4(View v){
        if(!AM_FM_Tracker){
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Presets[3])+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Presets[3] + " AM");
        }
    }
    public void Preset5(View v){
        if(!AM_FM_Tracker){
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String s = "" + df.format(FM_Presets[4])+" FM";
            StationDisplay.setText(s);
        }
        else {
            StationDisplay.setText("" + AM_Presets[4] + " AM");
        }
    }
}

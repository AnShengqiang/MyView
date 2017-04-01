package com.anshengqiang.android.myview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener ,View.OnClickListener{

    private TextView mPlanet1SpeedTextView;
    private TextView mEarthSpeedTextView;
    private TextView mPlanet2SpeedTextView;

    private SeekBar mPlanet1SpeedSeekBar;
    private SeekBar mEarthSpeedSeekBar;
    private SeekBar mPlanet2SpeedSeekBar;

    private ToggleButton mLineToggleButton;
    private ToggleButton mLongLineToggleButton;
    
    private GalaxyView mGalaxyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        mGalaxyView = (GalaxyView) findViewById(R.id.galaxyView);

        mPlanet1SpeedTextView = (TextView) findViewById(R.id.planet_1_speed_text_view);
        mEarthSpeedTextView = (TextView) findViewById(R.id.earth_speed_text_view);
        mPlanet2SpeedTextView = (TextView) findViewById(R.id.planet_2_speed_text_view);

        mPlanet1SpeedSeekBar = (SeekBar) findViewById(R.id.planet_1_speed_seek_bar);
        mPlanet1SpeedSeekBar.setOnSeekBarChangeListener(this);
        
        mEarthSpeedSeekBar = (SeekBar) findViewById(R.id.earth_speed_seek_bar);
        mEarthSpeedSeekBar.setOnSeekBarChangeListener(this);
        mPlanet2SpeedSeekBar = (SeekBar) findViewById(R.id.planet_2_speed_seek_bar);
        mPlanet2SpeedSeekBar.setOnSeekBarChangeListener(this);

        mLineToggleButton = (ToggleButton) findViewById(R.id.planet_1_earth_line_toggle_button);
        mLineToggleButton.setOnClickListener(this);
        mLongLineToggleButton = (ToggleButton) findViewById(R.id.planet_2_earth_line_toggle_button);
        mLongLineToggleButton.setOnClickListener(this);


    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.planet_1_speed_seek_bar:
                mPlanet1SpeedTextView.setText(String.valueOf(progress));
                mGalaxyView.getPlanetTest_1().setSpeed(progress);
                break;
            case R.id.earth_speed_seek_bar:
                mEarthSpeedTextView.setText(String.valueOf(progress));
                mGalaxyView.getEarth().setSpeed(progress);
                break;
            case R.id.planet_2_speed_seek_bar:
                mPlanet2SpeedTextView.setText(String.valueOf(progress));
                mGalaxyView.getPlanetTest_2().setSpeed(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.planet_1_earth_line_toggle_button:
                if (mLineToggleButton.isChecked()){
                    mGalaxyView.setShouldDrawLine(true);
                }else {
                    mGalaxyView.setShouldDrawLine(false);
                }
                break;
            case R.id.planet_2_earth_line_toggle_button:
                if (mLongLineToggleButton.isChecked()){
                    mGalaxyView.setShouldDrawLongLine(true);
                }else {
                    mGalaxyView.setShouldDrawLongLine(false);
                }
        }
    }
}

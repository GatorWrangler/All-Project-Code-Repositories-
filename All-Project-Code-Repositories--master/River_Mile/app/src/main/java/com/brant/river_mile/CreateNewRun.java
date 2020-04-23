package com.brant.river_mile;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by brant on 3/19/20.
 */


public class CreateNewRun extends AppCompatActivity {
    RunsDatabaseHelper rDBHelper;//run database builder and driver Brant 3/19
    private Button submitNewRun;
    private EditText TOCoordCreate, PICoordCreate, runNameCreate;
    private RadioGroup classOfNewRun;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_run);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submitNewRun= (Button) findViewById(R.id.submitNewRun);// configure buttons Brant 3/19
        TOCoordCreate=(EditText) findViewById(R.id.TOCoordCreate);
        PICoordCreate=(EditText) findViewById(R.id.PICoordCreate);
        runNameCreate=(EditText) findViewById(R.id.runNameCreate);
        classOfNewRun=(RadioGroup) findViewById(R.id.classOfNewRun);
        rDBHelper= new RunsDatabaseHelper(this);
        //Set button to listen from submission

        submitNewRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView)
            {
                String entryName=runNameCreate.getText().toString();
                String entryPICord=PICoordCreate.getText().toString();
                String entryTOCord=TOCoordCreate.getText().toString();
                String entryDiff="III";// hardcoded until radio buttons work Brant 3.19
                //String entryDiff=classOfNewRun.getCheckedRadioButtonId().text;
                //String runDifficultySelectedRadio="";// var to store difficulty
                /*
                boolean selectedRadioButton= ((RadioButton) currentView).isChecked();
                switch (currentView.getId())
                {
                    case R.id.class1Button:
                        if (selectedRadioButton)
                        {
                            runDifficultySelectedRadio="I";
                        }
                        break;
                    case R.id.class2Button:
                        if (selectedRadioButton)
                        {
                            runDifficultySelectedRadio="II";
                        }
                        break;
                    case R.id.class3Button:
                        if (selectedRadioButton)
                        {
                            runDifficultySelectedRadio="III";
                        }
                        break;
                    case R.id.class4Button:
                        if (selectedRadioButton)
                        {
                            runDifficultySelectedRadio="IV";
                        }
                        break;
                    case R.id.class5Button:
                        if (selectedRadioButton)
                        {
                            runDifficultySelectedRadio="V";
                        }
                        break;
            }
            */
                if(entryName.length()!=0 && entryTOCord.length()!=0&& entryPICord.length()!=0)
                {
                    RAddData(entryName,entryPICord,entryTOCord, entryDiff);
                }
                else
                    Log.d("entry error", "invalid entry");
            }});


    }

    public void RAddData(String runName, String PICord, String TOCord, String runDiff )//Brant 3/19
    {
        boolean insertData=rDBHelper.AddData(runName, PICord, TOCord, runDiff);
        if(insertData)//debugging Brant 3/19
        {
            Log.d("RUN DATA ADDED", "YUP IT WORKED");
        }
        else
        {
            Log.d("RUN DATA NOT ADDED", "IT DIDNT WORKED");
        }

    }
}



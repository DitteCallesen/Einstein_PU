package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrophyActivity extends AppCompatActivity {

    List<Integer> activeTrophies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);


        //Bare foreløpig, sånn at vi kan se troféene.
        activeTrophies.add(1);
        activeTrophies.add(2);
        activeTrophies.add(3);
        activeTrophies.add(4);
        activeTrophies.add(5);
        activeTrophies.add(6);
        activeTrophies.add(7);
        activeTrophies.add(8);
        activeTrophies.add(9);
        activeTrophies.add(10);
        activeTrophies.add(11);
        activeTrophies.add(12);

        showTrophies();

    }

    public void trophyClicked(View view) {
        LinearLayout big_trophy = (LinearLayout) findViewById(R.id.big_trophy);
        big_trophy.setVisibility(View.VISIBLE);
        TextView trophy_text = (TextView) findViewById(R.id.trophy_text);

        switch(view.getId()) {
            case R.id.trophy1:
                trophy_text.setText("Gjort din første oppgave.");
            break;
            case R.id.trophy2:
                trophy_text.setText("Fått riktig på 5 oppgaver på rad.");
            break;
            case R.id.trophy3:
                trophy_text.setText("Løst 10 oppgaver på én dag.");
                break;
            case R.id.trophy4:
                trophy_text.setText("Fått riktig på 10 oppgaver på rad.");
                break;
            case R.id.trophy5:
                trophy_text.setText("Løst 10 oppgaver.");
                break;
            case R.id.trophy6:
                trophy_text.setText("Løst 10 oppgaver på én time.");
                break;
            case R.id.trophy7:
                trophy_text.setText("Løst 10 oppgaver på en dag.");
                break;
            case R.id.trophy8:
                trophy_text.setText("Løst 30 oppgaver på én dag.");
                break;
            case R.id.trophy9:
                trophy_text.setText("Løst 50 oppgaver.");
                break;
            case R.id.trophy10:
                trophy_text.setText("Fått riktig på 30 oppgaver på rad.");
                break;
            case R.id.trophy11:
                trophy_text.setText("Løst 100 oppgaver.");
                break;
            case R.id.trophy12:
                trophy_text.setText("Løst 500 oppgaver.");
                break;

            default:
                //
        }

    }

    public void bigTrophyClicked(View view) {
        LinearLayout big_trophy = (LinearLayout) findViewById(R.id.big_trophy);
        big_trophy.setVisibility(View.INVISIBLE);
    }

    public void addNewTrophy(Integer trophyNumber) {
        //
    }

    public void showTrophies() {
        if (activeTrophies.contains(1)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy1);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(2)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy2);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(3)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy3);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(4)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy4);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(5)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy5);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(6)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy6);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(7)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy7);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(8)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy8);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(9)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy9);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(10)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy10);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(11)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy11);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(12)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy12);
            imageButton.setVisibility(View.VISIBLE);
        }
    }
}



    /*
    public void backTOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void method() {
        //
    }


}
*/
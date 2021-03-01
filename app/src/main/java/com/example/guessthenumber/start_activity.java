package com.example.guessthenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.*;

import java.util.*;

public class start_activity extends AppCompatActivity {
    TextView scoreText, healthText, hintText, guessText;
    EditText submitNumber;
    Button send_button;
    Random random = new Random();
    int health;
    int random_range;
    int score, guessTheNumber;
    String comparableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);

        send_button = findViewById(R.id.send);
        scoreText = findViewById(R.id.guess_score);
        healthText = findViewById(R.id.health_text);
        hintText = findViewById(R.id.guess_hint);
        guessText = findViewById(R.id.guess_text);
        submitNumber = findViewById(R.id.send_number);

        resetAll();
        send_button.setOnClickListener(v -> {
            send_button.setEnabled(false);
            result();
        });
    }

    public void result() {
        comparableString = String.valueOf(guessTheNumber);
        try {
            if (comparableString.equals(getSubmitNumber())) {
                winUpdate();
                System.out.println("NICE");
            } else {
                lossUpdate();
                loseHealth();
                System.out.println("Wrong");
            }
        } catch (Exception E) {
            comparableString = "?";
        }
    }



    public void setRandomNumber() {
        guessTheNumber = random.nextInt(random_range) + 1;
    }

    // start var
    public void resetAll() {
        health = 5;
        score = 0;
        random_range = 2;
        guessText.setText("");
        setRandomNumber();
        setAllText();
    }

    // update win
    public void winUpdate() {
        soundStart("plus");
        setGuessText(getSubmitNumber() + " is correct");
        score += 10;
        health += 1;
        random_range += 1;
        setRandomNumber();
        setAllText();
        emptyGuestText();

    }
    // update loss
    public void lossUpdate() {
        soundStart("wrong");
        health -= 1;
        setGuessText(getSubmitNumber() + " is wrong");
        emptyGuestText();
        setAllText();

    }

    public void setAllText() {
        scoreText.setText(String.valueOf(score));
        healthText.setText(String.valueOf(health));
        hintText.setText(String.valueOf(random_range));
    }

    public void setGuessText(String text) {
        guessText.setText(text);
    }

    public String getSubmitNumber() {
        return String.valueOf(submitNumber.getText());
    }

    public void loseHealth() {
        if (health == 0) {
            soundStart("restart");
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setMessage("your score : " + score);
            alert.setPositiveButton("retry", (dialog, which) -> {
                Toast.makeText(start_activity.this, "nice", Toast.LENGTH_LONG).show();
                finish();
            });
            alert.show();
        }
    }

    // delay button and text
    public void emptyGuestText() {
        new Thread(() -> {
            SystemClock.sleep(2000);
            runOnUiThread(() -> {
                send_button.setEnabled(true);
            });
        }).start();
    }

    private void soundStart(String file) {
        MediaPlayer mp = new MediaPlayer();
        if (file.equals("plus")) {
            mp = MediaPlayer.create(this, R.raw.win_sound);
        } else if (file.equals("wrong")) {
            mp = MediaPlayer.create(this, R.raw.quit_sound);
        } else if (file.equals("restart")) {
            mp = MediaPlayer.create(this, R.raw.game_over);
        } else {
            mp = MediaPlayer.create(this, R.raw.clicksounds);
        }
        mp.start();
    }

}
package com.example.kiera.encryptor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class CaesarDecrypt extends AppCompatActivity {
int steps = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesardecrypt);
        init();
    }

    public void init(){
        stepGetter();
    }

    public void stepGetter(){
        SeekBar seek = findViewById(R.id.stepChanger);
        final TextView seekBarValue = findViewById(R.id.stepNumber);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seek, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                steps = progress;
                seekBarValue.setText(String.valueOf("Step Number: " + progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seek) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStopTrackingTouch(SeekBar seek) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void decryptText(View view){
            Intent i = new Intent(this, Decrypted.class);

            // get the text box
            EditText inputText = findViewById(R.id.inputText);
            //Gets the text which is in the box
            String txtData = inputText.getText().toString();
            //Gets the length of the text in the edit text box
            int txtValue = inputText.getText().toString().length();

            //Ensures that there is text entered before button can be pressed.
            if (txtValue == 0){
                // Display message to user telling that they need to add text to the box.
                Toast.makeText(this, "You did not enter any text", Toast.LENGTH_SHORT).show();
                return;
            }

            //Assign the new text the encrypted to a new variable
            String decrypt = decrypted(txtData, steps);

            //add the new intent with the
            i.putExtra("txtData", decrypt);
            startActivity(i);
    }


    public void pasteText(View view){
        EditText inputText = findViewById(R.id.inputText);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        ClipData data = clipboard.getPrimaryClip();
        ClipData.Item item = data.getItemAt(0);

        String decryptText = item.getText().toString();
        inputText.setText(decryptText);
    }

    static String decrypted(String txtData, int steps) {

        txtData = txtData.trim();

        char[] decrypted = txtData.toCharArray();
        for (int i = 0; i < decrypted.length; i++) {

            char letter = decrypted[i];

            if (Character.isLetter(letter)) {
                letter = (char)(letter - steps);

                if (Character.isUpperCase(letter)) {
                    if (letter > 'Z') {
                        letter = (char) (letter - 26);
                    }
                } else {
                    if (letter > 'z') {
                        letter = (char) (letter - 26);
                    }
                }
            } else if (Character.isDigit(letter)) {
                letter = (char) (letter + steps);
                if (letter > '0') {
                    letter = (char) (letter - 10);
                }
            } else {
                letter = decrypted[i];
            }
            decrypted[i] = letter;
        }
        return new String(decrypted);
    }
}

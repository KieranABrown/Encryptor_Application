package com.example.kiera.encryptor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Decrypted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypted);

        Intent i = getIntent();

        //The second parameter below is the default string returned if the value is not there.
        String txtData = i.getExtras().getString("txtData","");
        TextView decryptedText = findViewById(R.id.decryptedText);
        decryptedText.setText(txtData);
    }
}

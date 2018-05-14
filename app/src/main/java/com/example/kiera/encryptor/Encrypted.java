package com.example.kiera.encryptor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Encrypted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted);

        Intent i = getIntent();

        //The second parameter below is the default string returned if the value is not there.
        String txtData = i.getExtras().getString("txtData","");
        TextView encryptedText = findViewById(R.id.encryptedText);
        encryptedText.setText(txtData);
    }

    public void CopyText(View view){
        TextView textView = findViewById(R.id.encryptedText);

        String text;
        String clear = "";
        text = textView.getText().toString();

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);

        textView.setText(clear);

        Toast.makeText(getApplicationContext(), "Text Copied",
                Toast.LENGTH_SHORT).show();
    }
}

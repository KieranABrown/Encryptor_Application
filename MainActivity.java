package com.example.kiera.encryptor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CaesarEncrypt(View view) {
        Intent i = new Intent(this, CaesarEncrypt.class);
        startActivity(i);
    }

    public void CaesarDecrypt(View view) {
        Intent i = new Intent(this, CaesarDecrypt.class);
        startActivity(i);
    }

    public void DESEncrypt(View view) {
        Intent i = new Intent(this, dataEncryptionCipher.class);
        startActivity(i);
    }

    public void DESDecrypt(View view) {
        Intent i = new Intent(this, dESDecrypt.class);
        startActivity(i);
    }

    public void AESEncrypt(View view) {
        Intent i = new Intent(this, AESEncryption.class);
        startActivity(i);
    }
}


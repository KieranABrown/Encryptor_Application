package com.example.kiera.encryptor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class dataEncryptionCipher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_encryption_cipher);
    }

    public void MoveEncrypt(View view){
        Intent i = new Intent(this, Encrypted.class);

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
        String encrypted = DES(txtData);

        //add the new intent with the
        i.putExtra("txtData", encrypted);
        startActivity(i);
    }


    public String DES(String txtData) {
       String newTxtData = "";
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher;

            // Create the cipher
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

            //sensitive information
            byte[] text = txtData.getBytes();
            // Encrypt the text
            newTxtData = Base64.encodeToString(desCipher.doFinal(text),Base64.DEFAULT);

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(NoSuchPaddingException e){
            e.printStackTrace();
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }catch(IllegalBlockSizeException e){
            e.printStackTrace();
        }catch(BadPaddingException e){
            e.printStackTrace();
        }

        return newTxtData;
    }
}


package com.example.kiera.encryptor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class dESDecrypt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_esdecrypt);
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
        String decrypt = DESDECRYPT(txtData);

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

    public String DESDECRYPT(String txtData) {
        String newTxtData = "";
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher;

            // Create the cipher
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

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

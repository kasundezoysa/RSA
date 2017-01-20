package org.scorelab.rsa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView input = (TextView) findViewById(R.id.input);
        final TextView output = (TextView) findViewById(R.id.output);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //RSA Signature creation
        final Button button1 = (Button) findViewById(R.id.sign);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1 = input.getText().toString();
                try {
                    RSASignature rsa = new RSASignature(1024);
                    String base64 = Base64.encodeToString(rsa.sign(s1), Base64.DEFAULT);
                    output.setText(base64);
                } catch (Exception e) {
                    output.setText(e.toString());
                }
            }
        });

        //RSA Signature verification
        final Button button2 = (Button) findViewById(R.id.verify);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= input.getText().toString();
                String s2= output.getText().toString();
                try{
                    RSASignature rsa= new RSASignature(1024);
                    if (rsa.verify(s1,Base64.decode(s2,Base64.DEFAULT)))
                        output.setText("Signature Verified");
                    else output.setText("Signature Verification Failed");
                }catch(Exception e){
                    output.setText(e.toString());
                }
            }});

        //RSA Encryption
        final Button button3 = (Button) findViewById(R.id.encrypt);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1= input.getText().toString();
                try{
                    RSAEncryption rsa= new RSAEncryption(1024);
                    String base64 = Base64.encodeToString(rsa.encrypt(s1),Base64.DEFAULT);
                    output.setText(base64);
                }catch(Exception e) {
                    output.setText(e.toString());
                }

                }
            });

        //RSA Decryption
        final Button button4 = (Button) findViewById(R.id.decrypt);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s2= output.getText().toString();
                try{
                    RSAEncryption rsa= new RSAEncryption(1024);
                    byte[] p=rsa.decrypt(Base64.decode(s2,Base64.DEFAULT));
                    String s=new String(p);
                    output.setText(s);
                }catch(Exception e){
                    output.setText(e.toString());
                }
            }});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.bhavesh.edutechload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        final EditText to = (EditText) findViewById(R.id.sendTO);
        final EditText subject = (EditText)findViewById(R.id.subject);
        final EditText message = (EditText)findViewById(R.id.EmailText);


        Button sendEmail = (Button)findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toS = to.getText().toString();
                String subS = subject.getText().toString();
                String mesS = message.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                //email.putExtra(Intent.EXTRA_EMAIL,toS);
                email.putExtra(Intent.EXTRA_EMAIL,new String[] {toS});
                email.putExtra(Intent.EXTRA_SUBJECT,subS);
                email.putExtra(Intent.EXTRA_TEXT,mesS);


                email.setType("message/rfc822");          //get this in description
                startActivity(Intent.createChooser(email,"Chooser App To Send Mail"));

            }
        });

    }
}



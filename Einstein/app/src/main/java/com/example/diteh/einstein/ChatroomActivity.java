package com.example.diteh.einstein;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import com.facebook.applinks.AppLinkData;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatroomActivity extends AppCompatActivity {
    private static int SIGN_IN_REQUEST_CODE=1;
    private FirebaseListAdapter<Chatmessage> adapter;
    RelativeLayout activity_chatroom;
    FloatingActionButton fab;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_sign_out){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_chatroom,"Signed out ",Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Snackbar.make(activity_chatroom,"Successfully signin in. Welcome! ",Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }
            else{
                Snackbar.make(activity_chatroom,"failed ",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        activity_chatroom=(RelativeLayout)findViewById(R.id.activity_chatroom);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input=(EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new Chatmessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });
        //check if not signinpage
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);

        }
        else{
            Snackbar.make(activity_chatroom,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //load content
            displayChatMessage();
        }



    }

    private void displayChatMessage() {

        ListView listOfMessage= (ListView)findViewById(R.id.listofmessage);
        adapter = new FirebaseListAdapter<Chatmessage>(this, Chatmessage.class, R.layout.listitem,FirebaseDatabase.getInstance().getReference()){
            @Override
            protected void populateView(View v, Chatmessage model, int position) {
                TextView messageText,messageuser, messageTime;
                messageText = (TextView)v.findViewById(R.id.messageText);
                messageuser=(TextView)v.findViewById((R.id.messageuser));
                messageTime=(TextView)v.findViewById((R.id.messageTime));

                messageText.setText(model.getMessageText());
                messageuser.setText(model.getMessageUser());
                messageTime.setText(android.text.format.DateFormat.format("dd-mm-yyyy (hh-mm-ss)",model.getMessageTIme()));
            }
        };

        listOfMessage.setAdapter(adapter);
    }

    public void backOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }



}

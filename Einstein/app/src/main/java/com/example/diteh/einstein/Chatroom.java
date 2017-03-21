package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chatroom extends AppCompatActivity {
    private Button btSendMsg;
    private EditText inputMsg;
    private TextView chatConversation;
    private String username, roomName, name, tempKey;
    private DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        btSendMsg = (Button)findViewById(R.id.btSend);
        inputMsg = (EditText)findViewById(R.id.msgInput);
        chatConversation=(TextView)findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        username=extras.getString("username");
        roomName = extras.getString("roomName");

        setTitle("Room " + roomName);

        root= FirebaseDatabase.getInstance().getReference().child(roomName);

        btSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String,Object>();
                tempKey=root.push().getKey();
                root.updateChildren(map);

                DatabaseReference messageRoot = root.child(tempKey);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name",name);
                map2.put("msg", inputMsg.getText().toString());

                messageRoot.updateChildren(map2);

            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private String chatMsg, chatName;
    private void appendChatConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while(i.hasNext()){
                chatMsg=(String)((DataSnapshot)i.next()).getValue();
                chatName=(String)((DataSnapshot)i.next()).getValue();
            chatConversation.append((chatName+ " : " +chatMsg + "\n"));
        }
    }


    public void backToRooms(View view) {
        Intent intent = new Intent(Chatroom.this, ListOfChatroomActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        intent.putExtras(extras);
        Chatroom.this.startActivity(intent);
        finish();
    }


}

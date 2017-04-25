package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Chatroom extends AppCompatActivity {
    private Button btSendMsg;
    private EditText inputMsg;
    private String username, roomName, name, tempKey, position;
    private DatabaseReference root;
    private SimpleDateFormat DateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    private ListView chatlist;
    private ChatroomAdapter adapter;
    private List<Chatmessage> mChatmessage;
    private String chatMsg, chatName, chatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        btSendMsg = (Button) findViewById(R.id.btSend);
        inputMsg = (EditText) findViewById(R.id.msgInput);
        mChatmessage = new ArrayList<>();

        chatlist = (ListView) findViewById(R.id.messageList);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        roomName = extras.getString("roomName");
        position = extras.getString("position");
        setTitle("Room " + roomName);

        root = FirebaseDatabase.getInstance().getReference().child(roomName);

        btSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                tempKey = root.push().getKey();
                root.updateChildren(map);
                Calendar calendar = Calendar.getInstance();
                String date = DateFormat.format(calendar.getTime());
                DatabaseReference messageRoot = root.child(tempKey);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", name);
                map2.put("msg", inputMsg.getText().toString());
                map2.put("stamp", date);
                messageRoot.updateChildren(map2);
                inputMsg.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
                adapter = new ChatroomAdapter(getApplicationContext(), mChatmessage);
                chatlist.setAdapter(adapter);
                chatlist.setSelection(mChatmessage.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
                adapter = new ChatroomAdapter(getApplicationContext(), mChatmessage);
                chatlist.setAdapter(adapter);
                chatlist.setSelection(mChatmessage.size());
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

    private void appendChatConversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();
        int j = 0;
        while (i.hasNext()) {
            chatMsg = (String) ((DataSnapshot) i.next()).getValue();
            chatName = (String) ((DataSnapshot) i.next()).getValue();
            chatTime = (String) ((DataSnapshot) i.next()).getValue();
            mChatmessage.add(new Chatmessage(j, chatMsg, chatName, chatTime));
            j++;
        }


    }


    public void backToRooms(View view) {
        Intent intent;
        intent = new Intent(Chatroom.this, ListOfChatroomActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        Chatroom.this.startActivity(intent);
        this.finish();
    }

    //use Android back button
    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(Chatroom.this, ListOfChatroomActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        this.finish();
    }

    private class ChatroomAdapter extends BaseAdapter {
        private Context mContext;
        private List<Chatmessage> chatmessageList;


        public ChatroomAdapter(Context mContext, List<Chatmessage> chatmessageList) {
            this.mContext = mContext;
            this.chatmessageList = chatmessageList;
        }

        @Override
        public int getCount() {
            return chatmessageList.size();
        }

        @Override
        public Object getItem(int position) {
            return chatmessageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void refill(List<Chatmessage> chatmessageList) {
            mChatmessage.clear();
            mChatmessage.addAll(chatmessageList);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mContext, R.layout.list_item, null);
            TextView Rname = (TextView) v.findViewById(R.id.msgName);
            TextView msg = (TextView) v.findViewById(R.id.msgText);
            TextView stamp = (TextView) v.findViewById(R.id.msgStamp);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) msg.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) Rname.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) stamp.getLayoutParams();
            //set text
            Rname.setText(chatmessageList.get(position).getMessageUser());
            msg.setText(chatmessageList.get(position).getMessageText());
            stamp.setText(chatmessageList.get(position).getMessageTIme().toString());
            if (name.equals(chatmessageList.get(position).getMessageUser())) {
                msg.setBackground(getDrawable(R.drawable.bubble_right_green));
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                msg.setBackground(getDrawable(R.drawable.bubble_left_gray));
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            v.setTag(chatmessageList.get(position).getiD());
            return v;
        }
    }


}

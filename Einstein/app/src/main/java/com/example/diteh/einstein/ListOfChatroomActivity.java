package com.example.diteh.einstein;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
// creates a list of chatrooms for the Einstein, user can make a new chatroom or select an exsisting
// when selected, a new activity will be launched
public class ListOfChatroomActivity extends AppCompatActivity {
    private Button add_room;
    private EditText room_name;
    private String name, username, position1;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfRooms = new ArrayList<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofchatroom);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position1 = extras.getString("position");
        add_room = (Button) findViewById(R.id.btAddRoom);
        room_name = (EditText) findViewById(R.id.roomNameEdittext);
        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfRooms);
        listView.setAdapter(arrayAdapter);

        //when a new room is added
        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(room_name.getText().toString(), "");
                root.updateChildren(map);
            }
        });

        //sets up the room list
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }
                listOfRooms.clear();
                listOfRooms.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //on room selected, new activity is launched
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListOfChatroomActivity.this, Chatroom.class);
                Bundle extras = new Bundle();
                extras.putString("name", name);
                extras.putString("username", username);
                extras.putString("position", position1);
                extras.putString("roomName", ((TextView) view).getText().toString());
                intent.putExtras(extras);
                ListOfChatroomActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    public void backOnClick(View view) {
        Intent intent;
        if (position1.equals("Student")) {
            intent = new Intent(ListOfChatroomActivity.this, MainActivity.class);
        } else {
            intent = new Intent(ListOfChatroomActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position1);
        intent.putExtras(extras);
        ListOfChatroomActivity.this.startActivity(intent);
        finish();
    }

    //use Android back button
    @Override
    public void onBackPressed() {
        Intent intent;
        if (position1.equals("Student")) {
            intent = new Intent(ListOfChatroomActivity.this, MainActivity.class);
        } else {
            intent = new Intent(ListOfChatroomActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position1);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }


}



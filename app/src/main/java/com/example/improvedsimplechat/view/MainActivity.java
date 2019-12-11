package com.example.improvedsimplechat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.improvedsimplechat.R;
import com.example.improvedsimplechat.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences shared;
    SharedPreferences.Editor spEditor;

    Spinner userSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = getSharedPreferences(Constants.SPREF, Context.MODE_PRIVATE);
        spEditor = shared.edit();
        userSelect = findViewById(R.id.user_spinner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> users = new ArrayList<>();
        int userNum = shared.getInt(Constants.NUM_USERS, 0);
        for(int i = 0; i < userNum; i++) {
            users.add(shared.getString(Constants.USERS_LIST + i, "Out of bounds"));
        }
        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, users);
        userAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        userSelect.setAdapter(userAdapter);
    }

    public void onJoin(View v) {
        CharSequence userName = ((TextView)userSelect.getSelectedView()).getText();
        Intent joinChat = new Intent(this, ChatActivity.class);
        joinChat.putExtra("userName", userName);
        startActivity(joinChat);
    }

    public void onAdd(View v) {
        Intent addUser = new Intent(this, AddUserActivity.class);
        startActivity(addUser);
    }
}

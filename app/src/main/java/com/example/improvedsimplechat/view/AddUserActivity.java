package com.example.improvedsimplechat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.improvedsimplechat.R;
import com.example.improvedsimplechat.util.Constants;

public class AddUserActivity extends AppCompatActivity {

    SharedPreferences shared;
    SharedPreferences.Editor spEditor;

    EditText nameEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        nameEnter = findViewById(R.id.editText);

        shared = getSharedPreferences(Constants.SPREF, Context.MODE_PRIVATE);
        spEditor = shared.edit();
    }

    public void onSubmit(View v) {
        String newUser = nameEnter.getText().toString().trim();
        if(!newUser.isEmpty()) {
            int numUsers = shared.getInt(Constants.NUM_USERS, 0);
            spEditor.putString(Constants.USERS_LIST + numUsers, newUser);
            numUsers++;
            spEditor.putInt(Constants.NUM_USERS, numUsers);
            spEditor.apply();
            finish();
        }
    }
}

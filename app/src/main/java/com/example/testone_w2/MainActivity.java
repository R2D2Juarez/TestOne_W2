package com.example.testone_w2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    public static final String SEARCH_VALUE = "" ;
    private DBHelper helper;
    private SQLiteDatabase database;

    EditText searchReccord;
    Button saveScreenBTN;
    Button readAllNotesScreenBTN;
    Button updateScreenBTN;
    Button deleteScreenBTN;
//__________________________________________________________________________________________________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        searchReccord = (EditText) findViewById(R.id.ed_searchRecord);
        saveScreenBTN = (Button) findViewById(R.id.btn_saveScreen);
        readAllNotesScreenBTN = (Button) findViewById(R.id.btn_readAllNotes);
        updateScreenBTN = (Button) findViewById(R.id.btn_updateScreen);
        deleteScreenBTN = (Button) findViewById(R.id.btn_deleteScreen);
    }

//__________________________________________________________________________________________________

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
//__________________________________________________________________________________________________

    public void openSaveScreen(View view) {
        Intent intent = new Intent(MainActivity.this, SaveNoteActivity.class);
        startActivity(intent);
    }
//__________________________________________________________________________________________________

    public void readAllNotesScreen(View view) {
        Intent intent = new Intent(MainActivity.this, ReadAllNotesActivity.class);
        startActivity(intent);
    }
//__________________________________________________________________________________________________

    public void openUpdateScreen(View view) {
        String value = searchReccord.getText().toString();
        Intent intent = new Intent(MainActivity.this, UpdateNoteActivity.class);
        intent.putExtra(SEARCH_VALUE, value);
        startActivity(intent);
    }
//__________________________________________________________________________________________________

    public void openDeleteScreen(View view) {
        Intent intent = new Intent(MainActivity.this, DeleteNoteActivity.class);
        String value = searchReccord.getText().toString();
        intent.putExtra(SEARCH_VALUE, value);
        startActivity(intent);
    }
}

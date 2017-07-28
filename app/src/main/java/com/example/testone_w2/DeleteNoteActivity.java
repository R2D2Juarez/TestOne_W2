package com.example.testone_w2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testone_w2.FeedReaderContract.FeedEntry;


public class DeleteNoteActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;
    private String searchValue = "";

    Button deleteBTN;
    TextView showResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        Intent intent = getIntent();
        searchValue = intent.getStringExtra(MainActivity.SEARCH_VALUE);

        deleteBTN = (Button) findViewById(R.id.btn_deleteNote);
        showResultTV = (TextView) findViewById(R.id.tv_show_resultDeleted);

        Toast.makeText(this, searchValue, Toast.LENGTH_SHORT).show();
    }

    public void deleteNote(View view) {

        String selection = FeedEntry._ID + " LIKE ? ";
        String[] selectionArgs = {
                //"Record title"
                searchValue
        };
        int deleted = database.delete(
                FeedEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        if (deleted > 0){
            showResultTV.setText("RECORD DELETED");
        }else {
            showResultTV.setText("RECORD NOT DELETED");

        }
    }
}

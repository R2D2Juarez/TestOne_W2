package com.example.testone_w2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testone_w2.FeedReaderContract.FeedEntry;

public class SaveNoteActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;

    EditText newTitleET;
    EditText newContentET;
    Button saveRecordBTN;
    TextView showResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        newTitleET = (EditText) findViewById(R.id.et_newNoteTitle);
        newContentET = (EditText) findViewById(R.id.et_newNoteContent);
        saveRecordBTN = (Button) findViewById(R.id.btn_saveNote);
        showResultTV = (TextView) findViewById(R.id.tv_show_resultSaved);
    }

    public void SaveNote(View view) {
        String Title = newTitleET.getText().toString();
        String NoteContent = newContentET.getText().toString();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, Title);
        values.put(FeedEntry.COLUMN_NAME_CONTENT, NoteContent);

        long recordId = database.insert(
                FeedEntry.TABLE_NAME,
                null,
                values);
        if (recordId > 0){
            Log.d(TAG, "saveRecord: Record saved");
            showResultTV.setText("RECORD SAVED");
        }else {
            Log.d(TAG, "saveRecord: Record not saved");
            showResultTV.setText("RECORD NOT SAVED");

        }
        newTitleET.setText("");
        newContentET.setText("");
    }
}

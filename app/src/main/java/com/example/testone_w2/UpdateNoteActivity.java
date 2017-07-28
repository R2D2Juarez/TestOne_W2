package com.example.testone_w2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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


public class UpdateNoteActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;
    private String SearchValue = "";

    EditText editTitleET;
    EditText editContentET;
    Button updateBTN;
    TextView showResultUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        Intent intent = getIntent();
        SearchValue = intent.getStringExtra(MainActivity.SEARCH_VALUE);

        editTitleET = (EditText) findViewById(R.id.et_updateNoteTitle);
        editContentET = (EditText) findViewById(R.id.et_updateNoteContent);
        updateBTN = (Button) findViewById(R.id.btn_updateNote);
        showResultUpdate = (TextView) findViewById(R.id.tv_show_resultUpdate);

        searchRecord();
    }


    public void searchRecord() {

        Cursor cursor = null;
        cursor = this.database.rawQuery(
                "select * from " + FeedEntry.TABLE_NAME +
                        " where " + FeedEntry._ID + "=" + SearchValue  , null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                String entryId = String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID)));
                String entryTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
                String entrySubtitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_CONTENT));

                editTitleET.setText(entryTitle);
                editContentET.setText(entrySubtitle);
            }
        }
    }

    public void updateNote(View view) {
        ContentValues values = new ContentValues();

        String newTitle = editTitleET.getText().toString();
        String newContent = editContentET.getText().toString();
        String recordToSearch = SearchValue;

        values.put(FeedEntry.COLUMN_NAME_TITLE, newTitle);
        values.put(FeedEntry.COLUMN_NAME_CONTENT, newContent);

        String selection = FeedEntry._ID  + " LIKE ? ";
        String[] selectionArgs = {
                //"Record title"
                recordToSearch
        };

        int count = database.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        if (count > 0){
            showResultUpdate.setText("RECORD UPDATED");
            Log.d(TAG, "updateRecord: Update record" + count);
        }else {
            showResultUpdate.setText("RECORD NOT UPDATED");
            Log.d(TAG, "updateRecord: Record not updated");
        }
    }
}

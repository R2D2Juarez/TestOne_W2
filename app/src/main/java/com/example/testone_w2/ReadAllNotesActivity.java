package com.example.testone_w2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.testone_w2.FeedReaderContract.FeedEntry;


public class ReadAllNotesActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;



    Button readAllNotesBTN;
    TextView resultAllNotesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_notes);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        readAllNotesBTN =  (Button) findViewById(R.id.btn_readAllNotes);
        resultAllNotesTV = (TextView) findViewById(R.id.tv_showAllNotes);
    }

    public void readAllNotes(View view) {
        resultAllNotesTV.setText("");
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_CONTENT
        };
        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ? ";
        String[] selectionArr = {
                "Record title"
        };
        //String sortOrder = FeedEntry.COLUMN_NAME_CONTENT + "DESC";
        Cursor cursor = database.query(
                FeedEntry.TABLE_NAME,       // TABLE
                projection,                 //Projection
                null,                       //Selection Where
                null,                       //Values for selection
                null,                       //Group by
                null,                       //Filters
                null                        //Sort order
        );
        while (cursor.moveToNext()){

            long entryId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String entryTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
            String entryContent = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_CONTENT));
            Log.d(TAG, "readRecord: id: " + String.valueOf(entryId) + " Title: " + entryTitle + " Subtitle: " + entryContent);

            resultAllNotesTV.append("[ " + entryId + " ] " + "Note Title --> " + entryTitle + "\n" +
                    "Content --> " + entryContent + "\n" + "\n" );

        }
    }
}

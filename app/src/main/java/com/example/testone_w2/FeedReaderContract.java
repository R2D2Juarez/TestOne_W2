package com.example.testone_w2;

import android.provider.BaseColumns;

/**
 * Created by pharr on 28/07/17.
 */

public class FeedReaderContract {
    private FeedReaderContract(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}

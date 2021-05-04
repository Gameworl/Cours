package iutinfo.lp.devmob.devoirbdandroid;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class CsProvider extends ContentProvider {

    static final String PROVIDER_NAME = "iutinfo.lp.devmob.devoirbdandroid";
    static final String URL = "content://" +PROVIDER_NAME + "/cs";
    static final Uri CONTENT_URI = Uri.parse(URL);

    //table
    private static final String TABLE_NAME = "cs_event";
    private static final String TABLE_ROW_ID = "id";
    private static final String TABLE_ROW_EVENTNAME = "cs_eventname";
    private static final String TABLE_ROW_EVENTDATE = "cs_eventdate";

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cs", 1);
    }

    DataBaseHandler databaseManager;
    SQLiteDatabase database;

    public static HashMap<String, String> csMap;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseManager = new DataBaseHandler(context);
        database = databaseManager.getWritableDatabase();

        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        queryBuilder.setProjectionMap(csMap);

        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/vnd.CsProvider.cs";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long row = database.insert(TABLE_NAME, "", values);

        //if record is added successfully
        if (row >0){
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("fail to insert on " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

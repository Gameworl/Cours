package iut.lp.dba.birthdayprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class BirthProvider extends ContentProvider {

    static final String PROVIDER_NAME = "iut.lp.dba.birthdayprovider";
    static final String URL = "content://" + PROVIDER_NAME +"/friends";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String TABLE_NAME = "BIRTHDAY";
    static final String ID = "id";
    static final String NAME = "name";
    static final String BIRTHDAY = "birthday";

    static final int FRIENDS = 1;
    static final int FRIENDS_ID = 2;

    private DatabaseManager databaseManager;
    private SQLiteDatabase database;

    private HashMap<String,String> BirthMap;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"friends", FRIENDS);
        uriMatcher.addURI(PROVIDER_NAME,"friends/#",FRIENDS_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseManager = new DatabaseManager(context);
        database = databaseManager.getWritableDatabase();

        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case FRIENDS:
                queryBuilder.setProjectionMap(BirthMap);
                break;
            case FRIENDS_ID:
                queryBuilder.appendWhere(ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || Objects.equals(sortOrder, "")){
            sortOrder =  NAME;
        }


        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long row = database.insert(TABLE_NAME, "", values);

        if (row > 0){
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI,row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return  newUri;
        }
        throw new SQLException("Fail to add a new record into "+ uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case FRIENDS:
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                String id = uri.getLastPathSegment();

                count = database.delete(TABLE_NAME, ID + "=" + id + (!TextUtils.isEmpty(selection) ?" AND ("+selection+")": ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;
        switch (uriMatcher.match(uri)){
            case FRIENDS:
                count = database.update(TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                count = database.update(TABLE_NAME, contentValues, ID + "=" + uri.getLastPathSegment() + (!TextUtils.isEmpty(selection) ?" AND ("+selection+")": ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case FRIENDS:
                return "vnd.android.cursor.dir/vnd.birthdaymobapp.friends";
            case FRIENDS_ID:
                return "vnd.android.cursor.item/vnd.birthdaymobapp.friends";
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
    }
}


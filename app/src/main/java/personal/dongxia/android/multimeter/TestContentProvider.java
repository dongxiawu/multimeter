package personal.dongxia.android.multimeter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class TestContentProvider extends ContentProvider {
    private static final String TAG = "TestContentProvider";
    public TestContentProvider() {
        Log.d(TAG, "TestContentProvider: ");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert: ");
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query: ");
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
        String[] selectionArgs) {
        Log.d(TAG, "update: ");
        return 0;
    }
}

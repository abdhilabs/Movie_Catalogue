package com.abdhilabs.submission4.provider;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.abdhilabs.submission4.db.AppDatabase;
import com.abdhilabs.submission4.db.MovieFavDAO;

import java.util.Objects;

@SuppressLint("Registered")
public class FavoriteProvider extends ContentProvider {

    public static final String AUTHORITY = "com.abdhilabs.submission4.provider";

    public static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/" + "tMovie");

    public static final Uri URI_TV_SHOW = Uri.parse(
            "content://" + AUTHORITY + "/" + "tTvShow");

    private static final int CODE_MOVIE = 1;
    private static final int CODE_TV_SHOW = 2;

    AppDatabase database;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "tMovie", CODE_MOVIE);
        MATCHER.addURI(AUTHORITY, "tTvShow", CODE_TV_SHOW);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (MATCHER.match(uri)) {
            case CODE_MOVIE:
                MovieFavDAO movieFavDAO = AppDatabase.getDatabase(getContext()).movieFavDAO();
                return movieFavDAO.selectAll();
            case CODE_TV_SHOW:
                MovieFavDAO movieFavDAO1 = AppDatabase.getDatabase(getContext()).movieFavDAO();
                return movieFavDAO1.selectAllTv();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

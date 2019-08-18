package com.abdhilabs.submission4.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.abdhilabs.submission4.model.MovieItem;
import com.abdhilabs.submission4.model.TvShowItem;

import java.util.List;

@Dao
public interface MovieFavDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieItem movieItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShow(TvShowItem tvShowItem);

    @Query("SELECT * FROM tMovie WHERE id = :id")
    MovieItem selectedMovie(int id);

    @Query("SELECT * FROM tTvShow WHERE id = :id")
    MovieItem selectedTv(int id);

    @Delete
    void deleteMovie(MovieItem movieItem);

    @Delete
    void deleteTv(TvShowItem tvShowItem);

    @Query("SELECT * FROM tMovie ORDER BY title ASC")
    List<MovieItem> selectFavoriteMovie();

    @Query("SELECT * FROM tTvShow ORDER BY title ASC")
    List<TvShowItem> selectFavoriteTv();

    @Query("Select * from tMovie ")
    Cursor selectAll();

    @Query("Select * from tTvShow ")
    Cursor selectAllTv();

    @Query("Select * from tMovie where id = :id")
    Cursor selectById(long id);
}

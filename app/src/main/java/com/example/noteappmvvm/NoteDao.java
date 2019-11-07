package com.example.noteappmvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.nio.channels.FileLock;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
    //TODO test and read but seems everything is alright
    @Query("DELETE FROM note_table ")
    void deleteAllNotes();

//    @Query("SELECT * FROM note_table ORDER BY priority DESC")
//    LiveData<List<Note>> getAllNote();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNote();
}

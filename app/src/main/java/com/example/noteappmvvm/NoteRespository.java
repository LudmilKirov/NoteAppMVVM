package com.example.noteappmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRespository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    //TODO remove the appdatabase
    public NoteRespository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNote();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void delete(Note note) {
        noteDao.delete(note);
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
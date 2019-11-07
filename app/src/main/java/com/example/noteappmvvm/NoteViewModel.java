package com.example.noteappmvvm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

public class NoteViewModel extends AndroidViewModel {
    private NoteRespository repository;
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private MutableLiveData<Boolean> booleanLiveData = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        repository = new NoteRespository(application);
        allNotes = repository.getAllNotes();
        setBooleanLiveData(true);
    }

    private void setBooleanLiveData(boolean b) {
       booleanLiveData.setValue(b);
    }

   // private MutableLiveData<>

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //public  LiveData<Boolean> getBooleanValue(){return }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            final long start = System.currentTimeMillis();
            // wait 5 seconds (5000 milliseconds) until proceeding
            while (System.currentTimeMillis() - start < 5000) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        NoteViewModel noteViewModel;


        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

         // noteViewModel.setShowDialog();
        }

        @Override
        protected void onPreExecute() {

        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;


        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
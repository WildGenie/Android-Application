package com.gelecekbilimde.gelecekbilimde.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gelecekbilimde.gelecekbilimde.Database.MyDatabase;
import com.gelecekbilimde.gelecekbilimde.Database.VideoDao;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.util.List;

public class VideoRepository {
    private VideoDao dao;
    private LiveData<List<VideoModel>> allVideos;

    public VideoRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        dao = database.videoDao();
        allVideos = dao.getAllVideos();
    }
    public void insertVideo(VideoModel video) {
        new InsertVideoAsync(dao).execute(video);
    }
    public void updateVideo(VideoModel video) {
        new UpdateVideoAsync(dao).execute(video);
    }
    public void deleteVideo(VideoModel video) {
        new DeleteVideoAsync(dao).execute(video);
    }
    public void deleteAllVideos() {
        new DeleteAllVideosAsync(dao).execute();
    }

    public LiveData<List<VideoModel>> getAllVideos(){
        return allVideos;
    }


    private static class InsertVideoAsync extends AsyncTask<VideoModel,Void,Void> {
        private VideoDao dao;

        public InsertVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.insertVideo(videoModels[0]);
            return null;
        }
    }
    private static class UpdateVideoAsync extends AsyncTask<VideoModel,Void,Void>{
        private VideoDao dao;

        public UpdateVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.updateVideo(videoModels[0]);
            return null;
        }
    }
    private static class DeleteVideoAsync extends AsyncTask<VideoModel,Void,Void>{
        private VideoDao dao;

        public DeleteVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.deleteVideo(videoModels[0]);
            return null;
        }
    }
    private static class DeleteAllVideosAsync extends AsyncTask<Void,Void,Void>{
        private VideoDao dao;

        public DeleteAllVideosAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllVideos();
            return null;
        }
    }
}
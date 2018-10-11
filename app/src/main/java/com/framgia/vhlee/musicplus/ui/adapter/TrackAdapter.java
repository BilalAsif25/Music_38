package com.framgia.vhlee.musicplus.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framgia.vhlee.musicplus.R;
import com.framgia.vhlee.musicplus.data.model.Track;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {
    private List<Track> mTracks;
    private OnClickItemSongListener mListener;

    public TrackAdapter(List<Track> tracks, OnClickItemSongListener listener) {
        mTracks = tracks;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_track, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bindData(i, mListener);
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mTrackImage;
        private TextView mTrackName;
        private TextView mSingerName;
        private ImageView mFeature;
        private ImageView mAddNowPlaying;
        private OnClickItemSongListener mListener;
        private int mPosition;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrackImage = itemView.findViewById(R.id.image_track);
            mTrackName = itemView.findViewById(R.id.text_song_name);
            mSingerName = itemView.findViewById(R.id.text_singer_name);
            mFeature = itemView.findViewById(R.id.image_feature);
            mAddNowPlaying = itemView.findViewById(R.id.image_add_now_play);
        }

        public void bindData(final int position, final OnClickItemSongListener listener) {
            mTrackName.setText(mTracks.get(position).getTitle());
            mSingerName.setText(mTracks.get(position).getArtist());
            Glide.with(itemView.getContext())
                    .load(mTracks.get(position).getArtworkUrl())
                    .into(mTrackImage);
            mListener = listener;
            mPosition = position;
            itemView.setOnClickListener(this);
            mFeature.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_feature:
                    mListener.showDialodFeatureTrack(mPosition);
                    break;
                default:
                    mListener.clickItemSongListener(mPosition);
            }
        }
    }

    public void updateTracks(List<Track> tracks) {
        if (tracks != null) {
            mTracks.clear();
            mTracks.addAll(tracks);
            notifyDataSetChanged();
        }
    }

    public void addTracks(List<Track> tracks) {
        if (tracks != null) {
            mTracks.addAll(tracks);
            notifyDataSetChanged();
        }
    }

    public interface OnClickItemSongListener {
        void clickItemSongListener(int position);

        void showDialodFeatureTrack(int position);
    }
}

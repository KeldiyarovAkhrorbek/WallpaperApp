package com.projects.wallpaper20.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.wallpaper20.databinding.ItemPhotoBinding;
import com.projects.wallpaper20.entity.PhotoEntity;
import com.squareup.picasso.Picasso;

public class PhotoAdapterLiked extends ListAdapter<PhotoEntity, PhotoAdapterLiked.ItemViewHolder> {

    public OnItemClickListener onItemClickListener;

    public PhotoAdapterLiked(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        PhotoEntity photo = getItem(position);
        if (photo != null) {
            Picasso.get().load(photo.getLandscape()).into(holder.binding.image);
            holder.binding.image.setOnClickListener(view -> {
                onItemClickListener.onItemClick(photo);
            });
        }
    }


    private static DiffUtil.ItemCallback<PhotoEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PhotoEntity>() {
                @Override
                public boolean areItemsTheSame(PhotoEntity oldItem, PhotoEntity newItem) {
                    return oldItem.getLandscape().equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(PhotoEntity oldItem, PhotoEntity newItem) {
                    return oldItem.getLandscape().equals(newItem);
                }
            };


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemPhotoBinding binding;

        public ItemViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PhotoEntity photo);
    }
}

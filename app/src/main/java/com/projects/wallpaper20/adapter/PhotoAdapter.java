package com.projects.wallpaper20.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.wallpaper20.databinding.ItemPhotoBinding;
import com.projects.wallpaper20.models.Photo;
import com.squareup.picasso.Picasso;

public class PhotoAdapter extends PagedListAdapter<Photo, PhotoAdapter.ItemViewHolder> {

    public OnItemClickListener onItemClickListener;

    public PhotoAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Photo photo = getItem(position);
        if (photo != null) {
            Picasso.get().load(photo.getSrc().getLandscape()).into(holder.binding.image);
            holder.binding.image.setOnClickListener(view -> {
                onItemClickListener.onItemClick(photo);
            });
        }
    }


    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Photo>() {
                @Override
                public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.getId().equals(newItem.getId());
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
        void onItemClick(Photo photo);
    }
}

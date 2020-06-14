package com.cralos.myapplicationdb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.myapplicationdb.R;
import com.cralos.myapplicationdb.databinding.ItemUserBinding;
import com.cralos.myapplicationdb.models.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private OnClickUser listener;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_user, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.binding.setUser(users.get(position));
        holder.binding.imgvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClickDeleteUser(users.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else
            return 0;

    }

    public void setUsers(List<User> users) {
        this.users.clear();
        if (users != null)
            this.users.addAll(users);
        notifyDataSetChanged();
    }

    public void setOnClickUser(OnClickUser listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding binding;

        public ViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}

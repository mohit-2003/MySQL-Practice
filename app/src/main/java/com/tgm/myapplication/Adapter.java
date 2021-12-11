package com.tgm.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Model> list;
    deleteRecord deleteRecord;
    updateRecord updateRecord;

    public Adapter(List<Model> list, deleteRecord deleteRecord, updateRecord updateRecord) {
        this.list = list;
        this.deleteRecord = deleteRecord;
        this.updateRecord = updateRecord;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(list.get(position).getId().concat("."));
        holder.name.setText(list.get(position).getName());
        holder.pass.setText(list.get(position).getPass());
        holder.delete.setOnClickListener(view -> {
            deleteRecord.delete(Integer.parseInt(list.get(position).getId()), holder.getAdapterPosition());
        });
        holder.update.setOnClickListener(view -> {
            updateRecord.update(Integer.parseInt(list.get(position).getId()), holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, pass;
        ImageButton delete, update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            pass = itemView.findViewById(R.id.pass);
            delete = itemView.findViewById(R.id.deleteBtn);
            update = itemView.findViewById(R.id.updateBtn);
        }
    }

    interface deleteRecord {
        void delete(int id, int position);
    }

    interface updateRecord {
        void update(int id, int position);
    }
}

package com.example.retrofit_open_source;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private List<ApiData> list;

    public DashboardAdapter(List<ApiData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.dashboard_list_item, parent, false);
        return new DashboardViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        ApiData listItem = list.get(position);
        holder.urlTextView.setText(listItem.getRepository_url());
        holder.loginTextView.setText(listItem.getUser().getLogin());
        holder.idTextView.setText(listItem.getUser().getId());
        if (listItem.getState().equals("open")) {
            holder.statusCheckbox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DashboardViewHolder extends RecyclerView.ViewHolder {

        private TextView urlTextView;
        private TextView loginTextView;
        private TextView idTextView;
        private CheckBox statusCheckbox;

        DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.urlTextView = itemView.findViewById(R.id.url);
            this.loginTextView = itemView.findViewById(R.id.login);
            this.idTextView = itemView.findViewById(R.id.id);
            this.statusCheckbox = itemView.findViewById(R.id.status_checkbox);
        }
    }
}

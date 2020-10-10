package com.example.retrofit_open_source;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashBoardView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public DashboardActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        new DashBoardPresenter(ServiceBuilder.build(ApiDataService.class),this).makeApiRequest();
    }

    @Override
    public void openNewActivity(List<ApiData> apiDataList) {
        progressBar.setVisibility(View.GONE);
        DashboardAdapter dashboardAdapter = new DashboardAdapter(apiDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dashboardAdapter);
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Unable to fetch data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}

package com.example.retrofit_open_source;

import java.util.List;

public interface DashBoardView {
    void openNewActivity(List<ApiData> apiDataList);

    void showError();

    void onRequestProgress();
}

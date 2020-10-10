package com.example.retrofit_open_source;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardPresenter {
    private ApiDataService apiDataService;
    private DashBoardView dashBoardView;

    public DashBoardPresenter(ApiDataService apiDataService, DashBoardView dashBoardView) {
        this.apiDataService = apiDataService;
        this.dashBoardView = dashBoardView;
    }

    public void makeApiRequest() {
        dashBoardView.onRequestProgress();
        apiDataService.getData().enqueue(new Callback<List<ApiData>>() {
            @Override
            public void onResponse(Call<List<ApiData>> call, Response<List<ApiData>> response) {
                if (response.isSuccessful()) {
                    List<ApiData> apiDataList = response.body();
                    dashBoardView.openNewActivity(apiDataList);
                    return;
                }
                dashBoardView.showError();
            }

            @Override
            public void onFailure(Call<List<ApiData>> call, Throwable t) {
                dashBoardView.showError();
            }
        });
    }
}

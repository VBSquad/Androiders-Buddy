package com.example.mapaparea;

import android.app.Activity;

interface BasePresenter {
    void start();
}
interface BaseView <T>{
    void setPresenter(T presenter);
    Activity getViewActivity();
}

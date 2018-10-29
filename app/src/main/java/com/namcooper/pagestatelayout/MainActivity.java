package com.namcooper.pagestatelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PageStateLayout state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = findViewById(R.id.psl_state);

        state.setOnPageStateClickListener(new OnPageStateClickListener() {
            @Override
            public void onEmpty() {
                showToast("空状态点击了");
            }

            @Override
            public void onNetError() {
                showToast("网络错误状态点击了");
            }

            @Override
            public void onDataError() {
                showToast("数据错误点击了");
            }

            @Override
            public void onDiyState() {
                showToast("自定义状态点击了");
            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public void loading(View view) {
        state.showLoading();
//        View loadingView = View.inflate(this, R.layout.loading_view, null);
//        state.showLoading(loadingView);
    }

    public void empty(View view) {
//        state.showEmpty();
    }

    public void netError(View view) {
        state.showNetError();
    }

    public void dataError(View view) {
        state.showDataError();
    }

    public void showContent(View view) {
        state.showContent();
    }
}

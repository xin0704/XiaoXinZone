package com.xin.xiaoxinzone.UI.Main.HeadNews.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vondear.rxtools.view.RxTitle;
import com.xin.xiaoxinzone.Base.BaseFragmentActivity;
import com.xin.xiaoxinzone.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/14.
 */

public class HeadNewsWebActivity extends BaseFragmentActivity {

    @BindView(R.id.head_news_web_view)
    WebView webView;
    @BindView(R.id.head_news_web_progress_bar)
    ProgressBar progressBar;

    private String url;

    private String title;

    private static final String URL_KEY = "url";

    private static final String TITLE_KEY = "title";

    public static void startHeadNewsWebActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, HeadNewsWebActivity.class);
        intent.putExtra(URL_KEY, url);
        intent.putExtra(TITLE_KEY, title);
        context.startActivity(intent);
    }


    @Override
    protected void initData() {

        url = getIntent().getStringExtra(URL_KEY);
        title = getIntent().getStringExtra(TITLE_KEY);

    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatusStyle(TEXT_STATUS_BAR);

        mTvTitle = (RxTitle) findViewById(R.id.rx_title);
        //设置显示返回按钮
        mTvTitle.setLeftIconVisibility(true);
        //设置标题
        mTvTitle.setTitle(title);

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


    }

    @Override
    protected void initListener() {
        //设置返回按钮监听事件
        mTvTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置加载进度
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                    progressBar.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //设置网页的title
                mTvTitle.setTitle(title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });

        //加载新闻链接
        webView.loadUrl(url);

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_head_news_web;
    }

    @Override
    protected void onClickListener(View v) {
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}

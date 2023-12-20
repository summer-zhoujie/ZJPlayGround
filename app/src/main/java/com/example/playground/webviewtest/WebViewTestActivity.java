package com.example.playground.webviewtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.DownloadListener;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;
import com.zj.tools.mylibrary.ZJLog;

public class WebViewTestActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, WebViewTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        HookUtils.hookMacAddress("Z-Activity", WebViewTestActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);

        setupWebView();
        webView.loadUrl("http://debug.mobads.baidu.com/clk?url=https%3A%2F%2Fm%2Ebaidu%2Ecom%2Fbaidu%2Ephp%3Furl%3D000000a25U966rnsnzaa4MTH7j%5FUtTTehL%5FWKx92t4GhK9g4Gd1ljtR3NwPTd5DTT0SniDTMeQsI0wWzbGvRmJYkl%2D32EO%5FCdSKAQsDH9Qrk0rmEqDauAqzMlhOjH9kPmJwisEqMPaIAwBjL%2DFPIJhaDNhl1S%5Fyubm%2D%5FkrLRT8wskAaGacIovDsZvY44jD77%5FXgcOZudlMk2wM8O0wNlAy%2DN74a%5F%2E7R%5FNR2Ar5Od66uxAS6Mz3D4g%5FkTPHniccLYDsTAg9HkvTp32ShzEgY%5Fd4qdh1gMenMHE1%5FsLtTtrHG%5FeD42qqheuLd3hcELOYrA28zetvgHOvcELOqTTNxSE%5FOPSLxS8Q5fOg7%2DOtZw7olsdnTXVqLQqAFBQqOqj9Bo%5F8OP%2DYOqBQqOOgZ7gOoe51xVLOoB1Y5dkuxOKYwx%2DCNkuUcqNx48WtLeOS8Etvy25tLeT5AUQS9OvrVqLQxYwYA5Pe5O6I8tx9hexU3YXjO6RQrM4uU5OKSvSS5lOkP5HjuuuuuG%5F%5F%5F%5F%5F%5F%5F%5F%5F%5F%5F%5F%5F%5F%5FxO%5FGlTEOmOZZODOPS9O3QQQQAxvO9SEYTUQQQQnEQeZe8MG%5F%5F%5F%5FE%5FOUlU5U7SOOoSBQqEHgP7SO8O3bOoOQOv5uSOgIS2U2PjOuJvxCpE%2D%5FxgCvX1qOxIfd5vFHYT8H1dOjS9CvT5SdZzt54OLOLOSkSxQSxVCqD5jfYRPMwSxFqAiUxtVIhotXW%2DT%5FHZH8ozyNq%2DMQjWhyAp7WI%5F3S5iC%2EU1Yk0ZDqmhN10ZfqnHRvnWm10A%2DV5HTsn0KM5yN1mgKzUL94gvN3T0KdpHY0TA%2Db5Hb0mv%2Db5HDYn6Kbpyfqn0KVIjYkPWRkPfKBm1Yk0AqvUjY1nj0snjDVn6KsTjYs0ZwdT1Y4rHcLPWDYrHD3nWTvrjfkrHTd0ZF%2DTgfqnHmLrjm1PWRYPHmvPsK1pyfqnHI%2DrH6dnHD1mWm3uW79msKsUWYs0Aq15Hc0IvbqnH00uh3qPWRknW0kPjKxmLKz0APYp1Y0IZRqXA%2D8UA78u1DsPHD3nWcdPH0v0A7bmvk9TLnqnHn0IAYqn0K9mv%2Db5fK1uAVxIWYY0ZP1TjYknjRk0Zwb5fKWpjY0UMwYTjY10A7sT7q1pyfqmhR3mW6Ynhn0pgwV5fKVIZ%2DsuHYkPsK9uZw4TARqnH6L0A7YTA%2Db5H00mLKGujYknj0k0ZK%5FpyfqnHRkPWmknWcdrHbkr0KWpA%2Db5fK9TZKvUWd9TA%2DxPa3s0Zw9XjY0Ugw15HDvPHDd0AqWTAnqnznzc1Tkc1T1Pinkn1T1PBn4njckc16WcznkQW0dnankQWcdnanWc10WninkQW0scznWnHDYn1fWnansc1KxnW0vrjbvc1DWPWTWnanLn1RWnanknHTsrinkc1D8nj0Wni3snj0Wni3snj0Wni3snj0Wna3snj0Wna3snj0Wni3snankc16WnW6WnWbLnjbkc1D8nj0sg1Kxni3snjKxni3snj0Wnankc1nvP1RWc10Wn7tkQW0sn7tsg108nj0sg1D8nj0sg17xn7tsg108nj0WnHDWnansg1Kxn7tsg1Kxn7tsQW0snj0sn7tsg1D8nj0snj0sg1Kxni3snj0snjKxn7tsg108nj0snj0sg1Kxn7tsQW0snj0sn7tsQW0snj0sn7tsg1Kxn7tsg1D3n1Rsg1Kxni3snj0snjKxn7tkQW0snj0snansg1Kxna3snj0snjKxn7tsg100XhG35Hn0XhG45HD8nWRs0ZGJXWYkQWcdn0KlpMfqni3snj00mgPG5H00TZfqn1cLP100TAq1pgwGUv3qn0KsULPGIA%2DEU%2DqWpA78uvNxThN9Tvq85H00ULFGgvdY5HDvPHDd0AdYgLKY5H00myPWIjYz0AwWmvfq0AwWTM0qmvqVQMPGUhD8UhNLTsKbuguYTjdETZKEQRqfRDqxgYqfRDqxgYqfRDt6fHRLI0K1UWY3Pj6YPhRvnAmLuj%2DWPWfkrHK%2Duhndnyfsn1RsnhDdPzY0TZFEujYknsKEmLKWgvk9m%2Dq1IA7YIgnqn0KBuZcqn0K9uZ6qn0KVIM0qn0KBIy%5Fqn0KEUg0qn0KBmvDqn0KEmLKWgvP%5FpyPo5H0Wnansc10Wnansc1D8nj0sc1D8nj0s0AuYXgK%2D5Hf0TMPz5HbLuHb3PHDknvcvrAmkmyPxn0KBTZTqPW0s0AFspjY1nj00myw1U%2Dq15H00myw1U%2DqGT1Y0mhd%5F5HDsnsKVTZfqn0KVTZwv5H00Uy7WPHY0UvDq0A7bmv%5Fqn0K%5FIjYs0ZPW5H00mMP%5F5Hc0mhP85Hc0Ih%2DbuyqGujY0mhN1UjYd0A%2D1uAsqnfKEUAw%5F5H00TZFEuv7bIWYs0A71XHYs0Adz5HD0mywsI1YknW6s0A7bTA6qPWfs0A%2D1TjdCmh7CTfKCTvkM5H00nHYs0jcqP17bnvmznH79mhNhmHmdrf015H6YrjfvuHmsuWIbrynvPjD4nANhm1Rkuj01PH0zmHRL0jRqnHTLPWbv0jmqn00L5H00rjYs0jbq0ZKzUvIxIZnqn163rHm3nWc1PWRvnjnzrHbLnfKsThqMgLK15H00TZFEudqYpHYs0ZKzUvIxTAbq0ZKWpHYs0APGpHYdPHnYn10vnW63P1f0TvGC5HcdPfK1mvRqPfK1u1Ys0AF15HD0mgwY5HD0mgNY5HKxn0K1IgKGujY0mMNYmyTqn0KBmL0qn0K8ugfqn6KzIADqnfKzIZnqnWR0UvqB5HT1PfKzmMfqn0KzUvcqP1nd0ZFBTWYkQW0sn0Kzug6qn0Kzmvnqn0KBmybqn0KBmgcqni3snj00uAd15H00uANV5H00mLFB5H00ThFE5HD8nj0s0AF%2Dm1Ys0AFbTjYzr0KBUhnqn0KBTvRqn0KBmvfqn0K%2DTLfqn0K1Uy%5Fq0A91T1Ys0A91uHYs0ZK%5FIjdBugPCTfKWThnqPH6YnjR&s=f1dca2526ab9de22");
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void setupWebView() {
        webView = findViewById(R.id.common_web_webview);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                ZJLog.d("download url=" + url);
                downloadByBrowser(url);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                ZJLog.d("url=" + url);
                if (TextUtils.isEmpty(url)) {
                    return false;
                }

                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    return false;
                }

                try {
                    Intent intent = Intent.parseUri(url, 0);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                }
                return true;
            }
        });

        //声明WebSettings子类
        webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
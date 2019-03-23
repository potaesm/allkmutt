package th.ac.kmutt.suthinan.allkmutt;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class AppActivity extends Activity {

    private WebView mWebview;
    private Button backButton, reloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_app);

        String url = getIntent().getExtras().getString("URL");

        backButton = findViewById(R.id.backButton);
        reloadButton = findViewById(R.id.reloadButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(false);
                Intent intent = new Intent(AppActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        });

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebview.loadUrl(mWebview.getUrl());
                //mWebview.loadUrl("javascript:window.location.reload(true)");
            }
        });

        mWebview = findViewById(R.id.webview);


        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl(url);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().endsWith(".pdf")) {
                    view.loadUrl("https://docs.google.com/viewer?url=" + request.getUrl().toString());
                } else {
                    view.loadUrl(request.getUrl().toString());
                }
                return false;
            }
        });
        mWebview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Downloaded Content");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebview.canGoBack()) {
                        mWebview.goBack();
                    } else {
                        Intent intent = new Intent(AppActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        onDestroy();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
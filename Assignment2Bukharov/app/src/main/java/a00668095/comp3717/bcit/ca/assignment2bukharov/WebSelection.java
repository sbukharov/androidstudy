package a00668095.comp3717.bcit.ca.assignment2bukharov;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import static android.R.attr.value;

public class WebSelection extends AppCompatActivity {

    TextView SelectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_selection);

        SelectedText = (TextView)findViewById(R.id.selectedText);
        String url = getIntent().getStringExtra("url");
        SelectedText.setText(url);

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.setWebViewClient(new WebViewClient());

        browser.loadUrl(url);
    }
}

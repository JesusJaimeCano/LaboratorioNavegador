package e.jesus.laboratorionavegador;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class Buscador extends AppCompatActivity {

    WebView webViewBA;
    Button buscarButton, backButton, updateButton, goButton, favButton;
    EditText sitioABuscar;

    String urlRecibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        webViewBA = findViewById(R.id.busquedaWebViewBA);
        buscarButton = findViewById(R.id.buscarButtonBA);
        backButton = findViewById(R.id.backButtonBA);
        updateButton = findViewById(R.id.updateButtonBA);
        goButton = findViewById(R.id.goButtonBA);
        favButton = findViewById(R.id.favButtonBA);
        sitioABuscar = findViewById(R.id.sitioABuscarEditTextBA);

        webViewBA.getSettings().setJavaScriptEnabled(true);

        urlRecibida = getIntent().getStringExtra("url");
        webViewBA.loadUrl(urlRecibida);

        webViewBA.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewBA.goBack();
                if (!webViewBA.canGoBack()) {
                    finish();
                }

            }
        });



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewBA.reload();
            }
        });


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewBA.goForward();
                webViewBA.canGoForward();
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();
                data.setData(Uri.parse(webViewBA.getUrl()));
                setResult(RESULT_OK, data);
                finish();

            }
        });

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewBA.loadUrl("https://www." + sitioABuscar.getText().toString() + ".com");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            webViewBA.goBack();
            webViewBA.canGoBack();
        }
        return false;
    }
}

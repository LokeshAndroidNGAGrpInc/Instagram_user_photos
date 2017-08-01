package com.dev.pyar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;
    private Singleton mSingleton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSingleton = Singleton.newInstance();
        mWebview = (WebView) findViewById(R.id.main_activity_webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new AuthWebViewClient());
   /*     mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });*/
        mWebview.addJavascriptInterface(new UriRespReceiverInterface(),"HTMLOUT");
        mWebview.loadUrl("https://api.instagram.com/oauth/authorize/?client_id=390bfd18da96488b90df5b43f4d435ed&response_type=code&redirect_uri=http://devm.pyar.com/");

    }


    private class UriRespReceiverInterface {
        public void showHTML(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//            Log.d("MainActivity***", "showHTML: "+result);
        }
    }


    public class AuthWebViewClient extends WebViewClient{

        private String request_token;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(StringConstants.mRedirectUrl)) {
                System.out.println(url);
//                Log.d("AuthWebViewClient*****", "redirect url "+url);
                String parts[] = url.split("=");
                request_token = parts[1];  //This is your request token.
//                Log.d("AuthWebViewClient*****", " request token "+request_token);
                mSingleton.setReuestToken(request_token);
                mGetRequestAccessToken(request_token);
                return true;
            }
            return false;
        }
    }

    private void mGetRequestAccessToken(String request_token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StringConstants.INSTAGRAM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceOperations service = retrofit.create(ServiceOperations.class);
        Call<Example>  resposeCall  = service.getAccessToken(StringConstants.mClientId,
                StringConstants.mClientSecret,StringConstants.mGrantType,StringConstants.mRedirectUrl,
                request_token);
        resposeCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();

                if(example != null) {
                    Singleton.setAccessToken(example.getAccessToken());
                    Toast.makeText(MainActivity.this, "Hello !" + example.getUser().getFullName(), Toast.LENGTH_SHORT).show();
//                    Log.d("MainActivity****", "onResponse: "+example.getUser().getId());
                    startActivity(new Intent(MainActivity.this,GetUserPhotosActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Retrofit Error!", Toast.LENGTH_SHORT).show();
//                Log.d("MainActiity****", "onFailure: "+t.getMessage());
            }
        });
    }
}

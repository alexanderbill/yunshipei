package com.yunshipei.browser;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	private WebView mWebview;

        public PlaceholderFragment() {
        }
        
        private class HelloWebViewClient extends WebViewClient { 
            @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) { 
                view.loadUrl(url); 
                return true; 
            } 
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            mWebview = (WebView) rootView.findViewById(R.id.webview);
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.setWebViewClient(new HelloWebViewClient()); 
            mWebview.loadUrl("http://m.baidu.com");
            ProxySetting.setProxy(mWebview, "211.157.146.27", 3128, "");
            EditText edittext = (EditText) rootView.findViewById(R.id.edittext_url);
            edittext.setOnEditorActionListener(new OnEditorActionListener() {

    			@Override
    			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
    				// TODO Auto-generated method stub
    				String url = arg0.getText().toString();
    				mWebview.loadUrl(url);
    				return true;
    			}  
            });  
            return rootView;
        }
    }

}

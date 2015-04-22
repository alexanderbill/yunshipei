package com.yunshipei.browser;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private WebView mWebview;
    private Context mContext;
    private String mHost = "211.157.146.27";
    private int mPort = 3128;
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
        mContext = rootView.getContext();
        mWebview = (WebView) rootView.findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new HelloWebViewClient());
        mWebview.loadUrl("http://m.baidu.com");
        ProxySetting.setProxy(mWebview, mHost, mPort, "");

        Button proxy_setting = (Button) rootView.findViewById(R.id.proxy_setting);
        proxy_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showGetRoomUI();
            }
        });
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

    private void showGetRoomUI() {
        final EditText roomInput = new EditText(mContext);
        roomInput.setText("211.157.146.27:3128");
        roomInput.setSelection(roomInput.getText().length());
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String proxy = roomInput.getText().toString();
                    String[] p = proxy.split(":");
                    if (p.length == 2) {
                        mHost = p[0];
                        mPort = Integer.valueOf(p[1]);
                        dialog.dismiss();
                        return;
                    }
                } catch (Exception e) {

                }
                Toast.makeText(mContext, "代理地址不合法", 0).show();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Enter room URL").setView(roomInput)
                .setPositiveButton("Go!", listener).show();
    }
}


package personal.dongxia.multimeter.foundation.web

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView

import personal.dongxia.multimeter.foundation.R


class WebViewFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_webview, container, false)
        webView = root.findViewById(R.id.web_view)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.databaseEnabled = true
        webSettings.setGeolocationEnabled(true)
        webView.webChromeClient = object: WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?) {
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }
        }
        return root
    }
}

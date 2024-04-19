package com.hanwill.webviewangular;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class WebAppInterface {
    private Activity _activity;
    private Context _context;

    public WebAppInterface(Context context, Activity activity)
    {
        _context = context;
        _activity = activity;
    }

    @JavascriptInterface
    public void showNotification(String title, String message) {
        NotificationChannel channel = new NotificationChannel("twChannel", "TW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context, "twChannel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(channel.getId());
        manager.createNotificationChannel(channel);
        manager.notify(1, builder.build());
    }

    @JavascriptInterface
    public void showCall() {
        showToast("Opening phone dialer");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        _context.startActivity(intent);
    }

    @JavascriptInterface
    public void showWhatsApp() {
//        showToast("Opening WhatsApp");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+6282255192245"));
        _context.startActivity(intent);
    }

    @JavascriptInterface
    public void showCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        _context.startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show();
    }
}

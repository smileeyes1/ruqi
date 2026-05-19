package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DiscoveryEngine {
    public interface Callback { void onFound(String ip); void onFinished(); }

    public static void scan(final Context context, final Callback callback) {
        new Thread(() -> {
            String wifiIp = "192.168.1."; // الافتراض الأكثر شيوعاً
            for (int i = 1; i < 255; i++) {
                final String host = wifiIp + i;
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, 11434), 50); // فحص سريع
                    socket.close();
                    
                    // إذا وجدنا المنفذ، نعيد العنوان
                    new Handler(Looper.getMainLooper()).post(() -> callback.onFound("http://" + host + ":11434/api/generate"));
                    return; 
                } catch (Exception ignored) {}
            }
            new Handler(Looper.getMainLooper()).post(callback::onFinished);
        }).start();
    }
}

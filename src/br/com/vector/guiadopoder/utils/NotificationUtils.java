package br.com.vector.guiadopoder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.guiadopoder.R;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


@SuppressLint("SimpleDateFormat")
public final class NotificationUtils {
	
	public static void showNotification(Context context,String contentText, @SuppressWarnings("rawtypes") Class classTo){
		
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        
		Intent intent = new Intent(context, classTo);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        
        @SuppressWarnings("static-access")
		NotificationManager notificationManager =(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        
        NotificationCompat.Builder myNotification = new NotificationCompat.Builder(context);
        
        myNotification.setContentTitle("Vector")
          .setContentText(contentText)
          .setTicker("Notificação")
          .setWhen(System.currentTimeMillis())
          .setContentIntent(pIntent)
          .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_VIBRATE| Notification.DEFAULT_SOUND)
          .setAutoCancel(true)
          .setSmallIcon(R.drawable.ic_launcher)
          .setContentInfo(sdf.format(new Date()))
          .build();
        	
        Notification notification=myNotification.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        
        notificationManager.notify((int) System.currentTimeMillis(), notification);

	}
	
	@SuppressWarnings("static-access")
	public static boolean verifyWifi3G(Context context){	

		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

		//For 3G check
		boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
		            .isConnectedOrConnecting();
		//For WiFi Check
		boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
		            .isConnectedOrConnecting();

		Log.i("Conexão", is3g + " net " + isWifi);

		if (!is3g && !isWifi) 
			return false;
		
		return true;
		
	}

}

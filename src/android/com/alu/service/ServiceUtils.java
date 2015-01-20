package com.alu.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * This class provides utils service-related methods
 * @author clement Perreau
 */
public class ServiceUtils {
	
	
	
	/**
	 * Create a default Foreground Service notification
	 * 
	 * @return
	 */
	public static Notification getDefaultForegroundNotification(Context context, Class<?> componentClass, String title, String text) {
		
		// Intent to resume the application in current state
		Intent intent = new Intent(context, componentClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

		// Build notification
		Notification n  = new Notification.Builder(context)
		        .setContentTitle(title)
		        .setContentText(text)
		        .setContentIntent(pIntent)
		        .build();

		n.flags |= Notification.FLAG_NO_CLEAR;
		return n;
	}
}

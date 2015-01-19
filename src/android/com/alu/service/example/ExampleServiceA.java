package com.alu.service.example;

import com.alu.service.ServicePlugin;
import com.alu.service.ServiceUtils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ExampleServiceA extends Service implements Runnable{

	/**
	 * Service ID
	 */
	private static int ID = 1245;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		super.onStartCommand(intent, flags, startId);
		Log.d(ServicePlugin.LOG_KEY, "Service A starting");
        
        Notification n = ServiceUtils.getDefaultForegroundNotification(this.getApplicationContext(),
        		this.getClass(),
        		"Service A",
        		"This is the service A running in foreground");
        startForeground(ID, n);
        
        Log.d(ServicePlugin.LOG_KEY, "This is service A starting");
        
        new Thread(this).start();
        
		return(START_STICKY);
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}


	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1500);
				Log.d(ServicePlugin.LOG_KEY, "I'm the Service A (foreground service)");
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}

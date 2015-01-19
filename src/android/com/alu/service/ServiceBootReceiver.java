package com.alu.service;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



/**
 * Receiver to start registered services at device boot
 * @author Clement Perreau
 */
public class ServiceBootReceiver extends BroadcastReceiver{
	
	@Override  
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Log.d(ServicePlugin.LOG_KEY, "Boot completed");
			List<String> services = ServiceManager.getRegisteredServices(context);
			for(String service:services){
				try{
					context.startService(new Intent(context, ServiceManager.getServiceClassByName(service)));
					Log.d(ServicePlugin.LOG_KEY, service + " was started at boot");
				}catch(Exception e){
					Log.d(ServicePlugin.LOG_KEY, service + " was unable to start at boot");
				}
			}
        }
	} 
	
}

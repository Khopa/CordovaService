package com.alu.service;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Cordova service to run plugins
 * @author Clement Perreau
 */
public class ServicePlugin extends CordovaPlugin {
	
	/**
	 * Log key for logcat
	 */
	public static final String LOG_KEY = "ServicePlugin";
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		
		boolean result = false;
		String serviceClassName;
		
		if("startService".equals(action)){
			serviceClassName = args.get(0).toString();
			Log.d(LOG_KEY, "Starting service");
			result = startService(serviceClassName);
	    	callbackContext.success(String.valueOf(result));
	    	return true;
		}else if("stopService".equals(action)){
			serviceClassName = args.get(0).toString();
	    	callbackContext.success(String.valueOf(false));
	    	return true;
	    }else if("registerService".equals(action)){
	    	serviceClassName = args.get(0).toString();
	    	result = ServiceManager.registerService(cordova.getActivity(), serviceClassName);
	    	callbackContext.success(String.valueOf(result));
	    	return true;
	    }else if("unregisterService".equals(action)) {
	    	serviceClassName = args.get(0).toString();
	    	result = ServiceManager.unregisterService(cordova.getActivity(), serviceClassName);
	    	callbackContext.success(String.valueOf(result));
	    	return true;
	    }else if("isServiceRunning".equals(action)) {
	    	serviceClassName = args.get(0).toString();
	    	result = isServiceRunning(ServiceManager.getServiceClassByName(serviceClassName));
	    	callbackContext.success(String.valueOf(result));
	    	return true;
	    }else{
	    	Log.d(LOG_KEY, "WARNING : Call to unknown plugin method");
	    	return false;
	    }
	}
	
	/**
	 * Start the service with given name
	 * @param className Service class name
	 */
	public boolean startService(String className){			
		Class<? extends Service> serviceClass = ServiceManager.getServiceClassByName(className);
		if(serviceClass != null && !isServiceRunning(serviceClass)){
			cordova.getActivity().startService(new Intent(cordova.getActivity(), serviceClass));
			Log.d(LOG_KEY, "Starting service -- Step 2 OK");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Stop the service using the given class name
	 * @return True if the service is not running after this method call
	 */
	public boolean stopService(String serviceIntentName){
		return false; // TODO : Implement
	}
	
	
	/**
	 * Check if a service is running
	 * @param serviceClass Service Class we want to run
	 * @return true if an instance of the given service is running
	 */
	private boolean isServiceRunning(Class<? extends Service> serviceClass) {
	    ActivityManager manager = (ActivityManager) cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	

}

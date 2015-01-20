package com.alu.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


/**
 * 
 * Manage a list of service for BootReceiver
 * 
 * @author Clement Perreau
 *
 */
public class ServiceManager {

	/**
	 * Shared Preferences key
	 */
	public static final String SERVICE_MANAGER_PREFS = "ServiceManagerPrefs";
	
	/**
	 * Service list key
	 */
	public static final String SERVICE_LIST_KEY = "ServiceList";
	
	/**
	 * Get the list of registered services
	 * @return List of registered services (for this app)
	 */
	public static List<String> getRegisteredServices(Context context){
		SharedPreferences prefs = context.getSharedPreferences(SERVICE_MANAGER_PREFS, Context.MODE_PRIVATE);
		String servicesString = prefs.getString(SERVICE_LIST_KEY, "");
		String[] servicesArray = servicesString.split(";");
		List<String> services = new ArrayList<String>();
		for(String str:servicesArray){
			services.add(str);
		}
		return services;
	}
	
	/**
	 * Register a service for boot receiver. This will not launch the service.
	 * @return True if the service was successfully registered
	 */
	public static boolean registerService(Context context, String serviceClassName){
		
		// The service must not be already registered and must exists in the app classpath
		if(isServiceRegistered(context, serviceClassName) || getServiceClassByName(serviceClassName) == null){
			return false;
		}
		
		SharedPreferences prefs = context.getSharedPreferences(SERVICE_MANAGER_PREFS, Context.MODE_PRIVATE);
		String servicesString = prefs.getString(SERVICE_LIST_KEY, "");
		Editor e = prefs.edit();
		
		servicesString += serviceClassName + ";";
		
		Log.d(ServicePlugin.LOG_KEY, "Registered services are : " + servicesString);
		
		e.putString(SERVICE_LIST_KEY, servicesString);
		return e.commit();
	}
	
	/**
	 * Unregister a service for Boot receiver. This will not stop the service.
	 * @return True if the service is not registered after the method call
	 */
	public static boolean unregisterService(Context context, String serviceClassName){
		
		if(!isServiceRegistered(context, serviceClassName) || getServiceClassByName(serviceClassName) == null){
			return true;
		}
		
		SharedPreferences prefs = context.getSharedPreferences(SERVICE_MANAGER_PREFS, Context.MODE_PRIVATE);
		String[] servicesArray = prefs.getString(SERVICE_LIST_KEY, "").split(";");
		Editor e = prefs.edit();
		
		String newServicesString = "";
		for(String str:servicesArray){
			if(!str.equals(serviceClassName)){
				newServicesString += str + ";";
			}
		}
		
		Log.d(ServicePlugin.LOG_KEY, "Registered services are : " + newServicesString);
		
		e.putString(SERVICE_LIST_KEY, newServicesString);
		return e.commit();
	}
	
	/**
	 * Check if a service is registered
	 * @return True if the service is registered
	 */
	public static boolean isServiceRegistered(Context context, String serviceClassName){
		for(String str:getRegisteredServices(context)){
			if(str.equals(serviceClassName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return a service class using from it's name as a String
	 * @param className Name of the service (should include package path, example : com.alu.MyService)
	 * @return Service class if found, else null
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends Service> getServiceClassByName(String className){
		try {
			Class<? extends Service> serviceClass;
			serviceClass = (Class<? extends Service>) Class.forName(className);
			return serviceClass;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}	
	}
	
	
	
}

##Cordova Service Plugin

*This is still work in progress and incomplete. Use at your own risks*

*Only android is supported currently*

###Description

This plugins allows to start and stop custom native Android foreground services. It may also register services to start at device boot.

###Installation

```
cordova plugin add https://github.com/Khopa/CordovaService
```

###Javascript Interface

***

- startService
- stopService
- registerService
- unregisterService
- isServiceRunning

***


####Start Service

```javascript
service.startService(serviceClassName, callback, error)
```

**Before being able to start, a service must be registered in the Android Manifest like this :**

```xml
<service android:name="com.me.MyService" android:label="MyService" >
</service>
```

**Example of usage :**

```javascript
service.startService("com.me.MyService",
					function(result){
						// result is either true or false
						console.log("The service was started");
					}, function(error){
						console.log(error);
					});
```

*The function may return "true" if you launch a service which is not declared in the manifest, that will die immediatly. So don't put too much trust into that return value, and use the isServiceRunning function if you want to be sure of the service's state.*

***

#### Stop Service

```javascript
service.stopService(serviceClassName, callback, error)
```

Callback method receive true as param if the service is not running after the method call.

***

#### Register Service

```javascript
service.registerService(serviceClassName, callback, error)
```

Once registered, the service will be started after device's boot. It may takes 30-40 seconds before the service actually starts. You may register as many service to start at boot as needed, as long as they are declared in the app manifest.

It is recommended to let the user configure this behaviour in the application settings if the background service is not a mandatory feature of your application.

The callback method receive true as param if the service is registered after the method call.

**Calling this function will not start the service**

***

#### Unregister Service

```javascript
service.unregisterService(serviceClassName, callback, error)
```

Unregister the service from boot sequence.

Callback method receive true as param if the service is unregistered after the method call.

**Calling this function will not stop the service**

***

#### Is Service Running ?

```javascript
service.isServiceRunning(serviceClassName, callback, error)
```

Callback method receive true if the service is currently running.

***

###Writing your service

See the example usage repository : https://github.com/Khopa/com.alu.cordova-service.example

###What is injected into your manifest

This plugin will inject a boot receiver in your manifest.

The permission "android.permission.RECEIVE_BOOT_COMPLETED" (Run at startup) will be added to allow starting services at device startup.

For more details, look up for yourself in plugin.xml.
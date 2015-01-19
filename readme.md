##Cordova Service Plugin

*This is still work in progress and incomplete. Use at your own risks*

###Description

This plugins allows to start and stop custom native Android foreground services. It may also register services to start at device boot.

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
window.startService(serviceClassName, callback, error)
```

**Before being able to start, a service must be registered in the Android Manifest like this :**

```xml
<service android:name="com.me.MyService" android:label="MyService" >
</service>
```

This start the service whose full name is serviceClassName.
An example of usage would be :

```javascript
window.startService("com.me.MyService",
					function(result){
						// result is either true or false
						console.log("The service was started");
					}, function(error){
						console.log(error);
					});
```

**The function may return "true" if you launch a service which is not declared in the manifest, that will die immediatly. So don't put too much trust into that return value, and use the isServiceRunning function if you want to be sure of the service's state.**

***

#### Stop Service

```javascript
window.stop(serviceClassName, callback, error)
```

***

#### Register Service

```javascript
window.registerService(serviceClassName, callback, error)
```

***

#### Unregister Service

```javascript
window.unregisterService(serviceClassName, callback, error)
```

***

#### Is Service Running ?

```javascript
window.isServiceRunning(serviceClassName, callback, error)
```

***

###Writing your service

See the example usage repository : https://github.com/Khopa/com.alu.cordova-service.example

###What is injected into your manifest

This plugin will inject a boot receiver in your manifest.

The permission "android.permission.RECEIVE_BOOT_COMPLETED" (Run at startup) will be added to allow starting services at device startup.

For more details, look up for yourself in plugin.xml.


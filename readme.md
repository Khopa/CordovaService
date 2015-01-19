##Cordova Service Plugin

*This is still work in progress and incomplete. Use at your own risks*

###Description

This plugins allows to start and stop custom native Android foreground services. It may also register services to start at device boot.

###Javascript Interface

```javascript
window.startService(serviceClassName, callback, error)
```

This start the service whose full name is serviceClassName.
An example of usage would be :

```javascript
window.startService("com.me.myService",
					function(result){
						// result is either true or false
						console.log("The service was started");
					}, function(error){
						console.log(error);
					});
```


```javascript
window.stop(serviceClassName, callback, error)
```

```javascript
window.registerService(serviceClassName, callback, error)
```

```javascript
window.unregisterService(serviceClassName, callback, error)
```

```javascript
window.isServiceRunning(serviceClassName, callback, error)
```


###Writing your service

See the example usage repository : https://github.com/Khopa/com.alu.cordova-service.example

###What is injected into your manifest

This plugin will inject a boot receiver in your manifest.

The permission "android.permission.RECEIVE_BOOT_COMPLETED" (Run at startup) will be added to allow starting services at device startup.

For more details, look up for yourself in plugin.xml.


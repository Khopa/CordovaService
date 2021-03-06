/**
 * Start a service using it's classpath name
 * @param str Service classpath name (Example : com.me.MyService)
 * @param callback Callback on success (Takes one param : True if service was succesfully started)
 * @param error Callback on error (Error string in the unique param)
 */
var startService = function(serviceClassName, callback, error) {
    cordova.exec(
        function(result){callback(result);},
        function(err){error((err));},
        "Service",
        "startService",
        [serviceClassName]);
};


/**
 * Stop a service using it's classpath name
 * @param str Service classpath name (Example : com.me.MyService)
 * @param callback Callback on success (Takes one param : True if service is not running after method call)
 * @param error Callback on error (Error string in the unique param)
 */
var stopService = function(serviceClassName, callback, error) {
    cordova.exec(
        function(result){callback(result);},
        function(err){error((err));},
        "Service",
        "stopService",
        [serviceClassName]);
};


/**
 * Register service for start at device boot
 * @param str Service classpath name (Example : com.me.MyService)
 * @param callback Callback on success (Takes one param : True if service was registered after method call)
 * @param error Callback on error (Error string in the unique param)
 */
var registerService = function(serviceClassName, callback, error) {
    cordova.exec(
        function(result){callback(result);},
        function(err){error((err));},
        "Service",
        "registerService",
        [serviceClassName]);
};

/**
 * Unregister service for start at device boot
 * @param str Service classpath name (Example : com.me.MyService)
 * @param callback Callback on success (Takes one param : True if service was unregistered after method call)
 * @param error Callback on error (Error string in the unique param)
 */
var unregisterService = function(serviceClassName, callback, error) {
    cordova.exec(
        function(result){callback(result);},
        function(err){error((err));},
        "Service",
        "unregisterService",
        [serviceClassName]);
};

/**
 * Check if the service with the given class name is running
 * @param str Service classpath name (Example : com.me.MyService)
 * @param callback Callback on success (Takes one param : True if service is running)
 * @param error Callback on error (Error string in the unique param)
 */
var isServiceRunning = function(serviceClassName, callback, error) {
    cordova.exec(
        function(result){callback(result);},
        function(err){error((err));},
        "Service",
        "isServiceRunning",
        [serviceClassName]);
};


module.exports = {
    startService:startService,
    stopService:stopService,
    registerService:registerService,
    unregisterService:unregisterService,
    isServiceRunning:isServiceRunning
};






(function() {
    'use strict';
    
    angular
        .module('ngMap')
        .constant('API_URL', "http://localhost:3000/")
        .service('SensorService', SensorService);
    
    function SensorService($http, $q, API_URL) {
        
        this.getData = function (versionId) {
          var defered = $q.defer();
          var promise = defered.promise;

          $http({
              method: 'GET',
              url: API_URL + "sensor/version/"+versionId
          })
          .success(function (response){
              defered.resolve(response)
          })
          
          return promise;
        }
    }
})();
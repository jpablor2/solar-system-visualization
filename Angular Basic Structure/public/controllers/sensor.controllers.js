(function() {
    'use strict';
    
    angular
        .module('ngMap')
        .controller('SensorController', SensorController);
    
    function SensorController($scope, SensorService) {
        $scope.coordenadas = [];
        $scope.versionId = 1;
        init();
        
        function init() {
            SensorService.getData($scope.versionId)
            .then(function (promise) {
                promise.forEach(function (cuadrante) {
                    var arr_cuadrante = [
                            [cuadrante.lon2, cuadrante.lat2],
                            [cuadrante.lon1, cuadrante.lat1],
                            [cuadrante.lon3, cuadrante.lat3],
                            [cuadrante.lon4, cuadrante.lat4]
                        ];
                    $scope.coordenadas.push({d
                        'puntos': arr_cuadrante,
                        'radiacion': cuadrante.radiacion
                    });
                });
            });
        }
        
        $scope.changeVersion = function() {
            $scope.coordenadas = [];
            init();
            console.log($scope.coordenadas);
        }
        
        $scope.showInfo = function(ev, radiacion) {
            swal({
              title: 'Radiación: '+radiacion,
              text: 'Radiación dada en el cuadrante.',
              timer: 3000
            })
        }
    }
})();
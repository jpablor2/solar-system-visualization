(function () {
    var app = angular.module('title', []);
    
    app.controller('StoreController', function () {
        this.products = gems;
    }); 
    
    
    app.directive('productTitle', function(){
        return {
            restrict: 'E',//It means element
            templateUrl: 'views/product-title.html'
        };
    });
    app.directive('productTitleA', function(){
        return {
            restrict: 'A',//It means atribute
            templateUrl: 'views/product-title.html'
        };
    });
    
})();


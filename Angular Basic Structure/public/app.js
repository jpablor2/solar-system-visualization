(function () {
//alguna pinga ahi lo que sea
    /*var express = require("/node_modules/express/");
    var server = express();*/
    
    var app = angular.module('store2', ['title','n-navbar','ui.router','file-picker']);
    
    app.config(function($httpProvider, $stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.when('/', '/login');
        $urlRouterProvider.otherwise('/login');
        $stateProvider
            .state('login', {
                url:'/login',
                templateUrl: '/views/base.html',
                controller: 'StoreController'
            }).state('manual', {
                url:'/manual',
                templateUrl: '/views/input-array.html',
                controller: 'ManualController'
            }).state('automatic', {
                url:'/automatic',
                templateUrl: '/views/input-module.html',
                controller: 'AutomaticController'
            }).state('loaded', {
                url:'/loaded',
                templateUrl: '/views/input-inverter.html',
                controller: 'LoadController'
            });
    });
    
 
    
    
    
    
    
    
    //app.directive()
    /*ar gems = [
        {
            name: "Dodecahedron",
            price: 2,
            description: '. . .',
            canPurchase: true,
            images: [
                {
                    full: '../../assets/img/logo.png',
                    thumb: 'algo2a.jpg'
                    },
                {
                    full: 'algo3a.jpg',
                    thumb: 'algo4a.jpg'
                    }
                ],
            reviews: [
                {
                    stars:5,
                    body: "I love this product",
                    author: "joe@thomas.com"
                },
                {
                   stars:1,
                    body: "THis products sucks",
                    author: "tim@hater.com" 
                }
            ]
    }, {
            name: "Pentagonal Gem",
            price: 3.95,
            description: '. . .',
            canPurchase: true,
            images: [
                {
                    full: '../../assets/img/logo.png',
                    thumb: 'algo2b.jpg'
                    },
                {
                    full: 'algo3b.jpg',
                    thumb: 'algo4b.jpg'
                    }
                ],
            reviews: [
                {
                    stars:5,
                    body: "I love this product2",
                    author: "joe2@thomas.com"
                },
                {
                   stars:1,
                    body: "THis products sucks2",
                    author: "tim2@hater.com" 
                }
            ]
    }
    ];*/
    
    /*Un solo elemento:
    var gem= {
        name: "Dodecahedron",
        price: 2.95,
        description: '. . .',
        canPurchase: true,
        soldOut: true
    }*/
    /*
    app.controller("PanelController",function(){
       this.tab=1;
        
        this.selectTab = function(setTab){
            this.tab=setTab;
        };
        this.isSelected = function(checkTab){
          return this.tab === checkTab;  
        };
    });
    
    app.controller("ReviewController", function(){
       this.review = {} ;
        
        this.addReview = function(product){
            product.reviews.push(this.review);
            this.review = {};
        };
    });*/
    /*
    app.directive('productTitle', function(){
        return {
            restrict: 'E',//It means element
            templateUrl: '../../product-title.html'
        };
    });*//*
    app.directive('productTitleA', function(){
        return {
            restrict: 'A',//It means atribute
            templateUrl: './product-title.html'
        };
    });*/
    
})();


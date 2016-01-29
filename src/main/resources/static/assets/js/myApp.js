var app = angular.module("myApp", [ 'ngRoute', 'ngAnimate' ]);

app.factory('Data', function() {
    return {
        email : "",
        isLogged : false
    };
});

app.config([ '$routeProvider', function($routeProvider) {

    $routeProvider.

    when('/home', {
        templateUrl : 'home.html',
        controller : 'homeController'

    }).

    when('/signin', {
        templateUrl : 'signin.html',
        controller : 'loginController'
    }).

    when('/signup', {
        templateUrl : 'signup.html',
        controller : 'registerController'
    }).

    when('/about', {
        templateUrl : 'about.html',
        controller : 'aboutController'
    }).

    when('/categories', {
        templateUrl : 'categories.html',
        controller : 'categoriesController'
    }).

    when('/book', {
        templateUrl : 'book.html',
        controller : 'bookController'
    }).

    when('/parking', {
        templateUrl : 'parking.html',
        controller : 'parkingController'
    }).

    when('/training', {
        templateUrl : 'training.html',
        controller : 'trainingController'
    }).

    when('/myAccount', {
        templateUrl : 'myAccount.html',
        controller : 'myAccountController'
    }).

    otherwise({
        redirectTo : '/home'
    });
} ]);

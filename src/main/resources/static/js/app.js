/**
 * Created by jpulipati on 3/19/17.
 */
'use strict';

angular.module('demo',['ngRoute','demo.controllers']).config(function ($routeProvider,$httpProvider) {
    console.log("Inside app.js Router script");

    //$routeProvider.when('/', {controller: 'attendanceCtrl'});//templateUrl:'/index.html',
    $routeProvider.when('/', {templateUrl: '/attendance.html', controller: 'attendanceCtrl'})
    $routeProvider.when('/attendance.html', {templateUrl: '/attendance.html', controller: 'attendanceCtrl'})
    $routeProvider.when('/budget.html', {templateUrl: '/budget.html', controller: 'budgetCtrl'})
    $routeProvider.when('/ideas.html', {templateUrl: '/ideas.html', controller: 'ideasCtrl'})
    $routeProvider.when('/feedback.html', {templateUrl: '/feedback.html', controller: 'feedbackCtrl'})
    
})
//.run(function($rootScope, $location) {
//        $rootScope.$on( "$routeChangeStart", function(event, next, current) {
//            console.log($rootScope.loggedInUser );
//            if ($rootScope.loggedInUser == null) {
//                // no logged user, redirect to /login
//                if ( next.templateUrl === "/index.html") {
//                } else {
//                    $location.path("/");
//                }
//            }
//        });
//    })
    ;

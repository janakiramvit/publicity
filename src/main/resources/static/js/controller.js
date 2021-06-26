/**
 * Created by jpulipati on 3/23/17.
 */
'use strict';

var app = angular.module('demo.controllers',['ngRoute','moment-picker','angular-loading-bar']);


app.controller('loginCtrl',['$scope','$location','$window','$rootScope', function ($scope, $location, $window,$rootScope) {
    console.log("Inside Login controller");
    $window.onSignIn = function(googleUser){
        var profile = googleUser.getBasicProfile();
        var yourDomain = '@manawholesale.in';
        var domain = profile.getEmail().substr(profile.getEmail().length - yourDomain.length);
        if(domain != yourDomain){
            console.log("Please login with Manawholesale ID");
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log("User Signed out");
            })
        }
        else{
            console.log("Login Success");
            $rootScope.loggedInUser = googleUser.getBasicProfile().getName();
            $window.location.href = '/#/attendance.html';
        }
    }
}]);

app.controller('attendanceCtrl',['$scope','$http','$location',function ($scope,$http,$location) {
    console.log("Inside attendance page controller here");
    $scope.navigate = function(entry){
        console.log("navigating to page - "+ entry)
        $location.url(entry);
    }

    $scope.loading = true;

    $scope.getAttendanceData = function () {
        $http({
            method:'GET',
            url:'/get/attendance',
            headers:{'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data){
            console.log("Attendance Get Success ");
            $scope.attendance_data = data;
        }).error(function(data,status){
            console.log('Attendance Get not success');
        }).finally(function () {
            $scope.loading = false;
        })
    }

    $scope.postData = function () {
      var data = $.param({
          time_sheet:JSON.stringify({
              date:$scope.ctrl.datepicker,
              task:$scope.today_task_model,
              inTime:$scope.ctrl.timepicker1,
              outTime:$scope.ctrl.timepicker2
          })
      });

        //time difference calculation on screen
        var inTime = $scope.ctrl.timepicker1;
        var inTimeParts = inTime.split(':');
        var inHour = parseInt(inTimeParts[0] * 60);
        var inMinute = parseInt(inTimeParts[1]);
        var inTotal = inHour + inMinute;

        var outTime = $scope.ctrl.timepicker2;
        var outTimeParts = outTime.split(':');
        var outHour = parseInt(outTimeParts[0] * 60);
        var outMinute = parseInt(outTimeParts[1]);
        var outTotal = outHour + outMinute;

        var timeDiff = outTotal - inTotal;
        var timeDiffHour = Math.floor(timeDiff/60);
        var timeDiffMinute = timeDiff%60;

        if (timeDiffHour   < 10) {timeDiffHour   = "0"+timeDiffHour;}
        if (timeDiffMinute < 10) {timeDiffMinute = "0"+timeDiffMinute;}

        timeDiff = timeDiffHour + ":" + timeDiffMinute
        $scope.diffHours = timeDiff;
        
      console.log("Post Data for attendance: " + data)
      $http({
          method:'POST',
          url:'/post/attendance',
          headers:{'Content-Type': 'application/x-www-form-urlencoded'},
          data: data,
      }).success(function(data,status){
          var final_status
          if(status === 200){
              final_status = " successful. Now fill data into other forms"
          } else{
              final_status = " not successful. Contact Admin."
          }
          $scope.postStatus = final_status;
          $scope.getTotalHours();
      }).error(function(data,status){
          console.log('Post not success');
          $scope.postStatus = status;
      })
  }

    $scope.getTotalHours = function () {
        $http({
            method:'GET',
            url:'/get/totalHours',
            headers:{'Content-Type':'application/x-www-form-urlencoded'}
        }).success(function (data) {
            console.log("AngularJS: Controller: Get Total Hours successful");
            console.log(data);
            $scope.totalHours = data;
        }).error(function () {
            console.log("AngularJS: Controller: Get Total Hours not successful")
        }).finally(function () {
            $scope.loading = false;
        })
    }

    //duplicate function for image upload
     $scope.postData1 = function (e) {
     //console.log("reached 0 " + file.upload);
     console.log("reached: " + $scope.itemprice );
     console.log("reached: " + $scope.itemname );
     console.log("reached, alert below: " + $scope.upload1 );
      console.log("reached, alert below: " + e);
        var reader = new FileReader();
//        reader.readAsDataURL = function (e) {
//         console.log("reached1, alert below: " + e.target.result);
//        };
       //reader.readAsDataURL(e.target);
       // console.log(reader.readAsDataURL(e.target.files[0])); //TypeError: Cannot read property '0' of undefined
       // console.log(reader.readAsDataURL(e.target)); //TypeError: Failed to execute 'readAsDataURL' on 'FileReader': parameter 1 is not of type 'Blob'.

               console.log(reader.readAsDataURL(e.target.result));

         console.log("end print");
          var data = $.param({
              time_sheet:JSON.stringify({
                  upload1:$scope.ctrl.upload1,
                  upload2:$scope.ctrl.upload2,
                  itemprice:$scope.itemprice,
                  itemname:$scope.itemname,
                  oprice:$scope.oprice
              })
          });

          console.log("Post Data for publicity: " + data)
          $http({
              method:'POST',
              url:'/post/publicity',
              headers:{'Content-Type': 'application/x-www-form-urlencoded'},
              data: data,
          }).success(function(data,status){
              var final_status
              if(status === 200){
                  final_status = " successful. Now fill data into other forms"
              } else{
                  final_status = " not successful. Contact Admin."
              }
              $scope.postStatus = final_status;
              $scope.getTotalHours();
          }).error(function(data,status){
              console.log('Post not success');
              $scope.postStatus = status;
          })
      }


}]);

app.controller('feedbackCtrl',['$scope','$location','$http',function ($scope,$location,$http) {
    console.log("Angular: Feedback: Controller");
    $scope.navigate = function(entry){
        console.log("navigating to page - "+ entry)
        $location.url(entry);
    }
    
    $scope.loading = true;

    $scope.getFeedbackData = function () {
        $http({
            method:'GET',
            url:'/get/feedback',
            headers:{'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data,status){
            console.log("Feedback Get Success " + data[1]);
            $scope.feedback_data = data;
        }).error(function(data,status){
            console.log('Feedback Get not success');
        }).finally(function () {
            $scope.loading = false;
        })
    }

    $scope.postData = function () {
        
        if($scope.phoneNumber === undefined)
            $scope.phoneNumber = "null";
        if($scope.shopName === undefined)
            $scope.shopName = "null";
        if($scope.address === undefined)
            $scope.address = "null"
        if($scope.description === undefined)
            $scope.description = "null"
        if($scope.ctrl.datepicker === undefined)
            $scope.ctrl.datepicker = "null"
        if($scope.customer_id === undefined)
            $scope.customer_id = "null"
        if($scope.customer_name  === undefined)
            $scope.customer_name  = "null"

        var data = $.param({
            feedback:JSON.stringify({
                date:$scope.ctrl.datepicker,
                customerID: $scope.customer_id,
                customerName: $scope.customer_name,
                description:$scope.description,
                shopName:$scope.shopName,
                address:$scope.address,
                phoneNumber:$scope.phoneNumber
            })
        });
        console.log("Feedback Data for post from angular: " + data)
        $http({
            method:'POST',
            url:'/post/feedback',
            headers:{'Content-Type': 'application/x-www-form-urlencoded'},
            //contentType:'application/x-www-form-urlencoded; charset=utf-8',
            data: data,
        }).success(function(data,status){
            var final_status
            if(status === 200){
                final_status = " successful !!"
            } else{
                final_status = " not successful. Contact Admin."
            }
            $scope.postStatus = final_status;
        }).error(function(data,status){
            console.log('Post not success for Feedback');
            $scope.postStatus = status;
        })
    }

}])
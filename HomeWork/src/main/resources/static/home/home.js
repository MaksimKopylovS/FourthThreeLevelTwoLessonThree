angular.module('app').controller('homeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app';

    $scope.tryToReg = function (){
        $http.post('http://localhost:8189/app/reg',$scope.user)
            .then(function successCallback(response){
                $scope.regBool = true
                $scope.regName = response.data.userName;

            }, function errorCallback(response) {
                $scope.regBool = false
                window.alert("Учетная запись уже существует");
            });
    }
});
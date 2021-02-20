angular.module('app').controller('basketController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.showBasket = function(){
        $http({
            url:contextPath +'/basket',
            method: 'GET'
        }).then(function (response){
            $scope.basketProduct = response.data;
        });
    };

    $scope.incrementCount  = function (id){
        console.log(id);
        $http.get(contextPath + '/basket/inc/'+ id)
            .then(function (response){
                $scope.basketProduct = response.data
                var s = response.data, sum = 0;
                for(var i=0; i < s.length; i ++){
                    sum += s[i].sumCost;
                }
                $scope.totalCost = sum;
            });
    }

    $scope.decrimentCount = function (id){
        console.log(id);
        $http.get(contextPath + '/basket/dec/'+ id)
            .then(function (response){
                $scope.basketProduct = response.data
                var s = response.data, sum = 0;
                for(var i=0; i < s.length; i ++){
                    sum += s[i].sumCost;
                }
                $scope.totalCost = sum;
                console.log(response.data);
            });

    }

    $scope.deleteProductFromBasket = function (id){
        $http.get(contextPath + '/basket/del/'+ id)
            .then(function (response){
                $scope.basketProduct = response.data
                var s = response.data, sum = 0;
                for(var i=0; i < s.length; i ++){
                    sum += s[i].sumCost;
                }
                $scope.totalCost = sum;
            });
    }

    $scope.createOrder = function (){
        $scope.order.userName = $localStorage.currentUser.username;
        $http.post(contextPath + '/basket/order', $scope.order)
            .then(function (response){
                $scope.order = null;
                $scope.showOrder = true;
                $localStorage.userOrder = response.data
            });
    }


    $scope.showBasket();
});
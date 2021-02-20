angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/happy';

    $scope.showMyOrders = function () {
        $scope.userOrder = $localStorage.userOrder
    };

    $scope.showMyOrders();
});
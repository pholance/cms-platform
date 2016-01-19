/**
 * @author 蔡迪旻
 * 2016年01月12日
 */
angular.module('app')
    .controller('mass', ['$scope', '$http', function ($scope, $http) {
        'use strict';
        $scope.aritcles = [{}];
        $scope.currentIndex = 0;
        $scope.addAritcle = function () {
            $scope.aritcles.push({});
        };
        $scope.setCurrentAritcle = function (index) {
            $scope.currentIndex = index;
            $scope.aritcle = $scope.aritcles[index];
        };
    }]);
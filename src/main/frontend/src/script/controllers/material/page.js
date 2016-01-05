/**
 * @author 蔡迪旻
 * 2016年01月03日
 */
angular.module('app')
    .controller('page-create', ['$scope', '$http', function ($scope, $http) {
        'use strict';
        $scope.submit = function () {
            if (!$scope.model) {
                $scope.$emit('alterPOP', {
                    type: 4,
                    title: '无法提交',
                    text: '未发现可提交的内容。'
                });
            }
            if (!$scope.model.title) {
                $scope.$emit('alterPOP', {
                    type: 3,
                    title: '验证失败',
                    text: '必须设置标题。'
                });
            }
            if (!$scope.model.content) {
                $scope.$emit('alterPOP', {
                    type: 3,
                    title: '验证失败',
                    text: '没有内容。'
                });
            }
            $http.post('/api/material/create/page', $scope.model).then(function (data) {
                $scope.$emit('serverResponsed', data.data);

            });
        };
    }]);
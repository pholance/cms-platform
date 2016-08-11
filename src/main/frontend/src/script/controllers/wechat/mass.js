/**
 * @author 蔡迪旻
 * 2016年01月12日
 */
angular.module('app')
    .controller('mass', ['$scope', '$uibModal', function ($scope, $uibModal) {
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
        $scope.showImgSelecter = function () {
            $uibModal.open({
                templateUrl: '/material/block/picture-view.html',
                size: 'lg',
                controller: 'imageView',
                windowClass: 'margin-top-10p',
                resolve: {
                    aritcle: function () {
                        return $scope.aritcle;
                    }
                }
            });
        };
    }]);
angular.module('app')
    .controller('imageView', ['$scope', '$http', 'ipCookie', '$uibModalInstance', 'aritcle',
        function ($scope, $http, ipCookie, $uibModalInstance, aritcle) {
            'use strict';
            $scope.aritcle = aritcle;
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.selected = -1;

            var pictures;
            $scope.currentPage = 1;
            $scope.pageSize = ipCookie('material.picture.edit-size');
            if (!$scope.pageSize) {
                $scope.pageSize = 12;
            }
            $scope.selectImage = function (index) {
                $scope.selected = index;
            };
            $http.get('/api/material/images').then(function (response) {
                pictures = $scope.pictures = response.data;
                $scope.currentGroup = pictures;
                $scope.groups = [];
                var group;
                pictures.forEach(function (picture) {
                    if (!picture.group || !picture.group.name) {
                        return;
                    }
                    for (var i = 0; i < $scope.groups.length; i++) {
                        group = $scope.groups[i];
                        if (picture.group.id === group.id) {
                            group.pictures.push(picture);
                            return;
                        }
                    }
                    group = picture.group;
                    group.pictures = [];
                    group.pictures.push(picture);
                    $scope.groups.push(group);
                });
            });
            $scope.currentGroupIndex = -1;
            $scope.displayGroup = function (index) {
                $scope.currentGroupIndex = index;
                $scope.currentPage = 1;
                if (index < 0) {
                    $scope.currentGroup = pictures;
                } else {
                    $scope.currentGroup = $scope.groups[index].pictures;
                }
            };
            $scope.$watch('pageSize', function (newValue) {
                ipCookie('material.picture.edit-size', newValue, {expires: 9999})
            });
            $scope.submitSelected = function () {
                //todo:图片消息后台及提交
            };

        }]);
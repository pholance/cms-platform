/**
 * @author 蔡迪旻
 * 2016年01月21日
 */
angular.module('app')
    .controller('sutra-edit', ['$scope', '$http', function ($scope, $http) {
        'use strict';
        var currentIndex = 0;

        /**
         * 根据给出的节点数组和当前索引，得到这个索引的子节点模型
         * @param aritcles
         * @returns {Array}
         */
        function paserChildren(aritcles) {
            var nodes = [];
            var aritcle = aritcles[currentIndex];
            //noinspection JSUnresolvedVariable
            for (var i = currentIndex + 1; i < aritcles.length; i++) {
                var item = aritcles[i];
                //noinspection JSUnresolvedVariable
                if ((aritcle.leftValue < item.leftValue) && (aritcle.rightValue > item.rightValue)) {
                    currentIndex = i;
                    //noinspection JSUnresolvedVariable
                    if (item.rightValue - item.leftValue > 1) {
                        item.nodes = paserChildren(aritcles);
                        i = currentIndex;
                    }
                    nodes.push(item);
                }
            }
            return nodes;
        }

        $http.get('/api/sutra/infos').then(function (data) {
            $scope.aritcles = [];
            for (currentIndex = 0; currentIndex < data.data.length; currentIndex++) {
                var aritcle = data.data[currentIndex];
                //noinspection JSUnresolvedVariable
                if (aritcle.rightValue - aritcle.leftValue > 1) {
                    aritcle.nodes = paserChildren(data.data);
                }
                $scope.aritcles.push(aritcle);
            }
        });

        $scope.setCurrentAritcle = function (node) {
            $scope.aritcle = node;
            $http.get('/api/sutra/info/'+node.id).then(function (data) {
                $scope.aritcle = data.data;
            });
        };
        $scope.submit = function () {
            if (!$scope.aritcle) {
                return;
            }
            if ($scope.isNew) {
                $http.post('/api/sutra/create').then(function (data) {
                    $scope.$emit('serverResponse', data.data);
                });
            } else {
                $http.put('/api/sutra/update').then(function (data) {
                    $scope.$emit('serverResponse', data.data);
                });
            }
        };
    }])
;
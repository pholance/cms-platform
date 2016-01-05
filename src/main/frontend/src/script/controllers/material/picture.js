app.controller('material-picture', ['$scope', '$http', 'ipCookie', function ($scope, $http, ipCookie) {
    'use strict';
    var pictures;
    $scope.currentPage = 1;
    $scope.pageSize = ipCookie('material.picture.view-size');
    if (!$scope.pageSize) {
        $scope.pageSize = 10;
    }
    $http.get('/api/material/images').then(function (response) {
        pictures = $scope.pictures = response.data;
        $scope.currentGroup = pictures;
        $scope.groups = [];
        pictures.forEach(function (picture) {
            var group;
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
        ipCookie('material.picture.view-size', newValue, {expires: 9999});
    });
}]);
app.controller('image-create', ['$scope', '$http', 'FileUploader', '$state', function ($scope, $http, FileUploader, $state) {
    'use strict';

    var uploader = $scope.uploader = new FileUploader({
        url: '/api/material/upload/image',
        filters: [{
            name: 'imgFilter',
            fn: function (item) {
                var filename = item.name;
                var pattern = /^.+\.(gif|jpg|jpeg|bmp|png)$/i;
                var isImage = pattern.test(filename);
                if (isImage) {
                    return true;
                } else {
                    $scope.$emit('alterPOP', {
                        type: 3,
                        title: '只能上传图片',
                        text: '请点击按钮重新选择图片'
                    });
                    return false;
                }
            }
        }]
    });

    $scope.uploadImg = function () {
        uploader.uploadAll();
    };

    uploader.onAfterAddingFile = function (item) {
        if (uploader.queue.length > 1) {
            var file = item._file;
            uploader.clearQueue();
            uploader.addToQueue(file);
        }
    };

    uploader.onBeforeUploadItem = function (item) {
        item.formData.push({
            title: $scope.title
        });
    };

    uploader.onSuccessItem = function (item, response) {
        $scope.$emit('serverResponsed', response);
        $state.reload();
    };

}]);
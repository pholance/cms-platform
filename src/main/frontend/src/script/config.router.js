/**
 * Config for the router
 */
angular.module('app')
    .run(['$rootScope', '$state', '$stateParams',
        function ($rootScope, $state, $stateParams) {
            'use strict';
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }])
    .config(['$stateProvider', '$urlRouterProvider', 'JQ_CONFIG',
        function ($stateProvider, $urlRouterProvider, JQ_CONFIG) {
            'use strict';
            $urlRouterProvider.otherwise('/dashboard');
            $stateProvider
                .state('home', {
                    url: '/dashboard',
                    templateUrl: '/platform/dashborad.html'
                })
                .state('site', {
                    url: '/site',
                    templateUrl: '/platform/tpl/sidebar/site.html'
                })
                .state('site.video-list', {
                    url: '/video/list',
                    templateUrl: '/video/info.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('angular-dataTables').then(function () {
                                return $ocLazyLoad.load('/script/controllers/video/info.js');
                            });
                        }]
                    },
                    controller: 'infoController'
                })
                .state('site.video-clip', {
                    url: '/clipInfo/:id',
                    templateUrl: '/video/clip_info.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('echarts').then(function () {
                                return $ocLazyLoad.load('/script/directives/clip-info-chart.js').then(function () {
                                    return $ocLazyLoad.load('/script/controllers/video/clip.js');
                                });
                            });
                        }]
                    },
                    controller: 'clipController'
                })
                .state('site.video-create', {
                    url: '/video/create',
                    templateUrl: '/video/create.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load([
                                '/script/controllers/video/column.js',
                                '/script/controllers/video/create.js'
                            ]);
                        }]
                    },
                    controller: 'createController'
                })
                .state('site.video-manager', {
                    url: '/video/manager',
                    templateUrl: '/video/manager.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('/script/controllers/video/manager.js');
                        }]
                    },
                    controller: 'managerController'
                })
                .state('site.video-edit', {
                    url: '/video/edit/:id',
                    templateUrl: '/video/edit.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load([
                                '/script/controllers/video/column.js',
                                '/script/controllers/video/tag.js',
                                '/script/controllers/video/edit.js'
                            ]);
                        }]
                    },
                    controller: 'editController'
                })
                .state('site.video-publish', {
                    url: '/video/publish',
                    templateUrl: '/video/publish.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('/script/controllers/video/publish.js');
                        }]
                    },
                    controller: 'publishController'
                })
                .state('site.goods-info', {
                    url: '/goods/info',
                    templateUrl: '/goods/info.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('angular-dataTables').then(function () {
                                return $ocLazyLoad.load('/script/controllers/goods/info.js');
                            });
                        }]
                    },
                    controller: 'infoController'
                })
                .state('site.goods-process', {
                    url: '/goods/process',
                    templateUrl: '/goods/process.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('angular-dataTables').then(function () {
                                return $ocLazyLoad.load('/script/controllers/goods/process.js');
                            });
                        }]
                    },
                    controller: 'processController'
                })
                .state('site.goods-trash', {
                    url: '/goods/trash',
                    templateUrl: '/goods/trash.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('angular-dataTables').then(function () {
                                return $ocLazyLoad.load('/script/controllers/goods/trash.js');
                            });
                        }]
                    },
                    controller: 'trashController'
                })
                .state('wechat', {
                    url: '/wechat',
                    templateUrl: '/platform/tpl/sidebar/wechat.html'
                })
                .state('wechat.material-image', {
                    abstract: true,
                    url: '/material/image',
                    template: '<div ui-view></div>'
                })
                .state('wechat.material-image.list', {
                    url: '/list',
                    templateUrl: '/material/image/list.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('/script/controllers/material/picture.js');
                        }]
                    },
                    controller: 'material-picture'
                })
                .state('wechat.material-image.create', {
                    url: '/create',
                    templateUrl: '/material/image/create.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('angularFileUpload').then(function () {
                                return $ocLazyLoad.load('/script/controllers/material/picture.js');
                            });
                        }]
                    },
                    controller: 'image-create'
                })

                .state('wechat.material-audio', {
                    url: '/material/audio',
                    templateUrl: '/material/audio.html',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load([
                                    'ngSanitize',
                                    'com.2fdevs.videogular',
                                    'com.2fdevs.videogular.plugins.controls',
                                    'com.2fdevs.videogular.plugins.buffering',
                                    'com.2fdevs.videogular.plugins.overlayplay'
                                ]).then(function () {
                                        return $ocLazyLoad.load('/script/controllers/material/audio.js');
                                    }
                                );
                            }]
                    },
                    controller: 'material-audio'
                })
                .state('wechat.material-video', {
                    url: '/material/video',
                    templateUrl: '/material/video.html',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load([
                                    'ngSanitize',
                                    'com.2fdevs.videogular',
                                    'com.2fdevs.videogular.plugins.controls',
                                    'com.2fdevs.videogular.plugins.buffering',
                                    'com.2fdevs.videogular.plugins.overlayplay',
                                    'com.2fdevs.videogular.plugins.imaads'
                                ]).then(function () {
                                    return $ocLazyLoad.load('/script/controllers/material/video.js');
                                });
                            }]
                    },
                    controller: 'material-video'
                })
                .state('wechat.material-page', {
                    abstract: true,
                    url: '/material/page',
                    template: '<div ui-view></div>',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('UEditor').then(function () {
                                return $ocLazyLoad.load('angularUEditor').then(function () {
                                    return $ocLazyLoad.load('/script/controllers/material/page.js');
                                });
                            });
                        }]
                    }
                })
                .state('wechat.material-page.create', {
                    url: '/create',
                    templateUrl: '/material/page/create.html',
                    controller: 'page-create'
                })
                .state('wechat.material-page.list', {
                    url: '/list',
                    templateUrl: '/material/page/list.html',
                    controller: 'page-list'
                })
                .state('wechat.material-aritcle', {
                    url: '/material/aritcle',
                    templateUrl: '/material/aritcle.html'
                })
                .state('wechat.replyRule', {
                    url: '/reply-rule',
                    templateUrl: '/wechat/reply_rule.html',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load([
                                '/script/controllers/wechat/replyRule.js',
                                '/script/directives/message-editor.js']);
                        }]
                    },
                    controller: 'wechat-replyRule'
                })
                .state('wechat.message-mass', {
                    abstract: true,
                    url: '/message',
                    template: '<div ui-view></div>',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('UEditor').then(function () {
                                return $ocLazyLoad.load('angularUEditor').then(function () {
                                    return $ocLazyLoad.load('/script/controllers/wechat/mass.js');
                                });
                            });
                        }]
                    }
                })
                .state('wechat.message-mass.sends', {
                    url: '/mass',
                    templateUrl: '/wechat/mass_message.html',
                    controller: 'mass'
                })
            ;
        }])
;

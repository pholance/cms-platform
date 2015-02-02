<%-- 
    Document   : info
    Created on : 2015-1-11, 18:50:47
    Author     : 蔡迪旻
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>视频信息编辑</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="/resources/css/metro-bootstrap.css">
        <link rel="stylesheet" href="/resources/css/iconFont.css">
        <link rel="stylesheet" href="/resources/css/app.css">
        <style>
            table td {
              vertical-align: baseline;
            }
        </style>
    </head>
    <body class="metro">
        <div id="navigate">
            <h1 class="no-margin"><a class="text-muted" href="javascript:window.history.back(); "><span class="icon-arrow-left-3 smaller on-right"></span></a></h1>
            <div class="breadcrumbs">
                <ul>
                    <li><a href="/platform"><span class="icon-home icon"></span>平台</a>
                    </li>
                    <li><a>视频管理</a>
                    </li>
                    <li><a href="../manager">信息维护</a>
                    <li class="active"><a>信息编辑</a>
                    </li>
                    <li></li>
                </ul>
            </div>
        </div>
        <hr class="ntm">
        <table class="center-block" style="width: 770px" cellpadding="10">
            <tr>
                <td><strong>视频标题：</strong></td><td colspan="7"><div class="input-control text"><input type="text" id="title" name="title"></div></td>
            </tr>
            <tr>
                <td><strong>记录标识：</strong></td><td id="itemId"></td>
                <td><strong>视频编号：</strong></td><td id="file" class="text-muted"></td>
                <td><strong>当前状态：</strong></td><td id="status" class="text-muted"></td>
                <td><strong>播放时长：</strong></td><td id="dur" class="text-muted"></td>
            </tr>
            <tr>
                <td><strong>发布序号：</strong></td><td><div class="input-control text size1"><input type="text" id="sort" name="sort"></div></td>
                <td><strong>分级信息：</strong></td><td><div class="input-control text size1"><input type="text" id="grade" name="grade"></div></td>
                <td class="text-right"><strong>推荐度：</strong></td><td><div class="input-control text size1"><input type="text" id="recommend" name="recommend"></div></td>
                <td><strong>拍摄日期：</strong></td><td><div class="input-control text size2"><input type="text" id="shoot" name="shoot"><button class="btn-date"></button></div></td>
            </tr>
            <tr>
                <td><strong>发布时间：</strong></td><td id="pub" class="text-muted" colspan="2"></td>
                <td class="text-right"><strong>所属栏目：</strong></td><td class="text-muted" colspan="4"><button id="col" class="btn-add"><span class="icon-plus-2"></span></button></td>
            </tr>
            <tr>
                <td class="text-right"><strong>标签：</strong></td><td class="text-muted" colspan="7"><button id="tag" class="btn-add"><span class="icon-plus-2"></span></button></td>
            </tr>
            <tr>
                <td style="vertical-align: top"><strong>视频描述：</strong></td><td colspan="3"><div class="input-control textarea size3"><textarea id="desc" name="desciption"></textarea></div></td>
                <td class="text-right" style="vertical-align: top"><strong>备注：</strong></td><td colspan="3"><div class="input-control textarea size3"><textarea id="note" name="note"></textarea></div></td>
            </tr>
            <tr>
                <td style="vertical-align: top"><strong>文件信息：</strong></td><td colspan="3">
                    <table class="table bordered" style="vertical-align: top">
                        <thead>
                            <tr>
                                <td>清晰度</td>
                                <td>文件大小</td>
                            </tr>
                        </thead>
                        <tbody id="ext" class="text-muted"></tbody>
                    </table>
                </td>
                <td colspan="4" style="vertical-align: top">
                    <div id="help">
                        <div>填写提示：</div>
                        <div class="col-sm-4">
                            <p class="text-warning">推荐度为0即为不推荐，推荐度高<br>的视频排列会更靠前。</p>
                            <p class="text-warning">同理，发布序号为0即为不排序。</p>
                            <p class="text-info">您并不能任意修改所有信息。某些信息<br>必须通过特定的工作流程才会被更新。<br>如：状态、更新时间等。</p>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <hr class="ntm">
        <div class="center-block" style="width: 200px;margin-bottom: 50px">
            <button class="primary large">提交</button>
        </div>
        <script src="/resources/js/jquery/jquery.min.js"></script>
        <script src="/resources/js/jquery/jquery.widget.min.js"></script>
        <script src="/resources/js/metro.min.js"></script>
        <script src="/resources/js/jquery/jquery.url.js"></script>
        <script src="/resources/js/video/edit.js"></script>
    </body>
</html>

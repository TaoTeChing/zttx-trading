<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修-商品分类管理</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />
    <link href="${res}/styles/market/brandmarket/brand_edit/kuang.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/market/brandmarket/brand_edit/btnico.css" rel="stylesheet" type="text/css" />
    <style>
        <!--
        html { overflow: auto; }
        .subfirst span.sub-down, .subfirst span.sub-bottom { color: #000; }
        .sublast span.sub-up, .sublast span.sub-top { color: #000; }
        .subnormal span.sub-down, .subnormal span.sub-up { color: #000; }
        .subonly { }
        .subfirst span.main-down, .subfirst span.main-bottom { color: #000; }
        .sublast span.main-up, .sublast span.main-top { color: #000; }
        .subnormal span.main-down, .subnormal span.main-up, .subnormal span.main-top, .subnormal span.main-bottom { color: #000; }
        -->
    </style>
</head>
<body>
    <div class="k-class" style="width: 98%; margin: 15px auto">
        <div class="k-selectbar btn-group">
            <div class="k-selall btn">
                <input type="checkbox" name="allche" class="k-sall f-l" style="margin: 4px 5px 0 0" id="k-sall" /><label for="k-sall"><span class="f-l">全选</span></label>
            </div>
            <div class="k-delall btn">批量删除</div>
            <div class="addmiantree btn">添加主分类</div>
            <div class="k-openall btn">展开全部</div>
            <div class="	k-closeall btn disabled">收起全部</div>
            <div class="btn"><i class="icon-question-sign"></i>使用帮助</div>
            <div class="btn btn-danger" id="saveall">保存全部</div>
        </div>
        <table class="table table-bordered table-hover">
            <tbody>
                <tr>
                    <th></th>
                    <th>分类名称</th>
                    <th>分类图片</th>
                    <th>移动</th>
                    <th>默认展开</th>
                    <th>创建时间</th>
                    <th>分类类型</th>
                    <th>操作</th>
                </tr>
            </tbody>
            <tbody class="tbody1">
                <tr class="maintree">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="" /></td>
                    <td style="width: 220px" class="td-001"><span id="trplus" class="plus"></span>
                        <input type="text" value="" class="k-tbtxt"  /></td>
                    <td style="width: 140px" class="td-002"><a href="#" title="" target="">编辑</a> <a href="#" title="" target="">删除</a></td>
                    <td class="td-003"><span class="main-up">上</span> <span class="main-down">下</span></td>
                    <td class="td-004"><span>开关</span></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>自动分类</span></td>
                    <td class="td-007"><a class="main-remove">删除</a> <a href="javascript:viod()" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙11" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙1" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙12" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree addsubtree">
                    <td class="td-000"></td>
                    <td class="td-001">---
                        <input type="button" value="添加子分类" class="k-tbbtnadd btn btn-warning" /></td>
                    <td class="td-002"></td>
                    <td class="td-003"></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"></td>
                    <td class="td-007"></td>
                </tr>
            </tbody>
            <tbody class="tbody2">
                <tr class="maintree">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="衣服2" /></td>
                    <td style="width: 220px" class="td-001"><span id="trplus" class="plus"></span>
                        <input type="text" value="" class="k-tbtxt"  /></td>
                    <td style="width: 140px" class="td-002"><a href="#" title="" target="">编辑</a> <a href="#" title="" target="">删除</a></td>
                    <td class="td-003"><span class="main-up">上</span> <span class="main-down">下</span></td>
                    <td class="td-004"><span>开关</span></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>自动分类</span></td>
                    <td class="td-007"><a class="main-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙2" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙21" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙2" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙22" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙2" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙22" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree addsubtree">
                    <td class="td-000"></td>
                    <td class="td-001">---
                        <input type="button" value="添加子分类" class="k-tbbtnadd btn btn-warning" /></td>
                    <td class="td-002"></td>
                    <td class="td-003"></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"></td>
                    <td class="td-007"></td>
                </tr>
            </tbody>
            <tbody class="tbody3">
                <tr class="maintree">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="衣服3" /></td>
                    <td style="width: 220px" class="td-001"><span id="trplus" class="plus"></span>
                        <input type="text" value="衣服" class="k-tbtxt"  /></td>
                    <td style="width: 140px" class="td-002"><a href="#" title="" target="">编辑</a> <a href="#" title="" target="">删除</a></td>
                    <td class="td-003"><span class="main-up">上</span> <span class="main-down">下</span></td>
                    <td class="td-004"><span>开关</span></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>自动分类</span></td>
                    <td class="td-007"><a class="main-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙3" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙31" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree subtreemove">
                    <td class="td-000">
                        <input type="checkbox" name="choice" class="f-l" style="margin: 4px 5px 0 0" value="碎花裙3" /></td>
                    <td class="td-001">---
                        <input type="text" value="碎花裙32" class="k-tbtxt"  /></td>
                    <td class="td-002">+ <span>添加图片</span></td>
                    <td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"><span>手动分类</span></td>
                    <td class="td-007"><a class="sub-remove">删除</a> <a href="#" title="" target="">查看</a></td>
                </tr>
                <tr class="subtree addsubtree">
                    <td class="td-000"></td>
                    <td class="td-001">---
                        <input type="button" value="添加子分类" class="k-tbbtnadd btn btn-warning" /></td>
                    <td class="td-002"></td>
                    <td class="td-003"></td>
                    <td class="td-004"></td>
                    <td class="td-005"><span>2014-01-24</span></td>
                    <td class="td-006"></td>
                    <td class="td-007"></td>
                </tr>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_foot.jsp" />
</body>
</html>

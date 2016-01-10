<%@ page import="com.zttx.web.consts.DecorateHeaderConst" %>
<%@ page import="com.zttx.web.consts.DecorateMenuConst" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:set var="SHOWCATE_DEFAULT" value="<%=DecorateHeaderConst.SHOWCATE_DEFAULT%>"/>
<c:set var="SHOWCATE_CUSTOM" value="<%=DecorateHeaderConst.SHOWCATE_CUSTOM%>"/>
<c:set var="SHOWCATE_MARKET" value="<%=DecorateHeaderConst.SHOWCATE_MARKET%>"/>
<%--<c:set var="MENU_SYSTEM" value="<%=DecorateMenuConst.MENU_SYSTEM%>"/>--%>

<div class="header-nav" id="header-nav">
    <div class="header-navcen" id="header-navcen">
        <div class="header-cen" id="headerhover">
            <div class="navbar">
                <div class="btn-group">
                    <span class="edit btn btn-small btn-inverse cover-btn-edit">编辑</span><span class="add btn btn-small btn-inverse cover-btn-plus" data-moddle="0">添加模块</span></div>
            </div>
            <div id="headnav-defualt" <c:if test="${dHeader.showCate!=null && dHeader.showCate!=SHOWCATE_DEFAULT}"> style="display: none;" </c:if> >
                <div class="logo">
                    <h2>${dHeader.comName}</h2>
                    <h3>${dHeader.mainDeal}</h3>
                </div>
            </div>
            <div id="headnav-custom" <c:if test="${dHeader.showCate!=SHOWCATE_CUSTOM}"> style="display: none;" </c:if> >
                <%--<div class="navbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px">
                        <span class="edit btn btn-small btn-inverse"><i class="icon-pencil icon-white"></i>编辑</span>
                        <span class="delete btn btn-small btn-inverse"><i class="icon-trash icon-white"></i>删除</span>
                        <span class="add btn btn-small btn-inverse" data-moddle="0">添加模块</span>
                    </div>
                </div>--%>
                <c:if test="${dHeader.showCate==SHOWCATE_CUSTOM}">
                    <style id="custom_style">
                        .header-nav {
                        background: #fff;
                        background-image: none;
                    }

                    .header-cen {
                        background: #fff;
                        height: 100%;
                    }</style>
                </c:if>

                <div class="customContent">${headerText}</div>
            </div>
            <div id="headnav-market" <c:if test="${dHeader.showCate!=SHOWCATE_MARKET}"> style="display: none;" </c:if>>

            </div>
        </div>
        <div class="nav-cen" style="height: 30px" id="headerhover1">
            <div class="navbar">
                <div class="btn-group">
                    <span class="navedit btn btn-small btn-inverse cover-btn-edit">编辑</span><span class="navadd btn btn-small btn-inverse cover-btn-plus" data-moddle="0">添加模块</span>
                </div>
            </div>
            <ul class="menu-list" id="menu-list">
                <li class="menu"><a class="menu-link" href="/index/${brandsId}" title="" target=""><span class="title">品牌首页</span></a></li>
                <c:if test="${allMenus!=null}">
                    <c:forEach items="${allMenus}" var="menu">
                        <c:if test="${menu.s=='1'}">
                            <li class="menu"><a class="menu-link" href="${menu.url}" title="${menu.n}" ><span class="title">${menu.n}</span></a></li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </ul>
            <%--<span class="address font f-r">店铺地址：http://duocare.8637.com 复制</span>--%>
        </div>
        <div class="biaochi"></div>
    </div>
</div>
<!--// header end-->

<!-- header编辑区域 2014-03-24 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-header-boxes">
        <div class="ui-head">
            <h3 class="">编辑店铺招牌</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:;"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li <c:if test="${dHeader.showCate==null||dHeader.showCate==SHOWCATE_DEFAULT}"> class="current" </c:if>>默认店铺招牌</li>
                <li <c:if test="${dHeader.showCate==SHOWCATE_CUSTOM}"> class="current" </c:if> >自定义店铺招牌</li>
                <li <c:if test="${dHeader.showCate==SHOWCATE_MARKET}"> class="current" </c:if> >招牌超市</li>
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact <c:if test="${dHeader.showCate!=null && dHeader.showCate!=SHOWCATE_DEFAULT}"> hide</c:if>">
                    <form:form cssClass="ui-form edit-common-form">
                        <input type="hidden" name="showCate" value="${SHOWCATE_DEFAULT}">
                        <div class="ui-form-item">
                            <label class="ui-label mt10">店铺logo设置：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads font">上传</div>
                                    <input class="input_file js-logoimg" type="file" name="photo" id="logoUploadImg" data-img="logoNameImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="logoName" id="logoName" value="${dHeader.logoName}">
                                    <img id="logoNameImg" src="${res}${dHeader.logoName}" alt="logo" class="js-logoimgset" width="30" height="30" >
                                </div>
                                <div class="edit-form-toshow inline-block font f-l">
                                    显示：<input type="checkbox" name="showLogo" <c:if test="${dHeader.showLogo}"> checked="checked" </c:if> class="ui-checkbox js-logoimg-tg" />
                                </div>
                                <div class="edit-form-toshow inline-block font f-l">高100PX 宽50PX</div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">店铺名称：</label>
                            <input type="text" class="ui-input js-logoname" style="width:190px" name="comName" value="${dHeader.comName}" />
                            <div class="edit-form-toshow inline-block" style="margin-top:0">
                                显示：<input type="checkbox" class="ui-checkbox js-logoname-tg"  name="showName" <c:if test="${dHeader.showName}"> checked="checked" </c:if>  />
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">主营产品：</label>
                            <input type="text" class="ui-input js-mainpro" style="width:250px" name="mainDeal" value="${dHeader.mainDeal}" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">店名色彩大小：</label>
                            <div class="inline-block font">
                                字体大小：
                                <select name="nameSize" class="js-header-size">
                                    <option <c:if test="${dHeader.nameSize=='12px'}"> selected="selected" </c:if> value="12px">12px</option>
                                    <option <c:if test="${dHeader.nameSize=='14px'}"> selected="selected" </c:if> value="14px">14px</option>
                                    <option <c:if test="${dHeader.nameSize=='16px'}"> selected="selected" </c:if> value="16px">16px</option>
                                    <option <c:if test="${dHeader.nameSize=='18px'}"> selected="selected" </c:if> value="18px">18px</option>
                                    <option <c:if test="${dHeader.nameSize=='20px'}"> selected="selected" </c:if> value="20px">20px</option>
                                </select>
                                字体颜色：
                                <input type="text" class="ui-input js-header-color js-colorpicker" style="width:50px" name="nameColor" value="${dHeader.nameColor}" />
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label mt10">通栏外背景图：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads inline-block font">上传</div>
                                    <input class="input_file" type="file" name="photo" id="headUrlUpload" data-img="headerUrlImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="outBackUrl" value="${dHeader.outBackUrl}"/>
                                    <img id="headerUrlImg" class="js-headall" src="${res}${dHeader.outBackUrl}" width="30" height="30" alt="通栏外背景图">
                                </div>
                                <div class="edit-form-toshow inline-block font f-l">高150PX 宽100%</div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label mt10">通栏内背景图：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads inline-block font">上传</div>
                                    <input class="input_file" type="file" name="photo" id="neibeiUpload" data-img="neibeiUploadImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="inBackUrl" value="${dHeader.inBackUrl}"/>
                                    <img id="neibeiUploadImg" class="js-headinner" src="${res}${dHeader.inBackUrl}" width="30" height="30" alt="通栏内背景图">
                                </div>
                                <div class="edit-form-toshow inline-block font">高120PX 宽1200PX</div>
                            </div>
                        </div>
                        <div class="ui-bottom">
                            <a href="javascript:;" class="edit-common-conserve js-header-save">保存</a>
                            <a href="javascript:;" class="edit-common-cancel js-header-cansle">取消</a>
                        </div>
                    </form:form>
                </div>
                <div class="jsedit-contact <c:if test="${dHeader.showCate!=SHOWCATE_CUSTOM}"> hide </c:if> ">
                    <div class="tips font">
                        自定义模式会覆盖前面所有默认招牌设置
                    </div>
                    <form:form cssClass="ui-form edit-common-form">
                        <input type="hidden" name="showCate" value="${SHOWCATE_CUSTOM}">
                        <textarea id="myEditor2" name="headerText" >${dHeader.headerText}</textarea>
                        <div class="ui-bottom">
                            <a href="javascript:;" class="edit-common-conserve js-editcove-save">保存</a>
                            <a href="javascript:;" class="edit-common-cancel js-editcove-cansle">取消</a>
                        </div>
                    </form:form>
                </div>
                <div class="jsedit-contact <c:if test="${dHeader.showCate!=SHOWCATE_MARKET}"> hide </c:if>">
                    <form:form cssClass="ui-form edit-common-form">
                        <input type="hidden" name="showCate" value="${SHOWCATE_MARKET}">
                        <div class="tips font">
                            店铺招牌位置预留
                        </div>
                        <div class="ui-bottom">
                            <a href="javascript:;" class="edit-common-conserve js-header-save">保存</a>
                            <a href="javascript:;" class="edit-common-cancel js-header-cansle">取消</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- nav编辑区域 2014-03-24 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-nav-boxes">
        <div class="ui-head">
            <h3 class="">编辑店铺导航</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:;"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li class="current">店铺导航设置</li>
                <li>店铺导航管理</li><!-- 第一版此功能暂不实现 -->
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact">
                    <form class="ui-form edit-common-form nav-edit-form" id="navForm">
                        <div class="ui-form-item">
                            <label class="ui-label mt10">导航背景设置默认：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads inline-block font">上传</div>
                                    <input class="input_file" type="file" name="photo" id="navDefaultUrlUpload" data-img="navDefaultUrlImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="navDefaultUrl" value="${dHeader.navDefaultUrl}"/>
                                    <img id="navDefaultUrlImg" class="js-navbg" src="${res}${dHeader.navDefaultUrl}" width="30" height="30" alt="导航背景设置默认">
                                </div>
                                <div class="edit-form-toshow inline-block font f-l">背景颜色：<input name="navDefaultColor" type="text"
                                    class="ui-input js-nav-bgcolor js-colorpicker" style="width:59px" value="${dHeader.navDefaultColor}"/></div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label mt10">导航背景设置变化：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads inline-block font">上传</div>
                                    <input class="input_file" type="file" name="photo" id="navChangeUrlUpload" data-img="navChangeUrlImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="navChangeUrl" value="${dHeader.navChangeUrl}"/>
                                    <img id="navChangeUrlImg" class="js-navhoverbg" src="${res}${dHeader.navChangeUrl}" width="30" height="30" alt="">
                                </div>
                                <div class="edit-form-toshow inline-block font f-l">背景颜色：<input name="navChangeColor" type="text"
                                      class="ui-input js-nav-hoverbgcolor js-colorpicker" style="width:59px" value="${dHeader.navChangeColor}"/></div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">导航选中背景颜色：</label>
                            <input type="text" class="ui-input js-navselect-color js-colorpicker" style="width:59px" name="navSelectColor" value="${dHeader.navSelectColor}" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">导航文字色彩：</label>
                            <div class="inline-block font">
                                默认颜色：<input type="text" class="ui-input js-nav-color js-colorpicker" style="width:59px" name="navDefaultFont" value="${dHeader.navDefaultFont}" />
                                移动变化：<input type="text" class="ui-input js-nav-hovercolor js-colorpicker" style="width:59px" name="navChangeFont" value="${dHeader.navChangeFont}" />
                            </div>
                        </div>
                        <div style="display: none" id="menuData"></div>
                        <%--<div style="display: none" id="delData"></div>&lt;%&ndash;选择删除或者隐藏的id&ndash;%&gt;--%>
                    </form>
                    <div class="ui-bottom">
                        <a href="javascript:;" class="edit-common-conserve js-editnav-save">保存</a>
                        <a href="javascript:;" class="edit-common-cancel js-editnav-cansle">取消</a>
                    </div>
                </div>
                <div class="jsedit-contact hide">
                    <div class="js-nav-all">
                        <div class="nav-edit-area font">
                            <ul>
                                <li>导航名称</li>
                                <li>导航状态</li>
                                <li>导航管理模式</li>
                            </ul>
                        </div>
                        <ul class="nav-edit-ul js_scllobox clear font" id="navUl">
                            <li class="menu"><a class="menu-link" href="/index/${brandsId}" title="" target="">品牌首页</a></li>
                            <c:if test="${allMenus!=null}">
                                <c:forEach items="${allMenus}" var="menu">
                                    <li class="menu movenav" data-id='${menu.id}'>
                                        <a class="menu-link" href="${menu.url}" title="${menu.n}" >${menu.n}</a>
                                        <div class="nav-edit-listate"><c:if test="${menu.s=='1'}">显示</c:if><c:if test="${menu.s=='0'}">隐藏</c:if></div>
                                        <div class="btn-group f-r">
                                            <span class="btn btn-small" id="up">上移</span>
                                            <span class="btn btn-small" id="down">下移</span>
                                            <c:choose>
                                                <c:when test="${menu.id!=null and menu.id!=''}">
                                                    <span class="btn btn-small navremove" id="remove"><c:if test="${menu.s=='0'}">显示</c:if><c:if test="${menu.s=='1'}">隐藏</c:if></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="btn btn-small addnavedit" id="addnavedit">编辑</span>
                                                    <span class="btn btn-small navremove" id="remove">删除</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <div class="tips font" style="">* 建议导航不要超过十项内容(如果导航字数少，可适量增加)</div>
                        <span class="btn btn-small nav-edit-addbtn" id="addnav">添加</span>
                    </div>

                   <%-- <form class="ui-form mt10 hide js-addnavbox">
                        <div class="ui-form-item">
                            链接名称：
                            <input class="ui-input" />
                            <span>显示：</span><input type="checkbox" class="ui-checkbox" />
                        </div>
                        <div class="ui-form-item">
                            链接网址：
                            <input class="ui-input" style="width: 245px;" />
                        </div>
                        <div class="ui-form-item">
                            <span>* 建议导航不要超过十项内容(如果导航字数少，可适量增加)</span>
                        </div>
                    </form>

                    <form class="ui-form mt10 hide js-editnavbox">
                        <div class="ui-form-item">
                            链接名称：
                            <input class="ui-input" placeholder="品牌招募书" />
                            <span>显示：</span><input type="checkbox" class="ui-checkbox" />
                        </div>
                        <div class="ui-form-item">
                            链接网址：
                            <input class="ui-input" style="width: 245px;" placeholder="http://www.8637.com/pinpai/index.html" />
                        </div>
                        <div class="ui-form-item">
                            <span>* 建议导航不要超过十项内容(如果导航字数少，可适量增加)</span>
                        </div>
                    </form>
--%>
                    <div class="ui-bottom">
                        <a href="javascript:;" class="edit-common-conserve js-editnav-save">保存</a>
                        <a href="javascript:;" class="edit-common-cancel js-editnav-cansle">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

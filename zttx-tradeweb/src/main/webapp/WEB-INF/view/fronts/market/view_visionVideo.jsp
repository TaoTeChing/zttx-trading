<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div id="js-video" class="videobox f-l" data-src="${video.vedioFile}">
</div>
<div class="share font">
    <p>分享给站外好友:</p>
    <table>
        <tr>
            <td>
                <input type="text" value="点击右侧按钮复制对应代码" class="copytext font" id="fe_text1" />
            </td>
            <td>
                <input type="button" value="复制Flash代码" class="copybtn font js-copyflashcode" data-clipboard-target="fe_text1"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" value="点击右侧按钮复制对应代码" class="copytext font" id="fe_text2" />
            </td>
            <td>
                <input type="button" value="复制Html代码" class="copybtn font js-copyflashtml" id="d_clip_button1" data-clipboard-target="fe_text2" />
            </td>
        </tr>
    </table>
    <div class="bdsharebuttonbox" data-tag="share_1" style="margin: 10px 0 0 0">
        <a href="#">一键转发：</a>
        <a href="#" class="bds_tsina" data-cmd="tsina"></a>
        <a href="#" class="bds_qzone" data-cmd="qzone"></a>
        <a href="#" class="bds_renren" data-cmd="renren"></a>
        <a href="#" class="bds_more" data-cmd="more"></a>
    </div>
    <script>
        window._bd_share_config = {
            "common": { "bdSnsKey": {}, "bdText": "", "bdMini": "2", "bdPic": "", "bdStyle": "1", "bdSize": "16" },
            "share": {},
            "image": { "viewList": ["qzone", "tsina", "tqq", "renren", "weixin"], "viewText": "分享到：", "viewSize": "16" },
            "selectShare": { "bdContainerClass": null, "bdSelectMiniList": ["qzone", "tsina", "tqq", "renren", "weixin"] }
        };
        with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
    </script>
</div>
<div class="clear"></div>
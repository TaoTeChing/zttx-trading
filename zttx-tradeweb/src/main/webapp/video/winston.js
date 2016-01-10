(function () {
    if (!window['XK']) {
        window['XK'] = {};
    }
    XK.config = {
        version: '2.1.2',
        swfId: strArray.id,
        swfUrl: strArray.url,
        resourceUrl: '/video/',
        CouterUrl: '',
        position: strArray.position||'rb',
        offset: [3,0],
        swfWidth: strArray.width,
        swfHeight: strArray.height,
        autoPlay: 1,
        closeTimes: 1,
        ad: '',
        adPos: 'before',
        adtime: 5,
        duetime: 0,
        drag: 1,
        close: 1,
        issavecookie: false,
        isstop: 0,
        startFn: function () {
            var isEx = Player.cookie.get("8637_VID_" + strArray.id);
            if (isEx) {
                setTimeout(function () {
                    Player.event.stop();
                 }, 382);
            }
        },
        overFn: function (o) {
            //o:0 自动结束 o:1 主动关闭
            Player.cookie.set("8637_VID_" + strArray.id, true);


        }
    };
    var scriptNode = document.createElement('script');
    scriptNode.setAttribute('type', 'text/javascript');
    document.getElementsByTagName('head')[0].appendChild(scriptNode);
})();

if (!window.swfobject) {
    window.swfobject = function () {
        var ae = "undefined",
		aG = "object",
		aJ = "Shockwave Flash",
		aR = "ShockwaveFlash.ShockwaveFlash",
		aE = "application/x-shockwave-flash",
		aH = "SWFObjectExprInst",
		aS = "onreadystatechange",
		aB = window,
		ap = document,
		aK = navigator,
		aL = false,
		aN = [al],
		aA = [],
		az = [],
		ao = [],
		au,
		aF,
		ag,
		aa,
		aq = false,
		X = false,
		ay,
		ak,
		aw = true,
		ax = function () {
		    var a = typeof ap.getElementById != ae && typeof ap.getElementsByTagName != ae && typeof ap.createElement != ae,
			h = aK.userAgent.toLowerCase(),
			j = aK.platform.toLowerCase(),
			e = j ? /win/.test(j) : /win/.test(h),
			c = j ? /mac/.test(j) : /mac/.test(h),
			f = /webkit/.test(h) ? parseFloat(h.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : false,
			i = !+"\v1",
			g = [0, 0, 0],
			b = null;
		    if (typeof aK.plugins != ae && typeof aK.plugins[aJ] == aG) {
		        b = aK.plugins[aJ].description;
		        if (b && !(typeof aK.mimeTypes != ae && aK.mimeTypes[aE] && !aK.mimeTypes[aE].enabledPlugin)) {
		            aL = true;
		            i = false;
		            b = b.replace(/^.*\s+(\S+\s+\S+$)/, "$1");
		            g[0] = parseInt(b.replace(/^(.*)\..*$/, "$1"), 10);
		            g[1] = parseInt(b.replace(/^.*\.(.*)\s.*$/, "$1"), 10);
		            g[2] = /[a-zA-Z]/.test(b) ? parseInt(b.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0
		        }
		    } else {
		        if (typeof aB.ActiveXObject != ae) {
		            try {
		                var d = new ActiveXObject(aR);
		                if (d) {
		                    b = d.GetVariable("$version");
		                    if (b) {
		                        i = true;
		                        b = b.split(" ")[1].split(",");
		                        g = [parseInt(b[0], 10), parseInt(b[1], 10), parseInt(b[2], 10)]
		                    }
		                }
		            } catch (k) { }
		        }
		    }
		    return {
		        w3: a,
		        pv: g,
		        wk: f,
		        ie: i,
		        win: e,
		        mac: c
		    }
		}(),
		ar = function () {
		    if (!ax.w3) {
		        return
		    }
		    if ((typeof ap.readyState != ae && ap.readyState == "complete") || (typeof ap.readyState == ae && (ap.getElementsByTagName("body")[0] || ap.body))) {
		        ah()
		    }
		    if (!aq) {
		        if (typeof ap.addEventListener != ae) {
		            ap.addEventListener("DOMContentLoaded", ah, false)
		        }
		        if (ax.ie && ax.win) {
		            ap.attachEvent(aS,
					function () {
					    if (ap.readyState == "complete") {
					        ap.detachEvent(aS, arguments.callee);
					        ah()
					    }
					});
		            if (aB == top) {
		                (function () {
		                    if (aq) {
		                        return
		                    }
		                    try {
		                        ap.documentElement.doScroll("left")
		                    } catch (a) {
		                        setTimeout(arguments.callee, 0);
		                        return
		                    }
		                    ah()
		                })()
		            }
		        }
		        if (ax.wk) {
		            (function () {
		                if (aq) {
		                    return
		                }
		                if (!/loaded|complete/.test(ap.readyState)) {
		                    setTimeout(arguments.callee, 0);
		                    return
		                }
		                ah()
		            })()
		        }
		        aI(ah)
		    }
		}();
        function ah() {
            if (aq) {
                return
            }
            try {
                var d = ap.getElementsByTagName("body")[0].appendChild(ac("span"));
                d.parentNode.removeChild(d)
            } catch (a) {
                return
            }
            aq = true;
            var b = aN.length;
            for (var c = 0; c < b; c++) {
                aN[c]()
            }
        }
        function at(a) {
            if (aq) {
                a()
            } else {
                aN[aN.length] = a
            }
        }
        function aI(b) {
            if (typeof aB.addEventListener != ae) {
                aB.addEventListener("load", b, false)
            } else {
                if (typeof ap.addEventListener != ae) {
                    ap.addEventListener("load", b, false)
                } else {
                    if (typeof aB.attachEvent != ae) {
                        an(aB, "onload", b)
                    } else {
                        if (typeof aB.onload == "function") {
                            var a = aB.onload;
                            aB.onload = function () {
                                a();
                                b()
                            }
                        } else {
                            aB.onload = b
                        }
                    }
                }
            }
        }
        function al() {
            if (aL) {
                aP()
            } else {
                am()
            }
        }
        function aP() {
            var b = ap.getElementsByTagName("body")[0];
            var a = ac(aG);
            a.setAttribute("type", aE);
            var d = b.appendChild(a);
            if (d) {
                var c = 0; (function () {
                    if (typeof d.GetVariable != ae) {
                        var e = d.GetVariable("$version");
                        if (e) {
                            e = e.split(" ")[1].split(",");
                            ax.pv = [parseInt(e[0], 10), parseInt(e[1], 10), parseInt(e[2], 10)]
                        }
                    } else {
                        if (c < 10) {
                            c++;
                            setTimeout(arguments.callee, 10);
                            return
                        }
                    }
                    b.removeChild(a);
                    d = null;
                    am()
                })()
            } else {
                am()
            }
        }
        function am() {
            var g = aA.length;
            if (g > 0) {
                for (var f = 0; f < g; f++) {
                    var k = aA[f].id;
                    var b = aA[f].callbackFn;
                    var a = {
                        success: false,
                        id: k
                    };
                    if (ax.pv[0] > 0) {
                        var e = ab(k);
                        if (e) {
                            if (ai(aA[f].swfVersion) && !(ax.wk && ax.wk < 312)) {
                                aQ(k, true);
                                if (b) {
                                    a.success = true;
                                    a.ref = aU(k);
                                    b(a)
                                }
                            } else {
                                if (aA[f].expressInstall && Y()) {
                                    var i = {};
                                    i.data = aA[f].expressInstall;
                                    i.width = e.getAttribute("width") || "0";
                                    i.height = e.getAttribute("height") || "0";
                                    if (e.getAttribute("class")) {
                                        i.styleclass = e.getAttribute("class")
                                    }
                                    if (e.getAttribute("align")) {
                                        i.align = e.getAttribute("align")
                                    }
                                    var h = {};
                                    var j = e.getElementsByTagName("param");
                                    var c = j.length;
                                    for (var d = 0; d < c; d++) {
                                        if (j[d].getAttribute("name").toLowerCase() != "movie") {
                                            h[j[d].getAttribute("name")] = j[d].getAttribute("value")
                                        }
                                    }
                                    aD(i, h, k, b)
                                } else {
                                    aC(e);
                                    if (b) {
                                        b(a)
                                    }
                                }
                            }
                        }
                    } else {
                        aQ(k, true);
                        if (b) {
                            var l = aU(k);
                            if (l && typeof l.SetVariable != ae) {
                                a.success = true;
                                a.ref = l
                            }
                            b(a)
                        }
                    }
                }
            }
        }
        function aU(a) {
            var b = null;
            var c = ab(a);
            if (c && c.nodeName == "OBJECT") {
                if (typeof c.SetVariable != ae) {
                    b = c
                } else {
                    var d = c.getElementsByTagName(aG)[0];
                    if (d) {
                        b = d
                    }
                }
            }
            return b
        }
        function Y() {
            return !X && ai("6.0.65") && (ax.win || ax.mac) && !(ax.wk && ax.wk < 312)
        }
        function aD(a, b, f, h) {
            X = true;
            ag = h || null;
            aa = {
                success: false,
                id: f
            };
            var e = ab(f);
            if (e) {
                if (e.nodeName == "OBJECT") {
                    au = aj(e);
                    aF = null
                } else {
                    au = e;
                    aF = f
                }
                a.id = aH;
                if (typeof a.width == ae || (!/%$/.test(a.width) && parseInt(a.width, 10) < 310)) {
                    a.width = "310"
                }
                if (typeof a.height == ae || (!/%$/.test(a.height) && parseInt(a.height, 10) < 137)) {
                    a.height = "137"
                }
                ap.title = ap.title.slice(0, 47) + " - Flash Player Installation";
                var d = ax.ie && ax.win ? "ActiveX" : "PlugIn",
				c = "MMredirectURL=" + encodeURI(aB.location).toString().replace(/&/g, "%26") + "&MMplayerType=" + d + "&MMdoctitle=" + ap.title;
                if (typeof b.flashvars != ae) {
                    b.flashvars += "&" + c
                } else {
                    b.flashvars = c
                }
                if (ax.ie && ax.win && e.readyState != 4) {
                    var g = ac("div");
                    f += "SWFObjectNew";
                    g.setAttribute("id", f);
                    e.parentNode.insertBefore(g, e);
                    e.style.display = "none"; (function () {
                        if (e.readyState == 4) {
                            e.parentNode.removeChild(e)
                        } else {
                            setTimeout(arguments.callee, 10)
                        }
                    })()
                }
                aM(a, b, f)
            }
        }
        function aC(b) {
            if (ax.ie && ax.win && b.readyState != 4) {
                var a = ac("div");
                b.parentNode.insertBefore(a, b);
                a.parentNode.replaceChild(aj(b), a);
                b.style.display = "none"; (function () {
                    if (b.readyState == 4) {
                        b.parentNode.removeChild(b)
                    } else {
                        setTimeout(arguments.callee, 10)
                    }
                })()
            } else {
                b.parentNode.replaceChild(aj(b), b)
            }
        }
        function aj(b) {
            var a = ac("div");
            if (ax.win && ax.ie) {
                a.innerHTML = b.innerHTML
            } else {
                var e = b.getElementsByTagName(aG)[0];
                if (e) {
                    var c = e.childNodes;
                    if (c) {
                        var d = c.length;
                        for (var f = 0; f < d; f++) {
                            if (!(c[f].nodeType == 1 && c[f].nodeName == "PARAM") && !(c[f].nodeType == 8)) {
                                a.appendChild(c[f].cloneNode(true))
                            }
                        }
                    }
                }
            }
            return a
        }
        function aM(i, g, k) {
            var j, a = ab(k);
            if (ax.wk && ax.wk < 312) {
                return j
            }
            if (a) {
                if (typeof i.id == ae) {
                    i.id = k
                }
                if (ax.ie && ax.win) {
                    var h = "";
                    for (var e in i) {
                        if (i[e] != Object.prototype[e]) {
                            if (e.toLowerCase() == "data") {
                                g.movie = i[e]
                            } else {
                                if (e.toLowerCase() == "styleclass") {
                                    h += ' class="' + i[e] + '"'
                                } else {
                                    if (e.toLowerCase() != "classid") {
                                        h += " " + e + '="' + i[e] + '"'
                                    }
                                }
                            }
                        }
                    }
                    var f = "";
                    for (var d in g) {
                        if (g[d] != Object.prototype[d]) {
                            f += '<param name="' + d + '" value="' + g[d] + '" />'
                        }
                    }
                    a.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + h + ">" + f + "</object>";
                    az[az.length] = i.id;
                    j = ab(i.id)
                } else {
                    var l = ac(aG);
                    l.setAttribute("type", aE);
                    for (var c in i) {
                        if (i[c] != Object.prototype[c]) {
                            if (c.toLowerCase() == "styleclass") {
                                l.setAttribute("class", i[c])
                            } else {
                                if (c.toLowerCase() != "classid") {
                                    l.setAttribute(c, i[c])
                                }
                            }
                        }
                    }
                    for (var b in g) {
                        if (g[b] != Object.prototype[b] && b.toLowerCase() != "movie") {
                            af(l, b, g[b])
                        }
                    }
                    a.parentNode.replaceChild(l, a);
                    j = l
                }
            }
            return j
        }
        function af(d, b, c) {
            var a = ac("param");
            a.setAttribute("name", b);
            a.setAttribute("value", c);
            d.appendChild(a)
        }
        function aT(b) {
            var a = ab(b);
            if (a && a.nodeName == "OBJECT") {
                if (ax.ie && ax.win) {
                    a.style.display = "none"; (function () {
                        if (a.readyState == 4) {
                            Z(b)
                        } else {
                            setTimeout(arguments.callee, 10)
                        }
                    })()
                } else {
                    a.parentNode.removeChild(a)
                }
            }
        }
        function Z(c) {
            var b = ab(c);
            if (b) {
                for (var a in b) {
                    if (typeof b[a] == "function") {
                        b[a] = null
                    }
                }
                b.parentNode.removeChild(b)
            }
        }
        function ab(c) {
            var a = null;
            try {
                a = ap.getElementById(c)
            } catch (b) { }
            return a
        }
        function ac(a) {
            return ap.createElement(a)
        }
        function an(c, a, b) {
            c.attachEvent(a, b);
            ao[ao.length] = [c, a, b]
        }
        function ai(c) {
            var b = ax.pv,
			a = c.split(".");
            a[0] = parseInt(a[0], 10);
            a[1] = parseInt(a[1], 10) || 0;
            a[2] = parseInt(a[2], 10) || 0;
            return (b[0] > a[0] || (b[0] == a[0] && b[1] > a[1]) || (b[0] == a[0] && b[1] == a[1] && b[2] >= a[2])) ? true : false
        }
        function aO(c, f, d, b) {
            if (ax.ie && ax.mac) {
                return
            }
            var a = ap.getElementsByTagName("head")[0];
            if (!a) {
                return
            }
            var e = (d && typeof d == "string") ? d : "screen";
            if (b) {
                ay = null;
                ak = null
            }
            if (!ay || ak != e) {
                var g = ac("style");
                g.setAttribute("type", "text/css");
                g.setAttribute("media", e);
                ay = a.appendChild(g);
                if (ax.ie && ax.win && typeof ap.styleSheets != ae && ap.styleSheets.length > 0) {
                    ay = ap.styleSheets[ap.styleSheets.length - 1]
                }
                ak = e
            }
            if (ax.ie && ax.win) {
                if (ay && typeof ay.addRule == aG) {
                    ay.addRule(c, f)
                }
            } else {
                if (ay && typeof ap.createTextNode != ae) {
                    ay.appendChild(ap.createTextNode(c + " {" + f + "}"))
                }
            }
        }
        function aQ(c, a) {
            if (!aw) {
                return
            }
            var b = a ? "visible" : "hidden";
            if (aq && ab(c)) {
                ab(c).style.visibility = b
            } else {
                aO("#" + c, "visibility:" + b)
            }
        }
        function av(b) {
            var c = /[\\\"<>\.;]/;
            var a = c.exec(b) != null;
            return a && typeof encodeURIComponent != ae ? encodeURIComponent(b) : b
        }
        var ad = function () {
            if (ax.ie && ax.win) {
                window.attachEvent("onunload",
				function () {
				    var c = ao.length;
				    for (var b = 0; b < c; b++) {
				        ao[b][0].detachEvent(ao[b][1], ao[b][2])
				    }
				    var f = az.length;
				    for (var a = 0; a < f; a++) {
				        aT(az[a])
				    }
				    for (var e in ax) {
				        ax[e] = null
				    }
				    ax = null;
				    for (var d in swfobject) {
				        swfobject[d] = null
				    }
				    swfobject = null
				})
            }
        }();
        return {
            registerObject: function (b, c, a, e) {
                if (ax.w3 && b && c) {
                    var d = {};
                    d.id = b;
                    d.swfVersion = c;
                    d.expressInstall = a;
                    d.callbackFn = e;
                    aA[aA.length] = d;
                    aQ(b, false)
                } else {
                    if (e) {
                        e({
                            success: false,
                            id: b
                        })
                    }
                }
            },
            getObjectById: function (a) {
                if (ax.w3) {
                    return aU(a)
                }
            },
            embedSWF: function (b, h, e, g, j, a, k, d, f, c) {
                var i = {
                    success: false,
                    id: h
                };
                if (ax.w3 && !(ax.wk && ax.wk < 312) && b && h && e && g && j) {
                    aQ(h, false);
                    at(function () {
                        e += "";
                        g += "";
                        var m = {};
                        if (f && typeof f === aG) {
                            for (var o in f) {
                                m[o] = f[o]
                            }
                        }
                        m.data = b;
                        m.width = e;
                        m.height = g;
                        var p = {};
                        if (d && typeof d === aG) {
                            for (var n in d) {
                                p[n] = d[n]
                            }
                        }
                        if (k && typeof k === aG) {
                            for (var l in k) {
                                if (typeof p.flashvars != ae) {
                                    p.flashvars += "&" + l + "=" + k[l]
                                } else {
                                    p.flashvars = l + "=" + k[l]
                                }
                            }
                        }
                        if (ai(j)) {
                            var q = aM(m, p, h);
                            if (m.id == h) {
                                aQ(h, true)
                            }
                            i.success = true;
                            i.ref = q
                        } else {
                            if (a && Y()) {
                                m.data = a;
                                aD(m, p, h, c);
                                return
                            } else {
                                aQ(h, true)
                            }
                        }
                        if (c) {
                            c(i)
                        }
                    })
                } else {
                    if (c) {
                        c(i)
                    }
                }
            },
            switchOffAutoHideShow: function () {
                aw = false
            },
            ua: ax,
            getFlashPlayerVersion: function () {
                return {
                    major: ax.pv[0],
                    minor: ax.pv[1],
                    release: ax.pv[2]
                }
            },
            hasFlashPlayerVersion: ai,
            createSWF: function (c, b, a) {
                if (ax.w3) {
                    return aM(c, b, a)
                } else {
                    return undefined
                }
            },
            showExpressInstall: function (d, a, b, c) {
                if (ax.w3 && Y()) {
                    aD(d, a, b, c)
                }
            },
            removeSWF: function (a) {
                if (ax.w3) {
                    aT(a)
                }
            },
            createCSS: function (a, d, c, b) {
                if (ax.w3) {
                    aO(a, d, c, b)
                }
            },
            addDomLoadEvent: at,
            addLoadEvent: aI,
            getQueryParamValue: function (a) {
                var d = ap.location.search || ap.location.hash;
                if (d) {
                    if (/\?/.test(d)) {
                        d = d.split("?")[1]
                    }
                    if (a == null) {
                        return av(d)
                    }
                    var c = d.split("&");
                    for (var b = 0; b < c.length; b++) {
                        if (c[b].substring(0, c[b].indexOf("=")) == a) {
                            return av(c[b].substring((c[b].indexOf("=") + 1)))
                        }
                    }
                }
                return ""
            },
            expressInstallCallback: function () {
                if (X) {
                    var a = ab(aH);
                    if (a && au) {
                        a.parentNode.replaceChild(au, a);
                        if (aF) {
                            aQ(aF, true);
                            if (ax.ie && ax.win) {
                                au.style.display = "block"
                            }
                        }
                        if (ag) {
                            ag(aa)
                        }
                    }
                    X = false
                }
            }
        }
    }()
} (function (bj, bK) {
    if (!bj.XK) {
        bj.XK = {}
    }
    var aW = function (a, b) {
        return new aW.fn.init(a, b)
    },
	cw = bj.document,
	co,
	ca = /^[^<]*(<[\w\W]+>)[^>]*$|^#([\w-]+)$/,
	bC = /^.[^:#\[\.,]*$/,
	bD = /\S/,
	b4 = /^(\s|\u00A0)+|(\s|\u00A0)+$/g,
	bP = /^<(\w+)\s*\/?>(?:<\/\1>)?$/,
	bJ = navigator.userAgent,
	cj,
	b0 = false,
	a0 = [],
	a7,
	bu = Object.prototype.toString,
	bo = Object.prototype.hasOwnProperty,
	bT = Array.prototype.push,
	bQ = Array.prototype.slice,
	cf = Array.prototype.indexOf;
    aW.fn = aW.prototype = {
        init: function (c, f) {
            var e, a, d, b;
            if (!c) {
                return this
            }
            if (c.nodeType) {
                this.context = this[0] = c;
                this.length = 1;
                return this
            }
            if (c === "body" && !f) {
                this.context = cw;
                this[0] = cw.body;
                this.selector = "body";
                this.length = 1;
                return this
            }
            if (typeof c === "string") {
                e = ca.exec(c);
                if (e && (e[1] || !f)) {
                    if (e[1]) {
                        b = (f ? f.ownerDocument || f : cw);
                        d = bP.exec(c);
                        if (d) {
                            if (aW.isPlainObject(f)) {
                                c = [cw.createElement(d[1])];
                                aW.fn.attr.call(c, f, true)
                            } else {
                                c = [b.createElement(d[1])]
                            }
                        } else {
                            d = bY([e[1]], [b]);
                            c = (d.cacheable ? d.fragment.cloneNode(true) : d.fragment).childNodes
                        }
                        return aW.merge(this, c)
                    } else {
                        a = cw.getElementById(e[2]);
                        if (a) {
                            if (a.id !== e[2]) {
                                return co.find(c)
                            }
                            this.length = 1;
                            this[0] = a
                        }
                        this.context = cw;
                        this.selector = c;
                        return this
                    }
                } else {
                    if (!f && /^\w+$/.test(c)) {
                        this.selector = c;
                        this.context = cw;
                        c = cw.getElementsByTagName(c);
                        return aW.merge(this, c)
                    } else {
                        if (!f || f.jquery) {
                            return (f || co).find(c)
                        } else {
                            return aW(f).find(c)
                        }
                    }
                }
            } else {
                if (aW.isFunction(c)) {
                    return co.ready(c)
                }
            }
            if (c.selector !== bK) {
                this.selector = c.selector;
                this.context = c.context
            }
            return aW.makeArray(c, this)
        },
        selector: "",
        jquery: "1.4.2",
        length: 0,
        size: function () {
            return this.length
        },
        toArray: function () {
            return bQ.call(this, 0)
        },
        get: function (a) {
            return a == null ? this.toArray() : (a < 0 ? this.slice(a)[0] : this[a])
        },
        pushStack: function (b, d, a) {
            var c = aW();
            if (aW.isArray(b)) {
                bT.apply(c, b)
            } else {
                aW.merge(c, b)
            }
            c.prevObject = this;
            c.context = this.context;
            if (d === "find") {
                c.selector = this.selector + (this.selector ? " " : "") + a
            } else {
                if (d) {
                    c.selector = this.selector + "." + d + "(" + a + ")"
                }
            }
            return c
        },
        each: function (b, a) {
            return aW.each(this, b, a)
        },
        ready: function (a) {
            aW.bindReady();
            if (aW.isReady) {
                a.call(cw, aW)
            } else {
                if (a0) {
                    a0.push(a)
                }
            }
            return this
        },
        eq: function (a) {
            return a === -1 ? this.slice(a) : this.slice(a, +a + 1)
        },
        first: function () {
            return this.eq(0)
        },
        last: function () {
            return this.eq(-1)
        },
        slice: function () {
            return this.pushStack(bQ.apply(this, arguments), "slice", bQ.call(arguments).join(","))
        },
        map: function (a) {
            return this.pushStack(aW.map(this,
			function (c, b) {
			    return a.call(c, b, c)
			}))
        },
        end: function () {
            return this.prevObject || aW(null)
        },
        push: bT,
        sort: [].sort,
        splice: [].splice
    };
    aW.fn.init.prototype = aW.fn;
    aW.extend = aW.fn.extend = function () {
        var b = arguments[0] || {},
		a = 1,
		i = arguments.length,
		d = false,
		e,
		h,
		f,
		g;
        if (typeof b === "boolean") {
            d = b;
            b = arguments[1] || {};
            a = 2
        }
        if (typeof b !== "object" && !aW.isFunction(b)) {
            b = {}
        }
        if (i === a) {
            b = this; --a
        }
        for (; a < i; a++) {
            if ((e = arguments[a]) != null) {
                for (h in e) {
                    f = b[h];
                    g = e[h];
                    if (b === g) {
                        continue
                    }
                    if (d && g && (aW.isPlainObject(g) || aW.isArray(g))) {
                        var c = f && (aW.isPlainObject(f) || aW.isArray(f)) ? f : aW.isArray(g) ? [] : {};
                        b[h] = aW.extend(d, c, g)
                    } else {
                        if (g !== bK) {
                            b[h] = g
                        }
                    }
                }
            }
        }
        return b
    };
    aW.extend({
        isReady: false,
        ready: function () {
            if (!aW.isReady) {
                if (!cw.body) {
                    return setTimeout(aW.ready, 13)
                }
                aW.isReady = true;
                if (a0) {
                    var b, a = 0;
                    while ((b = a0[a++])) {
                        b.call(cw, aW)
                    }
                    a0 = null
                }
                if (aW.fn.triggerHandler) {
                    aW(cw).triggerHandler("ready")
                }
            }
        },
        bindReady: function () {
            if (b0) {
                return
            }
            b0 = true;
            if (cw.readyState === "complete") {
                return aW.ready()
            }
            if (cw.addEventListener) {
                cw.addEventListener("DOMContentLoaded", a7, false);
                bj.addEventListener("load", aW.ready, false)
            } else {
                if (cw.attachEvent) {
                    cw.attachEvent("onreadystatechange", a7);
                    bj.attachEvent("onload", aW.ready);
                    var a = false;
                    try {
                        a = bj.frameElement == null
                    } catch (b) { }
                    if (cw.documentElement.doScroll && a) {
                        cp()
                    }
                }
            }
        },
        isFunction: function (a) {
            return bu.call(a) === "[object Function]"
        },
        isArray: function (a) {
            return bu.call(a) === "[object Array]"
        },
        isPlainObject: function (b) {
            if (!b || bu.call(b) !== "[object Object]" || b.nodeType || b.setInterval) {
                return false
            }
            if (b.constructor && !bo.call(b, "constructor") && !bo.call(b.constructor.prototype, "isPrototypeOf")) {
                return false
            }
            var a;
            for (a in b) { }
            return a === bK || bo.call(b, a)
        },
        isEmptyObject: function (b) {
            for (var a in b) {
                return false
            }
            return true
        },
        error: function (a) {
            throw a
        },
        parseJSON: function (a) {
            if (typeof a !== "string" || !a) {
                return null
            }
            a = aW.trim(a);
            if (/^[\],:{}\s]*$/.test(a.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
                return bj.JSON && bj.JSON.parse ? bj.JSON.parse(a) : (new Function("return " + a))()
            } else {
                aW.error("Invalid JSON: " + a)
            }
        },
        noop: function () { },
        globalEval: function (c) {
            if (c && bD.test(c)) {
                var b = cw.getElementsByTagName("head")[0] || cw.documentElement,
				a = cw.createElement("script");
                a.type = "text/javascript";
                if (aW.support.scriptEval) {
                    a.appendChild(cw.createTextNode(c))
                } else {
                    a.text = c
                }
                b.insertBefore(a, b.firstChild);
                b.removeChild(a)
            }
        },
        nodeName: function (b, a) {
            return b.nodeName && b.nodeName.toUpperCase() === a.toUpperCase()
        },
        each: function (h, d, g) {
            var f, a = 0,
			b = h.length,
			e = b === bK || aW.isFunction(h);
            if (g) {
                if (e) {
                    for (f in h) {
                        if (d.apply(h[f], g) === false) {
                            break
                        }
                    }
                } else {
                    for (; a < b;) {
                        if (d.apply(h[a++], g) === false) {
                            break
                        }
                    }
                }
            } else {
                if (e) {
                    for (f in h) {
                        if (d.call(h[f], f, h[f]) === false) {
                            break
                        }
                    }
                } else {
                    for (var c = h[0]; a < b && d.call(c, a, c) !== false; c = h[++a]) { }
                }
            }
            return h
        },
        trim: function (a) {
            return (a || "").replace(b4, "")
        },
        makeArray: function (c, b) {
            var a = b || [];
            if (c != null) {
                if (c.length == null || typeof c === "string" || aW.isFunction(c) || (typeof c !== "function" && c.setInterval)) {
                    bT.call(a, c)
                } else {
                    aW.merge(a, c)
                }
            }
            return a
        },
        inArray: function (c, d) {
            if (d.indexOf) {
                return d.indexOf(c)
            }
            for (var a = 0,
			b = d.length; a < b; a++) {
                if (d[a] === c) {
                    return a
                }
            }
            return -1
        },
        merge: function (a, d) {
            var e = a.length,
			c = 0;
            if (typeof d.length === "number") {
                for (var b = d.length; c < b; c++) {
                    a[e++] = d[c]
                }
            } else {
                while (d[c] !== bK) {
                    a[e++] = d[c++]
                }
            }
            a.length = e;
            return a
        },
        grep: function (d, b, c) {
            var e = [];
            for (var f = 0,
			a = d.length; f < a; f++) {
                if (!c !== !b(d[f], f)) {
                    e.push(d[f])
                }
            }
            return e
        },
        map: function (e, c, d) {
            var f = [],
			b;
            for (var g = 0,
			a = e.length; g < a; g++) {
                b = c(e[g], g, d);
                if (b != null) {
                    f[f.length] = b
                }
            }
            return f.concat.apply([], f)
        },
        guid: 1,
        proxy: function (c, b, a) {
            if (arguments.length === 2) {
                if (typeof b === "string") {
                    a = c;
                    c = a[b];
                    b = bK
                } else {
                    if (b && !aW.isFunction(b)) {
                        a = b;
                        b = bK
                    }
                }
            }
            if (!b && c) {
                b = function () {
                    return c.apply(a || this, arguments)
                }
            }
            if (c) {
                b.guid = c.guid = c.guid || b.guid || aW.guid++
            }
            return b
        },
        uaMatch: function (b) {
            b = b.toLowerCase();
            var a = /(webkit)[ \/]([\w.]+)/.exec(b) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(b) || /(msie) ([\w.]+)/.exec(b) || !/compatible/.test(b) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(b) || [];
            return {
                browser: a[1] || "",
                version: a[2] || "0"
            }
        },
        browser: {}
    });
    cj = aW.uaMatch(bJ);
    if (cj.browser) {
        aW.browser[cj.browser] = true;
        aW.browser.version = cj.version
    }
    if (aW.browser.webkit) {
        aW.browser.safari = true
    }
    if (cf) {
        aW.inArray = function (a, b) {
            return cf.call(b, a)
        }
    }
    co = aW(cw);
    if (cw.addEventListener) {
        a7 = function () {
            cw.removeEventListener("DOMContentLoaded", a7, false);
            aW.ready()
        }
    } else {
        if (cw.attachEvent) {
            a7 = function () {
                if (cw.readyState === "complete") {
                    cw.detachEvent("onreadystatechange", a7);
                    aW.ready()
                }
            }
        }
    }
    function cp() {
        if (aW.isReady) {
            return
        }
        try {
            cw.documentElement.doScroll("left")
        } catch (a) {
            setTimeout(cp, 1);
            return
        }
        aW.ready()
    }
    function bA(a, b) {
        if (b.src) {
            aW.ajax({
                url: b.src,
                async: false,
                dataType: "script"
            })
        } else {
            aW.globalEval(b.text || b.textContent || b.innerHTML || "")
        }
        if (b.parentNode) {
            b.parentNode.removeChild(b)
        }
    }
    function bk(f, e, c, h, b, d) {
        var g = f.length;
        if (typeof e === "object") {
            for (var i in e) {
                bk(f, i, e[i], h, b, c)
            }
            return f
        }
        if (c !== bK) {
            h = !d && h && aW.isFunction(c);
            for (var a = 0; a < g; a++) {
                b(f[a], e, h ? c.call(f[a], a, b(f[a], e)) : c, d)
            }
            return f
        }
        return g ? b(f[0], e) : bK
    }
    function bp() {
        return (new Date).getTime()
    } (function () {
        aW.support = {};
        var c = cw.documentElement,
		b = cw.createElement("script"),
		g = cw.createElement("div"),
		h = "script" + bp();
        g.style.display = "none";
        g.innerHTML = "   <link/><table></table><a href='/a' style='color:red;float:left;opacity:.55;'>a</a><input type='checkbox'/>";
        var e = g.getElementsByTagName("*"),
		d = g.getElementsByTagName("a")[0];
        if (!e || !e.length || !d) {
            return
        }
        aW.support = {
            leadingWhitespace: g.firstChild.nodeType === 3,
            tbody: !g.getElementsByTagName("tbody").length,
            htmlSerialize: !!g.getElementsByTagName("link").length,
            style: /red/.test(d.getAttribute("style")),
            hrefNormalized: d.getAttribute("href") === "/a",
            opacity: /^0.55$/.test(d.style.opacity),
            cssFloat: !!d.style.cssFloat,
            checkOn: g.getElementsByTagName("input")[0].value === "on",
            optSelected: cw.createElement("select").appendChild(cw.createElement("option")).selected,
            parentNode: g.removeChild(g.appendChild(cw.createElement("div"))).parentNode === null,
            deleteExpando: true,
            checkClone: false,
            scriptEval: false,
            noCloneEvent: true,
            boxModel: null
        };
        b.type = "text/javascript";
        try {
            b.appendChild(cw.createTextNode("window." + h + "=1;"))
        } catch (j) { }
        c.insertBefore(b, c.firstChild);
        if (bj[h]) {
            aW.support.scriptEval = true;
            delete bj[h]
        }
        try {
            delete b.test
        } catch (j) {
            aW.support.deleteExpando = false
        }
        c.removeChild(b);
        if (g.attachEvent && g.fireEvent) {
            g.attachEvent("onclick",
			function f() {
			    aW.support.noCloneEvent = false;
			    g.detachEvent("onclick", f)
			});
            g.cloneNode(true).fireEvent("onclick")
        }
        g = cw.createElement("div");
        g.innerHTML = "<input type='radio' name='radiotest' checked='checked'/>";
        var i = cw.createDocumentFragment();
        i.appendChild(g.firstChild);
        aW.support.checkClone = i.cloneNode(true).cloneNode(true).lastChild.checked;
        aW(function () {
            var k = cw.createElement("div");
            k.style.width = k.style.paddingLeft = "1px";
            cw.body.appendChild(k);
            aW.boxModel = aW.support.boxModel = k.offsetWidth === 2;
            cw.body.removeChild(k).style.display = "none";
            k = null
        });
        var a = function (k) {
            var m = cw.createElement("div");
            k = "on" + k;
            var l = (k in m);
            if (!l) {
                m.setAttribute(k, "return;");
                l = typeof m[k] === "function"
            }
            m = null;
            return l
        };
        aW.support.submitBubbles = a("submit");
        aW.support.changeBubbles = a("change");
        c = b = g = e = d = null
    })();
    aW.props = {
        "for": "htmlFor",
        "class": "className",
        readonly: "readOnly",
        maxlength: "maxLength",
        cellspacing: "cellSpacing",
        rowspan: "rowSpan",
        colspan: "colSpan",
        tabindex: "tabIndex",
        usemap: "useMap",
        frameborder: "frameBorder"
    };
    var bb = "jQuery" + bp(),
	a9 = 0,
	bx = {};
    aW.extend({
        cache: {},
        expando: bb,
        noData: {
            embed: true,
            object: true,
            applet: true
        },
        data: function (e, d, a) {
            if (e.nodeName && aW.noData[e.nodeName.toLowerCase()]) {
                return
            }
            e = e == bj ? bx : e;
            var b = e[bb],
			c = aW.cache,
			f;
            if (!b && typeof d === "string" && a === bK) {
                return null
            }
            if (!b) {
                b = ++a9
            }
            if (typeof d === "object") {
                e[bb] = b;
                f = c[b] = aW.extend(true, {},
				d)
            } else {
                if (!c[b]) {
                    e[bb] = b;
                    c[b] = {}
                }
            }
            f = c[b];
            if (a !== bK) {
                f[d] = a
            }
            return typeof d === "string" ? f[d] : f
        },
        removeData: function (d, c) {
            if (d.nodeName && aW.noData[d.nodeName.toLowerCase()]) {
                return
            }
            d = d == bj ? bx : d;
            var a = d[bb],
			b = aW.cache,
			e = b[a];
            if (c) {
                if (e) {
                    delete e[c];
                    if (aW.isEmptyObject(e)) {
                        aW.removeData(d)
                    }
                }
            } else {
                if (aW.support.deleteExpando) {
                    delete d[aW.expando]
                } else {
                    if (d.removeAttribute) {
                        d.removeAttribute(aW.expando)
                    }
                }
                delete b[a]
            }
        }
    });
    aW.fn.extend({
        data: function (a, c) {
            if (typeof a === "undefined" && this.length) {
                return aW.data(this[0])
            } else {
                if (typeof a === "object") {
                    return this.each(function () {
                        aW.data(this, a)
                    })
                }
            }
            var d = a.split(".");
            d[1] = d[1] ? "." + d[1] : "";
            if (c === bK) {
                var b = this.triggerHandler("getData" + d[1] + "!", [d[0]]);
                if (b === bK && this.length) {
                    b = aW.data(this[0], a)
                }
                return b === bK && d[1] ? this.data(d[0]) : b
            } else {
                return this.trigger("setData" + d[1] + "!", [d[0], c]).each(function () {
                    aW.data(this, a, c)
                })
            }
        },
        removeData: function (a) {
            return this.each(function () {
                aW.removeData(this, a)
            })
        }
    });
    aW.extend({
        queue: function (b, a, d) {
            if (!b) {
                return
            }
            a = (a || "fx") + "queue";
            var c = aW.data(b, a);
            if (!d) {
                return c || []
            }
            if (!c || aW.isArray(d)) {
                c = aW.data(b, a, aW.makeArray(d))
            } else {
                c.push(d)
            }
            return c
        },
        dequeue: function (d, c) {
            c = c || "fx";
            var a = aW.queue(d, c),
			b = a.shift();
            if (b === "inprogress") {
                b = a.shift()
            }
            if (b) {
                if (c === "fx") {
                    a.unshift("inprogress")
                }
                b.call(d,
				function () {
				    aW.dequeue(d, c)
				})
            }
        }
    });
    aW.fn.extend({
        queue: function (a, b) {
            if (typeof a !== "string") {
                b = a;
                a = "fx"
            }
            if (b === bK) {
                return aW.queue(this[0], a)
            }
            return this.each(function (e, c) {
                var d = aW.queue(this, a, b);
                if (a === "fx" && d[0] !== "inprogress") {
                    aW.dequeue(this, a)
                }
            })
        },
        dequeue: function (a) {
            return this.each(function () {
                aW.dequeue(this, a)
            })
        },
        delay: function (b, a) {
            b = aW.fx ? aW.fx.speeds[b] || b : b;
            a = a || "fx";
            return this.queue(a,
			function () {
			    var c = this;
			    setTimeout(function () {
			        aW.dequeue(c, a)
			    },
				b)
			})
        },
        clearQueue: function (a) {
            return this.queue(a || "fx", [])
        }
    });
    var bm = /[\n\t]/g,
	ce = /\s+/,
	bz = /\r/g,
	br = /href|src|style/,
	bN = /(button|input)/i,
	ct = /(button|input|object|select|textarea)/i,
	bZ = /^(a|area)$/i,
	bW = /radio|checkbox/;
    aW.fn.extend({
        attr: function (a, b) {
            return bk(this, a, b, true, aW.attr)
        },
        removeAttr: function (a, b) {
            return this.each(function () {
                aW.attr(this, a, "");
                if (this.nodeType === 1) {
                    this.removeAttribute(a)
                }
            })
        },
        addClass: function (d) {
            if (aW.isFunction(d)) {
                return this.each(function (k) {
                    var j = aW(this);
                    j.addClass(d.call(this, k, j.attr("class")))
                })
            }
            if (d && typeof d === "string") {
                var f = (d || "").split(ce);
                for (var i = 0,
				h = this.length; i < h; i++) {
                    var g = this[i];
                    if (g.nodeType === 1) {
                        if (!g.className) {
                            g.className = d
                        } else {
                            var a = " " + g.className + " ",
							c = g.className;
                            for (var b = 0,
							e = f.length; b < e; b++) {
                                if (a.indexOf(" " + f[b] + " ") < 0) {
                                    c += " " + f[b]
                                }
                            }
                            g.className = aW.trim(c)
                        }
                    }
                }
            }
            return this
        },
        removeClass: function (b) {
            if (aW.isFunction(b)) {
                return this.each(function (j) {
                    var i = aW(this);
                    i.removeClass(b.call(this, j, i.attr("class")))
                })
            }
            if ((b && typeof b === "string") || b === bK) {
                var c = (b || "").split(ce);
                for (var g = 0,
				f = this.length; g < f; g++) {
                    var a = this[g];
                    if (a.nodeType === 1 && a.className) {
                        if (b) {
                            var h = (" " + a.className + " ").replace(bm, " ");
                            for (var d = 0,
							e = c.length; d < e; d++) {
                                h = h.replace(" " + c[d] + " ", " ")
                            }
                            a.className = aW.trim(h)
                        } else {
                            a.className = ""
                        }
                    }
                }
            }
            return this
        },
        toggleClass: function (d, b) {
            var c = typeof d,
			a = typeof b === "boolean";
            if (aW.isFunction(d)) {
                return this.each(function (f) {
                    var e = aW(this);
                    e.toggleClass(d.call(this, f, e.attr("class"), b), b)
                })
            }
            return this.each(function () {
                if (c === "string") {
                    var g, f = 0,
					e = aW(this),
					h = b,
					i = d.split(ce);
                    while ((g = i[f++])) {
                        h = a ? h : !e.hasClass(g);
                        e[h ? "addClass" : "removeClass"](g)
                    }
                } else {
                    if (c === "undefined" || c === "boolean") {
                        if (this.className) {
                            aW.data(this, "__className__", this.className)
                        }
                        this.className = this.className || d === false ? "" : aW.data(this, "__className__") || ""
                    }
                }
            })
        },
        hasClass: function (a) {
            var d = " " + a + " ";
            for (var c = 0,
			b = this.length; c < b; c++) {
                if ((" " + this[c].className + " ").replace(bm, " ").indexOf(d) > -1) {
                    return true
                }
            }
            return false
        },
        val: function (d) {
            if (d === bK) {
                var h = this[0];
                if (h) {
                    if (aW.nodeName(h, "option")) {
                        return (h.attributes.value || {}).specified ? h.value : h.text
                    }
                    if (aW.nodeName(h, "select")) {
                        var b = h.selectedIndex,
						e = [],
						f = h.options,
						a = h.type === "select-one";
                        if (b < 0) {
                            return null
                        }
                        for (var i = a ? b : 0, c = a ? b + 1 : f.length; i < c; i++) {
                            var j = f[i];
                            if (j.selected) {
                                d = aW(j).val();
                                if (a) {
                                    return d
                                }
                                e.push(d)
                            }
                        }
                        return e
                    }
                    if (bW.test(h.type) && !aW.support.checkOn) {
                        return h.getAttribute("value") === null ? "on" : h.value
                    }
                    return (h.value || "").replace(bz, "")
                }
                return bK
            }
            var g = aW.isFunction(d);
            return this.each(function (m) {
                var l = aW(this),
				n = d;
                if (this.nodeType !== 1) {
                    return
                }
                if (g) {
                    n = d.call(this, m, l.val())
                }
                if (typeof n === "number") {
                    n += ""
                }
                if (aW.isArray(n) && bW.test(this.type)) {
                    this.checked = aW.inArray(l.val(), n) >= 0
                } else {
                    if (aW.nodeName(this, "select")) {
                        var k = aW.makeArray(n);
                        aW("option", this).each(function () {
                            this.selected = aW.inArray(aW(this).val(), k) >= 0
                        });
                        if (!k.length) {
                            this.selectedIndex = -1
                        }
                    } else {
                        this.value = n
                    }
                }
            })
        }
    });
    aW.extend({
        attrFn: {
            val: true,
            css: true,
            html: true,
            text: true,
            data: true,
            width: true,
            height: true,
            offset: true
        },
        attr: function (h, g, c, f) {
            if (!h || h.nodeType === 3 || h.nodeType === 8) {
                return bK
            }
            if (f && g in aW.attrFn) {
                return aW(h)[g](c)
            }
            var i = h.nodeType !== 1 || !aW.isXMLDoc(h),
			b = c !== bK;
            g = i && aW.props[g] || g;
            if (h.nodeType === 1) {
                var a = br.test(g);
                if (g === "selected" && !aW.support.optSelected) {
                    var d = h.parentNode;
                    if (d) {
                        d.selectedIndex;
                        if (d.parentNode) {
                            d.parentNode.selectedIndex
                        }
                    }
                }
                if (g in h && i && !a) {
                    if (b) {
                        if (g === "type" && bN.test(h.nodeName) && h.parentNode) {
                            aW.error("type property can't be changed")
                        }
                        h[g] = c
                    }
                    if (aW.nodeName(h, "form") && h.getAttributeNode(g)) {
                        return h.getAttributeNode(g).nodeValue
                    }
                    if (g === "tabIndex") {
                        var e = h.getAttributeNode("tabIndex");
                        return e && e.specified ? e.value : ct.test(h.nodeName) || bZ.test(h.nodeName) && h.href ? 0 : bK
                    }
                    return h[g]
                }
                if (!aW.support.style && i && g === "style") {
                    if (b) {
                        h.style.cssText = "" + c
                    }
                    return h.style.cssText
                }
                if (b) {
                    h.setAttribute(g, "" + c)
                }
                var j = !aW.support.hrefNormalized && i && a ? h.getAttribute(g, 2) : h.getAttribute(g);
                return j === null ? bK : j
            }
            return aW.style(h, g, c)
        }
    });
    var aZ = /\.(.*)$/,
	cv = function (a) {
	    return a.replace(/[^\w\s\.\|`]/g,
		function (b) {
		    return "\\" + b
		})
	};
    aW.event = {
        add: function (n, d, i, b) {
            if (n.nodeType === 3 || n.nodeType === 8) {
                return
            }
            if (n.setInterval && (n !== bj && !n.frameElement)) {
                n = bj
            }
            var l, h;
            if (i.handler) {
                l = i;
                i = l.handler
            }
            if (!i.guid) {
                i.guid = aW.guid++
            }
            var e = aW.data(n);
            if (!e) {
                return
            }
            var j = e.events = e.events || {},
			c = e.handle,
			c;
            if (!c) {
                e.handle = c = function () {
                    return typeof aW !== "undefined" && !aW.event.triggered ? aW.event.handle.apply(c.elem, arguments) : bK
                }
            }
            c.elem = n;
            d = d.split(" ");
            var g, a = 0,
			k;
            while ((g = d[a++])) {
                h = l ? aW.extend({},
				l) : {
				    handler: i,
				    data: b
				};
                if (g.indexOf(".") > -1) {
                    k = g.split(".");
                    g = k.shift();
                    h.namespace = k.slice(0).sort().join(".")
                } else {
                    k = [];
                    h.namespace = ""
                }
                h.type = g;
                h.guid = i.guid;
                var m = j[g],
				f = aW.event.special[g] || {};
                if (!m) {
                    m = j[g] = [];
                    if (!f.setup || f.setup.call(n, b, k, c) === false) {
                        if (n.addEventListener) {
                            n.addEventListener(g, c, false)
                        } else {
                            if (n.attachEvent) {
                                n.attachEvent("on" + g, c)
                            }
                        }
                    }
                }
                if (f.add) {
                    f.add.call(n, h);
                    if (!h.handler.guid) {
                        h.handler.guid = i.guid
                    }
                }
                m.push(h);
                aW.event.global[g] = true
            }
            n = null
        },
        global: {},
        remove: function (p, g, l, c) {
            if (p.nodeType === 3 || p.nodeType === 8) {
                return
            }
            var s, b, d, j = 0,
			n, e, h, a, f, k, r, o = aW.data(p),
			m = o && o.events;
            if (!o || !m) {
                return
            }
            if (g && g.type) {
                l = g.handler;
                g = g.type
            }
            if (!g || typeof g === "string" && g.charAt(0) === ".") {
                g = g || "";
                for (b in m) {
                    aW.event.remove(p, b + g)
                }
                return
            }
            g = g.split(" ");
            while ((b = g[j++])) {
                r = b;
                k = null;
                n = b.indexOf(".") < 0;
                e = [];
                if (!n) {
                    e = b.split(".");
                    b = e.shift();
                    h = new RegExp("(^|\\.)" + aW.map(e.slice(0).sort(), cv).join("\\.(?:.*\\.)?") + "(\\.|$)")
                }
                f = m[b];
                if (!f) {
                    continue
                }
                if (!l) {
                    for (var i = 0; i < f.length; i++) {
                        k = f[i];
                        if (n || h.test(k.namespace)) {
                            aW.event.remove(p, r, k.handler, i);
                            f.splice(i--, 1)
                        }
                    }
                    continue
                }
                a = aW.event.special[b] || {};
                for (var i = c || 0; i < f.length; i++) {
                    k = f[i];
                    if (l.guid === k.guid) {
                        if (n || h.test(k.namespace)) {
                            if (c == null) {
                                f.splice(i--, 1)
                            }
                            if (a.remove) {
                                a.remove.call(p, k)
                            }
                        }
                        if (c != null) {
                            break
                        }
                    }
                }
                if (f.length === 0 || c != null && f.length === 1) {
                    if (!a.teardown || a.teardown.call(p, e) === false) {
                        a6(p, b, o.handle)
                    }
                    s = null;
                    delete m[b]
                }
            }
            if (aW.isEmptyObject(m)) {
                var q = o.handle;
                if (q) {
                    q.elem = null
                }
                delete o.events;
                delete o.handle;
                if (aW.isEmptyObject(o)) {
                    aW.removeData(p)
                }
            }
        },
        trigger: function (i, a, k) {
            var f = i.type || i,
			l = arguments[3];
            if (!l) {
                i = typeof i === "object" ? i[bb] ? i : aW.extend(aW.Event(f), i) : aW.Event(f);
                if (f.indexOf("!") >= 0) {
                    i.type = f = f.slice(0, -1);
                    i.exclusive = true
                }
                if (!k) {
                    i.stopPropagation();
                    if (aW.event.global[f]) {
                        aW.each(aW.cache,
						function () {
						    if (this.events && this.events[f]) {
						        aW.event.trigger(i, a, this.handle.elem)
						    }
						})
                    }
                }
                if (!k || k.nodeType === 3 || k.nodeType === 8) {
                    return bK
                }
                i.result = bK;
                i.target = k;
                a = aW.makeArray(a);
                a.unshift(i)
            }
            i.currentTarget = k;
            var b = aW.data(k, "handle");
            if (b) {
                b.apply(k, a)
            }
            var g = k.parentNode || k.ownerDocument;
            try {
                if (!(k && k.nodeName && aW.noData[k.nodeName.toLowerCase()])) {
                    if (k["on" + f] && k["on" + f].apply(k, a) === false) {
                        i.result = false
                    }
                }
            } catch (d) { }
            if (!i.isPropagationStopped() && g) {
                aW.event.trigger(i, a, g, true)
            } else {
                if (!i.isDefaultPrevented()) {
                    var c = i.target,
					j, h = aW.nodeName(c, "a") && f === "click",
					e = aW.event.special[f] || {};
                    if ((!e._default || e._default.call(k, i) === false) && !h && !(c && c.nodeName && aW.noData[c.nodeName.toLowerCase()])) {
                        try {
                            if (c[f]) {
                                j = c["on" + f];
                                if (j) {
                                    c["on" + f] = null
                                }
                                aW.event.triggered = true;
                                c[f]()
                            }
                        } catch (d) { }
                        if (j) {
                            c["on" + f] = j
                        }
                        aW.event.triggered = false
                    }
                }
            }
        },
        handle: function (g) {
            var e, i, h, j, f;
            g = arguments[0] = aW.event.fix(g || bj.event);
            g.currentTarget = this;
            e = g.type.indexOf(".") < 0 && !g.exclusive;
            if (!e) {
                h = g.type.split(".");
                g.type = h.shift();
                j = new RegExp("(^|\\.)" + h.slice(0).sort().join("\\.(?:.*\\.)?") + "(\\.|$)")
            }
            var f = aW.data(this, "events"),
			i = f[g.type];
            if (f && i) {
                i = i.slice(0);
                for (var b = 0,
				a = i.length; b < a; b++) {
                    var d = i[b];
                    if (e || j.test(d.namespace)) {
                        g.handler = d.handler;
                        g.data = d.data;
                        g.handleObj = d;
                        var c = d.handler.apply(this, arguments);
                        if (c !== bK) {
                            g.result = c;
                            if (c === false) {
                                g.preventDefault();
                                g.stopPropagation()
                            }
                        }
                        if (g.isImmediatePropagationStopped()) {
                            break
                        }
                    }
                }
            }
            return g.result
        },
        props: "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),
        fix: function (f) {
            if (f[bb]) {
                return f
            }
            var d = f;
            f = aW.Event(d);
            for (var e = this.props.length,
			b; e;) {
                b = this.props[--e];
                f[b] = d[b]
            }
            if (!f.target) {
                f.target = f.srcElement || cw
            }
            if (f.target.nodeType === 3) {
                f.target = f.target.parentNode
            }
            if (!f.relatedTarget && f.fromElement) {
                f.relatedTarget = f.fromElement === f.target ? f.toElement : f.fromElement
            }
            if (f.pageX == null && f.clientX != null) {
                var a = cw.documentElement,
				c = cw.body;
                f.pageX = f.clientX + (a && a.scrollLeft || c && c.scrollLeft || 0) - (a && a.clientLeft || c && c.clientLeft || 0);
                f.pageY = f.clientY + (a && a.scrollTop || c && c.scrollTop || 0) - (a && a.clientTop || c && c.clientTop || 0)
            }
            if (!f.which && ((f.charCode || f.charCode === 0) ? f.charCode : f.keyCode)) {
                f.which = f.charCode || f.keyCode
            }
            if (!f.metaKey && f.ctrlKey) {
                f.metaKey = f.ctrlKey
            }
            if (!f.which && f.button !== bK) {
                f.which = (f.button & 1 ? 1 : (f.button & 2 ? 3 : (f.button & 4 ? 2 : 0)))
            }
            return f
        },
        guid: 100000000,
        proxy: aW.proxy,
        special: {
            ready: {
                setup: aW.bindReady,
                teardown: aW.noop
            },
            live: {
                add: function (a) {
                    aW.event.add(this, a.origType, aW.extend({},
					a, {
					    handler: ck
					}))
                },
                remove: function (b) {
                    var a = true,
					c = b.origType.replace(aZ, "");
                    aW.each(aW.data(this, "events").live || [],
					function () {
					    if (c === this.origType.replace(aZ, "")) {
					        a = false;
					        return false
					    }
					});
                    if (a) {
                        aW.event.remove(this, b.origType, ck)
                    }
                }
            },
            beforeunload: {
                setup: function (c, b, a) {
                    if (this.setInterval) {
                        this.onbeforeunload = a
                    }
                    return false
                },
                teardown: function (b, a) {
                    if (this.onbeforeunload === a) {
                        this.onbeforeunload = null
                    }
                }
            }
        }
    };
    var a6 = cw.removeEventListener ?
	function (b, a, c) {
	    b.removeEventListener(a, c, false)
	} : function (b, a, c) {
	    b.detachEvent("on" + a, c)
	};
    aW.Event = function (a) {
        if (!this.preventDefault) {
            return new aW.Event(a)
        }
        if (a && a.type) {
            this.originalEvent = a;
            this.type = a.type
        } else {
            this.type = a
        }
        this.timeStamp = bp();
        this[bb] = true
    };
    function bt() {
        return false
    }
    function bR() {
        return true
    }
    aW.Event.prototype = {
        preventDefault: function () {
            this.isDefaultPrevented = bR;
            var a = this.originalEvent;
            if (!a) {
                return
            }
            if (a.preventDefault) {
                a.preventDefault()
            }
            a.returnValue = false
        },
        stopPropagation: function () {
            this.isPropagationStopped = bR;
            var a = this.originalEvent;
            if (!a) {
                return
            }
            if (a.stopPropagation) {
                a.stopPropagation()
            }
            a.cancelBubble = true
        },
        stopImmediatePropagation: function () {
            this.isImmediatePropagationStopped = bR;
            this.stopPropagation()
        },
        isDefaultPrevented: bt,
        isPropagationStopped: bt,
        isImmediatePropagationStopped: bt
    };
    var cc = function (b) {
        var a = b.relatedTarget;
        try {
            while (a && a !== this) {
                a = a.parentNode
            }
            if (a !== this) {
                b.type = b.data;
                aW.event.handle.apply(this, arguments)
            }
        } catch (c) { }
    },
	bF = function (a) {
	    a.type = a.data;
	    aW.event.handle.apply(this, arguments)
	};
    aW.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    },
	function (b, a) {
	    aW.event.special[b] = {
	        setup: function (c) {
	            aW.event.add(this, a, c && c.selector ? bF : cc, b)
	        },
	        teardown: function (c) {
	            aW.event.remove(this, a, c && c.selector ? bF : cc)
	        }
	    }
	});
    if (!aW.support.submitBubbles) {
        aW.event.special.submit = {
            setup: function (b, a) {
                if (this.nodeName.toLowerCase() !== "form") {
                    aW.event.add(this, "click.specialSubmit",
					function (c) {
					    var e = c.target,
						d = e.type;
					    if ((d === "submit" || d === "image") && aW(e).closest("form").length) {
					        return bH("submit", this, arguments)
					    }
					});
                    aW.event.add(this, "keypress.specialSubmit",
					function (c) {
					    var e = c.target,
						d = e.type;
					    if ((d === "text" || d === "password") && aW(e).closest("form").length && c.keyCode === 13) {
					        return bH("submit", this, arguments)
					    }
					})
                } else {
                    return false
                }
            },
            teardown: function (a) {
                aW.event.remove(this, ".specialSubmit")
            }
        }
    }
    if (!aW.support.changeBubbles) {
        var bq = /textarea|input|select/i,
		bv, bX = function (b) {
		    var a = b.type,
			c = b.value;
		    if (a === "radio" || a === "checkbox") {
		        c = b.checked
		    } else {
		        if (a === "select-multiple") {
		            c = b.selectedIndex > -1 ? aW.map(b.options,
					function (d) {
					    return d.selected
					}).join("-") : ""
		        } else {
		            if (b.nodeName.toLowerCase() === "select") {
		                c = b.selectedIndex
		            }
		        }
		    }
		    return c
		},
		b8 = function b8(c) {
		    var a = c.target,
			b, d;
		    if (!bq.test(a.nodeName) || a.readOnly) {
		        return
		    }
		    b = aW.data(a, "_change_data");
		    d = bX(a);
		    if (c.type !== "focusout" || a.type !== "radio") {
		        aW.data(a, "_change_data", d)
		    }
		    if (b === bK || d === b) {
		        return
		    }
		    if (b != null || d) {
		        c.type = "change";
		        return aW.event.trigger(c, arguments[1], a)
		    }
		};
        aW.event.special.change = {
            filters: {
                focusout: b8,
                click: function (c) {
                    var b = c.target,
					a = b.type;
                    if (a === "radio" || a === "checkbox" || b.nodeName.toLowerCase() === "select") {
                        return b8.call(this, c)
                    }
                },
                keydown: function (c) {
                    var b = c.target,
					a = b.type;
                    if ((c.keyCode === 13 && b.nodeName.toLowerCase() !== "textarea") || (c.keyCode === 32 && (a === "checkbox" || a === "radio")) || a === "select-multiple") {
                        return b8.call(this, c)
                    }
                },
                beforeactivate: function (b) {
                    var a = b.target;
                    aW.data(a, "_change_data", bX(a))
                }
            },
            setup: function (c, b) {
                if (this.type === "file") {
                    return false
                }
                for (var a in bv) {
                    aW.event.add(this, a + ".specialChange", bv[a])
                }
                return bq.test(this.nodeName)
            },
            teardown: function (a) {
                aW.event.remove(this, ".specialChange");
                return bq.test(this.nodeName)
            }
        };
        bv = aW.event.special.change.filters
    }
    function bH(b, c, a) {
        a[0].type = b;
        return aW.event.handle.apply(c, a)
    }
    if (cw.addEventListener) {
        aW.each({
            focus: "focusin",
            blur: "focusout"
        },
		function (c, a) {
		    aW.event.special[a] = {
		        setup: function () {
		            this.addEventListener(c, b, true)
		        },
		        teardown: function () {
		            this.removeEventListener(c, b, true)
		        }
		    };
		    function b(d) {
		        d = aW.event.fix(d);
		        d.type = a;
		        return aW.event.handle.call(this, d)
		    }
		})
    }
    aW.each(["bind", "one"],
	function (b, a) {
	    aW.fn[a] = function (f, g, e) {
	        if (typeof f === "object") {
	            for (var c in f) {
	                this[a](c, g, f[c], e)
	            }
	            return this
	        }
	        if (aW.isFunction(g)) {
	            e = g;
	            g = bK
	        }
	        var d = a === "one" ? aW.proxy(e,
			function (j) {
			    aW(this).unbind(j, d);
			    return e.apply(this, arguments)
			}) : e;
	        if (f === "unload" && a !== "one") {
	            this.one(f, g, e)
	        } else {
	            for (var i = 0,
				h = this.length; i < h; i++) {
	                aW.event.add(this[i], f, d, g)
	            }
	        }
	        return this
	    }
	});
    aW.fn.extend({
        unbind: function (a, e) {
            if (typeof a === "object" && !a.preventDefault) {
                for (var d in a) {
                    this.unbind(d, a[d])
                }
            } else {
                for (var c = 0,
				b = this.length; c < b; c++) {
                    aW.event.remove(this[c], a, e)
                }
            }
            return this
        },
        delegate: function (a, b, d, c) {
            return this.live(b, d, c, a)
        },
        undelegate: function (a, b, c) {
            if (arguments.length === 0) {
                return this.unbind("live")
            } else {
                return this.die(b, null, c, a)
            }
        },
        trigger: function (a, b) {
            return this.each(function () {
                aW.event.trigger(a, b, this)
            })
        },
        triggerHandler: function (a, c) {
            if (this[0]) {
                var b = aW.Event(a);
                b.preventDefault();
                b.stopPropagation();
                aW.event.trigger(b, c, this[0]);
                return b.result
            }
        },
        toggle: function (c) {
            var a = arguments,
			b = 1;
            while (b < a.length) {
                aW.proxy(c, a[b++])
            }
            return this.click(aW.proxy(c,
			function (e) {
			    var d = (aW.data(this, "lastToggle" + c.guid) || 0) % b;
			    aW.data(this, "lastToggle" + c.guid, d + 1);
			    e.preventDefault();
			    return a[d].apply(this, arguments) || false
			}))
        },
        hover: function (a, b) {
            return this.mouseenter(a).mouseleave(b || a)
        }
    });
    var bB = {
        focus: "focusin",
        blur: "focusout",
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    };
    aW.each(["live", "die"],
	function (b, a) {
	    aW.fn[a] = function (h, e, j, c) {
	        var i, f = 0,
			g, m, k, d = c || this.selector,
			l = c ? this : aW(this.context);
	        if (aW.isFunction(e)) {
	            j = e;
	            e = bK
	        }
	        h = (h || "").split(" ");
	        while ((i = h[f++]) != null) {
	            g = aZ.exec(i);
	            m = "";
	            if (g) {
	                m = g[0];
	                i = i.replace(aZ, "")
	            }
	            if (i === "hover") {
	                h.push("mouseenter" + m, "mouseleave" + m);
	                continue
	            }
	            k = i;
	            if (i === "focus" || i === "blur") {
	                h.push(bB[i] + m);
	                i = i + m
	            } else {
	                i = (bB[i] || i) + m
	            }
	            if (a === "live") {
	                l.each(function () {
	                    aW.event.add(this, b5(i, d), {
	                        data: e,
	                        selector: d,
	                        handler: j,
	                        origType: i,
	                        origHandler: j,
	                        preType: k
	                    })
	                })
	            } else {
	                l.unbind(b5(i, d), j)
	            }
	        }
	        return this
	    }
	});
    function ck(k) {
        var g, l = [],
		j = [],
		f = arguments,
		i,
		e,
		h,
		n,
		b,
		d,
		a,
		c,
		o = aW.data(this, "events");
        if (k.liveFired === this || !o || !o.live || k.button && k.type === "click") {
            return
        }
        k.liveFired = this;
        var m = o.live.slice(0);
        for (b = 0; b < m.length; b++) {
            h = m[b];
            if (h.origType.replace(aZ, "") === k.type) {
                j.push(h.selector)
            } else {
                m.splice(b--, 1)
            }
        }
        e = aW(k.target).closest(j, k.currentTarget);
        for (d = 0, a = e.length; d < a; d++) {
            for (b = 0; b < m.length; b++) {
                h = m[b];
                if (e[d].selector === h.selector) {
                    n = e[d].elem;
                    i = null;
                    if (h.preType === "mouseenter" || h.preType === "mouseleave") {
                        i = aW(k.relatedTarget).closest(h.selector)[0]
                    }
                    if (!i || i !== n) {
                        l.push({
                            elem: n,
                            handleObj: h
                        })
                    }
                }
            }
        }
        for (d = 0, a = l.length; d < a; d++) {
            e = l[d];
            k.currentTarget = e.elem;
            k.data = e.handleObj.data;
            k.handleObj = e.handleObj;
            if (e.handleObj.origHandler.apply(e.elem, f) === false) {
                g = false;
                break
            }
        }
        return g
    }
    function b5(b, a) {
        return "live." + (b && b !== "*" ? b + "." : "") + a.replace(/\./g, "`").replace(/ /g, "&")
    }
    aW.each(("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error").split(" "),
	function (b, a) {
	    aW.fn[a] = function (c) {
	        return c ? this.bind(a, c) : this.trigger(a)
	    };
	    if (aW.attrFn) {
	        aW.attrFn[a] = true
	    }
	});
    if (bj.attachEvent && !bj.addEventListener) {
        bj.attachEvent("onunload",
		function () {
		    for (var b in aW.cache) {
		        if (aW.cache[b].handle) {
		            try {
		                aW.event.remove(aW.cache[b].handle.elem)
		            } catch (a) { }
		        }
		    }
		})
    } (function () {
        var h = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^[\]]*\]|['"][^'"]*['"]|[^[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,
		i = 0,
		o = Object.prototype.toString,
		c = false,
		b = true;[0, 0].sort(function () {
		    b = false;
		    return 0
		});
        var m = function (x, s, A, B) {
            A = A || [];
            var D = s = s || cw;
            if (s.nodeType !== 1 && s.nodeType !== 9) {
                return []
            }
            if (!x || typeof x !== "string") {
                return A
            }
            var y = [],
			u,
			F,
			I,
			t,
			w = true,
			v = n(s),
			C = x;
            while ((h.exec(""), u = h.exec(C)) !== null) {
                C = u[3];
                y.push(u[1]);
                if (u[2]) {
                    t = u[3];
                    break
                }
            }
            if (y.length > 1 && d.exec(x)) {
                if (y.length === 2 && e.relative[y[0]]) {
                    F = p(y[0] + y[1], s)
                } else {
                    F = e.relative[y[0]] ? [s] : m(y.shift(), s);
                    while (y.length) {
                        x = y.shift();
                        if (e.relative[x]) {
                            x += y.shift()
                        }
                        F = p(x, F)
                    }
                }
            } else {
                if (!B && y.length > 1 && s.nodeType === 9 && !v && e.match.ID.test(y[0]) && !e.match.ID.test(y[y.length - 1])) {
                    var E = m.find(y.shift(), s, v);
                    s = E.expr ? m.filter(E.expr, E.set)[0] : E.set[0]
                }
                if (s) {
                    var E = B ? {
                        expr: y.pop(),
                        set: g(B)
                    } : m.find(y.pop(), y.length === 1 && (y[0] === "~" || y[0] === "+") && s.parentNode ? s.parentNode : s, v);
                    F = E.expr ? m.filter(E.expr, E.set) : E.set;
                    if (y.length > 0) {
                        I = g(F)
                    } else {
                        w = false
                    }
                    while (y.length) {
                        var H = y.pop(),
						G = H;
                        if (!e.relative[H]) {
                            H = ""
                        } else {
                            G = y.pop()
                        }
                        if (G == null) {
                            G = s
                        }
                        e.relative[H](I, G, v)
                    }
                } else {
                    I = y = []
                }
            }
            if (!I) {
                I = F
            }
            if (!I) {
                m.error(H || x)
            }
            if (o.call(I) === "[object Array]") {
                if (!w) {
                    A.push.apply(A, I)
                } else {
                    if (s && s.nodeType === 1) {
                        for (var z = 0; I[z] != null; z++) {
                            if (I[z] && (I[z] === true || I[z].nodeType === 1 && f(s, I[z]))) {
                                A.push(F[z])
                            }
                        }
                    } else {
                        for (var z = 0; I[z] != null; z++) {
                            if (I[z] && I[z].nodeType === 1) {
                                A.push(F[z])
                            }
                        }
                    }
                }
            } else {
                g(I, A)
            }
            if (t) {
                m(t, D, A, B);
                m.uniqueSort(A)
            }
            return A
        };
        m.uniqueSort = function (t) {
            if (j) {
                c = b;
                t.sort(j);
                if (c) {
                    for (var s = 1; s < t.length; s++) {
                        if (t[s] === t[s - 1]) {
                            t.splice(s--, 1)
                        }
                    }
                }
            }
            return t
        };
        m.matches = function (s, t) {
            return m(s, null, null, t)
        };
        m.find = function (z, s, A) {
            var y, w;
            if (!z) {
                return []
            }
            for (var v = 0,
			u = e.order.length; v < u; v++) {
                var x = e.order[v],
				w;
                if ((w = e.leftMatch[x].exec(z))) {
                    var t = w[1];
                    w.splice(1, 1);
                    if (t.substr(t.length - 1) !== "\\") {
                        w[1] = (w[1] || "").replace(/\\/g, "");
                        y = e.find[x](w, s, A);
                        if (y != null) {
                            z = z.replace(e.match[x], "");
                            break
                        }
                    }
                }
            }
            if (!y) {
                y = s.getElementsByTagName("*")
            }
            return {
                set: y,
                expr: z
            }
        };
        m.filter = function (D, C, G, w) {
            var u = D,
			I = [],
			A = C,
			y,
			s,
			z = C && C[0] && n(C[0]);
            while (D && C.length) {
                for (var B in e.filter) {
                    if ((y = e.leftMatch[B].exec(D)) != null && y[2]) {
                        var t = e.filter[B],
						H,
						F,
						v = y[1];
                        s = false;
                        y.splice(1, 1);
                        if (v.substr(v.length - 1) === "\\") {
                            continue
                        }
                        if (A === I) {
                            I = []
                        }
                        if (e.preFilter[B]) {
                            y = e.preFilter[B](y, A, G, I, w, z);
                            if (!y) {
                                s = H = true
                            } else {
                                if (y === true) {
                                    continue
                                }
                            }
                        }
                        if (y) {
                            for (var x = 0; (F = A[x]) != null; x++) {
                                if (F) {
                                    H = t(F, y, x, A);
                                    var E = w ^ !!H;
                                    if (G && H != null) {
                                        if (E) {
                                            s = true
                                        } else {
                                            A[x] = false
                                        }
                                    } else {
                                        if (E) {
                                            I.push(F);
                                            s = true
                                        }
                                    }
                                }
                            }
                        }
                        if (H !== bK) {
                            if (!G) {
                                A = I
                            }
                            D = D.replace(e.match[B], "");
                            if (!s) {
                                return []
                            }
                            break
                        }
                    }
                }
                if (D === u) {
                    if (s == null) {
                        m.error(D)
                    } else {
                        break
                    }
                }
                u = D
            }
            return A
        };
        m.error = function (s) {
            throw "Syntax error, unrecognized expression: " + s
        };
        var e = m.selectors = {
            order: ["ID", "NAME", "TAG"],
            match: {
                ID: /#((?:[\w\u00c0-\uFFFF-]|\\.)+)/,
                CLASS: /\.((?:[\w\u00c0-\uFFFF-]|\\.)+)/,
                NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF-]|\\.)+)['"]*\]/,
                ATTR: /\[\s*((?:[\w\u00c0-\uFFFF-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
                TAG: /^((?:[\w\u00c0-\uFFFF\*-]|\\.)+)/,
                CHILD: /:(only|nth|last|first)-child(?:\((even|odd|[\dn+-]*)\))?/,
                POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^-]|$)/,
                PSEUDO: /:((?:[\w\u00c0-\uFFFF-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
            },
            leftMatch: {},
            attrMap: {
                "class": "className",
                "for": "htmlFor"
            },
            attrHandle: {
                href: function (s) {
                    return s.getAttribute("href")
                }
            },
            relative: {
                "+": function (y, t) {
                    var v = typeof t === "string",
					x = v && !/\W/.test(t),
					z = v && !x;
                    if (x) {
                        t = t.toLowerCase()
                    }
                    for (var u = 0,
					s = y.length,
					w; u < s; u++) {
                        if ((w = y[u])) {
                            while ((w = w.previousSibling) && w.nodeType !== 1) { }
                            y[u] = z || w && w.nodeName.toLowerCase() === t ? w || false : w === t
                        }
                    }
                    if (z) {
                        m.filter(t, y, true)
                    }
                },
                ">": function (y, t) {
                    var w = typeof t === "string";
                    if (w && !/\W/.test(t)) {
                        t = t.toLowerCase();
                        for (var u = 0,
						s = y.length; u < s; u++) {
                            var x = y[u];
                            if (x) {
                                var v = x.parentNode;
                                y[u] = v.nodeName.toLowerCase() === t ? v : false
                            }
                        }
                    } else {
                        for (var u = 0,
						s = y.length; u < s; u++) {
                            var x = y[u];
                            if (x) {
                                y[u] = w ? x.parentNode : x.parentNode === t
                            }
                        }
                        if (w) {
                            m.filter(t, y, true)
                        }
                    }
                },
                "": function (v, t, x) {
                    var u = i++,
					s = q;
                    if (typeof t === "string" && !/\W/.test(t)) {
                        var w = t = t.toLowerCase();
                        s = k
                    }
                    s("parentNode", t, u, v, w, x)
                },
                "~": function (v, t, x) {
                    var u = i++,
					s = q;
                    if (typeof t === "string" && !/\W/.test(t)) {
                        var w = t = t.toLowerCase();
                        s = k
                    }
                    s("previousSibling", t, u, v, w, x)
                }
            },
            find: {
                ID: function (t, u, v) {
                    if (typeof u.getElementById !== "undefined" && !v) {
                        var s = u.getElementById(t[1]);
                        return s ? [s] : []
                    }
                },
                NAME: function (u, x) {
                    if (typeof x.getElementsByName !== "undefined") {
                        var t = [],
						w = x.getElementsByName(u[1]);
                        for (var v = 0,
						s = w.length; v < s; v++) {
                            if (w[v].getAttribute("name") === u[1]) {
                                t.push(w[v])
                            }
                        }
                        return t.length === 0 ? null : t
                    }
                },
                TAG: function (s, t) {
                    return t.getElementsByTagName(s[1])
                }
            },
            preFilter: {
                CLASS: function (v, t, u, s, y, z) {
                    v = " " + v[1].replace(/\\/g, "") + " ";
                    if (z) {
                        return v
                    }
                    for (var w = 0,
					x; (x = t[w]) != null; w++) {
                        if (x) {
                            if (y ^ (x.className && (" " + x.className + " ").replace(/[\t\n]/g, " ").indexOf(v) >= 0)) {
                                if (!u) {
                                    s.push(x)
                                }
                            } else {
                                if (u) {
                                    t[w] = false
                                }
                            }
                        }
                    }
                    return false
                },
                ID: function (s) {
                    return s[1].replace(/\\/g, "")
                },
                TAG: function (t, s) {
                    return t[1].toLowerCase()
                },
                CHILD: function (s) {
                    if (s[1] === "nth") {
                        var t = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(s[2] === "even" && "2n" || s[2] === "odd" && "2n+1" || !/\D/.test(s[2]) && "0n+" + s[2] || s[2]);
                        s[2] = (t[1] + (t[2] || 1)) - 0;
                        s[3] = t[3] - 0
                    }
                    s[0] = i++;
                    return s
                },
                ATTR: function (w, t, u, s, x, y) {
                    var v = w[1].replace(/\\/g, "");
                    if (!y && e.attrMap[v]) {
                        w[1] = e.attrMap[v]
                    }
                    if (w[2] === "~=") {
                        w[4] = " " + w[4] + " "
                    }
                    return w
                },
                PSEUDO: function (w, t, u, s, x) {
                    if (w[1] === "not") {
                        if ((h.exec(w[3]) || "").length > 1 || /^\w/.test(w[3])) {
                            w[3] = m(w[3], null, null, t)
                        } else {
                            var v = m.filter(w[3], t, u, true ^ x);
                            if (!u) {
                                s.push.apply(s, v)
                            }
                            return false
                        }
                    } else {
                        if (e.match.POS.test(w[0]) || e.match.CHILD.test(w[0])) {
                            return true
                        }
                    }
                    return w
                },
                POS: function (s) {
                    s.unshift(true);
                    return s
                }
            },
            filters: {
                enabled: function (s) {
                    return s.disabled === false && s.type !== "hidden"
                },
                disabled: function (s) {
                    return s.disabled === true
                },
                checked: function (s) {
                    return s.checked === true
                },
                selected: function (s) {
                    s.parentNode.selectedIndex;
                    return s.selected === true
                },
                parent: function (s) {
                    return !!s.firstChild
                },
                empty: function (s) {
                    return !s.firstChild
                },
                has: function (u, t, s) {
                    return !!m(s[3], u).length
                },
                header: function (s) {
                    return /h\d/i.test(s.nodeName)
                },
                text: function (s) {
                    return "text" === s.type
                },
                radio: function (s) {
                    return "radio" === s.type
                },
                checkbox: function (s) {
                    return "checkbox" === s.type
                },
                file: function (s) {
                    return "file" === s.type
                },
                password: function (s) {
                    return "password" === s.type
                },
                submit: function (s) {
                    return "submit" === s.type
                },
                image: function (s) {
                    return "image" === s.type
                },
                reset: function (s) {
                    return "reset" === s.type
                },
                button: function (s) {
                    return "button" === s.type || s.nodeName.toLowerCase() === "button"
                },
                input: function (s) {
                    return /input|select|textarea|button/i.test(s.nodeName)
                }
            },
            setFilters: {
                first: function (t, s) {
                    return s === 0
                },
                last: function (u, t, s, v) {
                    return t === v.length - 1
                },
                even: function (t, s) {
                    return s % 2 === 0
                },
                odd: function (t, s) {
                    return s % 2 === 1
                },
                lt: function (u, t, s) {
                    return t < s[3] - 0
                },
                gt: function (u, t, s) {
                    return t > s[3] - 0
                },
                nth: function (u, t, s) {
                    return s[3] - 0 === t
                },
                eq: function (u, t, s) {
                    return s[3] - 0 === t
                }
            },
            filter: {
                PSEUDO: function (y, u, v, z) {
                    var t = u[1],
					w = e.filters[t];
                    if (w) {
                        return w(y, v, u, z)
                    } else {
                        if (t === "contains") {
                            return (y.textContent || y.innerText || l([y]) || "").indexOf(u[3]) >= 0
                        } else {
                            if (t === "not") {
                                var x = u[3];
                                for (var v = 0,
								s = x.length; v < s; v++) {
                                    if (x[v] === y) {
                                        return false
                                    }
                                }
                                return true
                            } else {
                                m.error("Syntax error, unrecognized expression: " + t)
                            }
                        }
                    }
                },
                CHILD: function (s, v) {
                    var y = v[1],
					t = s;
                    switch (y) {
                        case "only":
                        case "first":
                            while ((t = t.previousSibling)) {
                                if (t.nodeType === 1) {
                                    return false
                                }
                            }
                            if (y === "first") {
                                return true
                            }
                            t = s;
                        case "last":
                            while ((t = t.nextSibling)) {
                                if (t.nodeType === 1) {
                                    return false
                                }
                            }
                            return true;
                        case "nth":
                            var u = v[2],
                            B = v[3];
                            if (u === 1 && B === 0) {
                                return true
                            }
                            var x = v[0],
                            A = s.parentNode;
                            if (A && (A.sizcache !== x || !s.nodeIndex)) {
                                var w = 0;
                                for (t = A.firstChild; t; t = t.nextSibling) {
                                    if (t.nodeType === 1) {
                                        t.nodeIndex = ++w
                                    }
                                }
                                A.sizcache = x
                            }
                            var z = s.nodeIndex - B;
                            if (u === 0) {
                                return z === 0
                            } else {
                                return (z % u === 0 && z / u >= 0)
                            }
                    }
                },
                ID: function (t, s) {
                    return t.nodeType === 1 && t.getAttribute("id") === s
                },
                TAG: function (t, s) {
                    return (s === "*" && t.nodeType === 1) || t.nodeName.toLowerCase() === s
                },
                CLASS: function (t, s) {
                    return (" " + (t.className || t.getAttribute("class")) + " ").indexOf(s) > -1
                },
                ATTR: function (x, v) {
                    var u = v[1],
					s = e.attrHandle[u] ? e.attrHandle[u](x) : x[u] != null ? x[u] : x.getAttribute(u),
					y = s + "",
					w = v[2],
					t = v[4];
                    return s == null ? w === "!=" : w === "=" ? y === t : w === "*=" ? y.indexOf(t) >= 0 : w === "~=" ? (" " + y + " ").indexOf(t) >= 0 : !t ? y && s !== false : w === "!=" ? y !== t : w === "^=" ? y.indexOf(t) === 0 : w === "$=" ? y.substr(y.length - t.length) === t : w === "|=" ? y === t || y.substr(0, t.length + 1) === t + "-" : false
                },
                POS: function (w, t, u, x) {
                    var s = t[2],
					v = e.setFilters[s];
                    if (v) {
                        return v(w, u, t, x)
                    }
                }
            }
        };
        var d = e.match.POS;
        for (var a in e.match) {
            e.match[a] = new RegExp(e.match[a].source + /(?![^\[]*\])(?![^\(]*\))/.source);
            e.leftMatch[a] = new RegExp(/(^(?:.|\r|\n)*?)/.source + e.match[a].source.replace(/\\(\d+)/g,
			function (t, s) {
			    return "\\" + (s - 0 + 1)
			}))
        }
        var g = function (t, s) {
            t = Array.prototype.slice.call(t, 0);
            if (s) {
                s.push.apply(s, t);
                return s
            }
            return t
        };
        try {
            Array.prototype.slice.call(cw.documentElement.childNodes, 0)[0].nodeType
        } catch (r) {
            g = function (w, v) {
                var t = v || [];
                if (o.call(w) === "[object Array]") {
                    Array.prototype.push.apply(t, w)
                } else {
                    if (typeof w.length === "number") {
                        for (var u = 0,
						s = w.length; u < s; u++) {
                            t.push(w[u])
                        }
                    } else {
                        for (var u = 0; w[u]; u++) {
                            t.push(w[u])
                        }
                    }
                }
                return t
            }
        }
        var j;
        if (cw.documentElement.compareDocumentPosition) {
            j = function (t, s) {
                if (!t.compareDocumentPosition || !s.compareDocumentPosition) {
                    if (t == s) {
                        c = true
                    }
                    return t.compareDocumentPosition ? -1 : 1
                }
                var u = t.compareDocumentPosition(s) & 4 ? -1 : t === s ? 0 : 1;
                if (u === 0) {
                    c = true
                }
                return u
            }
        } else {
            if ("sourceIndex" in cw.documentElement) {
                j = function (t, s) {
                    if (!t.sourceIndex || !s.sourceIndex) {
                        if (t == s) {
                            c = true
                        }
                        return t.sourceIndex ? -1 : 1
                    }
                    var u = t.sourceIndex - s.sourceIndex;
                    if (u === 0) {
                        c = true
                    }
                    return u
                }
            } else {
                if (cw.createRange) {
                    j = function (v, t) {
                        if (!v.ownerDocument || !t.ownerDocument) {
                            if (v == t) {
                                c = true
                            }
                            return v.ownerDocument ? -1 : 1
                        }
                        var u = v.ownerDocument.createRange(),
						s = t.ownerDocument.createRange();
                        u.setStart(v, 0);
                        u.setEnd(v, 0);
                        s.setStart(t, 0);
                        s.setEnd(t, 0);
                        var w = u.compareBoundaryPoints(Range.START_TO_END, s);
                        if (w === 0) {
                            c = true
                        }
                        return w
                    }
                }
            }
        }
        function l(s) {
            var t = "",
			v;
            for (var u = 0; s[u]; u++) {
                v = s[u];
                if (v.nodeType === 3 || v.nodeType === 4) {
                    t += v.nodeValue
                } else {
                    if (v.nodeType !== 8) {
                        t += l(v.childNodes)
                    }
                }
            }
            return t
        } (function () {
            var t = cw.createElement("div"),
			u = "script" + (new Date).getTime();
            t.innerHTML = "<a name='" + u + "'/>";
            var s = cw.documentElement;
            s.insertBefore(t, s.firstChild);
            if (cw.getElementById(u)) {
                e.find.ID = function (w, x, y) {
                    if (typeof x.getElementById !== "undefined" && !y) {
                        var v = x.getElementById(w[1]);
                        return v ? v.id === w[1] || typeof v.getAttributeNode !== "undefined" && v.getAttributeNode("id").nodeValue === w[1] ? [v] : bK : []
                    }
                };
                e.filter.ID = function (x, v) {
                    var w = typeof x.getAttributeNode !== "undefined" && x.getAttributeNode("id");
                    return x.nodeType === 1 && w && w.nodeValue === v
                }
            }
            s.removeChild(t);
            s = t = null
        })(); (function () {
            var s = cw.createElement("div");
            s.appendChild(cw.createComment(""));
            if (s.getElementsByTagName("*").length > 0) {
                e.find.TAG = function (t, x) {
                    var w = x.getElementsByTagName(t[1]);
                    if (t[1] === "*") {
                        var v = [];
                        for (var u = 0; w[u]; u++) {
                            if (w[u].nodeType === 1) {
                                v.push(w[u])
                            }
                        }
                        w = v
                    }
                    return w
                }
            }
            s.innerHTML = "<a href='#'></a>";
            if (s.firstChild && typeof s.firstChild.getAttribute !== "undefined" && s.firstChild.getAttribute("href") !== "#") {
                e.attrHandle.href = function (t) {
                    return t.getAttribute("href", 2)
                }
            }
            s = null
        })();
        if (cw.querySelectorAll) {
            (function () {
                var s = m,
				u = cw.createElement("div");
                u.innerHTML = "<p class='TEST'></p>";
                if (u.querySelectorAll && u.querySelectorAll(".TEST").length === 0) {
                    return
                }
                m = function (y, x, v, w) {
                    x = x || cw;
                    if (!w && x.nodeType === 9 && !n(x)) {
                        try {
                            return g(x.querySelectorAll(y), v)
                        } catch (z) { }
                    }
                    return s(y, x, v, w)
                };
                for (var t in s) {
                    m[t] = s[t]
                }
                u = null
            })()
        } (function () {
            var s = cw.createElement("div");
            s.innerHTML = "<div class='test e'></div><div class='test'></div>";
            if (!s.getElementsByClassName || s.getElementsByClassName("e").length === 0) {
                return
            }
            s.lastChild.className = "e";
            if (s.getElementsByClassName("e").length === 1) {
                return
            }
            e.order.splice(1, 0, "CLASS");
            e.find.CLASS = function (t, u, v) {
                if (typeof u.getElementsByClassName !== "undefined" && !v) {
                    return u.getElementsByClassName(t[1])
                }
            };
            s = null
        })();
        function k(t, y, x, B, z, A) {
            for (var v = 0,
			u = B.length; v < u; v++) {
                var s = B[v];
                if (s) {
                    s = s[t];
                    var w = false;
                    while (s) {
                        if (s.sizcache === x) {
                            w = B[s.sizset];
                            break
                        }
                        if (s.nodeType === 1 && !A) {
                            s.sizcache = x;
                            s.sizset = v
                        }
                        if (s.nodeName.toLowerCase() === y) {
                            w = s;
                            break
                        }
                        s = s[t]
                    }
                    B[v] = w
                }
            }
        }
        function q(t, y, x, B, z, A) {
            for (var v = 0,
			u = B.length; v < u; v++) {
                var s = B[v];
                if (s) {
                    s = s[t];
                    var w = false;
                    while (s) {
                        if (s.sizcache === x) {
                            w = B[s.sizset];
                            break
                        }
                        if (s.nodeType === 1) {
                            if (!A) {
                                s.sizcache = x;
                                s.sizset = v
                            }
                            if (typeof y !== "string") {
                                if (s === y) {
                                    w = true;
                                    break
                                }
                            } else {
                                if (m.filter(y, [s]).length > 0) {
                                    w = s;
                                    break
                                }
                            }
                        }
                        s = s[t]
                    }
                    B[v] = w
                }
            }
        }
        var f = cw.compareDocumentPosition ?
		function (t, s) {
		    return !!(t.compareDocumentPosition(s) & 16)
		} : function (t, s) {
		    return t !== s && (t.contains ? t.contains(s) : true)
		};
        var n = function (s) {
            var t = (s ? s.ownerDocument || s : 0).documentElement;
            return t ? t.nodeName !== "HTML" : false
        };
        var p = function (s, z) {
            var v = [],
			w = "",
			x,
			u = z.nodeType ? [z] : z;
            while ((x = e.match.PSEUDO.exec(s))) {
                w += x[0];
                s = s.replace(e.match.PSEUDO, "")
            }
            s = e.relative[s] ? s + "*" : s;
            for (var y = 0,
			t = u.length; y < t; y++) {
                m(s, u[y], v)
            }
            return m.filter(w, v)
        };
        aW.find = m;
        aW.expr = m.selectors;
        aW.expr[":"] = aW.expr.filters;
        aW.unique = m.uniqueSort;
        aW.text = l;
        aW.isXMLDoc = n;
        aW.contains = f;
        return;
        bj.Sizzle = m
    })();
    var b6 = /Until$/,
	cq = /^(?:parents|prevUntil|prevAll)/,
	bh = /,/,
	bQ = Array.prototype.slice;
    var ba = function (d, c, a) {
        if (aW.isFunction(c)) {
            return aW.grep(d,
			function (f, e) {
			    return !!c.call(f, e, f) === a
			})
        } else {
            if (c.nodeType) {
                return aW.grep(d,
				function (f, e) {
				    return (f === c) === a
				})
            } else {
                if (typeof c === "string") {
                    var b = aW.grep(d,
					function (e) {
					    return e.nodeType === 1
					});
                    if (bC.test(c)) {
                        return aW.filter(c, b, !a)
                    } else {
                        c = aW.filter(c, b)
                    }
                }
            }
        }
        return aW.grep(d,
		function (f, e) {
		    return (aW.inArray(f, c) >= 0) === a
		})
    };
    aW.fn.extend({
        find: function (d) {
            var f = this.pushStack("", "find", d),
			b = 0;
            for (var g = 0,
			e = this.length; g < e; g++) {
                b = f.length;
                aW.find(d, this[g], f);
                if (g > 0) {
                    for (var c = b; c < f.length; c++) {
                        for (var a = 0; a < b; a++) {
                            if (f[a] === f[c]) {
                                f.splice(c--, 1);
                                break
                            }
                        }
                    }
                }
            }
            return f
        },
        has: function (b) {
            var a = aW(b);
            return this.filter(function () {
                for (var d = 0,
				c = a.length; d < c; d++) {
                    if (aW.contains(this, a[d])) {
                        return true
                    }
                }
            })
        },
        not: function (a) {
            return this.pushStack(ba(this, a, false), "not", a)
        },
        filter: function (a) {
            return this.pushStack(ba(this, a, true), "filter", a)
        },
        is: function (a) {
            return !!a && aW.filter(a, this).length > 0
        },
        closest: function (f, g) {
            if (aW.isArray(f)) {
                var c = [],
				e = this[0],
				b,
				a = {},
				i;
                if (e && f.length) {
                    for (var j = 0,
					h = f.length; j < h; j++) {
                        i = f[j];
                        if (!a[i]) {
                            a[i] = aW.expr.match.POS.test(i) ? aW(i, g || this.context) : i
                        }
                    }
                    while (e && e.ownerDocument && e !== g) {
                        for (i in a) {
                            b = a[i];
                            if (b.jquery ? b.index(e) > -1 : aW(e).is(b)) {
                                c.push({
                                    selector: i,
                                    elem: e
                                });
                                delete a[i]
                            }
                        }
                        e = e.parentNode
                    }
                }
                return c
            }
            var d = aW.expr.match.POS.test(f) ? aW(f, g || this.context) : null;
            return this.map(function (k, l) {
                while (l && l.ownerDocument && l !== g) {
                    if (d ? d.index(l) > -1 : aW(l).is(f)) {
                        return l
                    }
                    l = l.parentNode
                }
                return null
            })
        },
        index: function (a) {
            if (!a || typeof a === "string") {
                return aW.inArray(this[0], a ? aW(a) : this.parent().children())
            }
            return aW.inArray(a.jquery ? a[0] : a, this)
        },
        add: function (a, b) {
            var d = typeof a === "string" ? aW(a, b || this.context) : aW.makeArray(a),
			c = aW.merge(this.get(), d);
            return this.pushStack(cr(d[0]) || cr(c[0]) ? c : aW.unique(c))
        },
        andSelf: function () {
            return this.add(this.prevObject)
        }
    });
    function cr(a) {
        return !a || !a.parentNode || a.parentNode.nodeType === 11
    }
    aW.each({
        parent: function (b) {
            var a = b.parentNode;
            return a && a.nodeType !== 11 ? a : null
        },
        parents: function (a) {
            return aW.dir(a, "parentNode")
        },
        parentsUntil: function (b, a, c) {
            return aW.dir(b, "parentNode", c)
        },
        next: function (a) {
            return aW.nth(a, 2, "nextSibling")
        },
        prev: function (a) {
            return aW.nth(a, 2, "previousSibling")
        },
        nextAll: function (a) {
            return aW.dir(a, "nextSibling")
        },
        prevAll: function (a) {
            return aW.dir(a, "previousSibling")
        },
        nextUntil: function (b, a, c) {
            return aW.dir(b, "nextSibling", c)
        },
        prevUntil: function (b, a, c) {
            return aW.dir(b, "previousSibling", c)
        },
        siblings: function (a) {
            return aW.sibling(a.parentNode.firstChild, a)
        },
        children: function (a) {
            return aW.sibling(a.firstChild)
        },
        contents: function (a) {
            return aW.nodeName(a, "iframe") ? a.contentDocument || a.contentWindow.document : aW.makeArray(a.childNodes)
        }
    },
	function (a, b) {
	    aW.fn[a] = function (c, d) {
	        var e = aW.map(this, b, c);
	        if (!b6.test(a)) {
	            d = c
	        }
	        if (d && typeof d === "string") {
	            e = aW.filter(d, e)
	        }
	        e = this.length > 1 ? aW.unique(e) : e;
	        if ((this.length > 1 || bh.test(d)) && cq.test(a)) {
	            e = e.reverse()
	        }
	        return this.pushStack(e, a, bQ.call(arguments).join(","))
	    }
	});
    aW.extend({
        filter: function (c, a, b) {
            if (b) {
                c = ":not(" + c + ")"
            }
            return aW.find.matches(c, a)
        },
        dir: function (d, c, a) {
            var b = [],
			e = d[c];
            while (e && e.nodeType !== 9 && (a === bK || e.nodeType !== 1 || !aW(e).is(a))) {
                if (e.nodeType === 1) {
                    b.push(e)
                }
                e = e[c]
            }
            return b
        },
        nth: function (a, b, d, e) {
            b = b || 1;
            var c = 0;
            for (; a; a = a[d]) {
                if (a.nodeType === 1 && ++c === b) {
                    break
                }
            }
            return a
        },
        sibling: function (c, b) {
            var a = [];
            for (; c; c = c.nextSibling) {
                if (c.nodeType === 1 && c !== b) {
                    a.push(c)
                }
            }
            return a
        }
    });
    var cg = / jQuery\d+="(?:\d+|null)"/g,
	cs = /^\s+/,
	bU = /(<([\w:]+)[^>]*?)\/>/g,
	bg = /^(?:area|br|col|embed|hr|img|input|link|meta|param)$/i,
	bL = /<([\w:]+)/,
	ch = /<tbody/i,
	b2 = /<|&#?\w+;/,
	bO = /<script|<object|<embed|<option|<style/i,
	b3 = /checked\s*(?:[^=]|=\s*.checked.)/i,
	b9 = function (b, c, a) {
	    return bg.test(a) ? b : c + "></" + a + ">"
	},
	aY = {
	    option: [1, "<select multiple='multiple'>", "</select>"],
	    legend: [1, "<fieldset>", "</fieldset>"],
	    thead: [1, "<table>", "</table>"],
	    tr: [2, "<table><tbody>", "</tbody></table>"],
	    td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
	    col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
	    area: [1, "<map>", "</map>"],
	    _default: [0, "", ""]
	};
    aY.optgroup = aY.option;
    aY.tbody = aY.tfoot = aY.colgroup = aY.caption = aY.thead;
    aY.th = aY.td;
    if (!aW.support.htmlSerialize) {
        aY._default = [1, "div<div>", "</div>"]
    }
    aW.fn.extend({
        text: function (a) {
            if (aW.isFunction(a)) {
                return this.each(function (c) {
                    var b = aW(this);
                    b.text(a.call(this, c, b.text()))
                })
            }
            if (typeof a !== "object" && a !== bK) {
                return this.empty().append((this[0] && this[0].ownerDocument || cw).createTextNode(a))
            }
            return aW.text(this)
        },
        wrapAll: function (a) {
            if (aW.isFunction(a)) {
                return this.each(function (c) {
                    aW(this).wrapAll(a.call(this, c))
                })
            }
            if (this[0]) {
                var b = aW(a, this[0].ownerDocument).eq(0).clone(true);
                if (this[0].parentNode) {
                    b.insertBefore(this[0])
                }
                b.map(function () {
                    var c = this;
                    while (c.firstChild && c.firstChild.nodeType === 1) {
                        c = c.firstChild
                    }
                    return c
                }).append(this)
            }
            return this
        },
        wrapInner: function (a) {
            if (aW.isFunction(a)) {
                return this.each(function (b) {
                    aW(this).wrapInner(a.call(this, b))
                })
            }
            return this.each(function () {
                var b = aW(this),
				c = b.contents();
                if (c.length) {
                    c.wrapAll(a)
                } else {
                    b.append(a)
                }
            })
        },
        wrap: function (a) {
            return this.each(function () {
                aW(this).wrapAll(a)
            })
        },
        unwrap: function () {
            return this.parent().each(function () {
                if (!aW.nodeName(this, "body")) {
                    aW(this).replaceWith(this.childNodes)
                }
            }).end()
        },
        append: function () {
            return this.domManip(arguments, true,
			function (a) {
			    if (this.nodeType === 1) {
			        this.appendChild(a)
			    }
			})
        },
        prepend: function () {
            return this.domManip(arguments, true,
			function (a) {
			    if (this.nodeType === 1) {
			        this.insertBefore(a, this.firstChild)
			    }
			})
        },
        before: function () {
            if (this[0] && this[0].parentNode) {
                return this.domManip(arguments, false,
				function (b) {
				    this.parentNode.insertBefore(b, this)
				})
            } else {
                if (arguments.length) {
                    var a = aW(arguments[0]);
                    a.push.apply(a, this.toArray());
                    return this.pushStack(a, "before", arguments)
                }
            }
        },
        after: function () {
            if (this[0] && this[0].parentNode) {
                return this.domManip(arguments, false,
				function (b) {
				    this.parentNode.insertBefore(b, this.nextSibling)
				})
            } else {
                if (arguments.length) {
                    var a = this.pushStack(this, "after", arguments);
                    a.push.apply(a, aW(arguments[0]).toArray());
                    return a
                }
            }
        },
        remove: function (a, d) {
            for (var b = 0,
			c; (c = this[b]) != null; b++) {
                if (!a || aW.filter(a, [c]).length) {
                    if (!d && c.nodeType === 1) {
                        aW.cleanData(c.getElementsByTagName("*"));
                        aW.cleanData([c])
                    }
                    if (c.parentNode) {
                        c.parentNode.removeChild(c)
                    }
                }
            }
            return this
        },
        empty: function () {
            for (var a = 0,
			b; (b = this[a]) != null; a++) {
                if (b.nodeType === 1) {
                    aW.cleanData(b.getElementsByTagName("*"))
                }
                while (b.firstChild) {
                    b.removeChild(b.firstChild)
                }
            }
            return this
        },
        clone: function (b) {
            var a = this.map(function () {
                if (!aW.support.noCloneEvent && !aW.isXMLDoc(this)) {
                    var e = this.outerHTML,
					d = this.ownerDocument;
                    if (!e) {
                        var c = d.createElement("div");
                        c.appendChild(this.cloneNode(true));
                        e = c.innerHTML
                    }
                    return aW.clean([e.replace(cg, "").replace(/=([^="'>\s]+\/)>/g, '="$1">').replace(cs, "")], d)[0]
                } else {
                    return this.cloneNode(true)
                }
            });
            if (b === true) {
                cb(this, a);
                cb(this.find("*"), a.find("*"))
            }
            return a
        },
        html: function (c) {
            if (c === bK) {
                return this[0] && this[0].nodeType === 1 ? this[0].innerHTML.replace(cg, "") : null
            } else {
                if (typeof c === "string" && !bO.test(c) && (aW.support.leadingWhitespace || !cs.test(c)) && !aY[(bL.exec(c) || ["", ""])[1].toLowerCase()]) {
                    c = c.replace(bU, b9);
                    try {
                        for (var b = 0,
						a = this.length; b < a; b++) {
                            if (this[b].nodeType === 1) {
                                aW.cleanData(this[b].getElementsByTagName("*"));
                                this[b].innerHTML = c
                            }
                        }
                    } catch (d) {
                        this.empty().append(c)
                    }
                } else {
                    if (aW.isFunction(c)) {
                        this.each(function (g) {
                            var f = aW(this),
							e = f.html();
                            f.empty().append(function () {
                                return c.call(this, g, e)
                            })
                        })
                    } else {
                        this.empty().append(c)
                    }
                }
            }
            return this
        },
        replaceWith: function (a) {
            if (this[0] && this[0].parentNode) {
                if (aW.isFunction(a)) {
                    return this.each(function (d) {
                        var c = aW(this),
						b = c.html();
                        c.replaceWith(a.call(this, d, b))
                    })
                }
                if (typeof a !== "string") {
                    a = aW(a).detach()
                }
                return this.each(function () {
                    var c = this.nextSibling,
					b = this.parentNode;
                    aW(this).remove();
                    if (c) {
                        aW(c).before(a)
                    } else {
                        aW(b).append(a)
                    }
                })
            } else {
                return this.pushStack(aW(aW.isFunction(a) ? a() : a), "replaceWith", a)
            }
        },
        detach: function (a) {
            return this.remove(a, true)
        },
        domManip: function (c, h, g) {
            var l, a, f = c[0],
			j = [],
			b,
			e;
            if (!aW.support.checkClone && arguments.length === 3 && typeof f === "string" && b3.test(f)) {
                return this.each(function () {
                    aW(this).domManip(c, h, g, true)
                })
            }
            if (aW.isFunction(f)) {
                return this.each(function (n) {
                    var m = aW(this);
                    c[0] = f.call(this, n, h ? m.html() : bK);
                    m.domManip(c, h, g)
                })
            }
            if (this[0]) {
                e = f && f.parentNode;
                if (aW.support.parentNode && e && e.nodeType === 11 && e.childNodes.length === this.length) {
                    l = {
                        fragment: e
                    }
                } else {
                    l = bY(c, this, j)
                }
                b = l.fragment;
                if (b.childNodes.length === 1) {
                    a = b = b.firstChild
                } else {
                    a = b.firstChild
                }
                if (a) {
                    h = h && aW.nodeName(a, "tr");
                    for (var k = 0,
					i = this.length; k < i; k++) {
                        g.call(h ? d(this[k], a) : this[k], k > 0 || l.cacheable || this.length > 1 ? b.cloneNode(true) : b)
                    }
                }
                if (j.length) {
                    aW.each(j, bA)
                }
            }
            return this;
            function d(m, n) {
                return aW.nodeName(m, "table") ? (m.getElementsByTagName("tbody")[0] || m.appendChild(m.ownerDocument.createElement("tbody"))) : m
            }
        }
    });
    function cb(c, a) {
        var b = 0;
        a.each(function () {
            if (this.nodeName !== (c[b] && c[b].nodeName)) {
                return
            }
            var g = aW.data(c[b++]),
			f = aW.data(this, g),
			h = g && g.events;
            if (h) {
                delete f.handle;
                f.events = {};
                for (var e in h) {
                    for (var d in h[e]) {
                        aW.event.add(this, e, h[e][d], h[e][d].data)
                    }
                }
            }
        })
    }
    function bY(b, g, e) {
        var a, d, f, c = (g && g[0] ? g[0].ownerDocument || g[0] : cw);
        if (b.length === 1 && typeof b[0] === "string" && b[0].length < 512 && c === cw && !bO.test(b[0]) && (aW.support.checkClone || !b3.test(b[0]))) {
            d = true;
            f = aW.fragments[b[0]];
            if (f) {
                if (f !== 1) {
                    a = f
                }
            }
        }
        if (!a) {
            a = c.createDocumentFragment();
            aW.clean(b, c, a, e)
        }
        if (d) {
            aW.fragments[b[0]] = f ? a : 1
        }
        return {
            fragment: a,
            cacheable: d
        }
    }
    aW.fragments = {};
    aW.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    },
	function (a, b) {
	    aW.fn[a] = function (h) {
	        var d = [],
			g = aW(h),
			f = this.length === 1 && this[0].parentNode;
	        if (f && f.nodeType === 11 && f.childNodes.length === 1 && g.length === 1) {
	            g[b](this[0]);
	            return this
	        } else {
	            for (var e = 0,
				i = g.length; e < i; e++) {
	                var c = (e > 0 ? this.clone(true) : this).get();
	                aW.fn[b].apply(aW(g[e]), c);
	                d = d.concat(c)
	            }
	            return this.pushStack(d, a, g.selector)
	        }
	    }
	});
    aW.extend({
        clean: function (m, a, h, c) {
            a = a || cw;
            if (typeof a.createElement === "undefined") {
                a = a.ownerDocument || a[0] && a[0].ownerDocument || cw
            }
            var i = [];
            for (var g = 0,
			b; (b = m[g]) != null; g++) {
                if (typeof b === "number") {
                    b += ""
                }
                if (!b) {
                    continue
                }
                if (typeof b === "string" && !b2.test(b)) {
                    b = a.createTextNode(b)
                } else {
                    if (typeof b === "string") {
                        b = b.replace(bU, b9);
                        var j = (bL.exec(b) || ["", ""])[1].toLowerCase(),
						n = aY[j] || aY._default,
						f = n[0],
						l = a.createElement("div");
                        l.innerHTML = n[1] + b + n[2];
                        while (f--) {
                            l = l.lastChild
                        }
                        if (!aW.support.tbody) {
                            var k = ch.test(b),
							e = j === "table" && !k ? l.firstChild && l.firstChild.childNodes : n[1] === "<table>" && !k ? l.childNodes : [];
                            for (var d = e.length - 1; d >= 0; --d) {
                                if (aW.nodeName(e[d], "tbody") && !e[d].childNodes.length) {
                                    e[d].parentNode.removeChild(e[d])
                                }
                            }
                        }
                        if (!aW.support.leadingWhitespace && cs.test(b)) {
                            l.insertBefore(a.createTextNode(cs.exec(b)[0]), l.firstChild)
                        }
                        b = l.childNodes
                    }
                }
                if (b.nodeType) {
                    i.push(b)
                } else {
                    i = aW.merge(i, b)
                }
            }
            if (h) {
                for (var g = 0; i[g]; g++) {
                    if (c && aW.nodeName(i[g], "script") && (!i[g].type || i[g].type.toLowerCase() === "text/javascript")) {
                        c.push(i[g].parentNode ? i[g].parentNode.removeChild(i[g]) : i[g])
                    } else {
                        if (i[g].nodeType === 1) {
                            i.splice.apply(i, [g + 1, 0].concat(aW.makeArray(i[g].getElementsByTagName("script"))))
                        }
                        h.appendChild(i[g])
                    }
                }
            }
            return i
        },
        cleanData: function (g) {
            var a, h, f = aW.cache,
			d = aW.event.special,
			c = aW.support.deleteExpando;
            for (var b = 0,
			i; (i = g[b]) != null; b++) {
                h = i[aW.expando];
                if (h) {
                    a = f[h];
                    if (a.events) {
                        for (var e in a.events) {
                            if (d[e]) {
                                aW.event.remove(i, e)
                            } else {
                                a6(i, e, a.handle)
                            }
                        }
                    }
                    if (c) {
                        delete i[aW.expando]
                    } else {
                        if (i.removeAttribute) {
                            i.removeAttribute(aW.expando)
                        }
                    }
                    delete f[h]
                }
            }
        }
    });
    var bs = /z-?index|font-?weight|opacity|zoom|line-?height/i,
	ci = /alpha\([^)]*\)/,
	cu = /opacity=([^)]*)/,
	a8 = /float/i,
	bG = /-([a-z])/ig,
	cl = /([A-Z])/g,
	bn = /^-?\d+(?:px)?$/i,
	by = /^-?\d/,
	bf = {
	    position: "absolute",
	    visibility: "hidden",
	    display: "block"
	},
	cm = ["Left", "Right"],
	a3 = ["Top", "Bottom"],
	be = cw.defaultView && cw.defaultView.getComputedStyle,
	bl = aW.support.cssFloat ? "cssFloat" : "styleFloat",
	b1 = function (a, b) {
	    return b.toUpperCase()
	};
    aW.fn.css = function (a, b) {
        return bk(this, a, b, true,
		function (e, d, c) {
		    if (c === bK) {
		        return aW.curCSS(e, d)
		    }
		    if (typeof c === "number" && !bs.test(d)) {
		        c += "px"
		    }
		    aW.style(e, d, c)
		})
    };
    aW.extend({
        style: function (a, e, b) {
            if (!a || a.nodeType === 3 || a.nodeType === 8) {
                return bK
            }
            if ((e === "width" || e === "height") && parseFloat(b) < 0) {
                b = bK
            }
            var g = a.style || a,
			c = b !== bK;
            if (!aW.support.opacity && e === "opacity") {
                if (c) {
                    g.zoom = 1;
                    var d = parseInt(b, 10) + "" === "NaN" ? "" : "alpha(opacity=" + b * 100 + ")";
                    var f = g.filter || aW.curCSS(a, "filter") || "";
                    g.filter = ci.test(f) ? f.replace(ci, d) : d
                }
                return g.filter && g.filter.indexOf("opacity=") >= 0 ? (parseFloat(cu.exec(g.filter)[1]) / 100) + "" : ""
            }
            if (a8.test(e)) {
                e = bl
            }
            e = e.replace(bG, b1);
            if (c) {
                g[e] = b
            }
            return g[e]
        },
        css: function (h, f, b, e) {
            if (f === "width" || f === "height") {
                var d, g = bf,
				c = f === "width" ? cm : a3;
                function a() {
                    d = f === "width" ? h.offsetWidth : h.offsetHeight;
                    if (e === "border") {
                        return
                    }
                    aW.each(c,
					function () {
					    if (!e) {
					        d -= parseFloat(aW.curCSS(h, "padding" + this, true)) || 0
					    }
					    if (e === "margin") {
					        d += parseFloat(aW.curCSS(h, "margin" + this, true)) || 0
					    } else {
					        d -= parseFloat(aW.curCSS(h, "border" + this + "Width", true)) || 0
					    }
					})
                }
                if (h.offsetWidth !== 0) {
                    a()
                } else {
                    aW.swap(h, g, a)
                }
                return Math.max(0, Math.round(d))
            }
            return aW.curCSS(h, f, b)
        },
        curCSS: function (c, i, j) {
            var f, h = c.style,
			k;
            if (!aW.support.opacity && i === "opacity" && c.currentStyle) {
                f = cu.test(c.currentStyle.filter || "") ? (parseFloat(RegExp.$1) / 100) + "" : "";
                return f === "" ? "1" : f
            }
            if (a8.test(i)) {
                i = bl
            }
            if (!j && h && h[i]) {
                f = h[i]
            } else {
                if (be) {
                    if (a8.test(i)) {
                        i = "float"
                    }
                    i = i.replace(cl, "-$1").toLowerCase();
                    var e = c.ownerDocument.defaultView;
                    if (!e) {
                        return null
                    }
                    var g = e.getComputedStyle(c, null);
                    if (g) {
                        f = g.getPropertyValue(i)
                    }
                    if (i === "opacity" && f === "") {
                        f = "1"
                    }
                } else {
                    if (c.currentStyle) {
                        var b = i.replace(bG, b1);
                        f = c.currentStyle[i] || c.currentStyle[b];
                        if (!bn.test(f) && by.test(f)) {
                            var a = h.left,
							d = c.runtimeStyle.left;
                            c.runtimeStyle.left = c.currentStyle.left;
                            h.left = b === "fontSize" ? "1em" : (f || 0);
                            f = h.pixelLeft + "px";
                            h.left = a;
                            c.runtimeStyle.left = d
                        }
                    }
                }
            }
            return f
        },
        swap: function (e, d, a) {
            var b = {};
            for (var c in d) {
                b[c] = e.style[c];
                e.style[c] = d[c]
            }
            a.call(e);
            for (var c in d) {
                e.style[c] = b[c]
            }
        }
    });
    if (aW.expr && aW.expr.filters) {
        aW.expr.filters.hidden = function (d) {
            var b = d.offsetWidth,
			a = d.offsetHeight,
			c = d.nodeName.toLowerCase() === "tr";
            return b === 0 && a === 0 && !c ? true : b > 0 && a > 0 && !c ? false : aW.curCSS(d, "display") === "none"
        };
        aW.expr.filters.visible = function (a) {
            return !aW.expr.filters.hidden(a)
        }
    }
    var a4 = bp(),
	bd = /<script(.|\s)*?\/script>/gi,
	b7 = /select|textarea/i,
	bI = /color|date|datetime|email|hidden|month|number|password|range|search|tel|text|time|url|week/i,
	cd = /=\?(&|$)/,
	bM = /\?/,
	bE = /(\?|&)_=.*?(&|$)/,
	aX = /^(\w+:)?\/\/([^\/?#]+)/,
	bV = /%20/g,
	cn = aW.fn.load;
    aW.fn.extend({
        load: function (f, b, c) {
            if (typeof f !== "string") {
                return cn.call(this, f)
            } else {
                if (!this.length) {
                    return this
                }
            }
            var a = f.indexOf(" ");
            if (a >= 0) {
                var d = f.slice(a, f.length);
                f = f.slice(0, a)
            }
            var g = "GET";
            if (b) {
                if (aW.isFunction(b)) {
                    c = b;
                    b = null
                } else {
                    if (typeof b === "object") {
                        b = aW.param(b, aW.ajaxSettings.traditional);
                        g = "POST"
                    }
                }
            }
            var e = this;
            aW.ajax({
                url: f,
                type: g,
                dataType: "html",
                data: b,
                complete: function (i, h) {
                    if (h === "success" || h === "notmodified") {
                        e.html(d ? aW("<div />").append(i.responseText.replace(bd, "")).find(d) : i.responseText)
                    }
                    if (c) {
                        e.each(c, [i.responseText, h, i])
                    }
                }
            });
            return this
        },
        serialize: function () {
            return aW.param(this.serializeArray())
        },
        serializeArray: function () {
            return this.map(function () {
                return this.elements ? aW.makeArray(this.elements) : this
            }).filter(function () {
                return this.name && !this.disabled && (this.checked || b7.test(this.nodeName) || bI.test(this.type))
            }).map(function (a, b) {
                var c = aW(this).val();
                return c == null ? null : aW.isArray(c) ? aW.map(c,
				function (d, e) {
				    return {
				        name: b.name,
				        value: d
				    }
				}) : {
				    name: b.name,
				    value: c
				}
            }).get()
        }
    });
    aW.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "),
	function (a, b) {
	    aW.fn[b] = function (c) {
	        return this.bind(b, c)
	    }
	});
    aW.extend({
        get: function (a, c, d, b) {
            if (aW.isFunction(c)) {
                b = b || d;
                d = c;
                c = null
            }
            return aW.ajax({
                type: "GET",
                url: a,
                data: c,
                success: d,
                dataType: b
            })
        },
        getScript: function (a, b) {
            return aW.get(a, null, b, "script")
        },
        getJSON: function (a, b, c) {
            return aW.get(a, b, c, "json")
        },
        post: function (a, c, d, b) {
            if (aW.isFunction(c)) {

                b = b || d;
                d = c;
                c = {}
            }
            return aW.ajax({
                type: "POST",
                url: a,
                data: c,
                success: d,
                dataType: b
            })
        },
        ajaxSetup: function (a) {
            aW.extend(aW.ajaxSettings, a)
        },
        ajaxSettings: {
            url: location.href,
            global: true,
            type: "GET",
            contentType: "application/x-www-form-urlencoded",
            processData: true,
            async: true,
            xhr: bj.XMLHttpRequest && (bj.location.protocol !== "file:" || !bj.ActiveXObject) ?
			function () {
			    return new bj.XMLHttpRequest()
			} : function () {
			    try {
			        return new bj.ActiveXObject("Microsoft.XMLHTTP")
			    } catch (a) { }
			},
            accepts: {
                xml: "application/xml, text/xml",
                html: "text/html",
                script: "text/javascript, application/javascript",
                json: "application/json, text/javascript",
                text: "text/plain",
                _default: "*/*"
            }
        },
        lastModified: {},
        etag: {},
        ajax: function (p) {
            var g = aW.extend(true, {},
			aW.ajaxSettings, p);
            var u, o, t, v = p && p.context || g,
			m = g.type.toUpperCase();
            if (g.data && g.processData && typeof g.data !== "string") {
                g.data = aW.param(g.data, g.traditional)
            }
            if (g.dataType === "jsonp") {
                if (m === "GET") {
                    if (!cd.test(g.url)) {
                        g.url += (bM.test(g.url) ? "&" : "?") + (g.jsonp || "callback") + "=?"
                    }
                } else {
                    if (!g.data || !cd.test(g.data)) {
                        g.data = (g.data ? g.data + "&" : "") + (g.jsonp || "callback") + "=?"
                    }
                }
                g.dataType = "json"
            }
            if (g.dataType === "json" && (g.data && cd.test(g.data) || cd.test(g.url))) {
                u = g.jsonpCallback || ("jsonp" + a4++);
                if (g.data) {
                    g.data = (g.data + "").replace(cd, "=" + u + "$1")
                }
                g.url = g.url.replace(cd, "=" + u + "$1");
                g.dataType = "script";
                bj[u] = bj[u] ||
				function (w) {
				    t = w;
				    b();
				    e();
				    bj[u] = bK;
				    try {
				        delete bj[u]
				    } catch (x) { }
				    if (n) {
				        n.removeChild(r)
				    }
				}
            }
            if (g.dataType === "script" && g.cache === null) {
                g.cache = false
            }
            if (g.cache === false && m === "GET") {
                var k = bp();
                var s = g.url.replace(bE, "$1_=" + k + "$2");
                g.url = s + ((s === g.url) ? (bM.test(g.url) ? "&" : "?") + "_=" + k : "")
            }
            if (g.data && m === "GET") {
                g.url += (bM.test(g.url) ? "&" : "?") + g.data
            }
            if (g.global && !aW.active++) {
                aW.event.trigger("ajaxStart")
            }
            var j = aX.exec(g.url),
			a = j && (j[1] && j[1] !== location.protocol || j[2] !== location.host);
            if (g.dataType === "script" && m === "GET" && a) {
                var n = cw.getElementsByTagName("head")[0] || cw.documentElement;
                var r = cw.createElement("script");
                r.src = g.url;
                if (g.scriptCharset) {
                    r.charset = g.scriptCharset
                }
                if (!u) {
                    var i = false;
                    r.onload = r.onreadystatechange = function () {
                        if (!i && (!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) {
                            i = true;
                            b();
                            e();
                            r.onload = r.onreadystatechange = null;
                            if (n && r.parentNode) {
                                n.removeChild(r)
                            }
                        }
                    }
                }
                n.insertBefore(r, n.firstChild);
                return bK
            }
            var d = false;
            var c = g.xhr();
            if (!c) {
                return
            }
            if (g.username) {
                c.open(m, g.url, g.async, g.username, g.password)
            } else {
                c.open(m, g.url, g.async)
            }
            try {
                if (g.data || p && p.contentType) {
                    c.setRequestHeader("Content-Type", g.contentType)
                }
                if (g.ifModified) {
                    if (aW.lastModified[g.url]) {
                        c.setRequestHeader("If-Modified-Since", aW.lastModified[g.url])
                    }
                    if (aW.etag[g.url]) {
                        c.setRequestHeader("If-None-Match", aW.etag[g.url])
                    }
                }
                if (!a) {
                    c.setRequestHeader("X-Requested-With", "XMLHttpRequest")
                }
                c.setRequestHeader("Accept", g.dataType && g.accepts[g.dataType] ? g.accepts[g.dataType] + ", */*" : g.accepts._default)
            } catch (q) { }
            if (g.beforeSend && g.beforeSend.call(v, c, g) === false) {
                if (g.global && !--aW.active) {
                    aW.event.trigger("ajaxStop")
                }
                c.abort();
                return false
            }
            if (g.global) {
                h("ajaxSend", [c, g])
            }
            var f = c.onreadystatechange = function (w) {
                if (!c || c.readyState === 0 || w === "abort") {
                    if (!d) {
                        e()
                    }
                    d = true;
                    if (c) {
                        c.onreadystatechange = aW.noop
                    }
                } else {
                    if (!d && c && (c.readyState === 4 || w === "timeout")) {
                        d = true;
                        c.onreadystatechange = aW.noop;
                        o = w === "timeout" ? "timeout" : !aW.httpSuccess(c) ? "error" : g.ifModified && aW.httpNotModified(c, g.url) ? "notmodified" : "success";
                        var y;
                        if (o === "success") {
                            try {
                                t = aW.httpData(c, g.dataType, g)
                            } catch (x) {
                                o = "parsererror";
                                y = x
                            }
                        }
                        if (o === "success" || o === "notmodified") {
                            if (!u) {
                                b()
                            }
                        } else {
                            aW.handleError(g, c, o, y)
                        }
                        e();
                        if (w === "timeout") {
                            c.abort()
                        }
                        if (g.async) {
                            c = null
                        }
                    }
                }
            };
            try {
                var l = c.abort;
                c.abort = function () {
                    if (c) {
                        l.call(c)
                    }
                    f("abort")
                }
            } catch (q) { }
            if (g.async && g.timeout > 0) {
                setTimeout(function () {
                    if (c && !d) {
                        f("timeout")
                    }
                },
				g.timeout)
            }
            try {
                c.send(m === "POST" || m === "PUT" || m === "DELETE" ? g.data : null)
            } catch (q) {
                aW.handleError(g, c, null, q);
                e()
            }
            if (!g.async) {
                f()
            }
            function b() {
                if (g.success) {
                    g.success.call(v, t, o, c)
                }
                if (g.global) {
                    h("ajaxSuccess", [c, g])
                }
            }
            function e() {
                if (g.complete) {
                    g.complete.call(v, c, o)
                }
                if (g.global) {
                    h("ajaxComplete", [c, g])
                }
                if (g.global && !--aW.active) {
                    aW.event.trigger("ajaxStop")
                }
            }
            function h(x, w) {
                (g.context ? aW(g.context) : aW.event).trigger(x, w)
            }
            return c
        },
        handleError: function (b, d, a, c) {
            if (b.error) {
                b.error.call(b.context || b, d, a, c)
            }
            if (b.global) {
                (b.context ? aW(b.context) : aW.event).trigger("ajaxError", [d, b, c])
            }
        },
        active: 0,
        httpSuccess: function (b) {
            try {
                return !b.status && location.protocol === "file:" || (b.status >= 200 && b.status < 300) || b.status === 304 || b.status === 1223 || b.status === 0
            } catch (a) { }
            return false
        },
        httpNotModified: function (d, a) {
            var c = d.getResponseHeader("Last-Modified"),
			b = d.getResponseHeader("Etag");
            if (c) {
                aW.lastModified[a] = c
            }
            if (b) {
                aW.etag[a] = b
            }
            return d.status === 304 || d.status === 0
        },
        httpData: function (b, f, e) {
            var d = b.getResponseHeader("content-type") || "",
			c = f === "xml" || !f && d.indexOf("xml") >= 0,
			a = c ? b.responseXML : b.responseText;
            if (c && a.documentElement.nodeName === "parsererror") {
                aW.error("parsererror")
            }
            if (e && e.dataFilter) {
                a = e.dataFilter(a, f)
            }
            if (typeof a === "string") {
                if (f === "json" || !f && d.indexOf("json") >= 0) {
                    a = aW.parseJSON(a)
                } else {
                    if (f === "script" || !f && d.indexOf("javascript") >= 0) {
                        aW.globalEval(a)
                    }
                }
            }
            return a
        },
        param: function (c, f) {
            var d = [];
            if (f === bK) {
                f = aW.ajaxSettings.traditional
            }
            if (aW.isArray(c) || c.jquery) {
                aW.each(c,
				function () {
				    b(this.name, this.value)
				})
            } else {
                for (var a in c) {
                    e(a, c[a])
                }
            }
            return d.join("&").replace(bV, "+");
            function e(g, h) {
                if (aW.isArray(h)) {
                    aW.each(h,
					function (j, i) {
					    if (f || /\[\]$/.test(g)) {
					        b(g, i)
					    } else {
					        e(g + "[" + (typeof i === "object" || aW.isArray(i) ? j : "") + "]", i)
					    }
					})
                } else {
                    if (!f && h != null && typeof h === "object") {
                        aW.each(h,
						function (j, i) {
						    e(g + "[" + j + "]", i)
						})
                    } else {
                        b(g, h)
                    }
                }
            }
            function b(g, h) {
                h = aW.isFunction(h) ? h() : h;
                d[d.length] = encodeURIComponent(g) + "=" + encodeURIComponent(h)
            }
        }
    });
    var bS = {},
	a2 = /toggle|show|hide/,
	bw = /^([+-]=)?([\d+-.]+)(.*)$/,
	a5, bc = [["height", "marginTop", "marginBottom", "paddingTop", "paddingBottom"], ["width", "marginLeft", "marginRight", "paddingLeft", "paddingRight"], ["opacity"]];
    aW.fn.extend({
        show: function (h, f) {
            if (h || h === 0) {
                return this.animate(a1("show", 3), h, f)
            } else {
                for (var c = 0,
				j = this.length; c < j; c++) {
                    var g = aW.data(this[c], "olddisplay");
                    this[c].style.display = g || "";
                    if (aW.css(this[c], "display") === "none") {
                        var e = this[c].nodeName,
						d;
                        if (bS[e]) {
                            d = bS[e]
                        } else {
                            var i = aW("<" + e + " />").appendTo("body");
                            d = i.css("display");
                            if (d === "none") {
                                d = "block"
                            }
                            i.remove();
                            bS[e] = d
                        }
                        aW.data(this[c], "olddisplay", d)
                    }
                }
                for (var b = 0,
				a = this.length; b < a; b++) {
                    this[b].style.display = aW.data(this[b], "olddisplay") || ""
                }
                return this
            }
        },
        hide: function (b, c) {
            if (b || b === 0) {
                return this.animate(a1("hide", 3), b, c)
            } else {
                for (var a = 0,
				e = this.length; a < e; a++) {
                    var d = aW.data(this[a], "olddisplay");
                    if (!d && d !== "none") {
                        aW.data(this[a], "olddisplay", aW.css(this[a], "display"))
                    }
                }
                for (var g = 0,
				f = this.length; g < f; g++) {
                    this[g].style.display = "none"
                }
                return this
            }
        },
        _toggle: aW.fn.toggle,
        toggle: function (c, b) {
            var a = typeof c === "boolean";
            if (aW.isFunction(c) && aW.isFunction(b)) {
                this._toggle.apply(this, arguments)
            } else {
                if (c == null || a) {
                    this.each(function () {
                        var d = a ? c : aW(this).is(":hidden");
                        aW(this)[d ? "show" : "hide"]()
                    })
                } else {
                    this.animate(a1("toggle", 3), c, b)
                }
            }
            return this
        },
        fadeTo: function (a, c, b) {
            return this.filter(":hidden").css("opacity", 0).show().end().animate({
                opacity: c
            },
			a, b)
        },
        animate: function (a, c, e, d) {
            var b = aW.speed(c, e, d);
            if (aW.isEmptyObject(a)) {
                return this.each(b.complete)
            }
            return this[b.queue === false ? "each" : "queue"](function () {
                var h = aW.extend({},
				b),
				j,
				i = this.nodeType === 1 && aW(this).is(":hidden"),
				f = this;
                for (j in a) {
                    var g = j.replace(bG, b1);
                    if (j !== g) {
                        a[g] = a[j];
                        delete a[j];
                        j = g
                    }
                    if (a[j] === "hide" && i || a[j] === "show" && !i) {
                        return h.complete.call(this)
                    }
                    if ((j === "height" || j === "width") && this.style) {
                        h.display = aW.css(this, "display");
                        h.overflow = this.style.overflow
                    }
                    if (aW.isArray(a[j])) {
                        (h.specialEasing = h.specialEasing || {})[j] = a[j][1];
                        a[j] = a[j][0]
                    }
                }
                if (h.overflow != null) {
                    this.style.overflow = "hidden"
                }
                h.curAnim = aW.extend({},
				a);
                aW.each(a,
				function (l, p) {
				    var o = new aW.fx(f, h, l);
				    if (a2.test(p)) {
				        o[p === "toggle" ? i ? "show" : "hide" : p](a)
				    } else {
				        var n = bw.exec(p),
						q = o.cur(true) || 0;
				        if (n) {
				            var k = parseFloat(n[2]),
							m = n[3] || "px";
				            if (m !== "px") {
				                f.style[l] = (k || 1) + m;
				                q = ((k || 1) / o.cur(true)) * q;
				                f.style[l] = q + m
				            }
				            if (n[1]) {
				                k = ((n[1] === "-=" ? -1 : 1) * k) + q
				            }
				            o.custom(q, k, m)
				        } else {
				            o.custom(q, p, "")
				        }
				    }
				});
                return true
            })
        },
        stop: function (b, a) {
            var c = aW.timers;
            if (b) {
                this.queue([])
            }
            this.each(function () {
                for (var d = c.length - 1; d >= 0; d--) {
                    if (c[d].elem === this) {
                        if (a) {
                            c[d](true)
                        }
                        c.splice(d, 1)
                    }
                }
            });
            if (!a) {
                this.dequeue()
            }
            return this
        }
    });
    aW.each({
        slideDown: a1("show", 1),
        slideUp: a1("hide", 1),
        slideToggle: a1("toggle", 1),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        }
    },
	function (a, b) {
	    aW.fn[a] = function (c, d) {
	        return this.animate(b, c, d)
	    }
	});
    aW.extend({
        speed: function (c, d, b) {
            var a = c && typeof c === "object" ? c : {
                complete: b || !b && d || aW.isFunction(c) && c,
                duration: c,
                easing: b && d || d && !aW.isFunction(d) && d
            };
            a.duration = aW.fx.off ? 0 : typeof a.duration === "number" ? a.duration : aW.fx.speeds[a.duration] || aW.fx.speeds._default;
            a.old = a.complete;
            a.complete = function () {
                if (a.queue !== false) {
                    aW(this).dequeue()
                }
                if (aW.isFunction(a.old)) {
                    a.old.call(this)
                }
            };
            return a
        },
        easing: {
            linear: function (c, d, a, b) {
                return a + b * c
            },
            swing: function (c, d, a, b) {
                return ((-Math.cos(c * Math.PI) / 2) + 0.5) * b + a
            }
        },
        timers: [],
        fx: function (b, a, c) {
            this.options = a;
            this.elem = b;
            this.prop = c;
            if (!a.orig) {
                a.orig = {}
            }
        }
    });
    aW.fx.prototype = {
        update: function () {
            if (this.options.step) {
                this.options.step.call(this.elem, this.now, this)
            } (aW.fx.step[this.prop] || aW.fx.step._default)(this);
            if ((this.prop === "height" || this.prop === "width") && this.elem.style) {
                this.elem.style.display = "block"
            }
        },
        cur: function (b) {
            if (this.elem[this.prop] != null && (!this.elem.style || this.elem.style[this.prop] == null)) {
                return this.elem[this.prop]
            }
            var a = parseFloat(aW.css(this.elem, this.prop, b));
            return a && a > -10000 ? a : parseFloat(aW.curCSS(this.elem, this.prop)) || 0
        },
        custom: function (a, e, d) {
            this.startTime = bp();
            this.start = a;
            this.end = e;
            this.unit = d || this.unit || "px";
            this.now = this.start;
            this.pos = this.state = 0;
            var b = this;
            function c(f) {
                return b.step(f)
            }
            c.elem = this.elem;
            if (c() && aW.timers.push(c) && !a5) {
                a5 = setInterval(aW.fx.tick, 13)
            }
        },
        show: function () {
            this.options.orig[this.prop] = aW.style(this.elem, this.prop);
            this.options.show = true;
            this.custom(this.prop === "width" || this.prop === "height" ? 1 : 0, this.cur());
            aW(this.elem).show()
        },
        hide: function () {
            this.options.orig[this.prop] = aW.style(this.elem, this.prop);
            this.options.hide = true;
            this.custom(this.cur(), 0)
        },
        step: function (i) {
            var e = bp(),
			a = true;
            if (i || e >= this.options.duration + this.startTime) {
                this.now = this.end;
                this.pos = this.state = 1;
                this.update();
                this.options.curAnim[this.prop] = true;
                for (var b in this.options.curAnim) {
                    if (this.options.curAnim[b] !== true) {
                        a = false
                    }
                }
                if (a) {
                    if (this.options.display != null) {
                        this.elem.style.overflow = this.options.overflow;
                        var h = aW.data(this.elem, "olddisplay");
                        this.elem.style.display = h ? h : this.options.display;
                        if (aW.css(this.elem, "display") === "none") {
                            this.elem.style.display = "block"
                        }
                    }
                    if (this.options.hide) {
                        aW(this.elem).hide()
                    }
                    if (this.options.hide || this.options.show) {
                        for (var f in this.options.curAnim) {
                            aW.style(this.elem, f, this.options.orig[f])
                        }
                    }
                    this.options.complete.call(this.elem)
                }
                return false
            } else {
                var g = e - this.startTime;
                this.state = g / this.options.duration;
                var c = this.options.specialEasing && this.options.specialEasing[this.prop];
                var d = this.options.easing || (aW.easing.swing ? "swing" : "linear");
                this.pos = aW.easing[c || d](this.state, g, 0, 1, this.options.duration);
                this.now = this.start + ((this.end - this.start) * this.pos);
                this.update()
            }
            return true
        }
    };
    aW.extend(aW.fx, {
        tick: function () {
            var b = aW.timers;
            for (var a = 0; a < b.length; a++) {
                if (!b[a]()) {
                    b.splice(a--, 1)
                }
            }
            if (!b.length) {
                aW.fx.stop()
            }
        },
        stop: function () {
            clearInterval(a5);
            a5 = null
        },
        speeds: {
            slow: 600,
            fast: 200,
            _default: 400
        },
        step: {
            opacity: function (a) {
                aW.style(a.elem, "opacity", a.now)
            },
            _default: function (a) {
                if (a.elem.style && a.elem.style[a.prop] != null) {
                    a.elem.style[a.prop] = (a.prop === "width" || a.prop === "height" ? Math.max(0, a.now) : a.now) + a.unit
                } else {
                    a.elem[a.prop] = a.now
                }
            }
        }
    });
    if (aW.expr && aW.expr.filters) {
        aW.expr.filters.animated = function (a) {
            return aW.grep(aW.timers,
			function (b) {
			    return a === b.elem
			}).length
        }
    }
    function a1(b, a) {
        var c = {};
        aW.each(bc.concat.apply([], bc.slice(0, a)),
		function () {
		    c[this] = b
		});
        return c
    }
    if ("getBoundingClientRect" in cw.documentElement) {
        aW.fn.offset = function (f) {
            var i = this[0];
            if (f) {
                return this.each(function (k) {
                    aW.offset.setOffset(this, f, k)
                })
            }
            if (!i || !i.ownerDocument) {
                return null
            }
            if (i === i.ownerDocument.body) {
                return aW.offset.bodyOffset(i)
            }
            var a = i.getBoundingClientRect(),
			e = i.ownerDocument,
			b = e.body,
			g = e.documentElement,
			j = g.clientTop || b.clientTop || 0,
			c = g.clientLeft || b.clientLeft || 0,
			d = a.top + (self.pageYOffset || aW.support.boxModel && g.scrollTop || b.scrollTop) - j,
			h = a.left + (self.pageXOffset || aW.support.boxModel && g.scrollLeft || b.scrollLeft) - c;
            return {
                top: d,
                left: h
            }
        }
    } else {
        aW.fn.offset = function (h) {
            var b = this[0];
            if (h) {
                return this.each(function (m) {
                    aW.offset.setOffset(this, h, m)
                })
            }
            if (!b || !b.ownerDocument) {
                return null
            }
            if (b === b.ownerDocument.body) {
                return aW.offset.bodyOffset(b)
            }
            aW.offset.initialize();
            var k = b.offsetParent,
			j = b,
			g = b.ownerDocument,
			e, l = g.documentElement,
			c = g.body,
			d = g.defaultView,
			i = d ? d.getComputedStyle(b, null) : b.currentStyle,
			f = b.offsetTop,
			a = b.offsetLeft;
            while ((b = b.parentNode) && b !== c && b !== l) {
                if (aW.offset.supportsFixedPosition && i.position === "fixed") {
                    break
                }
                e = d ? d.getComputedStyle(b, null) : b.currentStyle;
                f -= b.scrollTop;
                a -= b.scrollLeft;
                if (b === k) {
                    f += b.offsetTop;
                    a += b.offsetLeft;
                    if (aW.offset.doesNotAddBorder && !(aW.offset.doesAddBorderForTableAndCells && /^t(able|d|h)$/i.test(b.nodeName))) {
                        f += parseFloat(e.borderTopWidth) || 0;
                        a += parseFloat(e.borderLeftWidth) || 0
                    }
                    j = k,
					k = b.offsetParent
                }
                if (aW.offset.subtractsBorderForOverflowNotVisible && e.overflow !== "visible") {
                    f += parseFloat(e.borderTopWidth) || 0;
                    a += parseFloat(e.borderLeftWidth) || 0
                }
                i = e
            }
            if (i.position === "relative" || i.position === "static") {
                f += c.offsetTop;
                a += c.offsetLeft
            }
            if (aW.offset.supportsFixedPosition && i.position === "fixed") {
                f += Math.max(l.scrollTop, c.scrollTop);
                a += Math.max(l.scrollLeft, c.scrollLeft)
            }
            return {
                top: f,
                left: a
            }
        }
    }
    aW.offset = {
        initialize: function () {
            var e = cw.body,
			f = cw.createElement("div"),
			a,
			c,
			b,
			d,
			g = parseFloat(aW.curCSS(e, "marginTop", true)) || 0,
			h = "<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div></div></div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td></td></tr></table>";
            aW.extend(f.style, {
                position: "absolute",
                top: 0,
                left: 0,
                margin: 0,
                border: 0,
                width: "1px",
                height: "1px",
                visibility: "hidden"
            });
            f.innerHTML = h;
            e.insertBefore(f, e.firstChild);
            a = f.firstChild;
            c = a.firstChild;
            d = a.nextSibling.firstChild.firstChild;
            this.doesNotAddBorder = (c.offsetTop !== 5);
            this.doesAddBorderForTableAndCells = (d.offsetTop === 5);
            c.style.position = "fixed",
			c.style.top = "20px";
            this.supportsFixedPosition = (c.offsetTop === 20 || c.offsetTop === 15);
            c.style.position = c.style.top = "";
            a.style.overflow = "hidden",
			a.style.position = "relative";
            this.subtractsBorderForOverflowNotVisible = (c.offsetTop === -5);
            this.doesNotIncludeMarginInBodyOffset = (e.offsetTop !== g);
            e.removeChild(f);
            e = f = a = c = b = d = null;
            aW.offset.initialize = aW.noop
        },
        bodyOffset: function (a) {
            var c = a.offsetTop,
			b = a.offsetLeft;
            aW.offset.initialize();
            if (aW.offset.doesNotIncludeMarginInBodyOffset) {
                c += parseFloat(aW.curCSS(a, "marginTop", true)) || 0;
                b += parseFloat(aW.curCSS(a, "marginLeft", true)) || 0
            }
            return {
                top: c,
                left: b
            }
        },
        setOffset: function (b, f, g) {
            if (/static/.test(aW.curCSS(b, "position"))) {
                b.style.position = "relative"
            }
            var a = aW(b),
			d = a.offset(),
			e = parseInt(aW.curCSS(b, "top", true), 10) || 0,
			c = parseInt(aW.curCSS(b, "left", true), 10) || 0;
            if (aW.isFunction(f)) {
                f = f.call(b, g, d)
            }
            var h = {
                top: (f.top - d.top) + e,
                left: (f.left - d.left) + c
            };
            if ("using" in f) {
                f.using.call(b, h)
            } else {
                a.css(h)
            }
        }
    };
    aW.fn.extend({
        position: function () {
            if (!this[0]) {
                return null
            }
            var c = this[0],
			b = this.offsetParent(),
			d = this.offset(),
			a = /^body|html$/i.test(b[0].nodeName) ? {
			    top: 0,
			    left: 0
			} : b.offset();
            d.top -= parseFloat(aW.curCSS(c, "marginTop", true)) || 0;
            d.left -= parseFloat(aW.curCSS(c, "marginLeft", true)) || 0;
            a.top += parseFloat(aW.curCSS(b[0], "borderTopWidth", true)) || 0;
            a.left += parseFloat(aW.curCSS(b[0], "borderLeftWidth", true)) || 0;
            return {
                top: d.top - a.top,
                left: d.left - a.left
            }
        },
        offsetParent: function () {
            return this.map(function () {
                var a = this.offsetParent || cw.body;
                while (a && (!/^body|html$/i.test(a.nodeName) && aW.css(a, "position") === "static")) {
                    a = a.offsetParent
                }
                return a
            })
        }
    });
    aW.each(["Left", "Top"],
	function (b, a) {
	    var c = "scroll" + a;
	    aW.fn[c] = function (e) {
	        var f = this[0],
			d;
	        if (!f) {
	            return null
	        }
	        if (e !== bK) {
	            return this.each(function () {
	                d = bi(this);
	                if (d) {
	                    d.scrollTo(!b ? e : aW(d).scrollLeft(), b ? e : aW(d).scrollTop())
	                } else {
	                    this[c] = e
	                }
	            })
	        } else {
	            d = bi(f);
	            return d ? ("pageXOffset" in d) ? d[b ? "pageYOffset" : "pageXOffset"] : aW.support.boxModel && d.document.documentElement[c] || d.document.body[c] : f[c]
	        }
	    }
	});
    function bi(a) {
        return ("scrollTo" in a && a.document) ? a : a.nodeType === 9 ? a.defaultView || a.parentWindow : false
    }
    aW.each(["Height", "Width"],
	function (b, a) {
	    var c = a.toLowerCase();
	    aW.fn["inner" + a] = function () {
	        return this[0] ? aW.css(this[0], c, false, "padding") : null
	    };
	    aW.fn["outer" + a] = function (d) {
	        return this[0] ? aW.css(this[0], c, false, d ? "margin" : "border") : null
	    };
	    aW.fn[c] = function (e) {
	        var d = this[0];
	        if (!d) {
	            return e == null ? null : this
	        }
	        if (aW.isFunction(e)) {
	            return this.each(function (g) {
	                var f = aW(this);
	                f[c](e.call(this, g, f[c]()))
	            })
	        }
	        return ("scrollTo" in d && d.document) ? d.document.compatMode === "CSS1Compat" && d.document.documentElement["client" + a] || d.document.body["client" + a] : (d.nodeType === 9) ? Math.max(d.documentElement["client" + a], d.body["scroll" + a], d.documentElement["scroll" + a], d.body["offset" + a], d.documentElement["offset" + a]) : e === bK ? aW.css(d, c) : this.css(c, typeof e === "string" ? e : e + "px")
	    }
	});
    XK.jQuery = XK.$ = aW
})(window);





(function () {
    var a = null;
    ctop = (XK.$(window).height() - XK.config.swfHeight) / 2,
	cleft = (XK.$(window).width() - XK.config.swfWidth) / 2;
    Player = {
        swfObject: null,
        _events: {},
        _initialized: false,
        options: {},
        createNode: function (e, d) {
            var c = document.createElement(e),
			b;
            for (b in d) {
                if (d.hasOwnProperty(b)) {
                    c.setAttribute(b, d[b])
                }
            }
            return c
        },
        cookie: {
            set: function (d, f, c, e, b) {
                if (typeof c == "undefined") {
                    c = new Date(new Date().getTime() + 24 * 3600 * 1000);
                }
                document.cookie = d + "=" + escape(f) + ((c) ? "; expires=" + c.toGMTString() : "") + ((e) ? "; path=" + e : "; path=/") + ((b) ? ";domain=" + b : "");
            },
            get: function (c) {
                var b = document.cookie.match(new RegExp("(^| )" + c + "=([^;]*)(;|$)"));
                if (b != null) {
                    return unescape(b[2])
                }
                return null;
            },
            clear: function (c, d, b) {
                if (this.get(c)) {
                    document.cookie = c + "=" + ((d) ? "; path=" + d : "; path=/") + ((b) ? "; domain=" + b : "") + ";expires=Fri, 02-Jan-1970 00:00:00 GMT";
                }
            }
        },
        _executeInWindowContext: function (b) {
            window.setTimeout(b, 1)
        },
        eventScheduler: function () { },
        bind: function (b, c) {
            if (!Player._events[b]) {
                Player._events[b] = []
            }
            Player._events[b].push(c)
        },
        clearBindings: function (b) {
            Player._events[b] = []
        },
        triggerEvent: function (d, b, c) {
            Player._executeInWindowContext(function () {
                if (!Player._events[d]) {
                    return
                }
                for (var e = 0,
				f = Player._events[d].length; e < f; e++) {
                    if (Player._events[d][e]) {
                        Player._events[d][e].apply(Player, [b, c])
                    }
                }
            })
        },
        _loadFlash: function () {
            this._clearFlash();
            this.options.flashContainer.append('<div id="xkFlashObject"></div>');
            swfobject.embedSWF(XK.config.resourceUrl + "xk.swf?ver=" + XK.config.version.split(".")[1], "xkFlashObject", "1", "1", "10.1.0", undefined, undefined, {
                align: "tl",
                allowscriptaccess: "always",
                wmode: "transparent",
                quality: "best",
                scale: "noscale"
            },
			undefined,
			function (b) {
			    if (b.success) {
			        Player.swfObject = b.ref;
			    } else {
			        Player._showFlashRequiredDialog()
			    }
			})
        },
        _clearFlash: function () {
            this.options.flashContainer.empty()
        },
        _showFlashRequiredDialog: function () {
            this.options.flashContainer.addClass("nosupport").html('<button type="button" class="close">×</button><p>Adobe Flash Player 10.1 or newer is required to use this feature.</p><p><a href="http://get.adobe.com/flashplayer" >Get it on Adobe.com.</a></p>')
        },
        _notFound: function () {
            /*
            * BUG #3263
            * */
            this.options.flashContainer.empty();
            //this.options.flashContainer.addClass("nofound").html('<button type="button" class="close">×</button><p>该视频不存在！</p>')
        },
        _dueTime: function () {
            this.options.flashContainer.addClass("nofound").html('<button type="button" class="close">×</button><p>视频主持人已经过期！</p>')
        },
        _ad: function () { },
        position: {
            wrap: {
                top: "top:" + XK.config.offset[0] + "px;left:" + cleft + "px",
                bottom: "left:" + cleft + "px;bottom:" + XK.config.offset[0] + "px",
                center: "top:" + ctop + "px;left:" + cleft + "px",
                lt: "left:" + XK.config.offset[0] + "px;top:" + XK.config.offset[1] + "px",
                rt: "right:" + XK.config.offset[0] + "px;top:" + XK.config.offset[1] + "px",
                lb: "left:" + XK.config.offset[0] + "px;bottom:" + XK.config.offset[1] + "px",
                rb: "right:" + XK.config.offset[0] + "px;bottom:" + XK.config.offset[1] + "px"
            },
            control: {
                top: "top:0",
                bottom: "bottom:0",
                center: "top: " + XK.config.swfHeight + "px",
                lt: "left:0;top:0",
                rt: "right:0;top:0",
                lb: "left:0;bottom:30px",
                rb: "right:0;bottom:30px"
            }
        },
        event: {
            show: function (d) {
                var b = XK.$("#XK_Controller_Show");
                if (!Player.options.isPlayAd && !d && XK.config.ad && XK.config.adPos === "before") {
                    Player.options.flashContainer.append('<div id="XKad_wrap" style="width:' + XK.config.swfWidth + "px;height:" + XK.config.swfHeight + 'px"><p id="XKad_time">' + XK.config.adtime + "秒</p>" + XK.config.ad + "</div>");
                    setTimeout(function () {
                        XK.$("#XKad_wrap").remove();
                        XK.$("#XK_Controller").show();
                        Player.event.show(1)
                    },
					(XK.config.adtime || 10) * 1000);
                    Player.event.countDown(XK.config.adtime || 10, XK.$("#XKad_time"));
                    XK.$("#XK_Controller").hide();
                    Player.options.isPlayAd = true;
                    return
                }
                Player.swfObject.width = XK.config.swfWidth;
                Player.swfObject.height = XK.config.swfHeight;
                Player.swfObject.xk_show(XK.config.swfWidth, XK.config.swfHeight);
                b.replaceWith('<a id="XK_Controller_Play" class="pause" href="javascript:void(0)"></a>');
                XK.$("#XK_Controller_Stop").attr("class", "stop");
                XK.$("#XK_Controller_Mute").attr("class", "voice");
                Player.event.tip.bind();
                if (XK.config.drag) {
                    var c = new Player.event.drag("XK_Container");
                    XK.$("#XK_Container").mousedown(function (f) {
                        c.drag(f)
                    })
                }
            },
            play: function () {
                if (Player.swfObject.xk_play()) {
                    XK.$(this).attr("class", "play")
                } else {
                    XK.$(this).attr("class", "pause")
                }
            },
            autoStop: function () {
                //Player.event.stop();
                if (XK.config.isstop !== 1) {
                    Player.event.stop();//循环播放// by winston 修改播放完毕主持人静止
                }
                //Player.event.show();//循环播放
                if (!Player.options.isPlayAd && XK.config.ad && XK.config.adPos === "after") {
                    Player.options.flashContainer.append('<div id="XKad_wrap" style="width:' + XK.config.swfWidth + "px;height:" + XK.config.swfHeight + 'px"><p id="XKad_time">' + XK.config.adtime + "秒</p>" + XK.config.ad + "</div>");
                    setTimeout(function () {
                        XK.$("#XKad_wrap").remove();
                        XK.$("#XK_Controller").show()
                    },
					(XK.config.adtime || 10) * 1000);
                    Player.event.countDown(XK.config.adtime || 10, XK.$("#XKad_time"));
                    XK.$("#XK_Controller").hide();
                    Player.options.isPlayAd = true
                }
                XK.config.overFn(0);
            },
            stop: function () {
                if ($("#XK_Controller_Stop").hasClass("no-stop")) {
                    return
                }
                Player.swfObject.width = 1;
                Player.swfObject.height = 1;
                //if(XK.config.isstop===0||XK.config.isstop==null)
                //{
                Player.swfObject.xk_stop();// by winston 修改播放完毕主持人静止
                //}
                XK.$("#XK_Controller_Play").replaceWith('<a id="XK_Controller_Show" class="play"  href="javascript:void(0)"></a>');
                XK.$("#XK_Controller_Stop").removeClass().addClass("no-stop");
                if (XK.$("#XK_Controller_Mute").attr("class") === "dis-voice") {
                    Player.swfObject.xk_mute()
                }
                XK.$("#XK_Controller_Mute").removeClass().addClass("no-voice");
                XK.$("#XK_Controller").show();
                if (XK.config.drag) {
                    Player.options.flashContainer.unbind("mousedown").attr("style", "width:" + XK.config.swfWidth + "px;" + Player.position.wrap[XK.config.position])
                }
                Player.event.tip.clear()
                XK.config.overFn(1);

            },
            mute: function () {
                if (XK.$(this).attr("class").indexOf("no-") > -1) {
                    return
                }
                if (Player.swfObject.xk_mute()) {
                    XK.$(this).attr("class", "voice")
                } else {
                    XK.$(this).attr("class", "dis-voice")
                }
            },
            countDown: function (c, b) {
                var d = setInterval(function () {
                    c--;
                    b.html(c + "秒");
                    if (c == 0) {
                        clearInterval(d)
                    }
                },
				1000)
            },
            tip: {
                bind: function () {
                    var d, c = new Player.eventScheduler(),
					b = XK.$("#XK_Controller");
                    Player.options.flashContainer.hover(function () {
                        d = setTimeout(function () {
                            b.fadeIn();
                            c.clear()
                        },
						200)
                    },
					function () {
					    clearTimeout(d);
					    c.set(function () {
					        b.fadeOut()
					    },
						1500)
					})
                },
                clear: function () {
                    Player.options.flashContainer.unbind("mouseenter").unbind("mouseleave")
                }
            },
            drag: function (c, b) {
                this.timeoutid = null;
                this.$parent = XK.$(document);
                this.target = document.getElementById(c);
                this.$target = XK.$(this.target);
                this.parentOffset_left = this.$parent.offset() ? this.$parent.offset().left : 0;
                this.parentOffset_top = this.$parent.offset() ? this.$parent.offset().top : 0;
                this.handlers = {
                    dragging: this.eventMethod(this, "onDragging"),
                    complete: this.eventMethod(this, "stop")
                }
            }
        },
        ready: function () {
            var c = [],
			b = new Date("2200", "1", "1");
            Player._initialized = true;
            this.swfObject.xk_load(XK.config.swfUrl);
            c.push('<div id="XK_Controller" style="width:' + (XK.config.close ? 150 : 115) + "px;" + this.position.control[XK.config.position] + '"><div class="thleft"></div><div class="thindex"><ul>');
            if (this.options.playTimes > XK.config.autoPlay) {
                c.push('<li><a id="XK_Controller_Show" class="play" href="javascript:void(0)"></a></li>')
            } else {
                c.push('<li><a id="XK_Controller_Play" class="pause" href="javascript:void(0)"></a></li>')
            }
            c.push('<li><a id="XK_Controller_Stop" class="' + (this.options.playTimes > XK.config.autoPlay ? "no-stop" : "stop") + '" href="javascript:void(0)"></a></li>');
            c.push('<li><a id="XK_Controller_Mute" class="' + (this.options.playTimes > XK.config.autoPlay ? "no-voice" : "voice") + '" href="javascript:void(0)"></a></li>');
            if (XK.config.close) {
                c.push('<li><a id="XK_Controller_Close" title="关闭" class="close" href="javascript:void(0)"></a></li>')
            }
            c.push('</ul></div><div class="thright"></div></div>');
            this.options.flashContainer.append(c.join(""));
            XK.$("#XK_Controller").delegate("a", "click", function (d) {
                switch (d.target.id) {
                    case "XK_Controller_Show":
                        Player.event.show.call(d.target);
                        break;
                    case "XK_Controller_Play":
                        Player.event.play.call(d.target);
                        break;
                    case "XK_Controller_Stop":
                        Player.event.stop.call(d.target);
                        break;
                    case "XK_Controller_Mute":
                        Player.event.mute.call(d.target);
                        break;
                    case "XK_Controller_Close":
                        if (XK.$("#XK_Controller_Stop").attr("class").indexOf("no-") == -1) {
                            Player.swfObject.xk_stop()
                        }
                        Player.options.flashContainer.remove();
                        /*if (Player.options.closeTimes == XK.config.closeTimes) {
                            //if (confirm("是否以后永久不显示秀客?")) {
                            //Player.options.closeTimes++
                            //}
                        } else {
                            //Player.options.closeTimes++
                        }*/
                        //Player.cookie.set("closeTimes" + XK.config.swfId, Player.options.closeTimes);
                        XK.config.overFn(1);
                        break;
                    default:
                        break
                }
            });
            if (this.options.playTimes <= XK.config.autoPlay) {
                Player.triggerEvent("showVideo");
                if (XK.config.issavecookie)//添加配置是否下次自动播放 by winston
                {
                    this.options.playTimes++;
                }
                //this.options.playTimes++; by winston 去除cookie记录，多次播放
                Player.cookie.set("playTimes" + XK.config.swfId, this.options.playTimes, b)
            }
            XK.config.startFn();
        },
        initialize: function (c, f) {
            this.options = c || {};
            this.options.playTimes = Player.cookie.get("playTimes" + XK.config.swfId) || 0;
            this.options.closeTimes = Player.cookie.get("closeTimes" + XK.config.swfId) || 0;
            if (window.location.protocol === "file:") {
                throw new Error("flash不支持file://协议.请用http协议打开")
            }
            if (this.options.closeTimes > XK.config.closeTimes) {
                return
            }
            var b = this.createNode("link", {
                type: "text/css",
                href: XK.config.resourceUrl + "xk.min.css?ver=" + XK.config.version.split(".")[2],
                rel: "Stylesheet"
            });
            document.getElementsByTagName("head")[0].appendChild(b);
            if (!this.options.flashContainer) {
                var d = "width:" + XK.config.swfWidth + "px;" + Player.position.wrap[XK.config.position];
                this.options.flashContainer = XK.$('<div id="XK_Container" style="' + d + '" ></div>');
                XK.$("body").append(this.options.flashContainer)
            }
            this.options.flashContainer.delegate(".close", "click", function () {
                Player.options.flashContainer.remove()
            });
            if (XK.config.duetime) {
                Player._dueTime();
                return
            }
            this.bind("initialized", Player.ready);
            this.bind("stopVideo", Player.event.autoStop);
            this.bind("notFound", Player._notFound);
            this.bind("showVideo", Player.event.show);
            this._loadFlash()
            f();
        }
    };
    Player.eventScheduler.prototype = {
        set: function (b, d, c) {
            if (c) {
                this.timer = setTimeout(function () {
                    b.call(c)
                },
				d)
            } else {
                this.timer = setTimeout(b, d)
            }
        },
        clear: function () {
            clearTimeout(this.timer)
        }
    };
    Player.event.drag.prototype = {
        start: function (b, c) {
            this.mode = c;
            this.width = this.$target.width();
            this.height = this.$target.height();
            this.border = this.$target.offset();
            this.border.right = this.border.left + this.width;
            this.border.bottom = this.border.top + this.height;
            this.$parent.mousemove(this.handlers[this.mode]).mouseup(this.handlers.complete);
            this.bound = {
                left: this.parentOffset_left,
                top: this.parentOffset_top,
                right: this.parentOffset_left + XK.$(window).width() + this.$parent.scrollLeft(),
                bottom: this.parentOffset_top + XK.$(window).height() + this.$parent.scrollTop()
            };
            this.scheduleEvent = new Player.eventScheduler();
            if (b.preventDefault) {
                b.preventDefault()
            } else {
                b.returnValue = false
            }
            if (b.stopPropagation) {
                b.stopPropagation()
            } else {
                b.cancelBubble = true
            }
        },
        stop: function (b) {
            this.$parent.unbind("mousemove", this.handlers[this.mode]);
            this.$parent.unbind("mouseup", this.handlers.complete);
            if (this.target.releaseCapture) {
                this.target.releaseCapture()
            } else {
                if (window.releaseEvents) {
                    window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP)
                }
            }

            return false
        },
        drag: function (b) {
            this.start(b, "dragging");
            this.oPos = {
                x: b.pageX || 0,
                y: b.pageY || 0
            };
            return false
        },
        onDragging: function (b) {
            this.border.left = this.boundx(this.border.left + b.pageX - this.oPos.x, -this.width);
            this.border.top = this.boundy(this.border.top + b.pageY - this.oPos.y, -this.height);
            this.oPos = {
                x: b.pageX,
                y: b.pageY
            };
            this.adjust(this.border.left, this.border.top);
            if (this.target.setCapture) {
                this.target.setCapture()
            } else {
                if (window.captureEvents) {
                    window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP)
                }
            }
            return false
        },
        adjust: function (d, f, g, b, e) {
            this.width = g || this.width || 0;
            this.height = b || this.height || 0;
            if (XK.$.browser.msie && XK.$.browser.version == 6) {
                if (XK.$("html").css("overflow") === "hidden") {
                    f = f - this.$parent.scrollTop()
                }
            } else {
                f = parseInt(f - this.$parent.scrollTop())
            }
            if (parseInt(f) < 0) {
                f = 0
            }
            var c = null;
            c = {
                bottom: "",
                right: "",
                left: d,
                top: f
            };
            this.$target.css(c)
        },
        boundx: function (c, b) {
            return Math.max(Math.min(c || 0, this.bound.right + (b || 0)), this.bound.left)
        },
        boundy: function (c, b) {
            return Math.max(Math.min(c || 0, this.bound.bottom + (b || 0)), this.bound.top)
        },
        eventMethod: function (b, c) {
            return function (d) {
                return b[c](d, this)
            }
        }
    };
    Player.initialize({}, function () {
        /*setTimeout(function () {
            XK.config.startFn();
        }, 382);*/
    });
})();
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css"/>
    <link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css"/>
    <style>
        .magnimg{
            width:300px;
            height:300px;
        }

        .magnimg img{
            max-width: 300px;
            max-height: 300px;
        }

        .magnimg1{
            width: 200px;
            height: 200px;
        }
        .magnimg1 img{
            max-width: 200px;
            max-height: 200px;
        }
        .magnimg2{
            width: 150px;
            height: 150px;
        }
        .magnimg2 img{
            max-width: 150px;
            max-height: 150px;
        }
        .magnimg3{
            width: 500px;
            height: 500px;
        }
        .magnimg3 img{
            max-width: 500px;
            max-height: 500px;
        }
    </style>
</head>
<body>

<div class="magnimg js-img-center magnimg3" style="position:relative;background: #f7f7f7; margin: 100px;">
    <a href="javascript:void(0)" class="jqzoom js-img-center">
        <img src="temp/glass.jpg" data-glasses="temp/glass.jpg" title=""/>
    </a>
</div>

<div class="magnimg js-img-center" data-src="" style="position:relative;background: #f7f7f7; margin: 100px;">
    <a href="javascript:void(0)" class="jqzoom">
        <img src="temp/1.jpg" data-glasses="temp/1.jpg" title=""/>
    </a>
</div>

<div class="magnimg magnimg3 js-img-center" style="position:relative;background: #f7f7f7; margin: 100px;">
    <a href="javascript:void(0)" class="jqzoom">
        <img src="temp/2.jpg" data-glasses="temp/2.jpg" title=""/>
    </a>
</div>

<div class="magnimg magnimg1 js-img-center" style="position:relative;background: #f7f7f7; margin: 100px;">
    <a href="javascript:void(0)" class="jqzoom js-img-center">
        <img src="temp/3.jpg" data-glasses="temp/3.jpg" title=""/>
    </a>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/src/common/base-init.js"></script>
<script>
    seajs.use(["gallery/glasses/0.1.2/glasses"], function (Glasses) {
        //放大镜
        new Glasses({
            elem: ".magnimg",
            fixedWidth: 400
        });
    });
</script>
</body>
</html>
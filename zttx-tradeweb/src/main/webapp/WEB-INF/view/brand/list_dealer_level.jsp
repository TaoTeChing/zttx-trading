<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<style>
	.dealers_layer{
		margin-left: 0 !important;
		margin-top: 0 !imporant;
	}
</style>
<div class="ui-form-item js_dealers_box">
	<label class="ui-label" for="">
		指定终端商：
	</label>
	<div class="inline-block">
		<c:if test="${empty param.isShowTitle}">
			<div style="margin-bottom: 10px;height: 20px;line-height: 28px;" class="radio_box">
				<c:choose>
					<c:when test="${empty param.docOpen || param.docOpen}">
						<input class="ui-radio ml5 js-allcount" name="docOpen" type="radio" value="1" checked="checked"/>${empty param.title1 ? '所有用户' : param.title1}
						<input class="ui-radio js-justjoin ml10" name="docOpen" type="radio" value="0"/>${empty param.title2 ? '只对加盟终端商' : param.title2}
					</c:when>
					<c:otherwise>
						<input class="ui-radio ml5 js-allcount" name="docOpen" type="radio" value="1" />${empty param.title1 ? '所有用户' : param.title1}
						<input class="ui-radio js-justjoin ml10" name="docOpen" type="radio" value="0" checked="checked"/>${empty param.title2 ? '只对加盟终端商' : param.title2}
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<div class="dealers_layer" <c:if test="${empty param.docOpen || param.docOpen}">style="display: none;"</c:if>>
			<div class="search_condition">
				<span class="fl check_box"><input id="chk_all" class="ui-checkbox mr5" type="checkbox"/>全选</span>
				<c:if test="${empty param.brandsName}">
					<div class="level fl">
						<label style="vertical-align: 3px;">品牌:</label>
						<div class="inline-block" style="vertical-align: top;">
							<select class="ui-select js_select" name="brandsId" id="brandsId" onchange="dealerLevel.init();">
								<option value="">请选择品牌</option>
								<c:forEach items="${brandesList}" var="brandes">
									<option   value="${brandes.refrenceId}">${brandes.brandsName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:if>
				<div class="level fl">
					<label>终端商等级:</label>

					<div class="inline-block">
						<select class="js_select" name="level_select" id="level_select">
							<option value="">全部终端商</option>
						</select>
					</div>
				</div>
				<div class="area fl">
					<label>终端商区域:</label>

					<div class="inline-block">
						<select class="js_select" name="area_select" id="area_select">
							<option value="">全国</option>
							<option value="110000">北京市</option>
							<option value="120000">天津市</option>
							<option value="130000">河北省</option>
							<option value="140000">山西省</option>
							<option value="150000">内蒙古自治区</option>
							<option value="210000">辽宁省</option>
							<option value="220000">吉林省</option>
							<option value="230000">黑龙江省</option>
							<option value="310000">上海市</option>
							<option value="320000">江苏省</option>
							<option value="330000">浙江省</option>
							<option value="340000">安徽省</option>
							<option value="350000">福建省</option>
							<option value="360000">江西省</option>
							<option value="370000">山东省</option>
							<option value="410000">河南省</option>
							<option value="420000">湖北省</option>
							<option value="430000">湖南省</option>
							<option value="440000">广东省</option>
							<option value="450000">广西壮族自治区</option>
							<option value="460000">海南省</option>
							<option value="500000">重庆市</option>
							<option value="510000">四川省</option>
							<option value="520000">贵州省</option>
							<option value="530000">云南省</option>
							<option value="540000">西藏自治区</option>
							<option value="610000">陕西省</option>
							<option value="620000">甘肃省</option>
							<option value="630000">青海省</option>
							<option value="640000">宁夏回族自治区</option>
							<option value="650000">新疆维吾尔自治区</option>
							<option value="710000">台湾省</option>
							<option value="810000">香港特别行政区</option>
							<option value="820000">澳门特别行政区</option>
						</select>
						<input type="button" id="levelBut" value="搜索" onclick="dealerLevel.levelSearch();" style="display:none;"/>
					</div>
				</div>
			</div>
			<div class="data_list">
				<table id="list-table">
					<colgroup>
						<col width="192"/>
						<col width="192"/>
						<col width="192"/>
						<col width="194"/>
					</colgroup>
					<tr></tr>
				</table>
			</div>
		</div>
	</div>
	<input type="hidden" name="docErr" id="docErr"/>
</div>
<script id="dealer-list" type="text/html">
	{{each result}}
	{{if $index % 4 == 0}}
	<tr>
		{{/if}}
		<td>
			<input data-area="{{$value.areaNo}}" data-level="{{$value.levelId}}" name="dealerIds" value="{{$value.dealerId}}" class="ui-checkbox mr5" type="checkbox" />{{$value.dealerName}}
		</td>
		{{if $index % 4 == 3 || result.length == $index+1}}
	</tr>
	{{/if}}
	{{/each}}
</script>

<script src="${res}/scripts/jquery.min.js"></script>
<script>
	$(function () {
		dealerLevel.init();
	});
	var dealerLevel = {
		init:function(){
			this.setLevelData();
			this.areafilter();//省份筛选
			this.levelfilter();//终端商等级筛选
			this.ALreset();//重置等级和区域
			this.chkall();//全选 指定终端商处显示隐藏
		},
		setLevelValue:function(){
			selectLevel = $('#level_select');
			brandsName = "${param.brandsName}";
			if(brandsName=="")
				brandsName="brandsId";
			bid = $.trim($("#"+brandsName).val());
			addLevelType="${param.addLevelType}";
			lineId="${param.lineId}";
		},
		setLevelData:function(){
			seajs.use(['template'], function (template) {
				dealerLevel.setLevelValue();
				if(bid=="" && addLevelType!="3")
				{
					$("#list-table tbody").html("");
				}else{
					var data={brandsId: bid,addLevelType:addLevelType,lineId:lineId};
					dealerLevel.levelAjaxData(data,true);
				}
			});
		},
		levelSearch:function(){
			var levelId=selectLevel.val();
			var province=$.trim($("#area_select").val());
			var data={brandsId: bid,addLevelType:addLevelType,lineId:lineId,dealerLevel:levelId,province:province};
			dealerLevel.levelAjaxData(data, false);
		},
		filterData: function(data){
			var arr = [];
			var oldArr = data.result;
			for(var i = 0,len = oldArr.length;i<len;i++){
				if(!this.isExit(arr,oldArr[i])){
					arr.push(oldArr[i]);
				}
			}
			data.result = arr;
			return data;
		},
		isExit: function(arr,obj){
			var num = 0;
			for(var i = 0,len=arr.length;i<len;i++){
				if(arr[i].dealerId == obj.dealerId){
					num ++;
				}
			}
			if(num > 0){
				return true;
			}else{
				return false;
			}
		},
		levelAjaxData:function(data,isSearch){
			var _this = this;
			$.get('${ctx}/brand/message/dealers?t='+Math.random(), data, function (data, status, jqXHR) {
				if (data.code === zttx.SUCCESS) {
					//处理后的数据
					var newData = _this.filterData(data.object);

					var html = template.render('dealer-list', newData);

					$("#list-table tbody").html(html);

					/*if(isSearch){
					 selectLevel.empty().append('<option value="">全部终端商等级</option> ');
					 for(var i=0;i<data.object.levels.length;i++)
					 {
					 selectLevel.append("<option value='"+data.object.levels[i].refrenceId+"'>"+data.object.levels[i].levelName+"</option>");
					 }
					 }*/
				}
			}, 'json');
		},
		areafilter: function () {
			renderSelect('#area_select');
			/*$("#sel-brandsId").on("click", function () {
			 var val = $("#sel-brandsId option:checked").val();
			 //$(".data_list").html();
			 });*/
			$("#area_select").change(function(){

				var BLval = $("#level_select option:selected").val();//等级
				var val = $(this).val()/10000;//区域

				$("#list-table td input.ui-checkbox").each(function(){

					var levelVal = $(this).data("level");//区域
					var areaNo = $(this).data("area")/10000;//等级
					var PNo = parseInt(areaNo);

					if(BLval == ''){
						levelVal = '';
					}

					val == PNo && BLval == levelVal ? $(this).parent().show():$(this).parent().hide();
					if(val == 0 && BLval == levelVal) $(this).parent().show();//省份为空，等级相同的都显示
					if(val == 0 && BLval == '') $(this).parent().show();//等级为空，省份都为空 全部显示

				});
			});
		},
		levelfilter:function(){
			$("#level_select").change(function(){

				var val = $(this).val();//等级
				var selval = $("#area_select option:selected").val()/10000;//区域

				$("#list-table td input.ui-checkbox").each(function(){

					var areaNo = $(this).data("area")/10000;
					var levelVal = $(this).data("level");
					var PNo = parseInt(areaNo);

					if(selval == 0){
						PNo = 0;
					}

					val == levelVal && selval == PNo ? $(this).parent().show():$(this).parent().hide();
					if(val == '' && selval == PNo) $(this).parent().show();//等级为空，省份值相等的都显示
					if(val == '' && selval == '') $(this).parent().show();//等级为空，省份都为空 全部显示

				});
			});
		},
		ALreset:function(){
			$("#brandsId").change(function(){
				$("#level_select_div").html(
						'<span class="fn-text-overflow" style="width: 90px; ">全部终端商</span><i class="arrow" style="top: 7px; "></i>');
				$("#area_select_div").html(
						'<span class="fn-text-overflow" style="width: 90px; ">全国</span><i class="arrow" style="top: 7px; "></i>');
				$("#chk_all").attr("checked", false);//重置全选
			});
		},
		chkall:function(){

			$("#chk_all").click(function(){

				var checkdd = this.checked;

				$("input[name=dealerIds]").each(function(){

					if($(this).is(":visible")){//可见的才能被全选选中
						$(this).prop("checked",checkdd);
					}

				});

			});

			$(".js-allcount").click(function(){
				$(".dealers_layer").hide();
			});
			$(".js-justjoin").click(function(){
				$(".dealers_layer").show();
			})
		}
	};
</script>
/**********************************************************************************
 * <p>JavaScript</p>
 *  @Author:刘志坚(LzjLiu307@163.com)
 *  @Date 2014-02-24
 *  @Description：8637品牌超级代理JavaScript
 **********************************************************************************/
var zttx=
{
	/**
	 * 分页显示时，跳转到第一页
	 * @param form 当前的form对象
	 */
	goFirst:function(form)
	{
		var obj=$("#currentPage");
		if(null != obj)
		{		
			obj.val(1);
			$Z.changPage(form);
		}
	},
	/**
	 * 分页显示时，跳转到上一页
	 * @param form 当前的form对象
	 */
	goPrevious:function(form)
	{
		var obj=$("#currentPage");
		if(null != obj&&parseInt(obj.val())!=1)
		{		
			obj.val(parseInt(obj.val())-1);
			$Z.changPage(form);
		}
	},
	/**
	 * 分页显示时，跳转到下一页
	 * @param form 当前的form对象
	 */
	goNext:function(form)
	{
		var obj=$("#currentPage");
		var tobj=$("#totalPage");
		if(null != obj&&parseInt(obj.val())!=parseInt(tobj.val()))
		{
			obj.val(parseInt(obj.val())+1);
			$Z.changPage(form);
		}
	},
	/**
	 * 分页显示时，跳转到最后一页
	 * @param form 当前的form对象
	 * @param totalPage 总页数
	 */
	goLast:function(form,totalPage)
	{
		var obj=$("#currentPage");
		if(null != obj)
		{
			obj.val(totalPage);	
			$Z.changPage(form);
		}
	},
	/**
	 * 显示指定的第几页
	 * @param form 当前的form对象
	 */
	goPage:function(form,iPage)
	{
		var obj=$("#currentPage");
		if(null != obj)
		{		
			obj.val(iPage);
			$Z.changPage(form);
		}
	},
	/**
	 * 资料分页提交
	 * @param form 当前的form对象
	 */
	changPage:function(form)
	{
		form.submit();
	},
	/**
	 * ajax 分页
	 * @param form 表单ID
	 * @param path ajax路径
	 * @param targetDiv　分页ＤＩＶ
	 * @param pageNumber　页码
	 * @author 罗盛平
	 */
	loadPage:function(form,path,targetDiv,pageNumber)
	{
		$("#"+targetDiv).html("");
	    var data=$("#"+form).serialize();
		path=path+"?currentPage="+pageNumber+"&"+data;
		$("#"+targetDiv).load(path);
	}
};
window.zttx = window.$Z = zttx;
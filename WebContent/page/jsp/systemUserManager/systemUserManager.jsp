<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="./page/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="./page/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="./page/static/css/animate.css" rel="stylesheet">
    <link href="./page/static/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="./page/static/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="./page/static/css/style.css" rel="stylesheet">
    
    <!-- Mainly scripts -->
    <script src="./page/static/js/jquery-2.1.1.js"></script>
    <script src="./page/static/js/bootstrap.min.js"></script>
    <script src="./page/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="./page/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- jqGrid -->
    <script src="./page/static/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
    <script src="./page/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    
     <!-- Sweet alert -->
    <script src="./page/static/js/plugins/sweetalert/sweetalert.min.js"></script>
    <link href="./page/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="./page/static/css/animate.css" rel="stylesheet">
    <link href="./page/static/css/style.css" rel="stylesheet">
</head>

<style type="text/css">
#bg{ display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: #E6E6E6; z-index:1001; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=70);}
#addUser{display: none; position: absolute; top: 25%; left: 35%;  z-index:1002;}
#updateUser{display: none; position: absolute; top: 25%; left: 35%;  z-index:1002;}
#updatePwd{display: none; position: absolute; top: 25%; left: 35%;  z-index:1002;}
.sweet-overlay{background-color: #E6E6E6 !important;-moz-opacity: 0.7  !important; opacity:.70  !important;filter: alpha(opacity=70)  !important;}
</style>

<script>
$(document).ready(function () {
	
	
	jQuery.ajax({
	    type:"POST",
	    data:{},
	    url: "/EmujeesuPayWeb/tsUserManager.json",
	    success: function(data){
// 	    	alert(JSON.stringify(data));
	    	 $("#table_list_2").jqGrid({
// 	    	    	url : "/EmujeesuPayWeb/tsUserManager.json",
// 	    	        datatype: "JSON",
					data: data,
	            	datatype: "local",
	    	        mtype: 'POST',  
	    	        height: 330,
	    	        autowidth: true,
	    	        shrinkToFit: true,
	    	        rowNum: 10, 
	    	        rownumbers: true,  
	    	        multiselect: true,
	    	        rowList: [10, 20, 30],
	    	        colNames:['ID','用户名','密码', '昵称','创建人','创建时间'],
	    	        colModel:[
	    				{name:'id',index:'id', editable: true, align:"center",hidden:true},
	    	            {name:'userName',index:'userName', editable: true, width:'25%',align:"center"},
	    	            {name:'userPasswd',index:'userPasswd', editable: true, hidden:true},
	    	            {name:'nickName',index:'nickName', editable: true,width:'25%',align:"center"},
	    	            {name:'creatorStr',index:'creatorStr', editable: true,width:'25%',align:"center"},
	    	            {name:'creatorTimeStr',index:'creatorTimeStr', editable: true,width:'25%',align:"center"},
	    	           
	    	        ],
	    	        pager: "#pager_list_2",
	    	        viewrecords: true,
	    	        caption: "管理员列表",
	    	        add: true,
	    	        edit: true,
	    	        addtext: 'Add',
	    	        edittext: 'Edit',
	    	        
	    	        sortname: 'creatorTimeStr',
	                sortorder: 'desc',    
	    	        
	    	        hidegrid: false
	    	    });
	    	
	    }
	});
	
   

    $("#table_list_2").jqGrid('navGrid', '#pager_list_2',
        {edit: false, add: false, del: false, search: false},
        {height: 200, reloadAfterSubmit: false}
    );

    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);
        $('#table_list_2').setGridWidth(width);
    });
        
});

function onOk(type){
	
	//增
	if(type == 1){
		var userName = $("#userName").val();
		var userPasswd = $("#userPasswd").val();
		var userPasswdAgain = $("#userPasswdAgain").val();
		var nickName = $("#nickName").val();
		var creator = "${id}";
		$("#addUserError").css('display','none'); 
		
		if(userName==null || userName==''){
			$("#addUserError").css('display','block'); 
			$("#addUserError").html("用户名不能为空");
			return;
		}
		if(userPasswd==null || userPasswd==''){
			$("#addUserError").css('display','block'); 
			$("#addUserError").html("密码不能为空");
			return;
		}
		if(userPasswd.length<6){
			$("#addUserError").css('display','block'); 
			$("#addUserError").html("密码不能少于6位");
			return;
		}
		if(userPasswd != userPasswdAgain){
			$("#addUserError").css('display','block'); 
			$("#addUserError").html("两次输入的密码不同");
			return;
		}
		
		if(nickName==null || nickName==''){
			$("#addUserError").css('display','block'); 
			$("#addUserError").html("昵称不能为空");
			return;
		}
		   jQuery.ajax({
		    type:"POST",
		    data:{"userName":userName,"userPasswd":userPasswd,"nickName":nickName,"creator":creator,"method":"add"},
		    url: "/EmujeesuPayWeb/tsUserManager.json",
		    success: function(data){
		    	if(data.code == 1){
		    		var ids = jQuery("#table_list_2").jqGrid('getDataIDs');
		    		var rowid = Math.max.apply(Math,ids); //获得当前最大行号（数据编号）
		    		newrowid = rowid+1;//获得新添加行的行号（数据编号）
		    		 
					$("#table_list_2").addRowData(newrowid,data , "first");
					jQuery("#table_list_2").jqGrid('setGridParam',{sortname:'creatorTimeStr'});
					$("#table_list_2").trigger("reloadGrid");
					
		    		hide();
		    		swal({
		                title: "",
		                text: "新增用户成功",
		                type: "success"
		            });
		    		return;
		    	}
		    	if(data.code == 2){
		    		hide();
		    		swal({
		                title: "",
		                text: "用户名重复",
		                type: "error"
		            });
		    		return;
		    	}
		    },
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				hide(); 
				swal({
	                title: "",
	                text: "新增失败",
	                type: "error"
	            });
				return;
			}
			});
	}
	//改
	if(type == 2){
		
		var userId = $("#userId_update").val();
		var userName = $("#userName_update").val();
		var nickName = $("#nickName_update").val();
		var oldUserName = $("#oldUserName_update").val();
		var creator = "${username}";
		
		$("#updateUserError").css('display','none'); 
		$("#updateUserError").val("");
		
		if(userName==null || userName==''){
			$("#updateUserError").css('display','block'); 
			$("#updateUserError").html("用户名不能为空");
			return;
		}
		
		if(nickName==null || nickName==''){
			$("#updateUserError").css('display','block'); 
			$("#updateUserError").html("昵称不能为空");
			return;
		}
	   jQuery.ajax({
	    type:"POST",
	    data:{"userName":userName,"nickName":nickName,"editor":creator,"method":"update","id":userId,"oldUserName":oldUserName},
	    url: "/EmujeesuPayWeb/tsUserManager.json",
	    success: function(data){
	    	if(data.code == 1){
	    		var dataRow = {
	    		        userName : data.userName,
	    		        nickName : data.nickName
	    		    };  
		    		      
		    	$("#table_list_2").jqGrid('setRowData', data.id, data);  
		    		
		    	hide();
		    	
		    	swal({
	                title: "",
	                text: "修改成功",
	                type: "success"
	            });
		    	
		    	return;
	    	}
	    	if(data.code == 2){
	    		hide();
	    		swal({
	                title: "",
	                text: "用户名重复",
	                type: "error"
	            });
	    	}else{
	    		hide();
	    		swal({
	                title: "",
	                text: "修改失败",
	                type: "error"
	            });
	    	}
	    },
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			hide();
			swal({
                title: "",
                text: "修改失败",
                type: "error"
            });
		}
		});
	}
	//改密码
	if(type == 3){
		var userId = $("#updatePwdId").val();
		var userOldPasswd = $("#userOldPasswd").val();
		var userNewPasswd = $("#userNewPasswd").val();
		var userAgainPasswd = $("#userAgainPasswd").val();
		
		if(userOldPasswd==null || userOldPasswd==''){
			$("#updatePwdError").css('display','block'); 
			$("#updatePwdError").html("旧密码不能为空");
			return;
		}
		if(userNewPasswd==null || userNewPasswd==''){
			$("#updatePwdError").css('display','block'); 
			$("#updatePwdError").html("新密码不能为空");
			return;
		}
		if(userNewPasswd.length<6){
			$("#updatePwdError").css('display','block'); 
			$("#updatePwdError").html("密码不能少于6位");
			return;
		}
		if(userNewPasswd != userAgainPasswd){
			$("#updatePwdError").css('display','block'); 
			$("#updatePwdError").html("两次密码不相同");
			return;
		}
		
		jQuery.ajax({
		    type:"POST",
		    data:{"method":"updatePwd","id":userId,"userPasswd":userOldPasswd,"userNewPasswd":userNewPasswd},
		    url: "/EmujeesuPayWeb/tsUserManager.json",
		    success: function(data){
		    	if(data.code == 1){
		    		var dataRow = {
		    		        userName : data.userName,
		    		        nickName : data.nickName
		    		    };  
			    		      
			    	hide();
			    	swal({
		                title: "",
		                text: "修改成功",
		                type: "success"
		            });
			    	return;
		    	}
		    	if(data.code == 0){
		    		hide();
		    		swal({
		                title: "",
		                text: "原密码错误",
		                type: "error"
		            });
		    	}else{
		    		hide();
		    		swal({
		                title: "",
		                text: "修改失败",
		                type: "error"
		            });
		    	}
		    },
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				hide();
				swal({
	                title: "",
	                text: "修改失败",
	                type: "error"
	            });
			}
			});
		
	}
	
	
}
function hide(){
	$("#addUser").css('display','none'); 
	$("#updateUser").css('display','none'); 
	$("#updatePwd").css('display','none'); 
	$("#bg").css('display','none'); 
	
	$("#addUserError").css('display','none'); 
	$("#updateUserError").css('display','none'); 
	$("#updatePwdError").css('display','none'); 
	
}

function add(){
	$("#addUser").css('display','block'); 
	$("#bg").css('display','block'); 
	
	$("#userName").val("");
	$("#userPasswd").val("");
	$("#userPasswdAgain").val("");
	$("#nickName").val("");
}
function update(){
	
	var ids = $("#table_list_2").jqGrid("getGridParam","selarrrow");
	if(ids){
		if(ids.length==0){
			swal({
                title: "",
                text: "未选取任何行",
                type: "warning"
            });
			return;
		}else if(ids.length==1){
			$("#updateUser").css('display','block'); 
			$("#bg").css('display','block'); 
			
			var rowid = $("#table_list_2").jqGrid("getGridParam","selrow");
			var rowData = $('#table_list_2').jqGrid("getRowData", rowid);
			
			$("#userName_update").val("");
			$("#nickName_update").val("");
			$("#userId_update").val("");
			$("#oldUserName_update").val("");
			
			$("#userId_update").val(rowData.id);
			$("#oldUserName_update").val(rowData.userName);
			$("#userName_update").val(rowData.userName);
			$("#nickName_update").val(rowData.nickName);
			
			
		}else{
			swal({
                title: "",
                text: "请选择一条要修改的记录",
                type: "warning"
            });
			return;
		}
	}
	
}

function updatePwd(){
	
	var rowid = $("#table_list_2").jqGrid("getGridParam","selarrrow");
	var rowData = $('#table_list_2').jqGrid("getRowData", rowid);
	
	if(rowid.length==0){
		swal({
            title: "",
            text: "未选取任何行",
            type: "warning"
        });
		return;
	}
	if(rowid.length>1){
		swal({
            title: "",
            text: "请选择一条要修改的记录",
            type: "warning"
        });
		return;
	}
	$("#updatePwd").css('display','block'); 
	$("#bg").css('display','block'); 
	
	$("#updatePwdId").val("");
	$("#updatePwdUserName").val("");
	$("#userOldPasswd").val("");
	$("#userNewPasswd").val("");
	$("#userAgainPasswd").val("");
	
	$("#updatePwdId").val(rowid);
	$("#updatePwdUserName").html(rowData.userName);
	
}

function del(){	
	 
	var ids = $("#table_list_2").jqGrid("getGridParam","selarrrow");
	var creator = "${id}";
	if(ids){
		if(ids.length==0){
			swal({
	            title: "",
	            text: "未选取任何行",
	            type: "warning"
	        });
		}
		else{
			var idss="";
			for (var i = 0; i < ids.length; i++) {  
				if(ids[i] == creator){
					swal({
		                title: "",
		                text: "不能删除系统当前使用账户",
		                type: "warning"
		            });
					return;
				}
				var rowData = $('#table_list_2').jqGrid("getRowData", ids[i]);  
				idss = idss + rowData.id+",";
		    }
			
			
			swal({
		         title: "",
		         text: "确认要删除么?",
		         type: "warning",
		         showCancelButton: true,
		         confirmButtonColor: "#DD6B55",
		         confirmButtonText: "确认",
		         cancelButtonText: "取消",
		         closeOnConfirm: false
		     }, function () {
		    	 
		    	 jQuery.ajax({
					    type:"POST",
					    data:{"method":"delete","editor":creator,"ids":idss},
					    url: "/EmujeesuPayWeb/tsUserManager.json",
					    success: function(data){
					    	if(data.code == 1){
					    		
					    		var gr = $("#table_list_2").jqGrid("getGridParam","selarrrow"); //删除选中的出库单   
					    		for ( var i = 0, j = gr.length; i < j; i++) {   
					    		    $("#table_list_2").jqGrid('delRowData', gr[0]);
					    		}
					    		
					    		swal("", "删除成功", "success");
					    		$("#table_list_2").trigger("reloadGrid");
					    		
					    	}
					    },
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							 swal("", "删除失败", "error");
						}
						});
		    	 
		    	 
		    	 
		         
		     });
			
		}
	}
}
</script>

<body style="background: #FFF;">
	<div id="bg" onclick="hide()"></div>
    <div class="ibox-title">
        <h5>管理员管理</h5>
    </div>
    <div class="ibox-content">
        <div class="jqGrid_wrapper">
            <table id="table_list_2"></table>
            <div id="pager_list_2"></div>
        </div>
    </div>
    
    <div style="text-align: right;margin-right: 20px">
	    <button class="btn btn-primary" type="button" onclick="add()"><i class="fa fa-plus-circle"></i>&nbsp;添加</button>&nbsp;
	    <button class="btn btn-warning" type="button" onclick="update()"><i class="fa fa-paste" ></i>&nbsp;修改</button>&nbsp;
	    <button class="btn btn-danger" type="button" onclick="del()"><i class="fa fa-remove"></i><span class="bold">&nbsp;删除</span></button>
   	 	<button class="btn btn-info" type="button" onclick="updatePwd()"><i class="fa fa-paste"></i><span class="bold">&nbsp;密码修改</span></button>
    </div>
    
    <!-- 新增用户 -->
    <div id="addUser" class="ibox float-e-margins" style="width: 300px;display:none;">
       <div class="ibox-title">
           <h5>添加管理员</h5>
           <div class="ibox-tools">
               <a class="close-link" onclick="hide()">
                   <i class="fa fa-times"></i>
               </a>
           </div>
       </div>
       <div class="ibox-content" style="padding-bottom: 0px">
           <form class="form-horizontal" onsubmit="return false;">
               <p id="addUserError" style="display:none;color:#FF0000;"></p>
			   <table>
			   		<tr style="height: 45px">
			   			<td>用户名:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userName" type="text" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>密码:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userPasswd" type="password" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>确认密码:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userPasswdAgain" type="password" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>昵称:</td>
			   			<td style="padding-left: 10px">
			   				<input id="nickName" type="text" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 70px">
			   			<td>&nbsp;</td>
			   			<td style="text-align: right">
			   				<button class="btn btn-white" type="button" onclick="hide()">取消</button>&nbsp;
			   				<button class="btn btn-primary" type="button"  onclick="onOk(1)">确认</button>
			   			</td>
			   		</tr>
			   </table>
           </form>
       </div>
   </div>
    
    <!-- 修改用户名、昵称 -->
     <div id="updateUser" class="ibox float-e-margins" style="width: 300px;display:none;">
       <div class="ibox-title">
           <h5>修改管理员</h5>
           <div class="ibox-tools">
               <a class="close-link" onclick="hide()">
                   <i class="fa fa-times"></i>
               </a>
           </div>
       </div>
       <div class="ibox-content" style="padding-bottom: 0px">
           <form class="form-horizontal" onsubmit="return false;">
  			   <p id="updateUserError" style="display:none;color:#FF0000;"></p>
			   <table>
			   		<tr style="height: 45px;display:none;">
			   			<td>隐藏ID</td>
			   			<td style="padding-left: 10px">
			   				<input type="hidden" id="userId_update" name="userId_update">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px;display:none;">
			   			<td>隐藏原用户名</td>
			   			<td style="padding-left: 10px">
			   				<input type="hidden" id="oldUserName_update" name="userId_update">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>用户名:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userName_update" type="text" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>昵称:</td>
			   			<td style="padding-left: 10px">
			   				<input id="nickName_update" type="text" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 70px">
			   			<td>&nbsp;</td>
			   			<td style="text-align: right">
			   				<button class="btn btn-white" type="button" onclick="hide()">取消</button>&nbsp;
			   				<button class="btn btn-primary" type="button"  onclick="onOk(2)">确认</button>
			   			</td>
			   		</tr>
			   </table>
           </form>
       </div>
   </div>
   
   
   <!-- 修改密码 -->
     <div id="updatePwd" class="ibox float-e-margins" style="width: 300px;display:none;">
       <div class="ibox-title">
           <h5>修改密码</h5>
           <div class="ibox-tools">
               <a class="close-link" onclick="hide()">
                   <i class="fa fa-times"></i>
               </a>
           </div>
       </div>
       <div class="ibox-content" style="padding-bottom: 0px">
           <form class="form-horizontal" onsubmit="return false;">
  			   <p id="updatePwdError" style="display:none;color:#FF0000;"></p>
			   <table>
			   		<tr style="height: 45px;display:none;">
			   			<td>隐藏ID</td>
			   			<td style="padding-left: 10px">
			   				<input type="hidden" id="updatePwdId" >
			   			</td>
			   		</tr>
			   		
			   		<tr style="height: 45px">
			   			<td>用户名:</td>
			   			<td style="padding-left: 10px">
			   				<p id="updatePwdUserName" class="form-control"></p>
			   			</td>
			   		</tr>
			   		
			   		<tr style="height: 45px">
			   			<td>原密码:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userOldPasswd" type="password" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>新密码:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userNewPasswd" type="password" class="form-control">
			   			</td>
			   		</tr>
			   		<tr style="height: 45px">
			   			<td>确认密码:</td>
			   			<td style="padding-left: 10px">
			   				<input id="userAgainPasswd" type="password" class="form-control">
			   			</td>
			   		</tr>
			   		
			   		<tr style="height: 70px">
			   			<td>&nbsp;</td>
			   			<td style="text-align: right">
			   				<button class="btn btn-white" type="button" onclick="hide()">取消</button>&nbsp;
			   				<button class="btn btn-primary" type="button"  onclick="onOk(3)">确认</button>
			   			</td>
			   		</tr>
			   </table>
           </form>
       </div>
   </div>
   
</body>
</html>


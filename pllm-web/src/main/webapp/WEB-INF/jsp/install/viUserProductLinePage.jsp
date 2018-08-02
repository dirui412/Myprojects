<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 生产线选型 -->
<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" role="form">
		    <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="queryUserProductLineName">用户生产线</label>
				<div class="col-sm-4">
					<input type="text" id="queryUserProductLineName" name="queryUserProductLineName" class="col-xs-10 col-sm-5" />
				</div>
				<div style="text-align: right; float: right;">
				     <button id="btnQuery" class="btn btn-purple btn-sm">查询</button>
				     <button id="btnAdd"   class="btn btn-primary btn-sm">新增</button>
			    </div>
			</div>
		</form>
	</div>
</div>
<div class="hr hr-18 dotted hr-double"></div>
<div class="clearfix">
	<div class="pull-right tableTools-container"></div>
</div>
<div class="table-header">查询结果</div>
<!-- div.dataTables_borderWrap -->
<div>
	<table id="dynamic-table"
		class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center"><label class="pos-rel"> <input
						type="checkbox" class="ace" /> <span class="lbl"></span>
				</label></th>
				<th>用户生产线名称</th>
				<th>生产线原型</th>
				<th>生产线状态</th>
				<th>选型人员</th>
				<th>选型时间</th>
				<th>选型参数</th>
				<th>选型说明</th>
				<th>客户单位</th>
				<th class="center">修改</th>
				<th class="center">删除</th>
				<th class="center">生产线建模</th>
				<th class="center">确定选型</th>
			</tr>
		</thead>
		<tfoot></tfoot>
	</table>
</div>
<div id="divInputForm" class="modal" tabindex="-1">
	<div class="modal-dialog" style="width: 800px; height: 600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3>请输入</h3>
			</div>
			<div class="modal-body">
				<div class="row">
					<form id="formInput" class="form-horizontal" role="form" >
						<input type="hidden" id="hiddenUserProductLineId" name="hiddenUserProductLineId" />
						<div class="form-group">
						    <label class="col-sm-2 control-label no-padding-right"
								for="inputUserProductLineName">用户生产线名称</label>
							<div class="col-sm-3">
								<input type="text" id="inputUserProductLineName" name="inputUserProductLineName" class="form-control" required >
							</div>
							<label class="col-sm-2 control-label no-padding-right"
								for="inputProductLineId">生产线原型</label>
							<div class="col-sm-3">
								<select class="chosen-select form-control" id="inputProductLineId"
									name="inputProductLineId" required>
									<option value=""></option>
									<c:forEach var="item" items="${productLines}">
										<option value="${item.productLineId}">${item.productName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="inputLectotypeTime">选型时间</label>
							<div class="col-sm-3">
								<input type="text" id="inputLectotypeTime" name="inputLectotypeTime"
									size="16" type="text" readonly required /> <span class="addon">
									<i class="fa fa-clock-o"></i>
								</span>
							</div>
							<label class="col-sm-2 control-label no-padding-right"
								for="inputLectotypeParams">选型参数</label>
							<div class="col-sm-3">
								<textarea id="inputLectotypeParams" name="inputLectotypeParams" class="form-control" required ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="inputLectotypeMemo">选型说明</label>
							<div class="col-sm-3">
								<textarea id="inputLectotypeMemo" name="inputLectotypeMemo" class="form-control" required ></textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button id="btnCancel" class="btn btn-sm" data-dismiss="modal">
				    <i class="ace-icon fa fa-times"></i> 取消
				</button>
				<button id="btnOk" class="btn btn-success btn-sm">
				    <i class="ace-icon fa fa-check"></i> 保存
				</button>
			</div>
		</div>
	</div>
</div>
<!-- 信息删除确认 -->
<div class="modal fade" id="divConfirmDelete">
	<div class="modal-dialog">
		<div class="modal-content message_align">
			<div class="modal-header">
				<h4 class="modal-title">提示信息</h4>
			</div>
			<div class="modal-body">
				<p>您确认要删除吗？</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<a onclick="deleteObject()" class="btn btn-success" data-dismiss="modal">确定</a>
			</div>
		</div>
	</div>
</div>

<!-- 确认选型 -->
<div class="modal fade" id="divConfirmLectoType">
	<div class="modal-dialog">
		<div class="modal-content message_align">
			<div class="modal-header">
				<h4 class="modal-title">提示信息</h4>
			</div>
			<div class="modal-body">
				<p>您确认选型吗？</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<a onclick="postLectoType()" class="btn btn-success" data-dismiss="modal">确定</a>
			</div>
		</div>
	</div>
</div>

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="assets/js/dataTables.tableTools.min.js"></script>
<script src="assets/js/dataTables.colVis.min.js"></script>
<script src="assets/js/jquery.validate.min.js"></script>
<script src="assets/js/localization/messages_zh.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    //1-声明表格对象
	var dataTableObj = null;
	
	$(document)
			.ready(
					function() {
						//1-初始化数据表格
						dataTableObj = $('#dynamic-table')
								.DataTable(
										{
											"serverSide" : true, //服务器处理：过滤、分页、排序
											"processing" : true, //是否显示处理状态
											"ajax" : {
												url : '../install/viUserProductLine/',
												type : 'get',
												data : function(d) {
                                                    d.queryUserProductLineName=document.getElementById("queryUserProductLineName").value;
												},
											//"dataSrc" : function(json) {
											//	return json.data;
											//}
											}, //获取数据的处理函数 
											"searching" : false,//搜索功能
											"bSort" : true, //排序功能
											"aaSorting" : [],
											"columns" : [ {
												"data" : "userProductLineId",
												"orderable" : false,
												"searchable" : false
											}, 
											{
												"data" : "userProductLineName",
												"searchable" : false
											},
											{
												"data" : "productName",
												"searchable" : false
											},
											{
												"data" : "productLineState",
												"searchable" : false
											},
											{
												"data" : "lectotypeUserName",
												"searchable" : false
											},
											{
												"data" : "lectotypeTime",
												"searchable" : false
											},
											{
												"data" : "lectotypeParams",
												"searchable" : false
											},
											{
												"data" : "lectotypeMemo",
												"searchable" : false
											},
											{
												"data" : "groupName",
												"searchable" : false
											},
											{
												"data" : "userProductLineId",
												"orderable" : false,
												"searchable" : false
											},
											{
												"data" : "userProductLineId",
												"orderable" : false,
												"searchable" : false
											},
											{
												"data" : "userProductLineId",
												"orderable" : false,
												"searchable" : false
											},
											{
												"data" : "userProductLineId",
												"orderable" : false,
												"searchable" : false
											}],
											"columnDefs" : [
													{
														"targets" : 0,
														"data" : "userProductLineId",
														"render" : function(
																data, type,
																row, meta) {

															var str = '<div class="center" > <label class="pos-rel">';
															str = str
																	+ '<input type="checkbox" class="ace" value="'+data+'" />';
															str = str
																	+ '<span class="lbl"></span></label> </div>';
															return str;
														}
													},
													
													{
														"targets" : 9,
														"data" : "userProductLineId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															if(row["productLineState"]=="选型中")
															    str = str+ '<a class="edit-row" href="javascript:editObject('
															       + meta.row
															       + ')" >修改</a>';
															else
																str=str+"";
															str = str
																	+ '</div>'
															return str;
														}
													} ,
													{
														"targets" : 10,
														"data" : "userProductLineId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															if(row["productLineState"]=="选型中")
															    str = str
																	+ '<a class="delete-row" href="javascript:confirmDelete('
																	+ data
																	+ ')" >删除</a>';
															else
																str=str+"";
															str = str
																	+ '</div>'
															return str;
														}
													} ,
													{
														"targets" : 11,
														"data" : "userProductLineId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															if(row["productLineState"]=="选型中" ||row["productLineState"]=="已选型" || row["productLineState"]=="已安装"|| row["productLineState"]=="已验收")
															    str = str+ '<a class="edit-row" href="javascript:productLineModel('
															        + data
															        + ')" >生产线建模</a>';
															else
																str=str+"";
															str = str
																	+ '</div>'
															return str;
														}
													},
													{
														"targets" : 12,
														"data" : "userProductLineId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															if(row["productLineState"]=="选型中")
															    str = str
															       + '<a class="edit-row" href="javascript:confirmLectoType('
															       + data
															       + ')" >确定选型</a>';
														    else
															    str=str+"";
															str = str
																	+ '</div>'
															return str;
														}
													} ],
											"bFilter" : false, //过滤功能
											"bPaginate" : true, //翻页功能
											"sPaginationType" : "full_numbers", //分页的格式
											//"iDisplayLength" : 5, //每页显示的数据行数
											"bLengthChange" : false, //改变每页显示数据数量
											"bInfo" : true, //页脚信息
											"bAutoWidth" : true,//自动宽度
											"language": {
										        "sProcessing": "处理中...",
										        "sLengthMenu": "显示 _MENU_ 项结果",
										        "sZeroRecords": "没有匹配结果",
										        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
										        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
										        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
										        "sInfoPostFix": "",
										        "sSearch": "搜索:",
										        "sUrl": "",
										        "sEmptyTable": "表中数据为空",
										        "sLoadingRecords": "载入中...",
										        "sInfoThousands": ",",
										        "oPaginate": {
										            "sFirst": "首页",
										            "sPrevious": "上页",
										            "sNext": "下页",
										            "sLast": "末页"
										        },
										        "oAria": {
										            "sSortAscending": ": 以升序排列此列",
										            "sSortDescending": ": 以降序排列此列"
										        }
										    }
										});
					    //2-设置TableTools
						TableTools.classes.container = "btn-group btn-overlap";
						TableTools.classes.print = {
							"body" : "DTTT_Print",
							"info" : "tableTools-alert gritter-item-wrapper gritter-info gritter-center white",
							"message" : "tableTools-print-navbar"
						}

						//3-初始化TableTools extension
						var tableTools_obj = new $.fn.dataTable.TableTools(
								dataTableObj,
								{
									"sSwfPath" : "assets/swf/copy_csv_xls_pdf.swf",
									"sRowSelector" : "td:not(:last-child)",
									"sRowSelect" : "multi",
									"fnRowSelected" : function(row) {
										//check checkbox when row is selected
										try {
											$(row).find('input[type=checkbox]')
													.get(0).checked = true
										} catch (e) {
										}
									},
									"fnRowDeselected" : function(row) {
										//uncheck checkbox
										try {
											$(row).find('input[type=checkbox]')
													.get(0).checked = false
										} catch (e) {
										}
									},
									"sSelectedClass" : "success",
									"aButtons" : [
											{
												"sExtends" : "copy",
												"sToolTip" : "Copy to clipboard",
												"sButtonClass" : "btn btn-white btn-primary btn-bold",
												"sButtonText" : "<i class='fa fa-copy bigger-110 pink'></i>",
												"fnComplete" : function() {
													this
															.fnInfo(
																	'<h3 class="no-margin-top smaller">Table copied</h3>\
							<p>Copied '
																			+ (dataTableObj
																					.fnSettings()
																					.fnRecordsTotal())
																			+ ' row(s) to the clipboard.</p>',
																	1500);
												}
											},
											{
												"sCharSet" : "utf8",
												"bBomInc" : true,
												"sExtends" : "csv",
												"sToolTip" : "导出Excel",
												"sButtonClass" : "btn btn-white btn-primary  btn-bold",
												"sButtonText" : "<i class='fa fa-file-excel-o bigger-110 green'></i>"
											} ]
								});
						//we put a container before our table and append TableTools element to it
						$(tableTools_obj.fnContainer()).appendTo(
								$('.tableTools-container'));

						//4-初始化表格中的复选框
						$('th input[type=checkbox], td input[type=checkbox]')
								.prop('checked', false);
						//select/deselect all rows according to table header checkbox
						$(
								'#dynamic-table > thead > tr > th input[type=checkbox]')
								.eq(0)
								.on(
										'click',
										function() {
											var th_checked = this.checked;//checkbox inside "TH" table header
											$(this)
													.closest('table')
													.find('tbody > tr')
													.each(
															function() {
																var row = this;
																if (th_checked)
																	tableTools_obj
																			.fnSelect(row);
																else
																	tableTools_obj
																			.fnDeselect(row);
															});
										});
						//select/deselect a row when the checkbox is checked/unchecked
						$('#dynamic-table').on(
								'click',
								'td input[type=checkbox]',
								function() {
									var row = $(this).closest('tr').get(0);
									if (!this.checked)
										tableTools_obj.fnSelect(row);
									else
										tableTools_obj.fnDeselect($(this)
												.closest('tr').get(0));
								});
						
						//5-设置窗口验证
						$("#formInput").validate({
							rules : {
								
							},
							messages : {
								
							}
						});
					});
    
    //重新加载数据
	function refreshData() {
		dataTableObj.ajax.reload(null, true);
	}
	
	//查询、新增和保存按钮绑定事件
	jQuery(function($) {
		$('#inputLectotypeTime').datetimepicker({
			language : 'zh',
			format : "YYYY-MM-DD HH:mm:ss",
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		}).on('changeDate', function(ev) {
			$(this).datetimepicker('hide');
		});
		//查询信息
		$('#btnQuery').on('click', function(e) {
			refreshData();
			e.preventDefault();
		});
		//新增信息
		$('#btnAdd').on('click', function(e) {
			$("#hiddenUserProductLineId").val(-1);
			$("#inputUserProductLineName").val("");
		    $("#inputProductLineId").val(-1);
		    $("#inputLectotypeTime").val("");
		    $("#inputLectotypeParams").val("");
		    $("#inputLectotypeMemo").val("");
			$('#divInputForm').modal();
			e.preventDefault();
		});
		//保存信息
		$('#btnOk').on('click', function(e) {
			//2-窗口验证,成功提交数据
			if($("#formInput").valid()==true){
				postObject();
				e.preventDefault(); //阻止默认事件Post
			}
		});
	});
	
	//修改对象
	function editObject(rowIndex) {
		if (dataTableObj.data().length <= 0)
			return;
		if (rowIndex < 0)
			return;
		var row = dataTableObj.data()[rowIndex];
		$("#hiddenUserProductLineId").val(row["userProductLineId"]);
		$("#inputUserProductLineName").val(row["userProductLineName"]);
		$("#inputProductLineId").val(row["productLineId"]);
		$("#inputLectotypeTime").val(row["lectotypeTime"]);
		$("#inputLectotypeParams").val(row["lectotypeParams"]);
		$("#inputLectotypeMemo").val(row["lectotypeMemo"]);

		$('#divInputForm').modal();
	}
	
	//提交对象
	function postObject() {
	    var obj=new Object();
		obj.userProductLineId =$("#hiddenUserProductLineId").val();
		obj.userProductLineName=$("#inputUserProductLineName").val();
		obj.productLineId=$("#inputProductLineId").val();
		obj.lectotypeTime=$("#inputLectotypeTime").val();
		obj.lectotypeParams=$("#inputLectotypeParams").val();
		obj.lectotypeMemo=$("#inputLectotypeMemo").val();

		var jsonStr = JSON.stringify(obj);
		//alert(jsonStr);
		var httpMethod="post";
		if($("#hiddenUserProductLineId").val()<=0)
		  httpMethod="post";
		else
		  httpMethod="put";
		$.ajax({
			type : httpMethod,
			url : "../install/viUserProductLine/",
			data : jsonStr,
			contentType : "application/json; charset=utf-8",
			async : false, //默认为true 异步   
			error : function() {
				alert('提交失败!');
			},
			success : function(data) {
				$('#divInputForm').modal("hide");
				if (data == null || data == "") {
					$.gritter.add({
						title : '成功',
						text : '提交已经成功!',
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-sucess'
					});
					//重新加载数据
					refreshData();
				} else
					$.gritter.add({
						title : '失败',
						text : data,
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-error'
					});
			}
		});
	}
	
	var deleteId=-1;
	function confirmDelete(id)
	{
		//1-保存删除标识
		deleteId=id;
		//显示确认删除框
		$('#divConfirmDelete').modal();
	}
	
	//删除对象
	function deleteObject() {
		if(deleteId<=0)
			return;
		$.ajax({
			type : "delete",
			url : "../install/viUserProductLine/"+deleteId,
			async : false, //默认为true 异步   
			error : function() {
				alert('提交失败!');
			},

			success : function(data) {
				$('#divInputForm').modal("hide");
				if (data == null || data == "") {
					$.gritter.add({
						title : '成功',
						text : '已经删除成功!',
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-sucess'
					});
					//重新加载数据
					refreshData();
				} else
					$.gritter.add({
						title : '失败',
						text : data,
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-error'
					});
			}
		});
	}
	
	//确认选型
	var lectotypeId=null;
	function confirmLectoType(id)
	{
		//1-保存删除标识
		lectotypeId=id;
		//显示确认删除框
		$('#divConfirmLectoType').modal();
	}
	
	//提交选型
	function postLectoType()
	{
		if(lectotypeId<=0)
			return;
		$.ajax({
			type : "post",
			url : "../install/confirmLectoType/"+lectotypeId,
			async : false, //默认为true 异步   
			error : function() {
				alert('提交选型失败!');
			},
			success : function(data) {
				if (data == null || data == "") {
					$.gritter.add({
						title : '成功',
						text : '已经选型成功!',
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-sucess'
					});
					//重新加载数据
					refreshData();
				} else
					$.gritter.add({
						title : '失败',
						text : data,
						//sticky: false,  
						time : 2000,
						//speed:500,  
						position : 'bottom-right',
						class_name : 'gritter-error'
					});
			}
		});
	}

	
	function productLineModel(userProductLineId)
	{
		var url = "../install/productLineModel?userProductLineId=" + userProductLineId;
		//alert(url);
		var title = "生产线选型》生产线建模";
		showPageContent(url, title);
	}
	
</script>
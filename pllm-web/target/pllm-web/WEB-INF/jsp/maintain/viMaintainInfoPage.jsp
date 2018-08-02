<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 设备维护页面 -->
<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" role="form">
		    <input type="hidden" id="queryGroupId" name="queryGroupId" value="${groupId}" />
		    <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="queryUserProductLineName">客户生产线</label>
				<div class="col-sm-4">
					<input type="text" id="queryUserProductLineName" name="queryUserProductLineName" class="col-xs-10 col-sm-5" />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="queryEquipmentName">设备名称</label>
				<div class="col-sm-4">
					<input type="text" id="queryEquipmentName" name="queryEquipmentName" class="col-xs-10 col-sm-5" />
				</div>
			</div>
			<div style="text-align: right; float: right;">
				<button id="btnQuery" class="btn btn-sm btn-purple">查询</button>
				<button id="btnAdd"   class="btn btn-sm btn-primary">新增</button>
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
				<th>客户公司</th>
				<th>客户生产线</th>
				<th>生产线状态</th>
				<th>设备名称</th>
				<th>设备状态</th>
				<th>维护时间</th>
				<th>维护类型</th>
				<th>维护说明</th>
				<th class="center">修改</th>
				<th class="center">删除</th>
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
						<input type="hidden" id="hiddenMaintainId" name="hiddenMaintainId" />
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="inputUserProductLineId">客户生产线</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="inputUserProductLineId" name="inputUserProductLineId" required>
									<option value=""></option>
									<c:forEach var="item" items="${userProductLines}">
										<option value="${item.userProductLineId}">${item.userProductLineName}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label no-padding-right"
								for="inputUserEquipmentId">生产线设备</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="inputUserEquipmentId"
									name="inputUserEquipmentId" required>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="inputMaintainTime">维护时间</label>
							<div class="col-sm-4">
									<input type="text" id="inputMaintainTime" name="inputMaintainTime" size="16" readonly required /> <span class="addon">
									<i class="fa fa-clock-o"></i>
								</span>
							</div>
							<label class="col-sm-2 control-label no-padding-right"
								for="inputMaintainType">维护类型</label>
							<div class="col-sm-4">
								<input type="text" id="inputMaintainType" name="inputMaintainType"  required />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="inputMaintainMemo">维护说明</label>
							<div class="col-sm-4">
								<input type="text" id="inputMaintainMemo" name="inputMaintainMemo"  required />
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
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="assets/js/dataTables.tableTools.min.js"></script>
<script src="assets/js/dataTables.colVis.min.js"></script>
<script src="assets/js/jquery.validate.min.js"></script>
<script src="assets/js/localization/messages_zh.js"></script>
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
												url : '../maintain/viMaintainInfo/',
												type : 'get',
												data : function(d) {
                                                    d.queryUserProductLineName=document.getElementById("queryUserProductLineName").value;
                                                    d.queryEquipmentName=document.getElementById("queryEquipmentName").value;
												},
											//"dataSrc" : function(json) {
											//	return json.data;
											//}
											}, //获取数据的处理函数 
											"searching" : false,//搜索功能
											"bSort" : true, //排序功能
											"aaSorting" : [],
											"columns" : [ {
												"data" : "maintainId",
												"orderable" : false,
												"searchable" : false
											},
											{
												"data" : "groupName",
												"orderable" : false,
												"searchable" : false
											}, 
											{
												"data" : "userProductLineName",
												"searchable" : false
											},
											{
												"data" : "productLineState",
												"searchable" : false
											},
											{
												"data" : "equipmentName",
												"searchable" : false
											},
											{
												"data" : "equipmentState",
												"searchable" : false
											},
											{
												"data" : "maintainTime",
												"searchable" : false
											},
											{
												"data" : "maintainType",
												"searchable" : false
											},
											{
												"data" : "maintainMemo",
												"searchable" : false
											},
											{
												"data" : "maintainId",
												"orderable" : false,
												"searchable" : false
											},
											{
												"data" : "maintainId",
												"orderable" : false,
												"searchable" : false
											} ],
											"columnDefs" : [
													{
														"targets" : 0,
														"data" : "maintainId",
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
														"targets" : 9 ,
														"data" : "maintainId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															str = str
																	+ '<a href="javascript:editObject('
																	+ meta.row
																	+ ')" >修改</a>';
															str = str
																	+ '</div>';
															return str;
														}
													},
													{
														"targets" : 10 ,
														"data" : "maintainId",
														"render" : function(
																data, type,
																row, meta) {
															var str = '<div class="center" >';
															str = str
																	+ '<a href="javascript:confirmDelete('
																	+ data
																	+ ')" >删除</a>';
															str = str
																	+ '</div>';
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
		//查询信息
		$('#btnQuery').on('click', function(e) {
			refreshData();
			e.preventDefault();
		});
		//新增信息
		$('#btnAdd').on('click', function(e) {
			$("#hiddenMaintainId").val(-1);
			$("#inputUserProductLineId").val("");
		    $("#inputUserEquipmentId").val("");
		    $("#inputMaintainTime").val("");
		    $("#inputMaintainType").val("");
		    $("#inputMaintainMemo").val("");
		  
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
		//修改用户生产线
		$('#inputUserProductLineId').change(function () {
			refreshInputUserEquipmentId();
		})
	});
	
	function refreshInputUserEquipmentId(){
		var id=$('#inputUserProductLineId').val();
		var url="../maintain/userEquipments?queryUserProductLineId="+id;
		//alert(url);
		$.ajax({
			type : "get",
			url : url,
			async : false,
			error : function() {
				alert("查询异常");
			},
			success : function(data) {
				$("#inputUserEquipmentId").empty();
				for(var index in data){
					$("#inputUserEquipmentId").append("<option value='" + data[index].userEquipmentId + "' >" 
							+ data[index].equipmentName + "</option>");
				}
				if(data.length>0)
					showChart();
				$('#divChartForm').modal();
			}
		});
	}
	
	//修改对象
	function editObject(rowIndex) {
		if (dataTableObj.data().length <= 0)
			return;
		if (rowIndex < 0)
			return;
		var row = dataTableObj.data()[rowIndex];
		$("#hiddenMaintainId").val(row["maintainId"]);
		
		$("#inputUserProductLineId").val(row["userProductLineId"]);
		refreshInputUserEquipmentId();
		$("#inputUserEquipmentId").val(row["userEquipmentId"]);
		$("#inputMaintainTime").val(row["maintainTime"]);
		$("#inputMaintainType").val(row["maintainType"]);
		$("#inputMaintainMemo").val(row["maintainMemo"]);
		
		$('#divInputForm').modal();
	}
	
	//提交对象
	function postObject() {
	    var obj=new Object();
		obj.maintainId =$("#hiddenMaintainId").val();
		obj.userEquipmentId=$("#inputUserEquipmentId").val();
		obj.maintainTime=$("#inputMaintainTime").val();
		obj.maintainType=$("#inputMaintainType").val();
		obj.maintainMemo=$("#inputMaintainMemo").val();
	
		var jsonStr = JSON.stringify(obj);
		//alert(jsonStr);
		var httpMethod="post";
		if($("#hiddenMaintainId").val()<=0)
		  httpMethod="post";
		else
		  httpMethod="put";
		$.ajax({
			type : httpMethod,
			url : "../maintain/viMaintainInfo/",
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
	
	var deleteId=null;
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
			url : "../maintain/viMaintainInfo/"+deleteId,
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
	
	$('#inputMaintainTime').datetimepicker({
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
</script>
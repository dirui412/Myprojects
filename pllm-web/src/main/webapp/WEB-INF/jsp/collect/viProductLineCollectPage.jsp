<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" role="form">
			<input type="hidden" id="queryGroupId" name="queryGroupId"
				value="${groupId}" />
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="queryUserProductLineName">客户生产线</label>
				<div class="col-sm-4">
					<input type="text" id="queryUserProductLineName"
						name="queryUserProductLineName" class="col-xs-10 col-sm-5" />
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="queryParamName">参数名称</label>
				<div class="col-sm-4">
					<input type="text" id="queryParamName" name="queryParamName"
						class="col-xs-10 col-sm-5" />
				</div>
			</div>
			<div style="text-align: right; float: right;">
				<div hidden="hidden" class="col-sm-4">
					<table id="table">
						<tr>
							<c:forEach var="item" items="${userProductLines}">
								<td>${item.userProductLineId}</td>
							</c:forEach>
						</tr>
					</table>
				</div>
				<button id="btnCollect" class="btn btn-sm btn-primary">采集</button>
				<button id="btnQuery" class="btn btn-sm btn-purple">查询</button>
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
				<th>客户厂商</th>
				<th>客户生产线</th>
				<th>生产线原型</th>
				<th>参数名称</th>
				<th>参数类型</th>
				<th>下限</th>
				<th>上限</th>
				<th>采集时间</th>
				<th>开关值</th>
				<th>模拟值</th>
			</tr>
		</thead>
		<tfoot></tfoot>
	</table>
</div>
<!-- 信息删除确认 -->
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
												url : '../collect/viProductLineCollect/',
												type : 'get',
												data : function(d) {
													d.queryGroupId = document
															.getElementById("queryGroupId").value;
													d.queryUserProductLineName = document
															.getElementById("queryUserProductLineName").value;
													d.queryParamName = document
															.getElementById("queryParamName").value;
												},
											//"dataSrc" : function(json) {
											//	return json.data;
											//}
											}, //获取数据的处理函数 
											"searching" : false,//搜索功能
											"bSort" : true, //排序功能
											"aaSorting" : [],
											"columns" : [
													{
														"data" : "productLineCollectId",
														"orderable" : false,
														"searchable" : false
													},
													{
														"data" : "groupName",
														"searchable" : false
													},
													{
														"data" : "userProductLineName",
														"searchable" : false
													}, {
														"data" : "productName",
														"searchable" : false
													}, {
														"data" : "paramName",
														"searchable" : false
													}, {
														"data" : "paramType",
														"searchable" : false
													}, {
														"data" : "paramDown",
														"searchable" : false
													}, {
														"data" : "paramUp",
														"searchable" : false
													}, {
														"data" : "collectTime",
														"searchable" : false
													}, {
														"data" : "paramState",
														"searchable" : false
													}, {
														"data" : "paramValue",
														"searchable" : false
													} ],
											"columnDefs" : [ {
												"targets" : 0,
												"data" : "productLineCollectId",
												"render" : function(data, type,
														row, meta) {

													var str = '<div class="center" > <label class="pos-rel">';
													str = str
															+ '<input type="checkbox" class="ace" value="'+data+'" />';
													str = str
															+ '<span class="lbl"></span></label> </div>';
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
											"language" : {
												"sProcessing" : "处理中...",
												"sLengthMenu" : "显示 _MENU_ 项结果",
												"sZeroRecords" : "没有匹配结果",
												"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
												"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
												"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
												"sInfoPostFix" : "",
												"sSearch" : "搜索:",
												"sUrl" : "",
												"sEmptyTable" : "表中数据为空",
												"sLoadingRecords" : "载入中...",
												"sInfoThousands" : ",",
												"oPaginate" : {
													"sFirst" : "首页",
													"sPrevious" : "上页",
													"sNext" : "下页",
													"sLast" : "末页"
												},
												"oAria" : {
													"sSortAscending" : ": 以升序排列此列",
													"sSortDescending" : ": 以降序排列此列"
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
		//伪造采集信息按钮绑定事件
		$('#btnCollect').on('click', function(e) {
			//alert("按钮被触发");
			creatCollectInfo();
			refreshData();
			e.preventDefault();
		});
	});
	
	//伪造采集信息
	function creatCollectInfo(){
		var mytable = document.getElementById('table');
		var userProductLineIds = new Array();
		for(var i=0; i<mytable.rows[0].cells.length; i++){
			userProductLineIds[i] = mytable.rows[0].cells[i].innerHTML;
			//alert(userProductLineIds[i]);
		}
		$.ajax({
			type : "post",
			url : "../rest/productLineCollect/",
			async : false, //默认为true 异步   
			data :{"userProductLineIds":userProductLineIds},
			success : function(data) {
				if (data == null || data == "") {
					$.gritter.add({
						title : '成功',
						text : '采集数据成功!',
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
			},
			error : function() {
				alert('采集数据失败!');
			}
		});
	}
</script>
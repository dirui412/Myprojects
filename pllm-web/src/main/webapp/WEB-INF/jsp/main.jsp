<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="lj.global.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<base href="<%=request.getContextPath()%>/resources/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>主页</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="assets/css/chosen.min.css" />
<link rel="stylesheet" href="assets/css/jquery.gritter.min.css" />
<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="assets/css/upload.css"/>
<!-- text fonts -->
<link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
<!--[endif]-->
<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lt IE 9]-->
<!-- <script src="assets/js/html5shiv.min.js"></script> html5shiv解决浏览器不支持html5问题，respond解决浏览器不支持css3的问题
<script src="assets/js/respond.min.js"></script> -->
<!--[endif]-->
</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle side bar</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<i class="navbar-brand">管理平台</i>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<!-- 任务菜单 -->
					<li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-tasks"></i> <span class="badge badge-grey">4</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-check"></i>
								4 任务需要完成</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left">Software Update</span> 
												<span class="pull-right">65%</span>
											</span>

											<span class="progress progress-mini">
												<span style="width: 65%" class="progress-bar"></span>
											</span>
									</a></li>

									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left">Hardware Upgrade</span> <span
													class="pull-right">35%</span>
											</span>

											<span class="progress progress-mini">
												<span style="width: 35%"
													class="progress-bar progress-bar-danger"></span>
											</span>
									</a></li>

									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left">Unit Testing</span> <span
													class="pull-right">15%</span>
											</span>

											<span class="progress progress-mini">
												<span style="width: 15%"
													class="progress-bar progress-bar-warning"></span>
											</span>
									</a></li>

									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left">Bug Fixes</span> <span
													class="pull-right">90%</span>
											</span>

											<span class="progress progress-mini progress-striped active">
												<span style="width: 90%"
													class="progress-bar progress-bar-success"></span>
											</span>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="#"> See tasks with
									details <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>
					<!-- 通知菜单 -->
					<li class="purple"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-bell icon-animated-bell"></i> <span
							class="badge badge-important">8</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i
								class="ace-icon fa fa-exclamation-triangle"></i> 8 个通知</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar navbar-pink">
									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left"> <i
													class="btn btn-xs no-hover btn-pink fa fa-comment"></i> New
													Comments
												</span> <span class="pull-right badge badge-info">+12</span>
											</span>
									</a></li>

									<li><a href="#"> <i
											class="btn btn-xs btn-primary fa fa-user"></i> Bob just
											signed up as an editor ...
									</a></li>

									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left"> <i
													class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
													New Orders
												</span> <span class="pull-right badge badge-success">+8</span>
											</span>
									</a></li>

									<li><a href="#">
											<span class="clearfix">
												<span class="pull-left"> <i
													class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
													Followers
												</span> <span class="pull-right badge badge-info">+11</span>
											</span>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="#"> See all
									notifications <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>
					<!-- 消息菜单 -->
					<li class="green"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-envelope icon-animated-vertical"></i> <span
							class="badge badge-success">5</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i
								class="ace-icon fa fa-envelope-o"></i> 5 条信息</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
									<li><a href="#" class="clearfix"> <img
											src="assets/avatars/avatar.png" class="msg-photo"
											alt="Alex's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Alex:</span>
													Ciao sociis natoque penatibus et auctor ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>a moment
														ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="#" class="clearfix"> <img
											src="assets/avatars/avatar3.png" class="msg-photo"
											alt="Susan's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Susan:</span>
													Vestibulum id ligula porta felis euismod ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>20 minutes
														ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="#" class="clearfix"> <img
											src="assets/avatars/avatar4.png" class="msg-photo"
											alt="Bob's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Bob:</span>
													Nullam quis risus eget urna mollis ornare ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>3:15 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="#" class="clearfix"> <img
											src="assets/avatars/avatar2.png" class="msg-photo"
											alt="Kate's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Kate:</span>
													Ciao sociis natoque eget urna mollis ornare ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>1:33 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="#" class="clearfix"> <img
											src="assets/avatars/avatar5.png" class="msg-photo"
											alt="Fred's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Fred:</span>
													Vestibulum id penatibus et auctor ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>10:09 am</span>
											</span>
										</span>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="inbox.html"> See
									all messages <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>
					<!-- 用户菜单 -->
					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="assets/avatars/user.jpg" alt="Jason's Photo" /> <span
							class="user-info"> <small>欢迎,</small> 用户01
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="javascript:changePassword()"> <i
									class="ace-icon fa fa-cog"></i> 帐号设置
							</a></li>
							<li class="divider"></li>
							<li><a href="javascript:exitSystem()"><i
									class="ace-icon fa fa-power-off"></i> 退出系统 </a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div id="sidebar" class="sidebar  responsive">
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'fixed')
				} catch (e) {

				}
			</script>
			<ul class="nav nav-list">
				<li class=""><a
					href='javascript:showPageContent("../GeneralChart?userProductLineId=" + 42,"生产线一览图")'>
						<i class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
							生产线一览图</span>
				</a> <b class="arrow"></b></li>
				<c:forEach var="item" items="${parentModules}">
					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-pencil-square-o"></i> <span
							class="menu-text">${item.moduleTitle}</span> <b
							class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>
						<ul class="submenu">
							<c:forEach var="child" items="${item.childModules}">
								<li class=""><a
									href='javascript:showPageContent("${child.moduleUrl}","${child.moduleTitle}")'>
										<i class="menu-icon fa fa-caret-right"></i>${child.moduleTitle}
								</a> <b class="arrow"></b></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>

			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script>
		</div>

		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">主页</a>
						</li>
						<li id="liModuleTitle" class="active">Blank Page</li>
					</ul>

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="divPageContent">
							<!-- PAGE CONTENT BEGINS -->
							<!-- PAGE CONTENT ENDS -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

	<%-- 	<div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <%=AppVar.sysConfig.getSystemName()%>
					</span>
				</div>
			</div>
		</div> --%>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>

	<div id="divChangePassword" class="modal" tabindex="-1">
		<div class="modal-dialog" style="width: 400px; height: 300px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">修改密码</h4>
				</div>
				<div class="modal-body">
					<div class="row widget-main">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<div class="col-sm-12">
									<input id="textPassword" name="textPassword" type="password"
										class="form-control" placeholder="输入新密码" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input id="textPasswordSecond" name="textPasswordSecond"
										type="password" class="form-control" placeholder="重复输入密码" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnChangePassword" type="button"
						class="width-65  btn btn-sm btn-success">
						<span class="bigger-110">确定</span> <i
							class="ace-icon fa fa-arrow-right icon-on-right"></i>
					</button>
				</div>
			</div>
		</div>
		<!-- /.widget-body -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="assets/js/jquery.2.1.1.min.js"></script>
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='assets/js/jquery.min.js'>"
						+ "<"+"/script>");
	</script>
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui.custom.min.js"></script>
	<script src="assets/js/jquery.gritter.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/localization/moment.zh-CN.js" charset="UTF-8"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
	<!-- ace scripts -->
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	<script type="text/javascript">
		$(function(){
			showPageContent("../GeneralChart?userProductLineId=" + 42,"生产线一览图");
		});
		function showPageContent(url, title) {
			//alert(url);
			$.get(url, function(result) {
				//返回登录页面
				if (url.indexOf("login") >= 0) {
					alert("return to login");
					window.location.href = "../login";
				}
				//填充模块页面
				else {
					$("#liModuleTitle").html(title);
					$('#divPageContent').html(result);
				}
			});
		}

		//修改密码
		function changePassword() {
			$('#divChangePassword').modal();
		}

		jQuery(function($) {
			//修改密码
			$('#btnChangePassword')
					.on(
							'click',
							function(e) {
								if ($("#textPassword").val() == "") {
									alert("新密码不能为空！")
									e.preventDefault();
									return;
								}
								if ($("#textPasswordSecond").val() == "") {
									alert("重复输入密码不能为空！")
									e.preventDefault();
									return;
								}
								if ($("#textPasswordSecond").val() != $(
										"#textPassword").val()) {
									alert("两个密码必须相同！")
									e.preventDefault();
									return;
								}

								var strData = "userPassword="
										+ $("#textPassword").val();
								//alert(strData);
								$
										.ajax({
											type : "post",
											url : "../user/userInfos/changeUserPassword",
											data : strData,
											datatype : "json",
											async : true, //默认为true 异步   
											error : function() {
												alert('提交失败!');
											},

											success : function(data) {
												$('#divChangePassword').modal(
														"hide");
												if (data == null || data == "") {
													$.gritter
															.add({
																title : '成功',
																text : '修改密码成功!',
																//sticky: false,  
																time : 2000,
																//speed:500,  
																position : 'bottom-right',
																class_name : 'gritter-sucess'
															});
												} else
													$.gritter
															.add({
																title : '修改密码失败',
																text : data,
																//sticky: false,  
																time : 2000,
																//speed:500,  
																position : 'bottom-right',
																class_name : 'gritter-error'
															});
											}
										});

							});
		});

		//退出系统
		function exitSystem() {
			$.ajax({
				type : "post",
				url : "../exitSystem",
				data : "",
				datatype : "json",
				async : true, //默认为true 异步   
				error : function() {
					alert('提交失败!');
				},

				success : function(data) {
					$('#divChangePassword').modal("hide");
					if (data == null || data == "") {
						$.gritter.add({
							title : '成功',
							text : '退出系统成功!',
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-sucess'
						});
						//跳转到登录页面
						window.location.href = "..";
					} else
						$.gritter.add({
							title : '修改密码失败',
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
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="lj.global.*"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<base href="<%=request.getContextPath()%>/resources/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>登录</title>

<meta name="description" content="User login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & font awesome -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />

<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />


</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h2>
								<i class="ace-icon fa fa-leaf green"></i> <span class="white"
									id="id-text2"><%=AppVar.sysConfig.getSystemName()%></span>
							</h2>
							<h4 class="blue" id="id-company-text">
								&copy;<%=AppVar.sysConfig.getCompanyName()%></h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i> 请输入你的信息
										</h4>

										<div class="space-6"></div>

										<form id="formLogin" method="post" action="../login">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														name="textUserCode" id="textUserCode" type="text"
														class="form-control" value="${userCode}" placeholder="登陆名" />
														<i class="ace-icon fa fa-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														name="textUserPassword" id="textUserPassword"
														type="password" class="form-control" placeholder="登陆密码" />
														<i class="ace-icon fa fa-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">


													<button type="button" id="btnLogin"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">登录</span>
													</button>
												</div>
												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>

									<!-- /.widget-main -->
									<div class="toolbar clearfix center">
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->
							<!-- /.forgot-box -->
							<!-- /.signup-box -->
						</div>
						<!-- /.position-relative -->

						<div class="navbar-fixed-top align-right">
							<br /> &nbsp; <a id="btn-login-dark" href="#">Dark</a> &nbsp; <span
								class="blue">/</span> &nbsp; <a id="btn-login-blur" href="#">Blur</a>
							&nbsp; <span class="blue">/</span> &nbsp; <a id="btn-login-light"
								href="#">Light</a> &nbsp; &nbsp; &nbsp;
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
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
	<script type="text/javascript">
		
		jQuery(function($) {
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
		});

		//you don't need this, just used for changing background
		jQuery(function($) {
			$('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				e.preventDefault();
			});
			$('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				e.preventDefault();
			});
			$('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				e.preventDefault();
			});

			//登录
			$('#btnLogin').on(
					'click',
					function(e) {
						var strCode = $('#textUserCode').val();
						var strPassword = $('#textUserPassword').val();
						if (strCode == null || strCode == '') {
							alert('用户名不能为空!');
							$('#textUserCode').focus();
							return;
						}
						if (strPassword == null || strPassword == '') {
							alert('用户密码不能为空!');
							$('#textUserPassword').focus();
							return;
						}
						var strData = "userCode=" + strCode + "&userPassword="
								+ strPassword;
						$.ajax({
							type : "post",
							url : "../login",
							data : strData,
							datatype : "json",
							async : false, //默认为true 异步   
							error : function() {
								alert('提交失败!');
							},
							success : function(data) {
								if (data == null || data == "") {
									//加入主页
									window.location.href = "../main";
								} else
									alert(data)
							}
						});
					});
		});
	</script>
</body>
</html>

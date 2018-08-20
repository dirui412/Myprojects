<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="assets/css/common.css?v=20180724"> <!-- 自定义的一些全局css -->
<link rel="stylesheet" href="assets/css/data_text.css">	<!-- 自定义的正文表格部分的css -->
<link rel="stylesheet" href="assets/css/foundation-datepicker.css">	<!-- 关于选择日期的css -->
<!--content开始-->
<div class="data_content">
	<!-- <div class="form-group"> -->
	    <div class="data_time">
	        <img src="assets/img/data.png" alt="" class="fl">
	        <input value="" id="demo-1" style="background:#F0F0F0; width: 80px;" placeholder="选择日期">
	    </div>
	<%--     <label class="col-sm-2 control-label no-padding-right" for="queryUserProductLineId">客户生产线</label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="queryUserProductLineId" name="queryUserProductLineId" required>
				<option value=""></option>
				<c:forEach var="item" items="${userProductLines}">
					<option value="${item.userProductLineId}">${item.userProductLineName}</option>
				</c:forEach>
			</select>
		</div>
	</div> --%>
    <div class="data_main">
        <div class="main_left fl">
            <div class="left_1">
                <div class="main_title">
                    <img src="assets/img/title_left1.png" alt="">
                    	设备故障类型
                </div>
                <div id="chart_1" class="chart" style="width:100%;height: 280px;"></div>
            </div>
            <div class="left_2">
                <div class="main_title">
                    <img src="assets/img/title_left2.png" alt="">
                    	物料用量统计
                </div>
                <div id="chart_2" class="chart" style="width:100%;height: 280px;"></div>
            </div>
         
        </div>
        <div class="main_center fl">
            <div class="center_text">
                <div class="main_title">
                    <img src="assets/img/title_middle1.png" alt="">
                    	生产线实况图
                </div>
                <div id="" style="background-color: whitesmoke;flex-grow: 1;width:100%; height: 610px"></div>
            </div>
        </div>
        <div class="main_right fr">
            <div class="right_1">
                <div class="main_title">
                    <img src="assets/img/title_right1.png" alt="">
                    	mttr&&mtbf
                </div>
                <div class="choice">
                    <label for="">类型：</label>
                    <select name="" id="">
                        <option value="">mttr</option>
                        <option value="">mtbf</option>
                    </select>
                </div>
                <div id="chart_3" class="echart" style="width:100%;height: 280px;"></div>
            </div>
            <div class="right_2">
                <div class="main_title">
                    <img src="assets/img/title_right2.png" alt="">
                    	生产效能统计
                </div>
                <div id="chart_4" class="echart fl" style="width:80%;height: 230px;"></div>
            </div>
        </div>
    </div>
    <div class="data_bottom">
     	<div class="bottom_1 fl">
            <div class="main_title">
                <img src="assets/img/title_bottom1.png" alt="">
                	本月污染物排放量
            </div>
            <div class="main_table">
                <table>
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>生产线</th>
                        <th>排放量(t)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>A</td>
                        <td>134.2</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>B</td>
                        <td>134.2</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>C</td>
                        <td>134.2</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>D</td>
                        <td>134.2</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>E</td>
                        <td>134.2</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="bottom_center fl">
            <div class="bottom_2 fl">
                <div class="main_title">
                    <img src="assets/img/title_bottom1.png" alt="">
                   		 本月废料排放量
                </div>
                <div class="main_table">
                    <table>
                        <thead>
                        <tr>
                            <th>排名</th>
                            <th>生产线</th>
                            <th>排放量(t)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>A</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>B</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>C</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>D</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>E</td>
                            <td>134.2</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="bottom_3 fl">
                <div class="main_title">
                    <img src="assets/img/title_bottom1.png" alt="">
                    	本月产品耐腐蚀时间
                </div>
                <div class="main_table">
                    <table>
                        <thead>
                        <tr>
                            <th>排名</th>
                            <th>生产线</th>
                            <th>时长(h)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>A</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>B</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>C</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>D</td>
                            <td>134.2</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>E</td>
                            <td>134.2</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="bottom_4 fr">
            <div class="main_title">
                <img src="assets/img/title_bottom1.png" alt="">
               		 本月产品正品率
            </div>
            <div class="main_table">
                <table>
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>生产线</th>
                        <th>正品率</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>A</td>
                        <td>96%</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>B</td>
                        <td>97%</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>C</td>
                        <td>98%</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>D</td>
                        <td>99%</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>E</td>
                        <td>99%</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
     
    </div>
</div>
<!--content结束-->

<script src="assets/js/jquery-2.2.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/common.js"></script>	 <!--自定义的一些js函数 -->
<script src="assets/js/echarts.min.js"></script>
<script src="assets/js/echart.js"></script>  <!-- 页面上的表格 -->
<script src="assets/js/foundation-datepicker.js"></script>	<!--关于选择日期的js-->
<script src="assets/js/foundation-datepicker.zh-CN.js"></script> 	<!--关于选择日期的js-->
<script>
    $('#demo-1').fdatepicker();

    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    var checkin = $('#dpd1').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
            var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate() + 1);
            checkout.update(newDate);
        }
        checkin.hide();
        $('#dpd2')[0].focus();
    }).data('datepicker');
    var checkout = $('#dpd2').fdatepicker({
        onRender: function (date) {
            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        checkout.hide();
    }).data('datepicker');
</script>
</html>
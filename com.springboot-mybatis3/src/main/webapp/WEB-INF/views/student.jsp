<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>超神学生信息列表</title>
	<script type="text/javascript" src="/webjars/echarts/4.0.4/dist/echarts.js"></script>
	<script type="text/javascript" src="/webjars/echarts/4.0.4/dist/echarts.common.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.js"></script>
	<style type="text/css">
		#main{
			width: 850px;
			height: 600px;
			position:absolute;
		    top:40%;
		    left:50%;
		    margin:-300px 0 0 -425px;
	 </style>
</head>
<body>
	<!-- 将后面mode传过来的值存放在input输入框中 -->
	<input id = "stuURL" type="hidden" th:value = '${stu}'/>
	<div id="main"></div>
	<script th:inline="javascript">
		var dataArr = ${stu};
		var dataInfo = [dataArr.length];
		var dataLegend = [dataArr.length];
		for(var i=0;i<dataArr.length;i++){
			dataInfo[i] = {value:dataArr[i].math,name:dataArr[i].name};
			dataLegend[i] = dataArr[i].name
		}
		 
		//整合echarts画图
		var myCharts = echarts.init(document.getElementById('main'), 'echartsTheme');
		var dataSeries = [
			{
				name:'战斗力',
				type:'pie',
				radius:'70%',
				center:['50%','60%'],
				label:{
					formatter:'{a}:{b}-{c}',
					fontSize:'100%'
				},
				data:dataInfo,
				itemStyle:{
					emphasis: {
	                	shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
				}
				
			}
		]
		
		//指定图表的配置项和数据
		var option = {
				title:{
					text:'战斗力评比',
					x:'center',
					top:'3%',
					textStyle:{
						fontSize: '200%'
					}
				},
				//提示框，鼠标悬停时显示当前信息
				tooltip:{
					trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    color:['#4CB4E7','#FFC09F','#FFEE93','#E2DBBE','#C7B3E5','#BAD4AA'],
        	    legend:[
        	    	{
        	    		orient: 'horizontal',
    					//top:'middle',
    					y:'70px',
    					data:dataLegend
        	    		//data:['百年修为-张三丰','无双剑姬-菲奥娜','放逐之刃-瑞文','傲之追猎者-狮子狗','神圣天使-卡尔']
        	    	}
        	    ],
        	    series:dataSeries
		};
		myCharts.setOption(option);
	</script>
</body>
</html>
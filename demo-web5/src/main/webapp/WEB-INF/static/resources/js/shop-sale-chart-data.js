// First Chart Example - Area Line Chart

$(document).ready(function () {
//	alert(request.QueryString("shopId"))
	$.get("http://localhost:8080/Bookshop3.0/background//shop/shopSaleChartData.do",{shopId:request.QueryString("shopId")},
			function(data){
//		alert(data)
		Morris.Area({
			  // ID of the element in which to draw the chart.
			  element: 'morris-chart-area1',
			  behaveLikeLine: true,
			  // Chart data records -- each entry in this array corresponds to a point on
			  // the chart.
			  data: eval("(" + data+ ")"),
			  // The name of the data record attribute that contains x-visitss.
			  xkey: 'affirmDate',
			  // A list of names of data record attributes that contain y-visitss.
			  ykeys: ['userId'],
			  // Labels for the ykeys -- will be displayed when you hover over the
			  // chart.
			  labels: ['订单成交量'],
			  // Disables line smoothing
			  smooth: true
			});
		Morris.Area({
			  // ID of the element in which to draw the chart.
			  element: 'morris-chart-area2',
			  behaveLikeLine: true,
			  // Chart data records -- each entry in this array corresponds to a point on
			  // the chart.
			  data: eval("(" + data+ ")"),
			  // The name of the data record attribute that contains x-visitss.
			  xkey: 'affirmDate',
			  // A list of names of data record attributes that contain y-visitss.
			  ykeys: ['orderPrice'],
			  // Labels for the ykeys -- will be displayed when you hover over the
			  // chart.
			  labels: ['图书总价值'],
			  // Disables line smoothing
			  smooth: true
			});
		Morris.Area({
			  // ID of the element in which to draw the chart.
			  element: 'morris-chart-area3',
			  behaveLikeLine: true,
			  // Chart data records -- each entry in this array corresponds to a point on
			  // the chart.
			  data: eval("(" + data+ ")"),
			  // The name of the data record attribute that contains x-visitss.
			  xkey: 'affirmDate',
			  // A list of names of data record attributes that contain y-visitss.
			  ykeys: ['discountPrice'],
			  // Labels for the ykeys -- will be displayed when you hover over the
			  // chart.
			  labels: ['交易额'],
			  // Disables line smoothing
			  smooth: true
			});
	});
		
});
	
	



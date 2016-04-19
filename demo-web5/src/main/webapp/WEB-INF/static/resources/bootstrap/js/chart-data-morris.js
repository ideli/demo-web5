// First Chart Example - Area Line Chart

Morris.Area({
  // ID of the element in which to draw the chart.
  element: 'morris-chart-area',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: [
	{ d: '2012-10-01', visits: 802,visits2: 802 },
	{ d: '2012-10-02', visits: 783 ,visits2: 802},
	{ d: '2012-10-03', visits:  820,visits2: 802 },
	{ d: '2012-10-04', visits: 839 ,visits2: 802},
	{ d: '2012-10-05', visits: 792 ,visits2: 802},
	{ d: '2012-10-06', visits: 859 ,visits2: 802},
	{ d: '2012-10-07', visits: 790 ,visits2: 802},
	{ d: '2012-10-08', visits: 1680,visits2: 802 },
	{ d: '2012-10-09', visits: 1592 ,visits2: 802},
	{ d: '2012-10-10', visits: 1420 ,visits2: 802},
	{ d: '2012-10-11', visits: 882 ,visits2: 802},
	{ d: '2012-10-12', visits: 889 ,visits2: 802},
	{ d: '2012-10-13', visits: 819 ,visits2: 802},
	{ d: '2012-10-14', visits: 849 ,visits2: 802},
	{ d: '2012-10-15', visits: 870 ,visits2: 802},
	{ d: '2012-10-16', visits: 1063 ,visits2: 802},
	{ d: '2012-10-17', visits: 1192 ,visits2: 802},
	{ d: '2012-10-18', visits: 1224 ,visits2: 802},
	{ d: '2012-10-19', visits: 1329 ,visits2: 802},
	{ d: '2012-10-20', visits: 1329 ,visits2: 802},
	{ d: '2012-10-21', visits: 1239 ,visits2: 802},
	{ d: '2012-10-22', visits: 1190 ,visits2: 802},
	{ d: '2012-10-23', visits: 1312 ,visits2: 802},
	{ d: '2012-10-24', visits: 1293 ,visits2: 802},
	{ d: '2012-10-25', visits: 1283 ,visits2: 802},
	{ d: '2012-10-26', visits: 1248 ,visits2: 802},
	{ d: '2012-10-27', visits: 1323 ,visits2: 802},
	{ d: '2012-10-28', visits: 1390 ,visits2: 802},
	{ d: '2012-10-29', visits: 1420 ,visits2: 802},
	{ d: '2012-10-30', visits: 1529 ,visits2: 802},
	{ d: '2012-10-31', visits: 1892,visits2: 802 },
  ],
  // The name of the data record attribute that contains x-visitss.
  xkey: 'd',
  // A list of names of data record attributes that contain y-visitss.
  ykeys: ['visits','visits2'],
  // Labels for the ykeys -- will be displayed when you hover over the
  // chart.
  labels: ['Visits','visits2'],
  // Disables line smoothing
  smooth: true,
});


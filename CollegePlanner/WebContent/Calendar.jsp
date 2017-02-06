<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calendar</title>

<!-- https://jqueryui.com/datepicker/ -->
<!-- http://docs.telerik.com/kendo-ui/api/javascript/ui/scheduler -->

 
 <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.rtl.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.silver.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.mobile.all.min.css"/>

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>
</head>
<body>
  
<div id="scheduler"></div>
<script>
$("#scheduler").kendoScheduler({
  date: new Date(),
  currentTimeMarker: {
    updateInterval: 100
  },
  views: [
    "day", "week", "workWeek"
  ]
});
</script>
</body>
</html>
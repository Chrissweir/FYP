<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calendar</title>

<!-- https://jqueryui.com/datepicker/ -->

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.rtl.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.silver.min.css"/>
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.118/styles/kendo.mobile.all.min.css"/>

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  allDayEventTemplate: $("#event-template").html(),
  dataSource: [
    {
      id: 1,
      start: new Date("2013/6/6 08:00 AM"),
      end: new Date("2013/6/6 09:00 AM"),
      isAllDay: true,
      title: "Interview",
      attendees: [1,2]
    }
  ],
  resources: [
    {
      field: "attendees",
      dataSource: [
       { value: 1, text: "Alex" },
       { value: 2, text: "Bob" }
      ],
      multiple: true
    }
  ]
});
  
	
  </script>
  
</head>
<body>
 
<p>Date: <input type="text" id="datepicker"></p>
 
 
</body>
</html>
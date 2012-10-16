<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="application/javascript">
    function draw() {
      var canvas = document.getElementById("canvas");
      if (canvas.getContext) {
        var ctx = canvas.getContext("2d");
 
        ctx.fillStyle = "rgb(0,200,0)";
        var factor = 3;
        ctx.fillRect (0, 0, 70*factor, 100*factor);
      }
    }
  </script>
 </head>
 <body style="margin:0; padding:0"onload="draw();">
   <canvas id="canvas" width="1000" height="1000"></canvas>
 </body>
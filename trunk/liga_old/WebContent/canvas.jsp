<%@page import="eu.benjam.liga.field.Measures"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Canvas</title>
<script src="jquery-1.8.1.min.js" ></script>
<script src="jcanvas.min.js" ></script>
<style type="text/css">
	body{margin:0}
</style>
</head>
<%
	Measures m = new Measures(150);
%>
<body>
<script>
$(document).ready(function() {

	/*VARIABLES*/
	var STROKE_WIDTH = 2;
	var COLOR_LINE = "#FFFFFF";
	var COLOR_FIELD = "gray";
	//var COLOR_FIELD = "#46B428";
	var FIELD_X = parseInt("<%=m.getFIELD_X()%>");
	var FIELD_Y = parseInt("<%=m.getFIELD_Y()%>");
	var SPOT_RADIUS = parseInt("<%=m.getCORNER_RADIUS()%>");
	var CENTER_CIRCLE_RADIUS = parseInt("<%=m.getCENTER_CIRCLE_RADIUS()%>");
	var PENALTY_AREA_X = parseInt("<%=m.getPENALTY_AREA_X()%>");
	var PENALTY_AREA_Y = parseInt("<%=m.getPENALTY_AREA_Y()%>");
	var PENALTY_SPOT_Y = parseInt("<%=m.getPENALTY_SPOT_Y()%>");
	var GOAL_AREA_X = parseInt("<%=m.getGOAL_AREA_X()%>");
	var GOAL_AREA_Y = parseInt("<%=m.getGOAL_AREA_Y()%>");
	var CORNER_RADIUS =  parseInt("<%=m.getCORNER_RADIUS()%>");

	$("#field").drawRect({
		layer: true,
		name: "Feld",
		fillStyle: COLOR_FIELD,
		x: FIELD_X/2, y: FIELD_Y/2,width:FIELD_X,height:FIELD_Y,
		mousemove: function(layer) {
			$('#x').text(layer.mouseX);
			$('#y').text(layer.mouseY);
			var dx, dy, dist;
			dx = layer.mouseX - layer.x;
			dy = layer.mouseY - layer.y;
			dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			
		}
	});
	$("#field").drawLine({
			layer: true,
			group: "lines",
			name: "Aussenline",
			strokeStyle: COLOR_LINE,
			strokeWidth: STROKE_WIDTH,
			rounded: false,
			x1: 1, y1: 1,
			x2: FIELD_X-1, y2: 1,
			x3: FIELD_X-1, y3: FIELD_Y-1,
			x4: 1, y4: FIELD_Y-1,
			x5: 1, y5:1
	});
	$("#field").drawLine({
		layer: true,
		group: "lines",
		name: "Mittelline",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		x1: 0, y1: FIELD_Y/2,
		x2: FIELD_X, y2: FIELD_Y/2,
	});
	$("#field").drawEllipse({
		layer: true,
		group: "lines",
		name: "Mittelkreis",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y/2,
		width: CENTER_CIRCLE_RADIUS*2, 
		height: CENTER_CIRCLE_RADIUS*2
	});
	$("#field").drawEllipse({
		layer: true,
		group: "lines",
		name: "Mittelpunkt",
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y/2,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	$("#field").drawEllipse({
		layer: true,
		group: "lines",
		name: "PENALTY_SPOT_A",
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: PENALTY_SPOT_Y,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	$("#field").drawEllipse({
		layer: true,
		group: "lines",
		name: "PENALTY_SPOT_B",
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y - PENALTY_SPOT_Y,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	/** AREAS **/
	$("#field").drawLine({
		layer: true,
		group: "PENALTY_AREA_A",
		name: "Strafraum A",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - PENALTY_AREA_X)/2, 
		x1: 0, y1: 0,
		x2: 0, y2: PENALTY_AREA_Y,
		x3: PENALTY_AREA_X, y3: PENALTY_AREA_Y,
		x4: PENALTY_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		layer: true,
		group: "PENALTY_AREA_B",
		name: "Strafraum B",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - PENALTY_AREA_X)/2,
		translateY: FIELD_Y - PENALTY_AREA_Y,
		x1: 0, y1: 0,
		x2: 0, y2: PENALTY_AREA_Y,
		x3: PENALTY_AREA_X, y3: PENALTY_AREA_Y,
		x4: PENALTY_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		layer: true,
		group: "GOAL_AREA_A",
		name: "Torraum A",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - GOAL_AREA_X)/2, 
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_AREA_Y,
		x3: GOAL_AREA_X, y3: GOAL_AREA_Y,
		x4: GOAL_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		layer: true,
		group: "GOAL_AREA_B",
		name: "Torraum B",
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - GOAL_AREA_X)/2,
		translateY: FIELD_Y - GOAL_AREA_Y,
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_AREA_Y,
		x3: GOAL_AREA_X, y3: GOAL_AREA_Y,
		x4: GOAL_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	/*****************************
	******** CORNERS *************
	******************************/
	$("#field").drawEllipse({
		group: "lines",
		name: "Corner LT",
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: 0,
		y: 0,
		width:  CORNER_RADIUS*4,
		height: CORNER_RADIUS*4,
	});
	$("#field").drawEllipse({
		group: "lines",
		name: "Corner LB",
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: 0, y: FIELD_Y,
		width:  CORNER_RADIUS*4,
		height: CORNER_RADIUS*4,
	});
	$("#field").drawEllipse({
		group: "lines",
		name: "Corner RT",
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X ,y: 0,
		width:  CORNER_RADIUS*4,
		height: CORNER_RADIUS*4,
	});
	$("#field").drawEllipse({
		group: "lines",
		name: "Corner RB",
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X, y: FIELD_Y,
		width:  CORNER_RADIUS*4,
		height: CORNER_RADIUS*4,
	});
	/************ OBJECTS ************/
	/** BALL **/
	$("#field").drawEllipse({
		layer: true,
		fillStyle: "yellow",
		draggable: true,
		name: "Ball",
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2, y: FIELD_Y/2+10,
		width:  SPOT_RADIUS*4,
		height: SPOT_RADIUS*4,
	});
	
	
	for(var i=1; i<=11; i++){
		$("#field").drawPolygon({
			layer: true,
			fillStyle: "red",
			name: "Triangle B",
			x: i*10, y: FIELD_Y-20,
			draggable: true,
			opacity: 0.5,
			radius: SPOT_RADIUS*4,
			sides: 3,
		});
		$("#field").drawPolygon({
			layer: true,
			fillStyle: "blue",
			name: "Triangle A",
			x:  i*10, y: 20,
			draggable: true,
			rotate: 180,
			opacity: 0.5,
			radius: SPOT_RADIUS*4,
			sides: 3,
		});
	}

	
});


</script>
<canvas id="field" width="<%=m.getFIELD_X()%>" height="<%=m.getFIELD_Y()%>"></canvas>
<br/>
X<span id="x"></span><br/>
Y<span id="y"></span>
</body>
</html>
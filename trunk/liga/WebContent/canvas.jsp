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
	Measures m = new Measures(600);

%>
<body>
<script>
$(document).ready(function() {

	/*VARIABLES*/
	var STROKE_WIDTH = 2;
	var COLOR_LINE = "#FFFFFF";
	var COLOR_FIELD = "#46B428";
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
	var GOAL_X = parseInt("<%=m.getGOAL_X()%>");
	var GOAL_Y = parseInt("<%=m.getGOAL_Y()%>");
	var CORNER_RADIUS =  parseInt("<%=m.getCORNER_RADIUS()%>");
	var PIXEL_PER_METER =  parseFloat("<%=m.PIXEL_PER_METER%>");
	
	/***************************
	*********** GROUP 1 ********
	****************************/
	$("#field").drawRect({
		name: "Feld", //Grass
		group: "GROUP1",
		layer: true,
		fillStyle: COLOR_FIELD,
		x: FIELD_X/2, y: FIELD_Y/2,width:FIELD_X,height:FIELD_Y,
		mousemove: function(layer) {
			$('#x').text(layer.mouseX);
			$('#y').text(layer.mouseY);
		}
	});
	/***************************
	*********** GROUP 2 ********
	****************************/
	$("#field").drawLine({
		name: "Strafraum A",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - PENALTY_AREA_X)/2,
		translateY: 0,
		x1: 0, y1: 0,
		x2: 0, y2: PENALTY_AREA_Y,
		x3: PENALTY_AREA_X, y3: PENALTY_AREA_Y,
		x4: PENALTY_AREA_X, y4: 0,
		x5: 0, y5: 0,
		mousemove: function(layer) {
			$(this).css({cursor: "pointer"});  
			$(this).setLayer(layer, {
				fillStyle: "#f8ac22",
			});
		},
		mouseout: function(layer) {
			$(this).css({cursor: "default"});  
			$(this).setLayer(layer, {
				fillStyle: "transparent",
			});
		}
	});
	$("#field").drawLine({
		name: "Strafraum B",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		translateX: (FIELD_X - PENALTY_AREA_X)/2,
		translateY: FIELD_Y - PENALTY_AREA_Y,
		x1: 0, y1: 0,
		x2: 0, y2: PENALTY_AREA_Y,
		x3: PENALTY_AREA_X, y3: PENALTY_AREA_Y,
		x4: PENALTY_AREA_X, y4: 0,
		x5: 0, y5: 0,
		mousemove: function(layer) {
			$(this).css({cursor: "pointer"});  
			$(this).setLayer(layer, {
				fillStyle: "#f8ac22",
			});
		},
		mouseout: function(layer) {
			$(this).css({cursor: "default"});  
			$(this).setLayer(layer, {
				fillStyle: "transparent",
			});
		}
	});
	$("#field").drawLine({
		name: "Torraum A",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		translateX: (FIELD_X - GOAL_AREA_X)/2, 
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_AREA_Y,
		x3: GOAL_AREA_X, y3: GOAL_AREA_Y,
		x4: GOAL_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		name: "Torraum B",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		translateX: (FIELD_X - GOAL_AREA_X)/2,
		translateY: FIELD_Y - GOAL_AREA_Y,
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_AREA_Y,
		x3: GOAL_AREA_X, y3: GOAL_AREA_Y,
		x4: GOAL_AREA_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		name: "Tor A",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		translateX: (FIELD_X - GOAL_X)/2,
		translateY: 0,
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_Y,
		x3: GOAL_X, y3: GOAL_Y,
		x4: GOAL_X, y4: 0,
		x5: 0, y5: 0,
	});
	$("#field").drawLine({
		name: "Tor B",
		group: "GROUP2",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		translateX: (FIELD_X - GOAL_X)/2,
		translateY: FIELD_Y - GOAL_Y,
		x1: 0, y1: 0,
		x2: 0, y2: GOAL_Y,
		x3: GOAL_X, y3: GOAL_Y,
		x4: GOAL_X, y4: 0,
		x5: 0, y5: 0,
	});

	/***************************
	*********** GROUP 3 ********
	****************************/
	$("#field").drawLine({
		name: "Seitenline",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x1: 1, y1: 1,
		x2: FIELD_X-1, y2: 1,
		x3: FIELD_X-1, y3: FIELD_Y-1,
		x4: 1, y4: FIELD_Y-1,
		x5: 1, y5: 1
	});
	$("#field").drawLine({
		name: "Mittelline",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		opacity: 1,
		strokeWidth: STROKE_WIDTH,
		rounded: false,
		x1: 0, y1: FIELD_Y/2,
		x2: FIELD_X, y2: FIELD_Y/2,
	});
	$("#field").drawEllipse({
		name: "Mittelkreis",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y/2,
		width: CENTER_CIRCLE_RADIUS*2, 
		height: CENTER_CIRCLE_RADIUS*2
	});
	$("#field").drawEllipse({
		name: "Mittelpunkt",
		group: "GROUP3",
		layer: true,
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y/2,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	$("#field").drawEllipse({
		name: "Elfmeterpunkt A",
		group: "GROUP3",
		layer: true,
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: PENALTY_SPOT_Y,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	$("#field").drawArc({
		name: "Elfmeterkreis A",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: PENALTY_SPOT_Y,
		radius: CENTER_CIRCLE_RADIUS, 
		start: 125.5, end:234.5
	});
	$("#field").drawEllipse({
		name: "Elfmeterpunkt B",
		group: "GROUP3",
		layer: true,
		fillStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y - PENALTY_SPOT_Y,
		width: SPOT_RADIUS, 
		height: SPOT_RADIUS
	});
	$("#field").drawArc({
		name: "Elfmeterkreis B",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2,
		y: FIELD_Y - PENALTY_SPOT_Y,
		radius: CENTER_CIRCLE_RADIUS, 
		start: 305.5, end: 54.5
	});
	$("#field").drawArc({
		name: "Ecke LT",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: 0,
		y: 0,
		start: 90, end: 180,
		radius:  CORNER_RADIUS*2
	});
	$("#field").drawArc({
		name: "Ecke LB",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: 0, y: FIELD_Y,
		start: 0, end: 90,
		radius:  CORNER_RADIUS*2
		
	});
	$("#field").drawArc({
		name: "Ecke RT",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X ,y: 0,
		start: 180, end: 270,
		radius:  CORNER_RADIUS*2
	});
	$("#field").drawArc({
		name: "Ecke RB",
		group: "GROUP3",
		layer: true,
		strokeStyle: COLOR_LINE,
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X, y: FIELD_Y,
		start: 270, end: 0,
		radius:  CORNER_RADIUS*2
	});
	/***************************
	*********** GROUP 4 ********
	****************************/
	$("#field").drawArc({
		name: "Ball",
		group: "GROUP4",
		layer: true,
		fillStyle: "#FFFFFF",
		draggable: true,
		strokeStyle: "#000000",
		strokeWidth: STROKE_WIDTH,
		x: FIELD_X/2, y: FIELD_Y/2+10,
		radius:  SPOT_RADIUS,
	});
	
	for(var i=0;i<=105;i++){
		$("#field").drawLine({
			name: "MatrixY",
			group: "GROUP4",
			layer: true,
			draggable: true,
			strokeStyle: "blue",
			opacity: 0.05,
			strokeWidth: 1,
			x1: 0, y1: PIXEL_PER_METER*i,
			x2: FIELD_X, y2: PIXEL_PER_METER*i,
		});
	}
	for(var i=0;i<=68;i++){
		$("#field").drawLine({
			name: "MatrixX",
			group: "GROUP4",
			layer: true,
			draggable: true,
			strokeStyle: "blue",
			opacity: 0.05,
			strokeWidth: 1,
			x1: PIXEL_PER_METER*i, y1: 0,
			x2: PIXEL_PER_METER*i, y2: FIELD_Y,
		});
	}

	
	/** TEST 
	$("#field").drawLine({
		name: "Visible",
		group: "GROUP4",
		layer: true,
		fillStyle: "white",
		opacity: 0.3,
		x1:120, y1: 0,
		x2: 75, y2: 170,
		x3: 223, y3: 170,
		x4: 333, y4: 120,
		x5: 484, y5: 0,

	});
	$("#field").drawPolygon({
		name: "Torwart",
		group: "GROUP4",
		layer: true,
		fillStyle: "yellow",
		x: FIELD_X/2, y: 10,
		draggable: true,
		rotate: 180,
		opacity: 0.5,
		radius: SPOT_RADIUS*2,
		sides: 3,
	});
	$("#field").drawPolygon({
		name: "Bayern",
		group: "GROUP4",
		layer: true,
		fillStyle: "red",
		x: FIELD_X/2, y: 50,
		draggable: true,
		rotate: 0,
		opacity: 0.5,
		radius: SPOT_RADIUS*2,
		sides: 3,
	});
	$("#field").drawPolygon({
		name: "Stuttgart",
		group: "GROUP4",
		layer: true,
		fillStyle: "white",
		x: FIELD_X/2+10, y: 60,
		draggable: true,
		rotate: 350,
		opacity: 0.5,
		radius: SPOT_RADIUS*2,
		sides: 3,
	});**/
	/**
	for(var i=1; i<=11; i++){
		$("#field").drawPolygon({
			name: "Spieler A",
			group: "GROUP4",
			layer: true,
			fillStyle: "red",
			x: i*10, y: FIELD_Y-20,
			draggable: true,
			opacity: 0.5,
			radius: SPOT_RADIUS*4,
			sides: 3,
		});
		$("#field").drawPolygon({
			name: "Spieler B",
			group: "GROUP4",
			layer: true,
			fillStyle: "blue",
			x:  i*10, y: 20,
			draggable: true,
			rotate: 180,
			opacity: 0.5,
			radius: SPOT_RADIUS*4,
			sides: 3,
		});
	}*/
});


</script>
<canvas id="field" style="margin: <%=m.getOUTER_FIELD_LENGTH()%>px;vertical-align: top;float:left;" width="<%=m.getFIELD_X()%>" height="<%=m.getFIELD_Y()%>"></canvas>

<br/>
X<span id="x"></span><br/>
Y<span id="y"></span>
</body>
</html>
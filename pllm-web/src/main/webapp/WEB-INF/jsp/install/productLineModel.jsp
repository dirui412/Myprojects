<%@ page language="java" pageEncoding="utf-8" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>production_line</title>
<meta name="description"
	content="production_line" />
<!-- Copyright 1998-2017 by Northwoods Software Corporation. -->
<meta charset="UTF-8">
<script src="assets/js/go-debug1.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script id="code">
	$(function init()
	{
		var $ = go.GraphObject.make; // for conciseness in defining templates
	
		myDiagram = $(go.Diagram, "myDiagramDiv", // must name or refer to the DIV HTML element
		{
	 	/* 		grid : $(go.Panel, "Grid", $(go.Shape, "LineH", {
				stroke : "lightgray",
				strokeWidth : 0.5
			}), $(go.Shape, "LineH", {
				stroke : "gray",
				strokeWidth : 0.5,
				interval : 10
			}), $(go.Shape, "LineV", {
				stroke : "lightgray",
				strokeWidth : 0.5
			}), $(go.Shape, "LineV", {
				stroke : "gray",
				strokeWidth : 0.5,
				interval : 10
			})), */
			allowDrop : true, // must be true to accept drops from the Palette 右边的面板允许放置图形
		    //mouseOver: doMouseOver,  // this event handler is defined below 鼠标经过时，调用doMouseOver函数
	        //click: doMouseOver , // this event handler is defined below
			"draggingTool.dragsLink" : true,
			"draggingTool.isGridSnapEnabled" : true,
			"linkingTool.isUnconnectedLinkValid" : true,
			"linkingTool.portGravity" : 20,
			"relinkingTool.isUnconnectedLinkValid" : true,
			"relinkingTool.portGravity" : 20,
			//连接线的起始点小菱形
			"relinkingTool.fromHandleArchetype" : $(go.Shape, "Diamond", {
				segmentIndex : 0,
				cursor : "pointer",
				desiredSize : new go.Size(8, 8),
				fill : "tomato",
				stroke : "darkred"
			}),
			//连接线终点的小菱形
			"relinkingTool.toHandleArchetype" : $(go.Shape, "Diamond", {
				segmentIndex : -1,
				cursor : "pointer",
				desiredSize : new go.Size(8, 8),
				fill : "darkred",
				stroke : "tomato"
			}),
			//连接线中间点的小菱形
			"linkReshapingTool.handleArchetype" : $(go.Shape, "Diamond", {
				desiredSize : new go.Size(7, 7),
				fill : "lightblue",
				stroke : "deepskyblue"
			}),
			rotatingTool : $(TopRotatingTool), // defined below
			"rotatingTool.snapAngleMultiple" : 15,
			"rotatingTool.snapAngleEpsilon" : 15,
			"undoManager.isEnabled" : true
		});
	
		//when the document is modified, add a "*" to the title and enable the "Save" button
		//DiagramListener实时监听，当画板被更改时isModified为true，在title上加上*，并且使保存按钮生效。
		//监听器触发：myDiagram.isModified的值发生改变时触发此函数
		myDiagram.addDiagramListener("Modified", function(e) 
		{
			//alert("isModified1"+myDiagram.isModified);
			var button = document.getElementById("SaveButton");
			if (button){
				button.disabled = !myDiagram.isModified; //当监听到isModified为true画布被修改时,button生效
				if('${productLineState}'=="已验收")
					button.disabled=true;
			}
			var idx = document.title.indexOf("*");		 //当执行完save()时，isModified被修改为false,button失效
			if (myDiagram.isModified) {
				//alert("isModified2"+myDiagram.isModified);
				if (idx < 0)
					document.title += "*";
			} else {
				if (idx >= 0)
					document.title = document.title.substr(0, idx);
			}
		});
	
		// Define a function for creating a "port" that is normally transparent.
		// The "name" is used as the GraphObject.portId, the "spot" is used to control how links connect
		// and where the port is positioned on the node, and the boolean "output" and "input" arguments
		// control whether the user can draw links from or to the port.
		//创建一个port,ID为name,spot控制其怎么被连接,放置于node的什么位置,output/input决定其哪里可以from和to
		function makePort(name, spot, output, input) 
		{
			// the port is basically just a small transparent square
			return $(go.Shape, "Circle", {
				fill : null, // not seen, by default; set to a translucent gray by showSmallPorts, defined below
				stroke : null,
				desiredSize : new go.Size(7, 7),
				alignment : spot, // align the port on the main Shape
				alignmentFocus : spot, // just inside the Shape
				portId : name, // declare this object to be a "port"
				fromSpot : spot,
				toSpot : spot, // declare where links may connect at this port
				fromLinkable : output,
				toLinkable : input, // declare whether the user may draw links to/from here
				cursor : "pointer" // show a different cursor to indicate potential link point
			});
		}
	
		//选中修饰，矩形框
		var nodeSelectionAdornmentTemplate = 
		$(go.Adornment, "Auto", 
				$(go.Shape, {
					fill : null,
					stroke : "deepskyblue",
					strokeWidth : 1.5,
					strokeDashArray : [ 4, 2 ]
				}), 
				$(go.Placeholder)
		);
		//改变大小修饰
		var nodeResizeAdornmentTemplate = $(go.Adornment, "Spot", {
			locationSpot : go.Spot.Right
		}, $(go.Placeholder), $(go.Shape, {		//左上
			alignment : go.Spot.TopLeft,
			cursor : "nw-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//上
			alignment : go.Spot.Top,
			cursor : "n-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//右上
			alignment : go.Spot.TopRight,
			cursor : "ne-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}),
	
		$(go.Shape, {							//左中
			alignment : go.Spot.Left,
			cursor : "w-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//右中
			alignment : go.Spot.Right,
			cursor : "e-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}),
	
		$(go.Shape, {							//下左
			alignment : go.Spot.BottomLeft,
			cursor : "se-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//下
			alignment : go.Spot.Bottom,
			cursor : "s-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//下右
			alignment : go.Spot.BottomRight,
			cursor : "sw-resize",
			desiredSize : new go.Size(6, 6),
			fill : "lightblue",
			stroke : "deepskyblue"
		}));
			
		//旋转修饰
		var nodeRotateAdornmentTemplate = $(go.Adornment, {
			locationSpot : go.Spot.Center,
			locationObjectName : "CIRCLE"
		}, $(go.Shape, "Circle", {				//旋转小圆点
			name : "CIRCLE",
			cursor : "pointer",
			desiredSize : new go.Size(7, 7),
			fill : "lightblue",
			stroke : "deepskyblue"
		}), $(go.Shape, {						//旋转小圆点狭下面的线
			geometryString : "M3.5 7 L3.5 30",
			isGeometryPositioned : true,
			stroke : "lightblue",
			strokeWidth : 1.5,
			strokeDashArray : [ 4, 2 ]
		}));
		
	    // This converter is used by the Picture.
	    function findHeadShot(key) {
	    	
	      //if (key < 0 || key > 16) return "images/HSnopic.png"; // There are only 16 images on the server
	      return "assets/images/HS/" + key + ".png"
	    }
		    
		//节点模板
		myDiagram.nodeTemplate = 
		$(go.Node, "Spot", 
			{locationSpot : go.Spot.Center}, 
			new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify), 
			{//设置其可选择
				selectable : true,
				selectionAdornmentTemplate : nodeSelectionAdornmentTemplate
			}, {//设置其可改变大小
				resizable : true,
				resizeObjectName : "Picture",
				resizeAdornmentTemplate : nodeResizeAdornmentTemplate
			}, {//设置其可旋转
				rotatable : true,
				rotateAdornmentTemplate : nodeRotateAdornmentTemplate
			},
			new go.Binding("angle").makeTwoWay(),
			// the main object is a Panel that surrounds a TextBlock with a Shape ~图形：Panel包围着TextBlock
			$(go.Panel, "Vertical", 
				{
					name : "PANEL"
				}, 
				new go.Binding("desiredSize", "size", go.Size.parse).makeTwoWay(go.Size.stringify), 
				$(go.Shape, "Circle",	//显示operation的图形
						{ alignment: go.Spot.TopLeft, alignmentFocus: go.Spot.TopLeft,
						width: 12, height: 12, fill: "orange" },
						new go.Binding("figure", "operation", nodeOperationConverter)
					),
				$(go.Shape, "Triangle", //显示status的图形
						{ alignment: go.Spot.TopRight, alignmentFocus: go.Spot.TopRight,
						width: 12, height: 12, fill: "blue" },
						new go.Binding("fill", "status", nodeStatusConverter)
				),
				$(go.Panel, "Auto",
					$(go.Shape,  			//显示problem故障的框
							{ fill: null, stroke: null },
							new go.Binding("background", "problem", nodeProblemConverter)
					),
					$(go.Picture, 
						{
						name: "Picture", 	//设备图片
						desiredSize: new go.Size(100, 110),
			            margin: new go.Margin(6, 8, 6, 10),
						portId : "", // the default port: if no spot on link data, use closest side
						fromLinkable : true,
						toLinkable : true,
						cursor : "pointer",
						//fill : "white", // default color
						//strokeWidth : 2
						}, 
						new go.Binding("source", "key", findHeadShot)
					)
				),
				$(go.TextBlock, {		//equipmentId隐藏框
					stroke :"#eeeeee"
					}, 
					new go.Binding("text","hiddenId").makeTwoWay()
				),
				$(go.TextBlock, {  		//equipmentName设备名
					font : "bold 11pt Helvetica, Arial, sans-serif",
					margin : 8,
					maxSize : new go.Size(160, NaN),
					wrap : go.TextBlock.WrapFit,
					editable : true,
					stroke :"#454545"
					}, 
					new go.Binding("text").makeTwoWay()
				)
				//new go.Binding("figure"), new go.Binding("fill") 
			),//end "Vertical" Panel
		
		
			// four small named ports, one on each side: 设置可被链接的上下左右四个点
			makePort("T", go.Spot.Top, false, true), 
			makePort("L", go.Spot.Left,true, true), 
			makePort("R", go.Spot.Right, true, true),
			makePort("B", go.Spot.Bottom, true, false),
			{ // handle mouse enter/leave events to show/hide the ports 使得鼠标离开或者进入图形时，显示四个圆点
					mouseEnter : function(e, node) {
					showSmallPorts(node, true);
					},
					mouseLeave : function(e, node) {
					showSmallPorts(node, false);
					}
			}
		);//end "Spot" Node
		function showSmallPorts(node, show) 
		{
			node.ports.each(function(port)
			{
				if (port.portId !== "") { // don't change the default port, which is the big shape 不改变port默认的大shape
					port.fill = show ? "rgba(0,0,0,.3)" : null;
				}
			});
		}
	
		//连接线装饰模板
		var linkSelectionAdornmentTemplate = $(go.Adornment, "Link", $(
				go.Shape,	
				// isPanelMain declares that this Shape shares the Link.geometry 画好之后确定之前的线条
				{
					isPanelMain : true,
					fill : null,
					stroke : "deepskyblue",
					strokeWidth : 0
				}) // use selection object's strokeWidth
		);
			
		//连接线模板
		myDiagram.linkTemplate = 
		$(go.Link, // the whole link panel
			{
				selectable : true,
				selectionAdornmentTemplate : linkSelectionAdornmentTemplate
			}, {
				relinkableFrom : true,
				relinkableTo : true,
				reshapable : true
			}, {
				routing : go.Link.AvoidsNodes,
				curve : go.Link.JumpOver,
				corner : 5,
				toShortLength : 4
			},
			new go.Binding("points").makeTwoWay(), 
			
			$(go.Shape, // the link path shape 连接线形状
			{
				isPanelMain : true,
				fill : "#696969",
				stroke :"#696969",
				strokeWidth : 2
			}),
			
		/* 	$(go.Shape, // the arrowhead 箭头头
			{
				toArrow : "Standard",
				fill : "#696969",
				stroke :"#696969",
			}), */ 
			
		    $(go.Shape, { isPanelMain: true, stroke: "black", strokeWidth: 5 }),
		    $(go.Shape, { isPanelMain: true, stroke: "gray", strokeWidth: 3 }),
		    $(go.Shape, { isPanelMain: true, stroke: "white", strokeWidth: 1, name: "PIPE", strokeDashArray: [10, 10] }),
		    $(go.Shape, { toArrow: "Triangle", fill: "black", stroke: null }),
			
			$(go.Panel, "Auto", new go.Binding("visible", "isSelected")
					.ofObject(), $(go.Shape, "RoundedRectangle", // the link shape
				{			//连接线上的块块
					fill : "#F8F8F8",
					stroke : null
				}), 
				$(go.TextBlock, {//连接线上的text文字
					textAlign : "center",
					font : "10pt helvetica, arial, sans-serif",
					stroke : "#919191",
					margin : 2,
					minSize : new go.Size(10, NaN),
					editable : true
					},
					new go.Binding("text").makeTwoWay()
				)
			)
		);
		var PaletteModelArray = new Array();
		//从表格table中获取列值，初始化模型值数组Palette的text和key
		function getModelArray(){
			var mytable = document.getElementById('table');
			for(var i=0; i<mytable.rows[0].cells.length; i++){
				PaletteModelArray[i] = new Object();
				PaletteModelArray[i].text = mytable.rows[0].cells[i].innerHTML;
				PaletteModelArray[i].hiddenId = mytable.rows[1].cells[i].innerHTML;
				PaletteModelArray[i].key = PaletteModelArray[i].hiddenId+PaletteModelArray[i].text;
				//alert(productEquipments[i].id);
			}
		}
		getModelArray();
		
		//myPalette节点模板
		myDiagramtemp = $(go.Diagram);
		myDiagramtemp.nodeTemplate= 
		$(go.Node, "Spot", 
			{locationSpot : go.Spot.Center}, 
			new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify), 
			{//设置其可选择
				selectable : true,
				selectionAdornmentTemplate : nodeSelectionAdornmentTemplate
			}, {//设置其可改变大小
				resizable : true,
				resizeObjectName : "Picture",
				resizeAdornmentTemplate : nodeResizeAdornmentTemplate
			}, {//设置其可旋转
				rotatable : true,
				rotateAdornmentTemplate : nodeRotateAdornmentTemplate
			},
			new go.Binding("angle").makeTwoWay(),
			// the main object is a Panel that surrounds a TextBlock with a Shape ~图形：Panel包围着TextBlock
			$(go.Panel, "Vertical", 
				{
					name : "PANEL"
				}, 
				new go.Binding("desiredSize", "size", go.Size.parse).makeTwoWay(go.Size.stringify), 
				$(go.Picture, 
					{
					name: "Picture", 	//设备图片
					desiredSize: new go.Size(100, 110),
		            margin: new go.Margin(6, 8, 6, 10),
					portId : "", // the default port: if no spot on link data, use closest side
					fromLinkable : true,
					toLinkable : true,
					cursor : "pointer",
					//fill : "white", // default color
					//strokeWidth : 2
					}, 
					new go.Binding("source", "key", findHeadShot)
				),
				$(go.TextBlock, {		//equipmentId隐藏框
					stroke :"#eeeeee"
					}, 
					new go.Binding("text","hiddenId").makeTwoWay()
				),
				//new go.Binding("figure"), new go.Binding("fill") 
				$(go.TextBlock, {  		//equipmentName设备名
					font : "bold 11pt Helvetica, Arial, sans-serif",
					margin : 8,
					maxSize : new go.Size(160, NaN),
					wrap : go.TextBlock.WrapFit,
					editable : true,
					stroke :"#454545"
					}, 
					new go.Binding("text").makeTwoWay()
				)
			),//end "Vertical" Panel
		
			// four small named ports, one on each side: 设置可被链接的上下左右四个点
			makePort("T", go.Spot.Top, false, true), 
			makePort("L", go.Spot.Left,true, true), 
			makePort("R", go.Spot.Right, true, true),
			makePort("B", go.Spot.Bottom, true, false),
			{ // handle mouse enter/leave events to show/hide the ports 使得鼠标离开或者进入图形时，显示四个圆点
					mouseEnter : function(e, node) {
					showSmallPorts(node, true);
					},
					mouseLeave : function(e, node) {
					showSmallPorts(node, false);
					}
			}
		);//end "Spot" Node
		//var strModelArray=JSON.stringify(modelArray);
		//alert(JSON.stringify(modelArray));
		//初始化左边的模板框  initialize the Palette that is on the left side of the page 
		myPalette = $(go.Palette, "myPaletteDiv", // must name or refer to the DIV HTML element
		{
			maxSelectionCount : 1,
			nodeTemplateMap : myDiagramtemp.nodeTemplateMap, // share the templates used by myDiagram
			linkTemplate : // simplify the link template, just in this Palette
				//设置连接线属性,在左侧模板框中,简化连接线
				$(go.Link, 
					{ // because the GridLayout.alignment is Location and the nodes have locationSpot == Spot.Center,
						// to line up the Link in the same manner we have to pretend the Link has the same location spot
						locationSpot : go.Spot.Center,
						selectionAdornmentTemplate : 
						$(go.Adornment, "Link", {
							locationSpot : go.Spot.Center
						}, 
						$(go.Shape, {
							isPanelMain : true,
							fill : null,
							stroke : "deepskyblue",
							strokeWidth : 0
						}), 
						$(go.Shape, // the arrowhead
						{
							toArrow : "Standard",
							stroke : null
						}))
					}, 
					{
						routing : go.Link.AvoidsNodes,
						curve : go.Link.JumpOver,
						corner : 5,
						toShortLength : 4
					}, 
					new go.Binding("points"), 
					$(go.Shape, // the link path shape
					{
						isPanelMain : true,
						strokeWidth : 2
					}), 
					$(go.Shape, // the arrowhead
					{
						toArrow : "Standard",
						stroke : null
					})
				),
			model : new go.GraphLinksModel( // specify the contents of the Palette
					PaletteModelArray		//特别地！！！此处为给palette传值
				   , [
					// the Palette also has a disconnected Link, which the user can drag-and-drop
					{
						points : new go.List(go.Point).addAll([ new go.Point(0, 0),
								new go.Point(30, 0), new go.Point(30, 40),
								new go.Point(60, 40) ])
					} ])
		});
			load(); //在init()函数内部调用load()函数，加载此页面时将该条生产线的模型加载至右侧模型框中
			looplink(); //在init()函数内部调用looplink()函数，加载此页面时连接线有流动效果
			loop();  // start the simulation
	})//end init();

	function TopRotatingTool() {
		go.RotatingTool.call(this);
	}
	go.Diagram.inherit(TopRotatingTool, go.RotatingTool);

	/** @override */
	TopRotatingTool.prototype.updateAdornments = function(part) {
		go.RotatingTool.prototype.updateAdornments.call(this, part);
		var adornment = part.findAdornment("Rotating");
		if (adornment !== null) {
			adornment.location = part.rotateObject
					.getDocumentPoint(new go.Spot(0.5, 0, 0, -30)); // above middle top
		}
	};

	/** @override */
	TopRotatingTool.prototype.rotate = function(newangle) {
		go.RotatingTool.prototype.rotate.call(this, newangle + 90);
	};
	// end of TopRotatingTool class

	// simulate some real-time problem monitoring, once every two seconds:
    function randomProblems() {
        var model = myDiagram.model;
        // update all nodes
        var arr = model.nodeDataArray;
        for (var i = 0; i < arr.length; i++) {
          data = arr[i];
          data.problem = (Math.random() < 0.8) ? "" : "Power loss due to ...";
          data.status = Math.random() * 3;
          data.operation = Math.random() * 3;
          model.updateTargetBindings(data);
        }
        // and update all links
        arr = model.linkDataArray;
        for (i = 0; i < arr.length; i++) {
          data = arr[i];
          data.problem = (Math.random() < 0.7) ? "" : "No Power";
          model.updateTargetBindings(data);
        }
    }
    function loop() {
      setTimeout(function() { randomProblems(); loop(); }, 2000);
    }
    function nodeProblemConverter(msg) {
        if (msg) return "red";
        return null;
      }

    function nodeOperationConverter(s) {
      if (s >= 2) return "TriangleDown";
      if (s >= 1) return "Rectangle";
      return "Circle";
    }

    function nodeStatusConverter(s) {
      if (s >= 2) return "red";
      if (s >= 1) return "yellow";
      return "green";
    }
	
	//使连接线的线条可以流动
	function looplink() {
		    var diagram = myDiagram;
		    setTimeout(function() {
		      var oldskips = diagram.skipsUndoManager;
		      diagram.skipsUndoManager = true;
		      diagram.links.each(function(link) {
		          var shape = link.findObject("PIPE");
		          var off = shape.strokeDashOffset - 2;
		          shape.strokeDashOffset = (off <= 0) ? 20 : off;
		        });
		      diagram.skipsUndoManager = oldskips;
		      looplink();
		    }, 100);
	}	
	// Show the diagram's model in JSON format that the user may edit
	//将画板中图形对应json加载至mySavedModel框（内存）中
	var productLinePosition = new Object();
	var linkDataArray = new Array();
	var userEquipmentArray = new Array();
	function save() {
		looplink(); //安装模型之后，线条可以流动
		saveDiagramProperties(); // do this first, before writing to JSON 将myDiagram画板中的参数信息存储至至myDiagram.model中
		//根据建模界面输出的json字符串->json对象数组->获得其中的各种属性值
		
		var strData = myDiagram.model.toJson();
		var myModel = $.parseJSON(strData);
		var userProductLineId = '${userProductLineId}';
		if(myModel.nodeDataArray.length!=0){
			//alert("myModel.nodeDataArray.length!=0:  "+myModel.nodeDataArray.length!=0);
			//productlineposition
			productLinePosition.userProductLineId = userProductLineId;
			productLinePosition.position1 = myModel.modelData.position.split(" ")[0];
			productLinePosition.position2 = myModel.modelData.position.split(" ")[1];
			console.log(productLinePosition.position1);
			console.log(productLinePosition.position2);
			
			//userequipments
			for (var i=0; i<myModel.nodeDataArray.length; i++)
			{
				userEquipmentArray[i]  = new Object();
				userEquipmentArray[i].userProductLineId = userProductLineId;
				userEquipmentArray[i].equipmentId = myModel.nodeDataArray[i].hiddenId;
				userEquipmentArray[i].text = myModel.nodeDataArray[i].text;
				userEquipmentArray[i].keyy = myModel.nodeDataArray[i].key;
				userEquipmentArray[i].loc1 = myModel.nodeDataArray[i].loc.split(" ")[0];
				userEquipmentArray[i].loc2 = myModel.nodeDataArray[i].loc.split(" ")[1];
				
				console.log(userEquipmentArray[i].text);
				console.log(userEquipmentArray[i].keyy);
				console.log(userEquipmentArray[i].loc1);
				console.log(userEquipmentArray[i].loc2);
			}
			//linkinfo
			for (var i=0; i<myModel.linkDataArray.length; i++)
			{
				linkDataArray[i]  = new Object();
				linkDataArray[i].userProductLineId = userProductLineId;
				linkDataArray[i].fromKey = myModel.linkDataArray[i].from;
				linkDataArray[i].toKey = myModel.linkDataArray[i].to;
				linkDataArray[i].fromPort = myModel.linkDataArray[i].fromPort;
				linkDataArray[i].toPort = myModel.linkDataArray[i].toPort;
				linkDataArray[i].point1 = myModel.linkDataArray[i].points[0];
				linkDataArray[i].point2 = myModel.linkDataArray[i].points[1];
				linkDataArray[i].point3 = myModel.linkDataArray[i].points[2];
				linkDataArray[i].point4 = myModel.linkDataArray[i].points[3];
				linkDataArray[i].point5 = myModel.linkDataArray[i].points[4];
				linkDataArray[i].point6 = myModel.linkDataArray[i].points[5];
				linkDataArray[i].point7 = myModel.linkDataArray[i].points[6];
				linkDataArray[i].point8 = myModel.linkDataArray[i].points[7];
				linkDataArray[i].point9 = myModel.linkDataArray[i].points[8];
				linkDataArray[i].point10 = myModel.linkDataArray[i].points[9];
				linkDataArray[i].point11= myModel.linkDataArray[i].points[10];
				linkDataArray[i].point12 = myModel.linkDataArray[i].points[11];
				
				console.log(linkDataArray[i].fromKey);
				console.log(linkDataArray[i].toKey);
				console.log(linkDataArray[i].fromPort);
				console.log(linkDataArray[i].toPort);
				console.log(linkDataArray[i].point1);
				console.log(linkDataArray[i].point2);
			}
			
			//传递productlineposition
			$.ajax({
				type : "post",
				url : "../install/productlineposition/post",
				data : JSON.stringify(productLinePosition),              
				datatype : "json",
				contentType:"application/json",
				async : false, //默认为true 异步   
				error : function() {
					alert('提交失败!');
				},
				success : function(data) {
					if (data == null || data == "") {
						$.gritter.add({
							title : '成功',
							text : '提交已经成功!',
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-sucess'
						});
					} else
						$.gritter.add({
							title : '失败',
							text : data,
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-error'
						});
				}
			});
			
			//传递linkinfo
			$.ajax({
				type : "post",
				url : "../install/linkinfo/post",
				data : JSON.stringify(linkDataArray),              
				datatype : "json",
				contentType:"application/json",
				async : false, //默认为true 异步   
				error : function() {
					alert('提交失败!');
				},
				success : function(data) {
					if (data == null || data == "") {
						$.gritter.add({
							title : '成功',
							text : '提交已经成功!',
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-sucess'
						});
					} else
						$.gritter.add({
							title : '失败',
							text : data,
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-error'
						});
				}
			});
		
			//传递userequipment
			$.ajax({
				type : "post",
				url : "../install/userequipment/post",
				data : JSON.stringify(userEquipmentArray),              
				datatype : "json",
				contentType:"application/json",
				async : false, //默认为true 异步   
				success : function(data) {
					if (data == null || data == ""){
						$.gritter.add({
							title : '成功',
							text : '生产线安装成功!',
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-sucess'
						});
					} else
						$.gritter.add({
							title : '失败',
							text : data,
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-error'
						});
				},
				error : function() {
					alert('提交失败!');
				}
				});
		}
		else{
			//alert("myModel.nodeDataArray.length!=0: "+ myModel.nodeDataArray.length!=0);
			//删除该条生产线
			$.ajax({
				type : "post",
				url : "../install/deleteThisUserProductLine/"+userProductLineId,
				async : false, //默认为true 异步   
				success : function(data) {
					if (data == null || data == ""){
						$.gritter.add({
							title : '成功',
							text : '用户生产线卸载成功!',
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-sucess'
						});
					} else
						$.gritter.add({
							title : '失败',
							text : data,
							//sticky: false,  
							time : 2000,
							//speed:500,  
							position : 'bottom-right',
							class_name : 'gritter-error'
						});
				},
				error : function() {
					alert('提交失败!');
				}
			});
		}
		document.getElementById("mySavedModel").value = strData;  //将myDiagram.model中信息存储至mySavedModel框中
		myDiagram.isModified = false; //修改标识设置为false
	}
	//将myDiagram.model中信息存储至mySavedModel框中
 	function saveDiagramProperties() {
		myDiagram.model.modelData.position = go.Point
				.stringify(myDiagram.position);
		//alert("myDiagram.position:   "+myDiagram.position);
		//alert("myDiagram.model.modelData.position:   "+myDiagram.model.modelData.position);
		//alert("myDiagram.model.modelData:   "+myDiagram.model.modelData.toString());
		//alert("myDiagram.model:   "+myDiagram.model.toJson());
	} 
	
	//按照mySavedModel框中json来显示画板
	function load() {
		var myModelDataStr = document.getElementById('myModelDataStr').value;
		//alert(myModelDataStr);
		//myDiagram.model = go.Model.fromJson(myModelDataStr);
		myDiagram.model = go.Model.fromJson(myModelDataStr); //将mySavedModel中信息赋值给至myDiagram.model
		loadDiagramProperties(); // do this after the Model.modelData has been brought into memory 将myDiagram.model中的信息展示在画板上
	}

	//将myDiagram.model中的信息展示在画板上
	function loadDiagramProperties(e) {
		// set Diagram.initialPosition, not Diagram.position, to handle initialization side-effects
		var pos = myDiagram.model.modelData.position;
		if (pos)
			myDiagram.initialPosition = go.Point.parse(pos);
		//alert(11111111111);
	}
	
	//查询、新增和保存按钮绑定事件
	jQuery(function($) {
		//返回
		$("#btnReturn").on('click', function(e) {
			var url = "../install/viUserProductLinePage";
			var title = "生产线维护";
			showPageContent(url, title);
			e.preventDefault();
		});
	});
</script>

</head>
<body>
	<div id="sample">
		<div class="row" >
			<div style="text-align: right; float: right;">
				<input type="hidden" name="myModelDataStr" id="myModelDataStr" value='${myModelDataStr}'/>
				<button id="SaveButton" class="btn btn-purple btn-sm" onclick="save()">确认安装</button>
				<button  class="btn btn-primary btn-sm" onclick="load()">加载模型</button>
				<button id="btnReturn" class="btn btn-green btn-sm">返回</button>
			</div>
			<textarea  hidden="hidden" id="mySavedModel" style="width: 100%; height: 300px">{ "class": "go.GraphLinksModel",
				  "linkFromPortIdProperty": "fromPort",
				  "linkToPortIdProperty": "toPort",
				  "nodeDataArray": [
				 ],
				  "linkDataArray": [
				 ]}
    		</textarea>
		</div>
		<div class="hr hr-18 dotted hr-double"></div>
		<div
			style="width: 100%; display: flex; justify-content: space-between">
			<div id="myPaletteDiv"
				style="width: 170px; margin-right: 2px; background-color: whitesmoke; border: solid 1px black"></div>
			<div id="myDiagramDiv" 
			style="background-color: whitesmoke;flex-grow: 1; height: 620px; border: solid 1px black"></div>
		</div>
		<!-- 隐藏域，存储从model获取的生产线设备名称 -->
		<div hidden="hidden" class="col-sm-4">
			<table id="table">
				<tr>
					<c:forEach var="item" items="${ViProductLineEquipments}">
						<td>${item.equipmentName}</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="item" items="${ViProductLineEquipments}">
						<td>${item.equipmentId}</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
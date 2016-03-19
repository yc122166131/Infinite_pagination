<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{padding:0;margin:0;}
	a{text-decoration:none;color:#fff;}
	ul li{list-style:none;}
	body{font-size:12px;font-family:"楷体";}
	#box{height:300px;width:960px;background:#333;margin:100px auto;overflow:auto;}
	#box li{color:#fff;margin-top:1px;background:#666;padding:8px;}
</style>

</head>
<body>
	<a href="#" style="color:blue;">加载数据</a>
	PageNo:<input type="text" id="pageNo"/>
	PageSize:<input type="text" id="pageSize"/>
	<br/>
	<div id="box">
		<ul id="ullistbox"></ul>
	</div>
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script>
		var Itemcount = 0 ;
		$(function(){
			loadData(0,10);
			var pno = 1;
			var psize = 10;
			var flag = true;
			$("#box").scroll(function(){
				var height = $("#box").height();//对象的高度！(如若改成$(window).height()则就是可视区的高度！)
				var scrolltop = $("#box").scrollTop(); //scrollTop的高度(离开最顶端的距离)
				var scrollHeight = $("#box").get(0).scrollHeight; //文档总高度(包括overflow隐藏的！)[也是滚动条的总高度，那个不动的嵌在后面的那个总高度]
				
				
				if(scrolltop  + height + 1 >= scrollHeight && flag){
					if(pno*psize>Itemcount){
						flag = false;
						return;
					}
					loadData(pno*psize,psize,function(){
						pno++;
						//document.getElementById("box").scrollTop =  scrolltop/2;
					});
					//alert(pno);
					//console.log("============="+pno);
				}
			});
			
		
			
			
			
			function loadData(pageNo,pageSize,callback){
				//var formParam = "pageNo="+$("#pageNo").val()+"&pageSize="+$("#pageSize").val();
				$.ajax({
					type:"post",
					url:"pageContent",
					data:{"pageNo":pageNo,"pageSize":pageSize},  
					dataType:"json",
					success:function(data){
						var datas = data.datas;
						Itemcount = data.count;
						var html = "";
						for(var i = 0 ; i < datas.length; i++){
							html += "<li>"+datas[i].id+"===="+datas[i].content+"</li>";
							//alert(data[i].id+"---"+data[i].content);
						}
						$("#ullistbox").append(html);
						if(callback)callback(Itemcount); //作用就是当数据加载完并append到区域上之后 再执行pno++
					}
				});
				
			}
		});
	</script>
</body>
</html>
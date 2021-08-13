<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Spring MVC java</h1>
	<img style="width:150px; height:150px;" src="image/icon-spring-framework.svg">
	
	<hr>
	
	<h2>Chap03</h2>
	<h4><a href="test1">test1</a></h4>
	<h4><a href="test2">test2</a></h4>
	<h4><a href="sub1/test3">sub1/test3</a></h4>
	<h4><a href="sub1/test4">sub1/test4</a></h4>
	<h4><a href="sub2/test5">sub2/test5</a></h4>
	<h4><a href="sub2/test6">sub2/test6</a></h4>
	
	
	<hr>
	<h2>Chap04</h2>
	<h4><a href="chap04/test1">test1 get</a></h4> <!-- GET 방식 -->
	<form action="chap04/test1" method="post">
		<button type="submit">test1 POST</button>
	</form>
	<br>
	<h4><a href="chap04/test2">test2 get</a></h4>
	<form action="chap04/test2" method="post">
		<button type="submit">test2 POST</button>
	</form>
	
	<br>
	
	<h4><a href="chap04/test3">test3 get</a></h4>
	<form action="chap04/test3" method="post">
		<button type="submit">test3 POST</button>
	</form>
	
	<br>
	
	<h4><a href="chap04/test4">test4 get</a></h4>
	<form action="chap04/test4" method="post">
		<button type="submit">test4 POST</button>
	</form>
	
	<br>
	
	<h4><a href="chap04/test5">test5 get</a></h4>
	<form action="chap04/test5" method="post">
		<button type="submit">test5 POST</button>
	</form>
	
	<br>
	
	<h4><a href="chap04/test6">test6 get</a></h4>
	<form action="chap04/test6" method="post">
		<button type="submit">test6 POST</button>
	</form>
	
	<br>
	
	<h4><a href="chap04/test7">test7 get</a></h4>
	<form action="chap04/test7" method="post">
		<button type="submit">test7 POST</button>
	</form>
	
	<br>
		
	<h4><a href="chap04/test8">test8 get</a></h4>
	<form action="chap04/test8" method="post">
		<button type="submit">test8 POST</button>
	</form>
	
	<hr>
	
	<h2>Chap05</h2>
	<h4><a href="chap05/test1?data1=100&data2=200&data3=300&data3=400">test1</a></h4>
	<h4><a href="chap05/test2?data1=10&data2=20&data3=30&data3=40">test2</a></h4>
	<h4><a href="chap05/test3/1000/2000/3000/4000">test3</a></h4>
	<h4><a href="chap05/test4?data1=100&data2=200&data3=300">test4</a></h4>
	<h4><a href="chap05/test5?data1=10&data2=20&data3=30">test5</a></h4>
	<h4><a href="chap05/test6">test6</a></h4>
	
	<hr>
	
	<h2>Chap06</h2>
	<h4><a href="chap06/test1?data1=100&data2=200&data3=300&data3=400">test1 get</a></h4>
	<h4><a href="chap06/test2?data1=10&data2=20&data3=30&data3=40">test2 get</a></h4>

	<hr>
	
	<h2>Chap07</h2>
	<h4><a href="chap07/test1?data1=100&data2=200">test1</a></h4>
	<h4><a href="chap07/test2">test2</a></h4>
	<h4><a href="chap07/test3">test3</a></h4>
	<h4><a href="chap07/test4">test4</a></h4>
	
</body>
</html>
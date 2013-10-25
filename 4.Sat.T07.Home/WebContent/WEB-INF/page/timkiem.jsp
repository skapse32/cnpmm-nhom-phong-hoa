<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tìm Kiếm</title>
<style>
body {
	background: #EAF2D3;
}

table {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 600px;
	border-collapse: collapse;
}

td,th {
	font-size: 1em;
	border: 1px solid #98bf21;
	padding: 3px 7px 2px 7px;
}

th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #A7C942;
	color: #ffffff;
}

tr:nth-child(2n+1) td {
	color: #000000;
	background-color: #EAF2D3;
}
"
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(
	function() {
		$.ajax({
			url : "TimKiemSinhVien",
			type : 'GET',
			success : function(result) {
				var $table = $('<table id="customers">').appendTo($('#result'));
				$.each(result,function(index,item) { // Iterate over the JSON array.
					var alink = "<a href='SuaSinhVien?id=";
					$('<tr>').appendTo($table).append($('<td>').html(alink+ item.mssv+ "''>"+ item.mssv+ "</a>"))
					.append($('<td>').text(item.hoTen))
					.append($('<td>').text(item.gioiTinh == true ? 'Nam': 'Nữ'))
					.append($('<td>').text(item.noiSinh))
					.append($('<td>').text(item.ngaySinh));
				});
			}
		});
	$("#search").click(function() {
		$("#customers").remove();
		var da = $("#formContent").serialize();
		$.ajax({
			url : "TimKiemSinhVien",
			type : 'POST',
			data : da,
			success : function(result) {
				var $table = $('<table id="customers">').appendTo($('#result'));
				$.each(result,function(index,item) { // Iterate over the JSON array.
					var alink = "<a href='SuaSinhVien?id=";
					$('<tr>').appendTo($table).append($('<td>').html(alink+ item.mssv+ "''>"+ item.mssv+ "</a>"))
					.append($('<td>').text(item.hoTen))
					.append($('<td>').text(item.gioiTinh == true ? 'Nam': 'Nữ'))
					.append($('<td>').text(item.noiSinh))
					.append($('<td>').text(item.ngaySinh));
				});
			}
		});
	});
	$("#Reset").click(function() {
		$("#customers").remove();
	});
	$("#luu").click(function() {
		window.location.href = "SaveResultSearch";
	});
});
</script>

</head>
<body>
	<form id="formContent" action="SaveResultSearch" method="post" enctype="multipart/form-data">
		<table width="750" border="1" align="center">
			<tr>
				<td colspan="3">Điều kiện tìm</td>
			</tr>
			<tr>
				<td width="133">MSSV :</td>
				<td width="321" colspan="2"><label for="mssv"></label> <input
					type="text" name="mssv" id="mssv"></td>
			</tr>
			<tr>
				<td>Họ và tên</td>
				<td colspan="2"><label for="hoten"></label> <input name="hoten"
					type="text" id="hoten" size="40"></td>
			</tr>
			<tr>
				<td colspan="3">Ngày sinh từ : <label for="startDate"></label>
					<input name="startDate" type="date" id="startDate" size="10"
					value="1900-01-01"> Đến : <label for="endDate"></label> <input
					type="date" name="endDate" id="endDate" value="2013-01-01"></td>
			</tr>
			<tr>
				<td colspan="3"><input type="button" name="search" id="search"
					value="Tìm"> <input type="reset" name="Reset" id="Reset"
					value="Reset"> <input type="button" name="luu" id="luu"
					value="lưu"> 
					<input type="file" name="file" /> <input type="submit" id="load" name="load" value="load"/></td>
			</tr>
			<tr>
				<td colspan="3"><div id="result">
					
				</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*, com.wind.modal.*, com.wind.connection.*"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>Danh Sách Sinh Viên</title>
</head>
<%
	SinhVienInterface dao = new SinhvienDAO();
	List<SinhVien> sv = dao.DanhSachSV();
%>
<body>
	<center>
		<h2 style="color: #007A29">Danh Sách Sinh Viên</h2>
		<form name="form1" method="POST" action="XoaSinhVien">
			<div id="main">
				<table id="customers">
					<div id="fix">
						<a href="ThemSinhVien">Thêm Sinh Viên</a>|<a href="timkiem">Tim
							Kiếm</a>|<a href="DanhSachSV">Save file Excel</a><input type="submit"
							name="xoa" value="Delete" />
					</div>
					<tr>
						<th>MSSV</th>
						<th>Họ Và Tên</th>
						<th>Sửa</th>
						<th>Xóa</th>
					</tr>
					<%
						for (SinhVien s : sv) {
					%>
					<tr>
						<td><a href="ThongTinSinhVien?id=<%=s.getMssv()%>"><%=s.getMssv()%></a></td>
						<td><%=s.getHoTen()%></td>
						<td><a href="SuaSinhVien?id=<%=s.getMssv()%>">Edit</a></td>
						<td><input type="checkbox" name="cbx_xoa"
							value="<%=s.getMssv()%>" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
	</center>
	</form>
</body>
</html>
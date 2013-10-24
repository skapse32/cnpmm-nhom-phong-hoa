package com.wind.quanlysinhvien;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;


/**
 * Servlet implementation class ThemSinhVienAction
 */
@WebServlet("/ThemSinhVienAction")
public class ThemSinhVienAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mssv = request.getParameter("mssv");
		if(mssv!=null && mssv.length()==0){
			response.sendRedirect("ThemSinhVien?errors=MSSV INVALID");
			return;
		}
		String hovaten = request.getParameter("hovaten");
		Boolean phai = Boolean.valueOf(request.getParameter("phai"));
		String noisinh	= request.getParameter("noisinh");
		Date ngaysinh =  Date.valueOf(request.getParameter("ngaysinh"));
		String quatrinhhoctap = request.getParameter("quatrinhhoctap");
		/*System.out.println(Boolean.valueOf(phai) +""+ ngaysinh);*/
		String[] sothich = request.getParameterValues("check");
		
		SinhVien SV = new SinhVien();
		SV.setMssv(mssv);
		SV.setHoTen(hovaten);
		SV.setGioiTinh(phai);
		SV.setNoiSinh(noisinh);
		System.out.println(hovaten);
		SV.setNgaySinh(ngaysinh);
		SV.setMonTheThaoYeuThich(sothich);
		SV.setQuaTrinhHocTap(quatrinhhoctap);
		SinhVienInterface svi = new SinhvienDAO();
		if (svi.AddSV(SV))
		{
			response.sendRedirect("DanhSachSinhVien");
		}
		else{
			response.sendRedirect("ThemSinhVien");
		}
	}

}

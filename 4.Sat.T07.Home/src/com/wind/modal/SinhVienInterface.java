package com.wind.modal;

import java.util.List;

public interface SinhVienInterface {
	public List<SinhVien> DanhSachSV();
	public SinhVien GetSinhVien(String id);
	public boolean AddSV(SinhVien sv);
	public boolean DeleSV(String id);
	public boolean UpdateSV(SinhVien sv);
}

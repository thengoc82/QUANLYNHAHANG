package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private boolean gioiTinh;
	private String DienThoai;
	private LocalDateTime ngayThem;
	
	private int diemTichLuy; //Biến lưu giá trị thuộc tính dẫn xuất
	
	
	public KhachHang() {
	}
	
	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public KhachHang(String maKhachHang, String hoTen, boolean gioiTinh, String dienThoai,LocalDateTime ngayThem) {
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.DienThoai = dienThoai;
		this.ngayThem = ngayThem;
		this.diemTichLuy = 0;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public LocalDateTime getNgayThem() {
		return ngayThem;
	}

	public void setNgayThem(LocalDateTime ngayThem) {
		this.ngayThem = ngayThem;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	//Tích lũy điểm
	public void tichDiem(HoaDon hoaDon) {
		diemTichLuy++;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", DienThoai="
				+ DienThoai + ", ngayThem=" + ngayThem + ", diemTichLuy=" + diemTichLuy + "]";
	}

	
}

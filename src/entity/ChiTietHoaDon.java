package entity;

import java.text.NumberFormat;
import java.util.Locale;

public class ChiTietHoaDon {
	private SanPham sanPham;
	private int soLuong;
	private KichThuoc kt;
	
	
	public ChiTietHoaDon(SanPham sanPham, int soLuong, KichThuoc kt) {
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.kt = kt;
	}
	
	public ChiTietHoaDon(SanPham sanPham, int soLuong) {
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public KichThuoc getKt() {
		return kt;
	}

	public void setKt(KichThuoc kt) {
		this.kt = kt;
	}
	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaBan() {
		Double gia = sanPham.getKichThuocGia().get(kt);
        if(sanPham.getKhuyenMai() != null && sanPham.getKhuyenMai().isTrangThai())
        	gia = gia - (gia * sanPham.getKhuyenMai().getPhanTramGiamGia()/100);
        return gia;
	}
	public double getGia() {
		return sanPham.getKichThuocGia().get(kt) * soLuong;
	}
	public double getTienGiam() {
		if(sanPham.getKhuyenMai() != null && sanPham.getKhuyenMai().isTrangThai())
        	return getGia() * sanPham.getKhuyenMai().getPhanTramGiamGia()/100;
			return 0;
	}
	
	//get thanh tien
	public double thanhTien(KichThuoc kt) {
		return sanPham.getKichThuocGia().get(kt) * soLuong;
	}
	//Tính VAT
	public double tinhVAT() {
		return 1.2 * soLuong *1000;
	}
	@Override
	public String toString() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return String.format(" |%-14s|%-25s|%-6s|%-14s|%-14d|%-14s|\n", sanPham.getMaSanPham(), sanPham.getTenSanPham(), kt.name()==null?"N":kt.name(), fm.format(getGiaBan()), soLuong, fm.format(thanhTien(kt)));
	}
}

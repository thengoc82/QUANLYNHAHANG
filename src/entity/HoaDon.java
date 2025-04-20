package entity;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime ngayTao;
	private boolean trangThaiThanhToan;
	private String ghiChu;
	private PhuongThucThanhToan phuongThucThanhToan;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private Ban ban;
	private ArrayList<ChiTietHoaDon> dsCTHD;

	public HoaDon(String maHoaDon,NhanVien nhanVien, LocalDateTime ngayTao, boolean trangThaiThanhToan, String ghiChu, Ban ban, PhuongThucThanhToan phuongThucThanhToan, KhachHang khachHang) {
		this.maHoaDon = maHoaDon;
		this.ngayTao = ngayTao;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.ghiChu = ghiChu;
		this.ban = ban;
		this.nhanVien= nhanVien;
		this.khachHang = khachHang;
		this.dsCTHD = new ArrayList<ChiTietHoaDon>();
		this.phuongThucThanhToan = phuongThucThanhToan;
	}
	//Setter và getter
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public PhuongThucThanhToan getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}
	public void setPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public Ban getBan() {
		return ban;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ArrayList<ChiTietHoaDon> getDsCTHD() {
		return dsCTHD;
	}

	public void setDsCTHD(ArrayList<ChiTietHoaDon> dsCTHD) {
		this.dsCTHD = dsCTHD;
	}
	
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public void setBan(Ban ban) {
		this.ban = ban;
	}
	//Thêm CTHD
	public boolean addChiTietHoaDon(ChiTietHoaDon cthd) {
        return dsCTHD.add(cthd);
    }
	//Xoá CTHD
    public boolean removeChiTietHoaDon(ChiTietHoaDon cthd) {
        return dsCTHD.remove(cthd); 
    }
	public double tinhTongThue() {
		return dsCTHD.stream()
				.mapToDouble(cthd -> cthd.tinhVAT())
				.sum();
	}
	public double tinhTongTien() {
		return dsCTHD.stream()
				.mapToDouble(cthd -> cthd.thanhTien(cthd.getKt()))
				.sum();
	}
	public int getSoLuongSanPham() {
		return dsCTHD.size();
	}
	public double tongTienGiam() {
		double tg = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tg += chiTietHoaDon.getTienGiam();
		}
		return tg;
	}
	public double tongTienSanPham() { //Lúc này chưa có giảm giá
		double tt = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tt += chiTietHoaDon.getGia();
		}
		return tt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String dsSP = "";
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			dsSP+= chiTietHoaDon.toString();
		}
		return """
                       \t\t\t\tTHE MINTY COFFEE\r
                       \r
                       \t\t\t590 CMT8, P.11, Q.3, TPHCM\r
                       \t\t\tS\u0110T: 01212692802\r
                       \r
                       ---------------------------------------------
                       M\u00e3 h\u00f3a \u0111\u01a1n: """ + maHoaDon + "\r\n"
				+ "Tên hoá đơn: Hóa đơn bán hàng\r\n"
				+ "Ngày lập: " + ngayTao.format(fm) + "\r\n"
				+ "                                               DANH SÁCH SẢN PHẨM                                                    \n"
				+ "______________________________________________________________________________________\n"
				+ String.format(" |%-14s|%-28s|%-6s|%-14s|%-14s|%-14s|\n", "Mã HD", "Tên SP", "KT", "Giá", "SL", "Tổng tiền")
				+ dsSP
				+ (getBan()==null?"":"Bàn:" + getBan().getMaBan()) + "\n"
				+ "Phương thức thanh toán: " + phuongThucThanhToan + "\r\n"
				+ "Trạng thái thanh toán: " + (trangThaiThanhToan==true?"Đã thanh toán":"Chưa thanh toán") + "\r\n"
				+ "Tổng tiền: " + fmt.format(tinhTongTien()) + "\r\n"
				+ "\t\t\t\tCẢM ƠN QUÝ KHÁCH!!!" + "\r\n"
				+ "\t\t\t\tWiFi: xincamonquykhach"
				;
				
	}
	
	
}

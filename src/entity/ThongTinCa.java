package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ThongTinCa {
	private NhanVien nhanVien;
	private CaLam calam;
	
	private LocalDateTime ngayLam;
	private Double doanhThuCa; 
	public ThongTinCa() {
	}
	public ThongTinCa(NhanVien nhanVien, CaLam calam, LocalDateTime ngayLam) {
		this.nhanVien = nhanVien;
		this.calam = calam;
		this.ngayLam = ngayLam;
		this.doanhThuCa = 0.0;
//		updateDoanhThuCa();
	}
	//cập nhật doanh thu ca và trả về để xử lý
//	private double updateDoanhThuCa() {
//		HoaDonDAO hoaDonDAO = new HoaDonDAO();
//		ArrayList<HoaDon> hoaDons = hoaDonDAO.selectDay(ngayLam.toLocalDate());
//		for (HoaDon hoaDon : hoaDons) {
//			doanhThuCa += hoaDon.tinhTongTien();
//		}
//		return doanhThuCa;
//	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	
	public void setDoanhThuCa(Double doanhThuCa) {
		this.doanhThuCa = doanhThuCa;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public CaLam getCalam() {
		return calam;
	}
	public void setCalam(CaLam calam) {
		this.calam = calam;
	}
	public LocalDateTime getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(LocalDateTime ngayLam) {
		this.ngayLam = ngayLam;
	}
	public Double getDoanhThuCa() {
		return doanhThuCa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(calam, ngayLam);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongTinCa other = (ThongTinCa) obj;
		return Objects.equals(calam, other.calam) && Objects.equals(ngayLam, other.ngayLam);
	}
	@Override
	public String toString() {
		return "ThongTinCa [nhanVien=" + nhanVien + ", calam=" + calam + ", ngayLam=" + ngayLam + ", doanhThuCa="
				+ doanhThuCa + "]";
	}
	
}

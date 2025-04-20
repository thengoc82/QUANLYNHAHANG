package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private boolean trangThai; //True: đang bán, False: Ngưng bán
	private final LoaiSanPham loaiSanPham;
	private String hinhAnh;
	private String moTa;
	private HashMap<KichThuoc, Double> kichThuocGia;
	
	private KhuyenMai khuyenMai;

	public SanPham(String maSanPham, String tenSanPham, boolean trangThai, LoaiSanPham loaiSanPham, String hinhAnh,
            String moTa, KhuyenMai khuyenMai) {
		 this.maSanPham = maSanPham;
		 this.tenSanPham = tenSanPham;
		 this.trangThai = trangThai;
		 this.loaiSanPham = loaiSanPham;
		 this.hinhAnh = hinhAnh;
		 this.moTa = moTa;
		 this.khuyenMai = khuyenMai;
		 this.kichThuocGia = new HashMap<>();
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}


	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	
	public void themKichThuocGia(KichThuoc kichThuoc, double gia) {
		kichThuocGia.put(kichThuoc, gia);
	}
	
	public double getGiaByKichThuoc(KichThuoc kichThuoc) {
		return kichThuocGia.getOrDefault(kichThuoc, 0.0);
	}
	
	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public Map<KichThuoc, Double> getKichThuocGia() {
		return kichThuocGia;
	}

	public void setKichThuocGia(HashMap<KichThuoc, Double> kichThuocGia) {
		this.kichThuocGia = kichThuocGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", trangThai=" + trangThai
				+ ", loaiSanPham=" + loaiSanPham + ", hinhAnh=" + hinhAnh + ", moTa=" + moTa + ", khuyenMai="
				+ khuyenMai + "]";
	}
	
}

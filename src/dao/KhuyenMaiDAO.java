package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.KhuyenMai;
import entity.LoaiSanPham;
import entity.TaiKhoan;

public class KhuyenMaiDAO implements InterfaceDAO<KhuyenMai> {
	public static KhuyenMaiDAO getInstance() {
		return new KhuyenMaiDAO();
	}
	@Override
	public int insert(KhuyenMai t) {
		int kq = 0;
		try {
			Connection conn = connectDB.getConnectDB();
			String sql = "INSERT INTO KhuyenMai (MaKhuyenMai, TenKhuyenMai, NgayBatDau, NgayKetThuc, PhanTramGiamGia, TrangThai, MoTa) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			pst.setString(1, t.getMaKhuyenMai());
			pst.setString(2, t.getTenKhuyenMai());
			pst.setString(3, t.getNgayBatDau().format(fm));
			pst.setString(4, t.getNgayKetThuc().format(fm));
			pst.setDouble(5, t.getPhanTramGiamGia());
			pst.setString(6, t.isTrangThai()? "true":"fasle");
			pst.setString(7, t.getMoTa());
			kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
		} catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
	}

	@Override
	public int update(KhuyenMai t) {
		int kq = 0;
		try {
			Connection con = connectDB.getConnectDB();
			String sql = "UPDATE KhuyenMai SET TenKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ?, PhanTramGiamGia = ?, TrangThai = ?, MoTa = ? WHERE MaKhuyenMai=?";
			PreparedStatement pst = con.prepareStatement(sql);
			DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			pst.setString(7, t.getMaKhuyenMai());
			pst.setString(1, t.getTenKhuyenMai());
			pst.setString(2, t.getNgayBatDau().format(fm));
			pst.setString(3, t.getNgayKetThuc().format(fm));
			pst.setDouble(4, t.getPhanTramGiamGia());
			pst.setString(5, t.isTrangThai()?"true":"false");
			pst.setString(6, t.getMoTa());
			//Thực thi
			kq = pst.executeUpdate();
			//Đóng kết nối
			connectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public ArrayList<KhuyenMai> selectAll() {
	    ArrayList<KhuyenMai> kq = new ArrayList<KhuyenMai>();
	    try {
	        Connection con = connectDB.getConnectDB();
	        String sql = "SELECT * FROM KhuyenMai";
	        PreparedStatement pst = con.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String maKM = rs.getString("MaKhuyenMai");
	            String tenKM = rs.getString("TenKhuyenMai");
	            String moTa = rs.getString("MoTa");
	            LocalDateTime ngayBD = rs.getTimestamp("NgayBatDau").toLocalDateTime();
	            LocalDateTime ngayKT = rs.getTimestamp("NgayKetThuc").toLocalDateTime();
	            Double phanTram = rs.getDouble("PhanTramGiamGia");
	            Boolean trangThai = rs.getString("TrangThai").equals("1") ? true : false;
	            KhuyenMai tk = new KhuyenMai(maKM, tenKM, ngayBD, ngayKT, phanTram, trangThai, moTa);
	            kq.add(tk);
	        }
	        connectDB.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return kq;
	}


	@Override
	public KhuyenMai  selectById(String id) {
	    KhuyenMai km = null;
		try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM KhuyenMai WHERE MaKhuyenMai = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, id);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            String maKM = rs.getString("MaKhuyenMai");
	            String tenKM = rs.getString("TenKhuyenMai");
	            String moTa = rs.getString("MoTa");
	            LocalDateTime ngayBD = rs.getTimestamp("NgayBatDau").toLocalDateTime();
	            LocalDateTime ngayKT = rs.getTimestamp("NgayKetThuc").toLocalDateTime();
	            Double phanTram = rs.getDouble("PhanTramGiamGia");
	            Boolean trangThai = rs.getString("TrangThai").equals("1") ? true : false;
	            km = new KhuyenMai(maKM, tenKM, ngayBD, ngayKT, phanTram, trangThai, moTa);
	        }
	        connectDB.closeConnection(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return km;
	}
	public String getMaxMaKhuyenMai() {
        String maxMaKhuyenMai = ""; // Mã KM lớn nhất

        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT MAX(MaKhuyenMai) AS maxMa FROM KhuyenMai";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                maxMaKhuyenMai = rs.getString("maxMa"); // Lấy mã KM lớn nhất từ kết quả
            }

            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxMaKhuyenMai;
    }
	public String getNextMaKhuyenMai() {
        String maxMaKhuyenMai = getMaxMaKhuyenMai(); // Lấy mã KM lớn nhất từ cơ sở dữ liệu

        // Kiểm tra xem maxMaKhuyenMai có rỗng hay không
        if (maxMaKhuyenMai.isEmpty()) {
            // Nếu chuỗi rỗng, trả về mã KM đầu tiên (NV001)
            return "KM001";
        } else {
            // Tách phần số từ mã nhân viên lớn nhất
            int nextNumber = Integer.parseInt(maxMaKhuyenMai.substring(2)) + 1; // Tăng số lên 1
            String nextMaKhuyenMai = "KM" + String.format("%03d", nextNumber); // Format lại mã nhân viên mới
            return nextMaKhuyenMai;
        }
    }
	public ArrayList<KhuyenMai> chonKhuyenMai(boolean a) {
	    ArrayList<KhuyenMai> danhSachKhuyenMai = new ArrayList<>();
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM KhuyenMai WHERE TrangThai = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setBoolean(1, a); 
	        ResultSet rs = pst.executeQuery();
	        System.out.println(a);
	        while (rs.next()) {
	           
	        	 String maKM = rs.getString("MaKhuyenMai");
		            String tenKM = rs.getString("TenKhuyenMai");
		            String moTa = rs.getString("MoTa");
		            LocalDateTime ngayBD = rs.getTimestamp("NgayBatDau").toLocalDateTime();
		            LocalDateTime ngayKT = rs.getTimestamp("NgayKetThuc").toLocalDateTime();
		            Double phanTram = rs.getDouble("PhanTramGiamGia");
		            Boolean trangThai = rs.getBoolean("TrangThai");
		           
	            KhuyenMai KhuyenMai = new KhuyenMai(maKM, tenKM, ngayBD, ngayKT, phanTram, trangThai, moTa);
	            danhSachKhuyenMai.add(KhuyenMai);
	        }
	        System.out.println(danhSachKhuyenMai);
	        connectDB.closeConnection(conn);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachKhuyenMai;
	}


}

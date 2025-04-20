package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.CaLam;

public class CaLamDAO implements InterfaceDAO<CaLam> {

	public static CaLamDAO getInstance() {
		return new CaLamDAO();
	}
	
	@Override
	public int insert(CaLam calam) {
        int kq = 0;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "INSERT INTO CaLam (MaCa, TenCa, ThoiGianBatDau, ThoiGianKetThuc, LuongTheoGio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, calam.getMaCa());
            pst.setString(2, calam.getTenCa());
            pst.setObject(3, calam.getThoiGianBatDau());
            pst.setObject(4, calam.getThoiGianketThuc());
            pst.setDouble(5, calam.getLuongTheoGio());
            
            kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
	
    // Cập nhật thông tin của một đối tượng CaLam trong cơ sở dữ liệu
	@Override
	public int update(CaLam calam) {
        int kq = 0;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "UPDATE CaLam SET TenCa = ?, ThoiGianBatDau = ?, ThoiGianKetThuc = ?, LuongTheoGio = ? WHERE MaCa = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, calam.getTenCa());
            pst.setObject(2, calam.getThoiGianBatDau());
            pst.setObject(3, calam.getThoiGianketThuc());
            pst.setDouble(4, calam.getLuongTheoGio());
            pst.setString(5, calam.getMaCa());
            
            kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    

	@Override
	public ArrayList<CaLam> selectAll() {
        ArrayList<CaLam> danhSachCaLam = new ArrayList<>();
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT * FROM CaLam";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maCa = rs.getString("MaCa");
                String tenCa = rs.getString("TenCa");
                Time batDau = rs.getTime("ThoiGianBatDau");
                Time ketThuc = rs.getTime("ThoiGianKetThuc");
                double luongTheoGio = rs.getDouble("LuongTheoGio");

                LocalTime thoiGianBatDau = batDau.toLocalTime();
                LocalTime thoiGianKetThuc = ketThuc.toLocalTime();

                CaLam calam = new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, luongTheoGio);
                danhSachCaLam.add(calam);
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachCaLam;
    }
	@Override
	public CaLam selectById(String id) {
	    CaLam calam = null;
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM CaLam WHERE MaCa = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, id);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            String maCa = rs.getString("MaCa");
	            String tenCa = rs.getString("TenCa");
	            LocalTime thoiGianBatDau = rs.getTime("ThoiGianBatDau").toLocalTime();
	            LocalTime thoiGianKetThuc = rs.getTime("ThoiGianKetThuc").toLocalTime();
	            double luongTheoGio = rs.getDouble("LuongTheoGio");
	            
	            calam = new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, luongTheoGio);
	        }
	        
	        connectDB.closeConnection(conn);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return calam;
	}

//	public static CaLam selectByTime(LocalTime thoiGianHt) {
//        CaLam calam = null;
//        try {
//            Connection conn = connectDB.getConnectDB();
//            String sql = "SELECT * FROM CaLam";
//            PreparedStatement pst = conn.prepareStatement(sql);
//
//            pst.setTime(1, Time.valueOf(thoiGianHt));
//            pst.setTime(2, Time.valueOf(thoiGianHt));
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                String maCa = rs.getString("MaCa");
//                String tenCa = rs.getString("TenCa");
//                LocalTime thoiGianBatDau = rs.getTime("ThoiGianBatDau").toLocalTime();
//                LocalTime thoiGianKetThuc = rs.getTime("ThoiGianKetThuc").toLocalTime();
//                double luongTheoGio = rs.getDouble("LuongTheoGio");
//
//                calam = new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, luongTheoGio);
//            }
//
//            rs.close();
//            pst.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return calam;
//    }
    // Xóa một đối tượng CaLam khỏi cơ sở dữ liệu
    public int delete(String maCa) {
        int kq = 0;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "DELETE FROM CaLam WHERE MaCa = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, maCa);
            
            kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

}

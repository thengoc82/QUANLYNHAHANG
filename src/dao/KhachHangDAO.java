package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectDB;
import entity.KhachHang;

public class KhachHangDAO implements InterfaceDAO<KhachHang> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHang khachHang) {
        int rowsAffected = 0;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "INSERT INTO KhachHang (MaKhachHang, HoTen, GioiTinh, DienThoai, NgayThem) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getMaKhachHang());
            preparedStatement.setString(2, khachHang.getHoTen());
            preparedStatement.setBoolean(3, khachHang.isGioiTinh());
            preparedStatement.setString(4, khachHang.getDienThoai());
            // Chuyển LocalDateTime thành java.sql.Timestamp để lưu vào cơ sở dữ liệu
            preparedStatement.setTimestamp(5, Timestamp.valueOf(khachHang.getNgayThem()));

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(KhachHang khachHang) {
        int rowsAffected = 0;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "UPDATE KhachHang SET HoTen = ?, GioiTinh = ?, DienThoai = ?, NgayThem = ? WHERE MaKhachHang = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getHoTen());
            preparedStatement.setBoolean(2, khachHang.isGioiTinh());
            preparedStatement.setString(3, khachHang.getDienThoai());
            // Chuyển LocalDateTime thành java.sql.Timestamp để lưu vào cơ sở dữ liệu
            preparedStatement.setTimestamp(4, Timestamp.valueOf(khachHang.getNgayThem()));
            preparedStatement.setString(5, khachHang.getMaKhachHang());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }


    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM KhachHang";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString("MaKhachHang");
                String hoTen = resultSet.getString("HoTen");
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                LocalDateTime ngayThem = resultSet.getTimestamp("NgayThem").toLocalDateTime();

                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
                danhSachKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    @Override
    public KhachHang selectById(String maKhachHang) {
        KhachHang khachHang = null;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maKhachHang);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hoTen = resultSet.getString("HoTen");
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                LocalDateTime ngayThem = resultSet.getTimestamp("NgayThem").toLocalDateTime();

                khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }
    public KhachHang selectBySdt(String sdt) {
        KhachHang khachHang = null;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM KhachHang WHERE DienThoai = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sdt);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	String maKH = resultSet.getString("MaKhachHang");
                String hoTen = resultSet.getString("HoTen");
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                LocalDateTime ngayThem = resultSet.getTimestamp("NgayThem").toLocalDateTime();

                khachHang = new KhachHang(maKH, hoTen, gioiTinh, dienThoai, ngayThem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }
    public ArrayList<KhachHang> selectByDay(LocalDate d1, LocalDate d2) {
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM KhachHang WHERE NgayThem BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            LocalDateTime fromDateTime = d1.atStartOfDay();
            LocalDateTime toDateTime = d2.atTime(23, 59, 59);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(fromDateTime));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(toDateTime));
            System.out.println("fromDateTime: " + fromDateTime);
            System.out.println("toDateTime: " + toDateTime);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString("MaKhachHang");
                String hoTen = resultSet.getString("HoTen");
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                LocalDateTime ngayThem = resultSet.getTimestamp("NgayThem").toLocalDateTime();
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
                danhSachKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
    
    public ArrayList<KhachHang> selectCustomersAddedThisMonth() {
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
            YearMonth yearMonth = YearMonth.from(LocalDate.now());
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
            
            String sql = "SELECT * FROM KhachHang WHERE NgayThem BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            LocalDateTime fromDateTime = firstDayOfMonth.atStartOfDay();
            LocalDateTime toDateTime = lastDayOfMonth.atTime(23, 59, 59);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(fromDateTime));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(toDateTime));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString("MaKhachHang");
                String hoTen = resultSet.getString("HoTen");
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                LocalDateTime ngayThem = resultSet.getTimestamp("NgayThem").toLocalDateTime();

                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
                danhSachKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
    public String getMaxMaKhachHang() {
        String maxMaKhachHang = ""; // Mã nhân viên lớn nhất

        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT MAX(MaKhachHang) AS maxMa FROM KhachHang";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                maxMaKhachHang = rs.getString("maxMa"); // Lấy mã nhân viên lớn nhất từ kết quả
            }

            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxMaKhachHang;
    }
	public String getNextMaKhachHang() {
        String maxMaKhachHang = getMaxMaKhachHang(); 
        if (maxMaKhachHang.isEmpty()) {
            return "KH001";
        } else {
            int nextNumber = Integer.parseInt(maxMaKhachHang.substring(2)) + 1; 
            String nextMaKhachHang = "KH" + String.format("%03d", nextNumber);
            return nextMaKhachHang;
        }
    }
}

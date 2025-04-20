package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.connectDB;
import controler.main;
import entity.LoaiSanPham;

public class LoaiSanPhamDAO implements InterfaceDAO<LoaiSanPham> {
	public static LoaiSanPhamDAO getInstance() {
		return new LoaiSanPhamDAO();
	}
    @Override
    public int insert(LoaiSanPham loaiSanPham) {
        int rowsInserted = 0;
        PreparedStatement preparedStatement = null;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "INSERT INTO LoaiSanPham (MaLoai, TenLoai, MoTa,CoPhanLoai) VALUES (?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, loaiSanPham.getMaLoai());
            preparedStatement.setString(2, loaiSanPham.getTenloai());
            preparedStatement.setString(3, loaiSanPham.getMoTa());
            preparedStatement.setBoolean(4, loaiSanPham.isCoPhanLoai());
            rowsInserted += preparedStatement.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsInserted;
    }

    @Override
    public int update(LoaiSanPham loaiSanPham) {
        int rowsUpdated = 0;
        PreparedStatement preparedStatement = null;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "UPDATE LoaiSanPham SET TenLoai=?, MoTa=?, CoPhanLoai= ? WHERE MaLoai=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, loaiSanPham.getTenloai());
            preparedStatement.setString(2, loaiSanPham.getMoTa());
            preparedStatement.setBoolean(3, loaiSanPham.isCoPhanLoai());
            preparedStatement.setString(4, loaiSanPham.getMaLoai());
            rowsUpdated += preparedStatement.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsUpdated;
    }

    @Override
    public ArrayList<LoaiSanPham> selectAll() {
        ArrayList<LoaiSanPham> danhSachLoaiSanPham = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT * FROM LoaiSanPham";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maLoai = resultSet.getString("MaLoai");
                String tenLoai = resultSet.getString("TenLoai");
                String moTa = resultSet.getString("MoTa");
                Boolean coPhanLoai = resultSet.getBoolean("CoPhanLoai");
                LoaiSanPham loaiSanPham = new LoaiSanPham(maLoai, tenLoai, moTa, coPhanLoai);
                danhSachLoaiSanPham.add(loaiSanPham);
            }
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return danhSachLoaiSanPham;
    }

    @Override
    public LoaiSanPham selectById(String id) {
        LoaiSanPham loaiSanPham = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT * FROM LoaiSanPham WHERE MaLoai=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String maLoai = resultSet.getString("MaLoai");
                String tenLoai = resultSet.getString("TenLoai");
                String moTa = resultSet.getString("MoTa");
                Boolean coPhanLoai = resultSet.getBoolean("CoPhanLoai");
                loaiSanPham = new LoaiSanPham(maLoai, tenLoai, moTa, coPhanLoai);
            }
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return loaiSanPham;
    }
    public LoaiSanPham selectByName(String ten) {
        LoaiSanPham loaiSanPham = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;

        try {
            conn = connectDB.getConnectDB();
            String sql = "SELECT * FROM LoaiSanPham WHERE TenLoai=?";
            preparedStatement = conn.prepareStatement(sql); 
            preparedStatement.setString(1, ten); 
            resultSet = preparedStatement.executeQuery(); 

            if (resultSet.next()) { 
                String maLoai = resultSet.getString("MaLoai");
                String tenLoai = resultSet.getString("TenLoai");
                String moTa = resultSet.getString("MoTa");
                Boolean coPhanLoai = resultSet.getBoolean("CoPhanLoai");
                loaiSanPham = new LoaiSanPham(maLoai, tenLoai, moTa, coPhanLoai); // Tạo đối tượng LoaiSanPham
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close(); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return loaiSanPham; 
    }
    public String taoMaLoaiSP()  {
        String newMaLoai = null;
        int currentMax = 0;
        String query = "SELECT MAX(CAST(SUBSTRING(maLoai, 2, 1) AS INT)) AS maxMaLoai " +
                       "FROM LoaiSanPham ";

        try (PreparedStatement preparedStatement = connectDB.getConnectDB().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();  
            if (resultSet.next() && resultSet.getObject("maxMaLoai") != null) {
                currentMax = resultSet.getInt("maxMaLoai");
            }
            int nextMaHoaDon = currentMax + 1;
            newMaLoai = "L" + nextMaHoaDon;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return newMaLoai;
    }
//    public static void main(String[] args) throws SQLException {
//		System.out.println(LoaiSanPhamDAO.getInstance().taoMaSP());
//	}
}

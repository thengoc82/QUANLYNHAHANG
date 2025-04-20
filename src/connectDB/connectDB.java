package connectDB;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

import controler.main;

public class connectDB {
	//tạo kết nối
    public static Connection getConnectDB() {
    	Connection con = null;
        Properties p = new Properties();
        try {
//            FileInputStream fin = new FileInputStream(new File("libs/conf.properties"));
//            p.load(fin);
//            String host = p.getProperty("ServerID");
//            String port = p.getProperty("Port");
//            String dbname = p.getProperty("Database");
//            String user = p.getProperty("Username");
//            String pw = p.getProperty("Password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + "localhost" + ":" + "1433" + ";databaseName=" + "QLNH";
            con = DriverManager.getConnection(url, "sa", "YourStrong@Passw0rd");
//            System.out.println("Đã kết nối!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 100: Không tìm thấy lớp");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101: Không thể kết nối đến máy chủ");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 102: Cấu hình bị trống");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi 103: " + e.getMessage());
        }
        return con;
    }
    public static void closeConnection(Connection con) {
		try {
			if(con!= null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void getInfoDB(Connection con) {
		try {
			if(con != null) {
				DatabaseMetaData dmd = con.getMetaData();
				System.out.println(dmd.getDatabaseProductName());
				System.out.println(dmd.getDatabaseProductVersion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//    public static void main(String[] args) {
//    	getConnectDB();
//	}
}

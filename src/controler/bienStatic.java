package controler;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import dao.NhanVienDAO;
import entity.Ban;
import entity.NhanVien;
import gui.frmTrangChu;

public class bienStatic {
	public static String maTKLogin;
	public static NhanVien nhanVienLogin;
	public static ArrayList<Ban> bans = new ArrayList<Ban>();
	public static double tienTaiQuay = 1000000;
	public static boolean role;
	public static String name;
	public static String caHienTai;
	public static LocalDateTime thoiGianBatDau;
	public static LocalDateTime thoiGianKeThuc;
	public static double doanhThuTemp;
	public static frmTrangChu frmTrangChu;
	
	public static JWindow createLoadingWindow(String tieuDe) {
        JWindow window = new JWindow();
        JPanel content = new JPanel(new BorderLayout());
        
        // Set a border for the content pane
        content.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

        JLabel label = new JLabel(tieuDe, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLUE);
        label.setBackground(Color.LIGHT_GRAY);
        label.setOpaque(true);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);

        content.add(label, BorderLayout.CENTER);
        content.add(progressBar, BorderLayout.SOUTH);

        window.getContentPane().add(content);
        window.setSize(300, 150);
        window.setLocationRelativeTo(null);  

        return window;
    }
}

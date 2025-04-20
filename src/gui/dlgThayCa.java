package gui;

import controler.bienStatic;
import dao.CaLamDAO;
import dao.ThongTinCaDAO;
import entity.CaLam;
import entity.ThongTinCa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class dlgThayCa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgThayCa dialog = new dlgThayCa(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgThayCa(JFrame frmTrangChu) {
		super(frmTrangChu, true);
		setBounds(100, 100, 767, 635);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ThongTinCaDAO thongTinCaDAO = new ThongTinCaDAO();
		ArrayList<ThongTinCa> thongTinCas = thongTinCaDAO.selectAll();
		for (ThongTinCa thongTinCa : thongTinCas) {
			bienStatic.tienTaiQuay += thongTinCa.getDoanhThuCa();
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTieuDe = new JLabel("XÁC NHẬN GIAO CA");
			lblTieuDe.setForeground(new Color(0, 122, 122));
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 30));
			lblTieuDe.setBounds(176, 10, 303, 55);
			contentPanel.add(lblTieuDe);
		}
		{
			JLabel lblMaCa = new JLabel("Mã ca:");
			lblMaCa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblMaCa.setBounds(100, 91, 89, 25);
			contentPanel.add(lblMaCa);
		}
		{
			JLabel lbltxtMaCa = new JLabel();
			lbltxtMaCa.setText(bienStatic.caHienTai);
			lbltxtMaCa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtMaCa.setBounds(414, 91, 89, 25);
			contentPanel.add(lbltxtMaCa);
		}
		{
			JLabel lbltxtMaNV = new JLabel();
			lbltxtMaNV.setText(bienStatic.nhanVienLogin.getMaNhanVien());
			lbltxtMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtMaNV.setBounds(414, 139, 169, 25);
			contentPanel.add(lbltxtMaNV);
		}
		{
			JLabel lblMaNV = new JLabel("Mã nhân viên:");
			lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblMaNV.setBounds(100, 139, 149, 25);
			contentPanel.add(lblMaNV);
		}
		{
			JLabel lblThoiGianBatDau = new JLabel("Thời gian bắt đầu:");
			lblThoiGianBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblThoiGianBatDau.setBounds(100, 190, 194, 25);
			contentPanel.add(lblThoiGianBatDau);
		}
		{
			JLabel lbltxtThoiGianKT = new JLabel();
			lbltxtThoiGianKT.setText(LocalDateTime.now().format(formatter));
			lbltxtThoiGianKT.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtThoiGianKT.setBounds(414, 241, 309, 25);
			contentPanel.add(lbltxtThoiGianKT);
		}
		{
			JLabel lblThoiGianKT = new JLabel("Thời gian kết thúc:");
			lblThoiGianKT.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblThoiGianKT.setBounds(100, 241, 162, 25);
			contentPanel.add(lblThoiGianKT);
		}
		{
			JLabel lbltxtTienBanDau = new JLabel();
			lbltxtTienBanDau.setText(fm.format(bienStatic.tienTaiQuay));
			lbltxtTienBanDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtTienBanDau.setBounds(414, 294, 203, 25);
			contentPanel.add(lbltxtTienBanDau);
		}
		{
			JLabel lblTienBanDau = new JLabel("Tiền ban đầu:");
			lblTienBanDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTienBanDau.setBounds(100, 294, 149, 25);
			contentPanel.add(lblTienBanDau);
		}
		{
			JLabel lbltxtTienDoanhThu = new JLabel();
			lbltxtTienDoanhThu.setText(fm.format(bienStatic.doanhThuTemp));
			lbltxtTienDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtTienDoanhThu.setBounds(414, 349, 218, 25);
			contentPanel.add(lbltxtTienDoanhThu);
		}
		{
			JLabel lblTienDoanhThu = new JLabel("Tiền doanh thu:");
			lblTienDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTienDoanhThu.setBounds(100, 349, 149, 25);
			contentPanel.add(lblTienDoanhThu);
		}
		{
			JLabel lblTongDoanhthu = new JLabel("Tổng doanh thu:");
			lblTongDoanhthu.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			lblTongDoanhthu.setBounds(100, 401, 194, 37);
			contentPanel.add(lblTongDoanhthu);
		}
		{
			JLabel lbltxtTongDoanhThu = new JLabel();
			lbltxtTongDoanhThu.setText(fm.format(bienStatic.doanhThuTemp + bienStatic.tienTaiQuay));
			lbltxtTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			lbltxtTongDoanhThu.setBounds(414, 401, 203, 37);
			contentPanel.add(lbltxtTongDoanhThu);
		}
		{
			JLabel lbltxtThoiGianBD = new JLabel();
			lbltxtThoiGianBD.setText(bienStatic.thoiGianBatDau.format(formatter));
			lbltxtThoiGianBD.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtThoiGianBD.setBounds(414, 190, 249, 25);
			contentPanel.add(lbltxtThoiGianBD);
		}
		{
			JButton btnXacNhan = new JButton("Xác nhận");
			btnXacNhan.addActionListener((ActionEvent e) -> {
                            CaLam caLam = CaLamDAO.getInstance().selectById(bienStatic.caHienTai);
                            ThongTinCa thongTinCa = new ThongTinCa(bienStatic.nhanVienLogin, caLam, LocalDateTime.now());
                            thongTinCa.setDoanhThuCa(bienStatic.doanhThuTemp);
                            ThongTinCaDAO.getInstance().insert(thongTinCa);
                            bienStatic.tienTaiQuay += bienStatic.doanhThuTemp;
                            bienStatic.doanhThuTemp = 0;
                            dlgThayCa.this.dispose();
                            frmTrangChu.setVisible(false);
                            frmDangNhap frmDangNhapGUI = new frmDangNhap();
                            frmDangNhapGUI.setLocationRelativeTo(null);;
                            frmDangNhapGUI.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Thay ca thành công");
                        });
			btnXacNhan.setBackground(new Color(255, 255, 255));
			btnXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btnXacNhan.setBounds(390, 551, 114, 37);
			contentPanel.add(btnXacNhan);
		}
		{
			JButton btnHuy = new JButton("Hủy");
			btnHuy.addActionListener((ActionEvent e) -> {
                            dlgThayCa.this.dispose();
                        });
			btnHuy.setBackground(new Color(255, 255, 255));
			btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btnHuy.setBounds(518, 551, 114, 37);
			contentPanel.add(btnHuy);
		}
		{
			JButton btnThayDoi = new JButton("Thay đổi tiền ban đầu");
			btnThayDoi.addActionListener((ActionEvent e) -> {
                            if(bienStatic.role) {
                                dlgThayDoiTienBanDau dlgThayDoi = new dlgThayDoiTienBanDau(dlgThayCa.this);
                                dlgThayDoi.setLocationRelativeTo(dlgThayCa.this);
                                dlgThayDoi.setVisible(true);
                            }else {
                                JOptionPane.showMessageDialog(dlgThayCa.this, "Bạn không có quyền truy cập chức năng này!");
                            }
                        });
			btnThayDoi.setBackground(new Color(255, 255, 255));
			btnThayDoi.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btnThayDoi.setBounds(144, 551, 214, 37);
			contentPanel.add(btnThayDoi);
		}
		{
			JLabel lblGhiChu = new JLabel("Ghi chú:");
			lblGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblGhiChu.setBounds(100, 459, 183, 25);
			contentPanel.add(lblGhiChu);
		}
		
		JTextArea txtAreaGhiChu = new JTextArea();
		txtAreaGhiChu.setBounds(293, 464, 339, 70);
		contentPanel.add(txtAreaGhiChu);
	}
	
}

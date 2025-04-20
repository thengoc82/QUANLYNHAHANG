//package gui;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import java.awt.Font;
//import java.awt.Frame;
//
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//
//import java.awt.Color;
//import com.toedter.calendar.JDateChooser;
//
//import controler.bienStatic;
//import dao.NhanVienDAO;
//import dao.TaiKhoanDAO;
//import entity.NhanVien;
//import entity.TaiKhoan;
//
//import javax.swing.ImageIcon;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class dlgQuenMK extends JDialog {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTextField txtTenDN;
//	private JTextField txtHoTen;
//	private JTextField txtSdt;
//	
//	dlgXacNhanCa dlgXacNhanCaGUI;
//	frmDangNhap frmDangNhapGUI;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			dlgQuenMK dialog = new dlgQuenMK(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public dlgQuenMK(JFrame frmDangNhapGUI) {
//		super(frmDangNhapGUI, true);
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 800, 500);
//		setLocationRelativeTo(null);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JPanel pnlKhung1 = new JPanel();
//		pnlKhung1.setBackground(new Color(0,0,0));
//		pnlKhung1.setBounds(0, 0, 776, 473);
//		contentPane.add(pnlKhung1);
//		pnlKhung1.setLayout(null);
//		
//		JLabel lblTieuDe = new JLabel("Xác nhận thông tin đăng nhập");
//		lblTieuDe.setForeground(Color.GRAY);
//		lblTieuDe.setBackground(new Color(64, 128, 128));
//		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 30));
//		lblTieuDe.setBounds(310, 10, 483, 47);
//		pnlKhung1.add(lblTieuDe);
//		
//		JLabel lblTenDN = new JLabel("Tên đăng nhập:");
//		lblTenDN.setForeground(Color.WHITE);
//		lblTenDN.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		lblTenDN.setBounds(294, 100, 142, 30);
//		pnlKhung1.add(lblTenDN);
//		
//		txtTenDN = new JTextField();
//		txtTenDN.setBounds(464, 100, 281, 30);
//		pnlKhung1.add(txtTenDN);
//		txtTenDN.setColumns(10);
//		
//		JLabel lblHoTen = new JLabel("Họ tên:");
//		lblHoTen.setForeground(Color.WHITE);
//		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		lblHoTen.setBounds(293, 170, 142, 30);
//		pnlKhung1.add(lblHoTen);
//		
//		txtHoTen = new JTextField();
//		txtHoTen.setColumns(10);
//		txtHoTen.setBounds(464, 175, 281, 30);
//		pnlKhung1.add(txtHoTen);
//		
//		JLabel lblSdt = new JLabel("Số điện thoại:");
//		lblSdt.setForeground(Color.WHITE);
//		lblSdt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		lblSdt.setBounds(293, 250, 142, 30);
//		pnlKhung1.add(lblSdt);
//		
//		txtSdt = new JTextField();
//		txtSdt.setColumns(10);
//		txtSdt.setBounds(464, 250, 281, 30);
//		pnlKhung1.add(txtSdt);
//		
//		JButton btnDangNhap = new JButton("Đăng nhập");
//		btnDangNhap.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(checkTT()) {
//					dlgQuenMK.this.dispose();
//					JOptionPane.showMessageDialog(null, "Đăng nhập thành công! Bạn vui lòng liên hệ quản lý để đặt lại mật khẩu!");
//					dlgXacNhanCaGUI = new dlgXacNhanCa(frmDangNhapGUI);
//					dlgXacNhanCaGUI.setLocationRelativeTo(null);
//					dlgXacNhanCaGUI.setVisible(true);
//				}
//			}
//		});
//		btnDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		btnDangNhap.setBounds(330, 350, 150, 40);
//		pnlKhung1.add(btnDangNhap);
//		
//		JButton btnQuayLai = new JButton("Quay lại");
//		btnQuayLai.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dlgQuenMK.this.dispose();
//				frmDangNhapGUI.setVisible(true);
//			}
//		});
//		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 15));
//		btnQuayLai.setBounds(570, 350, 150, 40);
//		pnlKhung1.add(btnQuayLai);
//		
//		JPanel pnlGiaoDien = new JPanel();
//		pnlGiaoDien.setBounds(0, 0, 273, 473);
//		pnlKhung1.add(pnlGiaoDien);
//		pnlGiaoDien.setLayout(null);
//		pnlGiaoDien.setBackground(new Color(248, 248, 248));
//		
//		JLabel lblTen = new JLabel();
//		lblTen.setText("  Restaurant");
//		lblTen.setForeground(Color.GRAY);
//		lblTen.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
//		lblTen.setBounds(22, 344, 241, 55);
//		pnlGiaoDien.add(lblTen);
//		
//		JLabel lblLogo = new JLabel("");
//		lblLogo.setIcon(new ImageIcon(dlgQuenMK.class.getResource("/icon/ICON_LOGO__229x200.png")));
//		lblLogo.setBounds(22, 89, 229, 200);
//		pnlGiaoDien.add(lblLogo);
//	}
//	//Check thông tin xác nhận
//	public boolean checkTT() {
//		String maTK = txtTenDN.getText().trim();
//		String hoTen = txtHoTen.getText().trim();
//		String sdt = txtSdt.getText().trim();
//		if(maTK.equals("") || hoTen.equals("") || sdt.equals("")) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
//			return false;
//		}
//		NhanVienDAO nhanVienDAO = new NhanVienDAO();
//		NhanVien nv = nhanVienDAO.findNhanVien(maTK, hoTen, sdt);
//		if(nv == null) {
//			JOptionPane.showMessageDialog(this, "Thông tin không tồn tại trên hệ thống!");
//			return false;
//		}else {
//			bienStatic.maTKLogin = nv.getTaiKhoan().getMaTaiKhoan();
//			bienStatic.role = nv.getTaiKhoan().isLoaiTaiKhoan();
//			bienStatic.nhanVienLogin = nv;
//		}
//		return true;
//	}
//}

package gui;


import controler.bienStatic;
import dao.NhanVienDAO;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class dlgQuenMK extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JTextField txtTenDN;
	private final JTextField txtHoTen;
	private final JTextField txtSdt;
	
	dlgXacNhanCa dlgXacNhanCaGUI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgQuenMK dialog = new dlgQuenMK(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public dlgQuenMK(JFrame frmDangNhapGUI) {
		super(frmDangNhapGUI, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setBackground(new Color(0, 0, 0));
		pnlKhung1.setBounds(0, 0, 776, 473);
		contentPane.add(pnlKhung1);
		pnlKhung1.setLayout(null);

		JLabel lblTieuDe = new JLabel("Xác nhận thông tin đăng nhập");
		lblTieuDe.setForeground(Color.GRAY);
		lblTieuDe.setBackground(new Color(64, 128, 128));
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTieuDe.setBounds(310, 10, 483, 47);
		pnlKhung1.add(lblTieuDe);

		JLabel lblTenDN = new JLabel("Tên đăng nhập:");
		lblTenDN.setForeground(Color.WHITE);
		lblTenDN.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTenDN.setBounds(294, 100, 142, 30);
		pnlKhung1.add(lblTenDN);

		txtTenDN = new RoundedTextField(10);
		txtTenDN.setBounds(464, 100, 281, 30);
		pnlKhung1.add(txtTenDN);

		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setForeground(Color.WHITE);
		lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHoTen.setBounds(293, 170, 142, 30);
		pnlKhung1.add(lblHoTen);

		txtHoTen = new RoundedTextField(10);
		txtHoTen.setBounds(464, 175, 281, 30);
		pnlKhung1.add(txtHoTen);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSdt.setBounds(293, 250, 142, 30);
		pnlKhung1.add(lblSdt);

		txtSdt = new RoundedTextField(10);
		txtSdt.setBounds(464, 250, 281, 30);
		pnlKhung1.add(txtSdt);

		JButton btnDangNhap = new RoundedButton("Đăng nhập");
		btnDangNhap.addActionListener((ActionEvent e) -> {
                    if (checkTT()) {
                        dlgQuenMK.this.dispose();
                        JOptionPane.showMessageDialog(null,
                                "Đăng nhập thành công! Bạn vui lòng liên hệ quản lý để đặt lại mật khẩu!");
                        dlgXacNhanCaGUI = new dlgXacNhanCa(frmDangNhapGUI);
                        dlgXacNhanCaGUI.setLocationRelativeTo(null);
                        dlgXacNhanCaGUI.setVisible(true);
                    }
                });
		btnDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDangNhap.setBounds(330, 350, 150, 40);
		pnlKhung1.add(btnDangNhap);

		JButton btnQuayLai = new RoundedButton("Thoát");
		btnQuayLai.addActionListener((ActionEvent e) -> {
                    dlgQuenMK.this.dispose();
                    frmDangNhapGUI.setVisible(true);
                });
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnQuayLai.setBounds(570, 350, 150, 40);
		pnlKhung1.add(btnQuayLai);

		JPanel pnlGiaoDien = new JPanel();
		pnlGiaoDien.setBounds(0, 0, 273, 473);
		pnlKhung1.add(pnlGiaoDien);
		pnlGiaoDien.setLayout(null);
		pnlGiaoDien.setBackground(new Color(248, 248, 248));

		JLabel lblTen = new JLabel();
		lblTen.setText("  Restaurant");
		lblTen.setForeground(Color.GRAY);
		lblTen.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblTen.setBounds(22, 344, 241, 55);
		pnlGiaoDien.add(lblTen);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(dlgQuenMK.class.getResource("/icon/ICON_LOGO__229x200.png")));
		lblLogo.setBounds(22, 89, 229, 200);
		pnlGiaoDien.add(lblLogo);
	}

	// Check thông tin xác nhận
	public boolean checkTT() {
		String maTK = txtTenDN.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSdt.getText().trim();
		if (maTK.equals("") || hoTen.equals("") || sdt.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
			return false;
		}
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		NhanVien nv = nhanVienDAO.findNhanVien(maTK, hoTen, sdt);
		if (nv == null) {
			JOptionPane.showMessageDialog(this, "Thông tin không tồn tại trên hệ thống!");
			return false;
		} else {
			bienStatic.maTKLogin = nv.getTaiKhoan().getMaTaiKhoan();
			bienStatic.role = nv.getTaiKhoan().isLoaiTaiKhoan();
			bienStatic.nhanVienLogin = nv;
		}
		return true;
	}

	// Custom bo tròn 4 góc cho JTextField
	public class RoundedTextField extends JTextField {
		private static final long serialVersionUID = 1L;
		private final int arc = 20;

		public RoundedTextField(int columns) {
			super(columns);
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
			super.paintComponent(g2);
			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Color.GRAY);
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
			g2.dispose();
		}
	}
	
	public class RoundedButton extends JButton {
		private static final long serialVersionUID = 1L;
		private final int arc = 20;

		public RoundedButton(String text) {
			super(text);
			setContentAreaFilled(false);
			setFocusPainted(false);
			setBorderPainted(false);
			setForeground(Color.WHITE);
			setBackground(new Color(70, 130, 180)); // xanh dương nhạt
			setFont(new Font("Segoe UI", Font.PLAIN, 16));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
			super.paintComponent(g2);
			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(new Color(100, 100, 100));
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
			g2.dispose();
		}
	}

}


package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.bienStatic;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgXacNhanQL extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenDangNhap;
	private JPasswordField pwdMatKhau;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dlgXacnhanQL dialog = new dlgXacnhanQL();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dlgXacNhanQL(JDialog dlgThayDoiTien) {
		super(dlgThayDoiTien, true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(186, 90, 90));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTieuDe = new JLabel("Xác thực thông tin");
			lblTieuDe.setForeground(Color.gray);
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 25));
			lblTieuDe.setBounds(145, 10, 225, 34);
			contentPanel.add(lblTieuDe);
		}
		{
			JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
			lblTenDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTenDangNhap.setBounds(10, 73, 150, 34);
			contentPanel.add(lblTenDangNhap);
		}
		{
			txtTenDangNhap = new JTextField();
			txtTenDangNhap.setBounds(161, 73, 282, 32);
			contentPanel.add(txtTenDangNhap);
			txtTenDangNhap.setColumns(10);
		}
		{
			JLabel lblMatKhau = new JLabel("Mật khẩu:");
			lblMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblMatKhau.setBounds(10, 141, 150, 34);
			contentPanel.add(lblMatKhau);
		}
		
		pwdMatKhau = new JPasswordField();
		pwdMatKhau.setBounds(161, 141, 282, 32);
		contentPanel.add(pwdMatKhau);
		
		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check()) {
					dlgXacNhanQL.this.dispose();
//					dlgThayDoiTien.dispose();
					JOptionPane.showMessageDialog(null, "Đã cập nhật tiền ban đầu!");
				}
			}
		});
		btnXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnXacNhan.setBounds(172, 226, 101, 34);
		contentPanel.add(btnXacNhan);
		
		JButton btnQuayLai = new JButton("Hủy");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgXacNhanQL.this.dispose();
			}
			
		});
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnQuayLai.setBounds(306, 226, 101, 34);
		contentPanel.add(btnQuayLai);
	}
	private boolean check() {
		String tdn = txtTenDangNhap.getText().trim();
		String pass = pwdMatKhau.getText().trim();
		if(tdn.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(dlgXacNhanQL.this, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		}
		if(tdn.equals(bienStatic.maTKLogin) && pass.equals(bienStatic.nhanVienLogin.getTaiKhoan().getMatKhau())) {
			JOptionPane.showMessageDialog(dlgXacNhanQL.this, "Đã cập nhật!");
			double tienLayRa = Double.parseDouble(dlgThayDoiTienBanDau.txtTienLayRa.getText());
			dlgThayDoiTienBanDau.txtTienLayRa.setText("");
			dlgThayDoiTienBanDau.txtTienBanDau.setText((bienStatic.tienTaiQuay - tienLayRa) + "");
			bienStatic.tienTaiQuay = bienStatic.tienTaiQuay - tienLayRa;
			return true;
		}else {
			JOptionPane.showMessageDialog(dlgXacNhanQL.this, "Thông tin đang nhập không đúng!");
			return false;
		}

	}

}

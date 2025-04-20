package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controler.bienStatic;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class dlgThayDoiMatKhau extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JPasswordField txtMKcu;
	private final JPasswordField txtMKMoi;
	private final JPasswordField txtMKXN;
	private final JLabel lblMK, lbltxtMKMoi, lbltxtMKXn;

	/**
	 * Create the frame.
	 */
	public dlgThayDoiMatKhau(JFrame frmTrangChu) {
		super(frmTrangChu, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTieuDe = new JLabel("Thay đổi mật khẩu");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setBounds(10, 10, 460, 40);
		contentPane.add(lblTieuDe);

		JLabel lblXacNhanMK = new JLabel("Nhập mật khẩu cũ:");
		lblXacNhanMK.setFont(new Font("Arial", Font.PLAIN, 16));
		lblXacNhanMK.setBounds(31, 76, 180, 30);
		contentPane.add(lblXacNhanMK);

		JLabel lblMKMoi = new JLabel("Nhập mật khẩu mới:");
		lblMKMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMKMoi.setBounds(31, 155, 180, 30);
		contentPane.add(lblMKMoi);

		JLabel lblXacNhanMK1 = new JLabel("Xác nhận mật khẩu:");
		lblXacNhanMK1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblXacNhanMK1.setBounds(31, 228, 180, 30);
		contentPane.add(lblXacNhanMK1);

		txtMKcu = new JPasswordField();
		txtMKcu.setBounds(220, 76, 250, 30);
		contentPane.add(txtMKcu);

		txtMKMoi = new JPasswordField();
		txtMKMoi.setBounds(220, 155, 250, 30);
		contentPane.add(txtMKMoi);

		txtMKXN = new JPasswordField();
		txtMKXN.setBounds(220, 228, 250, 30);
		contentPane.add(txtMKXN);

		lblMK = new JLabel("");
		lblMK.setForeground(new Color(255, 0, 0));
		lblMK.setBounds(220, 113, 250, 20);
		contentPane.add(lblMK);

		lbltxtMKMoi = new JLabel("");
		lbltxtMKMoi.setForeground(Color.RED);
		lbltxtMKMoi.setBounds(220, 192, 250, 20);
		contentPane.add(lbltxtMKMoi);

		lbltxtMKXn = new JLabel("");
		lbltxtMKXn.setForeground(Color.RED);
		lbltxtMKXn.setBounds(220, 265, 250, 20);
		contentPane.add(lbltxtMKXn);

		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mkMoi = txtMKMoi.getText();
				if(checkTT()) {
					TaiKhoanDAO tkDAO = new TaiKhoanDAO();
					tkDAO.updatePassword(bienStatic.maTKLogin, mkMoi);
					JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công!");
					dlgThayDoiMatKhau.this.dispose();
				}
			}
		});
		btnXacNhan.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXacNhan.setBackground(new Color(0, 153, 51));
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setBounds(104, 306, 126, 40);
		contentPane.add(btnXacNhan);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener((ActionEvent e) -> {
                    dlgThayDoiMatKhau.this.dispose();
                });
		btnHuy.setFont(new Font("Arial", Font.PLAIN, 16));
		btnHuy.setBackground(new Color(255, 51, 51));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBounds(265, 306, 126, 40);
		contentPane.add(btnHuy);
	}

	private boolean checkTT() {
		lblMK.setText("");
		lbltxtMKMoi.setText("");
		lbltxtMKXn.setText("");

		if(txtMKcu.getText().equals("")) {
			lblMK.setText("Vui lòng nhập mật khẩu cũ");
			return false;
		}
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		TaiKhoan taiKhoan = tkDAO.selectById(bienStatic.maTKLogin);
		if(taiKhoan == null || !taiKhoan.getMatKhau().equals(txtMKcu.getText())) {
			lblMK.setText("Mật khẩu không đúng");
			return false;
		}
		if(txtMKMoi.getText().equals("")) {
			lbltxtMKMoi.setText("Vui lòng nhập mật khẩu mới");
			return false;
		}
		if(txtMKXN.getText().equals("")) {
			lbltxtMKXn.setText("Vui lòng nhập lại mật khẩu");
			return false;
		}
		if(!txtMKMoi.getText().equals(txtMKXN.getText())) {
			lbltxtMKXn.setText("Mật khẩu không khớp");
			return false;
		}
		return true;
	}
}

package gui;

import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class dlgQuanLyTaiKhoan extends JDialog implements MouseListener , ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField txtMaTK;
	private JTextField txtTenTK;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dlgQuanLyTaiKhoan dialog = new dlgQuanLyTaiKhoan();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dlgQuanLyTaiKhoan(JFrame frmTrangChu, String maTaiKhoan) {
		super(frmTrangChu, true);
		setBounds(100, 100, 605, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Mã tài khoản:");
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblNewLabel.setBounds(54, 77, 115, 41);
			contentPanel.add(lblNewLabel);
		}
		{
			txtMaTK = new JTextField();
			txtMaTK.setBounds(172, 85, 243, 28);
			contentPanel.add(txtMaTK);
			txtMaTK.setColumns(10);
			txtMaTK.setEditable(false);
		}
		{
			JLabel lblTnTiKhon = new JLabel("Tên tài khoản:");
			lblTnTiKhon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblTnTiKhon.setBounds(54, 128, 115, 41);
			contentPanel.add(lblTnTiKhon);
		}
		{
			txtTenTK = new JTextField();
			txtTenTK.setColumns(10);
			txtTenTK.setBounds(172, 136, 243, 28);
			contentPanel.add(txtTenTK);
		}
		{
			JLabel lblMtKhu = new JLabel("Mật khẩu:");
			lblMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblMtKhu.setBounds(54, 179, 115, 41);
			contentPanel.add(lblMtKhu);
		}
		{
			JLabel lblLoiTiKhon = new JLabel("Loại tài khoản:");
			lblLoiTiKhon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblLoiTiKhon.setBounds(54, 243, 115, 41);
			contentPanel.add(lblLoiTiKhon);
		}
		
		 comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản lý"}));
		comboBox.setBounds(172, 256, 243, 28);
		contentPanel.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 193, 243, 27);
		contentPanel.add(passwordField);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(dlgQuanLyTaiKhoan.class.getResource("/icon/iconShowPass.png")));
		lblNewLabel_1.setBounds(425, 193, 30, 30);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        // Toggle password visibility
		        if (passwordField.getEchoChar() != 0) {
		            // Mật khẩu đang được ẩn, hiển thị
		            passwordField.setEchoChar((char) 0);
		        } else {
		            // Mật khẩu đang được hiển thị, ẩn
		            passwordField.setEchoChar('\u25CF'); // Sử dụng ký tự mặc định để ẩn mật khẩu
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.setBounds(213, 316, 92, 28);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener((ActionEvent e) -> {
                    boolean LoaiTK = comboBox.getSelectedIndex() == 1;
                    String maTK = txtMaTK.getText();
                    String tenTK = txtTenTK.getText();
                    String pass= passwordField.getText();
                    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
                    
                    TaiKhoan taiKhoan = new TaiKhoan(maTK, tenTK, pass, LoaiTK);
                    // Sau khi cập nhật, có thể đóng dialog nếu cần
                    if ( tenTK.isEmpty() || pass.isEmpty() ) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
                        
                        int updated = taiKhoanDAO.update(taiKhoan);
                        System.out.println(taiKhoan.toString());
                        if (updated!=0) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    dispose(); // Đóng dialog
                });
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setBounds(323, 316, 92, 28);
		contentPanel.add(btnThot);
		btnThot.addActionListener((ActionEvent e) -> {
                    dlgQuanLyTaiKhoan.this.dispose();
                });
		JLabel lblThongTin = new JLabel("Thông tin tài khoản");
		lblThongTin.setForeground(new Color(0, 102, 102));
		lblThongTin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblThongTin.setBounds(54, 10, 275, 40);
		contentPanel.add(lblThongTin);
		hienThiThongTin(maTaiKhoan);
	}
	public void hienThiThongTin(String maTaiKhoan) {
		TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
		TaiKhoan tk= taiKhoanDAO.selectById(maTaiKhoan);
		txtMaTK.setText(tk.getMaTaiKhoan());
		txtTenTK.setText(tk.getTenTaiKhoan());
		passwordField.setText(tk.getMatKhau());
		System.out.println(tk.toString());
		if (tk.isLoaiTaiKhoan()) {
			System.out.println("QL");
	        comboBox.setSelectedItem("Quản lý"); // Set the selected item in the JComboBox to "Quản lý"
	    } else {
	    	System.out.println("NV");
	        comboBox.setSelectedItem("Nhân viên"); // Set the selected item in the JComboBox to "Nhân viên"
	    }
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

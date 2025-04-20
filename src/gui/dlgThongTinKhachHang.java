package gui;

import dao.KhachHangDAO;
import entity.KhachHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class dlgThongTinKhachHang extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlContent = new JPanel();
	JTextField txtMaKH;
	JTextField txtHoTen;
	JTextField txtSoDienThoai;
	JTextField txtNgayThem;
	JTextField txtDiemTichLuy;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JButton btnThem;
	JButton btnLamMoi;
	JRadioButton rabNam;
	JRadioButton rabNu ;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dlgThongTinSanPham dialog = new dlgThongTinSanPham();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dlgThongTinKhachHang(JFrame frmTrangChu,String a,KhachHang kh) {
		super(frmTrangChu, true);
		setBounds(100, 100, 642, 558);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		
		pnlContent.setLayout(null);
		{
			JLabel lblMaKH = new JLabel("Mã khách hàng:");
			
			lblMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblMaKH.setBounds(71, 59, 152, 28);
			pnlContent.add(lblMaKH);
		}
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(217, 59, 263, 28);
		pnlContent.add(txtMaKH);
		txtMaKH.setColumns(10);
		{
			JLabel lblHoTen = new JLabel("Họ tên");
			lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblHoTen.setBounds(71, 124, 152, 28);
			pnlContent.add(lblHoTen);
		}
		{
			txtHoTen = new JTextField();
			txtHoTen.setColumns(10);
			txtHoTen.setBounds(217, 124, 263, 28);
			pnlContent.add(txtHoTen);
		}
		{
			JLabel lblGioiTinh = new JLabel("Giới tính:");
			lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblGioiTinh.setBounds(71, 189, 152, 28);
			pnlContent.add(lblGioiTinh);
		}
		{
			JLabel lblSoDienThoai = new JLabel("Điện thoại:");
			lblSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblSoDienThoai.setBounds(71, 254, 152, 28);
			pnlContent.add(lblSoDienThoai);
		}
		{
			txtSoDienThoai = new JTextField();
			txtSoDienThoai.setColumns(10);
			txtSoDienThoai.setBounds(217, 254, 263, 28);
			pnlContent.add(txtSoDienThoai);
		}
		{
			JLabel lblNgaySinh = new JLabel("Ngày thêm:");
			lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblNgaySinh.setBounds(71, 325, 152, 28);
			pnlContent.add(lblNgaySinh);
		}
		{
			txtNgayThem = new JTextField();
			txtNgayThem.setEditable(false);
			txtNgayThem.setColumns(10);
			txtNgayThem.setBounds(217, 325, 263, 28);
			pnlContent.add(txtNgayThem);
		}
		{
			JLabel lblDiemTichLuy = new JLabel("Điểm tích lũy:");
			lblDiemTichLuy.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblDiemTichLuy.setBounds(71, 395, 152, 28);
			pnlContent.add(lblDiemTichLuy);
		}
		{
			txtDiemTichLuy = new JTextField();
			txtDiemTichLuy.setEditable(false);
			txtDiemTichLuy.setColumns(10);
			txtDiemTichLuy.setBounds(217, 395, 263, 28);
			pnlContent.add(txtDiemTichLuy);
		}
		{
			btnLamMoi = new JButton("Làm mới");
			btnLamMoi.setBounds(395, 430, 85, 21);
			pnlContent.add(btnLamMoi);
			btnLamMoi.addActionListener((ActionEvent e) -> {
                            txtHoTen.setText("");
                            txtSoDienThoai.setText("");
                        });
		}

		rabNam = new JRadioButton("Nam");
		buttonGroup.add(rabNam);
		rabNam.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rabNam.setBounds(217, 197, 66, 21);
		pnlContent.add(rabNam);
		 rabNu = new JRadioButton("Nữ");
		buttonGroup.add(rabNu);
		rabNu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rabNu.setBounds(285, 197, 66, 21);
		pnlContent.add(rabNu);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		


if (kh != null) {
    txtMaKH.setText(kh.getMaKhachHang());
    txtHoTen.setText(kh.getHoTen());
    txtSoDienThoai.setText(kh.getDienThoai());
    txtDiemTichLuy.setText("0"); 
    txtNgayThem.setText(kh.getNgayThem().toString());

    if (kh.isGioiTinh()) {
        rabNam.setSelected(true);
    } else {
        rabNu.setSelected(true);
    }
}
		{
			btnThem = new JButton(a);
			btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
			btnThem.setBounds(240, 462, 105, 39);
			pnlContent.add(btnThem);
			btnThem.addActionListener((ActionEvent e) -> {
                            if("Thêm".equals(a)){
                                KhachHangDAO khoanDAO= new KhachHangDAO();
                                
                                String maKhachHang =khoanDAO.getNextMaKhachHang();
                                txtMaKH.setText(maKhachHang);
                                String hoTen= txtHoTen.getText();
                                Boolean gioiTinh= rabNam.isSelected();
                                String dienThoai= txtSoDienThoai.getText();
                                LocalDateTime ngayThem= LocalDateTime.now();
                                
                                KhachHang khachHang= new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
                                
                                
                                if ( hoTen.isEmpty() || dienThoai.isEmpty() ) {
                                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
                                    
                                    int updated = khoanDAO.insert(khachHang);
                                    
                                    if (updated!=0) {
                                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                            if("Cập nhật".equals(a)) {
                                KhachHangDAO khoanDAO= new KhachHangDAO();
                                
                                String maKhachHang =kh.getMaKhachHang();
                                String hoTen= txtHoTen.getText();
                                Boolean gioiTinh= rabNam.isSelected();
                                String dienThoai= txtSoDienThoai.getText();
                                LocalDateTime ngayThem= kh.getNgayThem();
                                
                                KhachHang khachHang= new KhachHang(maKhachHang, hoTen, gioiTinh, dienThoai, ngayThem);
                                
                                
                                if ( hoTen.isEmpty() || dienThoai.isEmpty() ) {
                                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
                                    
                                    int updated = khoanDAO.update(khachHang);
                                    
                                    if (updated!=0) {
                                        JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                            dispose(); // Đóng dialog
                        });
		}
		{
			JButton btnHuy = new JButton("Hủy");
			btnHuy.addActionListener((ActionEvent e) -> {
                            dlgThongTinKhachHang.this.dispose();
                        });
			btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 15));
			btnHuy.setBounds(366, 461, 114, 40);
			pnlContent.add(btnHuy);
		}
		{
			JLabel lblTieuDe = new JLabel("Thông tin khách hàng");
			lblTieuDe.setForeground(new Color(0, 102, 102));
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 25));
			lblTieuDe.setBounds(63, 10, 326, 39);
			pnlContent.add(lblTieuDe);
		}
		
	}
	
}


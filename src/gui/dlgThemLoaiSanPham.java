package gui;

import dao.LoaiSanPhamDAO;
import entity.LoaiSanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class dlgThemLoaiSanPham extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTextField textField;
	private final JTextField txtTenLoai;
	private final JTextArea areaMoTa;
	private final JCheckBox chkPhanKichThuoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgThemLoaiSanPham dialog = new dlgThemLoaiSanPham(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgThemLoaiSanPham(JFrame frmTrangChu) {
		super(frmTrangChu, true);
		setBounds(100, 100, 544, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.BLACK);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTieuDe = new JLabel("Thông tin loại sản phẩm");
			lblTieuDe.setForeground(Color.GRAY);
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 21));
			lblTieuDe.setBounds(150, 10, 278, 50);
			contentPanel.add(lblTieuDe);
		}
		{
			textField = new RoundedTextField(10);
			textField.setEditable(false);
			textField.setBounds(140, 77, 312, 29);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblLoai = new JLabel("Mã loại:");
			lblLoai.setForeground(Color.WHITE);
			lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblLoai.setBounds(42, 70, 88, 36);
			contentPanel.add(lblLoai);
		}
		{
			JLabel lblTenLoai = new JLabel("Tên loại:");
			lblTenLoai.setForeground(Color.WHITE);
			lblTenLoai.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblTenLoai.setBounds(42, 126, 88, 36);
			contentPanel.add(lblTenLoai);
		}
		{
			txtTenLoai = new RoundedTextField(10);
			txtTenLoai.setColumns(10);
			txtTenLoai.setBounds(140, 133, 312, 29);
			contentPanel.add(txtTenLoai);
		}
		{
			JLabel lblMT = new JLabel("Mô tả:");
			lblMT.setForeground(Color.WHITE);
			lblMT.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblMT.setBounds(42, 181, 88, 36);
			contentPanel.add(lblMT);
		}
		
		areaMoTa = new RoundedTextArea(5, 30);
		areaMoTa.setBounds(140, 190, 312, 101);
		contentPanel.add(areaMoTa);
		
		chkPhanKichThuoc = new JCheckBox("Sản phẩm có phân chia kích thước");
		chkPhanKichThuoc.setForeground(Color.WHITE);
		chkPhanKichThuoc.setBackground(Color.BLACK);
		chkPhanKichThuoc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		chkPhanKichThuoc.setBounds(42, 304, 327, 21);
		contentPanel.add(chkPhanKichThuoc);
		
		JButton btnThem = new RoundedButton("Thêm");
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnThem.setBounds(223, 369, 109, 36);
		contentPanel.add(btnThem);
		btnThem.addActionListener((ActionEvent e) -> {
                    if(checkData()) {
                        LoaiSanPhamDAO.getInstance().insert(convertToObject());
                        JOptionPane.showMessageDialog(null, "Đã thêm loại sản phẩm!");
                        pnlSanPham.updateCboLoaiSP();
                        dlgThemLoaiSanPham.this.dispose();
                    }
                });
		JButton btnHuy = new RoundedButton("Hủy");
		btnHuy.addActionListener((ActionEvent e) -> {
                    dlgThemLoaiSanPham.this.dispose();
                });
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnHuy.setBounds(354, 369, 109, 36);
		contentPanel.add(btnHuy);
		
		JButton btnLamMoi = new RoundedButton("Làm mới");
		btnLamMoi.setBounds(354, 326, 98, 21);
		contentPanel.add(btnLamMoi);
	}
	private LoaiSanPham convertToObject() {
		String tenL = txtTenLoai.getText();
		String moTa = areaMoTa.getText().trim().equals("")?"":areaMoTa.getText();
		String maL = LoaiSanPhamDAO.getInstance().taoMaLoaiSP();
		boolean phanLoai = chkPhanKichThuoc.isSelected();
		return new LoaiSanPham(maL, tenL, moTa, phanLoai);
		
	}
	private boolean checkData() {
		String tenL = txtTenLoai.getText().trim();
		if(tenL.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập loại sản phẩm!");
			return false;
		}
		return true;
	}
	
	public class RoundedTextArea extends JTextArea {
	    private static final long serialVersionUID = 1L;
	    private final int arc = 20;

	    public RoundedTextArea(int rows, int columns) {
	        super(rows, columns);
	        setOpaque(false);
	        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	        setLineWrap(true);
	        setWrapStyleWord(true);
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

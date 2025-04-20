package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.bienStatic;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgThayDoiTienBanDau extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static JTextField txtTienBanDau;
	public static JTextField txtTienLayRa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dlgThayDoiTienBanDau dialog = new dlgThayDoiTienBanDau(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dlgThayDoiTienBanDau(JDialog dlgThayCa) {
		super(dlgThayCa, true);
		setBounds(100, 100, 557, 298);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.BLACK);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTienBanDau = new JLabel("Tiền ban đầu:");
			lblTienBanDau.setForeground(Color.WHITE);
			lblTienBanDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTienBanDau.setBounds(50, 47, 143, 27);
			contentPanel.add(lblTienBanDau);
		}
		{
			txtTienBanDau = new RoundedTextField(10);
			txtTienBanDau.setEditable(false);
			txtTienBanDau.setText(bienStatic.tienTaiQuay + "");
			txtTienBanDau.setBounds(200, 47, 287, 27);
			contentPanel.add(txtTienBanDau);
			txtTienBanDau.setColumns(10);
		}
		{
			JLabel lblTienLayRa = new JLabel("Tiền lấy ra:");
			lblTienLayRa.setForeground(Color.WHITE);
			lblTienLayRa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTienLayRa.setBounds(50, 118, 143, 27);
			contentPanel.add(lblTienLayRa);
		}
		{
			txtTienLayRa = new RoundedTextField(10);
			txtTienLayRa.setColumns(10);
			txtTienLayRa.setBounds(200, 118, 287, 27);
			contentPanel.add(txtTienLayRa);
		}
		{
			JButton btnXacNhan = new RoundedButton("Xác nhận");
			btnXacNhan.addActionListener((ActionEvent e) -> {
                            if(check()) {
                                dlgXacNhanQL dlgXacNhan = new dlgXacNhanQL(dlgThayCa);
                                dlgXacNhan.setLocationRelativeTo(dlgThayCa);
                                dlgXacNhan.setVisible(true);
                                dlgThayDoiTienBanDau.this.setVisible(false);
                            }
                        });
			btnXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btnXacNhan.setBounds(130, 191, 107, 29);
			contentPanel.add(btnXacNhan);
		}
		{
			JButton btnHuy = new RoundedButton("Hủy");
			btnHuy.addActionListener((ActionEvent e) -> {
                            dlgThayDoiTienBanDau.this.dispose();
                        });
			btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btnHuy.setBounds(300, 191, 107, 29);
			contentPanel.add(btnHuy);
		}
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                txtTienLayRa.requestFocusInWindow();
	            }
	        });
	}
	public boolean check() {
		String layRa = txtTienLayRa.getText();
		try {
			double tienLayRa = Double.parseDouble(layRa);
			if(tienLayRa > bienStatic.tienTaiQuay) {
				JOptionPane.showMessageDialog(null, "Không đủ tiền!");
				return false;
			}
		} catch (HeadlessException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Lỗi: kiểu dữ liệu");
			return false;
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
	

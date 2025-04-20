package gui;

import dao.SanPhamDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class dlgThongKe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelTable;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dlgThongKe dialog = new dlgThongKe();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dlgThongKe(JFrame frmTrangChu, LocalDate d1,LocalDate d2,String l1,String l2) {
		super(frmTrangChu, true);
		setBounds(100, 100, 1040, 609);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thống kê:");
		lblNewLabel.setForeground(new Color(0, 102, 102));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(20, 22, 166, 51);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDay1 = new JLabel();
		lblDay1.setText("");
		lblDay1.setForeground(new Color(0, 102, 102));
		lblDay1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 35));
		lblDay1.setBounds(207, 22, 201, 51);
		contentPanel.add(lblDay1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 1004, 362);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelTable= new DefaultTableModel(
			new Object[][] {
			  
			},

			new String[] {
				"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "M\u00F4 t\u1EA3", "K\u00EDch th\u01B0\u1EDBc", "Gi\u00E1 b\u00E1n", "S\u1ED1 l\u01B0\u1EE3ng b\u00E1n", "Doanh thu"
			}
			
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(79);
		table.getColumnModel().getColumn(1).setPreferredWidth(92);
		table.getColumnModel().getColumn(5).setPreferredWidth(76);
		scrollPane.setViewportView(table);
		
		JCheckBox chkSapXep = new JCheckBox("Sắp xép số lượng bán giảm dần");
		chkSapXep.setBounds(791, 69, 223, 21);
		contentPanel.add(chkSapXep);
		chkSapXep.addActionListener((ActionEvent e) -> {
                    boolean selected = chkSapXep.isSelected();
                    modelTable = (DefaultTableModel) table.getModel();
                    modelTable.setRowCount(0);
                    
                    SanPhamDAO sanPhamDAO = new SanPhamDAO();
                    ArrayList<Object[]> danhSachSanPhamTK = sanPhamDAO.danhSach(d1.atStartOfDay(),d2.atTime(23, 59, 59, 999999999),selected);
                    
                    for (Object[] ds : danhSachSanPhamTK) {
                        modelTable.addRow(ds);
                    }
                });
		
		JLabel lblSLngSn = new JLabel("Số lượng sản phẩm bán ra:");
		lblSLngSn.setForeground(new Color(0, 0, 0));
		lblSLngSn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSLngSn.setBounds(20, 468, 291, 39);
		contentPanel.add(lblSLngSn);
		
		JLabel lblTngDoanhThu = new JLabel("Tổng doanh thu:");
		lblTngDoanhThu.setForeground(new Color(0, 0, 0));
		lblTngDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblTngDoanhThu.setBounds(20, 517, 291, 39);
		contentPanel.add(lblTngDoanhThu);
		
		JLabel lblSLngSn_1 = new JLabel("");
		lblSLngSn_1.setForeground(Color.BLACK);
		lblSLngSn_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSLngSn_1.setBounds(310, 468, 152, 39);
		contentPanel.add(lblSLngSn_1);
		lblSLngSn_1.setText(l1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 60, 60));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_2.setBounds(310, 507, 229, 51);
		contentPanel.add(lblNewLabel_2);
		lblNewLabel_2.setText(l2);
		
		JButton btnNewButton = new JButton("Xác nhận");
		btnNewButton.addActionListener((ActionEvent e) -> {
                    dispose();
                });
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(848, 517, 166, 41);
		contentPanel.add(btnNewButton);
		
		JLabel lblGach = new JLabel();
		lblGach.setText("-");
		lblGach.setForeground(new Color(0, 102, 102));
		lblGach.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 35));
		lblGach.setBounds(414, 22, 20, 51);
		contentPanel.add(lblGach);
		
		JLabel lblDay2 = new JLabel();
		lblDay2.setText("20/04/2004");
		lblDay2.setForeground(new Color(0, 102, 102));
		lblDay2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 35));
		lblDay2.setBounds(444, 22, 201, 51);
		contentPanel.add(lblDay2);
		capNhatBangSanPham(d1, d2);
		lblDay1.setText(d1.toString());
		lblDay2.setText(d2.toString());
		if(d1==d2) {
			lblDay1.setText("Hôm nay");
			lblGach.setText("");
			lblDay2.setText("");
		}
	}
	public void capNhatBangSanPham(LocalDate day1, LocalDate day2) {
        modelTable = (DefaultTableModel) table.getModel();
        modelTable.setRowCount(0);
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        ArrayList<Object[]> danhSachSanPhamTK = sanPhamDAO.danhSach(day1.atStartOfDay(),day2.atTime(23, 59, 59, 999999999),false);

        for (Object[] ds : danhSachSanPhamTK) {
            modelTable.addRow(ds);
        }
    }
}

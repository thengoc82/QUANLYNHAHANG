package gui;

import dao.ThongTinCaDAO;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class dlgThongKeTheoCa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JTable table;
	private final DefaultTableModel dataTable;

	/**
	 * Create the jdialog.
	 */
	public dlgThongKeTheoCa(JFrame frmTrangChu) {
		super(frmTrangChu, true);
		setTitle("THỐNG KÊ THEO CA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1058, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1038, 533);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 1018, 76);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Từ ngày:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 143, 19);
		panel_1.add(lblNewLabel);
		
		JLabel lblnNgy = new JLabel("Đến ngày:");
		lblnNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnNgy.setBounds(241, 10, 143, 19);
		panel_1.add(lblnNgy);
		
		JButton btnNewButton = new JButton("Lọc");
		btnNewButton.setBounds(454, 32, 58, 21);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("(*) Trong ngày");
		lblNewLabel_1.setForeground(new Color(255, 128, 64));
		lblNewLabel_1.setBounds(574, 36, 86, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("(*) Ca đã reset");
		lblNewLabel_1_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_1.setBounds(708, 36, 86, 13);
		panel_1.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 33, 176, 19);
		panel_1.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(241, 32, 176, 19);
		panel_1.add(dateChooser_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 87, 1018, 434);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1018, 426);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(dataTable= new DefaultTableModel(
			new Object[][] {
				{"Ca01", "NV001", "6:30:00 ", "11:30:00", "1.000.000", "900.500"},
				{"Ca02", "NV002", "11:30:00 ", "16:30:00", "1.900.500", "1.200.000"},
				{"Ca03", "NV003", "16:30:00 ", "21:30:00", "3.100.500", "1.400.250"},
				
			},
			new String[] {
				"M\u00E3 ca", "Nh\u00E2n vi\u00EAn", "Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u", "Th\u1EDDi gian k\u00EAt th\u00FAc", "Ngày làm","Ti\u1EC1n ban \u0111\u1EA7u", "T\u1ED5ng ti\u1EC1n ca"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(94);
		table.getColumnModel().getColumn(3).setPreferredWidth(94);
		scrollPane.setViewportView(table);
		capNhatBang();
	}
	public void capNhatBang() {
		 dataTable.setRowCount(0);
	        
	        // Lấy danh sách từ cơ sở dữ liệu
	       ThongTinCaDAO thongTinCaDAO =new ThongTinCaDAO();
	        ArrayList<Object[]> danhSach = thongTinCaDAO.danhSachThongKe();

	        // Thêm dữ liệu vào bảng
	        for (Object[] ds : danhSach) {
	            dataTable.addRow(ds);
	        }
	}
}

package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.KichThuoc;
import entity.SanPham;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

public class tpKhungSanPham extends JTabbedPane{
	public static NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	
	public static ArrayList<ChiTietHoaDon> temp = new ArrayList<ChiTietHoaDon>();
    public tpKhungSanPham(ArrayList<SanPham> cafeProducts, ArrayList<SanPham> pastryProducts, ArrayList<SanPham> drinkProducts) {
        initComponents(cafeProducts, pastryProducts, drinkProducts);
    }

    private void initComponents(ArrayList<SanPham> cafeProducts, ArrayList<SanPham> pastryProducts, ArrayList<SanPham> drinkProducts) {
        addTab("Cafe", createProductTab(cafeProducts));
        addTab("Bánh ngọt", createProductTab(pastryProducts));
        addTab("Thức uống khác", createProductTab(drinkProducts));
    }

    private JScrollPane createProductTab(ArrayList<SanPham> products) {
        JPanel panel = new JPanel(new GridLayout(0, 4, 10, 10)); // 4 cột, khoảng cách 10px

        for (SanPham sp : products) {
        	if(sp.isTrangThai()) {
        		JPanel temp = createProductPanel(sp);
            	if(temp != null) {
            		panel.add(temp);
            	}
        	}
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        return scrollPane;
    }

    private static JPanel createProductPanel(SanPham sp) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        String imagePath = sp.getHinhAnh();
        try {
//            ImageIcon imageIcon = new ImageIcon(frmTrangChu.class.getResource(imagePath));
//            Image imgHinh = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            
//            JLabel imageLabel = new JLabel(new ImageIcon(imgHinh));
        	ImageIcon imageIcon = null;
            Image imgHinh = null;
            if(imagePath.startsWith("/")) {
            	imageIcon = new ImageIcon(frmTrangChu.class.getResource(imagePath));
            	imgHinh = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            }else {
            	imageIcon = new ImageIcon(imagePath);
                imgHinh = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            } 	
            JLabel imageLabel = new JLabel(new ImageIcon(imgHinh));

            String[] sizes = sp.getKichThuocGia().entrySet().stream()
            	    .filter(entry -> entry.getValue() != 0)
            	    .map(entry -> entry.getKey().toString())
            	    .toArray(String[]::new);

            Arrays.sort(sizes);
            JComboBox<String>sizeComboBox = new JComboBox<>(sizes);
            Font font = new Font("Segoe UI", Font.PLAIN, 15);
            
            JLabel nameLabel = new JLabel(sp.getTenSanPham());
            nameLabel.setFont(font);
            nameLabel.setHorizontalAlignment(JLabel.CENTER); 
            JLabel priceLabel;
            boolean flag = false;
            for (Entry<KichThuoc, Double> entry : sp.getKichThuocGia().entrySet()) {
                Double gia = entry.getValue();
                if(gia != 0) {
                	flag = true;
                }
            }
            if(flag == false) {
            	return null;
            }
            if(sp.getLoaiSanPham().isCoPhanLoai()) {
            	String s = sizeComboBox.getSelectedItem().toString();
            	priceLabel = new JLabel("Giá: " + fmt.format(sp.getKichThuocGia().get(KichThuoc.valueOf(s))));
            }else {
            	priceLabel = new JLabel("Giá: " + fmt.format(sp.getKichThuocGia().get(KichThuoc.D)));
            }
            priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            // Lắng nghe sự kiện khi thay đổi kích thước
            sizeComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedSize = (String) e.getItem();
                        KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(selectedSize);
                        Double newPrice = sp.getKichThuocGia().get(kichThuoc);
                        priceLabel.setText("Giá: " + fmt.format(newPrice));
                    }
                }
            });

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            centerPanel.add(nameLabel);
            centerPanel.add(Box.createVerticalStrut(10)); 
            centerPanel.add(priceLabel);
             // Thêm JComboBox để chọn kích thước
            centerPanel.add(Box.createVerticalStrut(10)); 
            
            centerPanel.add(sizeComboBox);

            panel.add(imageLabel, BorderLayout.NORTH); 
            panel.add(centerPanel, BorderLayout.CENTER); 

            panel.setBorder(new LineBorder(Color.BLACK, 1));
            panel.setBackground(new Color(205,201,195,255));
	        panel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) { 
	                	String selectedSize = (String) sizeComboBox.getSelectedItem(); 
	                	System.out.println(selectedSize);
	                    KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(selectedSize);
	                    Double price = sp.getKichThuocGia().get(kichThuoc);
	                    SanPhamDAO sanPhamDAO = new SanPhamDAO();
	                    SanPham sanPhamMoi = sanPhamDAO.selectById(sp.getMaSanPham());
	                    sanPhamMoi.setKichThuocGia(new HashMap<KichThuoc, Double>());
	                    sanPhamMoi.themKichThuocGia(kichThuoc, price);
	                    String input = JOptionPane.showInputDialog(null, "Vui lòng nhập số lượng sản phẩm:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);
	                    int quantity = 0;
	                    if(input != null) {
	                    	try {
		                        quantity = Integer.parseInt(input);
		                        if(quantity <= 0) {
			                    	throw new Exception();
		                        }
		                    	Boolean f = false;
		                        ChiTietHoaDon cthdt = new ChiTietHoaDon(sanPhamMoi, quantity, KichThuoc.fromStringIgnoreCase(selectedSize));
		                        for (ChiTietHoaDon cthd: tpKhungSanPham.temp) {
		    						if(cthd.getSanPham().getMaSanPham().equals(cthdt.getSanPham().getMaSanPham()) && cthd.getKt().name().equals(cthdt.getKt().name())) {
		    							tpKhungSanPham.temp.get(tpKhungSanPham.temp.indexOf(cthd)).setSoLuong(cthd.getSoLuong() + cthdt.getSoLuong());
		    							f = true;
		    						}
		    					}
		                        if(f == false) {
		                        	tpKhungSanPham.temp.add(cthdt);
		                        }
			                	// Get instance of frmTrangChu
			                	frmTrangChu trangChu = controler.bienStatic.frmTrangChu;
			                	if (trangChu != null) {
			                		trangChu.updateTable(trangChu.tblSanPhamChon, temp);
			                		trangChu.updateTableKM(trangChu.tblKhuyenMai, temp);
			                		trangChu.updateTTThanhToan(temp);
			                	}
		                    } catch (NumberFormatException ex) {
		                    	JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ!");
		                    } catch (Exception e2) {
		                    	JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0!");
							}
	                    }
	                }
	            }
	        });
        } catch (Exception e) {
		}
        return panel;
    }
    public static ArrayList<ChiTietHoaDon> getCTHD() {
		return temp;
	}
    
}


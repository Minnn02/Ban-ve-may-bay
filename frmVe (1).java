import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class frmVe extends JFrame implements ActionListener,MouseListener{
	private Quanlyvemaybay s;
	private List<Vemaybay> list;
	private JTable table;
	private JTextField txtMaVe,txtSanBay,txtChangBay,txtNgayBay,txtNhaGa,txtSoGhe,txtDonGia,txtISBN, txtTim;
	private JButton btnThem,btnXoa,btnXoaTrang,btnLuu,btnSua,btnTim;
	private DefaultTableModel tableModel;
	private static final String FILENAME="dulieu1.txt";
	
	public frmVe(Quanlyvemaybay s) {
		setTitle("Quan Ve May Bay");
		setSize(800,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
		JPanel pnlNorth;
		add(pnlNorth= new JPanel(), BorderLayout.NORTH);
		javax.swing.border.Border NorthBorder= BorderFactory.createLineBorder(Color.red);
		TitledBorder northTitleBorder= new TitledBorder(NorthBorder, "RECORD EDITOR");
		pnlNorth.setBorder(northTitleBorder);
		Box b= Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7;
		JLabel lblMaVe,lblSanBay,lblChangBay,lblNgayBay, lblNhaGa, lblSoGhe, lblDonGia,lblISBN;
		b.add(b1 =Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaVe= new JLabel("Ma Ve: "));
		b1.add(txtMaVe= new JTextField(10));
		

		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblSanBay= new JLabel("San Bay: "));
		b2.add(txtSanBay= new JTextField(15));
		
		b2.add(lblChangBay= new JLabel("Chang Bay: "));
		b2.add(txtChangBay= new JTextField(15));

		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));

		b3.add(lblNgayBay= new JLabel("Ngay Bay: "));
		b3.add(txtNgayBay= new JTextField(10));
		b3.add(lblNhaGa= new JLabel("Nha Ga: "));
		b3.add(txtNhaGa= new JTextField(15));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		
		b4.add(lblSoGhe= new JLabel("So Ghe: "));
		b4.add(txtSoGhe= new JTextField(15));
		b4.add(lblDonGia= new JLabel("Don Gia: "));
		b4.add(txtDonGia= new JTextField(15));
		
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b5.add(lblISBN= new JLabel("International Standard Number: "));
		b5.add(txtISBN= new JTextField(15));
		
		lblMaVe.setPreferredSize(lblNgayBay.getPreferredSize());
		lblSanBay.setPreferredSize(lblNgayBay.getPreferredSize());
		lblChangBay.setPreferredSize(lblNgayBay.getPreferredSize());
		lblNgayBay.setPreferredSize(lblNgayBay.getPreferredSize());
		lblNhaGa.setPreferredSize(lblNgayBay.getPreferredSize());
		lblSoGhe.setPreferredSize(lblNgayBay.getPreferredSize());
		lblDonGia.setPreferredSize(lblNgayBay.getPreferredSize());

		
		
		b.add(b6=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		String [] headers="MaVe;SanBay;TacGia;NgayBay;NhaGa;SoGhe;DonGia;ISBN;".split(";");
		tableModel =new DefaultTableModel(headers,0);
		JScrollPane scroll= new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JPanel pnS = new JPanel();
		add(pnS , BorderLayout.SOUTH);
		//javax.swing.border.Border southBorder= BorderFactory.createLineBorder(Color.blue);
		//TitledBorder southTitleBorder= new TitledBorder(southBorder, "DANH SACH CAC CUON SACH");
		//pnS.setBorder(southTitleBorder);
		pnS.add(scroll);
		pnlNorth.add(b);
		
		JSplitPane split= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.CENTER);
		JPanel pnlLeft, pnlRight;
		split.add(pnlLeft= new JPanel());
		split.add(pnlRight= new JPanel());
		pnlRight.add(new JLabel("Nhap ma Ve can tim: "));
		pnlRight.add(txtTim= new JTextField(10));
		pnlRight.add(new JButton("Tim"));		
		pnlLeft.add(btnThem= new JButton("Them"));
		pnlLeft.add(btnXoaTrang= new JButton("Xoa Trang"));
		pnlLeft.add(btnXoa= new JButton("Xoa"));
		pnlLeft.add(btnLuu=new JButton("Luu"));
		LoadDatabase();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		table.addMouseListener(this);

	

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		if(o.equals(btnThem))
			themActions();
		if(o.equals(btnXoa))
			xoaActions();
		if(o.equals(btnXoaTrang))
			xoaTrangActions();	
		if(o.equals(btnLuu))
			luuActions(s.getList());
	
			
	}
	

	private void xoaTrangActions() {
		txtMaVe.setText("");
		txtSanBay.setText("");
		txtChangBay.setText("");
		txtNgayBay.setText("");
		txtNhaGa.setText("");
		txtSoGhe.setText("");
		txtDonGia.setText("");
		txtISBN.setText("");
		txtMaVe.requestFocus();
		
	}
	private void themActions() {
		try {
			int maVe= Integer.parseInt(txtMaVe.getText());
			String sanBay= txtSanBay.getText();
			String changBay= txtChangBay.getText();
			int ngayBay = Integer.parseInt(txtNgayBay.getText());
			String nhaGa= txtNhaGa.getText();
			int soGhe = Integer.parseInt(txtNgayBay.getText());
			double donGia= Double.parseDouble(txtSoGhe.getText());
			String iSBN= txtISBN.getText();
			Vemaybay s1 = new Vemaybay(maVe,sanBay, changBay,ngayBay,nhaGa, soGhe,donGia,iSBN);
			if(s.themVe(s1)) {
				String[] row= {Integer.toString(maVe),sanBay,changBay,Integer.toString(ngayBay),nhaGa,Integer.toString(soGhe),Double.toString(donGia),iSBN+""};
				tableModel.addRow(row);
				xoaTrangActions();
			}
			else {
				JOptionPane.showMessageDialog(null, "Trung ma ve.");
				txtMaVe.selectAll();
				txtMaVe.requestFocus();
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Loi nhap du lieu.");
			return;
		}
	}
	private void xoaActions(){
		int row= table.getSelectedRow();
		if(row!=-1) {
			int maVe= Integer.parseInt((String)table.getModel().getValueAt(row, 0));
			int hoiNhac=JOptionPane.showConfirmDialog(this, "Chac chan xoa khong","Chu y",JOptionPane.YES_NO_OPTION);
			if(hoiNhac==JOptionPane.YES_OPTION) {
				if(s.xoaVe(maVe)) {
					tableModel.removeRow(row);
				}
			}
			
		}
	}
	void LoadDatabase() {
		BufferedReader br=null;
		s=new Quanlyvemaybay();
		try {
			if(new File(FILENAME).exists()) {
				br=new BufferedReader (new FileReader(FILENAME));
				br.readLine();
			
			
				while(br.ready()){
					String line = br.readLine();
					if(line !=null&& !line.trim().equals("")) {
						String[]a= line.split(";");
						Vemaybay s1= new Vemaybay(Integer.parseInt(a[0]),a[1],a[2],Integer.parseInt(a[3]),a[4],Integer.parseInt(a[5]),Double.parseDouble(a[6]),a[7]);
						s.themVe(s1);
						tableModel.addRow(a);
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	void luuActions(ArrayList<Vemaybay> ds) {
		BufferedWriter bw;
		try{
			bw=new BufferedWriter(new FileWriter(FILENAME));
			bw.write("MaVe;SanBay;ChangBay;NgayBay;NhaGa;SoGhe;DonGia;ISBN\n");
			for(Vemaybay s1: ds) {
				bw.write(s1.toString()+"\n");
			}
			bw.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		txtMaVe.setText(table.getValueAt(row, 0).toString());
		txtSanBay.setText(table.getValueAt(row, 1).toString());
		txtChangBay.setText(table.getValueAt(row, 2).toString());
		txtNgayBay.setText(table.getValueAt(row, 3).toString());
		txtNhaGa.setText(table.getValueAt(row, 4).toString());
		txtSoGhe.setText(table.getValueAt(row, 5).toString());
		txtDonGia.setText(table.getValueAt(row, 6).toString());
		txtISBN.setText(table.getValueAt(row, 7).toString());
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


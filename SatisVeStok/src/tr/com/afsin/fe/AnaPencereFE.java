package tr.com.afsin.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.afsin.complex.types.SatisContractComplex;
import tr.com.afsin.complex.types.StokContractComplex;
import tr.com.afsin.complex.types.StokContractTotalComplex;
import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.MusteriContract;
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.types.SatisContract;
import tr.com.afsin.types.StokContract;
import tr.com.afsin.types.UrunlerContract;
import tr.com.afsin.utilities.MenulerCom;
import tr.com.afsin.vtk.MusteriVTK;
import tr.com.afsin.vtk.SatisVTK;
import tr.com.afsin.vtk.StokVTK;
import tr.com.afsin.vtk.UrunlerVTK;

public class AnaPencereFE extends JFrame implements FeInterfaces {

	public AnaPencereFE() {
		// TODO Auto-generated constructor stub
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		JTabbedPane tabs = initTabs();
		JMenuBar bar = initBar();

		add(panel);
		// TODO Auto-generated method stub
		// add(tabs);
		setJMenuBar(bar);

		// TODO Auto-generated method stub
		setTitle("Satýþ Ve Stok Progarmý");
		setSize(600, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		// TODO Auto-generated method stub
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		panel.add(pane);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		// TODO Auto-generated method stub

		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icon/stok.png");
		ImageIcon icon2 = new ImageIcon("icon/stok.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		
		
							/* STOK ÝTEMLERÝ */
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5,2));
		JPanel stokSolAltPanel = new JPanel();
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok Ýþlemleri"));
		// DefaultTableColumnModel model = new DefaultTableColumnModel();
		// JTable table = new JTable()
		Object[] stokKolanlar = { "Id", "Ürün Adý", "Personel Adý", "Adet", "Tarih","Toplam" };
		DefaultTableModel model = new DefaultTableModel(stokKolanlar, 0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);
		
		for(StokContractComplex contract :new StokVTK().GetAllStok() ) {
			model.addRow(contract.getVeriler());
			
		}
		
		JLabel stokUrunAdiLabel = new JLabel("Ürün Adý :", JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);

		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerVTK().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet :", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);

		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);

		JLabel stokTarihiLabel = new JLabel("Stok Tarihi :", JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokToplaButton = new JButton("Stok Toplam");
		stokSolUstPanel.add(stokToplaButton);
		stokToplaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i< satir;i++) {
					model.removeRow(0);
				}
				for (StokContractTotalComplex total : new StokVTK().GetTotalStok()) {
					model.addRow(total.getVeriler());
				}
				
			}
		});
		
		stokYenileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for(int i=0;i<satir;i++) {
					model.removeRow(0);
					
				}
				
				
				
				for(StokContractComplex compcontract :new StokVTK().GetAllStok() ) {
					model.addRow(compcontract .getVeriler());
					
				}
			}
		});
		
		
		stokEkleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(stokTarihi.getDate());
				
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokVTK().Insert(contract);
				
				JOptionPane.showMessageDialog(null,uContract.getAdi()+" adlý  ürün stok sistemine eklenmiþtir..");
				int satir = model.getRowCount();
				for (int i=0; i<satir ;i++){
						model.removeRow(0);
						
				}
				for (StokContractComplex compContract : new StokVTK().GetAllStok()){
					model.addRow(compContract.getVeriler());
					
				}
								
			}
		});
		
					/*SATIÞ ÝTEMLERÝ*/
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satissagUstPanel = new JPanel(new GridLayout(6,2));
		JPanel satisSagAltPanel = new JPanel();
		
		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satýþ Ýþlemleri"));
		Object[] satisKolanlar = { "Müþteri Adý", "Personel Adý", "Ürün Adý" , "Adet", "Tarih" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolanlar, 0);
		JTable satisTable = new JTable(satisModel);

		JScrollPane satisTablePane = new JScrollPane(satisTable);
		
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adý :", JLabel.RIGHT);
		satissagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerVTK().GetAll().toArray());
		satissagUstPanel.add(satisUrunAdiBox);
		
		JLabel musteriLabel = new JLabel("Müþteri Adý",JLabel.RIGHT);
		satissagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriVTK().GetAll().toArray());
		satissagUstPanel.add(musteriAdiBox);
		
		
		JLabel satisAdetLabel = new JLabel("Adet :", JLabel.RIGHT);
		satissagUstPanel.add(satisAdetLabel);

		JTextField satisAdetField = new JTextField(10);
		satissagUstPanel.add(satisAdetField);

		JLabel satisTarihiLabel = new JLabel("Satiþ Tarihi :", JLabel.RIGHT);
		satissagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satissagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satiþ Yap");
		satissagUstPanel.add(satisEkleButton);
		satisEkleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
				MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
				SatisContract contract = new SatisContract();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(satisTarihi.getDate());
				
				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setTarih(date);
				
				
				new SatisVTK().Insert(contract);
				StokContract stokContract = new StokContract();
			
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setTarih(date);
				
				new StokVTK().Insert(stokContract);
				
				JOptionPane.showMessageDialog(null, mContract.getAdiSoyadi()+"Adlý Müþteriye Satýþ Gerçekleþmiþtir.");
				int satir = satisModel.getRowCount();
				for(int i =0 ; i<satir;i++) {
					satisModel.removeRow(0);
				}
			

				
				for(SatisContractComplex yenileContract :new SatisVTK().GetAllSatis() ) {
					satisModel.addRow(yenileContract.getVeriler());
					
				}
				
			}
		}); 
		JButton satisYenileButton = new JButton("Yenile");
		satissagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					int satir = satisModel.getRowCount();
					for(int i =0 ; i<satir;i++) {
						satisModel.removeRow(0);
					}
				

					
					for(SatisContractComplex contract :new SatisVTK().GetAllSatis() ) {
						satisModel.addRow(contract.getVeriler());
						
					}
				}
			
			
		});
		
		
		
	
		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);
		
		satisPanel.add(satisSagPanel,BorderLayout.EAST);
		satisPanel.add(satisTablePane,BorderLayout.CENTER);
		
		satisSagPanel.add(satissagUstPanel,BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel,BorderLayout.SOUTH);
		
		
		stokSolPanel.add(stokSolUstPanel,BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel,BorderLayout.SOUTH);

		pane.addTab("STOK", icon, stokPanel, "nating");

		pane.addTab("SATIÞ", icon2, satisPanel, "nating");
		return pane;
	}
	

}

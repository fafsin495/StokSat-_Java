package tr.com.afsin.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.KategoriContract;
import tr.com.afsin.types.UrunlerContract;
import tr.com.afsin.vtk.KategoriVTK;
import tr.com.afsin.vtk.UrunlerVTK;

public class UrunEkleFE extends JDialog implements FeInterfaces {

	public UrunEkleFE() {
		// TODO Auto-generated constructor stub
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		// TODO Auto-generated method stub
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayýt Alaný"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType (DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel adiLabel = new JLabel("Ürun Adý : ", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);

		JLabel kategoriLabel = new JLabel("Kategori Seç", JLabel.RIGHT);
		panel.add(kategoriLabel);
	
		JComboBox kategoriBox = new JComboBox(new KategoriVTK().GetAllParentId().toArray());
		panel.add(kategoriBox);
		JLabel tarihLabel = new JLabel("Tarih Seç", JLabel.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);

		JLabel fiyatLabel = new JLabel("Fiyat Gir", JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UrunlerContract contract = new UrunlerContract();
				KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(tarihDate.getDate());
				contract.setAdi(adiField.getText());
				contract.setKategoriId(casContract.getId());
				contract.setTarih(date);
				contract.setFiyat(Float.parseFloat(fiyatField.getText()));
				new UrunlerVTK().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdi()+"  adlý ürün baþarýlý bir þekilde eklenmiþtir.");
				
				
			}
		});
		
		


		// TODO Auto-generated method stub
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}

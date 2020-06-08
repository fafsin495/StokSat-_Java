package tr.com.afsin.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.KategoriContract;
import tr.com.afsin.vtk.KategoriVTK;

public class KategoriEkleFe extends JDialog implements FeInterfaces  {

	public KategoriEkleFe() {
		initPencere();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));
		add(panel);
		// TODO Auto-generated method stub
		setTitle ("Kategori Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
	
		
		
		// TODO Auto-generated method stub
		JPanel panel = new JPanel (new GridLayout(3,2));
		JLabel adiLabel= new JLabel("Kategori Adý",JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JLabel kategoriLabel = new JLabel("Kategori Seç",JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox=new JComboBox(new KategoriVTK().GetAllParentId().toArray());
		
		
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("**Kategori Seçiniz**",0);
		kategoriBox.setSelectedIndex(0);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract =new KategoriContract();
				
				
				
				if(kategoriBox.getSelectedIndex()!=0) {
					KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
					contract.setAdi(adiField.getText());
					contract.setParentId(casContract.getId());
					
					new KategoriVTK().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAdi()+"adlý Kategori baþarýlý bir þekilde eklenmiþtir.");
					
					
				}
				else {
					contract.setAdi(adiField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());
					
					new KategoriVTK().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAdi()+"adlý Kategori baþarýlý bir þekilde eklenmiþtir.");
					kategoriBox.removeAll();
				
					
				}
				
				
			}
		});
		
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);

		
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

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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.AccountsContract;
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.types.YetkilerContract;
import tr.com.afsin.vtk.AccountVTK;
import tr.com.afsin.vtk.PersonelVTK;
import tr.com.afsin.vtk.YetkilerVTK;

public class SifreIslemleriFE extends JDialog implements FeInterfaces{

	public SifreIslemleriFE() {
		// TODO Auto-generated constructor stub
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel =initPanel() ;
		
		// TODO Auto-generated method stub
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ýþlemleri"));
		add(panel);
		setTitle("Þifre Belirleme Ýþlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		JPanel panel= new JPanel(new GridLayout (5,2));
		JLabel personelLabel = new JLabel("Personel Seç :",JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox=new JComboBox(new PersonelVTK().GetAll().toArray());
		panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Seç: ",JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox=new JComboBox(new YetkilerVTK().GetAll().toArray());
		panel.add(yetkiBox);
		JLabel password1Label =new JLabel("ÞÝfre belirle ",JLabel.RIGHT);
		panel.add(password1Label);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel1 = new JLabel("Þifreyi Tekrar Gir",JLabel.RIGHT);
		panel.add(passTekrarLabel1);
		JPasswordField passTekrar = new JPasswordField(10);
		panel.add(passTekrar);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		JButton iptalButton = new JButton("Ýptal Et");
		panel.add(iptalButton );
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract =new  AccountsContract();
				PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
				YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
				if(passField.getText().equals(passTekrar.getText())) {
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					new AccountVTK().Insert(contract);
					JOptionPane.showMessageDialog(null,pContract.getAdiSoyadi()+ "Adlý Personelin"+yContract.getAdi()+"Yetkisi Baþarýlý Bir Þekilde Gerçekleþmiþtir.:)");
					
				}else 
					JOptionPane.showMessageDialog(null, "Þifreler uyuþmuyor tekrarla.");
					
				
				
				
				
			}
		});	
		
				
		
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

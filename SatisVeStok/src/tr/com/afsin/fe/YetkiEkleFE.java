package tr.com.afsin.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.types.YetkilerContract;
import tr.com.afsin.vtk.PersonelVTK;
import tr.com.afsin.vtk.YetkilerVTK;

public class YetkiEkleFE extends JDialog implements FeInterfaces {

	public YetkiEkleFE() {
		initPencere();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPencere() {
		// TODO Auto-generated method stub
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekleme"));
		add(panel);
		setTitle("Yetki Ekleme");
		pack();
		
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new GridLayout (2,2));
		
		JLabel adiLabel = new JLabel("Yetki Adý:",JLabel.RIGHT );
		panel.add(adiLabel);
		JTextField adiField=new JTextField(10);
		panel.add(adiField);
		
	
		
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		JButton iptalButton = new JButton("Ýptal Et");
		panel.add(iptalButton );
		kaydetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract contract = new YetkilerContract();
				contract.setAdi(adiField.getText());
				new YetkilerVTK().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdi()+" adlý personelimiz eklenmiþtir.");
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

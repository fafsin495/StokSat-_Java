package tr.com.afsin.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.MusteriContract;
import tr.com.afsin.vtk.MusteriVTK;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {
		initPencere();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPencere() {
		// TODO Auto-generated method stub
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müþteri Ekle"));
		add(panel);
		
	
		setTitle("Müþteri EKle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5,2));
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		JLabel adiSoyadiLabel = new JLabel("Adý Soyadý : ",JLabel.RIGHT);
		
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldPanel.add(adiSoyadiField);
		JLabel telefonLabel = new JLabel ("Telefon :",JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel ("Þehir Seçin:",JLabel.RIGHT);
		fieldPanel.add(sehirSecLabel );
		JTextField sehirField = new JTextField(15);
		fieldPanel.add(sehirField);
		//JComboBox sehirlerBox = new JComboBox();
		//fieldPanel.add(sehirlerBox);
		
		JLabel adresLabel = new JLabel ("Adres :");
		fieldPanel.add(adresLabel);
		
		
		JTextArea adresArea = new JTextArea(7,1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal Et");
		buttonPanel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = new MusteriContract();
				
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setAdres(adresLabel.getText());
				new MusteriVTK().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdiSoyadi()+" Adlý Müþteri Eklenmiþtir Satýþa Devam Edebilirsinizi");
			}
			
		});
		
		panel.add(fieldPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		
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

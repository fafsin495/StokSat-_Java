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
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.vtk.AccountVTK;
import tr.com.afsin.vtk.PersonelVTK;

public class LoginFE extends JDialog implements FeInterfaces {

	public static JComboBox emailBox;
	
	public LoginFE() {
		initPencere();
		
	}

	@Override
	public void initPencere() {
		JPanel panel= initPanel();
		add(panel);
		
		panel.setBorder(BorderFactory.createTitledBorder("Lütfen Giriþ Yapmak Ýçin Bilgilerinizi Giriniz.."));
		
		setTitle("Lütfen Giriþ Yapýnýz");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel emailLabel = new JLabel("Email :",JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelVTK().GetAll().toArray());
		panel.add(emailBox);
		
		JLabel passwordLabel = new JLabel("Þifreniz :",JLabel.RIGHT);
		panel.add(passwordLabel);
		
		JPasswordField passwordField=new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton=new JButton("Giriþ Yap");
		panel.add(loginButton);
		JButton iptalButton=new JButton("Ýptal");
		panel.add(iptalButton);
		
						/* Mail ve þifre Kontrolü yaptýðýmý if bloðumuz*/
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
				String sifre = passwordField.getText();
				
				if (new AccountVTK().GetPersonelIdVeSifre(contract.getId(),sifre).getId()!=0 ) {
					new AnaPencereFE();
				}else {
					JOptionPane.showMessageDialog(null, "Giriþ Baþarýsýz");
				}
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

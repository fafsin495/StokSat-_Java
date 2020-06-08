package tr.com.afsin.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.afsin.interfaces.FeInterfaces;
import tr.com.afsin.types.KategoriContract;
import tr.com.afsin.vtk.KategoriVTK;

public class KategorDuzenFE extends JDialog implements FeInterfaces {

	public KategorDuzenFE() {
		initPencere();
		
		
	}

	@Override
	public void initPencere() {
		JPanel panel= initPanel();
		add(panel);
		setTitle("Kategori Düzenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//pencere kapatýldýðýnda tüm pencereler kapanmasýn diye
		
		
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel ustPanel = new JPanel(new GridLayout(3,2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		JLabel kategoriAdiLabel = new JLabel("Kategori Adi : ",JLabel.RIGHT);
		ustPanel.add(kategoriAdiLabel);
		JTextField kategoriAdiField = new JTextField(10);
		ustPanel.add(kategoriAdiField);
		JLabel ustKategoriLabel = new JLabel("Üst Kategorisi : ", JLabel.RIGHT);
		ustPanel.add(ustKategoriLabel);
		JComboBox ustKategoriBox = new JComboBox(new KategoriVTK().GetAll().toArray());
		ustPanel.add(ustKategoriBox);
		
		
		JList kategoriList= new JList();
		kategoriList.setListData(new KategoriVTK().GetAll().toArray());
		
		JScrollPane pane = new JScrollPane(kategoriList);
		pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));
		panel.add(ustPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		kategoriList.setSelectedIndex(0);
		
		kategoriAdiField.addKeyListener(new KeyListener () {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				kategoriList.setListData(new KategoriVTK().GetSearchKategori(kategoriAdiField.getText()).toArray());
				kategoriList.setSelectedIndex(0);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
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


























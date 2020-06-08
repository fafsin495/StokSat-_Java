package tr.com.afsin.utilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import tr.com.afsin.fe.KategorDuzenFE;
import tr.com.afsin.fe.KategoriEkleFe;
import tr.com.afsin.fe.LoginFE;
import tr.com.afsin.fe.MusteriEkleFE;
import tr.com.afsin.fe.PersonelEkleFE;
import tr.com.afsin.fe.SifreIslemleriFE;
import tr.com.afsin.fe.UrunEkleFE;
import tr.com.afsin.fe.YetkiEkleFE;
import tr.com.afsin.main.DarkRun;
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.vtk.AccountVTK;


public class MenulerCom {

	private static final ActionListener ActionListener = null;

	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		// TODO Auto-generated method stub
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("��k��");
		dosyaMenu.add(cikisItem);

		/* �r�nler MEn�s� */

		JMenu urunlerMenu = new JMenu("�r�nler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("�r�n Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori D�zenle");
		urunlerMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new KategorDuzenFE();
						
					}
					
				});
				
			}
			
		});
		
		/* Personel MEn�s� */
		
		JMenu personellerMenu = new JMenu("Personel ��lemleri");
		bar.add(personellerMenu);

		JMenuItem personelEkleItem = new JMenuItem("Persone Ekle");
		personellerMenu.add(personelEkleItem);

		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);

		JMenuItem sifreBelirleItem = new JMenuItem("Personel Konum ��lemleri");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();
		
		

		
						/*M��teri Men�s�	*/
		JMenu musterilerMenu = new JMenu("M��teriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("M��teri Ekle");
		musterilerMenu.add(musteriEkleItem);
						
		
					/*Yetkilere  g�re men�y� gizlemek*/
		PersonelContract contract =(PersonelContract) LoginFE.emailBox.getSelectedItem();
		if(new AccountVTK().GetYetkiId(contract.getId()).getYetkiId()==2) {
			personellerMenu.hide();
		}else if (new AccountVTK().GetYetkiId(contract.getId()).getYetkiId()==3) {
			musterilerMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}

		urunEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new UrunEkleFE();

					}

				});
			}
		});

		
		cikisItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		kategoriEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFe();
			}

		});
		personelEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new PersonelEkleFE();

					}
				});
			}
		});
		
		yetkiEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new YetkiEkleFE();

					}
				});
			}
		});
		
		sifreBelirleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SifreIslemleriFE();
			}
		});
		musteriEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new MusteriEkleFE();

					}
				});
			}
		});
		
		

		return bar;
	}

}

package tr.com.afsin.vtk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.afsin.complex.types.StokContractComplex;
import tr.com.afsin.complex.types.StokContractTotalComplex;
import tr.com.afsin.core.ObjectHelper;
import tr.com.afsin.interfaces.VTKInterfaces;
import tr.com.afsin.types.KategoriContract;
import tr.com.afsin.types.StokContract;

public class StokVTK extends ObjectHelper implements VTKInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Stok (PersonelId,UrunId,Tarih,Adet) VALUES(" + entity.getPersonelId()
					+ "," + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
			statement.close();
			connection.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * SELECT stok.Id,AdiSoyadi,Adet,stok.Tarih FROM stok
	   LEFT JOIN urunler on stok.UrunId=urunler.Id
	   LEFT JOIN personel on stok.PersonelId=personel.id
	 */
	
	
	public List<StokContractComplex> GetAllStok(){
		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT stok.Id,AdiSoyadi,urunler.Adi, Adet,stok.Tarih FROM stok " + 
					"	   LEFT JOIN urunler on stok.UrunId=urunler.Id " + 
					"	   LEFT JOIN personel on stok.PersonelId=personel.id ORDER BY stok.Id DESC ");
			while (rs.next()) {
				contract = new StokContractComplex();
				contract.setId(rs.getInt("Id"));
				contract.setPersonelAdi(rs.getString("AdiSoyadi"));
				contract.setUrunAdi(rs.getString("urunler.Adi"));
				contract.setAdet(rs.getInt("Adet"));
				contract.setTarih(rs.getString("stok.Tarih"));
				contract.setUrunAdi(rs.getString("Adi"));
				contract.setAdet(rs.getInt("Adet"));
				
				
				datacontract.add(contract);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	
	
	
	
	public List<StokContractTotalComplex> GetTotalStok(){
		List<StokContractTotalComplex> datacontract = new ArrayList<StokContractTotalComplex>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		StokContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(Adet) as toplam,stok.Id,AdiSoyadi,Adi,Adet,stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId= personel.Id   GROUP BY UrunId" );
			while (resultSet.next()) {
				contract = new StokContractTotalComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("urunler.Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));
				contract.setToplam(resultSet.getInt("toplam"));
				
				
				datacontract.add(contract);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	
	
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

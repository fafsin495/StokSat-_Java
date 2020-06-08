package tr.com.afsin.vtk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.afsin.core.ObjectHelper;
import tr.com.afsin.interfaces.VTKInterfaces;
import tr.com.afsin.types.KategoriContract;
import tr.com.afsin.types.UrunlerContract;

public class UrunlerVTK extends ObjectHelper implements VTKInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler (Adi,KategoriId,Tarih,Fiyat) VALUES('" + entity.getAdi() + "',"
					+ entity.getKategoriId() + ",'" + entity.getTarih() + "'," + entity.getFiyat() + ")");

			statement.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<UrunlerContract> GetAll() {
		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while (resultSet.next()) {
				contract = new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				contract.setTarih(resultSet.getString("Tarih"));
				datacontract.add(contract);
				
				System.out.println(resultSet.getString("Adi"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public UrunlerContract Delete(UrunlerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

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

public class KategoriVTK extends ObjectHelper implements VTKInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Kategori (Adi,ParentId) VALUES('" + entity.getAdi() + "',"
					+ entity.getParentId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {
		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	public List<KategoriContract> GetAllParentId() {
		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE parentId=0");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public KategoriContract Delete(KategoriContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(KategoriContract entity) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE Kategori SET Adi='" + entity.getAdi() + "', ParentId="
					+ entity.getParentId() + " WHERE id=" + entity.getId() + " ");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	@Override
	public List<KategoriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<KategoriContract> GetSearchKategori(String kategoriAdi){
		List<KategoriContract> dataContract= new ArrayList<KategoriContract>();
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet baglan = statement.executeQuery("SELECT *FROM Kategori Where Adi LIKE '"+"%"+kategoriAdi+"%"+"'");
			while(baglan.next()) {
				KategoriContract contract= new KategoriContract();
				contract.setAdi(baglan.getString("Adi"));
				contract.setParentId(baglan.getInt("ParentId"));
				dataContract.add(contract);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

}

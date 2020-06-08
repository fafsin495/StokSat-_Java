package tr.com.afsin.vtk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.afsin.core.ObjectHelper;
import tr.com.afsin.interfaces.VTKInterfaces;
import tr.com.afsin.types.PersonelContract;
import tr.com.afsin.types.YetkilerContract;

public class YetkilerVTK extends ObjectHelper implements VTKInterfaces<YetkilerContract> {

	@Override
	public void Insert(YetkilerContract entity) {
		// TODO Auto-generated method stub
Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES('"+entity.getAdi()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<YetkilerContract> GetAll() {
		List<YetkilerContract> datacontract = new ArrayList<YetkilerContract>();
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		YetkilerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
			while (resultSet.next()) {
				contract = new YetkilerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));

				
				datacontract.add(contract);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return datacontract;
	}
	

	@Override
	public YetkilerContract Delete(YetkilerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(YetkilerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<YetkilerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

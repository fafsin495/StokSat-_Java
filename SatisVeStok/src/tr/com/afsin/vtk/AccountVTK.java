package tr.com.afsin.vtk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.afsin.core.ObjectHelper;
import tr.com.afsin.interfaces.VTKInterfaces;
import tr.com.afsin.types.AccountsContract;

public class AccountVTK extends ObjectHelper implements VTKInterfaces<AccountsContract> {

	@Override
	public void Insert(AccountsContract entity) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Accounts (PersonelId,YetkiId,Sifre) VALUES(" + entity.getPersonelId()
					+ "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
					/*DB den login giriþi için gerekli bilgilerin sorgusunu yapýyoruz*/	
	public AccountsContract GetPersonelIdVeSifre(int personelId , String sifre){
		
		AccountsContract contract = new AccountsContract();
		
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+" AND Sifre ='"+sifre.trim()+"' ");
			
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				
				
			}
			sorgu.close();
			baglanti.close();
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return contract;
	}
	
	
	
	public AccountsContract GetYetkiId(int personelId ){
		
		AccountsContract contract = new AccountsContract();
		
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+" ");
			
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				
				
			}
			sorgu.close();
			baglanti.close();
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return contract;
	}
	
	
	
	
	@Override
	public List<AccountsContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountsContract Delete(AccountsContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AccountsContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

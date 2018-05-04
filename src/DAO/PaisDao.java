package DAO;

import Model.Pais;
import Conexao.ConnectionFactory;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaisDao {
	
	public int IncluirPais(Pais pais){
		String sqlInsert = "INSERT INTO paises (nome, populacao, area) VALUES(?, ?, ?)";
		//Iniciar Conexao com o banco
		//Try para verificar se nao ocorre exeptions
		try {
			
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlInsert);
			stm.setString(1,pais.getNome());
			stm.setLong(2,pais.getPopulacao());
			stm.setDouble(3,pais.getArea());
			
			stm.execute();
			
			//Essa parte e necessaria para poder inserir um valor no ID para mostrar no HTML gerado pelo Controller
			String sqlSelect = "SELECT LAST_INSERT_ID() as id";
			stm = conn.prepareStatement(sqlSelect);
			
			ResultSet rs = stm.executeQuery();
			
			try {
				if(rs.next()) {
					pais.setId(rs.getInt("id"));
				}
			}catch(SQLException e ) {
				System.out.println(e);
			}
			
		}catch(SQLException e) {
			
			System.out.println(e);
		}
		return pais.getId();
		
	}
	
	
	public void AtualizarPais(Pais pais) {
		
		String sqlAlter = "UPDATE paises SET nome = ?, populacao = ?, area =?  WHERE id=?";
		System.out.println(sqlAlter);
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlAlter);
			 
			 stm.setString(1, pais.getNome());
			 stm.setLong(2, pais.getPopulacao());
			 stm.setDouble(3, pais.getArea());
			 stm.setInt(4, pais.getId());
			 stm.execute();
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void DeletarPais(Pais pais) {
		String sqlDelete = "DELETE from paises WHERE id = ?";
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, pais.getId());
			stm.execute();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void CarregarPais(Pais pais) {
		String sqlSelect = "Select nome, area, populacao FROM paises WHERE id = ?";
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, pais.getId());
			
			//responsavel por retornar o valor que a query tras
			ResultSet rs = stm.executeQuery();
			
			try {
				if(rs.next()) {
					// o rs e necessario para poder retornar o resultado da query
					pais.setNome(rs.getString("nome"));
					pais.setArea(rs.getDouble("area"));
					pais.setPopulacao(rs.getLong("populacao"));
				}else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		

	}
	
	
	public ArrayList<Pais> ListarTodos() {
		String sqlSelect = "Select * from paises";
		
		ArrayList<Pais> array = new ArrayList<Pais>();
		try {
			Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);
			
			ResultSet rs = stm.executeQuery();
			
			try {
				while(rs.next()) {
					Pais p = new Pais();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setArea(rs.getDouble("area"));
					p.setPopulacao(rs.getLong("populacao"));
					array.add(p);
					System.out.println("entrei no array");
				}
			}catch(Exception e) {
				Pais p = new Pais();
				p.setId(-1);
				p.setNome(null);
				p.setPopulacao(0);
				p.setArea(0);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return array;
	}
	
	
	public void getMenorArea(Pais pais) {
		try {
		String sqlQuery = "Select * from paises where area = (Select Min(area) from paises)";
		Connection conn  = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		
		//Pegando o retorno
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			pais.setId(rs.getInt("id"));
			pais.setNome(rs.getString("nome"));
			pais.setArea(rs.getDouble("area"));
			pais.setPopulacao(rs.getLong("populacao"));
		}
		
		}catch(SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public void getHabitantes(Pais pais) {
		try {
		String sqlQuery =  "Select * from paises where pop_pais = (Select Max(pop_pais) from paises)";
		Connection conn  = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		
		//Pegando o retorno
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			pais.setId(rs.getInt("id"));
			pais.setNome(rs.getString("nome"));
			pais.setArea(rs.getDouble("area"));
			pais.setPopulacao(rs.getLong("populacao"));
		}
		
		}catch(SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	
	public String[] vetorPaises() {
		
		String[] vet = new String[3];
		int cont = 0;
		
		try {
		String sqlQuery = "Select nome_pais from paises order by nome_pais";
		Connection conn = ConnectionFactory.obtemConexao();
		
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next() && cont < 3) {
			vet[cont] = rs.getString("nome");
			cont++;
		}
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vet;
	
	}
}
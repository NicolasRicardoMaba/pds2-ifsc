package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pessoa;

public class PessoaDAO {

	public boolean inserir(Pessoa p) {

		Conexao c = Conexao.getInstancia();
		
		Connection con = c.conectar();
		
		
		String query = "INSERT INTO pessoa "
				+ "(id_pessoa, primeiro_nome) "
				+ "VALUES (?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, p.getId());
			ps.setString(2, p.getPrimeiro_nome());
			
			ps.executeUpdate();
			
			c.fecharConexao();

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

		public boolean excluir(Pessoa p) {
			return true;
		}
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Pessoa> listar() {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

	
		ArrayList<Pessoa> clientes = new ArrayList<Pessoa>();
		String comando = "select * from pessoa";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = con.prepareStatement(comando);
			 rs = ps.executeQuery();

			while (rs.next()) {
				Integer idade = rs.getInt("pessoa_idade");
				String nome = rs.getString("pessoa_primeiro_nome");
				Integer id = rs.getInt("pessoa_id");
				Pessoa cliente = new Pessoa();
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return clientes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
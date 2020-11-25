package br.edu.faculdadedelta.projetovendajsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetovendajsf.modelo.Venda;
import br.edu.faculdadedelta.projetovendajsf.util.Conexao;

public class VendaDAO {

	// C R U D
	
	public void incluir(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas (produto_venda, quantidade_produto, valor_produto, data_venda) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getDescProduto().trim());
		ps.setString(1, venda.getPaciente().trim());
		ps.setDouble(3, venda.getValor());
		ps.setDouble(3, venda.getQtdExames());
		ps.setDate(4, new java.sql.Date(venda.getInicioProcedimento().getTime()));
		ps.setDate(4, new java.sql.Date(venda.getFimProcedimento().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE vendas SET produto_venda = ?, "
				+ " desc_prod = ?, "
				+ " valor_produto = ?, "
				+ " data_inicio  = ? "
				+ " data_fim  = ? "
				+ " paciente  = ? "
				+ " qtdExames  = ? "
				+ " WHERE id_venda = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getDescProduto().trim());
		ps.setString(1, venda.getPaciente().trim());
		ps.setDouble(3, venda.getValor());
		ps.setDouble(3, venda.getQtdExames());
		ps.setDate(4, new java.sql.Date(venda.getInicioProcedimento().getTime()));
		ps.setDate(4, new java.sql.Date(venda.getFimProcedimento().getTime()));
		ps.setLong(5, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Venda> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_venda, produto_venda, quantidade_produto, "
				+ " valor_produto, data_venda FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Venda> listaRetorno = new ArrayList<Venda>();
 		
		while (rs.next()) {
			Venda venda = new Venda();
			venda.setId(rs.getLong("id_venda"));
			venda.setDescProduto(rs.getString("desc_prod").trim());
			venda.setValor(rs.getDouble("valor_produto"));
			venda.setQtdExames(rs.getDouble("qtdExames"));
			venda.setInicioProcedimento(rs.getDate("data_inicio"));
			venda.setFimProcedimento(rs.getDate("data_fim"));
			venda.setPaciente(rs.getString("paciente").trim());
			listaRetorno.add(venda);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return listaRetorno;
	}
}

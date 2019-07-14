package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void salvar(Contato contato) {
		String sql = "INSERT INTO CONTATOS(ID,NOME,IDADE,DATACADASTRO)" + "VALUES(?,?,?,?)";

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		
		try {
			con = ConnectionFactory.createConnectionToPostgres();
			pstm = con.prepareStatement("SELECT ID FROM CONTATOS ORDER BY ID DESC LIMIT 1  ");
			pstm.execute();
			resultSet = pstm.executeQuery();
			Integer contador = 0;
			Integer idQuery = 0;
			while (resultSet.next()) {
				contador = contador++;
				idQuery = resultSet.getInt(1) + 1;
			}
			
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idQuery);
			pstm.setString(2, contato.getNome());
			pstm.setInt(3, contato.getIdade());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void deletarPorId(int id) {
		String sql = "DELETE FROM CONTATOS WHERE ID = ?";

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ConnectionFactory.createConnectionToPostgres();

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void atualizar(Contato contato) {
		String sql = "UPDATE CONTATOS SET NOME = ?, IDADE = ?, DATACADASTRO = ?" + " WHERE id = ?";

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ConnectionFactory.createConnectionToPostgres();

			pstm = con.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getId());
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> getContatos() {
		String sql = "SELECT * FROM CONTATOS ORDER BY ID";
		List<Contato> contatos = new ArrayList<Contato>();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			con = ConnectionFactory.createConnectionToPostgres();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				contato.setId(rset.getInt("ID"));
				contato.setNome(rset.getString("NOME"));
				contato.setIdade(rset.getInt("IDADE"));
				contato.setDataCadastro(rset.getDate("DATACADASTRO"));
				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

}

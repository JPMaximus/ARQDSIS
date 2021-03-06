package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;

public class FilmeDAO {

	public int inserirFilme(Filme filme) throws IOException {
		int id = -1;
		String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
				+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, filme.getTitulo());
			pst.setString(2, filme.getDescricao());
			pst.setString(3, filme.getDiretor());
			pst.setString(4, filme.getPosterPath());
			pst.setDouble(5, filme.getPopularidade());
			if (filme.getDataLancamento() != null) {
				pst.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
			} else {
				pst.setDate(6, null);
			}
			pst.setInt(7, filme.getGenero().getId());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Filme buscarFilme(int id) throws IOException {
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f.popularidade, f.data_lancamento, f.id_genero, g.id, g.nome from filme f, genero g "
				+ "where f.id_genero = g.id AND f.id = " + id;
		Filme filme = new Filme();
		Genero genero = new Genero();
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {
		
			while (rs.next()) {
				filme.setId(rs.getInt("f.id"));
				filme.setTitulo(rs.getString("f.titulo"));
				filme.setDescricao(rs.getString("f.descricao"));
				filme.setDiretor(rs.getString("f.diretor"));
				filme.setPosterPath(rs.getString("f.posterpath"));
				filme.setPopularidade(rs.getDouble("f.popularidade"));
				filme.setDataLancamento(rs.getDate("f.data_lancamento"));
				genero = new Genero();
				genero.setId(rs.getInt("f.id_genero"));
				genero.setNome(rs.getString("g.nome"));
				filme.setGenero(genero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		return filme;
	}

	public ArrayList<Filme> listarFilmes(String chave) throws IOException {
		ArrayList<Filme> lista = new ArrayList<>();
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f. popularidade, f.data_lancamento, f.id_genero, g.nome " + "from filme f, genero g "
				+ "where f.id_genero = g.id and upper(f.titulo) like ?";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, "%" + chave.toUpperCase() + "%");

			try (ResultSet rs = pst.executeQuery();) {

				Filme filme;
				Genero genero;
				while (rs.next()) {
					filme = new Filme();
					filme.setId(rs.getInt("f.id"));
					filme.setTitulo(rs.getString("f.titulo"));
					filme.setDescricao(rs.getString("f.descricao"));
					filme.setDiretor(rs.getString("f.diretor"));
					filme.setPosterPath(rs.getString("f.posterpath"));
					filme.setDataLancamento(rs.getDate("f.data_lancamento"));
					genero = new Genero();
					genero.setId(rs.getInt("f.id_genero"));
					genero.setNome(rs.getString("g.nome"));
					filme.setGenero(genero);
					lista.add(filme);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		return lista;
	}

	public ArrayList<Filme> listarFilmes() throws IOException {
		ArrayList<Filme> lista = new ArrayList<>();
		String sql = "select f.id, f.titulo, f.descricao, f.diretor, f.posterpath, "
				+ "f. popularidade, f.data_lancamento, f.id_genero, g.nome " + "from filme f, genero g "
				+ "where f.id_genero = g.id";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			Filme filme;
			Genero genero;
			while (rs.next()) {
				filme = new Filme();
				filme.setId(rs.getInt("f.id"));
				filme.setTitulo(rs.getString("f.titulo"));
				filme.setDescricao(rs.getString("f.descricao"));
				filme.setDiretor(rs.getString("f.diretor"));
				filme.setPosterPath(rs.getString("f.posterpath"));
				filme.setDataLancamento(rs.getDate("f.data_lancamento"));
				genero = new Genero();
				genero.setId(rs.getInt("f.id_genero"));
				genero.setNome(rs.getString("g.nome"));
				filme.setGenero(genero);
				lista.add(filme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}

	public int editarFilme(Filme filme) throws IOException {
		String sql = "UPDATE filme set titulo=?, descricao=?, diretor=?, posterPath=?, popularidade=?, data_Lancamento=?, id_genero=? "
				+ "where id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, filme.getTitulo());
			pst.setString(2, filme.getDescricao());
			pst.setString(3, filme.getDiretor());
			pst.setString(4, filme.getPosterPath());
			pst.setDouble(5, filme.getPopularidade());
			if (filme.getDataLancamento() != null) {
				pst.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
			} else {
				pst.setDate(6, null);
			}
			pst.setInt(7, filme.getGenero().getId());
			pst.setInt(8, filme.getId());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return filme.getId();
	}
	
	public boolean excluirFilme(int id) throws  IOException {
		String sql = "DELETE FROM filme WHERE id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);){
			pst.setInt(1, id);
			pst.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}

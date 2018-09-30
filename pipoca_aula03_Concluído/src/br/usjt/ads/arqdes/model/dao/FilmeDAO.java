package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
@Repository
public class FilmeDAO {

	Connection conn;
	
	@Autowired
	public FilmeDAO(DataSource data) throws IOException{
		try {
			conn = data.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
	}
	
	public int inserirFilme(Filme filme) throws IOException {
		int id = -1;
		String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
				+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";

		try (PreparedStatement pst = conn.prepareStatement(sql);) {

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
			try (PreparedStatement pst1 = conn.prepareStatement(query); 
					ResultSet rs = pst1.executeQuery();) {

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

		try (	PreparedStatement pst = conn.prepareStatement(sql);
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
		try (PreparedStatement pst = conn.prepareStatement(sql);) {

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
		try (	PreparedStatement pst = conn.prepareStatement(sql);
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

	public ArrayList<ArrayList<Filme>> listarFilmesPop() throws IOException {
		ArrayList<ArrayList<Filme>> listas = new ArrayList<>();
		String sql1 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where popularidade >= 0 && popularidade <= 25";
		String sql2 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where popularidade >= 26 && popularidade <= 50";
		String sql3 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where popularidade >= 51 && popularidade <= 75";
		String sql4 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where popularidade >= 76 && popularidade <= 100";
		
		//Cria o array de lista de filmes
		for(int i = 1; i <= 4; i++) {
            ArrayList<Filme> listaPop = new ArrayList<>();
            String sql = (i == 1 ? sql1 : (i == 2 ? sql2 : (i == 3 ? sql3 : sql4))); 

            try (PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();) {

                Filme filme;
                while (rs.next()) {
                    filme = new Filme();
                    filme.setId(rs.getInt("id"));
                    filme.setTitulo(rs.getString("titulo"));
                    filme.setDescricao(rs.getString("descricao"));
                    filme.setDiretor(rs.getString("diretor"));
                    filme.setPosterPath(rs.getString("posterpath"));
                    filme.setDataLancamento(rs.getDate("data_lancamento"));
                    listaPop.add(filme);
                }

                listas.add(listaPop);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

		return listas;
	}

	public ArrayList<ArrayList<Filme>> listarFilmesData() throws IOException {
		ArrayList<ArrayList<Filme>> listas = new ArrayList<>();
		
		String sql1 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where data_Lancamento BETWEEN DATE_ADD(NOW(), INTERVAL -1 MONTH) AND "
				+ "NOW()";
		String sql2 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where data_Lancamento BETWEEN  DATE_ADD(NOW(), INTERVAL -1 YEAR) AND "
				+ "DATE_ADD(NOW(), INTERVAL -1 MONTH)";
		String sql3 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where data_Lancamento BETWEEN DATE_ADD(NOW(), INTERVAL -10 YEAR) AND "
				+ "DATE_ADD(NOW(), INTERVAL -1 YEAR)";
		String sql4 = "select id, titulo, descricao, diretor, posterpath, popularidade, data_lancamento, id_genero from filme "
				+ "where data_Lancamento < DATE_ADD(NOW(), INTERVAL -10 YEAR)";
		
		for(int i = 1; i <= 4; i++) {
			ArrayList<Filme> listaPeriodo = new ArrayList<>();
			String sql = (i == 1 ? sql1 : (i == 2 ? sql2 : (i == 3 ? sql3 : sql4)));

            try (PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();) {

                Filme filme;
                while (rs.next()) {
                    filme = new Filme();
                    filme.setId(rs.getInt("id"));
                    filme.setTitulo(rs.getString("titulo"));
                    filme.setDescricao(rs.getString("descricao"));
                    filme.setDiretor(rs.getString("diretor"));
                    filme.setPosterPath(rs.getString("posterpath"));
                    filme.setDataLancamento(rs.getDate("data_lancamento"));
                    listaPeriodo.add(filme);
                }

                listas.add(listaPeriodo);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
	
		return listas;
	}

	public int editarFilme(Filme filme) throws IOException {
		String sql = "UPDATE filme set titulo=?, descricao=?, diretor=?, posterPath=?, popularidade=?, data_Lancamento=?, id_genero=? "
				+ "where id = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql);) {

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

	public boolean excluirFilme(int id) throws IOException {
		String sql = "DELETE FROM filme WHERE id = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setInt(1, id);
			pst.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

package br.usjt.ads.arqdes.model.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.ads.arqdes.model.dao.FilmeDAO;
import br.usjt.ads.arqdes.model.entity.Filme;
@Service
public class FilmeService {
	@Autowired
	private FilmeDAO dao;
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}

	public ArrayList<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}

	public ArrayList<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
	
	public ArrayList<ArrayList<Filme>> listarFilmesPop() throws IOException{
		return dao.listarFilmesPop();
	}
	
	public ArrayList<ArrayList<Filme>> listarFilmesData() throws IOException{
		return dao.listarFilmesData();
	}

	public Filme editarFilme(Filme filme) throws IOException{
		int id = dao.editarFilme(filme);
		filme.setId(id);
		return filme;
	}
	
	public boolean excluirFilme(int id) throws IOException{
		return dao.excluirFilme(id);
	}

}

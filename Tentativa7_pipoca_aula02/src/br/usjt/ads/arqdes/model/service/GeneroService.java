package br.usjt.ads.arqdes.model.service;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.arqdes.model.dao.GeneroDAO;
import br.usjt.ads.arqdes.model.entity.Genero;

public class GeneroService {
	private GeneroDAO dao;
	
	public GeneroService() {
		this.dao = new GeneroDAO();
	}
	
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}
	
	public ArrayList<Genero> listarGeneros() throws IOException{
		return dao.listarGeneros();
	}

}

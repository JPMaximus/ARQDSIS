package br.usjt.ads.arqdes.model.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.ads.arqdes.model.dao.GeneroDAO;
import br.usjt.ads.arqdes.model.entity.Genero;
@Service
public class GeneroService {
	@Autowired
	private GeneroDAO dao;
	
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}
	
	public ArrayList<Genero> listarGeneros() throws IOException{
		return dao.listarGeneros();
	}

}

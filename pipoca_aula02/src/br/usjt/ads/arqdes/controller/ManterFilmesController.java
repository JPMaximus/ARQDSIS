package br.usjt.ads.arqdes.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
import br.usjt.ads.arqdes.model.service.FilmeService;
import br.usjt.ads.arqdes.model.service.GeneroService;

/**
 * Servlet implementation class ManterFilmesController
 */
@WebServlet("/manterfilmes.do")
public class ManterFilmesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		RequestDispatcher dispatcher;
		FilmeService fService;
		GeneroService gService;
		Filme filme;
		HttpSession session;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Genero> generos;
		ArrayList<Filme> lista;

		// Pega os atributos do filme utilizado na ação
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String diretor = request.getParameter("diretor");
		String posterPath = request.getParameter("posterPath");
		String popularidade = request.getParameter("popularidade") == null
				|| request.getParameter("popularidade").length() == 0 ? "0" : request.getParameter("popularidade");
		String dataLancamento = request.getParameter("dataLancamento") == null
				|| request.getParameter("dataLancamento").length() == 0 ? "" : request.getParameter("dataLancamento");
		String idGenero = request.getParameter("genero.id");
		String chave = request.getParameter("data[search]");

		switch (acao) {
		case "novo":
			//Cria Sessão para setar o comboBox Genero
			gService = new GeneroService();
			generos = gService.listarGeneros();
			session = request.getSession();
			session.setAttribute("generos", generos);
			
			//Manda para tela criar Filmes
			dispatcher = request.getRequestDispatcher("CriarFilme.jsp");
			dispatcher.forward(request, response);
			break;

		case "criar":
			//Seta atributos do filme
			fService = new FilmeService();
			filme = new Filme();
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				filme.setDataLancamento(formatter.parse(dataLancamento));
			} catch (ParseException e) {

				e.printStackTrace();
				filme.setDataLancamento(null);
			}

			filme.setPopularidade(Double.parseDouble(popularidade));
			filme.setPosterPath(posterPath);

			//Seta atributos do genero
			gService = new GeneroService();
			Genero genero = new Genero();
			genero.setId(Integer.parseInt(idGenero));
			genero.setNome(gService.buscarGenero(genero.getId()).getNome());
			filme.setGenero(genero);
			
			//Insere o Filme
			filme = fService.inserirFilme(filme);
			request.setAttribute("filme", filme);

			//Manda para tela VisualizarFilme
			dispatcher = request.getRequestDispatcher("VisualizarFilme.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "reiniciar":
			//Apaga a lista da Sessão
			session = request.getSession();
			session.setAttribute("lista", null);
			
			//Manda para tela ListarFilmes
			dispatcher = request.getRequestDispatcher("ListarFilmes.jsp");
			dispatcher.forward(request, response);
			break;

		case "listar":
			session = request.getSession();
			fService = new FilmeService();
			
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			dispatcher = request.getRequestDispatcher("ListarFilmes.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "listarfilmesgenero":
			session = request.getSession();
			fService = new FilmeService();
		
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			
			gService = new GeneroService();
			generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			
			dispatcher = request.getRequestDispatcher("ListarFilmesGenero.jsp");
			dispatcher.forward(request, response);
			break;

		case "editar":
			//Busca o filme e popula o genero
			fService = new FilmeService();
			gService = new GeneroService();
			filme = fService.buscarFilme(Integer.parseInt(request.getParameter("id")));
			generos = gService.listarGeneros();
			session = request.getSession();
			session.setAttribute("generos", generos);
			request.setAttribute("filme", filme);

			//Manda para tela de EditarFilmes
			dispatcher = request.getRequestDispatcher("EditarFilme.jsp");
			dispatcher.forward(request, response);
			break;

		case "alterar":
			// Instanciando as classes
			filme = new Filme();
			genero = new Genero();
			fService = new FilmeService();
			gService = new GeneroService();

			// Alterar os atributos do filme
			filme.setId(Integer.parseInt(request.getParameter("id")));
			filme.setTitulo(titulo);
			filme.setPopularidade(Double.parseDouble(popularidade));
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);
			filme.setPosterPath(posterPath);
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				filme.setDataLancamento(formatter.parse(dataLancamento));
			} catch (ParseException e) {

				e.printStackTrace();
				filme.setDataLancamento(null);
			}
			genero.setId(Integer.parseInt(idGenero));
			genero.setNome(gService.buscarGenero(genero.getId()).getNome());
			filme.setGenero(genero);

			//Edita o Filme
			fService.editarFilme(filme);
			request.setAttribute("filme", filme);
			
			//Manda pra Visualizar
			dispatcher = request.getRequestDispatcher("VisualizarFilme.jsp");
			dispatcher.forward(request, response);
			break;

		case "visualizar":
			//Busca o filme
			fService = new FilmeService();
			filme = fService.buscarFilme(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("filme", filme);

			//Manda pra Visualizar
			dispatcher = request.getRequestDispatcher("VisualizarFilme.jsp");
			dispatcher.forward(request, response);
			break;

		case "excluir":
			//Excluí o filme
			fService = new FilmeService();
			fService.excluirFilme(Integer.parseInt(request.getParameter("id")));
			
			//Atualiza lista
			session = request.getSession();
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			
			//Manda para tela ListarFilmes
			dispatcher = request.getRequestDispatcher("ListarFilmes.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

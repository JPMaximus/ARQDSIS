package br.usjt.ads.arqdes.controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.usjt.ads.arqdes.model.entity.Filme;
import br.usjt.ads.arqdes.model.entity.Genero;
import br.usjt.ads.arqdes.model.service.FilmeService;
import br.usjt.ads.arqdes.model.service.GeneroService;

@Controller
public class ManterFilmesController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@Autowired
	private FilmeService fService;
	@Autowired
	private GeneroService gService;
	
	@RequestMapping("/")
	public String inicio() {
		return "index";
	}

	@RequestMapping("/inicio")
	public String inicio1() {
		return "index";
	}

	@RequestMapping("/novo_filme")
	public String novoFilme(HttpSession session) {
		try {
			ArrayList<Genero> generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			return "CriarFilme";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/inserir_filme") // BindingResult vem o erro // @Valid obriga filme ser valido
	public String inserirFilme(@Valid Filme filme, BindingResult result, Model model) {
		try {
			if (!result.hasFieldErrors()) {
				Genero genero = gService.buscarGenero(filme.getGenero().getId());
				filme.setGenero(genero);
				model.addAttribute("filme", filme);
				fService.inserirFilme(filme);
				return "VisualizarFilme";
			} else {
				return "CriarFilme";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarFilmes";
	}

	@RequestMapping("/buscar_filmes")
	public String buscarFilmes(HttpSession session, @RequestParam String chave) {
		try {
			ArrayList<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);

			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/listarfilme_genero")
	public String listarFilmeGenero(HttpSession session) {
		try {
			ArrayList<Filme> lista = fService.listarFilmes();
			ArrayList<Genero> generos = gService.listarGeneros();
			session.setAttribute("lista", lista);
			session.setAttribute("generos", generos);
			
			return "ListarFilmesGenero";
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return "index";
		
	}
	
	@RequestMapping("/listarfilme_pop")
	public String listarFilmePop(HttpSession session){
		try {
			ArrayList<ArrayList<Filme>> listas = fService.listarFilmesPop();
			
			session.setAttribute("listapop1", listas.get(0));
			session.setAttribute("listapop2", listas.get(1));
			session.setAttribute("listapop3", listas.get(2));
			session.setAttribute("listapop4", listas.get(3));
			
			return "ListarFilmesPopularidade";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/listarfilme_data")
	public String listarFilmeData(HttpSession session) {
		
		try {
			ArrayList<ArrayList<Filme>> listas = fService.listarFilmesData();
			
			session.setAttribute("listaperiodo1", listas.get(0));
			session.setAttribute("listaperiodo2", listas.get(1));
			session.setAttribute("listaperiodo3", listas.get(2));
			session.setAttribute("listaperiodo4", listas.get(3));
			
			return "ListarFilmesData";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	@RequestMapping("/editar_filme")
	public String editarFilme(Model model, @RequestParam String id) {
		try {
			Filme filme2 = fService.buscarFilme(Integer.parseInt(id));
			ArrayList<Genero> generos = gService.listarGeneros();
			model.addAttribute("filme", filme2);
			model.addAttribute("generos", generos);

			return "EditarFilme";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "index";

	}

	@RequestMapping("/alterar_filme")
	public String alterarFilme(Model  model, @Valid Filme filme, BindingResult result) {
		try {
			
			if(!result.hasFieldErrors()) {
				fService.editarFilme(filme);

				model.addAttribute("filme", filme);

				return "VisualizarFilme";
			}else {
				return "EditarFilme";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "index";

	}

	@RequestMapping("/visualizar_filme")
	public String visualizarFilme(HttpSession session, @RequestParam String id) {

		try {
			Filme filme2 = fService.buscarFilme(Integer.parseInt(id));
			session.setAttribute("filme", filme2);

			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "index";
	}

	@RequestMapping("/excluir_filme")
	public String excluirFilme(HttpSession session, @RequestParam String id) {

		try {
			fService.excluirFilme(Integer.parseInt(id));

			ArrayList<Filme> lista;
			lista = fService.listarFilmes();
			session.setAttribute("lista", lista);

			return "ListarFilmes";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

}

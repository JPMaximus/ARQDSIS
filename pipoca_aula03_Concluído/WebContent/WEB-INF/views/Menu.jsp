<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="inicio">Hora da Pipoca</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="listar_filmes">Filmes</a></li>
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catálogo</a>
                    		<ul class="dropdown-menu">
  						  <li><a class="dropdown-item" href="listarfilme_genero">por Gênero</a></li>
  						   <li><a class="dropdown-item" href="listarfilme_pop">por Popularidade</a></li>
  						   <li><a class="dropdown-item" href="listarfilme_data">Lançamentos</a></li>
						</ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
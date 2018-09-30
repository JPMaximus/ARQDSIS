<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Editar Filme</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Editar Filme</h3>
		<!-- Formulario para alteração de Filmes -->
		<form action="alterar_filme" method="post">
			<!-- area de campos do form -->
			<input type="hidden" name="id" value="${filme.id }" />
			<div class="row">
				<div class="form-group col-md-4">
					<label for="genero">Gênero</label> <select class="form-control"
						name="genero.id" id="genero">
						<c:forEach var="genero" items="${generos}">
							<c:if test="${filme.getGenero().getId() == genero.getId() }">
								<option value="${genero.id}" selected>${genero.nome}</option>
							</c:if>
							<c:if test="${filme.getGenero().getId() != genero.getId() }">
								<option value="${genero.id}">${genero.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<div class="form-group col-md-8">
					<label for="titulo">Titulo</label>
					<f:errors path="filme.titulo" cssStyle="color:red" />
					<input type="text" class="form-control" name="titulo" id="titulo"
						required maxlength="100" placeholder="Nome do Filme"
						value="${filme.titulo}">
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label for="descricao">Descrição</label>
					<f:errors path="filme.descricao" cssStyle="color:red" />
					<textarea class="form-control rounded-0" id="descricao"
						name="descricao" maxlength="4000" rows="10" cols="5">${filme.descricao }</textarea>
				</div>
			</div>

			<div class="row">
				<div class="fpr,=group col-md-8">
					<label for="diretor">Direção</label>
					<f:errors path="filme.diretor" cssStyle="color:red" />
					<input type="text" class="form-control" name="diretor" id="diretor"
						required maxlength="60"
						placeholder="Nome do(s) Diretor(es) do Filme"
						value="${filme.diretor}">
				</div>

				<div class="form-group col-md-4">
					<label for="dataLancamento">Data de Lancamento</label>
					<fmt:formatDate pattern="dd/MM/yyyy"
						value="${filme.dataLancamento}" type="date" var="data" />
					<f:errors path="filme.dataLancamento" cssStyle="color:red" />
					<input type="text" class="form-control" name="dataLancamento"
						id="dataLancamento" placeholder="formato dd/MM/yyyy" value="${data}">
						
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-8">
					<label for="posterpath">Poster</label>
					<input type="text" class="form-control" name="posterPath"
						id="posterpath" required maxlength="200"
						placeholder="Caminho da imagem Poster"
						value="${filme.posterPath}">
				</div>
			
				<div class="form-group col-md-4">
					<label for="popularidade">Popularidade</label>
					<input type="number" class="form-control" name="popularidade"
						id="popularidade" min="0.0" max="100.0"
						placeholder="Popularidade" value="${filme.popularidade }">
				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button id="id" type="submit" class="btn btn-primary" name="acao"
						value="alterar_filme?id=${filme.id}">Salvar</button>
					<a href="listar_filmes" class="btn btn-default">Cancelar</a>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
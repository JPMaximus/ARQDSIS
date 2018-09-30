<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Listar Filmes por Popularidade</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>
<body>
	<!-- /.modal -->
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<form action="manterfilmes.do" method="post">
			<div class="col-md-4">
				<h2>Listar Filmes por Popularidade</h2>
				<!-- Para cada Faixa de popularidade será realziado um caroussel -->
				<h3>Popularidade de: "0" a "25"</h3>
				<!-- Inicia a criação do carousel-->
				<div class="container">
					<div id="De0a25" class="carousel slide" data-ride="carousel">
						<!-- Indicadores de quais/quantos filmes terão no caroussel (Pontos do caroussel)-->
						<ol class="carousel-indicators">
							<c:set var="contador" value="0" />
							<!-- Para cada Filme da lista, adicionar um ponto -->
							<c:forEach var="filme" items="${listapop1}">
								<!-- Verifica se é o primeiro da lista de filmes -->
								<c:choose>
									<c:when test="${contador == 0}">
										<li data-target="De0a25" data-slide-to="0" class="active"></li>
										<c:set var="contador" value="1" />
									</c:when>
									<c:otherwise>
										<li data-target="De0a25" data-slide-to="next"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ol>

						<!-- Popula os indicadores do caroussel com as imagens do filme e diretor -->
						<div class="carousel-inner">
							<c:set var="contador" value="0" />
							<c:forEach var="filme" items="${listapop1}">
								<c:choose>
									<c:when test="${contador == 0}">
										<div class="item active">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
											<c:set var="contador" value="1" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="item">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#De0a25" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#De0a25"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
				<br>
				<h3>Popularidade de: "26" a "50"</h3>
				<!-- Inicia a criação do carousel-->
				<div class="container">
					<div id="De26a50" class="carousel slide" data-ride="carousel">
						<!-- Indicadores de quais/quantos filmes terão no caroussel (Pontos do caroussel)-->
						<ol class="carousel-indicators">
							<c:set var="contador" value="0" />
							<!-- Para cada Filme da lista, adicionar um ponto -->
							<c:forEach var="filme" items="${listapop2}">
								<!-- Verifica se é o primeiro da lista de filmes -->
								<c:choose>
									<c:when test="${contador == 0}">
										<li data-target="De26a50" data-slide-to="0" class="active"></li>
										<c:set var="contador" value="1" />
									</c:when>
									<c:otherwise>
										<li data-target="De26a50" data-slide-to="next"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ol>

						<!-- Popula os indicadores do caroussel com as imagens do filme e diretor -->
						<div class="carousel-inner">
							<c:set var="contador" value="0" />
							<c:forEach var="filme" items="${listapop2}">
								<c:choose>
									<c:when test="${contador == 0}">
										<div class="item active">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
											<c:set var="contador" value="1" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="item">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#De26a50" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#De26a50"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
				<br>
				<h3>Popularidade de: "51" a "75"</h3>
				<!-- Inicia a criação do carousel-->
				<div class="container">
					<div id="De51a75" class="carousel slide" data-ride="carousel">
						<!-- Indicadores de quais/quantos filmes terão no caroussel (Pontos do caroussel)-->
						<ol class="carousel-indicators">
							<c:set var="contador" value="0" />
							<!-- Para cada Filme da lista, adicionar um ponto -->
							<c:forEach var="filme" items="${listapop3}">
								<!-- Verifica se é o primeiro da lista de filmes -->
								<c:choose>
									<c:when test="${contador == 0}">
										<li data-target="De51a75" data-slide-to="0" class="active"></li>
										<c:set var="contador" value="1" />
									</c:when>
									<c:otherwise>
										<li data-target="De51a75" data-slide-to="next"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ol>

						<!-- Popula os indicadores do caroussel com as imagens do filme e diretor -->
						<div class="carousel-inner">
							<c:set var="contador" value="0" />
							<c:forEach var="filme" items="${listapop3}">
								<c:choose>
									<c:when test="${contador == 0}">
										<div class="item active">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
											<c:set var="contador" value="1" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="item">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#De51a75" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#De51a75"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
				<br>
				<h3>Popularidade de: "76" a "100"</h3>
				<!-- Inicia a criação do carousel-->
				<div class="container">
					<div id="De76a100" class="carousel slide" data-ride="carousel">
						<!-- Indicadores de quais/quantos filmes terão no caroussel (Pontos do caroussel)-->
						<ol class="carousel-indicators">
							<c:set var="contador" value="0" />
							<!-- Para cada Filme da lista, adicionar um ponto -->
							<c:forEach var="filme" items="${listapop4}">
								<!-- Verifica se é o primeiro da lista de filmes -->
								<c:choose>
									<c:when test="${contador == 0}">
										<li data-target="De76a100" data-slide-to="0" class="active"></li>
										<c:set var="contador" value="1" />
									</c:when>
									<c:otherwise>
										<li data-target="De76a100" data-slide-to="next"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ol>

						<!-- Popula os indicadores do caroussel com as imagens do filme e diretor -->
						<div class="carousel-inner">
							<c:set var="contador" value="0" />
							<c:forEach var="filme" items="${listapop4}">
								<c:choose>
									<c:when test="${contador == 0}">
										<div class="item active">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
											<c:set var="contador" value="1" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="item">
											<img src="${filme.posterPath}" alt="?" width="250"
												height="100">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#De76a100"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#De76a100"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="RU">
<head>
  <title>refrigerator project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/recipe.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/recipe.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <img src="${pageContext.servletContext.contextPath}/resources/images/refrigerator.png" alt="logo" class="navbar-brand">
      <%--<a class="navbar-brand" href="#">Холодильник</a>--%>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="${pageContext.servletContext.contextPath}/refrigerator">Холодильник</a></li>
        <li class="active"><a href="${pageContext.servletContext.contextPath}/search">Поиск<span class="sr-only">(current)</span></a></li>
        <li><a  href="${pageContext.servletContext.contextPath}/recipes">Свои рецепты</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/recipe/add">Добавить рецепт</a></li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Найти</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.servletContext.contextPath}/auth/logout" name="logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
  <form class="well form-horizontal" action="${pageContext.servletContext.contextPath}/recipe/add" method="post" id="recipe_add_form">
    <fieldset>
      <%--<legend><h2><b>Добавление рецепта</b></h2></legend><br>--%>
      <div class="form-group">
        <label class="col-md-4 control-label">Название рецепта</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-shopping-cart"></i></span>
            <input  name="recipename" placeholder="Name" class="form-control"  type="text" value=${recipename} disabled>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label">Время приготовления рецепта</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
            <input  name="timerecipe" placeholder="Time" class="form-control"  type="text" value=${timerecipe} disabled>
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">Способ приготовления рецепта</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-wrench"></i></span>
            <input  name="cookingmethod" placeholder="Cooking Method" class="form-control"  type="text" value=${cookingmethod} disabled>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label">Сложность рецепта</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
            <input  name="complexity" placeholder="" class="form-control"  type="text" value=${complexity} disabled>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label">Список игредиентов</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt"></i></span>
            <table class="table table-bordered table-hover" id="tab_logic">
              <thead>
              <tr >
                <th class="text-center">
                  #
                </th>
                <th class="text-center">
                  Игредиент
                </th>
                <th class="text-center">
                  Количество
                </th>
                <th class="text-center">
                  Размерность
                </th>
              </tr>
              </thead>
              <tbody>
              ${ingredients}
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label">Описание процесса приготовления.</label>
        <div class="col-md-6 inputGroupContainer">
          <div class="input-group">
            <span class="input-group-addon"><i class=" 	glyphicon glyphicon-book"></i></span>
            <textarea id="instructionstextarea" name="instructions" placeholder="Instructions" class="form-control" disabled>${instructions}</textarea>
          </div>
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4 inputGroupContainer">
          <div class="input-group">
            <p class="text-danger">${msgerror}</p>
          </div>
        </div>
      </div>
    </fieldset>
  </form>
</div>
</body>
</html>

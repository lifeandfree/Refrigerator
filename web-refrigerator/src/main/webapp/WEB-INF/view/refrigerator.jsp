<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="RU">
<head>
  <title>refrigerator project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.servletContext.contextPath}/refrigerator">Холодильник <span class="sr-only">(current)</span></a></li>
        <li><a href="${pageContext.servletContext.contextPath}/search">Поиск</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/recipes">Свои рецепты</a></li>
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
  <div class="row">
    <div class="col-md-12">
      <h4>Список игредиентов</h4>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Игредиент</th>
                <th>Размерность</th>
                <th>Количество</th>
                <th>Удалить</th>
              </tr>
              </thead>
              <tbody>
              ${refrigeratorlist}
              </tbody>
            </table>
            <div class="table-responsive">
            </div>
          </div>
        </div>
      </div>
      <form class="well form-horizontal" action="${pageContext.servletContext.contextPath}/refrigerator/save" method="post" id="recipe_add_form">
        <h5>Добавить ингредиент</h5>
        <fieldset>
          <div class="form-group">
            <div class="col-md-9 inputGroupContainer">
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
                  <tr id='addr0'>
                    <td>
                      1
                    </td>
                    <td>
                      <input type="text" name='ingredient'  placeholder='Ingredient' class="form-control"/>
                    </td>
                    <td>
                      <input type="text" name='quantity' placeholder='Quantity' class="form-control"/>
                    </td>
                    <td>
                      <input type="text" name='dimension' placeholder='Dimension' class="form-control"/>
                    </td>
                  </tr>
                  <tr id='addr1'></tr>
                  </tbody>
                </table>
                <div class="form-group">
                  <label class="col-md-4 control-label"></label>
                  <div class="col-md-4"><br>
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-warning" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSUBMIT <span class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </fieldset>
      </form>
      <div class="table-responsive">
      </div>
    </div>
  </div>
</div>
</body>
</html>

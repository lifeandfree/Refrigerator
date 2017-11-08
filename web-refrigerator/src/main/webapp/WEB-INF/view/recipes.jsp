<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <title>refrigerator project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        <li><a href="${pageContext.servletContext.contextPath}/search">Поиск</a></li>
        <li class="active"><a href="${pageContext.servletContext.contextPath}/recipes">Свои рецепты <span class="sr-only">(current)</span></a></li>
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
      <h4>Список созданых рецептов</h4>
      <table class="table table-hover">
        <thead>
        <tr>
          <th>#</th>
          <th>Название</th>
          <th>Сложность</th>
          <th>Время</th>
          <th>Редактировать</th>
          <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        ${recipes}
        </tbody>
        <tfoot>
        <tr class="footable-editing">
          <td colspan="7">
            <button type="submit" class="btn btn-primary footable-add"
                    onclick="window.location.href='${pageContext.servletContext.contextPath}/recipe/add'">
              Добавить рецепт
            </button>
          </td>
        </tr>
        </tfoot>
      </table>
      <div class="table-responsive">
      </div>
    </div>
  </div>
</div>
</body>
</html>

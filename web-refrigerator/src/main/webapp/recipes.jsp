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
      <a class="navbar-brand" href="#">Холодильник</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#">Главная</a></li>
        <li><a href="#">Поиск</a></li>
        <li class="active"><a href="#">Свои рецепты <span class="sr-only">(current)</span></a></li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Найти</button>
      </form>
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
        <%--<tr>--%>
          <%--<th scope="row">1</th>--%>
          <%--<td>Mark</td>--%>
          <%--<td>Otto</td>--%>
          <%--<td>@mdo</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
          <%--<th scope="row">2</th>--%>
          <%--<td>Jacob</td>--%>
          <%--<td>Thornton</td>--%>
          <%--<td>@fat</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
          <%--<th scope="row">3</th>--%>
          <%--<td colspan="2">Larry the Bird</td>--%>
          <%--<td>@twitter</td>--%>
        <%--</tr>--%>
        </tbody>
      </table>
      <div class="table-responsive">
      </div>
    </div>
  </div>
</div>
</body>
</html>

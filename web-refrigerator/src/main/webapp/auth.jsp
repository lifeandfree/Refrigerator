<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
  <title>refrigerator project</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <%--<link href="<c:url value='/css/auth.css'/>" rel="stylesheet">--%>
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/auth.css">
</head>
<body>
  <form action="${pageContext.servletContext.contextPath}/auth" method="post">
    <div class="imgcontainer">
      <img src="image/reflogo.jpeg" alt="logo" class="avatar">
    </div>
    <div class="container">
      <label><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="login" required>
      <label><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>
      <button type="submit">Login</button>
      <input type="checkbox" checked="checked"> Remember me
    </div>
    <div class="form-group">
      <div class="col-md-4 inputGroupContainer">
        <div class="input-group">
          <label class="text-danger"><b>${msgerror}</b></label>
          <%--<p ></p>--%>
        </div>
      </div>
    </div>
    <div class="container" style="background-color:#f1f1f1">
      <div class="etc-login-form">
        <p>forgot your password? <a href="#">click here</a></p>
        <p>new user? <a href="${pageContext.servletContext.contextPath}/reg">create new account</a></p>
      </div>
    </div>
  </form>
</body>
</html>

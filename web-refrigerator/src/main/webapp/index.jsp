<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>refrigerator project</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/login.css">
</head>
<body>
  <form action="/web-refrigerator/auth" method="post">
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
    <div class="container" style="background-color:#f1f1f1">
      <div class="etc-login-form">
        <p>forgot your password? <a href="#">click here</a></p>
        <p>new user? <a href="reg.jsp">create new account</a></p>
      </div>
    </div>
  </form>
</body>
</html>

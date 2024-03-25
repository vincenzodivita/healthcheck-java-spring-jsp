
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<html>
<head>
	<link href="css/navbar.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<title>Login SAMPLE</title>
</head>
<body>
<%@ include file="/css/navbar.jsp" %>
<section class="container vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="/images/doctor.png"
          class="img-fluid" alt="Sample image">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1 border border-primary p-5 rounded-4 shadow-lg p-3 mb-5 bg-white rounded">
      <h2 class="text-center" style="color: #002B7E; font-size: 2.5em;">Accedi</h2>
        <form  action="/user/login" method="post">

          <div class="form-outline mb-4">
          <label class="form-label" for="user">Email</label>
          <input type="text" id="user" name="email"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Inserisci email..." required class="form-control form-control-lg">
          </div>

          <div class="form-outline mb-3">
          <label class="form-label" for="pass">Password</label>
          <input type="password" id="pass" name="password" placeholder="Inserisci password..." required class="form-control form-control-lg">
          </div>

          <% String error = (String) request.getAttribute("error");%>
           <%if(error != null){%>
          <div class="form-outline" style="color: red;">
          <p>
          <%=error%>
          </p>
          </div>
          <%}%>

          <div class="text-center text-lg-start mt-4 pt-2 d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-lg mx-3"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Accedi</button>
              <a class="btn btn-secondary btn-lg mx-3" href="/registration.jsp" >Registrati</a>
          </div>

        </form>
      </div>
    </div>
  </div>

</section>
<%@ include file="/css/footer.jsp" %>
</body>
</html>
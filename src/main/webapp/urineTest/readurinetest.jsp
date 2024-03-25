<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.UrineTestDTO"%>
         <%@ page import="it.contrader.dto.UserDTO" %>
         <%@ page import = "it.contrader.dto.RegistryDTO"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <title>Esami delle Urine</title>
</head>
<body>
    <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>

    <% if(user.getUsertype().toString().equals("USER")){ %>
        <%@ include file="../css/navbarUser.jsp" %>
    <% }else if(user.getUsertype().toString().equals("ADMIN")){ %>
          <%@ include file="../css/navbarAdmin.jsp" %>
    <% }else{ %>
           <%@ include file="../css/navbarSuper.jsp" %>
    <% } %>


    <div class="main container d-flex justify-content-center align-items-center vh-100">
    <div class="w-75">
    <%
        UrineTestDTO urinetest =(UrineTestDTO) request.getSession().getAttribute("dto");
        RegistryDTO paziente =(RegistryDTO) request.getSession().getAttribute("paziente");
        RegistryDTO dottore =(RegistryDTO) request.getSession().getAttribute("dottore");
    %>

        <div class="border border-primary my-2 p-5 rounded-4">
        <h1  class="my-2 text-center" style="color: #002B7E;">Esami delle Urine</h1>
        <h2  class="my-2 text-center" style="color: #002B7E;">Referto ${urinetest.getId()}</h2>
        <div class="d-flex justify-content-center">
        <ul class="list-group my-3  w-50">
          <li class="list-group-item"><span style="color: #002B7E;">Colore:</span> <%=urinetest.getColor()%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Ph:</span> <%=urinetest.getPh()%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Proteine:</span> <%=urinetest.getProtein()%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Emoglobine:</span> <%=urinetest.getHemoglobin()%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Dottore:</span> <%=dottore.getName().substring(0, 1).toUpperCase() + dottore.getName().substring(1)%> <%=dottore.getSurname().substring(0, 1).toUpperCase() + dottore.getSurname().substring(1)%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Paziente:</span> <%=paziente.getCf().toUpperCase()%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Stato:</span>  <%if(urinetest.getIsChecked()){ %>Validato<% } else { %>Da validare<%}%></li>
          <li class="list-group-item"><span style="color: #002B7E;">Data:</span> <%=urinetest.getDateInsert()%></li>
        </ul>
        </div>
        <%if(!urinetest.getIsChecked()){%>
        <div class="button-wrapper d-flex justify-content-center">

         <a href="/urinetest/preupdate?id=<%=urinetest.getId()%>" class="btn btn-primary btn-lg mx-2 mb-2">Modifica</a>

        </div>
        <%}%>
        </div>
	</div>
	</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>
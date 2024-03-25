<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.BloodTestDTO"%>
         <%@ page import="it.contrader.dto.UserDTO" %>
         <%@ page import="it.contrader.dto.RegistryDTO" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <title>Esami del Sangue</title>
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
        BloodTestDTO bloodTest =(BloodTestDTO) request.getSession().getAttribute("dto");
        RegistryDTO paziente =(RegistryDTO) request.getSession().getAttribute("paziente");
        RegistryDTO dottore =(RegistryDTO) request.getSession().getAttribute("dottore");
    %>

        <div class="border border-primary my-2 p-5 rounded-4">
        <h1  class="my-2 text-center" style="color: #002B7E;">Esami del Sangue</h1>
        <h2  class="my-2 text-center" style="color: #002B7E;">Referto ${bloodTest.getId()}</h2>
        <div class="d-flex justify-content-center">
	        <ul class="list-group my-3  w-50">
	          <li class="list-group-item"><span style="color: #002B7E;">Globuli Rossi:</span> <%=bloodTest.getRedBloodCell()%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Globuli Bianchi:</span> <%=bloodTest.getWhiteBloodCell()%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Piastrine:</span> <%=bloodTest.getPlatelets()%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Emoglobine:</span> <%=bloodTest.getHemoglobin()%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Dottore:</span> <%=dottore.getName().substring(0, 1).toUpperCase() + dottore.getName().substring(1)%> <%=dottore.getSurname().substring(0, 1).toUpperCase() + dottore.getSurname().substring(1)%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Paziente:</span> <%=paziente.getCf().toUpperCase()%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Stato:</span> <%if(bloodTest.getIsChecked()){ %>Validato<% } else { %>Da validare<%}%></li>
	          <li class="list-group-item"><span style="color: #002B7E;">Data:</span> <%=bloodTest.getDateInsert()%></li>
	        </ul>
        </div>
        <%if(!bloodTest.getIsChecked()){%>
        <div class="button-wrapper d-flex justify-content-center">
                 <a href="/bloodTest/preupdate?id=<%=bloodTest.getId()%>" class="btn btn-primary btn-lg">Modifica</a>
        </div>
        <%}%>
        </div>

	</div>
	</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>
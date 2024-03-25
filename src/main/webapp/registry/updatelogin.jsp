
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
import = "it.contrader.dto.UserDTO"%>
<%@ page import="it.contrader.dto.RegistryDTO" %>
<html>
<head>
	<link href="css/navbar.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<title>Login SAMPLE</title>
</head>
<body>
<%@ include file="../css/navbar.jsp" %>

 <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>

    <% if(user.getUsertype().toString().equals("USER")){ %>
        <%@ include file="../css/navbarUser.jsp" %>
    <% }else if(user.getUsertype().toString().equals("ADMIN")){ %>
          <%@ include file="../css/navbarAdmin.jsp" %>
    <% }else{ %>
           <%@ include file="../css/navbarSuper.jsp" %>
    <% } %>


<div class="main container d-flex justify-content-center align-items-center vh-100">
  <%RegistryDTO registry = (RegistryDTO) request.getSession().getAttribute("registryDto");%>

  <form class="w-75 my-3 border border-primary p-5 rounded-4" action="/registry/updateLogin" method="post">
    <h1 id="title" class="my-2 pb-3 d-flex justify-content-center" style="color: #002B7E;">Anagrafica</h1>
        <input type="hidden" name="idUser" value="<%=registry.getIdUser()%>">
        <input type="hidden" name="id" value="<%=registry.getId()%>">

        <div class="row mb-3">
                   <label for="name" class="col-sm-2 col-form-label" style="color: #002B7E;">Nome</label>
                   <div class="col-sm-10">
                       <input type="text" id="name" name="name" <%if(registry.getName()!= null ){%>value="<%=registry.getName()%>"<%}%> class="form-control">
                   </div>
               </div>

               <div class="row mb-3">
                   <label for="surname" class="col-sm-2 col-form-label" style="color: #002B7E;">Cognome</label>
                   <div class="col-sm-10">
                       <input type="text" id="surname" name="surname" <%if(registry.getSurname()!= null ){%>value="<%=registry.getSurname()%>"<%}%> class="form-control">
                   </div>
               </div>

               <div class="row mb-3">
                   <label for="birthDate" class="col-sm-2 col-form-label" style="color: #002B7E;">Data Di Nascita</label>
                   <div class="col-sm-10">
                       <input type="date" id="birthDate" name="birthDate" <%if(registry.getBirthDate()!= null ){%>value="<%=registry.getBirthDate()%>"<%}%> class="form-control" max="<%= java.time.LocalDate.now() %>">
                   </div>
               </div>

              <div class="row mb-3">
                  <label for="gender" class="col-sm-2 col-form-label" style="color: #002B7E;">Genere</label>
                  <div class="col-sm-10">
                      <select id="gender" name="gender" class="form-select">
                          <option value="Maschio">Maschio</option>
                          <option value="Femmina">Femmina</option>

                      </select>
                  </div>
              </div>

               <div class="row mb-3">
                   <label for="nationality" class="col-sm-2 col-form-label" style="color: #002B7E;">Nazionalit&#224</label>
                   <div class="col-sm-10">
                       <input type="text" id="nationality" name="nationality" maxlength="2" <%if(registry.getNationality()!= null ){%>value="<%=registry.getNationality()%>"<%}%> class="form-control">
                   </div>
               </div>

               <div class="row mb-3">
                   <label for="city" class="col-sm-2 col-form-label" style="color: #002B7E;">Citt&#224</label>
                   <div class="col-sm-10">
                       <input type="text" id="city" name="city" <%if(registry.getCity()!= null ){%>value="<%=registry.getCity()%>"<%}%> class="form-control">
                   </div>
               </div>

               <div class="row mb-3">
                   <label for="address" class="col-sm-2 col-form-label" style="color: #002B7E;">Indirizzo</label>
                   <div class="col-sm-10">
                       <input type="text" id="address" name="address" <%if(registry.getAddress()!= null ){%>value="<%=registry.getAddress()%>"<%}%> class="form-control">
                   </div>
               </div>

               <div class="row mb-3">
                   <label for="cf" class="col-sm-2 col-form-label" style="color: #002B7E;">Codice Fiscale</label>
                   <div class="col-sm-10">
                        <input type="text" id="cf" name="cf" maxlength="16" <%if(registry.getCf()!= null ){%>value="<%=registry.getCf()%>"<%}%> class="form-control">
                   </div>
               </div>

              <div class="row mb-3">
                  <label for="bg" class="col-sm-2 col-form-label" style="color: #002B7E;">Gruppo Sanguigno</label>
                  <div class="col-sm-10">
                      <select id="bg" name="bg" class="form-select">

                          <option value="A+" >A+</option>
                          <option value="A-" >A-</option>
                          <option value="B+" >B+</option>
                          <option value="B-">B-</option>
                          <option value="AB+" >AB+</option>
                          <option value="AB-" >AB-</option>
                          <option value="0+" >0+</option>
                          <option value="0-" >0-</option>

                      </select>
                  </div>
              </div>



               <div class="button-wrapper d-flex justify-content-center">
                   <button type="submit" class="btn btn-primary btn-lg">Inserisci</button>

               </div>
           </form>
       </div>

       <%@ include file="../css/footer.jsp" %>
       </body>
       </html>
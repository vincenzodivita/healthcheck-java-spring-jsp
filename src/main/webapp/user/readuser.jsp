<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.UserDTO"%>
<%@ page import="it.contrader.dto.RegistryDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Profilo</title>
</head>
<body>
<% UserDTO user = (UserDTO) request.getSession().getAttribute("user");
   RegistryDTO r = (RegistryDTO) request.getSession().getAttribute("registryDto");
%>
<% if(user.getUsertype().toString().equals("USER")){ %>
    <%@ include file="../css/navbarUser.jsp" %>
<% }else if(user.getUsertype().toString().equals("ADMIN")){ %>
    <%@ include file="../css/navbarAdmin.jsp" %>
<% }else{ %>
    <%@ include file="../css/navbarSuper.jsp" %>
<% } %>

<% UserDTO u = (UserDTO) request.getSession().getAttribute("dto");%>

<div class="main container d-flex justify-content-center align-items-center vh-100 py-5">
    <div class="w-75 border border-primary my-2 p-5 rounded-4">
        <h1  class="my-2 text-center" style="color: #002B7E;">Info <%= u != null ? u.getEmail() : "" %></h1>
		<div class="d-flex">
			<div class="d-flex w-100">
                        <div class="w-100 border m-3 mh-100">
                            <h2  class="my-2 text-center" style="color: #002B7E;">Credenziali</h2>
                            <div class="d-flex justify-content-center">
                                <ul class="list-group my-3  w-50">
                                    <li class="list-group-item"><span style="color: #002B7E;">Email: </span><%= u.getEmail()%></li>
                                    <li class="list-group-item"><span style="color: #002B7E;">Password: </span><%=u.getPassword()%></li>
                                </ul>
                            </div>

                            <div class="button-wrapper d-flex justify-content-center">

                                    <a href="/user/preupdate?id=<%= u.getId() %>" class="btn btn-primary btn-lg mx-2 mb-2">Modifica</a>
                                     <% if( !u.getUsertype().toString().equals("SUPER")) { %>
                                    <button type="button" class="btn btn-danger btn-lg mx-2 mb-2" data-bs-toggle="modal" data-bs-target="#modalDelete<%= user.getId() %>">
                                        Elimina
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" id="modalDelete<%= user.getId() %>" tabindex="-1" aria-labelledby="modalDelete<%= user.getId() %>" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Elimina Profilo</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Sei sicuro di voler eliminare il profilo?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="btn btn-primary" href="/user/delete?id=<%= u.getId() %>">Si</a>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%}%>
                            </div>

                        </div>
                    </div>
                    <% if(r.getName() != null && r.getSurname() != null && r.getBirthDate() != null && r.getGender() != null && r.getNationality() != null && r.getCity() != null && r.getAddress() != null && r.getCf() != null && r.getBg() != null && !u.getUsertype().toString().equals("SUPER")) { %>
                    <div class="w-75 border m-3 h-100">

                        <h2  class="my-2 text-center" style="color: #002B7E;">Anagrafica</h2>

                        <div class="d-flex justify-content-center">
                            <ul class="list-group my-3  w-75">
                                <li class="list-group-item"><span style="color: #002B7E;">Nome: </span><%=r.getName().substring(0, 1).toUpperCase() + r.getName().substring(1) %></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Cognome: </span><%=r.getSurname().substring(0, 1).toUpperCase() + r.getSurname().substring(1) %></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Data Nascita: </span><%=r.getBirthDate()%></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Sesso: </span><%= r.getGender()%></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Nazione: </span><%= r.getNationality().toUpperCase()%></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Citt&#224: </span><%=r.getCity().substring(0, 1).toUpperCase() + r.getCity().substring(1) %></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Indirizzo: </span><%=r.getAddress().substring(0, 1).toUpperCase() + r.getAddress().substring(1) %></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Cf: </span><%= r.getCf().toUpperCase()%></li>
                                <li class="list-group-item"><span style="color: #002B7E;">Gruppo sanguigno: </span><%= r.getBg() %></li>
                            </ul>
                        </div>

                        <% if(!user.getUsertype().toString().equals("SUPER")){ %>
                            <div class="button-wrapper d-flex justify-content-center">
                                <a href="/registry/preupdate?id=<%=r.getId()%>" class="btn btn-primary btn-lg mb-2 mx-2">Modifica</a>
                            </div>
                        <% } %>
                    </div>
                    <% } %>
		</div>


    </div>
</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>

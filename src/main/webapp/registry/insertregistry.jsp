<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
import = "it.contrader.dto.UserDTO"%>
<html>
<head>
    <link href="css/navbar.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Login SAMPLE</title>
</head>
<body>
<%@ include file="../css/navbar.jsp" %>

<% UserDTO user = (UserDTO) request.getSession().getAttribute("user"); %>
<div class="main container d-flex justify-content-center align-items-center mvh-100">
    <div class="col-md-8 col-lg-6 col-xl-4 border border-primary p-5 rounded-4 shadow-lg mb-5 bg-white rounded w-50">
        <h2 class="text-center" style="color: #002B7E; font-size: 2.5em;">Anagrafica</h2>
        <form action="/registry/insert" method="post">

            <input type="hidden" name="idUser" value=<%= user.getId() %>>
            <div class="form-outline mb-4">
                <label for="name" class="form-label">Nome</label>
                <input type="text" id="name" name="name" placeholder="Inserisci il tuo nome..." required class="form-control form-control-lg">
            </div>

            <div class="form-outline mb-3">
                <label class="form-label" for="surname">Cognome</label>
                <input type="text" id="surname" name="surname" placeholder="Inserisci cognome..." required class="form-control form-control-lg">
            </div>

            <div class="form-outline mb-4">
                <label for="birthDate" class="form-label">Data di nascita</label>
                <input type="date" id="birthDate" name="birthDate" placeholder="Inserisci la data di nascita..." required class="form-control form-control-lg" max="<%= java.time.LocalDate.now() %>">
            </div>

            <div class="form-outline mb-3">
                <label for="gender" class="form-label">Genere</label>
                <select id="gender" name="gender" class="form-select form-select-lg" required>
                    <option value="Maschio">Maschio</option>
                    <option value="Femmina">Femmina</option>
                </select>
            </div>

            <div class="form-outline mb-3">
                <label for="bg" class="form-label">Gruppo sanguigno</label>
                <select id="bg" name="bg" class="form-select form-select-lg" required>
                 <option value="A+">A+</option>
                 <option value="A-">A-</option>
                 <option value="B+">B+</option>
                 <option value="B-">B-</option>
                 <option value="AB+">AB+</option>
                 <option value="AB-">AB-</option>
                 <option value="0+">0+</option>
                 <option value="0-">0-</option>
                </select>
            </div>

            <div class="form-outline mb-4">
                <label for="nationality" class="form-label">Nazionalit&#224</label>
                <input type="text" id="nationality" name="nationality" maxlength="2" placeholder="Inserisci la tua nazionalit&#224..." required class="form-control form-control-lg">
            </div>

            <div class="form-outline mb-3">
                <label for="city" class="form-label">Citt&#224</label>
                <input type="text" id="city" name="city" placeholder="Inserisci la citt&#224..." required class="form-control form-control-lg">
            </div>

            <div class="form-outline mb-4">
                <label for="address" class="form-label">Indirizzo</label>
                <input type="text" id="address" name="address" placeholder="Inserisci l'indirizzo..." required class="form-control form-control-lg">
            </div>

            <div class="form-outline mb-3">
                <label for="cf" class="form-label">Codice Fiscale</label>
                <input type="text" id="cf" name="cf" maxlength="16" placeholder="Inserisci il codice fiscale..." required class="form-control form-control-lg">
            </div>

            <div class="text-center text-lg-start mt-4 pt-2 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mx-3" style="padding-left: 2.5rem; padding-right: 2.5rem;">Inserisci</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
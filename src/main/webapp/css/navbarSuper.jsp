<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="../css/navbar.css" rel="stylesheet">
</head>
<body>

    <nav class="navbar navbar-expand-lg bg-body-tertiary master">
      <div class="container-fluid">
        <div class="d-flex justify-content-between">
            <div class="collapse navbar-collapse">
                <span>
                    HealthCheck
                </span>
            </div>
        </div>
        <div class="d-flex justify-content-between">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a href="/homesuper.jsp" class="nav-link">Home</a>
                </li>
                <li>
                   <a href="/user/read?id=<%=user.getId()%>" class="nav-link">Profilo</a>
                 </li>
                <li class="nav-item">
                    <a href="/user/getall" class="nav-link">Utenti</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Referti</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/bloodTest/getall">Sangue</a></li>
                        <li><a class="dropdown-item" href="/urinetest/getall">Urine</a></li>
                    </ul>
                </li>

                <li>
                    <a href="/user/logout" class="nav-link">Logout</a>
                </li>
              </ul>
            </div>
        </div>
      </div>
    </nav>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
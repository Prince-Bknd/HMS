<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Page</title>
    <link rel="icon" type="image/x-icon" href="static/media/images/iconHMS.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/header.css">
    <link rel="stylesheet" href="static/css/nav.css">
    <link rel="stylesheet" href="static/css/account.css">
    <link rel="stylesheet" href="static/css/footer.css">
    
    <c:choose>
        <c:when test="${not empty sessionScope.doctor}">
            <link rel="stylesheet" href="static/css/doctor.css">
            <style>
                body {
                    background: linear-gradient(to bottom, #99bbb2, #4fbbb2, rgb(128, 132, 133));
                }
            </style>
        </c:when>
        <c:when test="${not empty sessionScope.patient}">
            <link rel="stylesheet" href="static/css/patient.css">
            <style>
                body {
                    background: url('static/media/images/patientBg.png') no-repeat center center fixed;
                }
            </style>
        </c:when>
        <c:when test="${not empty sessionScope.pharmaCompany}">
            <link rel="stylesheet" href="static/css/pharmacy.css">
            <style>
                body {
                    background: linear-gradient(to bottom, #99bbb2, #4fbbb2, rgb(128, 132, 133));
                }
            </style>
        </c:when>
        <c:otherwise>
            <style>
                body {
                    background: linear-gradient(to bottom, #99bbb2, #4fbbb2, rgb(128, 132, 133));
                }
            </style>
        </c:otherwise>
    </c:choose>
</head>

<body>
    <div class="container-fluid justify-content-center">
        <c:choose>
            <c:when test="${not empty sessionScope.doctor}">
                <%@ include file="header.jsp" %>
        
                <%@ include file="nav.jsp" %>
        
                <%@ include file="doctor.jsp" %>
        
                <%@ include file="footer.jsp" %>
            </c:when>
            <c:when test="${not empty sessionScope.patient}">
                <%@ include file="header.jsp" %>
        
                <%@ include file="nav.jsp" %>
        
                <%@ include file="patient.jsp" %>
        
                <%@ include file="footer.jsp" %>
            </c:when>
            <c:when test="${not empty sessionScope.pharmaCompany}">
                <%@ include file="header.jsp" %>
        
                <%@ include file="nav.jsp" %>
        
                <%@ include file="pharmacy.jsp" %>
        
                <%@ include file="footer.jsp" %>
            </c:when>
            <c:otherwise>
                <%@ include file="account.jsp" %>
            </c:otherwise>
        </c:choose>

    </div>


    
    <script>
        let starNumber = "${sessionScope.doctor.star}";  
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <c:if test="${not empty sessionScope.doctor}">
        <script src="static/js/doctor.js"></script>
    </c:if>
    <c:if test="${not empty sessionScope.patient}">
        <script src="static/js/patient.js"></script>
    </c:if>
    <c:if test="${not empty sessionScope.pharmaCompany}">
        <script src="static/js/pharmacy.js"></script>
    </c:if>
    <script src="static/js/account.js"></script>
</body>

</html>
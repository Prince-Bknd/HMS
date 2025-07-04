<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HMS</title>
    <link rel="icon" type="image/x-icon" href="static/media/images/iconHMS.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/header.css">
    <link rel="stylesheet" href="static/css/nav.css">
    <link rel="stylesheet" href="static/css/ibody.css">
    <link rel="stylesheet" href="static/css/footer.css">

    <style>
        body {
            background-image: url('static/media/images/indexbg.jpg');
            background-color: rgba(165, 255, 255, 0.8);
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            min-height: 100vh;
            backdrop-filter: blur(10px);
        }

        @media (max-width: 768px) {
            body {
                background-size: contain; 
                background-position: top center;
                background-attachment: scroll; 
            }
        }

        @media (max-width: 576px) {
            body {
                background-size: contain; 
                background-position: center; 
            }
        }

    </style>
</head> 

<body>


    <div class="container-fluid justify-content-center">
        <%@ include file="header.jsp" %>
            
        <%@ include file="nav.jsp" %>
        
        <!-- BODY HERE -->
        <%@ include file="ibody.jsp" %>
        
        <%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>const signupStatus = "${param.signup}";</script>
    <script src="static/js/nav.js"></script>
</body>

</html>

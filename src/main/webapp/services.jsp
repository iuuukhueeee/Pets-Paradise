<%@ page import="com.DTO.ServiceDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/services.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <link rel="shortcut icon" type="img/png" href="./img/paw-solid.svg"/>
    <title>Services</title>
</head>

<body>
<header class="header " id="header">
    <div class="container">
        <a href="#" class="logo">
            <img src="./img/paw-solid.svg" style="height: 40px"/>
            <span>Pet's Paradise</span>
        </a>
        <nav class="navbar navbar-expand-lg navbar-light " style="  box-sizing: border-box;
      ">
            <div class="container-fluid">
                <a class="navbar" href="#"></a>
                <button class="navbar-toggler " type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="" href="./index.jsp">HOME</a>
                        <a class="active" href="services">SERVICES</a>
                        <a class="" href="shopping">SHOPPING</a>
                        <a class="" href="blog">BLOG</a>
                        <a class="" href="./contact.jsp">CONTACT</a>

                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
<section class="carousell">
    <div class="container">
        <p>WELCOME TO OUR PET SERVICES</p>
        <button class="button">Read more</button>
        <i class="fa fa-angle-down"></i>
    </div>
</section>
<section class="services animate__animated animate__slideInLeft  wow py-5">
    <div class="container">
        <div class="row">

            <%
                List<ServiceDTO> listService = (List<ServiceDTO>) request.getAttribute("LIST_SERVICE");
                int index = 0;
                if (listService == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }
            %>

            <%
                for (ServiceDTO service : listService) {
            %>
            <div class=" col ">
                <div class="item p-4">
                    <div class="top d-flex">
                        <div class="left me-3">
                            <img src="./img/service1.jpeg" class="img-fluid"/>
                        </div>
                        <div class="right">
                            <h1>
                                <%=service.getServiceName()%>
                            </h1>
                            <span class="badge bg-info" style="color: white;"><%=service.getServicePrice()%> VND</span>

                        </div>

                    </div>
                    <div class="bottom mt-3" style="height: auto; ">
                        <p><%=service.getServiceDescription()%>
                        </p>
                    </div>
                    <div class="price">
                        <a href="GetSupportedShopController?ID=<%=service.getServiceID()%>">
                            <button class="btn btn-danger">Book now</button>
                        </a>
                    </div>
                </div>

            </div>
            <%
                }
            %>
        </div>
    </div>
</section>
<section class="pagination">
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <li class="page-item"><a href="services?page=1" class="page-link">1</a>
                    </a></li>
            </ul>
        </nav>
    </div>
</section>
<footer>
    <div class="container">
        <div class="row w-100">
            <div class="col col-12 col-md-6 col-lg-4 ">
                <div class="item">
                    <img src="./img/footer.png"/>
                    <p>
                        For more details or questions about us, please contact us via gmail, the support team will
                        quickly find
                        and help you
                    </p>
                </div>
            </div>
            <div class="col col-12 col-md-6 col-lg-2">
                <div class="item">
                    <h4>Explore</h4>
                    <ul>
                        <li>
                            <i class="fa-solid fa-paw"></i>About us
                        </li>
                        <li><i class="fa-solid fa-paw"></i>
                            Services
                        </li>
                        <li><i class="fa-solid fa-paw"></i>
                            More
                        </li>
                        <li>
                            <i class="fa-solid fa-paw"></i>Contact
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col col-12 col-md-6 col-lg-2 ">
                <div class="item">
                    <h4>Help</h4>
                    <ul>
                        <li>
                            <i class="fa-solid fa-paw"></i>
                            Terms & Conditions
                        </li>
                        <li>
                            <i class="fa-solid fa-paw"></i>
                            Privacy Policy
                        </li>
                        <li><i class="fa-solid fa-paw"></i>
                            Reporting
                        </li>
                        <li><i class="fa-solid fa-paw"></i>
                            FAQ
                        </li>
                        <li><i class="fa-solid fa-paw"></i>
                            Support
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col col-12 col-md-6 col-lg-4 animation-item-4 wow">
                <div class="item">
                    <div class="submit">
                        <h4>Subscribe to our Newsletter</h4>
                        <p>Enter your email and receive the latest news, updates and special offers from us.
                        </p>
                        <div>
                            <input type="text" id="lname" name="lastname" placeholder="Your email...">
                            <input type="submit" value="Subscribe now">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<section class="backToTop">
    <a href="#header"><i class="fa fa-arrow-down"></i></a>
</section>
<script src="./js/pagination.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>

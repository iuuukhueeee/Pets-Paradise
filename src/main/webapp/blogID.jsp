<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.DTO.BlogDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<%
    BlogDTO blog = (BlogDTO) request.getAttribute("BLOG_CONTENT");
    if (blog == null) {
        response.sendRedirect("blog");
        return;
    }
%>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/readmore.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"/>
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
    <title>Read More</title>
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
                        <a class="" href="index">HOME</a>
                        <a class="" href="services">SERVICES</a>
                        <a class="" href="shopping">SHOPPING</a>
                        <a class="active" href="blog">BLOG</a>
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
<section class="body">
    <div class="container body mt-4 pt-2">
        <div class="row pb-3">
            <div class="col-md-8">
                <div class="mt-2">
                    <div class="read-title text-center">
                        <h3 style="font-weight: bold">
                            <%=blog.getBlogTitle()%>
                        </h3>
                    </div>
                    <div class="text-muted float-right">
                        <%=blog.getWrittenDate()%>
                    </div>

                    <img src="./img/mèo dt.jpg" class="img-fluid rounded img-responsive" alt=""/>
                    <div class="text-note">Ảnh mạng</div>
                </div>

                <div class="mt-1">
                    <%=blog.getBlogContent()%>
                </div>
                <hr/>
                <div class="footer-body">
                    <a href="blog" class="footer-back"><i class="fa fa-arrow-left"></i> &nbsp;
                        <strong>Back To Forum</strong></a>
                </div>
            </div>
            <div class="col-md-4 left-content">
                <div class="row justify-content-center">
                    <div class="mt-5 wow fadeIn">
                        <div class="row justify-content-center orther">Information</div>
                        <hr class="mt-2 ml-4 mr-4"/>

                        <div class="">
                            <div class="card-body">
                                <div class="text-center">
                                    <h5 class=""><strong><%=blog.getAuthor()%></strong></h5>
                                    <h6 class="text-primary">Author &amp; Writer</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <div>
                    <div class="row justify-content-center orther">Others</div>
                    <hr class="mt-2 ml-4 mr-4"/>

                    <%
                        List<BlogDTO> listBlog = (List<BlogDTO>) request.getAttribute("BLOG_TEMPLATE");
                        for (BlogDTO blogTemplate : listBlog) {
                    %>
                    <article class="row justify-content-center">
                        <div class="title-more mt-1 pl-4 pr-4">
                            <a href="BlogContent?blogID=<%=blog.getBlogID()%>">
                                <%=blogTemplate.getBlogTitle()%>
                            </a>
                        </div>
                    </article>
                    <hr class="m-2"/>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</section>

<footer>
    <div class="container">
        <div class="row w-100">
            <div class="col col-12 col-md-6 col-lg-4">
                <div class="item">
                    <img src="./img/footer.png"/>
                    <p>
                        For more details or questions about us, please contact us via gmail, the support team will
                        quickly find and help you
                    </p>
                </div>
            </div>
            <div class="col col-12 col-md-6 col-lg-2">
                <div class="item">
                    <h4>Explore</h4>
                    <ul>
                        <li><i class="fa-solid fa-paw"></i>About us</li>
                        <li><i class="fa-solid fa-paw"></i> Services</li>
                        <li><i class="fa-solid fa-paw"></i> More</li>
                        <li><i class="fa-solid fa-paw"></i>Contact</li>
                    </ul>
                </div>
            </div>

            <div class="col col-12 col-md-6 col-lg-2">
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
                        <li><i class="fa-solid fa-paw"></i> Reporting</li>
                        <li><i class="fa-solid fa-paw"></i> FAQ</li>
                        <li><i class="fa-solid fa-paw"></i> Support</li>
                    </ul>
                </div>
            </div>
            <div class="col col-12 col-md-6 col-lg-4 animation-item-4 wow">
                <div class="item">
                    <div class="submit">
                        <h4>Subscribe to our Newsletter</h4>
                        <p>
                            Enter your email and receive the latest news, updates and
                            special offers from us.
                        </p>
                        <div>
                            <input type="text" id="lname" name="lastname" placeholder="Your email..."/>
                            <input type="submit" value="Subscribe now"/>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
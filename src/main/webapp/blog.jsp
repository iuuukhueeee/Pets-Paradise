<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.DTO.BlogDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="./css/blog.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
    <title>Blog</title>
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
                        data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="" href="index.html">HOME</a>
                        <a class="" href="./services.html">SERVICES</a>
                        <a class="" href="shopping.html">SHOPPING</a>
                        <a class="active" href="./blog.html">BLOG</a>
                        <a class="" href="contact.html">CONTACT</a>

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
    <div class="container mt-100 mt-60">

        <%
            List<BlogDTO> listBlog = (List<BlogDTO>) request.getAttribute("LIST_BLOG");
            if (listBlog != null && listBlog.size() > 0) {
                BlogDTO blog = listBlog.get(0);
        %>

        <div class="row">
            <div class="col-12 text-center">
                <div class="section-title mb-4 pb-2">
                </div>
            </div>
        </div>
        <div class="container mt-100 mt-60">
            <div class="row">
                <div class="well blog blog-post rounded border">
                    <div class="row">
                        <div class="col-xs-12 md-sm-8 col-md-8 col-lg-8">
                            <div class="image blog-img d-block overflow-hidden position-relative">
                                <img src="./img/cat.jpg" class="img-title img-fluid rounded-top img-responsive" alt="">
                                <div class="post-meta">
                                    <a href="BlogContent?blogID=<%=blog.getBlogID()%>" class="text-light read-more main-title">Read More <i
                                            class="mdi mdi-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                            <div class="blog-details">
                                <h4 class="mt-4"><a href="BlogContent?blogID=<%=blog.getBlogID()%>" class="text-dark title">
                                    <%=blog.getBlogTitle()%>
                                </a>
                                </h4>
                                <p class="text-muted text-content">
                                    <%=blog.getBlogDescription()%>
                                </p>
                                <small class="text-muted float-right"><%=blog.getWrittenDate()%></small>
                            </div>

                        </div>

                    </div>
                    <div class="pt-3 mt-3 border-top d-flex float-left">
                        <img src="./img/cat.jpg" class="img-fluid avatar avatar-ex-sm rounded-pill mr-3 shadow"
                             alt="">
                        <div class="author mt-2">
                            <h6 class="mb-0"><a href="javascript:void(0)" class="text-dark name">
                                <%=blog.getAuthor()%>
                            </a>
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
            }
        %>

        <%
            if (listBlog != null && listBlog.size() > 0) {
        %>

        <div class="row">

            <%
                for (int i = 1; i < listBlog.size(); i++) {
                    BlogDTO blog = listBlog.get(i);
            %>

            <div class="col col-12 col-lg-4 col-md-6 mt-4 pt-2">
                <div class="blog-post rounded border">
                    <div class="blog-img d-block overflow-hidden position-relative">
                        <img src="./img/cat1.jpeg" class="img-fluid rounded-top img-responsive" alt="">
                        <div class="overlay rounded-top bg-dark"></div>
                        <div class="post-meta">
                            <a href="BlogContent?blogID=<%=blog.getBlogID()%>" class="text-light read-more">Read More <i
                                    class="mdi mdi-chevron-right"></i></a>
                        </div>
                    </div>
                    <div class="content p-3">
                        <small class="text-muted p float-right"><%=blog.getWrittenDate()%></small>
                        <h4 class="mt-4"><a href="BlogContent?blogID=<%=blog.getBlogID()%>" class="text-dark title"<%=blog.getBlogTitle()%>></a></h4>
                        <p class="text-muted text-content mt-2" style="text-overflow: ellipsis; ">
                            <%=blog.getBlogDescription()%>
                        </p>
                        <div class="pt-3 mt-3 border-top d-flex">
                            <img src="./img/suuf.png" class="img-fluid avatar avatar-ex-sm rounded-pill mr-3 shadow"
                                 alt="">
                            <div class="author mt-2">
                                <h6 class="mb-0"><%=blog.getAuthor()%>
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>

        </div>
    </div>
</section>

<footer>
    <div class="container">
        <div class="row w-100">
            <div class="col col-12 col-md-6 col-lg-4 ">
                <div class="item">
                    <img src="./img/footer.png"/>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adihic quibusdam ad
                        laborum tempore uscipit, laborum.
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
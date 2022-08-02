<%@ page import="com.DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DTO.BlogDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/shopping.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"/>
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
    <title>Shopping</title>
</head>

<body>

<%
    List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
    if (list == null) {
        response.sendRedirect("error.jsp");
        return;
    }
%>

<header class="header " id="header">
    <div class="container">
        <nav class="border-gray-200 px-2 sm:px-4 py-2.5 rounded ">
            <div class="container flex flex-wrap justify-between items-center mx-auto">
                <a href="#" class="flex items-center">
                    <img src="./img/paw-solid.svg" class="mr-3 h-6 sm:h-9"/>
                    <span class="self-center text-gray-900 text-xl font-semibold whitespace-nowrap">Pet's
                            Paradise</span>
                </a>
                <div class="flex items-center md:order-2">
                    <button type="button"
                            class="flex mr-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
                            id="user-menu-button" aria-expanded="false" data-dropdown-toggle="dropdown">
                        <span class="sr-only"></span>
                    </button>
                    <div class="hidden z-50 my-4 text-base list-none bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-700 dark:divide-gray-600"
                         id="dropdown" data-popper-reference-hidden="" data-popper-escaped=""
                         data-popper-placement="top" style="
                  position: absolute;
                  inset: auto auto 0px 0px;
                  margin: 0px;
                  transform: translate3d(810.5px, 1032.5px, 0px);
                ">
                    </div>
                    <button data-collapse-toggle="mobile-menu-2" type="button"
                            class="inline-flex items-center p-2 ml-1 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 "
                            aria-controls="mobile-menu-2" aria-expanded="false">
                        <span class="sr-only"></span>
                        <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                                  clip-rule="evenodd"></path>
                        </svg>
                        <svg class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                                  clip-rule="evenodd"></path>
                        </svg>
                    </button>
                </div>
                <div class="hidden justify-between items-center w-full md:flex md:w-auto md:order-1 nav"
                     id="mobile-menu-2">
                    <ol
                            class="list-reset flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
                        <li><a href="index"
                               class="active text-gray-700 md:hover:bg-transparent md:border-0 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:text-white ">HOME</a>
                        </li>
                        <li><span class="text-gray-500 mx-2">|</span></li>
                        <li><a href="services" class="text-gray-500 hover:text-gray-600">SERVICES</a></li>
                        <li><span class="text-gray-500 mx-2">|</span></li>
                        <li><a href="blog" class="text-gray-500 hover:text-gray-600">BLOG</a></li>
                        <li><span class="text-gray-500 mx-2">|</span></li>
                        <li><a href="./contact.jsp" class="text-gray-500 hover:text-gray-600">CONTACT</a></li>
                        </li>
                    </ol>

                </div>
                <div class="
                     items-center w-full md:flex md:w-auto md:order-1 detail">
                    <ol class="list-reset flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium list"
                        id="mobile-menu-2">
                        <li>
                            <div class="cart" id="cart">
                                <a href="checkout">
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                                <span class="count">0</span>
                            </div>
                        </li>

                        <li class="login">
                            <a href="./login.jsp">
                                <i class="fa-solid fa-user block text-gray-600 focus:outline-none font-medium rounded-lg text-sm "
                                   type="button" data-modal-toggle="authentication-modal" style="padding:16px"></i>
                            </a>

                        </li>

                    </ol>
                </div>

            </div>

        </nav>
    </div>
</header>

<section class="coming-soon absolute top-15 left-20 right-20 ">
    <div class="week">
        <h1>Deal of the week</h1>
    </div>
    <div class="count-down">
        <div class="container-day">
            <h3 class="day">Day</h3>
            <h3>Days</h3>
        </div>
        <div class="container-hour">
            <h3 class="hour">Hour</h3>
            <h3 style="text-align:center ;margin-left: 2px;">Hours</h3>
        </div>
        <div class="container-minute">
            <h3 class="minute">Minute</h3>
            <h3>Minute</h3>
        </div>
        <div class="container-second">
            <h3 class="second">Sec</h3>
            <h3>Second</h3>
        </div>
    </div>
</section>

<section class="deal relative">
    <div id="indicators-carousel" class="relative" data-carousel="static">
        <div class="overflow-hidden relative h-48 rounded-lg sm:h-64 xl:h-80 2xl:h-96">
            <div class="duration-700 ease-in-out absolute inset-0 transition-all transform translate-x-0 z-20"
                 data-carousel-item="active">
                <img src="./img/carosel1.webp"
                     class="block absolute top-1/2 left-1/2 w-full -translate-x-1/2 -translate-y-1/2 calendar"
                     alt="..." style="object-fit: cover;">
            </div>
            <div class="duration-702 ease-in-out absolute inset-0 transition-all transform translate-x-full z-10 "
                 data-carousel-item="">
                <img src="./img/carousel3.jpg"
                     class="block absolute top-1/2 left-1/2 w-full -translate-x-1/2 -translate-y-1/2" alt="...">
            </div>
            <div class="hidden duration-704 ease-in-out absolute inset-0 transition-all transform background"
                 data-carousel-item="">
                <img src="/img/carousel.jpg"
                     class="block absolute top-1/2 left-1/2 w-full -translate-x-1/2 -translate-y-1/2" alt="..."
                     style="height: 400px;; object-fit: cover;">
            </div>
            <div class="flex absolute bottom-5 left-1/2 z-30 space-x-3 -translate-x-1/2">
                <button type="button" class="w-3 h-3 rounded-full bg-white dark:bg-gray-800" aria-current="true"
                        aria-label="Slide 1" data-carousel-slide-to="0"></button>
                <button type="button"
                        class="w-3 h-3 rounded-full bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800"
                        aria-current="false" aria-label="Slide 2" data-carousel-slide-to="1"></button>
                <button type="button"
                        class="w-3 h-3 rounded-full bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800"
                        aria-current="false" aria-label="Slide 3" data-carousel-slide-to="2"></button>
            </div>
            <button type="button"
                    class="flex absolute top-0 left-0 z-30 justify-center items-center px-4 h-full cursor-pointer group focus:outline-none"
                    data-carousel-prev="">
                    <span
                            class="inline-flex justify-center items-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                        <svg class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                             stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7">
                            </path>
                        </svg>
                        <span class="hidden">Previous</span>
                    </span>
            </button>
            <button type="button"
                    class="flex absolute top-0 right-0 z-30 justify-center items-center px-4 h-full cursor-pointer group focus:outline-none"
                    data-carousel-next="">
                    <span
                            class="inline-flex justify-center items-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                        <svg class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                             stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7">
                            </path>
                        </svg>
                        <span class="hidden">Next</span>
                    </span>
            </button>
        </div>
    </div>

</section>

<section class="product container mx-auto" style="margin-bottom: 3rem">
    <div class="feature text-center">
        <h1>Featured Products</h1>
    </div>
    <div class="search" style="margin-bottom:40px">
        <form action="MainController" method="POST">
            <div class="flex">
                <label for="categories"
                       class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400"></label>
                <select id="categories" name="categories"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option selected="">Choose category</option>
                    <option value="food">Food</option>
                    <option value="gel">Gel</option>
                    <option value="acc">Accessories</option>
                    <option value="toy">Toy</option>
                    <option value="other">Stuff</option>

                </select>

                <div class="relative w-full">
                    <input type="search" id="search-dropdown" name="SearchProduct"
                           class="block p-2.5 w-full z-20 text-sm text-gray-900 bg-[#a5d0cc] rounded-r-lg border-l-gray-50 border-l-2 border border-gray-300 focus:ring-[#68A7AD] focus:border-[#68A7AD] dark:bg-transparent dark:border-l-[#68A7AD]  dark:border-[#68A7AD] dark:placeholder-gray-400 dark:text-dark dark:focus:border-[#68A7AD]"
                           placeholder="Search ">
                    <button type="submit" name="action" value="SearchProduct"
                            class="absolute top-0 right-0 p-2.5 text-sm font-medium text-white bg-blue-700 rounded-r-lg border border-[#68A7AD] dark:bg-[#68A7AD] dark:hover:bg-[#68A7AD]dark:focus:ring-[#68A7AD]">
                        <svg
                                class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                        </svg>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="grid grid-cols-4 gap-4 ">

        <%
            for (ProductDTO product : list) {
        %>

        <div class="col">
            <div class="item" style="height: 100%">
                <div class="max-w-xs bg-white rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700 " style="height: 650px">
                    <a href="#" style="height: 100%">
                        <img class="p-8 rounded-t-lg" src="./<%=product.getImage()%>" alt="product image" style="height: 400px">
                    </a>
                    <div class="p-4">
                        <a href="#">
                            <h5 class="text-md font-semibold tracking-tight text-gray-900 "><%=product.getName()%>
                            </h5>
                        </a>
                        <div class="flex items-center mt-2.5 mb-5">
                            <svg class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                </path>
                            </svg>
                            <svg class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                </path>
                            </svg>
                            <svg class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                </path>
                            </svg>
                            <svg class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                </path>
                            </svg>
                            <svg class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                </path>
                            </svg>
                            <span
                                    class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-blue-200 dark:text-blue-800 ml-3">5.0</span>
                        </div>
                        <div class="flex justify-between items-center">
                            <span class="text-xl font-bold text-gray-900 "><%=product.getPrice()%></span>
                            <form action="MainController" method="POST">
                                <div class="container">
                                    <input type="hidden" name="ID" value="<%=product.getProductID()%>"/>
                                    <input type="hidden" name="quantity" value="1" min="0" placeholder="quantity"/>
                                    <button type="submit" name="action" value="AddToCart"
                                            class="bg-[#68A7AD] hover:bg-[#51969c] text-white font-bold py-2 px-4 rounded">
                                        Add to cart
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</section>
<section class="pagination">
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <%--                <li class="page-item disabled">--%>
                <%--                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>--%>
                <%--                </li>--%>

                <%
                    int size = (int) request.getAttribute("SIZE");
                    for (int i = 1; i <= size; i++) {
                %>
                    <li class="page-item"><a href="shopping?page=<%=i%>" class="page-link"><%=i%></a>
                </li>
                <%
                    }
                %>

                <%--                <li class="page-item"><a href="services?page=2" class="page-link">2</a>--%>
                <%--                    </a></li>--%>
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
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
<script src="./js/shopping.js"></script>
˝
<script src="./js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>

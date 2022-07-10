<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if (user == null || !user.getRoleID().equals("US")) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (user == null) {
            user = new UserDTO();
        }
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg" />
    <link rel="stylesheet" href="./css/petsinfo.css">
    <link
        href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
        rel="stylesheet">
    <title>User - Pet's Info</title>

</head>

<body>

    <div class="sidebar">
        <div class="LOGO">
            <img src="./img/paw-solid.svg" alt="" class="img-responsive">
            <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
                Pet's Paradise
            </div>
        </div>
        <div>
            <a href="./user.jsp" class="collapsible">Personal Information</a>
        </div>
        <div>
            <a href="./userOrder.jsp" class="collapsible">Order</a>
        </div>
        <div>
            <a href="./userPetInfo.jsp" class="collapsible active">Pet's Info</a>
        </div>
    </div>
    <div class="content">
        <div class="t-container mt-5">
            <div class="container">
                <ul class="responsive-table">
                    <li class="table-header">
                        <div class="col col-1">#</div>
                        <div class="col col-2">Name</div>
                        <div class="col col-3">Age</div>
                        <div class="col col-4">Type</div>
                        <div class="col col-5">Picture</div>
                        <div class="col col-6">Description</div>
                        <div class="col col-7">Action</div>
                    </li>
        <%
            List<PetDTO> list = (List<PetDTO>) request.getAttribute("PET_INFO");
            int index = 1;
            if (list == null) {
                response.sendRedirect("error.jsp");
                return;
            }
            for (PetDTO pet : list) {
               String petType = "";
                if(pet.getAnimalID().equals("ANIMAL-001")){
                    petType = "Cat";
                }
                else if(pet.getAnimalID().equals("ANIMAL-002")){
                    petType = "Dog";
                }
        %>
                    <form action="MainController" method="post">
                    <input type="hidden" name="animalName" value="<%= pet.getAnimalName()%>">
                    <li class="table-row">
                        <div class="col col-1" data-label="#"><%= index++%></div>
                        <div class="col col-2" data-label="Name"><%= pet.getAnimalName()%></div>
                        <div class="col col-3" data-label="Age"><%= pet.getAnimalAge()%></div>
                        <div class="col col-4" data-label="Type"><%= petType%></div>
                        <div class="col col-5" data-label="Picture" width="50px" height="50px">
                            <img src="<%= pet.getAnimalPicture()%>" alt="">
                        </div>
                        <div class="col col-6" data-label="Description">
                            <%= pet.getAnimalDescription()%>
                        </div>
                        <div class="col col-7" data-label="Action">
                            <i class="fas fa-edit trash" aria-hidden="true"></i>
                            <button type="submit" name="action" value="DeletePetInfo"><i class='far fa-trash-alt'
                                                                                      style='font-size:24px ; cursor: pointer;'></i>
                            </button>
                        </div>
                    </li>
                    </form>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
</body>

</html>
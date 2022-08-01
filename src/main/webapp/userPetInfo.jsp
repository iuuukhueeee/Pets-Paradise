<%@ page import="com.DTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page import="com.DTO.AnimalDTO" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/petsinfo.css">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>User - Pet's Info</title>

</head>

<body>

<%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<PetDTO> list = (List<PetDTO>) request.getAttribute("LIST_PET");
    Map<String, AnimalDTO> petType = (Map<String, AnimalDTO>) request.getAttribute("ANIMAL_TYPE");
%>

<div class="sidebar">
    <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="user.jsp" class="collapsible">Personal Information</a>
    </div>
    <div>
        <a href="UserOrder" class="collapsible">Order</a>
    </div>
    <div>
        <a href="UserPetInfo" class="collapsible active">Pet's Info</a>
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
                    if (list.size() > 0 && list != null && petType != null) {
                        int index = 0;
                        for (PetDTO pet : list) {
                %>
                <li class="table-row">
                    <div class="col col-1" data-label="#"><%=++index%>
                    </div>
                    <div class="col col-2" data-label="Name"><%=pet.getAnimalName()%>
                    </div>
                    <div class="col col-3" data-label="Age"><%=pet.getAnimalAge()%>
                    </div>
                    <div class="col col-4" data-label="Type"><%=petType.get(pet.getAnimalID()).getAnimalName()%>
                    </div>
                    <div class="col col-5" data-label="Picture" width="50px" height="50px">
                        <img src="./img/dog.jpg" alt="">
                    </div>
                    <div class="col col-6" data-label="Description">
                        <%=pet.getAnimalDescription()%>
                    </div>
                    <div class="col col-7" data-label="Action">
                        <a href="./updatePetForm.jsp" target="popup" onclick="window.open('LoadPetInfoForm?method=update&ID=<%= pet.getPetID()%>','_blank','fullscreen=yes','true')">
                            <i class="fas fa-edit trash" style="color: black" aria-hidden="true"></i></a>

                        <form method="post" action="MainController">
                            <input type="hidden" name="ID" value="<%= pet.getPetID()%>" />
                            <input type="hidden" name="Name" value="<%= user.getUsername()%>" />
                            <button name="action" value="DeletePet">
                                <i class="fa fa-trash trash" aria-hidden="true"></i>
                            </button>
                        </form>
                    </div>
                </li>
                <%
                        }
                    }
                %>


            </ul>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
</body>

</html>

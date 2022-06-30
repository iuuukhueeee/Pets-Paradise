<%@ page import="com.DTO.UserDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/user.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
      rel="stylesheet"
    />
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg" />
</head>
<body>

	<%
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
	%>

    <div class="container">
        <div class="main-body">
        
              <div class="row gutters-sm">
                <div class="col-md-3 mb-3">
                  <div class="card">
                    <div class="card-body">
                      <div class="d-flex flex-column align-items-center text-center">
                        <img src="./img/cat.jpg" alt="" class="rounded-circle" style="width: 160px;overflow:hidden">
                        <div class="mt-3">
                          <h5><%=user.getName()%></h5>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
                <div class="col-lg-9">
					<div class="card">
						<div class="card-body">
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Full Name</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" value="<%=user.getName()%>">
								</div>
							</div>
              <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Username</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" value="<%=user.getUsername()%>">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Email</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" value="<%=user.getEmail()%>">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Phone</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" value="<%=user.getPhoneNumber()%>">
								</div>
							</div>
              <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Password</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="password" class="form-control" value="">
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3"></div>
								<div class="col-sm-9">
									<button class="button">Update</button>
								</div>
							</div>
              
						</div>
					</div>
                </div>
            </div>    
        </div>
    </div>
</body>
</html>
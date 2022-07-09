<%@ page import="com.DTO.UserDTO" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
  <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg" />
  <link rel="stylesheet" href="./css/user.css" />
  <link
    href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
    rel="stylesheet" />
  <title>User - Personal Information</title>
</head>

<body>
  <div class="sidebar">
    <div class="LOGO">
      <img src="../img/paw-solid.svg" alt="" class="img-responsive" />
      <div style="
            font-size: 20px;
            font-weight: 900;
            color: #68a7ad;
            justify-content: center;
            padding-left: 16px;
          ">
        Pet's Paradise
      </div>
    </div>
    <div>
      <a href="./user.html" class="collapsible active">Personal Information</a>
    </div>
    <div>
      <a href="./order.html" class="collapsible">Order</a>
    </div>
    <div>
      <a href="./petInfo.html" class="collapsible">Pet's Info</a>
    </div>
  </div>
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
          <div class="tm table-h-autotm-block-h-auto">
            <div class="row">
              <div class="col-12">
                <h2 class="d-inline-block">Personal Information</h2>
              </div>
            </div>
            <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
              <form action="">
                <div class="username row mb-4">
                  <div class="col-md-3 d-flex align-items-center">
                    Full Name
                  </div>
                  <div class="col-md-9">
                    <div class="form-outline">
                      <input type="text" id="form3Example1cg" required=""
                        class="custom-box form-control form-control-lg pt-1" value="Nguyen Van A" />
                    </div>
                  </div>
                </div>

                <div class="username row mb-4">
                  <div class="col-md-3 d-flex align-items-center">
                    Username
                  </div>
                  <div class="col-md-9">
                    <div class="form-outline">
                      <input type="text" id="form3Example1cg" required=""
                        class="custom-box form-control form-control-lg pt-1" value="user123" />
                    </div>
                  </div>
                </div>

                <div class="username row mb-4">
                  <div class="col-md-3 d-flex align-items-center">Email</div>
                  <div class="col-md-9">
                    <div class="form-outline">
                      <input type="email" id="form3Example1cg" required=""
                        class="custom-box form-control form-control-lg pt-1" value="example@example.com" />
                    </div>
                  </div>
                </div>

                <div class="username row mb-4">
                  <div class="col-md-3 d-flex align-items-center">Phone</div>
                  <div class="col-md-9">
                    <div class="form-outline">
                      <input type="text" id="form3Example1cg" required=""
                        class="custom-box form-control form-control-lg pt-1" value="032615261631" />
                    </div>
                  </div>
                </div>

                <div class="username row mb-4">
                  <div class="col-md-3 d-flex align-items-center">Password</div>
                  <div class="col-md-9">
                    <div class="form-outline">
                      <input type="password" id="form3Example1cg" required=""
                        class="custom-box form-control form-control-lg pt-1" value="***********" />
                    </div>
                  </div>
                </div>

                <div class="row mb-4">
                  <div class="col-md-3"></div>
                  <div class="col-md-9">
                    <button class="button">Update</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
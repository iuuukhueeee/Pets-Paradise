<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="./css/login.css" />

    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"
    />
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg" />
</head>

<body>
<div class="container">
    <section class="vh-100">
        <div class="mask d-flex align-items-center h-100">
            <div class="container h-100">
                <div
                        class="row d-flex justify-content-center align-items-center h-100"
                >
                    <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                        <div class="card" style="border-radius: 15px">
                            <div class="card-body p-4">
                                <h2 class="text-uppercase text-center mb-4">LOGIN</h2>

                                <form action="MainController" method="POST">
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form3Example4"
                                        >Username
                                        </label>
                                        <input
                                                required=""
                                                type="text"
                                                id="form3Example1cg"
                                                class="custom-box form-control form-control-lg pt-1"
                                                placeholder="Your Username"
                                                name="Username"
                                        />
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form3Example4"
                                        >Password
                                        </label>
                                        <input
                                                required=""
                                                type="password"
                                                id="form3Example4cg"
                                                class="custom-box form-control form-control-lg pt-1"
                                                placeholder="Password"
                                                name="Password"
                                        />
                                    </div>

                                    <div
                                            class="d-flex justify-content-between align-items-center"
                                    >
                                        <!-- Checkbox -->
                                        <div class="form-check mb-0">
                                            <input

                                                    class="form-check-input me-2"
                                                    type="checkbox"
                                                    value=""
                                                    id="form2Example3"
                                            />
                                            <label class="form-check-label" for="form2Example3">
                                                Remember me
                                            </label>
                                        </div>
                                        <a href="#!" class="text-body">Forgot password?</a>
                                    </div>
                                    <div class="text-center text-lg-start mt-4 pt-2">
                                        <button class="button" type="submit" name="action" value="Login">Login</button>
                                        <p class="small fw-bold mt-2 pt-1 mb-0">
                                            Don't have an account?
                                            <a href="./register.jsp" class="link-danger"
                                            >Register</a
                                            >
                                        </p>
                                    </div>
                                    <div class="divider d-flex align-items-center my-4">
                                        <p class="text-center fw-bold mx-3 mb-0">Or</p>
                                    </div>
                                    <div
                                            class="d-flex align-items-center justify-content-center"
                                    >
                                        <p class="lead fw-normal mb-0 me-3">
                                            Sign in with &nbsp;
                                        </p>
                                        <div class="icon">
                                            <button
                                                    type="button"
                                                    class="btn btn-primary btn-floating mx-2"
                                            >
                                                <a href="http://facebook.com"><i class="fab fa-facebook-f"></i></a>
                                            </button>

                                            <button
                                                    type="button"
                                                    class="btn btn-primary btn-floating mx-2"
                                            >
                                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/Pets_Paradise_war_exploded/GoogleAuthController&response_type=code&client_id=503483004538-81lb3cajsdbmmnec4i8b1b9sif92ia5n.apps.googleusercontent.com&approval_prompt=force"><i class="fab fa-google"></i></a>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>

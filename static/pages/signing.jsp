<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signing</title>
    <link rel="icon" type="image/x-icon" href="static/media/images/iconHMS.png">


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/signing.css">

    <style>
        body {
            background-image: url('static/media/images/signing.jpg');
            /* background-color: rgb(185, 185, 250); */
            background-size: cover;
            background-position: -50px center;
            /* background-repeat: no-repeat; */
            background-attachment: fixed;
            backdrop-filter: blur(5px);
        }
    </style>
</head>

<body>
    <input type="hidden" id="signininv" value="${signin}">

    <c:choose>
        <c:when test="${signin}">

            <div class="container  mt-5 w-75" id="signin">
                <div class="row justify-content-center signingupdesc h3">
                    You need to fill this information before signing in
                </div>
                <hr>
                <div class="row w-50 mt-5 mx-auto ">
                    <div class="col shadow-lg mt-3 rounded">
                        <div class="row border-bottom rounded-top" style="background-color: rgb(119, 191, 212);">
                            <h5 class="pt-3 pb-2 signingcardhead fs-3">Fill Below Information to Login</h5>
                        </div>
                        <form action="activate.do" id="SigninForm">
                            <div class="row pt-3" style="background-color: rgb(151, 226, 245);">
                                <div class="text-center text-danger d-none" id="signinBE"></div>
                                <div class="pb-3" style="height: 110px;">
                                    <label for="Lemail" class="form-label signingininfieldsL fs-4">Email</label>
                                    <input type="text" name="Lemail" id="Lemail"
                                        class="form-control signingininfieldsR bg-info-subtle"
                                        placeholder="Enter Email here...">
                                    <div class="form-text ms-2 d-none" id="LemailFT">
                                        Eg:- abc@irctc.in
                                    </div>
                                    <div class="form-text ms-2 d-none text-danger" id="LemailFT2">
                                        cannot be kept Empty!!!
                                    </div>
                                </div>
                                <div class="pb-3" style="height: 110px;">
                                    <label for="Lpassword"
                                        class="form-label signingininfieldsL fs-4">Password</label>
                                    <input type="password" name="Lpassword" id="Lpassword"
                                        class="form-control signingininfieldsR bg-info-subtle"
                                        aria-describedby="passwordHelpBlock" placeholder="Enter password here...">
                                    <div class="form-text ms-2 d-none" id="LpasswordFT">
                                        made by you for this account while creating it
                                    </div>
                                    <div class="form-text ms-2 d-none text-danger" id="LpasswordFT2">
                                        cannot be kept Empty!!!
                                    </div>
                                </div>
                                <div class="py-2 signingupanim text-center">
                                    Dont have account?? Create one
                                    <a href="signup.do">
                                        click here
                                    </a>
                                </div>
                            </div>
                            <div class="row" style="background-color: rgb(131, 167, 162);">
                                <div class="col">
                                    <button type="submit" class="btn btn-success btnanimation mt-3 mb-2"
                                        id="signinFL" style="float: right;"> Login </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <h5 class="mt-3 text-center signingupdesc">Before Creating your account, You need to choose your role on
                this
                application</h5>
            <hr>
            <div class="container rounded-top border border-2 signingupanim shadow w-75">
                <div class="row border-bottom border-primary border-4"
                    style="background-color: rgb(200, 200, 253);">
                    <h3 class="pt-2 pb-1 signingcardhead">Create Account via???</h3>
                </div>
                <div class="row border signingChoose">
                    <div class="container w-75">
                        <div class="row justify-content-around">

                            <div class="col-lg-3 my-5 ms-lg-0 ms-4 signingcardanimL" id="s1">
                                <div style="cursor: pointer;">
                                    <div class="card" style="width: 15rem; background-color: rgb(49, 243, 91);"
                                        id="signup_box_1">
                                        <div class="card-body">
                                            <h4 class="card-title">Doctor</h4>
                                            <h6 class="card-subtitle mb-2 text-body-secondary">Schedule your Clinic
                                                timings,
                                                features, etc</h6>
                                            <hr>
                                            <img src="static/media/images/doctor.webp" class="card-img-top h-50"
                                                alt="image not found!!!">
                                            <br>
                                        </div>
                                        <div class="text-center"><small>Already have account?? <a href="signin.do"
                                                    class="card-link mt-3">click here</a></small></div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-lg-3 my-5 ms-lg-0 ms-4 signingcardanimD" id="s2">
                                <div style="cursor: pointer;">
                                    <div class="card" style="width: 15rem; background-color: rgb(49, 243, 91);"
                                        id="signup_box_2">
                                        <div class="card-body">
                                            <h4 class="card-title">Patient</h4>
                                            <h6 class="card-subtitle mb-2 text-body-secondary">Take Appointments,
                                                <br> Stay
                                                safe and
                                                healthy
                                            </h6>
                                            <hr>
                                            <img src="static/media/images/patient.webp" class="card-img-top h-50"
                                                alt="image not found!!!">
                                            <br>
                                        </div>
                                        <div class="text-center"><small>Already have account?? <a href="signin.do"
                                                    class="card-link mt-3">click here</a></small></div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3 my-5 ms-lg-0 ms-4 signingcardanimR" id="s3">
                                <div style="cursor: pointer;">
                                    <div class="card" style="width: 15rem; background-color: rgb(49, 243, 91);"
                                        id="signup_box_3">
                                        <div class="card-body">
                                            <h4 class="card-title">Pharmacy</h4>
                                            <h6 class="card-subtitle mb-2 text-body-secondary">Add and Manage
                                                medicine
                                                Details</h6>
                                            <hr>
                                            <img src="static/media/images/pharmacy.webp" class="card-img-top h-50"
                                                alt="image not found!!!">
                                            <br>
                                        </div>
                                        <div class="text-center"><small>Already have account?? <a href="signin.do"
                                                    class="card-link mt-3">click here</a></small></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row border" style="background-color: rgb(156, 214, 195);">
                    <div class="col">
                        <button class="btn btn-sm btn-success my-1" style="float: right;"> MORE... </button>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <!-- finally signup(s) here -->
    <div class="modal fade mb-5" id="signup1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-scrollable">
            <form action="signup1.do" method="post" id="signup_form1">
                <div class="modal-content">
                    <div class="modal-header text-bg-secondary">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel" style="color: beige;">Create
                            Account(As <span id="ut"></span> )
                        </h1>
                        <input type="hidden" id="user_type" name="sutype">

                        <button type="button" class="btn-close" id="closing_confirmation" aria-label="Close"
                            style="color: rgb(209, 160, 160);"></button>
                    </div>
                    <div class="modal-body bg-dark-subtle" style="height: 400px;">
                        <div class="mb-3" style="height: 80px;">
                            <label for="name" class="form-label fw-semibold">Your Name</label>
                            <input type="text" name="suname" class="form-control bg-info-subtle" id="cname"
                                placeholder="Type name here..." autocomplete="off" maxlength="35">
                            <div class="form-text d-none" id="signup1BN">
                                Provide valid UserName
                            </div>
                            <small class="d-none text-danger ms-3" id="signup1BN2">
                                Invalid name
                            </small>
                        </div>
                        <div class="mb-3" style="height: 80px;">
                            <label for="email" class="form-label fw-semibold">Email</label>
                            <input type="text" name="suemail" class="form-control bg-info-subtle" id="cemail"
                                placeholder="Type email here..." autocomplete="off">
                            <div class="form-text d-none" id="signup1BE">
                                Eg:- abc@irctc.in
                            </div>
                            <small class="error d-none text-danger ms-3" id="signup1BE2">
                                User with same email already exists (or) Invalid email
                            </small>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label fw-semibold">Password</label>
                            <input type="password" name="supassword" id="cpassword"
                                class="form-control bg-info-subtle" aria-describedby="passwordHelpBlock"
                                placeholder="create Strong one..." autocomplete="off">
                            <div class="form-text d-none" id="signup1BP">
                                Your password must be more than 8 characters, must include lower case, upper case,
                                special characters and numbers <b>(must not contain emoji)</b>.
                            </div>
                            <div class="form-text d-none text-danger ms-3" id="signup1BP2">
                                Cannot be kept Empty...
                            </div>
                        </div>
                        <div class="mb-3" style="height: 80px;">
                            <label for="contact" class="form-label fw-semibold">Contact</label>
                            <input type="tel" class="form-control bg-info-subtle" name="sucontact" id="ccontact"
                                placeholder="Type your Phone Number here..." autocomplete="off" maxlength="10">
                            <div class="form-text d-none" id="signup1BC">
                                Use Only numbers here. Eg:- 9999999999
                            </div>
                            <small class="error d-none text-danger ms-3" id="signup1BC2">
                                Either User with this phone number already exists or Invalid number :(
                            </small>
                        </div>

                        <button type="submit" style="display: none;"></button>
                    </div>
                    <div class="modal-footer  bg-dark-subtle">
                        <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button> -->
                        <button type="submit" class="btn btn-primary" id="csignup">Create Account</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Conformation to close the signup page -->
    <div class="modal fade" id="confirmation_alert">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content bg-warning-subtle">
                <div class="modal-body">
                    <p class="fs-5">Are You sure???</p>
                    <span class="fs-5">You will lose your <b> current filled information!!!</b></span> <br>
                    <small>(if not
                        saved Already)</small>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" id="not_confirmed">cancel</button>
                    <button type="button" class="btn btn-danger" id="confirmed">ok</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Activation Modal -->
    <div class="modal fade" tabindex="-1" id="activation" data-bs-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content bg-info-subtle">
                <div class="modal-body text-center fs-5">
                    <div>You need to first Activate Your Account</div>
                    <span>
                        To activate this account, Click on activate button
                        <!-- <form action="activation.do" method="post"> -->
                        <button type="button" class="btn btn-primary btn-sm" id="activate_btn">Activate</button>
                        <!-- </form> -->
                    </span>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/signing.js"></script>
</body>

</html>
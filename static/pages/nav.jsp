<!-- final nav which will display by default -->
<div class="row mt-1 sticky-top navAnim rounded" id="navbar">
    <nav class="navbar navbar-expand-lg" style="background: linear-gradient(45deg, #9ea5a3, #bfc7c6, #95a5a1);"> 
        <div class="container">
            <a href="#" class="navbar-brand">
                <img src="static/media/images/hms.webp" class="rounded-circle" alt="loading.." style="height: 50px;">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navigation">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact us</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            Services
                        </a>
                        <ul class="dropdown-menu bg-secondary-subtle">
                            <li><a class="dropdown-item" href="#">Health Insurance</a></li>
                            <li><a class="dropdown-item" href="#">Documentaries</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">FeedBack</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2 bg-info-subtle border border-primary" type="search"
                        placeholder="Type here...">
                    <button class="btn btn-success" type="submit" style="opacity: 0.6;">Search</button>
                </form>

                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <div class="btn-group ms-lg-5 my-2 my-lg-0">
                            <button class="btn btn-info btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                <span class="text-white">Actions</span>
                            </button>
                            <ul class="dropdown-menu bg-secondary-subtle">
                                <li>
                                    <a class="dropdown-item" href="signin.do"
                                        style="cursor: pointer;">Login</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="signup.do"
                                        style="cursor: pointer;">Create Account</a>
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="btn-group ms-lg-5 my-2 my-lg-0">
                            <form action="logout.do">
                                <button class="btn btn-danger btn-sm" type="submit"
                                    aria-expanded="false">
                                    <span class="text-white">LogOut</span>
                                </button>
                            </form>
                        <div>
                    </c:otherwise>
                </c:choose> 

            </div>
        </div>
    </nav>
</div>

<!-- Signup Failed or Success -->
<div class="modal fade " id="signupFS" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content " id="signupFSC">
            <div class="modal-body d-flex fs-3 " id="signupFSCMB">
                This will never be shown up
            </div>
            <div class="modal-footer pt-2" style="height: 50px;">
                <button type="button" class="btn btn-danger btn-sm" data-bs-dismiss="modal">cancel</button>
                <button type="button" id="signupFSSI" class="btn btn-success btn-sm">SignIn</button>
            </div>
        </div>
    </div>
</div>

<!-- SignIn(s) here --> 
<div class="modal fade" tabindex="-1" id="signin" data-bs-backdrop="static" data-bs-keyboard="false">
    <div class="modal-dialog modal-dialog-scrollable">
        <form action="activate.do" id="SigninForm">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #9fa88c;">
                    <h5 class="modal-title">Fill Below Information to Login</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" style="background-color: #ebe2c6; height: 265px;">
                    <div class="text-center text-danger d-none" id="signinBE"></div>
                    <div class="pb-3" style="height: 80;">
                        <label for="Lemail" class="form-label">Email</label>
                        <input type="text" name="Lemail" id="Lemail" class="form-control bg-info-subtle"
                            placeholder="Enter Email here...">
                        <div class="form-text ms-2 d-none" id="LemailFT">
                            Eg:- abc@irctc.in
                        </div>
                        <div class="form-text ms-2 d-none text-danger" id="LemailFT2">
                            cannot be kept Empty!!!
                        </div>
                    </div>
                    <div class="pb-3" style="height: 80;">
                        <label for="Lpassword" class="form-label">Password</label>
                        <input type="password" name="Lpassword" id="Lpassword" class="form-control bg-info-subtle"
                            aria-describedby="passwordHelpBlock" placeholder="Enter password here...">
                        <div class="form-text ms-2 d-none" id="LpasswordFT">
                            made by you for this account while creating it
                        </div>
                        <div class="form-text ms-2 d-none text-danger" id="LpasswordFT2">
                            cannot be kept Empty!!!
                        </div>
                    </div>
                    <div class="pt-2 text-center">
                        Don't have Account?? Create one
                        <a href="signup.do">
                            click here
                        </a>
                    </div>
                </div>
                <div class="modal-footer" style="background-color: #cfc7af;">
                    <button type="submit" class="btn btn-success btn-sm" id="signinFL">Login</button>
                </div>
            </div>
        </form>
    </div>
</div>

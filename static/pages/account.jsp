<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="row">
        <c:choose>
            <%-- for doctor --%>
            <c:when test="${user.userType.userTypeId == 1}">
                <div class="container">
                    <div class="col">
                        <div class="msg mx-auto" role="alert" style="border: 1px solid red;">
                            <div class="sis px-1 pt-2">
                                Before getting towards DashBoard Page, We will need more details about you
                                click on button below to go forward... <br>
                                <div class="sub">
                                    <button class="btn btn-success" data-bs-toggle="modal"
                                        data-bs-target="#signup1-2">Click
                                        here</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="signup1-2" data-bs-backdrop="static" data-bs-keyboard="false"
                    tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <form action="signupDoc.do" method="post" id="dSignUp" enctype="multipart/form-data">
                            <div class="modal-content">
                                <div class="modal-header text-bg-secondary">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel" style="color: beige;">Last
                                        Step to
                                        GO!!!&nbsp;&nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                            height="16" fill="currentColor" class="bi bi-question-circle"
                                            viewBox="0 0 16 16" style="cursor: pointer;">
                                            <path
                                                d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                                            <path
                                                d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286m1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94" />
                                        </svg>
                                    </h1>
                                </div>
                                <div class="modal-body bg-dark-subtle" style="height: 450px;">
                                    <h5>Read the given information carefully and Fill the correct information
                                        Accordingly
                                    </h5>
                                    <br>

                                    <div class="mb-5">
                                        <label for="certificate" class="form-label">Since You are creating account
                                            as a
                                            doctor,
                                            You
                                            must attach any one certificate for verification</label>
                                        <input type="text" class="form-control bg-info-subtle" name="certificate"
                                            id="certificate" placeholder="Type Certificate name here..."
                                            style="height: 35px;" required>
                                        <div class="form-text ms-3">
                                            Enter type of certificate here. Example:- MBBS, MD, etc
                                        </div>
                                        <div class="d-flex">
                                            <span class="mt-2" style="font-size: 15px;">Upload Certificate
                                                here:&nbsp;&nbsp;</span>
                                            <input class="form-control form-control-sm mt-1" name="upload"
                                                type="file"
                                                style="width: 200px; color: #63543a; background-color: rgb(199, 193, 160);"
                                                accept="image/*" required>
                                        </div>
                                    </div>
                                    <div class="row g-3 mb-3 align-items-center">
                                        <div class="col-auto">
                                            <label for="experience" class="col-form-label">Experience of</label>
                                        </div>
                                        <div class="col-auto d-flex">
                                            <input type="number" id="experience" name="experience"
                                                class="form-control bg-info-subtle"
                                                aria-describedby="passwordHelpInline"
                                                placeholder="Type Years of exp here..." style="height: 30px;"
                                                required> <span class="my-auto"> &nbsp;Years</span>
                                        </div>
                                        <div class="form-text">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Experience
                                            in terms of diagonising Patients
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="specialization">Choose specialization:</label>
                                        <select class="bg-primary-subtle rounded" id="specialization"
                                            name="specialization" required>
                                            <option style="display: none;">Select specialization</option>
                                            <c:forEach var="sp" items="${specializations}">
                                                <option value="${sp.specializationId}">${sp.name}</option>
                                            </c:forEach>
                                        </select>

                                        <div class="form-text text-danger ms-3 fw-bold d-none" id="dSignUpC">Must be
                                            choosen</div>
                                    </div>
                                    <div class="mb-3">
                                        <div class="container">
                                            <div class="row">
                                                Please select Your Gender:
                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="male" checked>
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Male
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="female">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Female
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="other">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            other
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="qualification" class="form-label">Qualifications</label>
                                        <input type="number" class="form-control bg-info-subtle"
                                            name="qualification" id="qualification" placeholder="Working on this!!!"
                                            disabled>
                                    </div>
                                </div>
                                <div class="modal-footer  bg-dark-subtle">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Next</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:when>

            <%-- for patients --%>
            <c:when test="${user.userType.userTypeId == 2}">
                <div class="container">
                    <div class="col">
                        <div class="msg mx-auto" role="alert" style="border: 1px solid red;">
                            <div class="sis px-1 pt-2">
                                Before getting towards DashBoard Page, We will need more details about you
                                click on button below to go forward... <br>
                                <div class="sub">
                                    <button class="btn btn-success" data-bs-toggle="modal"
                                        data-bs-target="#signup1-3">Click
                                        here</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="signup1-3" data-bs-backdrop="static" data-bs-keyboard="false"
                    tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <form action="signupPat.do" method="post" id="mSignUp">
                            <div class="modal-content">
                                <div class="modal-header" style="background-color: rgb(133, 189, 135);">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Last Step towards your
                                        goal!!</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body" style="background-color: rgb(142, 247, 147);">
                                    <div class="fw-semibold">
                                        Please read the below information carefully and fill it accordingly!!
                                    </div>
                                    <div class="my-3">
                                        <label for="aadhar" class="form-label">Aadhar Number(For
                                            identification)</label>
                                        <input type="aadhar" name="aadhar" class="form-control bg-success-subtle" id="aadhar"
                                            placeholder="XXXX-XXXX-XXXX" maxlength="12" required>
                                        <div class="form-text fw-semibold ms-3 text-danger"
                                            style="visibility: hidden;">
                                            please provide correct info
                                        </div>
                                    </div>
                                    <div class="row g-3 mb-3 align-items-center">
                                        <div class="col-auto">
                                            <label for="height" class="col-form-label">Height</label>
                                        </div>
                                        <div class="col-auto">
                                            <input type="number" name="height" id="height" class="form-control bg-success-subtle" >
                                        </div>
                                    </div>
                                    <div class="row g-3 mb-3 align-items-center">
                                        <div class="col-auto">
                                            <label for="weight" class="col-form-label">Weight</label>
                                        </div>
                                        <div class="col-auto">
                                            <input type="number" name="weight" id="weight" class="form-control bg-success-subtle" >
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <div class="container">
                                            <div class="row">
                                                Please select Your Gender:
                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="male" checked>
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Male
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="female">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Female
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="gender"
                                                            value="other">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Other
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="blood">Choose Your Blood Group:</label>
                                        <select class="bg-primary-subtle rounded text-center bg-success-subtle" id="blood"
                                            name="blood" style="width: 100px;" required>
                                            <option class="d-none">click here..</option>
                                            <option>A+ve</option>
                                            <option>A-ve</option>
                                            <option>B+ve</option>
                                            <option>B-ve</option>
                                            <option>AB+ve</option>
                                            <option>AB-ve</option>
                                            <option>O+ve</option>
                                            <option>O-ve</option>
                                        </select>

                                        <div class="form-text text-danger ms-3 fw-bold d-none" id="mSignUpC">Must be
                                            choosen</div>
                                    </div>

                                </div>
                                <div class="modal-footer bg-secondary">
                                    <button type="submit" class="btn btn-primary bg-success">Next</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:when>

            <%-- for pharmacy --%>
            <c:when test="${user.userType.userTypeId == 3}">
                <div class="container">
                    <div class="col">
                        <div class="msg mx-auto" role="alert" style="border: 1px solid red;">
                            <div class="sis px-1 pt-2">
                                Before getting towards DashBoard Page, We will need more details about you
                                click on button below to go forward... <br>
                                <div class="sub">
                                    <button class="btn btn-success" data-bs-toggle="modal"
                                        data-bs-target="#signup1-4">Click
                                        here</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="signup1-4" data-bs-backdrop="static" data-bs-keyboard="false"
                    tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content bg-primary-subtle">
                            <form action="signupPhar.do" method="post" id="pSignUp"
                                enctype="multipart/form-data">
                                <div class="modal-header bg-secondary-subtle">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">One Last Step to
                                        go...</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h3 class="fw-semibold">Read below information carefully and Fill the
                                        correct info accordingly</h3>
                                    <br>
                                    <div class="mb-5">
                                        <label for="license" class="form-label">Since You are creating
                                            account
                                            as a
                                            Pharma Company,
                                            You
                                            must attach a license for verification</label>
                                        <input type="text" class="form-control bg-info-subtle"
                                            name="license" id="license"
                                            placeholder="Type Certificate name here..."
                                            style="height: 35px;" required>
                                        <div class="form-text ms-3">
                                            Enter type of licence here. Example:- Retail Drug License,
                                            Wholesale Drug License, etc
                                        </div>
                                        <div class="d-flex">
                                            <span class="mt-2" style="font-size: 15px;">Upload License
                                                here:&nbsp;&nbsp;</span>
                                            <input class="form-control form-control-sm mt-1" name="upload"
                                                type="file"
                                                style="width: 200px; color: #63543a; background-color: rgb(199, 193, 160);"
                                                accept="image/*" required>
                                        </div>
                                        <div class="form-text ms-3">
                                            Try to attach image of license here
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="detail" class="form-label">Provide some more details
                                            about your company</label>
                                        <textarea class="form-control" name="detail" id="detail" rows="3"
                                            required></textarea>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-secondary">
                                        Next
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </c:when>

            <c:otherwise>
                <div>
                    <h2>SOME ERROR OCCUR</h2>
                    <hr>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
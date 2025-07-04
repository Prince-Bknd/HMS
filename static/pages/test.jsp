<div class="row my-4">
    <div class="col-3">
        <div class="row-1 bottom-0">
            <span class="fs-1 star" var="1">&#9733;</span>
            <span class="fs-1 star" var="2">&#9733;</span>
            <span class="fs-1 star" var="3">&#9733;</span>
            <span class="fs-1 star" var="4">&#9733;</span>
            <span class="fs-1 star" var="5">&#9733;</span>
            <small>(${sessionScope.doctor.star})</small>
        </div>
    </div>
    <div class="col-6 mt-4 text-center fs-4">
        Welcome, <b>Dr. ${sessionScope.user.name}</b>
    </div>
    <div class="col-3">
        <div class="navbar-brand btn float-end me-5 pe-3" id="show_doctor_profile">
            <img src="${sessionScope.user.profilePic}" class="rounded-circle" alt="loading.." style="height: 50px;">
            <div class="ps-2 text-center fw-semibold">${sessionScope.user.name}</div>
        </div>
    </div>
</div>

<!-- Modal Showing Doctor's Profile -->
<div class="modal modal-xl fade" id="doctor_profile" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="doctorProfileLabel" aria-hidden="true">
    <c:choose>
        <c:when test="${not empty doctor}">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: rgb(49, 124, 182);">
                        <h1 class="modal-title fs-5" id="doctorProfileLabel">Doctor's Profile</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body bg-primary bg-gradient text-white py-4">
                        <div class="container">
                            <!-- Profile Section -->
                            <div class="row justify-content-center text-center">
                                <div class="col-md-8">
                                    <h4 class="fw-bold mb-3">${doctor.user.email}</h4>
                                    <img src="${sessionScope.user.profilePic}"
                                        class="rounded-circle img-fluid border border-light shadow-lg"
                                        alt="Profile Picture" style="width: 120px; height: 120px; object-fit: cover;">
                                </div>
                            </div>

                            <!-- Basic Details -->
                            <div class="row justify-content-center text-center mt-3">
                                <div class="col-md-8">
                                    <h5 class="fw-bold">${doctor.user.name}</h5>
                                    <p class="mb-1">${doctor.user.contact}</p>
                                    <p class="mb-3">
                                        <c:choose>
                                            <c:when test="${empty doctor.user.address}">Address Not Updated</c:when>
                                            <c:otherwise>${doctor.user.address}</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>

                            <!-- Doctor Information Grid -->
                            <div class="row mx-md-5 mt-4">
                                <!-- Column 1 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom">
                                        <strong>Date of Birth:</strong>
                                        <span>
                                            <c:choose>
                                                <c:when test="${empty doctor.user.dob}">Not Provided</c:when>
                                                <c:otherwise>${doctor.user.dob}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                    <div class="py-2 border-bottom"><strong>Gender:</strong> ${doctor.gender}</div>
                                    <div class="py-2 border-bottom"><strong>Specialization:</strong>
                                        ${doctor.specialization.name} </div>
                                </div>

                                <!-- Column 2 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom"><strong>Qualification:</strong>
                                        ${doctor.qualification}</div>
                                    <div class="py-2 border-bottom"><strong>Experience:</strong> ${doctor.experience}
                                        years</div>
                                    <div class="py-2 border-bottom d-flex align-items-center">
                                        <strong class="me-2">Certificate:</strong>
                                        <span class="flex-grow-1">${doctor.certificate}</span>
                                        <a href="getdoctorCertificate.do?user=${doctor.user.userId}" target="_blank"
                                            class="ms-2" title="Show Certificate">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="gold"
                                                class="bi bi-award" viewBox="0 0 16 16" style="cursor: pointer;">
                                                <path
                                                    d="M9.669.864 8 0 6.331.864 4.5.75 3.5 2.5l-1.75.1-.75 1.5L0 6l.75 1.5-.75 1.5 1.25 1.5.75 1.5 1.75.1 1 1.75 1.831-.114L8 16l1.169-.864 1.831.114 1-1.75 1.75-.1.75-1.5L16 9l-.75-1.5.75-1.5-1.25-1.5-.75-1.5-1.75-.1-1-1.75L9.669.864zM8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8z" />
                                            </svg>
                                        </a>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer" style="background-color: rgb(137, 184, 221);">
                        <button type="button" class="btn btn-sm btn-info">Update Profile</button>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body" style="background-color: rgb(57, 150, 221);">
                        <div class="container">
                            <div class="row">
                                <div class="h3">Doctor not found, Login again</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="background-color: rgb(137, 184, 221);">
                        <button type="button" class="btn btn-sm btn-info">Login</button>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<div class="row doctorbg1 shadow-lg rounded ms-2 me-5 mb-5" style="height: 850px;">
    <div class="col-3 fs-3 bg-primary rounded-start">
        <div class="row-3 fw-bold pt-2">
            Events and Actions
        </div>
        <hr>
        <div class="row-3 fs-4 ms-3 mt-2 e-1 fw-semibold text-center btn w-100 evaac" id="b1">
            Appointments
        </div>
        <div class="row-3 fs-4 ms-3 mt-2 fw-semibold text-center btn w-100 evaac" id="b2">
            Clinics
        </div>
        <div class="row-3 fs-4 ms-3 mt-2 fw-semibold text-center btn w-100 evaac" id="b3">
            Add new clinic
        </div>
        <div class="row-3 fs-4 ms-3 mt-2 fw-semibold text-center btn w-100 evaac" id="b4">
            FeedBacks or Comments
        </div>
        <div class="row-3 fs-4 ms-3 mt-2 fw-semibold text-center btn w-100 evaac" id="b5">
            Pending..
        </div>
    </div>

    <!-- Default Welcome Section -->
    <div class="col-8 rounded-end text-light text-center" id="intro">
        <div class="row fs-2 fw-bold justify-content-center mt-5">
            WELCOME
        </div>
        <div class="row fs-5 mt-4 px-5">
            <div class="col">
                <p class="fw-medium">
                    This is the Dashboard Page for the Doctor. <br>
                    When you visit this page for the first time, it will serve as your landing page.
                </p>
                <p class="fw-light">
                    To perform an action, please select an event or task from the options on the left side.
                </p>
            </div>
        </div>
    </div>

    <!-- Appointments -->
    <div class="col-8 rounded-end text-light pb-3 d-none overflow-auto" id="intro1"
        style="width: 951px; height: 850px; background-color: blue;">
        <div class="row-2 fw-semibold text-light fs-3 text-center mt-3">
            APPOINTMENTS
        </div>
        <hr>
        <div class="row bxs mt-3 ms-1 me-2 rounded shadow-lg">
            <div class="col fs-5">
                <div class="row mb-2 pb-1 border-bottom border-secondary rounded">
                    <div class="col ps-2 pt-2 h4">
                        UnDiagonised
                    </div>
                    <div class="col text-end" style="float: right;">
                        <a class="btn btn-sm btn-primary fw-bold" href="#" style="color: rgb(215, 189, 240);">show
                            all</a>
                    </div>
                </div>
                <div class="row">
                    <!-- Default when their is no patient record -->
                    <div class="text-center fs-2 fw-semibold pt-5" style="height: 200px; color: rgb(150, 139, 139);">
                        No Patient left for Diagnosis
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row bxs mt-3 ms-1 me-2 rounded shadow-lg">
            <div class="col fs-5">
                <div class="row mb-2 pb-1 border-bottom border-secondary rounded">
                    <div class="col ps-2 pt-2 h4">
                        Diagonised
                    </div>
                    <div class="col text-end" style="float: right;">
                        <a class="btn btn-sm btn-primary fw-bold" href="#" style="color: rgb(215, 189, 240);">show
                            all</a>
                    </div>
                </div>
                <div class="row">
                    <!-- Default when no Appointments are their -->
                    <div class="text-center fs-2 fw-semibold pt-5" style="height: 200px; color: rgb(150, 139, 139);">
                        None of the patients are Diagonised today!!
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row bxs mt-3 ms-1 me-2 rounded shadow-lg">
            <div class="col fs-5">
                <div class="row mb-2 pb-1 border-bottom border-secondary rounded">
                    <div class="col ps-2 pt-2 h4">
                        All
                    </div>
                    <div class="col text-end" style="float: right;">
                        <a class="btn btn-sm btn-primary fw-bold" href="#" style="color: rgb(215, 189, 240);">show
                            all</a>
                    </div>
                </div>
                <div class="row">
                    <!-- Default when no record found -->
                    <div class="text-center fs-2 fw-semibold pt-5" style="height: 200px; color: rgb(150, 139, 139);">
                        Unable to get Record
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-none" style="height: 200px;">
                        <div class="card bg-info ms-3" style="width: 10rem; height: 190px;">
                            <img src="static/media/images/doctoruu.webp" class="card-img-top" alt="loading"
                                height="115px">
                            <div class="card-body">
                                <div class="col">
                                    <small class="row">Patient: ${appointment.patient.user.name}</small>
                                    <div class="row d-flex">
                                        <div class="col" style="width: 15px; height: 30px">
                                            <small>18 yrs</small>
                                        </div>
                                        <div class="col">
                                            <a href="#"><small style="color: rgb(62, 71, 69);">More...</small></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Clinic Section -->
    <div class="col-8 rounded-end text-light d-none" id="intro2"
        style="width: 951px; height: 850px; background: linear-gradient(to right, #0f2027, #203a43, #2c5364);">

        <div class="row-2 fw-bold text-light fs-3 text-center my-3">
            <i class="bi bi-hospital"></i> Clinic Details
        </div>
        <hr class="border-light">

        <div class="container">
            <!-- Header for clinic -->
            <div class="row d-flex justify-content-between align-items-center p-3 border-bottom border-secondary">
                <div class="col-auto fs-4">My Clinics:</div>
                <div class="col-auto">
                    <form class="d-flex" role="search">
                        <input class="form-control form-control-sm me-2" type="search" placeholder="Search Clinic..."
                            aria-label="Search" style="width: 220px;">
                        <button class="btn btn-outline-light btn-sm fw-bold" type="submit">Search</button>
                    </form>
                </div>
            </div>

            <!-- Clinics List -->
            <div class="row overflow-auto" style="max-height: 690px;">
                <c:choose>
                    <c:when test="${not empty clinics}">
                        <c:forEach var="clinic" items="${clinics}">
                            <div class="col-12 my-3 complete_clinic">
                                <div class="card bg-dark text-light shadow-lg border border-light rounded">
                                    <div class="card-body p-4">
                                        <div class="row align-items-center">
                                            <!-- Clinic Image -->
                                            <div class="col-md-3 text-center">
                                                <img src='getImage.do?userId=${clinic.doctor.user.userId}&img=${clinic.photo}'
                                                    alt="Clinic Image" class="img-fluid rounded shadow-lg"
                                                    style="height: 120px; width: 120px; object-fit: cover;">
                                            </div>

                                            <!-- Clinic Details -->
                                            <div class="col-md-6">
                                                <h4 class="fw-bold text-warning clinic_name">${clinic.name}</h4>
                                                <p class="mb-1 text-white-50">Rating: ${clinic.rating * 1.9} / 10</p>
                                                <p class="mb-1 text-white-50">Phone: ${clinic.contact}</p>
                                                <p class="mb-1 text-white-50">Address: ${clinic.address},
                                                    ${clinic.city.name}, ${clinic.city.state.name}</p>
                                                <p class="mb-1 text-white-50">Closed Days: ${clinic.closedDay}</p>
                                            </div>

                                            <!-- Edit Button -->
                                            <div class="col-md-3 text-center">
                                                <button class="btn btn-warning text-dark fw-bold btn-sm">Edit
                                                    Clinic</button>
                                            </div>
                                        </div>
                                        <hr class="border-light">

                                        <!-- Clinic Extra Info -->
                                        <div class="row mt-3">
                                            <div class="col-md-6">
                                                <h5 class="text-info">Description</h5>
                                                <p class="text-white-50">${clinic.description}</p>
                                            </div>
                                            <div class="col-md-6">
                                                <h5 class="text-info">Services Offered</h5>
                                                <ul class="text-white-50">
                                                    <li>General Health Check-ups</li>
                                                    <li>Specialized Consultations</li>
                                                    <li>Laboratory Tests</li>
                                                    <li>Emergency Services</li>
                                                </ul>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-lg-5 pe-lg-4">
                                                <!-- Working Hours -->
                                                <div class="row mt-3 clinic_hours">
                                                    <input type="hidden" class="clinicId" value="${clinic.clinicId}">
                                                    <div class="d-flex align-items-center justify-content-between">
                                                        <h5 class="text-info mb-0">Working Hours</h5>
                                                        <button
                                                            class="btn btn-warning text-dark fw-bold btn-sm ms-2 edit_hours_btn">Edit</button>
                                                    </div>
                                                    <div class="col-md-12 text-white-50 mt-2">
                                                        <div>Monday: 11:00 AM - 4:00 PM</div>
                                                        <div>Tuesday: 11:00 AM - 4:00 PM</div>
                                                        <div>Wednesday: 11:00 AM - 4:00 PM <a
                                                                class="text-decoration-none text-primary"
                                                                style="cursor: pointer;">...more</a></div>
                                                    </div>
                                                </div>

                                                <!-- Sliding Box -->
                                                <div id="editBox" class="edit-box bg-primary">
                                                    <div class="edit_box_content border border-danger bg-success w-75">
                                                        
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="col-lg p-2 text-center">
                                                Scheduling here
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="alert text-center text-light border-0 fs-4 p-5 rounded shadow-lg"
                            style="background-color: #34495e;">
                            No clinic added yet!
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Add new clinic -->
    <div class="col-8 rounded-end text-light setBgClinic overflow-auto d-none pb-4" id="intro3"
        style="width: 951px; height: 850px; background-color: #0d6efd;">

        <!-- Header -->
        <div class="text-center fw-semibold text-light fs-3 py-3 bg-primary shadow rounded-bottom">
            You will need to first create a clinic to manage it
        </div>

        <div class="d-flex justify-content-center mt-3">
            <div class="card shadow-lg p-4 w-75 bg-light text-dark rounded">

                <!-- Form Heading -->
                <h4 class="text-center fw-bold mb-3 text-primary">Fill in the details to create a clinic</h4>
                <hr>

                <div class="clinic_body">
                    <!-- Upload Clinic Image -->
                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-semibold">Upload Clinic's Image</label>
                        <div class="d-flex">
                            <input class="form-control me-2" type="file" id="formFile">
                            <button class="btn btn-primary" id="clinicUpload">Upload</button>
                        </div>
                        <input type="text" class="d-none" id="photoName">
                    </div>
                    <form id="clinic_adding_form">

                        <!-- Choose City -->
                        <div class="mb-3">
                            <label class="fw-semibold" for="city">Choose City:</label>
                            <input list="cities" type="text" class="form-control" placeholder="Enter city" id="city">
                            <datalist id="cities">
                                <c:forEach var="sp" items="${cities}">
                                    <option value="${sp.name} (${sp.state.name})" city="${sp.cityId}"></option>
                                </c:forEach>
                            </datalist>
                            <div class="form-text text-danger d-none" id="cityc">Must be chosen</div>
                        </div>

                        <!-- Clinic Name -->
                        <div class="mb-3">
                            <label for="name" class="form-label fw-semibold">Clinic Name</label>
                            <input type="text" id="name" class="form-control" placeholder="Enter clinic name"
                                maxlength="20">
                            <div class="form-text text-danger d-none" id="contactE">Invalid Name</div>
                        </div>

                        <!-- Contact Number -->
                        <div class="mb-3">
                            <label for="contact" class="form-label fw-semibold">Contact</label>
                            <input type="tel" id="contact" class="form-control" placeholder="Enter contact number"
                                maxlength="10">
                            <div class="form-text text-danger d-none" id="contactE">Invalid Contact</div>
                        </div>

                        <!-- Address -->
                        <div class="mb-3">
                            <label for="address" class="form-label fw-semibold">Address</label>
                            <textarea class="form-control" id="address" rows="2"
                                placeholder="Enter clinic address"></textarea>
                        </div>

                        <!-- Certifications -->
                        <div class="mb-3">
                            <label for="certifications" class="form-label fw-semibold">Certifications</label>
                            <input type="text" id="certifications" class="form-control"
                                placeholder="Enter certifications (comma-separated)">
                            <div class="form-text">Enter certificates in CSV format (comma-separated values).</div>
                        </div>

                        <!-- Closed Days -->
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Closed Days</label>
                            <div class="d-flex flex-wrap">
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Monday"
                                        id="closedMonday">
                                    <label class="form-check-label" for="closedMonday">Monday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Tuesday"
                                        id="closedTuesday">
                                    <label class="form-check-label" for="closedTuesday">Tuesday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox"
                                        value="Wednesday" id="closedWednesday">
                                    <label class="form-check-label" for="closedWednesday">Wednesday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Thursday"
                                        id="closedThursday">
                                    <label class="form-check-label" for="closedThursday">Thursday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Friday"
                                        id="closedFriday">
                                    <label class="form-check-label" for="closedFriday">Friday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Saturday"
                                        id="closedSaturday">
                                    <label class="form-check-label" for="closedSaturday">Saturday</label>
                                </div>
                                <div class="form-check me-3">
                                    <input class="form-check-input closed-day-checkbox" type="checkbox" value="Sunday"
                                        id="closedSunday">
                                    <label class="form-check-label" for="closedSunday">Sunday</label>
                                </div>
                            </div>
                            <input type="hidden" id="closedDay" class="form-control">
                            <div class="form-text">Select closed days from the checkboxes above.</div>
                        </div>

                        <script>
                            document.querySelectorAll('.closed-day-checkbox').forEach(checkbox => {
                                checkbox.addEventListener('change', () => {
                                    let selectedDays = Array.from(document.querySelectorAll('.closed-day-checkbox:checked'))
                                        .map(cb => cb.value).join(', ');
                                    document.getElementById('closedDay').value = selectedDays;
                                });
                            });
                        </script>

                        <!-- Description -->
                        <div class="mb-3">
                            <label for="description" class="form-label fw-semibold">Description</label>
                            <textarea id="description" class="form-control"
                                placeholder="Enter clinic description"></textarea>
                        </div>

                        <!-- Consultation Fees -->
                        <div class="mb-3">
                            <label for="fees" class="form-label fw-semibold">Consultation Fees</label>
                            <div class="input-group">
                                <span class="input-group-text">$</span>
                                <input type="number" id="fees" class="form-control"
                                    placeholder="Enter amount (max: 9999)">
                            </div>
                            <div class="form-text text-danger d-none">Cannot be empty!</div>
                        </div>

                        <!-- Terms and Conditions -->
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="TAC">
                            <label class="form-check-label" for="TAC">
                                Accept Terms and Conditions
                            </label>
                        </div>

                        <!-- Save Clinic Button -->
                        <div class="text-center">
                            <button class="btn btn-success btn-lg px-4" id="ClinicAdd" type="button" disabled>
                                upload first
                            </button>
                        </div>
                    </form>
                </div>


                <!-- Success Message -->
                <div class="alert alert-success fw-bold text-center mt-3 d-none" id="successful_msg">
                    Clinic Successfully Added!
                </div>
            </div>
        </div>
    </div>

    <!-- Feedbacks -->
    <div class="col-8 rounded-end text-light d-none" id="intro4"
        style="width: 951px; height: 920px; background-color: blue;">
        <div class="row-2 fw-semibold text-light fs-3 text-center mt-3">
            FeedBack Details
        </div>
        <hr>
    </div>

    <!-- Pending -->
    <div class="col-8 rounded-end text-light d-none" id="intro5"
        style="width: 951px; height: 920px; background-color: blue;">
        <div class="row-2 fw-semibold text-light fs-3 text-center mt-3">
            Reserved for future
        </div>
        <hr>
    </div>
</div>

<div class="my-5 chooseHeight">
    some content here
</div>
<div class="row signingcardanimL mt-3 mb-3">
    <div class="col"></div>
    <div class="col">
        <div class="row fs-3 fs-semibold">
            WELCOME, ${sessionScope.user.name}
        </div>
        <div class="row fs-5 ps-5">
            Your path to wellness starts with a single step
        </div>
    </div>
    <div class="col-3">
        <div class="navbar-brand btn float-end me-5 pe-3" id="show_doctor_profile">
            <img src="${sessionScope.user.profilePic}" class="rounded-circle" alt="loading.." style="height: 50px;">
            <div class="ps-2 text-center fw-semibold">${sessionScope.user.name}</div>
        </div>
    </div>
</div>

<!-- Preventions for Patients to stay Healthy -->
<div class="container my-5">
    <div class="row fs-3 border-bottom signingcardanimL fw-semibold mx-5 ps-5">
        Preventions
    </div>

    <div
        class="row mx-5 signingcardanimR pt-3 d-flex justify-content-center text-center row-cols-lg-4 row-cols-lg-4 row-cols-sm-2 row-cols-1">
        <div class="col d-flex mb-2 flex-column align-items-center">
            <img src="static/media/images/prevension1.jfif" class="rounded-circle img-fluid small-image"
                alt="Image not found!">
            <div class="fs-5 px-2 mt-2">
                Avoid bad habits like excessive social media use, oversleeping, etc.
            </div>
        </div>
        <div class="col d-flex mb-2 flex-column align-items-center">
            <img src="static/media/images/prevension2.jfif" class="rounded-circle img-fluid small-image"
                alt="Image not found!">
            <div class="fs-5 px-2 mt-2">
                Avoid eating too much junk and oily food.
            </div>
        </div>
        <div class="col d-flex mb-2 flex-column align-items-center">
            <img src="static/media/images/prevension3.jfif" class="rounded-circle img-fluid small-image"
                alt="Image not found!">
            <div class="fs-5 px-2 mt-2">
                Avoid unhealthy food and drinks like soda and processed items.
            </div>
        </div>
        <div class="col d-flex mb-2 flex-column align-items-center">
            <img src="static/media/images/prevension4.jfif" class="rounded-circle img-fluid small-image"
                alt="Image not found!">
            <div class="fs-5 px-2 mt-2">
                Stay away from harmful addictions like cigarettes, tobacco, and alcohol.
            </div>
        </div>
    </div>
</div>

<!-- Modal Showing Patients profile -->
<div class="modal modal-xl fade" id="patient_profile" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <c:choose>
        <c:when test="${not empty patient}">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: rgb(124, 182, 49);">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Patients Profile</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body bg-success bg-gradient text-white py-4">
                        <div class="container">
                            <!-- Profile Section -->
                            <div class="row justify-content-center text-center">
                                <div class="col-md-8">
                                    <h4 class="fw-bold mb-3">${patient.user.email}</h4>
                                    <img src="${sessionScope.user.profilePic}"
                                        class="rounded-circle img-fluid border border-light shadow-lg"
                                        alt="Profile Picture" style="width: 120px; height: 120px; object-fit: cover;">
                                </div>
                            </div>

                            <!-- Basic Details -->
                            <div class="row justify-content-center text-center mt-3">
                                <div class="col-md-8">
                                    <h5 class="fw-bold">${patient.user.name}</h5>
                                    <p class="mb-1">${patient.user.contact}</p>
                                    <p class="mb-3">
                                        <c:choose>
                                            <c:when test="${empty patient.user.address}">Address Not Updated</c:when>
                                            <c:otherwise>${patient.user.address}</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>

                            <!-- Patient Information Grid -->
                            <div class="row mx-md-5 mt-4">
                                <!-- Column 1 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom">
                                        <strong>Date of Birth:</strong>
                                        <span>
                                            <c:choose>
                                                <c:when test="${empty patient.user.dob}">Not Provided</c:when>
                                                <c:otherwise>${patient.user.dob}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                    <div class="py-2 border-bottom"><strong>Gender:</strong> ${patient.gender}</div>
                                    <div class="py-2 border-bottom"><strong>Blood Group:</strong> ${patient.bloodGroup}
                                    </div>
                                </div>

                                <!-- Column 2 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom"><strong>Weight:</strong> ${patient.weight}</div>
                                    <div class="py-2 border-bottom"><strong>Height:</strong> ${patient.height}</div>
                                    <div class="py-2 border-bottom"><strong>Aadhar No:</strong> ${patient.uid}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="background-color: rgb(184, 221, 137);">
                        <button type="button" class="btn btn-sm btn-info">update profile</button>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body" style="background-color: rgb(150, 221, 57);">
                        <div class="container">
                            <div class="row">
                                <div class="h3">User not found, Login again</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="background-color: rgb(184, 221, 137);">
                        <button type="button" class="btn btn-sm btn-info">Login</button>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<!-- Main Selection of Actions and working here -->
<div class="row border-danger my-3 mx-5">
    <div class="row rounded my-5 ms-1 shadow" style="background-color: rgb(163, 187, 136);">
        <div class="col p-3">
            <div class="d-flex justify-content-between align-items-center">
                <h4 class="mb-0 fw-semibold ps-2">Your Appointments</h4>
                <div class="cntnr">
                    <!-- <button class="expand-btn" onclick="expandButton()">Take Appointment</button> -->
                    <div id="expandBtn" class="btn btn-sm me-2 fw-bold expand-btn" onclick="expandButton(`${specializations}`)">Take Appointment</div>
                    <span id="closeBtn" class="close-btn text-danger fw-semibold fs-5 d-none" onclick="closeButton()">close</span>
                </div>
            </div>
            <hr class="mt-2 mb-3">

            <!-- Appointments list here -->
            <div class="row mx-3 p-2">
                <div class="d-flex justify-content-center">
                    <button type="button" class="btn w-25 fw-semibold p-2 overflow-auto" style="background-color: rgb(166, 202, 3);" onclick="fetch_appointments(`${patient.patientId}`, this.parentElement)">
                        Fetch my appointments
                    </button>
                    
                </div>
            </div>
        </div>
    </div>

    <div class="col mx-1 mb-4 mt-3">
        <!-- selection -->
        <div class="row border-bottom rounded-top" style="background-color: rgb(140, 140, 207);">
            <div class="col-2 border-end text-center fs-5 fw-semibold btn overflow-auto">check Your health status</div>
            <div class="col-2 border-end text-center fs-5 fw-semibold btn overflow-auto">Health status History</div>
            <div class="col-2 border-end text-center fs-5 fw-semibold btn overflow-auto">Previous Medical Reports</div>
            <div class="col-2 border-end text-center fs-5 fw-semibold btn overflow-auto">Notifications</div>
        </div>

        <!-- Check Your health -->
        <div class="row d-flex align-items-center justify-content-center" id="health_status" style="height: 500px; background-color: rgb(79, 201, 130);">
            <div class="col-6">
                <div class="row text-center d-flex flex-column align-items-center justify-content-center p-4">
                    <div>
                        <button class="btn btn-secondary btn-sm fw-semibold rounded">Start Test</button>
                    </div>
                    <div class="fw-semibold mt-3">
                        Take this test in order to check how healthy you are!!!
                    </div>
                </div>
            </div>
        </div>

        <!-- Take Appointments -->
        <div class="row d-none" id="appointment" style="height: 500px;">
            Check and take appointments here
        </div>
    </div>  
</div>

<script>
    // Since c:foreach is used
    function expandButton(specializations) {
        let expandBtn = document.getElementById("expandBtn");

        if (expandBtn.classList.contains("btn")) {
            let closeBtn = document.getElementById("closeBtn");
            let navbar = document.getElementById("navbar");
            
            expandBtn.classList.add("fullscreen");
            expandBtn.classList.remove("btn");
            expandBtn.classList.remove("fw-bold");
            expandBtn.innerHTML = ``;
            setTimeout(() => expandBtn.classList.add("expanding"), 200);
        
            setTimeout(() => {
                closeBtn.classList.remove('d-none');
                document.body.classList.add("noscroll");
        
                expandBtn.innerHTML = `
                    <div class="container border rounded p-3 shadow overflow-auto" style="height: 80vh; background-color: #d5e263;">
                        <div class="row justify-content-center">
                            <div class="col-md-6">
                                <div class="d-flex align-items-center gap-2">
                                    <select class="form-select form-select-sm flex-grow-1" id="specializationSelect" onchange="fetchDoctorHandler(this)">
                                        <option value="" selected class="d-none">Select Specialization</option>
                                        <c:forEach var="spec" items="${specializations}">
                                            <option value="${spec.specializationId}">${spec.name}</option>
                                        </c:forEach> 
                                    </select>
                                    <button class="btn btn-primary btn-sm w-25" onclick="fetchDoctor(this.parentElement.parentElement.parentElement, 0)">Show All</button>
                                </div>
                            </div>
                        </div>
        
                        <div id="doctorList" class="mt-3">
                            <p class="text-muted text-sm text-center">Please select a specialization to see available doctors.</p>
                        </div>
                    </div>
                `;
            }, 1000);
            
            navbar.classList.add('d-none');
        }
    }
    
</script>
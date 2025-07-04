const show_doctor_profile = document.querySelector("#show_doctor_profile");
const navbar = document.querySelector("#navbar");
let expandBtn = document.querySelector("#expandBtn");

const patient_profile = new bootstrap.Modal("#patient_profile");

show_doctor_profile.addEventListener("click", () => {
    patient_profile.show();
});

//To fetch all appointments of patients
let fetch_appointments =  async (patientId, elm) => {
    try{
        let response = await fetch(`get_appointments.do?patientId=${encodeURIComponent(patientId)}`);

        if (!response.ok) {
            throw new Error(`Failed to save schedule. Status: ${response.status}`);
        }
        let data = await response.json();

        elm.innerHTML = '';

        if(data.length !== 0){
            for(let d of data){
                let appointmentDiv = document.createElement('div');
                appointmentDiv.classList.add("w-100");
                appointmentDiv.classList.add("mb-3");
                appointmentDiv.innerHTML = `
                        <div class="p-2 rounded d-flex justify-content-between align-items-center mb-2 shadow"  style="background-color: rgb(209, 219, 74);">
                            <div>
                                <strong class="fs-3 fw-bold">Dr. ${d.clinicSchedule.clinic.doctor.user.name}</strong> <br>
                                <small>
                                    ${d.appointmentDate} | &nbsp  ${d.clinicSchedule.openingTime} ~ ${d.clinicSchedule.closingTime}<br>
                                    <span class="fw-semibold">${d.status.name} <span>
                                </small> 
                                
                            </div>
                            <div>
                            </div>
                            <div>
                                <button class="btn btn-info btn-sm mb-1" data-bs-toggle="collapse" href=".collapse${d.appointmentId}">Details</button>
                                <button class="btn btn-danger btn-sm mb-1">Cancel</button>
                            </div>
                        </div>
                        <div class="collapse collapse${d.appointmentId} overflow-auto">
                            <div class="card card-body" style="background-color: rgb(180, 134, 142);">
                                <div class="container">
                                    <div class="row">
                                        <div class="col"> 
                                            <div>
                                                ${d.clinicSchedule.clinic.doctor.specialization.name}
                                            </div>
                                            <div> 
                                                <strong> About Doctor: </strong><br>
                                                <span> Dr. ${d.clinicSchedule.clinic.doctor.user.name} </span> 
                                                &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
                                                Rating: ${d.clinicSchedule.clinic.doctor.star}/5<br>
                                                <span>${d.clinicSchedule.clinic.doctor.experience} yrs of experience</span> <br>
                                                <span>Gender: ${d.clinicSchedule.clinic.doctor.gender}</span> <br>
                                            </div> <br>
                                            <div>
                                                <strong> About Clinic: </strong><br>
                                                <span>${d.clinicSchedule.clinic.name} </span> <br>
                                                <span class="w-50 text-wrap d-inline-block">${d.clinicSchedule.clinic.address}, ${d.clinicSchedule.clinic.city.name}(${d.clinicSchedule.clinic.city.state.name})</span> <br>
                                                <span>${d.clinicSchedule.clinic.contact} </span> <br>
                                                <span>Consultation Fees: ${d.clinicSchedule.clinic.consultationFee} </span> <br>
                                                <span>Description: ${d.clinicSchedule.clinic.description} </span> <br>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <button type="button" class="btn btn-sm btn-warning fw-semibold" style="font-size: smaller;"> Report doctor </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                `;
                elm.classList.remove('d-flex');
                elm.append(appointmentDiv);
            }
        } else{
            let e = document.createElement('div');
            //No Appointments Message
            e.innerHTML = `
                <div class="text-center text-muted mt-3">
                    <em>You have no appointments.</em>
                </div>
            `;                
            elm.append(e);
            console.log(elm);
        }
    } catch(err){
        console.error("Error while fetching appointments: ", err);
    }
}

// To take an appointment 
let take_appointments = async () => {
    alert("Ready to take an appointment");
}

let saveAppointments = (clinicId) => {
    event.preventDefault();

    let form = document.getElementById(`saveAppointmentForm${clinicId}`);
    
    let appointmentDate = form.querySelector('input[name="AppointmentDate"]').value;
    let selectedSchedule = document.querySelector(`#saveAppointmentForm${clinicId} input[name="selectedSchedule"]:checked`);
    
    if (!appointmentDate) {
        return;
    }
    
    if (!selectedSchedule) {
        return;
    }

    let formData = new URLSearchParams();
    formData.append("clinicId", clinicId);
    formData.append("AppointmentDate", appointmentDate);
    formData.append("selectedSchedule", selectedSchedule.value);

    fetch("save_appointment.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: formData.toString(),
    })
    .then(response => response.text())
    .then(data => {
        if (data) {
            location.reload();
        } else {
            alert("Some Technical Error Occur");
        }
    })
    .catch(error => {
        console.error("Error:", error);
    });
}


async function fetchClinics(doctorId, name) {
    try {
        let response = await fetch(`get_clinics.do?doctorId=${doctorId}`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        let clinics = await response.json();
        let modalBody = document.getElementById(`viewClinicsModal${doctorId}`).querySelector(".modal-body");
        
        if (clinics.length === 0) {
            modalBody.innerHTML = `<p class="text-muted text-center">No clinics available for this doctor.</p>`;
            return;
        }
        
        let clinicList = `<ul class="list-group overflow-auto" style="height: 60vh; overflow-x: hidden;">`;
        clinics.forEach(clinic => {
            let scheduleHtml = "";
            if (clinic.clinicSchedules && clinic.clinicSchedules.length > 0) {
                scheduleHtml += `<ul>`;
                clinic.clinicSchedules.forEach((schedule) => {
                    scheduleHtml += `
                        <input type="radio" name="selectedSchedule" id="schedule${clinic.clinicId}" value="${schedule.clinicScheduleId}" required>
                        <label for="schedule${clinic.clinicId}">${schedule.shiftType}: ${schedule.openingTime} - ${schedule.closingTime}</label>
                        <br>`;
                });
                scheduleHtml += `</ul>`;
            } else {
                scheduleHtml = `<p>No schedules available</p>`;
            }
            

            clinicList += `
                <li class="list-group-item d-flex justify-content-between align-items-center row" style="background-color: rgb(236, 223, 248);">
                    <div class="col">
                        <strong>${clinic.name}</strong> - ${clinic.city.name}(${clinic.city.state.name})
                        <br><small class="text-muted">${clinic.address}</small>
                        <br>&nbsp;&nbsp;<small>Diagnose by Dr.${name}</small>
                    </div>
                    <div class="col-3">
                        Timing here
                    </div>
                    <div class="col-2">
                        <button class="btn btn-sm btn-primary" data-bs-toggle="collapse" data-bs-target="#collapse${clinic.clinicId}" aria-expanded="false" aria-controls="collapse${clinic.clinicId}">Show</button>
                        <button class="btn btn-sm btn-success mt-1" data-bs-toggle="collapse" data-bs-target="#collapse2${clinic.clinicId}" aria-expanded="false" aria-controls="collapse2${clinic.clinicId}">Take Appmt</button>
                    </div>
                </li>

                <div id="clinicAccordion${clinic.clinicId}">
                    <div class="collapse mb-1" id="collapse${clinic.clinicId}" data-bs-parent="#clinicAccordion${clinic.clinicId}" style="background-color: rgb(222, 233, 195);">
                        <div class="card card-body">
                            <div><strong>Address:</strong> ${clinic.address}</div>
                            <div><strong>Timings:</strong> Avialable Soon </div>
                            <div><strong>Contact:</strong> ${clinic.contact}</div>
                            <div><strong>Doctor Available:</strong> ${name}</div>
                            <div><strong>Rating:</strong> ${clinic.rating} / 5</div>
                            <div><strong>Fees per appointment:</strong> $${clinic.consultationFee}</div>
                        </div>
                    </div>

                    <div class="collapse mb-1" id="collapse2${clinic.clinicId}" data-bs-parent="#clinicAccordion${clinic.clinicId}" style="background-color: rgb(222, 233, 195);">
                        <div class="card card-body">
                            <form id="saveAppointmentForm${clinic.clinicId}">
                                <div class="mb-2">
                                    Select date for Appointment: 
                                    <input type="date" class="w-25 text-center" name="AppointmentDate" required>
                                </div>
                                
                                <h5>Available Schedules</h5>
                                ${scheduleHtml}

                                <br>                                
                                <button class="btn btn-primary btn-sm" onclick="saveAppointments(${clinic.clinicId})">Book Appointment</button>
                            </form>
                        </div>
                    </div>

                </div>
            `;
        });
        clinicList += `</ul>`;

        modalBody.innerHTML = clinicList;

    } catch (error) {
        console.error("Error fetching clinics:", error);
        let modalBody = document.getElementById(`viewClinicsModal${doctorId}`).querySelector(".modal-body");
        modalBody.innerHTML = `<p class="text-danger text-center">Failed to load clinics.</p>`;
    }
}

function viewClinic(doctorId, name, element) {
    let modal = document.getElementById(`viewClinicsModal${doctorId}`);
    let spinner = document.getElementById(`spinner${doctorId}`); // Get spinner element

    if (modal) {
        element.innerHTML=`
            <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        `;

        setTimeout(() => {
            closeButton();
            fetchClinics(doctorId, name);

            let bootstrapModal = new bootstrap.Modal(modal);
            bootstrapModal.show();

            element.innerText = "view clinics";
        }, 1011);
    } else {
        console.error("Modal not found for doctorId:", doctorId);
    }
}


// Fetching doctors to display patients to take appointment
const fetchDoctor = async (elm, specialization) => {
    try {
        let response = await fetch(`get_doctors.do?spId=${specialization}`);

        

        let data = await response.json();

        let doctorListContainer = document.getElementById("doctorList");
        doctorListContainer.innerHTML = ""; 
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);            
        }

        data.forEach(doctor => {
            let doctorItem = document.createElement("div");
            doctorItem.className = "doctor-card p-4 my-3 rounded shadow-sm position-relative";
        
            // Styling
            doctorItem.style.backgroundColor = "#eef7ab";
            doctorItem.style.border = "1px solid #ddd";
            doctorItem.style.borderRadius = "12px";
            doctorItem.style.padding = "20px";
            doctorItem.style.display = "flex";
            doctorItem.style.flexDirection = "column";
            doctorItem.style.justifyContent = "center";
            doctorItem.style.position = "relative";
            doctorItem.style.maxWidth = "800px";
            doctorItem.style.margin = "auto";
        
            doctorItem.innerHTML = `
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="fw-bold text-primary mb-0">
                            <i class="bi bi-person-circle"></i> Dr. ${doctor.user.name}
                        </h5>
                        <small class="text-muted fs-6 mt-0">
                            <i class="bi bi-briefcase-fill"></i> Experience: ${doctor.experience} years
                        </small>
                    </div>

                    <span class="badge bg-success p-2 fs-6 border shadow">${doctor.specialization.name}</span> <button class="btn btn-sm btn-primary" onclick="viewClinic(${doctor.doctorId}, '${doctor.user.name}', this)">View clinics</button>
                </div>
                <hr>
                <small class="fs-4"><strong><i class="bi bi-star-fill text-warning"></i> Rating:</strong>(${doctor.star}/5)</small>
                <small class="fs-4"><strong><i class="bi bi-hospital"></i> Gender:</strong> ${doctor.gender}</small>
                <small class="fs-4"><strong><i class="bi bi-telephone"></i> Contact:</strong> ${doctor.user.contact}</small>
        
                <button class="btn btn-primary w-100 mt-3">
                    View Schedule
                </button>
            `;
        
            let viewClinicModal = document.createElement('div');
            viewClinicModal.innerHTML = `
                <div class="modal fade modal-lg shadow" id="viewClinicsModal${doctor.doctorId}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" style="background-color: rgb(232, 247, 192);">
                    <div class="modal-dialog">
                        <div class="modal-content" style="background-color: rgb(154, 207, 181)">
                            <div class="modal-header">
                                <h1 class="modal-title fs-4 fw-semibold" id="staticBackdropLabel">Avialable clinics for Dr.${doctor.user.name}</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body" style="background-color: rgb(189, 241, 215)">
                                hello, body here
                            </div>
                            <div class="modal-footer" style="background-color: rgb(189, 241, 215)">
                                <button type="button" class="btn btn-info btn-sm fw-semibold">info</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            doctorListContainer.appendChild(doctorItem);
            document.body.appendChild(viewClinicModal);
        });
        
        

    } catch (error) {
        console.error("Error fetching doctors:", error);
        document.getElementById("doctorList").innerHTML = `<p class="text-muted text-center">Unable to get doctors for this specialization.</p>`;
    }
};

function fetchDoctorHandler(selectElement) {
    let doctorList = document.getElementById("doctorList");
    let specialization = selectElement.value;

    if (specialization) {
        fetchDoctor(doctorList, specialization);
    }
}

function closeButton() {
    let expandBtn = document.getElementById("expandBtn");
    let closeBtn = document.getElementById("closeBtn");
    let navbar = document.getElementById("navbar");

    expandBtn.classList.remove("expanding");
    expandBtn.classList.add("btn");
    expandBtn.classList.add("fw-bold");
    setTimeout(() => {
        expandBtn.classList.remove("fullscreen")
        expandBtn.innerHTML = "Take Appointment"
    }, 500);
    
    closeBtn.classList.add('d-none');
    document.body.classList.remove("noscroll");
    navbar.classList.remove('d-none');
}

// Close on Escape Key Press
document.addEventListener("keydown", function(event) {
    if(event.key === "Escape"){
        closeButton();
    }
});

// Search filter function
function filterDoctors() {
    let input = document.getElementById("searchDoctor").value.toLowerCase();
    let doctors = document.querySelectorAll(".doctor-card");

    doctors.forEach(doc => {
        let name = doc.querySelector("h5").textContent.toLowerCase();
        doc.style.display = name.includes(input) ? "block" : "none";
    });
}


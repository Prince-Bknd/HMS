const stars = document.querySelectorAll(".star");

const b1 = document.querySelector("#b1");
const b2 = document.querySelector("#b2");
const b3 = document.querySelector("#b3");
const b4 = document.querySelector("#b4");
const b5 = document.querySelector("#b5");
const intro = document.querySelector("#intro");
const intro1 = document.querySelector("#intro1");
const intro2 = document.querySelector("#intro2");
const intro3 = document.querySelector("#intro3");
const intro4 = document.querySelector("#intro4");
const intro5 = document.querySelector("#intro5");

// While managing clinicUpload's working hours
const clinic_adding_form = document.querySelector("#clinic_adding_form");
const navbar = document.querySelector("#navbar");
const footer = document.querySelector("#footer");
const heading = document.createElement("h4");

// profile of doctors
const show_doctor_profile = document.querySelector("#show_doctor_profile");

const doctor_profile = new bootstrap.Modal("#doctor_profile");

// Adding clinics
const ClinicAdd = document.querySelector("#ClinicAdd");
const city = document.querySelector("#city");
const contact = document.querySelector("#contact");
const address = document.querySelector("#address");
const fees = document.querySelector("#fees");
const naam = document.querySelector("#name");
const TAC = document.querySelector("#TAC");
const cityc = document.querySelector("#cityc");
const contactE = document.querySelector("#contactE");

const clinicUpload = document.querySelector("#clinicUpload");
const formFile = document.querySelector("#formFile");
const photoName = document.querySelector("#photoName");

const all_clinics = document.querySelector("#all_clinics");

// Msg displaying after adding clinics successfull
const successful_msg = document.querySelector("#successful_msg");

const open = (button, info) => {
    button.addEventListener("click", () => {
        intro.classList.add("d-none");
        intro1.classList.add("d-none");
        b1.classList.remove("selectedEv");
        intro2.classList.add("d-none");
        b2.classList.remove("selectedEv");
        intro3.classList.add("d-none");
        b3.classList.remove("selectedEv");
        intro4.classList.add("d-none");
        b4.classList.remove("selectedEv");
        intro5.classList.add("d-none");
        b5.classList.remove("selectedEv");
        
        info.classList.remove("d-none");
        button.classList.add("selectedEv");
    });
}

const removeInvalid = (elm, klass) => {
    elm.addEventListener("mouseenter", () => {
        elm.classList.remove(klass);
    });
}

// Age Calculations
function calculateAge(dobString) {
    const dob = new Date(dobString);
    const today = new Date();

    let age = today.getFullYear() - dob.getFullYear();
    const monthDiff = today.getMonth() - dob.getMonth();
    const dayDiff = today.getDate() - dob.getDate();

    if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
        age--;
    }

    return age;
}


// ###################################################################################################################
// This is used to add and delete rows of medications while doctors prescription
document.addEventListener('click', function (event) {
    // Add Row
    if (event.target.id === 'addRowBtn') {
        const tbody = document.getElementById('medicationTableBody');
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td><input type="text" name="medicationPrescriptions" class="form-control inp-bg" required></td>
            <td><input type="text" name="dosages" class="form-control inp-bg" required></td>
            <td><span class="btn btn-danger btn-sm fw-semibold deleteRow">Delete</span></td>
        `;
        tbody.appendChild(newRow);
    }

    // Delete Row
    if (event.target.classList.contains('deleteRow')) {
        event.target.closest('tr').remove();
    }
});

// trigger details of diagnosis by doctor
function diagnosisPatient(patientId, doctorId, clinicId, parentElement){
    // parentElement.classList.remove('show'); 

    let dosageAndMedications = `
        <table class="table shadow"> 
            <thead>
                <tr>
                    <th>Medication</th>
                    <th>Dosage</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="medicationTableBody">
                <tr>
                    <td><input type="text" name="medicationPrescriptions" class="form-control inp-bg" required></td>
                    <td><input type="text" name="dosages" class="form-control inp-bg" required></td>
                    <td><span class="btn btn-danger btn-sm fw-semibold deleteRow">Delete</span></td>
                </tr>
            </tbody>
            <button type="button" class="btn btn-success btn-sm fw-semibold" id="addRowBtn">+ Add</button>
        </table>
    `;



    let elm = `
        <div class="offcanvas-header">
            <div class="w-100 text-center">
                <h3 class="offcanvas-title" id="offcanvasTopLabel">
                    Let's go for diagnosis
                </h3>
            </div>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <!-- <p>${patientId} is the Patient ID, ${doctorId} is the Doctor ID, and ${clinicId} is the Clinic ID</p> -->

            <div class="container-fluid d-flex justify-content-center align-items-center mb-5">
                <div class="card shadow-lg p-4 w-100 mx-3" style="max-width: 800px; background-color: rgb(126, 175, 152);">
                    <form id="diagnosisForm">
                        <div class="row align-items-center mb-4">
                            <div class="col-md-8 d-flex align-items-center">
                                <img src="https://cdn-icons-png.flaticon.com/512/387/387561.png" 
                                    alt="Doctor" 
                                    width="100" 
                                    height="100" 
                                    class="rounded-circle me-3 border border-white">
                                <div>
                                    <p class="mb-1 fw-semibold">👨 Dr. John Smith</p>
                                    <p class="mb-1">Doctor Id: <strong class="text-muted">DR-${doctorId * 69}</strong></p>
                                    <p class="mb-1">Clinic Id: <strong class="text-muted">${clinicId + 69}</strong></p>
                                    <p class="mb-1">Patient Id: <strong class="text-muted">${patientId * 69}</strong></p>
                                </div>
                            </div>

                            <div class="col-md-4 text-center" style="cursor: pointer">
                                <div id="qrCodeBox" 
                                    class="d-flex justify-content-center align-items-center text-muted fw-bold"
                                    style="width: 100px; height: 100px; background-color: #f0f0f0; border: 2px dashed #ccc; margin: 0 auto;">
                                    QR
                                </div>
                            </div>

                        </div>

                        <h2 class="text-center mb-4">Prescription Form</h2>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="prescription_date" class="form-label fw-semibold fs-3 text-color">Prescription Date</label>
                                <input type="date" class="form-control inp-bg" id="prescription_date" name="prescription_date" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="duration" class="form-label fw-semibold fs-3 text-color">Medicine Duration</label>
                                <input type="text" class="form-control inp-bg" id="duration" name="duration" required>
                            </div>
                        </div>
                        
                        <div class="row">
                            ${dosageAndMedications}
                        </div>


                        <div class="mb-4">
                            <label for="treatment" class="form-label fw-semibold fs-3 text-color">Treatment</label>
                            <textarea class="form-control inp-bg" id="treatment" name="treatment" rows="3" required></textarea>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg fw-bold">Submit Prescription</button>
                        </div>
                    </form>
                </div>
            </div>



            
        </div>
    `;

    const offcanvasElement = document.getElementById('offcanvasDiagnosis');
    const offcanvas = new bootstrap.Offcanvas(offcanvasElement);

    offcanvasElement.innerHTML = elm;
    offcanvas.show();
}

// Details of Appointments
function details(appointment){
    const offcanvasElement = document.getElementById('offcanvasAppointmentDetails');
    const offcanvas = new bootstrap.Offcanvas(offcanvasElement);
    
    let check = appointment.patient.user.profilePic;
    let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
    let imgSrc = check.startsWith("static") ? check : img;

    let elm = `
        <div class="offcanvas-header">
            <div class="w-100 text-center">
                <h3 class="offcanvas-title" id="offcanvasTopLabel">
                    Complete Details
                </h3>
            </div>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <div class="card-body rounded shadow p-3 overflow-auto" style="background-color: rgb(82, 191, 199); height: 83vh;">
                <div class="container">
                    <div class="row">
                        <div class="col d-flex justify-content-end align-items-center fs-4 fw-semibold p-2">
                            Patient's Details
                        </div>
                        <div class="col d-flex justify-content-end align-items-center p-2">
                            <button class="btn btn-primary fw-semibold">History</button>
                        </div>
                    </div>

                    <div class="row p-3">
                        <div class="col-md-3 d-flex justify-content-center align-items-start mb-3">
                            <img src="${imgSrc}" alt="Patient Photo" class="img-fluid rounded shadow" style="max-height: 180px; object-fit: cover;">
                        </div>

                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-6 mb-2">
                                    <strong>Name:</strong> ${appointment.patient.user.name}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Age:</strong> ${appointment.patient.age}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Gender:</strong> ${appointment.patient.gender}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Email:</strong> ${appointment.patient.user.email}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Phone:</strong> ${appointment.patient.user.contact}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Address:</strong> ${appointment.patient.address}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Blood Group:</strong> ${appointment.patient.bloodGroup}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Weight:</strong> ${appointment.patient.weight}
                                </div>
                                <div class="col-md-6 mb-2">
                                    <strong>Height:</strong> ${appointment.patient.height}
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col d-flex justify-content-end align-items-center fs-4 fw-semibold p-2">
                            Appointment Details
                        </div>
                        <div class="col d-flex justify-content-end align-items-center p-2">
                            <button class="btn btn-danger fw-semibold">Cancel Appointment</button>
                        </div>
                    </div>
                    <div class="row px-3 pb-3">
                        <div class="col-md-6 mb-2">
                            <strong>Doctor:</strong> ${appointment.clinicSchedule.clinic.doctor.user.name}
                        </div>
                        <div class="col-md-6 mb-2">
                            <strong>Specialization:</strong> ${appointment.clinicSchedule.clinic.doctor.specialization.name}
                        </div>
                        <div class="col-md-6 mb-2">
                            <strong>Date:</strong> ${appointment.appointmentDate}
                        </div>
                        <div class="col-md-6 mb-2">
                            <strong>Time:</strong> ${appointment.clinicSchedule.openingTime} ~ ${appointment.clinicSchedule.closingTime}
                        </div>
                        <div class="col-12 mb-2">
                            <strong>Reason:</strong> For Diagnosis
                        </div>

                        <div class="col-12 mb-2 muted mt-3">
                            <small>Scheduled on ${appointment.currentDate} </small>
                        </div>
                    </div>

                </div>
                <div class="d-flex justify-content-end">
                    <button class="btn btn-success btn-sm fw-bold" onclick="diagnosisPatient(${appointment.patient.patientId},${appointment.clinicSchedule.clinic.doctor.doctorId} , ${appointment.clinicSchedule.clinic.clinicId}, this.parentElement.parentElement.parentElement.parentElement)">Go for Diagnosis</button>
                </div>
            </div>
        </div>
    `;

    offcanvasElement.innerHTML = elm;
    offcanvas.show();

}

// Sending ajax request to store data of clinic
const saveClinic = async () => {
    // getting choosen option from city
    const option = Array.from(document.getElementById("cities").options).find(option => option.value === city.value);

    let response = await fetch("signupclinic.do?cityId=" + option.getAttribute("city") + "&contact=" + contact.value + "&address=" + address.value + "&fees=" + fees.value+ "&name=" + naam.value + "&certifications=" + certifications.value + "&closedDay=" + closedDay.value + "&photoName=" + photoName.value + "&description=" + description.value, {
        method: "post"
    });
    return await response.text();
}

stars.forEach(st => {
    if (st.getAttribute('var') <= starNumber) {
        st.classList.add('coloradded');
    }
});

// Deleting particular schedule
async function deleteSchedule(clinicScheduleId, clinicId, element){
    let formData = new URLSearchParams();
    formData.append("clinicScheduleId", clinicScheduleId);

    try {
        let response = await fetch(`delete_schedules.do`, { 
            method: 'POST',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        });

        if (!response.ok) {
            throw new Error(`Failed to delete schedule. Status: ${response.status}`);
        }

        let data = await response.text();

        console.log("Server Response:", data);

    } catch (err) {
        console.error("Error while saving schedules for clinic:", err);
    }    

    checkSchedule(clinicId, element);
}

// Checking Schedules
const checkSchedule = (clinicId, element) => {
    element.classList.remove('btn');
    element.classList.remove('fw-semibold');
    element.classList.remove('w-25');
    element.classList.add('rounded');
    element.classList.add('shadow');
    element.classList.add('justify-content-center');

    const getClinicSchedule = async () => {
        if (!clinicId) {
            console.error("clinicId is required");
            return null;
        }
    
        try {
            let result = await fetch(`get_schedules.do?clinicId=${clinicId}`);
    
            let data = await result.json();
    
            if (typeof data === "object") {
                return data;
            } else {
                throw new Error("Invalid JSON received from server");
            }
        } catch (err) {
            console.log("Result send is not a JSON: ", err);
            return null;
        }
    };

    getClinicSchedule().then((data) => {
        let refreshelm = document.createElement('div');
        refreshelm.innerHTML = `
            <button type="button" class="btn btn-sm btn-primary" onclick="checkSchedule(clinicId, element)">refresh schedulings</button>
        `;
        if(data){
            element.innerHTML = ``;
            element.append(refreshelm);
            data.forEach(schedule => {
                console.log(data);
                let scheduleBox = document.createElement("div");
        
                scheduleBox.innerHTML = `
                    <div class="card-body d-flex justify-content-between align-items-center">
                        <h5 class="card-title fw-bold mb-0">${schedule.shiftType}</h5>
                        <button class="btn btn-danger btn-sm" onclick="deleteSchedule(${schedule.clinicScheduleId}, ${clinicId}, this.parentElement.parentElement.parentElement)">Delete</button>
                    </div>
                    <small class="fw-normal">
                        <span class="card-text"><strong>Opening Time:</strong> ${schedule.openingTime}</span> <br>
                        <span class="card-text"><strong>Closing Time:</strong> ${schedule.closingTime}</span> <br>
                        <span class="card-text"><strong>Max Appointments:</strong> ${schedule.maxAppointment}</span>
                    </small>
                `;
                element.appendChild(scheduleBox);
            });
            let note = document.createElement('small');
            note.classList.add('text-info-emphasis', 'mt-2');
            note.innerText = "Note: This whole schedule will be fixed for all working days";
            element.append(note);
        } else{
            element.innerHTML = "";
            element.append(refreshelm);
            element.insertAdjacentHTML("beforeend", "DATA NOT FOUND (or) Some error occurred <br> <br> <small class='text-danger'> Kindly schedule your clinic if not yet and refresh schedulings </small>");
        }
    }).catch((err) => {
        console.log("Error while fetching Clinic schedules: ", err);
    })

}

// Saving Schedules
const saveSchedule = async (clinicId, openingTime, closingTime, maxAppointment, shiftType) => {
    let formData = new URLSearchParams();
    formData.append("clinicId", clinicId);
    formData.append("openingTime", openingTime);
    formData.append("closingTime", closingTime);
    formData.append("maxAppointment", maxAppointment);
    formData.append("shiftType", shiftType);

    try {
        let response = await fetch(`save_schedules.do`, { 
            method: 'POST',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        });

        if (!response.ok) {
            throw new Error(`Failed to save schedule. Status: ${response.status}`);
        }

        let data = await response.text();

        console.log("Server Response:", data);

        clean(); 
        location.reload();

    } catch (err) {
        console.error("Error while saving schedules for clinic:", err);
    }
};

// To dissolve offcanvas
function clean() {
    let offcanvas = document.querySelector('.offcanvas');
    offcanvas.parentElement.remove();
    document.body.style.overflow = 'auto';
}

// Scheduling of clinic starts from here
function scheduling(clinicId, clinicName, element){
    let elm = document.createElement('div');
    let offcanvaselm = document.createElement('div');
    offcanvaselm.classList.add('offcanvas', 'offcanvas-top');
    offcanvaselm.id = `scheduleClinicOffcanvas${clinicId}`;
    offcanvaselm.style.height = "69vh";
    offcanvaselm.setAttribute('data-bs-backdrop', 'static');

    offcanvaselm.innerHTML = `
        <div class="offcanvas-header">
            <h5 class="offcanvas-title fw-bold fs-3" id="offcanvasTopLabel">
                Update Clinic Schedulings (${clinicName})
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body mx-auto w-50">
            <div class="fs-4"> Fill below info carefully to save Schedulings </div>
            <div id="clinicScheduleForm">
                <div class="container border rounded"> 
                    <div class="row p-2"> 
                        <div class="col"> 
                            <input class="d-none" type="text" id="clinicId" value="${clinicId}">
                            <!-- Opening Time -->
                            <div class="mb-3 row align-items-center">
                                <label for="openingTime" class="col-sm-4 col-form-label fs-5">Opening Time</label>
                                <div class="col-sm-8">
                                    <input type="time" class="form-control text-center" id="openingTime" name="openingTime" required>
                                </div>
                            </div>

                            <!-- Closing Time -->
                            <div class="mb-3 row align-items-center">
                                <label for="closingTime" class="col-sm-4 col-form-label fs-5">Closing Time</label>
                                <div class="col-sm-8">
                                    <input type="time" class="form-control text-center" id="closingTime" name="closingTime" required>
                                </div>
                            </div>

                            <!-- Max Appointments -->
                            <div class="mb-3 row align-items-center">
                                <label for="maxAppointment" class="col-sm-4 col-form-label fs-5">Max Appointments</label>
                                <div class="col-sm-8">
                                    <input type="number" class="form-control text-center" id="maxAppointment" name="maxAppointment" min="1" required>
                                </div>
                            </div>

                            <!-- Shift Type -->
                            <div class="mb-3 row align-items-center">
                                <label for="shiftType" class="col-sm-4 col-form-label fs-5">Shift Type</label>
                                <div class="col-sm-8">
                                    <select class="form-select text-center" id="shiftType" name="shiftType" required>
                                        <option value="" class="d-none" selected>Select Shift Type</option>
                                        <option value="Morning">Morning</option>
                                        <option value="Afternoon">Afternoon</option>
                                        <option value="Evening">Evening</option>
                                        <option value="Night">Night</option>
                                    </select>
                                </div>
                            </div>


                            <!-- Submit Button -->
                            <div class="row"> 
                                <button class="btn btn-success w-25 btn-sm ms-auto fw-semibold" onclick="saveSchedule(document.getElementById('clinicId').value, document.getElementById('openingTime').value,document.getElementById('closingTime').value, document.getElementById('maxAppointment').value, document.getElementById('shiftType').value)">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;

    elm.append(offcanvaselm);
    element.parentElement.append(elm);

    let offcanvasInstance = new bootstrap.Offcanvas(document.getElementById(`scheduleClinicOffcanvas${clinicId}`));
    offcanvasInstance.show();

    console.log(element.parentElement);
}

// Doctor's Profile
show_doctor_profile.addEventListener("click", () => {
    doctor_profile.show();
});

{
    // opens clinics, manage clinic, appointments
    open(b1, intro1);
    open(b2, intro2);
    open(b3, intro3);
    open(b4, intro4);
    open(b5, intro5);

    // removes invalid when mouse comes over input field
    removeInvalid(contact, "is-invalid")
    removeInvalid(address, "is-invalid")
    removeInvalid(fees, "is-invalid")
}

// Remove alert msg if not choosing city
city.addEventListener("mouseenter", () => {
    cityc.classList.add("d-none");
});

// adding clinics
ClinicAdd.addEventListener("click", () => {
    let flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false;
    const contactcheck = new RegExp("^[1-9][0-9]{9}$");
    const feescheck = new RegExp("^[1-9]?[0-9]{1,3}$");

    if(city.value != "")
        flag1 = true;
    if(address.value != "")
        flag3 = true;
    if(naam.value != "")
        flag6 = true
    flag2 = contactcheck.test(contact.value);
    flag4 = feescheck.test(fees.value);
    flag5 = TAC.checked;

    if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6){
        saveClinic().then((data) => {
            if(data == "true"){
                successful_msg.classList.remove('d-none');
                clinic_adding_form.reset();
                setTimeout(() => {
                    successful_msg.classList.add('d-none');
                }, 3000);
                ClinicAdd.disabled = true;   //Disable the save clinic button after clinic successfully saved
                ClinicAdd.innerText = "Upload First";
                clinicUpload.disabled = false; //Enable save photo button 
            } else{
                console.log("CLinic not added");
            }
        }).catch((err) => {
            console.log(err);
        })

    } else{
        if(!flag1){
            cityc.classList.remove("d-none");
        } else if(!flag2){
            contact.classList.add("is-invalid");
        } else if(!flag3){
            address.classList.add("is-invalid");
        } else if(!flag4){
            fees.classList.add("is-invalid");
        } else if(!flag5){
            alert("Error in TAC");
        } else if(!flag6){
            alert("Error in name");
        }
    }
});

// uploading clinic photo
clinicUpload.addEventListener("click", () => {
    const saveClinicImg = async () => {
        let fd = new FormData();
        fd.append("clinicImage", formFile.files[0]); 

        let result = await fetch("save_clinic_img.do", {
            method: "POST",
            body: fd,
        });
        return await result.text();
    }

    saveClinicImg().then((data) =>{
        if(data == "ERROR"){
            alert("img Already exists with this name or some error occur while uploading");
        } else{
            clinicUpload.disabled = true;
            ClinicAdd.disabled = false;
            ClinicAdd.innerText = "Save Clinic";
            photoName.value = data;
            formFile.disabled = true;
        }
    }).catch((err) => {
        console.log("Error while uploading clinic Img", err);
    })
});

// Managing working hours of clinics and clinic
let mx_app = null;
document.addEventListener("click", (ev) => {
    const edit_hrs_btn = ev.target.closest('.edit_hours_btn');
    const cancel = ev.target.closest('.cancel_editbox_btn');
    const save_working_hours = ev.target.closest('.save_working_hours');
    
    if (edit_hrs_btn) {
        const box = edit_hrs_btn.closest('.clinic_hours');
        const completeClinic = edit_hrs_btn.closest('.complete_clinic');
        
        const edit_box_content = completeClinic.querySelector('.edit_box_content');

        edit_box_content.innerHTML = `
                                    <div class="container p-3 bg-success text-white rounded position-relative" style="max-width: 800px; height: 90vh; overflow-y: auto">
                                        <div class="row">
                                            <div class="col pt-2 set_clinic_name"></div>
                                            <div class="col-3 mb-3 pb-1 text-end">
                                                <span class="fw-bold close-btn btn text-white cancel_editbox_btn">Cancel</span>
                                            </div>
                                        </div>
                                        <div class="row text-center">
                                            <h3>Fill below info to edit Working Hours</h3>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <form class="mt-3" style="max-height: 80vh;">
                                                    <input type="hidden" class="hidden_clinic_id">
                                                    
                                                    ${['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'].map(day => `
                                                        <div class="d-flex flex-column flex-md-row align-items-center gap-2 mb-2 border-bottom px-2 mt-1 pb-2">
                                                            <div class="col-3"> 
                                                                <label class="fw-bold col-md-8 text-sm-end">${day}:</label>
                                                            </div>
                                                            <div class="col-md-8 d-flex align-items-center gap-2 w-75">
                                                                <input class="form-control form-control-sm text-center w-25 ms-3" name="${day.toLowerCase()}s" id="${day.toLowerCase()}s" type="time">
                                                                <span>to</span>
                                                                <input class="form-control form-control-sm text-center w-25" name="${day.toLowerCase()}e" id="${day.toLowerCase()}e" type="time">
                                                            </div>
                                                        </div>`).join('')}
                                                        <div>
                                                            <label for="mx_app" class="my-lg-3 fw-semibold">Avg Appointments /weeks: </label>
                                                            <input class="mx_app rounded text-center w-25" type="number" id="mx_app" placeholder="enter here..">
                                                        </div>
                                                </form>
                                                <div class="d-flex justify-content-md-center justify-content-end mt-3">
                                                    <button class="btn btn-sm btn-primary w-auto px-4 rounded save_working_hours" id="save_working_hours">
                                                        Save Changes
                                                    </button>
                                                </div>



                                            </div>
                                        </div>
                                    </div>
                                    `;

        const clinicId = box.querySelector('.clinicId').value;
        const clinicName = completeClinic.querySelector('.clinic_name').innerText;

        // set values in slided box
        heading.innerText = clinicName;
        document.querySelector(".set_clinic_name").append(heading);
        document.querySelector(".hidden_clinic_id").value = clinicId;

        // Show the sliding box
        const editBox = document.getElementById("editBox");
        editBox.style.top = "0"; // Slide down
        document.body.style.overflow = "hidden";
        navbar.classList.add("d-none");
        footer.classList.add("d-none");
        
        mx_app = completeClinic.querySelector('.mx_app'); //Just an assignment for future use
    } else if(cancel){
        // Close Button Event
        const editBox = document.getElementById("editBox");
        editBox.style.top = "-100%"; // Slide up
        document.body.style.overflow = "auto"; // Allow background scroll
        navbar.classList.remove("d-none");
        footer.classList.remove("d-none");
        heading.innerText = "";
    } else if (save_working_hours) {
        const clinicContainer = save_working_hours.closest('.complete_clinic');
        const saveWorkingHoursRequest = async () => {
            let formData = new URLSearchParams();
    
            formData.append("clinic_id", clinicContainer.querySelector(".hidden_clinic_id").value);
            formData.append("mx_appoinments", clinicContainer.querySelector(".mx_app").value);
    
            ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"].forEach(day => {
                formData.append(`${day}s`, clinicContainer.querySelector(`#${day}s`).value);
                formData.append(`${day}e`, clinicContainer.querySelector(`#${day}e`).value);
            });
    
            let response = await fetch('save_working_hours.do', {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: formData.toString()
            });
    
            return await response.text();
        };
    
        let flag = false;
        if (clinicContainer.querySelector(".mx_app").value != "") {
            flag = true;
        }
    
        if (flag) {
            saveWorkingHoursRequest().then((data) => {
                if (data === "Done") {
                    const editBox = document.getElementById("editBox");
                    editBox.style.top = "-100%"; 
                    document.body.style.overflow = "auto";
                    navbar.classList.remove("d-none");
                    footer.classList.remove("d-none");
                    heading.innerText = "";
                } else if (data === "Problem in inserting data") {
                    alert("Please fill time for at least one day");
                } else {
                    alert("Some issue occurred while saving your data");
                }
            })
            .catch((err) => {
                console.log("Some problem occurred when saving working hours", err);
            });
        } else {
            alert("Max Appointment must be filled!!!");
        }
    }
});

// Ajax Requests for fetching appointments
const fetchDiagnosed = async (doctorId) => {
    let result = await fetch(`fetch_diagonised_appointments.do?doctorId=${doctorId}`);
    return await result.json();
}
const fetchUndiagnosed = async (doctorId) => {
    let result = await fetch(`fetch_undiagonised_appointments.do?doctorId=${doctorId}`);
    return await result.json();
}
const fetchAllAppointments = async (doctorId) => {
    let result = await fetch(`fetch_all_appointments.do?doctorId=${doctorId}`);
    return await result.json();
}

const fetchAppointments = (doctorId) => {
    fetchDiagnosed(doctorId).then((data) => {
        if(data.length > 0){
            let diagnosedContainer = document.getElementById("Diagonised");
            let diagnosedDataContainer = document.getElementById('DiagonisedData');
            diagnosedDataContainer.classList.add('d-none');
            diagnosedContainer.classList.remove('d-none');
            diagnosedContainer.innerHTML = ""; 
            diagnosedDataContainer.innerHTML = ""; 

            let cardsHTML = "";
            data.slice(0, 4).forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);

                cardsHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3"> 
                        <div class="card shadow-lg rounded-4 bg-info appointments-undone-border-color">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });
            
            
            let dataHTML = "";
            data.forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);
                dataHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3"> 
                        <div class="card shadow-lg rounded-4 bg-info appointments-undone-border-color">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });

            diagnosedDataContainer.innerHTML = `<div class="row">${dataHTML}</div>`;
            diagnosedContainer.innerHTML = `<div class="row">${cardsHTML}</div>`;
        }
    })
    .catch((err) => {
        console.log("Problem in fetching Diagnosed Appointments: ", err);
    });

    fetchUndiagnosed(doctorId).then((data) => {
        if(data.length > 0){
            let unDiagonisedContainer = document.getElementById("UnDiagonised");
            let UndiagnosedDataContainer = document.getElementById('UndiagonisedData');
            unDiagonisedContainer.innerHTML = ""; 
            UndiagnosedDataContainer.innerHTML = ""; 
            UndiagnosedDataContainer.classList.add('d-none'); 
            unDiagonisedContainer.classList.remove('d-none'); 
    
            let cardsHTML = "";
            data.slice(0, 4).forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);
    
                cardsHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3"> 
                        <div class="card shadow-lg rounded-4 bg-info appointments-done-border-color">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });
    
            let dataHTML = "";
            data.forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);
                dataHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3"> 
                        <div class="card shadow-lg rounded-4 bg-info appointments-done-border-color">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });
    
            UndiagnosedDataContainer.innerHTML = `<div class="row">${dataHTML}</div>`;
            unDiagonisedContainer.innerHTML = `<div class="row">${cardsHTML}</div>`;
        }
        
    }).catch((err) => {
        console.log("Problem in fetching Undiagonised Appointments: ", err);
    })

    fetchAllAppointments(doctorId).then((data) => {
        if(data.length > 0){
            let AppointmentsContainer = document.getElementById("Diagonised_UnDiagonised");
            let AllDataContainer = document.getElementById('AllappointmentsData');
            AllDataContainer.classList.add('d-none');
            AppointmentsContainer.classList.remove('d-none');
            AppointmentsContainer.innerHTML = ""; 
            AllDataContainer.innerHTML = ""; 
    
            let cardsHTML = "";
            data.slice(0, 4).forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);
    
                cardsHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3 "> 
                        <div class="card shadow-lg rounded-4 bg-info ${appointment.status.statusId === 4 ? 'appointments-done-border-color' : 'appointments-undone-border-color'}">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });
    
            let dataHTML = "";
            data.forEach((appointment) => {
                let check = appointment.patient.user.profilePic;
                let img = `getImage.do?userId=${appointment.patient.user.userId}&img=${appointment.patient.user.profilePic}`;
                let imgSrc = check.startsWith("static") ? check : img;
                let age = calculateAge(appointment.patient.user.dob);
                dataHTML += `
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3"> 
                        <div class="card shadow-lg rounded-4 bg-info ${appointment.status.statusId === 4 ? 'appointments-done-border-color' : 'appointments-undone-border-color'}">
                            <img src="${imgSrc}" class="card-img-top" alt="Doctor" 
                                style="height: 150px; object-fit: cover; object-position: center 30%;">
                            <div class="card-body text-center">
                                <h6 class="fw-bold">${appointment.patient.user.name}</h6>
                                <p class="mb-1 text-muted">Age: ${age} yrs</p>
                                <a onclick='details(JSON.parse(decodeURIComponent("${encodeURIComponent(JSON.stringify(appointment))}")))' class="btn btn-dark btn-sm">More Details</a>
                            </div>
                        </div>
                    </div>
                `;
            });
    
            AllDataContainer.innerHTML = `<div class="row">${dataHTML}</div>`;
            AppointmentsContainer.innerHTML = `<div class="row">${cardsHTML}</div>`;
        }


    }).catch((err) => {
        console.log("Problem in fetching all Appointments: ", err);
    })

}

function showDiagonisedPatients(){
    document.getElementById("DiagonisedData").classList.remove('d-none');  
    document.getElementById("Diagonised").classList.add('d-none');  
}
function showUndiagonisedPatients(){
    document.getElementById("UndiagonisedData").classList.remove('d-none');  
    document.getElementById("UnDiagonised").classList.add('d-none');  
}
function showAllPatients(){
    document.getElementById("AllappointmentsData").classList.remove('d-none');  
    document.getElementById("Diagonised_UnDiagonised").classList.add('d-none');  
}
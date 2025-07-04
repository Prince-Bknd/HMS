const add_medicine = document.querySelector("#add_medicine");
const save_med = document.querySelector("#save_med");
const medicine_add_form = document.querySelector("#medicine_add_form");
const view_all_med = document.querySelector("#view_all_med");
const all_medicines = document.querySelector("#all_medicines");
const default_medicine = document.querySelector("#default_medicine");
const close_all_medicines = document.querySelector("#close_all_medicines");

const showFormatsBtn = document.querySelector("#showFormatsBtn");
const allUpdateFormatList = document.querySelector("#allUpdateFormatList");
const medicineFormatArray = document.querySelector("#medicineFormatArray");

const pharma_profile = new bootstrap.Modal("#pharma_profile");

// PharmaCompany profile
show_pharmaCompany_profile.addEventListener("click", () => {
    pharma_profile.show();
});

function UpdateDenomination(btn, medicineFormatId) {
    const formatData = btn.getAttribute("data-format");
    const unitData = btn.getAttribute("data-unit");
    const format = JSON.parse(formatData);
    const unit = JSON.parse(unitData);

    const modalElm = document.getElementById("updateMedicineDenominationModal");
    const updateModal = new bootstrap.Modal(modalElm);

    let denominationRows = "";

    if (format.medicineDenominations.length === 0) {
        denominationRows = `<div class="text-center text-muted mb-3">No denominations saved for this</div>`;
    } else {
        format.medicineDenominations.forEach((denomination, index) => {
            let unitOptions = "";
            unit.forEach(u => {
                const selected = u.id === denomination.Unit.id ? "selected" : "";
                unitOptions += `<option value="${u.id}" ${selected}>${u.name}</option>`;
            });
        
            denominationRows += `
                <div class="row mb-3 denomination-row existing-row">
                    <div class="col-md-6">
                        <label class="form-label fw-semibold">Quantity:</label>
                        <input class="form-control quantity" type="number" value="${denomination.quantity}" 
                            data-denomination-id="${denomination.medicineDenominationId}" 
                            data-format-id="${denomination.medicineFormatId}" />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-semibold">Unit:</label>
                        <select class="form-select unit" required>
                            ${unitOptions}
                        </select>
                    </div>
                </div>
            `;
        });
    }

    const elm = `
        <div class='modal-header'>
            <h1 class='modal-title fs-5'>Updating denomination of ${format.formatName}</h1>
            <button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>
        </div>
        <div class='modal-body'>
            <div class='container' id="denomination-container">
                ${denominationRows}
            </div>
            <div class="text-end">
                <button class="btn btn-outline-secondary btn-sm" type="button" id="add-denomination-btn">+ Add Denomination</button>
            </div>
        </div>
        <div class='modal-footer'>
            <small class="text-muted fw-semibold">Make sure to fill each and every boxes in order to avoide any kind of logical error</small>
            <button type="button" class="btn btn-primary" id="update-denominations-btn">Update</button>
        </div>
    `;

    document.getElementById("updateMedicineDenominationModalContent").innerHTML = elm;
    updateModal.show();

    let unitOptions = "";
    unitOptions += `<option value="0" selected class="d-none">Select unit</option>`;
    unit.forEach(u => {
        unitOptions += `<option value="${u.id}">${u.name}</option>`;
    });
    document.getElementById("add-denomination-btn").addEventListener("click", () => {
        const container = document.getElementById("denomination-container");
        const newRow = document.createElement("div");
        newRow.classList.add("row", "mb-3", "denomination-row", "new-row");
        newRow.innerHTML = `
            <div class="col-md-6">
                <label class="form-label fw-semibold">Quantity:</label>
                <input class="form-control quantity" type="number" placeholder="Enter quantity" />
            </div>
            <div class="col-md-6">
                <label class="form-label fw-semibold">Unit:</label>
                <select class="form-select unit" required>
                    ${unitOptions}
                </select>
            </div>
        `;
        container.appendChild(newRow);
    });

    // Update Denominations
    document.getElementById("update-denominations-btn").addEventListener("click", () => {
        const rows = document.querySelectorAll(".denomination-row");
        const denominationsToUpdate = [];

        rows.forEach(row => {
            const quantityInput = row.querySelector(".quantity");
            const unitInput = row.querySelector(".unit");

            const denominationId = quantityInput.dataset.denominationId || 0;

            const denomination = {
                medicineDenominationId: parseInt(denominationId), //0 when a new denomination is added
                medicineFormatId: medicineFormatId,
                quantity: parseInt(quantityInput.value), //NaN when not provided
                unitId: parseInt(unitInput.value) //0 when not selected
            };

            denominationsToUpdate.push(denomination);
        });
        const jsonString = JSON.stringify(denominationsToUpdate);

        // Send as application/x-www-form-urlencoded so server can access it via request.getParameter
        const formData = new URLSearchParams();
        formData.append("denominations", jsonString);

        fetch("update_denomination.do", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        })
        .then(res => res.text())
        .then(data => {
            if(data === "done"){
                location.reload();
            } else{
                console.log("Server Response:", data);
            }
        })
        .catch(err => {
            console.error("Error:", err);
        });

    });
}



const add_medicine_modal = new bootstrap.Modal("#add_medicine_modal");

add_medicine.addEventListener("click" ,() => {
    add_medicine_modal.show();
});

close_all_medicines.addEventListener("click", () => {
    all_medicines.classList.add("d-none");
    default_medicine.classList.remove("d-none");
});
view_all_med.addEventListener("click", () => {
    all_medicines.classList.remove("d-none");
    default_medicine.classList.add("d-none");
});

save_med.addEventListener("click", () => {
    const saveMedicineRequest = async () => {
        let formdata = new FormData(medicine_add_form);

        let result = await fetch("save_medicine.do", {
            method: "POST",
            body: formdata
        });
        return await result.text();
    }

    let flags = [false, false, false, false, false, false];
    let flag2 = false;
    let flag3 = false;

    let check = new RegExp("^(?!\\s).+$");

    let fields = [
        "#medicineName",
        "#description",
        "#ingredients",
        "#effectiveBodyPart",
        "#sideEffects",
        "#warnings"
    ];

    let formatSelect = document.querySelector("#formats");
    if (formatSelect.value !== "0") {
        flag2 = true;
    }

    formatSelect.addEventListener("change", function () {
        if (this.value !== "0") {
            this.classList.remove("error");
        }
    });

    fields.forEach((selector, index) => {
        let inputElement = document.querySelector(selector);
        let inputValue = inputElement.value;

        flags[index] = check.test(inputValue);

        inputElement.addEventListener("input", function () {
            this.classList.remove("error");
        });
    });

    let fileInput = document.querySelector("#medicineImage");
    if (fileInput.files.length > 0) {
        flag3 = true;
    }

    fileInput.addEventListener("change", function () {
        this.classList.remove("error");
    });

    function scrollToError(element) {
        element.scrollIntoView({ behavior: "smooth", block: "center" });
    }

    if (flags.every(flag => flag) && flag2 && flag3) {
        saveMedicineRequest().then((data) => {
            add_medicine_modal.hide();
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(document.querySelector("#saved_medicine_msg"));
            toastBootstrap.show();
            location.reload();
        }).catch((err) => {
            console.log("Error while storing Medicine", err);
        });
    } else {
        let firstErrorElement = null;

        if (!flag3) {
            fileInput.classList.add("error");
            firstErrorElement = fileInput;
        } else if (!flag2) {
            formatSelect.classList.add("error");
            firstErrorElement = formatSelect;
        } else {
            let failedIndex = flags.findIndex(flag => !flag);
            let failedField = document.querySelector(fields[failedIndex]);
            failedField.classList.add("error");
            firstErrorElement = failedField;
        }

        if (firstErrorElement) {
            scrollToError(firstErrorElement);
        }
    }
});

// Editing Medicine here
const editMedicine = (id, userId, img, name, description, warnings, ingredients, effective_bodypart, side_effects) => {
    // Fetch medicine data (You can replace this with an actual API call)
    const medicineData = {
        medicineId: id,
        userId: userId,
        photo: img,
        name: name,
        description: description,
        warnings: warnings,
        ingredients: ingredients,
        effective_bodypart: effective_bodypart,
        side_effects: side_effects
    };
    // Show the modal using Bootstrap JS
    var editModal = new bootstrap.Modal(document.getElementById("editMedicineModal"));
    editModal.show();
    
    // Populate modal fields
    document.getElementById("medicineId").value = medicineData.medicineId;
    document.getElementById("medicineImg").src = 'getImage.do?userId='+ medicineData.userId + '&img=' + medicineData.photo;
    document.getElementById("medicineIngredients").value = medicineData.ingredients;
    document.getElementById("medicineEffectiveBodypart").value = medicineData.effective_bodypart;
    document.getElementById("medicineSideEffects").value = medicineData.side_effects;
    document.getElementById("medicineDescription").value = medicineData.description;
    document.getElementById("medicineWarnings").value = medicineData.warnings;
    document.getElementById("MedName").value = medicineData.name;

    showFormatsBtn.classList.remove('d-none');
    allUpdateFormatList.classList.add('d-none');
};

document.getElementById("editMedicineForm").addEventListener("submit", function(event) {
    event.preventDefault(); 

    let formData = new FormData(this); 

    let params = new URLSearchParams();
    for (let pair of formData.entries()) {
        params.append(pair[0], pair[1]);
    }

    fetch("update_medicine.do", { 
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: params.toString()
    })
    .then(response => response.text())
    .then(data => {
        location.reload(); 
    })
    .catch(error => console.error("Error:", error));
});

showFormatsBtn.addEventListener("click", function () {
    this.classList.add("d-none");
    allUpdateFormatList.classList.remove("d-none");

    let medicineId = document.getElementById("medicineId").value;

    fetch("get_medicine_formats.do?medicineId=" + medicineId)
        .then(response => response.json())
        .then(data => {
            medicineFormatArray.value = data;
            let formatArrayString = medicineFormatArray.value.trim();

            let selectedFormats = formatArrayString.split(",");
    
            document.querySelectorAll(".format-checkbox").forEach(checkbox => {
                if (selectedFormats.includes(checkbox.value)) {
                    checkbox.checked = true;
                } else{
                    checkbox.checked = false;
                }
            });
        })
        .catch(error => console.error("Error fetching formats:", error));
});

// save formats
document.getElementById("saveFormatsBtn").addEventListener("click", function () {
    let selectedFormats = [];

    document.querySelectorAll(".format-checkbox:checked").forEach(checkbox => {
        selectedFormats.push(checkbox.value);
    });

    let formBody = "medicineId=" + encodeURIComponent(document.getElementById("medicineId").value) +
                    "&selectedFormats=" + encodeURIComponent(selectedFormats.join(",")); 

    fetch("save_medicine_formats.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded" 
        },
        body: formBody
    })
    .then(response => response.text())
    .then(data => {
        showFormatsBtn.classList.remove('d-none');
        allUpdateFormatList.classList.add('d-none');
    })
    .catch(error => console.error("Error saving formats:", error));
});



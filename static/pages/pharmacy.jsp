<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="row my-4">
    <div class="col-3">

    </div>
    <div class="col-6 mt-4 text-center fs-4">
        Welcome, <b> ${sessionScope.user.name}</b>
    </div>
    <div class="col-3">
        <div class="navbar-brand btn float-end me-5 pe-3" id="show_pharmaCompany_profile">
            <img src="${sessionScope.user.profilePic}" class="rounded-circle" alt="loading.." style="height: 50px;">
            <div class="ps-2 text-center fw-semibold">${sessionScope.user.name}</div>
        </div>
    </div>
</div>

<!-- Modal Showing Pharma Company Profile -->
<div class="modal modal-xl fade" id="pharma_profile" data-bs-backdrop="static" data-bs-keyboard="false"
    tabindex="-1" aria-labelledby="pharmaProfileLabel" aria-hidden="true">
    <c:choose>
        <c:when test="${not empty pharmaCompany}">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: rgb(49, 124, 182);">
                        <h1 class="modal-title fs-5" id="pharmaProfileLabel">Pharma Company Profile</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body bg-primary bg-gradient text-white py-4">
                        <div class="container">
                            <!-- Profile Section -->
                            <div class="row justify-content-center text-center">
                                <div class="col-md-8">
                                    <h4 class="fw-bold mb-3">${pharmaCompany.user.email}</h4>
                                    <img src="${sessionScope.user.profilePic}"
                                        class="rounded-circle img-fluid border border-light shadow-lg"
                                        alt="Company Logo" style="width: 120px; height: 120px; object-fit: cover;">
                                </div>
                            </div>

                            <!-- Basic Details -->
                            <div class="row justify-content-center text-center mt-3">
                                <div class="col-md-8">
                                    <h5 class="fw-bold">${pharmaCompany.user.name}</h5>
                                    <p class="mb-1">${pharmaCompany.user.contact}</p>
                                    <p class="mb-3">
                                        <c:choose>
                                            <c:when test="${empty pharmaCompany.user.address}">Address Not Updated
                                            </c:when>
                                            <c:otherwise>${pharmaCompany.user.address}</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>

                            <!-- Pharma Company Information Grid -->
                            <div class="row mx-md-5 mt-4">
                                <!-- Column 1 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom">
                                        <strong>Date of Establishment:</strong>
                                        <span>
                                            <c:choose>
                                                <c:when test="${empty pharmaCompany.user.dob}">Not Provided</c:when>
                                                <c:otherwise>${pharmaCompany.user.dob}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                    <div class="py-2 border-bottom">
                                        <strong>Password:</strong> <span>********</span>
                                        <a href="#" class="ms-2 text-warning" title="Reset Password">Reset</a>
                                    </div>
                                    <div class="py-2 border-bottom"><strong>Details:</strong>
                                        ${pharmaCompany.details}
                                    </div>
                                </div>

                                <!-- Column 2 -->
                                <div class="col-lg-6">
                                    <div class="py-2 border-bottom d-flex align-items-center">
                                        <strong class="me-2">License:</strong>
                                        <span class="flex-grow-1">${pharmaCompany.license}</span>
                                        <a href="getPharmaLicense.do?company=${pharmaCompany.user.userId}"
                                            target="_blank" class="ms-2" title="Show License">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                fill="gold" class="bi bi-award" viewBox="0 0 16 16"
                                                style="cursor: pointer;">
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
                                <div class="h3">Pharma Company not found, Login again</div>
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

<!-- Default showing medicines addded by you -->
<div class="row mx-3 p-3 shadow-lg mt-4 mb-2 rounded" style="background-color: rgb(14, 218, 218);">
    <div class="col">
        <div class="row d-flex justify-content-between align-items-center border-bottom rounded">
            <div class="col-auto fw-bold h3 ps-3 text-secondary heading">
                Medicines added by you
            </div>
            <div class="col-auto">
                <div class="row me-2">
                    <div class="col">
                        <button type="button" class="btn btn-sm fw-bold mb-1 rounded" id="add_medicine"
                            style="background-color: rgb(116, 238, 79);">Add medicine</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-sm bg-success-subtle my-2 fw-semibold"
                            id="view_all_med">view
                            all</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- By default medicine -->
        <div class="row" id="default_medicine">
            <div class="col">
                <div class="row mt-2 shadow-sm rounded p-3" style="background-color: rgb(106, 187, 253);">

                    <c:choose>
                        <c:when test="${empty myMedicines}">
                            <div class="col">
                                <div
                                    class="row justify-content-center fw-semibold fs-4 text-dark-emphasis w-50 m-auto">
                                    None of the Medicine is added by you!!!
                                </div>
                                <div class="row w-25 m-auto mt-1">
                                    <button type="button"
                                        class="btn btn-sm btn-outline-light btn-primary rounded fw-semibold">+ add
                                        medicine</button>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- All cards here (Set its limit to 4 only) -->
                            <c:forEach var="med" items="${myMedicines}" varStatus="loop" begin="0" end="3">
                                <div class="card m-auto my-3 shadow-lg rounded-4 border-0 card-style">
                                    <img src="getImage.do?userId=${pharmaCompany.user.userId}&img=${med.pic}"
                                        class="card-img-top card-img mt-2 p-1 shadow-sm" alt="Image not found">

                                    <!-- Medicine Name -->
                                    <h5 class="card-title fw-bold m-auto text-dark my-1">${med.name}</h5>

                                    <!-- Medicine Details -->
                                    <div class="card-text text-muted mx-auto overflow-auto px-4"
                                        style="height: 3.7vh;">
                                        <strong>${med.description}</strong>
                                    </div>
                                    <div class="card-body text-center overflow-auto" style="height: 10vh;">
                                        <div class="mb-0">
                                            <div class="card-text text-warning my-0">
                                                <strong>Warnings:</strong>
                                                <small>${med.warnings}
                                                    <!-- <a class="text-primary fw-semibold"
                                                    style="cursor: pointer; text-decoration: none;">read more</a> -->
                                                </small>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- Edit Button -->
                                    <div onclick="editMedicine(`${med.medicineId}`, `${pharmaCompany.user.userId}`, `${med.pic}`, `${med.name}`, `${med.description}`, `${med.warnings}`, `${med.ingredients}`, `${med.effectiveBodypart}`, `${med.sideEffects}`)"
                                        class="btn btn-sm btn-outline-primary d-flex align-items-center justify-content-center gap-2 px-1 py-1 mb-2 edit-btn">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                            <path
                                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                            <path fill-rule="evenodd"
                                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
                                        </svg>
                                        Edit
                                    </div>
                                </div>
                            </c:forEach>

                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

        <!-- Showing all medicines -->
        <div class="container border my-3 rounded d-none" id="all_medicines" style="background-color: aquamarine;">
            <div class="d-flex justify-content-end">
                <button type="button" class="btn-close mt-2 me-2" id="close_all_medicines"></button>
            </div>

            <div class="row p-3 overflow-auto" style="height: 100vh;">
                <div class="column">
                    <c:forEach var="med" items="${myMedicines}">
                        <div class="row g-0 bg-body-secondary position-relative bg-primary rounded-top p-2">
                            <div class="col-lg-3 col-md-6 col-12 mb-md-0 p-md-4">
                                <img src="getImage.do?userId=${pharmaCompany.user.userId}&img=${med.pic}"
                                    class="rounded img-fluid w-100 h-100 object-fit-cover" alt="...">
                            </div>                                   
                            <div class="col-lg p-4 ms-3 p-3">
                                <div class="row">
                                    <div class="col">
                                        <h3 class="mt-0">${med.name}</h3>
                                    </div>
                                    <div class="col">
                                        <button type="button"
                                            class="btn btn-sm btn-outline-light btn-primary rounded fw-semibold"
                                            onclick="editMedicine(`${med.medicineId}`, `${pharmaCompany.user.userId}`, `${med.pic}`, `${med.name}`, `${med.description}`, `${med.warnings}`, `${med.ingredients}`, `${med.effectiveBodypart}`, `${med.sideEffects}`)">edit
                                            this medicine</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <strong>${med.description}</strong>
                                        <div>
                                            <b>ingredients used:</b> ${med.ingredients}
                                        </div>
                                        <div>
                                            <b>Effective Bodypart:</b> ${med.effectiveBodypart}
                                        </div>
                                        <div>
                                            <b>Side Effects of this medicine:</b> ${med.sideEffects}
                                        </div>
                                        <div>
                                            <b>Warnings:</b> ${med.warnings}
                                        </div>
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty med.medicineFormats}">
                                                    <b>Available Formats:</b>
                                                    <c:forEach var="format" items="${med.medicineFormats}"
                                                        varStatus="status">
                                                        ${format.format.name}<c:if test="${!status.last}">, </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <b>Available Formats:</b> No format available.
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <%-- ===========================================================Format in parameter================================================================================================================================================== --%>
                                    <div class="col shadow p-3 rounded"
                                        style="background-color: rgb(221, 247, 240);">
                                        <div class="row m-2 p-1 overflow-auto" style="max-height: 20vh;">
                                            <c:choose>
                                                <c:when test="${not empty med.medicineFormats}">
                                                    <table class="table table-bordered table-striped">
                                                        <thead style="background-color: rgb(75, 65, 65);">
                                                            <tr>
                                                                <th>Format</th>
                                                                <th class="w-50">Quantity</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="format" items="${med.medicineFormats}">
                                                                <c:set var="formatName" value="${format.format.name}" />
                                                                <c:set var="formatJson">
                                                                    {"formatId":"${format.format.formatId}",
                                                                    "formatName":"${fn:escapeXml(format.format.name)}",
                                                                    "medicineDenominations":[
                                                                    <c:forEach var="medDenom" items="${format.medicineDenominations}" varStatus="loop">
                                                                        {"medicineDenominationId":"${medDenom.medicineDenominationId}",
                                                                        "medicineFormatId":"${medDenom.medicineFormat.medicineFormatId}",
                                                                        "quantity":"${medDenom.quantity}",
                                                                        "unitId":"${medDenom.unit.unitId}",
                                                                        "Unit":{"id":"${medDenom.unit.unitId}","name":"${fn:escapeXml(medDenom.unit.name)}"}}
                                                                        <c:if test="${!loop.last}">,</c:if>
                                                                    </c:forEach>
                                                                    ]}
                                                                </c:set>
                                                                <c:set var="unitsJson">
                                                                    [<c:forEach var="unit" items="${units}" varStatus="loop">
                                                                        {"id": "${unit.unitId}",
                                                                        "name": "${fn:escapeXml(unit.name)}"}<c:if test="${!loop.last}">,</c:if>
                                                                    </c:forEach>]
                                                                </c:set>

                                                                <tr>
                                                                    <td>${format.format.name}</td>
                                                                    <td>
                                                                        <c:forEach var="medDenom" items="${format.medicineDenominations}">
                                                                            <li>${medDenom.quantity} ${medDenom.unit.name}</li>
                                                                        </c:forEach>
                                                                    </td>
                                                                    <td>
                                                                        <button type="button"
                                                                            class="btn btn-sm btn-info text-white rounded fw-semibold"
                                                                            data-format='${fn:escapeXml(formatJson)}'
                                                                            data-unit='${fn:escapeXml(unitsJson)}'
                                                                            onclick="UpdateDenomination(this, `${format.medicineFormatId}`)">
                                                                            Update
                                                                        </button>

                                                                        <button type="button"
                                                                            class="btn btn-sm btn-danger text-white rounded fw-semibold">
                                                                            Delete
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="text-muted text-center w-100">Format not available</div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty myMedicines}">
                        NO MEDICINE FOUND
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Modal To update Medicine Denominations -->
        <div class="modal fade" id="updateMedicineDenominationModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="updateMedicineDenominationModalContent"></div>
            </div>
        </div>
        <!-- Modal To add Medicine Denominations -->
        <div class="modal fade" id="addMedicineDenominationModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="addMedicineDenominationModalContent"></div>
            </div>
        </div>

    </div>
</div>

<!-- Add Medicine Modal -->
<div class="modal fade" id="add_medicine_modal" data-bs-backdrop="static" tabindex="-1"
    aria-labelledby="addMedicineModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background-color: #ADD8E6;">
            <div class="modal-header">
                <h4 class="modal-title fw-bold" id="addMedicineModalLabel">Add New Medicine</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="medicine_add_form" enctype="multipart/form-data">
                    <!-- Upload Medicine Image -->
                    <div class="mb-3">
                        <label for="medicineImage" class="form-label h5 fw-semibold">Upload Medicine Image</label>
                        <input type="file" class="form-control medicine-add-inp" name="medicineImage"
                            id="medicineImage" accept="image/*" required>
                        <div class="form-text ms-2 ps-2">Upload an image of the medicine (JPG, PNG, etc.)</div>
                    </div>
                    <!-- Medicine Name -->
                    <div class="mb-3">
                        <label for="medicineName" class="form-label h5 fw-semibold">Medicine Name</label>
                        <input type="text" class="form-control medicine-add-inp" name="name" id="medicineName"
                            placeholder="Enter medicine name" required>
                        <div class="form-text ms-2 ps-2">E.g., Paracetamol, Ibuprofen</div>
                    </div>
                    <!-- Description -->
                    <div class="mb-3">
                        <label for="description" class="form-label h5 fw-semibold">Description</label>
                        <textarea class="form-control medicine-add-inp" name="description" id="description" rows="3"
                            placeholder="Provide a brief description" required></textarea>
                    </div>
                    <!-- Ingredients -->
                    <div class="mb-3">
                        <label for="ingredients" class="form-label h5 fw-semibold">Ingredients</label>
                        <textarea class="form-control medicine-add-inp" id="ingredients" name="ingredients" rows="3"
                            placeholder="List the active ingredients" required></textarea>
                    </div>
                    <!-- Effective Body Part -->
                    <div class="mb-3">
                        <label for="effectiveBodyPart" class="form-label h5 fw-semibold">Effective Body Part</label>
                        <textarea class="form-control medicine-add-inp" id="effectiveBodyPart"
                            name="effectiveBodyPart" rows="2" placeholder="Specify the targeted body part"
                            required></textarea>
                    </div>
                    <!-- Side Effects -->
                    <div class="mb-3">
                        <label for="sideEffects" class="form-label h5 fw-semibold">Side Effects</label>
                        <textarea class="form-control medicine-add-inp" id="sideEffects" name="sideEffects" rows="3"
                            placeholder="List possible side effects" required></textarea>
                    </div>
                    <!-- Warnings -->
                    <div class="mb-3">
                        <label for="warnings" class="form-label h5 fw-semibold">Warnings</label>
                        <textarea class="form-control medicine-add-inp" id="warnings" rows="3" name="warnings"
                            placeholder="Provide any warnings" required></textarea>
                    </div>
                    <!-- Formats -->
                    <div class="mb-3">
                        <label class="form-label h5 fw-semibold">Available Formats</label>
                        <div id="formats" class="row">
                            <c:forEach var="format" items="${formats}" varStatus="status">
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="formats"
                                            id="format-${format.formatId}" value="${format.formatId}">
                                        <label class="form-check-label" for="format-${format.formatId}">
                                            ${format.name}
                                        </label>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success btn-sm" id="save_med">Save Medicine</button>
            </div>
        </div>
    </div>
</div>

<!-- Popup When medicine saved -->
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="saved_medicine_msg"
        class="toast bg-success rounded w-75 h-100 text-center h4 fw-bold border shadow-lg p-1 text-light"
        role="alert" aria-live="assertive" aria-atomic="true">
        Medicine Saved :)
    </div>
</div>

<!-- Edit Medicine Modal -->
<div class="modal fade bg-primary" id="editMedicineModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: rgb(88, 168, 142);">
                <h4 class="modal-title fw-semibold">Edit Medicine</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body p-5" style="background-color: aquamarine;">
                <form id="editMedicineForm">
                    <input type="hidden" name="medicineId" id="medicineId">
                    <div class="mb-3 text-center">
                        <img id="medicineImg" class="img-fluid rounded" name="medicineImg" alt="Loading..."
                            style="width: 150px; height: 150px; object-fit: cover;">
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Name</label>
                        <input type="text" class="form-control input-field" name="MedName" id="MedName" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Ingredients</label>
                        <input type="text" class="form-control input-field" name="medicineIngredients"
                            id="medicineIngredients" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Effective Bodypart</label>
                        <input type="text" class="form-control input-field" name="medicineEffectiveBodypart"
                            id="medicineEffectiveBodypart">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Side Effects</label>
                        <input type="text" class="form-control input-field" name="medicineSideEffects"
                            id="medicineSideEffects">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Description</label>
                        <textarea class="form-control input-field" name="medicineDescription"
                            id="medicineDescription"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Warnings</label>
                        <textarea class="form-control input-field" name="medicineWarnings"
                            id="medicineWarnings"></textarea>
                    </div>

                    <div class="text-center my-3">
                        <button type="button" class="btn btn-secondary btn-sm" id="showFormatsBtn">Show
                            Formats</button>
                    </div>

                    <div class="mb-3 d-none" id="allUpdateFormatList">
                        <label class="form-label h5 fw-semibold">Update Formats</label>
                        <div id="updateFormats" class="row">
                            <input type="text" class="form-control input-field d-none" id="medicineFormatArray">

                            <c:forEach var="format" items="${formats}" varStatus="status">
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input input-field format-checkbox" type="checkbox"
                                            name="updateFormats" id="updateFormat-${format.formatId}"
                                            value="${format.formatId}">
                                        <label class="form-check-label" for="updateFormat-${format.formatId}">
                                            ${format.name}
                                        </label>
                                    </div>
                                </div>
                            </c:forEach>
                            <small class="fw-bold">Note: <span class="text-danger">You must choose atleast one to reflect any changes</span></small>
                        </div>

                        <div class="d-flex justify-content-end mt-3">
                            <div class="btn btn-primary btn-sm" id="saveFormatsBtn">Save Formats</div>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-success btn-lg">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- ----------------------------------------------------------------------------------------------------------- -->

<!-- Popular medicines -->
<div class="row mx-3 p-3 shadow-lg mt-4 mb-2 rounded" style="background-color: rgb(14, 218, 218);">
    <div class="col">
        <div class="row d-flex justify-content-between align-items-center border-bottom rounded">
            <div class="col-auto fw-bold h3 ps-3 text-secondary heading">
                Most Popular Medicines
            </div>
            <div class="col-auto">
                <div class="row me-2">
                    <button type="button" class="btn btn-sm bg-success-subtle border my-2 fw-semibold mb-2">view
                        all</button>
                </div>
            </div>
        </div>

        <!-- Content here -->
        <div class="row">
            <div class="col">
                <div class="row mt-2 shadow-sm rounded p-3" style="background-color: rgb(106, 187, 253);">

                    <c:choose>
                        <c:when test="true">
                            <div class="col">
                                <div
                                    class="row justify-content-center fw-semibold fs-4 text-dark-emphasis w-50 m-auto">
                                    Unavialable :(
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>

                            <!-- All cards here -->
                            <div class="card m-auto my-3 shadow-lg rounded-4 border-0 card-style">
                                <img src="static/media/images/logo.jpeg"
                                    class="card-img-top card-img mt-2 p-1 shadow-sm" alt="Image not found">

                                <div class="card-body text-center">
                                    <!-- Medicine Name -->
                                    <h5 class="card-title fw-bold text-dark mb-2">Paracetamol</h5>

                                    <!-- Medicine Category -->
                                    <p class="card-text text-muted fs-6">
                                        Pain Reliever
                                    </p>

                                    <!-- Button with Hover Effect -->
                                    <a href="#"
                                        class="btn btn-sm btn-outline-primary d-flex align-items-center justify-content-center gap-2 px-3 py-2 edit-btn">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                            <path
                                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                            <path fill-rule="evenodd"
                                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
                                        </svg>
                                        Edit
                                    </a>
                                </div>
                            </div>



                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</div>

<%-- Since c:forEach is used --%>
<script>
    // Fetch Denominations
    function fetchDenominations(element, medicineFormats){
        alert("clicked button to fetch denominations");
        console.log(medicineFormats);
        for(let mf of medicineFormats){
            console.log(mf.medicineFormatId);
        }
    }

    // Update Medicine Denominations
    
    function addDenomination(medicineName, medicineId, medicineFormats){
        console.log(medicineName, medicineFormats);
        // Medicine Id Required*
        <c:set var="uname" value="\${medicineName}" />
        const modalElm = document.getElementById("addMedicineDenominationModal");
        const updateModal = new bootstrap.Modal(modalElm);

        <c:set var="mid" value="\${medicineId}" />
        console.log("Denominations are:  ",`${medFormats}`);
        
        let elm = `
                <div class='modal-header'>
                    <h1 class='modal-title fs-5 fw-bold'>Adding denomination for ${uname}</h1>
                    <button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>
                </div>
                <form action="testing.doing" method="get" id="addMedicineDenominationForm">
                    <div class='modal-body'>
                        <div class='container'>
                            <div class="row mb-3 align-items-center">
                                <div class="col-12 col-lg-4">
                                    <label class="form-label mb-0 fw-semibold" for="formatSelect">Choose Format:</label>
                                </div>
                                <div class="col-12 col-lg-8">
                                    <select class="form-control text-center w-75" name="format" id="formatSelect" style="cursor: pointer" required>
                                        <option value="0" selected class="d-none">Click here to choose</option>
                                        <c:forEach var="f" items="${formats}">
                                            <option value="${f.formatId}">${f.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row mb-3 align-items-center">
                                <div class="col-12 col-lg-4">
                                    <label class="form-label mb-0 fw-semibold" for="quantityInput">Quantity:</label>
                                </div>
                                <div class="col-12 col-lg-8">
                                    <input class="form-control w-75 text-center" name="quantity" type="number" id="quantityInput" placeholder="Recommended quantity" required />
                                </div>
                            </div>
                            <div class="row mb-3 align-items-center">
                                <div class="col-12 col-lg-4">
                                    <label class="form-label mb-0 fw-semibold" for="unitSelect">Choose Unit:</label>
                                </div>
                                <div class="col-12 col-lg-8">
                                    <select class="form-control text-center w-75" name="unit" id="unitSelect" style="cursor: pointer" required>
                                        <option value="0" selected class="d-none">Click here to choose</option>
                                        <c:forEach var="u" items="${units}">
                                            <option value="${u.unitId}">${u.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>    
                </form>
            `;

        document.getElementById("addMedicineDenominationModalContent").innerHTML = elm;
        updateModal.show();

        document.getElementById("addMedicineDenominationForm").addEventListener("submit", (ev) => {
            ev.preventDefault();

            const form = ev.target;

            fetch('add_denomination.do?format=' + form.querySelector("#formatSelect").value + '&quantity=' + form.querySelector("#quantityInput").value + '&unit=' + form.querySelector("#unitSelect").value + '&medicineId=' + `${mid}`, {
                method: 'POST'
            })
            .then(response => {
                if (!response.ok) throw new Error("Network response was not OK");
                return response.text(); 
            })
            .then(data => {
                console.log("Response from server:", data);
            })
            .catch(error => {
                console.error("Error adding denomination:", error);
            });
        });
    }

</script>

<%--
<div class="row m-2 p-1 overflow-auto" style="max-height: 20vh;">
    <table>
        <thead>
            <tr>
                <th>Format</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty med.medicineFormats}">                                                       
                    <c:set var="mdi" value="${medDeno.medicineDenominationId}" />
                    <c:forEach var="medicineFormat"
                        items="${med.medicineFormats}">
                        <c:if
                            test="${not empty medicineFormat.medicineDenominations}">
                            <c:set var="hasDenomination" value="true" />
                            <c:forEach var="medDeno"
                                items="${medicineFormat.medicineDenominations}">
                                <tr>
                                    <td>${medDeno.medicineFormat.format.name}
                                    </td>
                                    <td>${medDeno.quantity}
                                        ${medDeno.unit.name}</td>
                                    <td>
                                        <c:set var="mdi" value="${medDeno.medicineDenominationId}" />
                                        <c:set var="fname" value="${medDeno.medicineFormat.format.name}" />
                                        <c:set var="qnty" value="${medDeno.quantity}" />
                                        <c:set var="uname" value="${medDeno.unit.name}" />
                                        <button type="button"
                                            class="btn btn-sm btn-info text-white rounded fw-semibold"
                                            onclick="UpdateDenomination()">
                                            Update
                                        </button>
                                        <button type="button"
                                            class="btn btn-sm btn-danger text-white rounded fw-semibold"
                                            >
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                    <c:if test="${not hasDenomination}">
                        <tr>
                            <td colspan="2" class="text-center text-muted">
                                Denomination not set</td>
                        </tr>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="2" class="text-center text-muted">
                            Denomination not set</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    
    <button class="btn btn-success rounded mt-2" onclick="addDenomination(`${med.name}`, `${med.medicineId}`, `${med.medicineFormats}`)"> + Add Denomination</button>
</div>
--%>

<%-- 
    function UpdateDenomination(){
    const modalElm = document.getElementById("updateMedicineDenominationModal");
    const updateModal = new bootstrap.Modal(modalElm);
    
    let elm = `
            <div class='modal-header'>
                <h1 class='modal-title fs-5'>Updating denomination of ${fname} </h1>
                <button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>
            </div>
            <div class='modal-body'>
                <div class='container'>
                    <div class='row mb-3'>
                        <label class='form-label' for='formatSelect'>Choose Format:</label>
                        <select class='form-control' id='formatSelect' required>
                            <c:forEach var='f' items='${formats}'>
                                <c:choose>
                                    <c:when test="${f.name == fname}">
                                        <option value="${f.formatId}" selected>${f.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${f.formatId}">${f.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="row mb-3">
                        <label class="form-label" for="quantityInput">Quantity:</label>
                        <input class="form-control" type="number" id="quantityInput" value="${qnty}" required />
                    </div>

                    <div class="row mb-3">
                        <label class="form-label" for="unitSelect">Choose Unit:</label>
                        <select class='form-control' id='unitSelect' required>
                            <c:forEach var='u' items='${units}'>
                                <c:choose>
                                    <c:when test="${u.name == uname}">
                                        <option value="${u.unitId}" selected>${u.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${u.unitId}">${u.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Update</button>
            </div>
        `;


    document.getElementById("updateMedicineDenominationModalContent").innerHTML = elm;
    updateModal.show();
}
--%>

// const user_type = document.querySelector("#user_type");
// const ut = document.querySelector("#ut");
// const confirmed = document.querySelector("#confirmed");
// const not_confirmed = document.querySelector("#not_confirmed");
// const signinFL = document.querySelector("#signinFL");
// const cname = document.querySelector("#cname");
// const signup1BN = document.querySelector("#signup1BN");
// const cemail = document.querySelector("#cemail");
// const signup1BE = document.querySelector("#signup1BE");
// const signup1BE2 = document.querySelector("#signup1BE2");
// const cpassword = document.querySelector("#cpassword");
// const signup1BP = document.querySelector("#signup1BP");    
// const signup1BP2 = document.querySelector("#signup1BP2");    
// const ccontact = document.querySelector("#ccontact");
// const signup1BC = document.querySelector("#signup1BC");
// const signup1BC2 = document.querySelector("#signup1BC2");
// const signinBE = document.querySelector("#signinBE"); 
const signupFSC = document.querySelector("#signupFSC");
const signupFSCMB = document.querySelector("#signupFSCMB");

const signupFS = new bootstrap.Modal("#signupFS"); // Signup fail or success

// signup Failed or Success        
{
    if (signupStatus === "true") {
        signupFSC.classList.add("bg-success-subtle");
        signupFSC.classList.add("text-success");
        signupFSC.classList.add("box-shadow-green");
        signupFSCMB.innerHTML = "<svg xmlns='http://www.w3.org/2000/svg' width='30' height='40' fill='currentColor' class='bi bi-check-circle-fill text-success pt-1' viewBox='0 0 16 16'><path d='M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z'/></svg><p class='text-center ms-2 mb-2'>SignUp <b>SUCCESS!!!</b></p>";
        signupFS.show();
    } else if (signupStatus === "false"){
        signupFSC.classList.add("bg-danger-subtle");
        signupFSC.classList.add("text-danger");
        signupFSC.classList.add("box-shadow-red");
        signupFSCMB.innerHTML = "<svg xmlns='http://www.w3.org/2000/svg' width='32' height='50' fill='currentColor' class='bi bi-exclamation-triangle-fill text-danger pb-1 ms-1' viewBox='0 0 16 16'><path d='M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5m.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2'/></svg><p class='text-center mb-2'>Some Error Occur While Creating Your Profile!!!</p>";
        signupFSSI.innerText = "SignUp Again";
        signupFS.show();
    }
}

signupFSSI.addEventListener("click", () => {
    alert("Redirecting to Signup/Signin");
    // Redirect to signup or signin here
});



// const fieldoperationfocus = (field, workon, remove) => {
//     if(remove){
//         field.addEventListener("focus", () => {
//             workon.classList.remove("d-none");
//         });    
//     } else{
//         field.addEventListener("focus", () => {
//             workon.classList.add("d-none");
//         });    
//     }
// };
// const fieldoperationblur = (field, workon, remove) => {
//     if(remove){
//         field.addEventListener("blur", () => {
//             workon.classList.remove("d-none");
//         });
//     } else{
//         field.addEventListener("blur", () => {
//             workon.classList.add("d-none");
//         });
//     }
// };


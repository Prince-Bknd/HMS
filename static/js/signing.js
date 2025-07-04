const sbdoc = document.querySelector("#s1");
const sbpat = document.querySelector("#s2");
const sbphar = document.querySelector("#s3");

const signininv = document.querySelector("#signininv");
const signin = document.querySelector("#signin");

// Related to activation
const activation = new bootstrap.Modal("#activation"); //Activation modal
const activate_btn = document.querySelector("#activate_btn");

// For shadow while mouseover
const signup_box_1 = document.querySelector("#signup_box_1");
const signup_box_2 = document.querySelector("#signup_box_2");
const signup_box_3 = document.querySelector("#signup_box_3");

// Closing signup modal closing
const closing_confirmation = document.querySelector("#closing_confirmation");


const confirmation_alert = new bootstrap.Modal("#confirmation_alert");

const signup1 = document.querySelector("#signup1");
const signup_form1 = document.querySelector("#signup_form1");
 
const signup_modal = new bootstrap.Modal("#signup1");

// focus or blur in signin/signup fields(only Functions)
const fieldoperationfocus = (field, workon, remove) => {
    if (remove) {
        field.addEventListener("focus", () => {
            workon.classList.remove("d-none");
        });
    } else {
        field.addEventListener("focus", () => {
            workon.classList.add("d-none");
        });
    }
};
const fieldoperationblur = (field, workon, remove) => {
    if (remove) {
        field.addEventListener("blur", () => {
            workon.classList.remove("d-none");
        });
    } else {
        field.addEventListener("blur", () => {
            workon.classList.add("d-none");
        });
    }
};

// Work only for signin
if (signininv.value === "true") {
    // signin fields
    {
        const Lemail = document.querySelector("#Lemail");
        const LemailFT = document.querySelector("#LemailFT");
        const Lpassword = document.querySelector("#Lpassword");
        const LpasswordFT = document.querySelector("#LpasswordFT");

        fieldoperationfocus(Lpassword, LpasswordFT, true);
        fieldoperationblur(Lpassword, LpasswordFT, false);
        fieldoperationfocus(Lpassword, LpasswordFT2, false);

        fieldoperationfocus(Lemail, LemailFT, true);
        fieldoperationblur(Lemail, LemailFT, false);
        fieldoperationfocus(Lemail, LemailFT2, false);
    }

    // SignIn Form 
    {
        const SigninForm = document.querySelector("#SigninForm");
        const LpasswordFT2 = document.querySelector("#LpasswordFT2");
        const LemailFT2 = document.querySelector("#LemailFT2");
        SigninForm.addEventListener("submit", (ev) => {
            ev.preventDefault();
            let flag1 = true, flag2 = true;
            if (Lemail.value === "") {
                flag1 = false;
            }
            if (Lpassword.value === "") {
                flag2 = false;
            }
            if (flag1 && flag2) {
                const getResult = async () => {
                    let result = await fetch('signinCheck.do?email=' + Lemail.value + "&password=" + Lpassword.value);

                    if (result.ok) {
                        // return await result.json();
                        return await result.text();
                    } else {
                        return "Error in sending ajax request for signing in";
                    }

                };

                getResult().then((data) => {
                    if (data == "verified") {
                        const getResult2 = async () => {
                            let result2 = await fetch('activationcheck.do');

                            if (result2.ok) {
                                return await result2.text();
                            } else {
                                return "Error while  getting response from activationcheck";
                            }
                        }
                        getResult2().then((data2) => {
                            if (data2 != null) {
                                if (data2 == "0") {
                                    activation.show();
                                } else if(data2 == "Done") {
                                    SigninForm.submit();
                                } else if(data2 == "Error") {
                                    console.log("Need to contact Technician!! Some big error occurs");
                                }
                            } else {
                                alert("Error in getting response frin server after sending request to activationSevlet");
                            }

                        }).error((err) => {
                            console.log("Some error occur while fetching data via ajax request from activation check: ", err);
                        })

                    } else if (data === "unverified") {
                        signinBE.innerHTML = "<b>Incorrect Password</b>";
                        signinBE.classList.remove("d-none");
                    } else if (data == "asdf") {
                        signinBE.innerHTML = "<b>Provided details are Incorrect!!!</b>";
                        signinBE.classList.remove("d-none");
                    } else {
                        signinBE.innerHTML = "<b>Some Error Occur while Fetcing data</b>";
                        signinBE.classList.remove("d-none");
                    }
                }).catch((error) => {
                    console.log("Some error occur while getting json response: ", error);
                })
                // SigninForm.submit();

            } else {
                if (flag1 == false) {
                    LemailFT2.classList.remove("d-none");

                } else if (flag2 == false) {
                    LpasswordFT2.classList.remove("d-none");
                } else {
                    alert("Some Technical Issue occurs!!!");
                }
            }
        });

        signin.addEventListener("click", () => {
            signinBE.classList.add("d-none");
        });
    }
}

// Work only for signup
if (signininv.value === "") {
    // For shadow while mouseover
    signup_box_1.addEventListener("mouseenter", () => {
        signup_box_1.classList.add("signup-animation");
    });
    signup_box_1.addEventListener("mouseleave", () => {
        signup_box_1.classList.remove("signup-animation");
    });
    signup_box_2.addEventListener("mouseenter", () => {
        signup_box_2.classList.add("signup-animation");
    });
    signup_box_2.addEventListener("mouseleave", () => {
        signup_box_2.classList.remove("signup-animation");
    });
    signup_box_3.addEventListener("mouseenter", () => {
        signup_box_3.classList.add("signup-animation");
    });
    signup_box_3.addEventListener("mouseleave", () => {
        signup_box_3.classList.remove("signup-animation");
    });

    // To open modal of signup when click on three different cards
    sbdoc.addEventListener("click", () => {
        user_type.value = 1;
        ut.innerText = "Doctor";
        document.getElementById("signup_form1").reset();
        signup_modal.show();
    });
    sbpat.addEventListener("click", () => {
        user_type.value = 2;
        ut.innerText = "Patient";
        document.getElementById("signup_form1").reset();
        signup_modal.show();
    });
    sbphar.addEventListener("click", () => {
        user_type.value = 3;
        ut.innerText = "Pharmacy";
        document.getElementById("signup_form1").reset();
        signup_modal.show();
    });

    // This will open confirmation small Modal
    closing_confirmation.addEventListener("click", () => {
        signup1.classList.add("blur-effect");
        confirmation_alert.show();
    });

    // When click 'cancel' on confirmation page 
    not_confirmed.addEventListener("click", () => {
        signup1.classList.remove("blur-effect");
        confirmation_alert.hide();
    });

    // When click 'ok' on confirmation page
    confirmed.addEventListener("click", () => {
        signup1.classList.remove("blur-effect");
        confirmation_alert.hide();
        signup_modal.hide();
    });



    // signup fields (error displaying/removing)
    {
        fieldoperationfocus(cname, signup1BN, true);
        fieldoperationblur(cname, signup1BN, false);
        fieldoperationfocus(cname, signup1BN2, false);

        fieldoperationfocus(cemail, signup1BE, true);
        fieldoperationblur(cemail, signup1BE, false);
        fieldoperationfocus(cemail, signup1BE2, false);

        fieldoperationfocus(cpassword, signup1BP, true);
        fieldoperationblur(cpassword, signup1BP, false);
        fieldoperationfocus(cpassword, signup1BP2, false);

        fieldoperationfocus(ccontact, signup1BC, true);
        fieldoperationblur(ccontact, signup1BC, false);
        fieldoperationfocus(ccontact, signup1BC2, false);
    }

    // SignUp Form submit
    {
        const signup_form1 = document.querySelector("#signup_form1");
        signup_form1.addEventListener("submit", (ev) => {
            ev.preventDefault();

            //Form Validation here
            let flag1 = false, flag2 = false, flag3 = false, flag4 = true;
            const namecheck = new RegExp("^[A-Za-z0-9]+(?:[A-Za-z0-9\s]*[A-Za-z0-9])?$");
            const emailcheck = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$");
            const contactcheck = new RegExp("^[1-9][0-9]{9}$");
            flag1 = namecheck.test(cname.value);
            flag2 = emailcheck.test(cemail.value);
            flag3 = contactcheck.test(ccontact.value);
            if (cpassword.value === "") {
                flag4 = false;
            }
            if (flag1 && flag2 && flag3 && flag4) {
                signup_form1.submit();
            } else {
                if (!flag1) {
                    signup1BN2.classList.remove("d-none");
                }
                else if (!flag2) {
                    signup1BE2.classList.remove("d-none");
                }
                else if (!flag3) {
                    signup1BC2.classList.remove("d-none");
                }
                else if (!flag4) {
                    signup1BP2.classList.remove("d-none");
                }
                else {
                    alert("Some other technical issue occurs");
                }
            }
        });
    }

}
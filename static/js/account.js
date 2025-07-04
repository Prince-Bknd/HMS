const dSignUp = document.querySelector("#dSignUp");
const specialization = document.querySelector("#specialization");
const dSignUpC = document.querySelector("#dSignUpC");
const pSignUp = document.querySelector("#pSignUp");
const mSignUp = document.querySelector("#mSignUp");
const mSignUpC = document.querySelector("#mSignUpC");
const blood = document.querySelector("#blood");

// const signup1 = new bootstrap.Modal("#signup1-2");

if(dSignUp){
    dSignUp.addEventListener("submit", (ev) => {
        ev.preventDefault();
        let flag1 = true, flag2 = true;
    
        if(specialization.value == "Select specialization"){
            flag1 = false;
        }
        if(flag1){
            dSignUp.submit();
        } else{
            if(!flag1){
                dSignUpC.classList.remove("d-none");
            }
        }
    });
}

if(pSignUp){
    pSignUp.addEventListener("submit", (ev) => {
        ev.preventDefault();
        if(true){
            pSignUp.submit();
        }
    });

}

if(mSignUp){
    mSignUp.addEventListener("submit", (ev) => {
        ev.preventDefault();

        flag = false;

        if(blood.value != "click here.."){
            flag = true;
        }


        if(flag){
            mSignUp.submit();
        } else{
            if(!flag){
                mSignUpC.classList.remove("d-none");
            }
        }
    });
}
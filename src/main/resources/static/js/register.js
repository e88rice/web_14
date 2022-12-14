const inputNullCheck = [...document.getElementsByClassName("register-input")];
const nullCheckDiv = [...document.getElementsByClassName("null-check-div")];
const registerID = document.getElementById("registerID");
const registerPW = document.getElementById("registerPW");
const registerPWCheck = document.getElementById("registerPWCheck");
const registerEmail = document.getElementById("registerEmail");
const emailSelect = document.getElementById("email-select");
const registerTel = document.getElementById("registerTel");
const telSelect = document.getElementById("tel-select");
const submitBtn = document.getElementById("submitBtn");
const saveTel = document.getElementById("saveTel");
const saveEmail = document.getElementById("saveEmail");


inputNullCheck.forEach(function (element){
    element.onkeydown = function (event) {
        element.nextElementSibling.textContent = '';
    }
})

registerPW.onkeydown = function (element) {
    registerPW.onkeyup = function (event) {
        const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/
        if (!reg.test(registerPW.value)) {
            registerPW.nextElementSibling.textContent = "비밀번호는 대/소문자 + 숫자 + 특수문자 조합의 6자 이상이어야 합니다";
            return;
        }
        else{
            registerPW.nextElementSibling.textContent = '';
        }
    }
}

registerPWCheck.onkeydown = function (element){
    registerPWCheck.onkeyup = function (event){
        if(registerPW.value !== registerPWCheck.value){
            registerPWCheck.nextElementSibling.textContent = "비밀번호가 다릅니다";
        }
        else{
            registerPWCheck.nextElementSibling.textContent = '';
        }
    }
}

registerEmail.onkeydown = function (element){
    registerEmail.onkeyup = function (event) {
            const reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
            if(emailSelect.value === "직접입력"){
                if(!reg.test(registerEmail.value)){
                    registerEmail.nextElementSibling.textContent = "이메일 형식이 다릅니다";
                }
                else{
                    registerEmail.nextElementSibling.textContent = "";
                    saveEmail.value = registerEmail.value;
                }
            }
            else{
                if( !reg.test(registerEmail.value+"@"+emailSelect.value)){
                    registerEmail.nextElementSibling.textContent = "이메일 형식이 다릅니다";
                }
                else{
                    registerEmail.nextElementSibling.textContent = "";
                    saveEmail.value = registerEmail.value+"@"+emailSelect.value;
                }
            }
    }
}

emailSelect.onclick = function (element){
    const reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    if(emailSelect.value === "직접입력"){
        if(!reg.test(registerEmail.value)){
            registerEmail.nextElementSibling.textContent = "이메일 형식이 다릅니다";
        }
        else{
            registerEmail.nextElementSibling.textContent = "";
            saveEmail.value = registerEmail.value;
        }
    }
    else{
        if( !reg.test(registerEmail.value+"@"+emailSelect.value)){
            registerEmail.nextElementSibling.textContent = "이메일 형식이 다릅니다";
        }
        else{
            registerEmail.nextElementSibling.textContent = "";
            saveEmail.value = registerEmail.value+"@"+emailSelect.value;
        }
    }
}

registerTel.onkeydown = function (element){
    registerTel.onkeyup = function (event) {
            const reg = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/
            if(!reg.test(telSelect.value + registerTel.value)){
                registerTel.nextElementSibling.textContent = "휴대폰 번호 형식이 다릅니다";
            }
            else{
                registerTel.nextElementSibling.textContent = "";
                saveTel.value = telSelect.value+registerTel.value;
            }
    }
}

registerID.onkeydown = function (element) {
    registerID.onkeyup = function (event) {
        const regSpace = /\s/g;
        const reglength = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{6,10}$/
        if(regSpace.test(registerID.value)){
            registerID.nextElementSibling.textContent = "ID는 공백이 없어야 합니다";
        }
        else if(!reglength.test(registerID.value)){
            registerID.nextElementSibling.textContent = "ID가 너무 짧습니다";
        }
        else{
            registerID.nextElementSibling.textContent = "";
        }
    }
}

submitBtn.onclick = () => {
    let count = 0;
    nullCheckDiv.forEach(function (element){
        if (element.textContent !== ''){
            count += 1;
        }
    })
    if (count >= 1) {
        alert("제대로 입력되지 않은 정보가 있습니다")
    }
    else{ // 모든게 정상적으로 입력되었다면
        const registerForm = document.forms.namedItem("register-form");
        registerForm.action = '/users/register';
        registerForm.method = 'post';
        registerForm.submit();
    }

}








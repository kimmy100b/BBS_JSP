const joinForm = window.document.joinForm,
userPW = joinForm.userPassword,
pwContent = joinForm.querySelector("#pwContent");

function pwCheck(){
	if(userPW.value.length<6){
		pwContent.innerHTML="비밀번호의 길이가 6미만입니다";
	}else if(userPW.value.length>=12){
		pwContent.innerHTML="비밀번호의 길이가 12이상입니다";
	}else{
		pwContent.innerHTML="";
	}
}

userPW.addEventListener("input",pwCheck);



const logInSection = document.querySelector(".log-in-section");
const getStartedSection = document.querySelector(".get-started-section");

document.addEventListener('click', (e) => {
  if(e.target.classList.contains('click-get-started')){
    console.log("oi");
    getStartedSection.classList.add('actived');
    document.body.classList.add('body-no-scroll');
  }
  if(e.target.classList.contains('log-in')){
    logInSection.classList.add('actived');
    document.body.classList.add('body-no-scroll');
  }
  if(e.target.classList.contains('sing-in-from-get-started')){
    getStartedSection.classList.remove('actived');
    logInSection.classList.add('actived');
  }
  if(e.target.classList.contains('close-overlay')){
    getStartedSection.classList.remove('actived');
    logInSection.classList.remove('actived');
    document.body.classList.remove('body-no-scroll');
  }
});
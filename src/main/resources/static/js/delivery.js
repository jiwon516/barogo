$(document).ready(function() {
    if(localStorage.jwt === undefined || localStorage.jwt === null){
        alert('로그인 정보가 없습니다.');
        window.location.href = '/';
    }
});
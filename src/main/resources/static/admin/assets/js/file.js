//사진 업로드
function fileUpload(file) {

    const maxSize = 50 * 1024 * 1024;
    const fileSize = file.files[0].size;

    if(fileSize > maxSize){
        alert("사진 용량은 50MB 이내로 등록 가능합니다.");
        return false;
    }

    const reg = /(.*?)\.(jpg|jpeg|png)$/;

    if(!(file.files[0].name).toLowerCase().match(reg)) {
        alert("jpg, jpeg, png 형식의 파일을 첨부해주세요.");
        return false;
    }

    const div = file.closest('div');
    const img = div.querySelector('img');
    
    const fileReader = new FileReader();

    fileReader.onload = function () {
        img.src = fileReader.result;
    };
    fileReader.readAsDataURL(file.files[0]);

}
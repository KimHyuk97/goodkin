function spinnerStart(){
    var createLayDiv = document.createElement("div");
    createLayDiv.setAttribute("id", "spinnerLay1000");
    var createLayDivStyle = "width:100%; height:100%; margin:0 auto; padding:0; border:none; background:";
    createLayDivStyle = createLayDivStyle + " float:top; position:fixed; top:0%; z-index:1000;";
    createLayDivStyle = createLayDivStyle + " background-color:rgba(0, 0, 0, 0.5);";
    createLayDiv.setAttribute("style", createLayDivStyle);
    document.body.appendChild(createLayDiv); 


    // 실제 스핀 수행 컨테이너 동적 생성
    var createSpinDiv = document.createElement("div");
    createSpinDiv.setAttribute("id", "spinnerContainer1000");
    var createSpinDivStyle = "width:100px; height:100px; margin:0 auto; padding:0; border:none;";
    createSpinDivStyle = createSpinDivStyle + " float:top; position:relative; top:40%;";
    createSpinDiv.setAttribute("style", createSpinDivStyle);
    document.getElementById("spinnerLay1000").appendChild(createSpinDiv);


    // 스핀 옵션 지정 실시
    var opts = {
        lines: 10, // 그릴 선의 수 
        length: 0, // 각 줄의 길이
        width: 15, // 선 두께
        radius: 42, // 내부 원의 반지름 
        scale: 0.5, // 스피너의 전체 크기 지정 
        corners: 1, // 모서리 라운드 
        color: '#3399ff', // 로딩 CSS 색상 
        fadeColor: 'transparent', // 로딩 CSS 색상 
        opacity: 0.05, // 선 불투명도 
        rotate: 0, // 회전 오프셋 각도 
        direction: 1, // 회전 방향 시계 방향, 반시계 방향 
        speed: 1, // 회전 속도
        trail: 74, // 꼬리 잔광 비율 
        fps: 20, // 초당 프레임 수
        zIndex: 2e9 // 인덱스 설정 
    };


    // 스피너 매핑 및 실행 시작
    var target = document.getElementById("spinnerContainer1000");
    var spinner = new Spinner(opts).spin(target);
};


//중지
function spinnerStop(){
    try {
        var tagId = document.getElementById("spinnerLay1000");
        document.body.removeChild(tagId);
    }
    catch (exception) {
        console.error("catch : " + "not find spinnerLay1000");
    }
};
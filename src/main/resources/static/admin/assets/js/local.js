const LOCATION = [
    {
        city: '강원도',
        sub: [
            {gu : '강릉시'},
            {gu : '고성군'},
            {gu : '동해시'},
            {gu : '삼척시'},
            {gu : '속초시'},
            {gu : '인제군'},
            {gu : '양구군'},
            {gu : '양양군'},
            {gu : '영월군'},
            {gu : '원주시'},
            {gu : '정선군'},
            {gu : '춘천시'},
            {gu : '태백시'},
            {gu : '평창군'},
            {gu : '철원군'},
            {gu : '홍천군'},
            {gu : '화천군'},
            {gu : '횡성군'},
        ]
    },
    {
        city: '경기도',
        sub: [
            {gu : '가평군'},
            {gu : '김포시'},
            {gu : '고양시 덕양구'},
            {gu : '고양시 일산동구'},
            {gu : '고양시 일산서구'},
            {gu : '구리시'},
            {gu : '군포시'},
            {gu : '광주시'},
            {gu : '과천시'},
            {gu : '광명시'},
            {gu : '남양주시'},
            {gu : '동두천시'},
            {gu : '부천시'},
            {gu : '수원시 장안구'},
            {gu : '수원시 권선구'},
            {gu : '수원시 팔달구'},
            {gu : '수원시 영통구'},
            {gu : '성남시 수정구'},
            {gu : '성남시 중원구'},
            {gu : '성남시 분당구'},
            {gu : '시흥시'},
            {gu : '의정부시'},
            {gu : '안양시 만안구'},
            {gu : '안양시 동안구'},
            {gu : '안산시'},
            {gu : '안산시 상록구'},
            {gu : '안산시 단원구'},
            {gu : '오산시'},
            {gu : '의왕시'},
            {gu : '용인시 처인구'},
            {gu : '용인시 기흥구'},
            {gu : '용인시 수지구'},
            {gu : '이천시'},
            {gu : '안성시'},
            {gu : '양주시'},
            {gu : '여주시'},
            {gu : '연천군'},
            {gu : '양평군'},
            {gu : '파주시'},
            {gu : '포천시'},
            {gu : '평택시'},
            {gu : '하남시'},
            {gu : '화성시'},
        ]
    },

    {
        city: '경상남도',
        sub: [
            {gu: '김해시'},
            {gu: '거제시'},
            {gu: '고성군'},
            {gu: '거창군'},
            {gu: '남해군'},
            {gu: '사천시'},
            {gu: '밀양시'},
            {gu: '산청군'},
            {gu: '진주시 '},
            {gu: '양산시'},
            {gu: '의령군'},
            {gu: '창녕군'},
            {gu: '창원시 의창구'},
            {gu: '창원시 성산구'},
            {gu: '창원시 마산합포구'},
            {gu: '창원시 마산회원구'},
            {gu: '창원시 진해구'},
            {gu: '통영시'},
            {gu: '하동군'},
            {gu: '함양군'},
            {gu: '합천군'},
            {gu: '함안군'},
        ]
    },

    {
        city: '경상북도',
        sub: [
            {gu:  '경주시'},
            {gu:  '김천시'},
            {gu:  '구미시'},
            {gu:  '고령군'},
            {gu:  '군위군'},
            {gu:  '경산시'},
            {gu:  '문경시'},
            {gu:  '봉화군'},
            {gu:  '상주시'},
            {gu:  '성주군'},
            {gu:  '안동시'},
            {gu:  '영주시'},
            {gu:  '영천시'},
            {gu:  '의성군'},
            {gu:  '영양군'},
            {gu:  '영덕군'},
            {gu:  '울진군'},
            {gu:  '울릉군'},
            {gu:  '예천군'},
            {gu:  '청송군'},
            {gu:  '청도군'},
            {gu:  '칠곡군'},
            {gu:  '포항시 남구'},
            {gu:  '포항시 북구'},
        ]
    },

    {
        city: '광주광역시',
        sub: [
            {gu : '광산구'},
            {gu : '남구'},
            {gu : '동구'},
            {gu : '북구'},
            {gu : '서구'},
        ]
    },

    {
        city: '대구광역시',
        sub: [
            {gu : '남구'},
            {gu : '동구'},
            {gu : '달서구'},
            {gu : '달성군'},
            {gu : '북구'},
            {gu : '서구'},
            {gu : '수성구'},
            {gu : '중구'},
        ]
    },

    {
        city: '대전광역시',
        sub: [
            {gu : '동구'},
            {gu : '대덕구'},
            {gu : '서구'},
            {gu : '유성구'},
            {gu : '중구'},
        ]
    },

    {
        city: '부산광역시',
        sub: [
            {gu : '강서구'},
            {gu : '금정구'},
            {gu : '기장군'},
            {gu : '남구'},
            {gu : '동구'},
            {gu : '동래구'},
            {gu : '부산진구'},
            {gu : '북구'},
            {gu : '사하구'},
            {gu : '사상구'},
            {gu : '수영구'},
            {gu : '서구'},
            {gu : '연제구'},
            {gu : '영도구'},
            {gu : '중구'},
            {gu : '해운대구'},
        ]
    },

    {
        city: '서울특별시',
        sub: [
            {gu : '강남구'},
            {gu : '강동구'},
            {gu : '강북구'},
            {gu : '강서구'},
            {gu : '구로구'},
            {gu : '광진구'},
            {gu : '관악구'},
            {gu : '금천구'},
            {gu : '종로구'},
            {gu : '중구'},
            {gu : '노원구'},
            {gu : '도봉구'},
            {gu : '동대문구'},
            {gu : '동작구'},
            {gu : '마포구'},
            {gu : '서대문구'},
            {gu : '서초구'},
            {gu : '성동구'},
            {gu : '성북구'},
            {gu : '송파구'},
            {gu : '양천구'},
            {gu : '영등포구'},
            {gu : '은평구'},
            {gu : '용산구'},
            {gu : '중랑구'},
        ]
    },

    {
        city: '세종특별자치시',
        sub: [
            {gu : ''},
        ]
    },

    
    {
        city: '인천광역시',
        sub: [
            {gu : '강화군'},
            {gu : '계양구'},
            {gu : '남동구'},
            {gu : '동구'},
            {gu : '미추홀구'},
            {gu : '부평구'},
            {gu : '서구'},
            {gu : '옹진군'},
            {gu : '연수구'},
            {gu : '중구'},
        ]
    },

    {
        city: '울산광역시',
        sub: [
            {gu : '남구'},
            {gu : '동구'},
            {gu : '북구'},
            {gu : '울주군'},
            {gu : '중구'},
        ]
    },

    {
        city: '전라남도',
        sub: [
            {gu: '강진군'},
            {gu: '구례군'},
            {gu: '곡성군'},
            {gu: '고흥군'},
            {gu: '광양시'},
            {gu: '나주시'},
            {gu: '담양군'},
            {gu: '무안군'},
            {gu: '목포시'},
            {gu: '여수시'},
            {gu: '보성군'},
            {gu: '순천시'},
            {gu: '신안군'},
            {gu: '영암군'},
            {gu: '영광군'},
            {gu: '완도군'},
            {gu: '장흥군'},
            {gu: '장성군'},
            {gu: '진도군'},
            {gu: '함평군'},
            {gu: '해남군'},
            {gu: '화순군'},
        ]
    },

    {
        city: '전라북도',
        sub: [
            {gu : '군산시'},
            {gu : '김제시'},
            {gu : '고창군'},
            {gu : '남원시'},
            {gu : '무주군'},
            {gu : '부안군'},
            {gu : '순창군'},
            {gu : '익산시'},
            {gu : '임실군'},
            {gu : '완주군'},
            {gu : '장수군'},
            {gu : '정읍시'},
            {gu : '진안군'},
            {gu : '전주시 완산구'},
            {gu : '전주시 덕진구'},
            {gu : '전주시 완산구'},
        ]
    },

    {
        city: '제주특별자치도',
        sub: [
            {gu: '제주시'},
            {gu: '서귀포시'},
        ]
    },

    {
        city: '충청남도',
        sub: [
            {gu: '공주시'},
            {gu: '금산군'},
            {gu: '계룡시'},
            {gu: '논산시'},
            {gu: '당진시'},
            {gu: '부여군'},
            {gu: '보령시'},
            {gu: '서산시'},
            {gu: '서천군'},
            {gu: '아산시'},
            {gu: '예산군'},
            {gu: '천안시'},
            {gu: '천안시 동남구'},
            {gu: '천안시 서북구'},
            {gu: '청양군'},
            {gu: '태안군'},
            {gu: '홍성군'},
        ]
    },

    {
        city: '충청북도',
        sub: [
            {gu : '괴산군'},
            {gu : '단양군'},
            {gu : '보은군'},
            {gu : '옥천군'},
            {gu : '음성군'},
            {gu : '영동군'},
            {gu : '증평군'},
            {gu : '진천군'},
            {gu : '제천시'},
            {gu : '청주시 상당구'},
            {gu : '청주시 서원구'},
            {gu : '청주시 흥덕구'},
            {gu : '청주시 청원구'},
            {gu : '충주시'},
        ]
    },
]


function siDoHandler() {
    let template = `<option value="" selected>시/도</option>`;
    LOCATION.forEach(b => {
        template += `
            <option value="${b.city}">${b.city}</option>
        `;
    });

    document.getElementById('siDo').innerHTML = template;
}

function guGunHandler(item) {
    let template = '<option value="" checked>구/군</option>';
    LOCATION.forEach(b => {
        if(b.city === item.options[item.selectedIndex].value) {
            if(b.sub.length > 0) {
                b.sub.forEach(bb => {
                    template += `<option value="${bb.gu}">${bb.gu}</option>`;
                });
            }
        }
    });
    document.getElementById('guGun').innerHTML = template;
}
function postCodeOpen() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById('zonecode').value = data.zonecode;
            document.getElementById("detailAddress").focus();

            serach_map(data.address);
        }
    }).open();
}

function serach_map(query) {
    ajax(`/map/geocode/${query}`, 'POST')
        .then((data) => {
            document.getElementById("address").value = data.addresses[0].jibunAddress;
            document.getElementById("x").value = data.addresses[0].x;
            document.getElementById("y").value = data.addresses[0].y;

            open_map(data.addresses[0].x, data.addresses[0].y);
        })
        .catch((error) => console.log(error));
}

function open_map(x, y) {
    const map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(y, x),
        zoom: 15
    });

    const marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(y, x),
        map: map
    });
}
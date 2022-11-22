const ZOOMS = {
    dong: [15, 22],// 읍면동 진입(상세 마커)
    gugun: [13, 15], // 구군 진입(읍면동 마커)
    sido: [11, 13], // 시도 진입(구군 마커)
    korea: [8, 11], // 남한 진입(시도 마커)
    out: [0, 8] // 경계 이탈
}
const MAP_DATA = {
    latlng: [37.6480921, 127.0336832],
    zoom: 10, 
    address: { area1: '', area2: '', area3: '' },
}


/* === 지도 데이터 업데이트 === */
const dataHandler = (latlng, zoom) => {
    map.morph(new naver.maps.LatLng(latlng[0],latlng[1]), zoom, "easeOutCubic");
}

/* === 지도 생성 === */
const NAVER_MAP = () => {
    
    const nvMap = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(MAP_DATA.latlng[0], MAP_DATA.latlng[1]),
        zoom: MAP_DATA.zoom,
        minZoom: 10,
    });
    
    return nvMap;
};

const getZoomLevel = zoom => {
    let level = 'out';

    for (const key in ZOOMS) {
        if (zoom >= ZOOMS[key][0] && zoom < ZOOMS[key][1]) level = key;
    }

    return level;
};

const getZoomByAddress = address => {
    let validAddress = address.filter(item => item.longName.length > 0);

    if (!validAddress) return ZOOMS.gugun[0]; // 디폴트값: 구군주소 레벨(읍면동 마커)

    if (validAddress.length >= 3) return ZOOMS.dong[0]; // 읍면동 주소(상세 마커)
    else if (validAddress.length <= 1) return ZOOMS.sido[0]; // 시도 주소(구군 마커)
    else return ZOOMS.gugun[0]; // 구군 주소(읍면동 마커)
};

const getRegionByLatlng = latlng => {
    return new Promise((resolve, reject) => {
        naver.maps.Service.reverseGeocode({
            coords: new naver.maps.LatLng(latlng[0], latlng[1]),
            orders: [
                naver.maps.Service.OrderType.ADDR, // 법정동 주소만 받음
            ].join(',')
        }, (status, response) => {
            if (status === naver.maps.Service.Status.ERROR) reject("geocode오류");
            else if (response.v2.results.length <= 0) resolve('');
            else resolve(response.v2.results[0].region); // 법정동
        })
    })
};

const getRegionByZoom = (regions, zoom) => {
    const level = getZoomLevel(zoom);
    let result = { area1: '', area2: '', area3: '' };
    if (regions && Object.keys(regions).length > 0) {
        if (level === 'sido') result = {area1: regions.area1.name, area2: '', area3: ''};
        if (level === 'gugun') result = {area1: regions.area1.name, area2: regions.area1.name === '세종특별자치시'? '세종특별자치시': regions.area2.name, area3: ''};
        if (level === 'dong') result = {area1: regions.area1.name, area2: regions.area1.name === '세종특별자치시'? '세종특별자치시': regions.area2.name, area3: regions.area3.name};
    }
    return result;
};

/* === 지도 검색 === */
const getSearchByAddress = (address) => {
    return new Promise((resolve, reject) => {
        naver.maps.Service.geocode({
            query: address
        }, async (status, response) => {
            if (status === naver.maps.Service.Status.ERROR) {
                reject(null);
            }

            if (response.v2.meta.totalCount === 0) {
                reject(null);
            }

            const data = response.v2.addresses[0];
            if (data) {
                const latlng = [parseFloat(data.y), parseFloat(data.x)];
                const zoom = getZoomByAddress(data.addressElements);
                const areas = await getRegionByLatlng(latlng)
                resolve({
                    latlng: latlng,
                    zoom: zoom,
                    address: getRegionByZoom(areas, zoom)
                });

            }
        });
    })
};

/* === 마커 생성 === */
const current_marker = [];

const makeMarkers = (data) => {
    const markers = [];

    data.forEach(item => {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(item.y, item.x),
            zIndex: 999,
            icon: {
                content: `
                    <div class="marker_container">    
                        <div class="marker_img"><img src="../assets/images/food.svg" width="16px" height="16px"></div>
                        <p>잘만든 치킨 굿킨 ${item.name}</p>
                    </div>`,
              },
        });
            
        markers.push(marker);
        current_marker.push(marker);
    })

    // 지도 움직임 이벤트
    // naver.maps.Event.addListener(map, 'idle', () => {
    //     updateMarkers(map, markers);
    // });

    updateMarkers(map, markers);

}

function updateMarkers(map, markers) {

    var marker, position;

    if(markers.length > 0) {
        for (var i = 0; i < markers.length; i++) {
            marker = markers[i]
            position = marker.getPosition();

            showMarker(map, marker);
        }
    } else {
        hideMarker(markers);
    }
}

function showMarker(map, marker) {
    if (marker.setMap()) return;
    marker.setMap(map);
}

function hideMarker(marker) {
    if(marker === undefined) return;

    if(marker.length > 0){
        if(Array.isArray(marker)) for (const mk of marker) mk.setMap(null);
        else marker.setMap(null);
    } else {
        for (const mk of current_marker) mk.setMap(null);
    }
}

const map = NAVER_MAP();
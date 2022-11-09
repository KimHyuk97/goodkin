const ZOOMS = {
    dong: [15, 22],// 읍면동 진입(상세 마커)
    gugun: [13, 15], // 구군 진입(읍면동 마커)
    sido: [11, 13], // 시도 진입(구군 마커)
    korea: [8, 11], // 남한 진입(시도 마커)
    out: [0, 8] // 경계 이탈
}
const MAP_DATA = {
    latlng: [37.532167, 126.81715],
    zoom: 15, 
    address: { area1: '', area2: '', area3: '' },
}

/* === 지도 검색 === */
const mapSearch = async () => {
    const address = document.getElementById('address').value !== ''  ? document.getElementById('address').value : '경기도 부천시 고강동'
    
    await getSearchByAddress(address)
      .then(res => {
        dataHandler(res.latlng, res.zoom, res.address)
      })
      .catch(err => {
        alert('검색 지역을 찾을 수 없습니다. 지역명을 정확히 입력해 주세요.')
      })
}

/* === 지도 데이터 업데이트 === */
const dataHandler = (latlng, zoom, address) => {
    map.setCenter(new naver.maps.LatLng(latlng[0], latlng[1]));
    map.setZoom(zoom, true);
    getStores(address);
}

/* === 지도 생성 === */
const NAVER_MAP = () => {
    
    const nvMap = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(MAP_DATA.latlng[0], MAP_DATA.latlng[1]),
        zoom: MAP_DATA.zoom,
        minZoom: 11,
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
const makeMarkers = (data) => {
    const markers = [];
    const infoWindows = [];
    
    data.forEach(item => {
        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(item.y, item.x),
            zIndex: 999
        });
            
        var infoWindow = new naver.maps.InfoWindow({
            content: `<div style="width:100px;text-align:center;">굿킨 ${item.name}</div>`
        });

        // 이벤트 생성
        naver.maps.Event.addListener(marker, 'mouseover', (e) => {
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open(map, marker);
            }
        });

        markers.push(marker);
        infoWindows.push(infoWindow);
    })

    naver.maps.Event.addListener(map, 'idle', () => {
        updateMarkers(map, markers);
    });
}

function updateMarkers(map, markers) {

    var mapBounds = map.getBounds();
    var marker, position;

    for (var i = 0; i < markers.length; i++) {
        marker = markers[i]
        position = marker.getPosition();

        if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
        } else {
            hideMarker(map, marker);
        }
    }
}

function showMarker(map, marker) {
    if (marker.setMap()) return;
    marker.setMap(map);
}

function hideMarker(map, marker) {
    if (!marker.setMap()) return;
    marker.setMap(null);
}

const map = NAVER_MAP();
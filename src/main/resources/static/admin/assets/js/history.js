//상담내역 불러오기
const getHistory = (fkName, fkNo,) => {
    if (fkNo !== null) {
        ajax('/admin/myadmin/history/getHistory', 'post', {
            fkName: fkName,
            fkNo: fkNo,
        })
            .then((data) => {
                if(data != null) viewHistory(data.result);
            })
            .catch((error) => console.log(error));
    }
}


const viewHistory = (data) => {
    let template = '';

    if(data.length > 0) {
        data.forEach(history => {
            template += `
            <fieldset class="fieldset">
                <div>
                    <span style="font-size:16px; font-weight:bold;">${history.adminRole.toString() === 'SYSTEM' ? '시스템관리자' : '지역관리자'} (${history.adminName})</span>
                </div>
                <span class="input-wrap" style="position:relative;">
                    <textarea class="textarea" id="content${history.historyNo}">${history.content}</textarea>
                    <span style="font-size:14px; position: absolute; bottom: 10px; right: 10px; font-weight: 600;">${history.createDate.replace("T", ' ')}</span>
                </span>
                
                <div class="form-action">
                    <button type="button" onclick="deleteHistory('${history.fkName}', ${history.fkNo}, ${history.historyNo})">삭제</button>
                    <button type="button" onclick="updateHistory('${history.fkName}', ${history.fkNo}, ${history.historyNo})">수정</button>
                </div>
            </fieldset>
            `
        });
        
    }
    document.getElementById('history_list').innerHTML = template;

}

// 상담내역 등록
const insertHistory = (fkName, fkNo) => {

    const content = document.getElementById('history_content').value;

    if (content === '') {
        alert('상담 내용을 입력해주세요.')
    } else {
        ajax('/admin/myadmin/history/insert', 'POST', {
            fkName: fkName,
            fkNo: fkNo,
            content: content
        })
            .then((data) => {
                alert(data.message);
                getHistory(fkName, fkNo);
                document.getElementById('history_content').value = '';
            })
            .catch((error) => console.log(error));
    }
}

// 상담내역 수정
const updateHistory = (fkName, fkNo, historyNo) => {
    console.log(historyNo);
    const content = document.getElementById(`content${historyNo}`).value;

    if (content === '') {
        alert('상담 내용을 입력해주세요.')
    } else {
        ajax('/admin/myadmin/history/update', 'POST', {
            historyNo: historyNo,
            content: content
        })
            .then((data) => {
                alert(data.message);
                getHistory(fkName, fkNo);
            })
            .catch((error) => console.log(error));
    }

}

// 상담내역 삭제
const deleteHistory = (fkName, fkNo, historyNo) => {

    if (confirm('상담내용을 삭제하시겠습니까?')) {
        ajax('/admin/myadmin/history/delete', 'POST', {
            historyNo: historyNo
        })
            .then((data) => {
                alert(data.message);
                getHistory(fkName, fkNo);
            })
            .catch((error) => console.log(error));
    }

}


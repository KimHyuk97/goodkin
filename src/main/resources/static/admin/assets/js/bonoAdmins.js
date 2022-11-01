let admins = [];
let adminNos = [];

function adminCheckedSSR(nos, names) {
    if(names !== null) {
        let arrayNames = names.split(',');
        arrayNames.forEach(name => {
            if(name !== '') admins.push(name)
        });
    }
    if(nos !== null) {
        let arrayNumbers = nos.split(',');
        arrayNumbers.forEach(no =>  {
            if(no !== '') adminNos.push(no)
        });
    } 
}

function bonoAdmins(selected) {
    if(selected.value === '0') {

        for(let i=0; i<selected.options.length; i++) selected.options[i].classList.remove('on');
        
        if(adminNos.includes('0')) {
            admins = [];
            adminNos = [];
        } else {
            admins = [];
            adminNos = [];

            admins.push(selected.options[selected.selectedIndex].text);
            adminNos.push(selected.value);
            selected.options[selected.selectedIndex].classList.add('on')
        }

    
    } else {

        if(adminNos.indexOf('0') > -1) {
            admins = [];
            adminNos = [];
            for(let i=0; i<selected.options.length; i++) selected.options[i].classList.remove('on');
        }

        if(adminNos.indexOf(selected.value) > -1) {
            selected.options[selected.selectedIndex].classList.remove('on')    
            
            admins = admins.filter(admin => {
                return admin !== selected.options[selected.selectedIndex].text
            });
            adminNos = adminNos.filter(adminNo => {
                return adminNo != selected.value
            });
        } else {
            selected.options[selected.selectedIndex].classList.add('on')

            admins.push(selected.options[selected.selectedIndex].text);
            adminNos.push(selected.value);
        }
    }

    //초기화
    selected.selectedIndex = 0;

    document.getElementById('adminName').value = admins.toString();
    document.getElementById('adminNo').value = adminNos.toString();
}
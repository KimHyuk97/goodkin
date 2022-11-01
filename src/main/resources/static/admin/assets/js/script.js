class Tab {
    constructor(tabEl) {
        this.btns = tabEl.querySelectorAll('.tab_nav_item');
        this.contetns = tabEl.querySelectorAll('.tab_cont_item');
        this.activIdx = 0;
        this.activClass = 'on'
        this.init();
    }
    init() {
        this.activateTab(this.activIdx);
        this.listenClick();
    }
    listenClick() {
        for(let i = 0; i < this.btns.length; i++){
            this.btns[i].addEventListener('click', e=> {
                e.preventDefault();
                this.activateTab(i);
            })
        }
    }
    activateTab(index) {
        this.btns[this.activIdx].classList.remove(this.activClass);
        this.contetns[this.activIdx].classList.remove(this.activClass);
        this.btns[index].classList.add(this.activClass);
        this.contetns[index].classList.add(this.activClass);
        this.activIdx = index;
    }
}

class DropDown {
    constructor(dropdownEl) {
        this.dropdown = dropdownEl;
        this.dropdownBtn = this.dropdown.querySelector('.dropdown_btn');
        this.dropdownCont = this.dropdown.querySelector('.dropdown_cont');
        this.contHeight = this.dropdownCont.getClientRects()[0].height;
        this.activClass = 'on';
        this.init();
    }
    init() {
        this.slideUp();
        this.listenClick();
    }  
    listenClick() {
        this.dropdownBtn.addEventListener('click', e=> {
            e.preventDefault();
            if(this.dropdown.classList.contains(this.activClass)) this.slideUp();
            else this.slideDown();
        })
    } 
    slideDown(){
        this.dropdown.classList.add(this.activClass);
        this.dropdownCont.style = 'height:'+ this.contHeight +'px; overflow: show;';
    }
    slideUp(){
        this.dropdownCont.style = 'height: 0px; overflow: hidden;';
        this.dropdown.classList.remove(this.activClass);
    }
}

class MultipleDropDown {
    constructor(dropdownGroup) {
        this.dropdownGroup = dropdownGroup;
        this.dropdowns = this.dropdownGroup.querySelectorAll('.dropdown');
        this.dropdownBtns = this.dropdownGroup.querySelectorAll('.dropdown_btn');
        this.dropdownConts = this.dropdownGroup.querySelectorAll('.dropdown_cont');
        this.contHeights = [];
        this.activClass = 'on';
        this.activatedIdx = -1;
        this.init();
    }
    init() {
        for(let i = 0; i < this.dropdowns.length; i++) {
            this.pushContsHeight(i);
            this.slideUp(i);
            this.listenClick(i);
        }
        this.dropdownGroup.classList.remove('loading');
    }  
    pushContsHeight(index) {
        this.contHeights[index] = this.dropdownConts[index].getClientRects()[0].height;
    }
    listenClick(index) {
        this.dropdownBtns[index].addEventListener('click', e => {
            e.preventDefault();
            if(this.dropdowns[index].classList.contains(this.activClass)) this.slideUp(index);
            else this.slideDown(index);
        })
    } 
    slideDown(index){
        if(this.activatedIdx >= 0) this.slideUp(this.activatedIdx);
        this.dropdowns[index].classList.add(this.activClass);
        this.dropdownConts[index].style = 'height:'+ this.contHeights[index] +'px; overflow: show;';
        this.activatedIdx = index;
    }
    slideUp(index){
        this.dropdownConts[index].style = 'height: 0px; overflow: hidden;';
        this.dropdowns[index].classList.remove(this.activClass);
        this.activatedIdx = -1;
    }
}

class Modal {
    constructor(name) {
        this.outer = document.querySelector('body');
        this.modWindow = document.querySelector(`.modal[data-modal="${name}"]`);
        this.modBtn = document.querySelectorAll(`.modalBtn[data-modal="${name}"]`);
        this.modClose = this.modWindow.querySelectorAll('.modalClose');
        this.activClass = 'on';
        this.init();
    }
    init() {
        this.listenClick();
        this.listenResize();
    }
    listenClick(){
        for(let btn of this.modBtn) btn.addEventListener('click', e => {
            e.preventDefault();
            this.showModal();
        });
        for(let close of this.modClose) close.addEventListener('click', e => {
            e.preventDefault();
            this.closeModal();
        });
    }
    listenResize(){
        window.addEventListener('resize', e => {
            this.posWindow();
        });
    }
    showModal(){
        this.modWindow.classList.add(this.activClass);
        this.isActivated = true;
        this.posWindow();
        this.preventScroll();
        this.addOutsideEvent();
    }
    closeModal(){
        this.modWindow.classList.remove(this.activClass);
        this.letScroll();
    }
    posWindow(){
        let wWidth = window.innerWidth;
        let wHeight = window.innerHeight;
        let mWidth = this.modWindow.getClientRects()[0].width;
        let mHeight = this.modWindow.getClientRects()[0].height;

        if(mWidth < wWidth) {
            this.modWindow.style.left = parseInt((wWidth - mWidth) * 0.5) + 'px';
        } else {
            this.modWindow.style.left = 10 + 'px';
            this.modWindow.style.width = (wWidth - 20) + 'px';
        }
        if(mHeight < wHeight) {
            this.modWindow.style.top = parseInt((wHeight - mHeight) * 0.5) + 'px';
        } else {
            this.modWindow.style.top = 20 + 'px';
            this.modWindow.style.height = (wHeight - 40) + 'px';
        }
    }
    preventScroll(){
        this.outer.style.height = '100%';
        this.outer.style.overflow = 'hidden';
    }
    letScroll(){
        this.outer.style.height = 'auto';
        this.outer.style.overflow = 'auto';
    }
    addOutsideEvent(){
        this.modWindow.addEventListener('click', e => {
            // console.log(e.target.closest('.inner') );
            if(e.target.closest('.inner') || e.target.classList.contains('.inner')) return false;
            else this.closeModal();
        });
    }
}

class ModifyModal extends Modal {
    constructor(modalName) {
        super(modalName);
    }
    init(){
        this.mainTable = this.modBtn[0].closest('.table').querySelector('table');
        this.selectedList = undefined;
        this.modalTable = this.modWindow.querySelector('table tbody');
        super.init.call(this);
    }
    listenClick(){
        for(let btn of this.modBtn) btn.addEventListener('click', e => {
            e.preventDefault();
            if(this.isDataSelected()) {
                this.createTable();
                this.showModal();
            } else {
                alert('항목을 선택해 주세요.');
            }
        });
        for(let close of this.modClose) close.addEventListener('click', e => {
            e.preventDefault();
            this.closeModal();
        });
    }
    isDataSelected(){
        this.selectedList = this.mainTable.querySelectorAll('tr.selected');
        return this.selectedList.length > 0 ? true : false;
    }
};

class LikeModal extends Modal {
    constructor(modalName) {
        super(modalName);
    }
    init(){
        this.modBtn = document.querySelectorAll('td.like a');
        this.modOption = this.modWindow.querySelectorAll('select option');
        this.modRadio = this.modWindow.querySelectorAll('input[type="radio"]');
        super.init.call(this);
    }
    listenClick(){
        for(let btn of this.modBtn) btn.addEventListener('click', e => {
            e.preventDefault();
            this.showModal();
            this.setCategory(btn);
        });
        for(let close of this.modClose) close.addEventListener('click', e => {
            e.preventDefault();
            this.closeModal();
        });
    }
    setCategory(selected){
        let like = (selected.closest('tr').querySelector('.type').innerText == "L")? 'like' : 'dislike';
        let category = selected.classList[0];
        for(let option of this.modOption) {
            if(option.value == category) option.selected = true;
            else option.selected = false;
        }
        for(let radio of this.modRadio) {
            if(radio.value== like) radio.checked = true;
            else radio.checked = false;
        }
    }
}


class Table {
    constructor(tableEl, option = null) {
        this.table = tableEl;
        this.checkControll = this.table.querySelector('thead input[type="checkbox"]');
        this.checkEls = this.table.querySelectorAll('tbody input[type="checkbox"]');
        this.activClass = 'selected';
        this.checkCnt = 0;
        this.orderSelect = this.table.querySelectorAll('thead select.order');
        this.sorts = this.table.querySelectorAll('thead .table_sort');
        this.init();
    }
    init() {
        this.listenCheck();
        if(this.orderSelect) this.listenOrderSelect();
        if(this.sorts) this.listenSort();
    }
    listenCheck(){
        this.checkControll.addEventListener('click', e => {
            if (e.currentTarget.checked) this.activateChecks();
            else this.deactivateChecks();
        });
        for(let checkEl of this.checkEls) {
            checkEl.addEventListener('click', e => {
                this.countCheck(e);
            });
        };  
    }
    countCheck(event){
        let row = event.currentTarget.closest('tr');
        if(event.currentTarget.checked) {
            row.classList.add(this.activClass);
            this.checkCnt++;
        } else {
            row.classList.remove(this.activClass);
            if(this.checkCnt == 1 && this.checkControll.checked) this.checkControll.checked = false;
            this.checkCnt--;
        }
    }
    activateChecks(){
        for(let i = 0; i<this.checkEls.length; i++) {
            this.checkEls[i].checked = true;
            this.checkEls[i].closest('tr').classList.add(this.activClass);
        }
        this.checkCnt  = this.checkEls.length;
    }
    deactivateChecks(){
        for(let i = 0; i<this.checkEls.length; i++) {
            this.checkEls[i].checked = false;
            this.checkEls[i].closest('tr').classList.remove(this.activClass);
        }
        this.checkCnt = 0;
    }
    listenOrderSelect(){
        for(let orderSel of this.orderSelect) {
            orderSel.value = null;
            orderSel.addEventListener('change', e => {
                let order = e.currentTarget.value;
                console.log(order);
                if(order.length > 0) this.activateOrder(e.currentTarget.previousElementSibling, order);
            });
        }
    }
    activateOrder(el, order){
        el.className = order;
    }
    listenSort() {
        for(let sort of this.sorts) {
            sort.addEventListener('click', e => {
                this.activateSort(e.currentTarget);
            });
        }
    }
    activateSort(clicked){
        if(clicked.classList.contains('ascending')) {
            clicked.classList.remove('ascending');
            clicked.classList.add('descending');
        } else if(clicked.classList.contains('descending')) {
            clicked.classList.remove('descending');
            clicked.classList.add('ascending');
        } else {
            clicked.classList.add('ascending');
        }
    };
}

class PwdValidation {
    constructor(pwdEl, pwdCfEl = null) {
        this.pwd = pwdEl;
        if(pwdCfEl) this.pwdCf = pwdCfEl;
        this.init();
    }
    init() {
        this.validationFn();
        if(this.pwdCf) this.matchFn();
    }
    validationFn(){
        this.pwd.addEventListener('change', e => {
            const result = this.isValid(e.currentTarget.value);
            if(this.pwdCf && !result.valid) this.pwdCf.value = '';     
            this.writeWarning(this.pwd, result.warning);
        });
    }
    isValid(pwdText){
        const regEx = /^(?=.*?[0-9])(?=.*?[a-zA-Z]).{8,}$/;
        let result = {valid: false, warning: ''};

        if(pwdText.length <= 0) {
            result.warning = '비밀번호를 입력해 주십시오.'
            return result;
        } else {
            if(regEx.test(pwdText)){
                result.valid = true;
            } else {
                result.warning = '비밀번호는 영문과 숫자를 포함하여 8자 이상 입력해 주십시오.'
            }
        }

        return result;
    }
    matchFn(){
        this.pwdCf.addEventListener('change', e => {
            const result = this.isMatch(e.currentTarget.value);
            if(!result.match) { e.currentTarget.value = ''; }
            this.writeWarning(this.pwdCf, result.warning);
        });
    }
    isMatch(pwdText) {
        const pwdVal = this.pwd.value;
        const pwdValid = this.isValid(pwdVal);
        let result = {match: false, warning: ''};

        if(!pwdValid.valid) {
            this.writeWarning(this.pwd, pwdValid.warning);
            this.pwd.focus();
        } else {
            if(pwdVal == pwdText) {
                result.match = true;
                result.warning = '';
            } else {
                result.warning = '비밀번호가 일치하지 않습니다.';
            }
        }

        return result;
    }
    writeWarning(el, text){
        if(text.length <= 0) {
            const warnEl = el.nextElementSibling;
            if(warnEl && warnEl.classList.contains('warning')) warnEl.remove();
        } else {
            const nextEl = el.nextElementSibling;
            if(nextEl && nextEl.classList.contains('warning')) {
                nextEl.innerText = text;
            } else {
                const warnEl = document.createElement('i');
                warnEl.classList.add('warning');
                warnEl.innerText = text;
                if(nextEl) el.parentNode.insertBefore(warnEl, nextEl);
                else el.parentNode.append(warnEl);
            }
        }
    }
}

class FileUploader {
    constructor(fileUploaderEl, option) {
        this.fileUploader = fileUploaderEl;
        this.fileName = this.fileUploader.querySelector('.file_name');
        this.fileInput = this.fileUploader.querySelector('input');
        this.fileAdd = this.fileUploader.querySelector('.file_action_add');
        this.fileDel = this.fileUploader.querySelector('.file_action_del');
        this.fileExtension = option.extension;
        this.init();
    }
    init(){
        this.listenEvent();
    }
    listenEvent(){
        this.fileInput.addEventListener('change', e => {
            this.addFile(e);
        });
        this.fileDel.addEventListener('click', e => {
            e.preventDefault();
            this.clearFile();
        })
    }
    addFile(event){
        const file = event.currentTarget.files[0];
        const fileValidation = this.checkFileValidation(file);
  
        if(fileValidation.valid) {
            this.writeFileName(file.name);
        } else {
            this.clearFile();
            alert(fileValidation.warning);
        }
    }
    checkFileValidation(file){
        let result = {
            valid: false, 
            warning: ''
        };
        let fileExt = file.name.split('.');
        fileExt = fileExt[fileExt.length - 1];

        for(let i = 0; i < this.fileExtension.length; i++) {
            if(this.fileExtension[i] == fileExt) {
                result.valid = true;
                result.warning = '';
                break;
            } else {
                result.warning += this.fileExtension[i];
                if(i == this.fileExtension.length - 1) result.warning += ' 파일만 업로드 할 수 있습니다';
                else result.warning += ',';
            }
        }
        return result;
    }
    clearFile(){
        if(this.fileInput.value) {
            this.fileInput.value = null;
            this.writeFileName('');
        }
    }
    writeFileName(name){
        this.fileName.innerText = name;
    }
}

/* 테이블 클릭 스크립트 */
function linkTo(event, addr) {
    const preventClicks = ['input', 'a', 'button', 'select', 'option'];
    const isPreventClick = function(clicked){
        let isPrevent = false;
        let clickedTag = clicked.tagName.toLowerCase();
        for(let prevent of preventClicks) {
            if(clickedTag == prevent) {
                isPrevent = true;
                break;
            }
        }
        return isPrevent;
    };

    if(!isPreventClick(event.target) && addr.length > 0) window.location.href = addr;
}

/* 메뉴 스크립트(헤더, 사이드) */
function layoutFn(){
    const header = document.querySelector('.header');
    const aside = document.querySelector('.aside');
    const asideBtn = aside.querySelector('.aside_btn');

    //header
    new DropDown(header.querySelector('.dropdown'));

    //aside
    new MultipleDropDown(aside);
    // mobile button
    asideBtn.addEventListener('click', e => {
        e.preventDefault();
        if(aside.classList.contains('on')) aside.classList.remove('on');
        else aside.classList.add('on');
    });
    //highlight Category
    const path = window.location.pathname.split('/');
    const depth01 = aside.querySelector(`.category-${path[path.length-2]}`);
    const depth02 = aside.querySelector(`.category-${path[path.length-1]}`);
    if(depth01) depth01.classList.add('page');
    if(depth02) depth02.classList.add('page');
}

function writeFee(event, input) {
    let fee = getNumber(input.value);
    
    if(fee < 0) {
        alert('숫자만 입력해 주세요.');
        input.value = localeNumber(eraseChar(input.value));
    } else {
        input.value = localeNumber(fee);
    }

    function getNumber(text) {
        text = text.split(',');
        let val = '';
        for(let t of text) val += t;

        if(isNaN(val)) return -1;
        else return val;
    }

    function localeNumber(value) {
        return Number(value).toLocaleString('ko-KR');
    }

    function eraseChar(text){
        let res = 0;
        for(let char of text) {
            console.log(char);
            if(isNaN(char)) continue;
            else res += char;
        }
        return res;
    }
};

function numberOnly() {
    const numInputs = document.querySelectorAll('.js-numOnly');
    for(let input of numInputs) input.addEventListener('keyup', event => {
        let valueToNum = event.currentTarget.value.replaceAll(',', '');

        console.log(valueToNum)

        if(isNaN(valueToNum)) {
            event.preventDefault();
            alert('숫자만 입력 가능합니다.');
            valueToNum = valueToNum.substring(0, valueToNum.toString().length-1);
        }

        event.currentTarget.value = getFormattedNumber(valueToNum);
    });
};

function getFormattedNumber(stringVal) {
    return Number(stringVal.replace(/[^0-9]/g,"")).toLocaleString('ko-KR');
};

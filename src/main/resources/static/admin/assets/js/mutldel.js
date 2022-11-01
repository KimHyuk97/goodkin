function del(name, key, mode){
	const check = document.querySelectorAll('input[name="ck"]:checked');

	if(check.length > 0){		    	
		let val = [];
		for(let i = 0; i < check.length; i++){
			val.push(check[i].value);
		}

		

		if(confirm("선택하신 "+mode+"(을/를) 삭제하시겠습니까?")){
			ajax('/admin/utils/mutldel', 'POST', {
				ids: val,
				name: name,
				key: key
			})
			  .then((data) => {
			  	alert(data.message);
			  	location.reload()
			  })
			  .catch((error) => console.log(error));
		}
	}else{
		alert(`삭제할 ${mode}(을/를) 선택해주세요.`);
	}
}
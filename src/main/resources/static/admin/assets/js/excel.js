function excel(mode, kind, state = 0) {
	
	const check = document.querySelectorAll('input[name="ck"]:checked');

	let ids = [];
	if(check.length > 0) {
		for(let i = 0; i < check.length; i++) ids.push(check[i].value);
	}
			  
	location.href=`/admin/myadmin/excel?mode=${mode}&kind=${kind}&ids=${ids}&state=${state}`;
}
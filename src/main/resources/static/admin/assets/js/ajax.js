const ajax = async (url, method, body = {}) => {
						  
    const options = {
      method: method,
      headers: {'Content-Type': 'application/json',},
      body: JSON.stringify(body),
    };
          
    const res = await fetch(url, options);

    const data = await res.json();
    
    if(res.status === 200) return data;
    else if (res.status === 404) return data;
    else if (res.status === 500) return data;
    else throw Error(JSON.stringify(data));
    
}

const getAjax = async (url) => {
						  
  const options = {
    method: "get",
    headers: {
      "Content-Type": "application/json",
      "X-NCP-APIGW-API-KEY-ID": "sg6n0oldss",
      "X-NCP-APIGW-API-KEY": "CQM5yLrbgemhoWeQ2anzpmeo7Hke9AuGsuurvXn1"
    }
  };
        
  const res = await fetch(url, options);

  const data = await res.json();
  
  if(res.status === 200) return data;
  else if (res.status === 404) return data;
  else if (res.status === 500) return data;
  else throw Error(JSON.stringify(data));
  
}
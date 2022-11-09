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
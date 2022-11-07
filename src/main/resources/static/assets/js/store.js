const getStores = () => {
    ajax('/api/stores', 'post')
        .then((response) => {
            if(response != null) return response.data;
        })
        .catch((error) => console.log(error));
    
}
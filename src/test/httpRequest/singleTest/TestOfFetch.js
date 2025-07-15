fetch('http://localhost:8080/books/1', {
    method: 'GET',
    headers: {'Content-Type': 'application/json'},
}).then(res => {
    return res.json()
}).then(data => {
    console.log(data)
})
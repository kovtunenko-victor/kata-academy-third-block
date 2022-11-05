async function getAllRoles() {
    let response = await fetch("http://localhost:8081/api/roles/get");
    return response.json();
}

async function getUserById(id) {
    let response = await fetch("http://localhost:8081/api/users/get/" + id);
    return response.json().then(user => {
        return user;
    });
}

async function getAllUsers() {
    let response = await fetch("http://localhost:8081/api/users/get");
    return response.json();
}

async function loadRolesToAddForm() {
    $('#roles_add').empty();

    await fetch("http://localhost:8081/api/roles/get")
        .then(res => res.json())
        .then(roleList => {
            roleList.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                $('#roles_add')[0].appendChild(el);
            })
        });
}
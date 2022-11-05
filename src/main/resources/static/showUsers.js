$(async function () {
    await showAllUser();
});

async function showAllUser() {
    $('#tbodyShowUsers').empty();

    getAllUsers().then(data => {
        data.forEach(user => {
            let userData = `$(
                <tr>
                  <td>${user.id}</td>
                  <td>${user.login}</td>
                  <td>${user.name}</td>
                  <td>${user.title}</td>
                  <td>${user.roles.map(role => role.name.substring(5).concat(" ")).toString().replaceAll(",", ", ")}</td>
                  <td style="width: 25px">
                    <div style="display: flex; justify-content: space-around">
                      <a id="modal-edit" data-target="#modal-container-edit" role="button" data-id="${user.id}"
                         class="btn btn-primary" data-toggle="modal">Edit</a>
                      &nbsp
                     <a id="modal-delete" data-target="#modal-container-delete" role="button" data-id="${user.id}"
                         class="btn btn-danger" data-toggle="modal">Delete</a>
                    </div>
                  </td>
                </tr>
                )`;

            $('#tbodyShowUsers').append(userData);
        });
    });
}
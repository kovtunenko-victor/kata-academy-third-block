$(async function () {
    await showCurrentUser();
});

async function showCurrentUser() {
    $('#tbodyShowOneUser').empty();

    getCurrentUser().then(user => {
        let userData = `$(
                <tr>
                  <td>${user.id}</td>
                  <td>${user.login}</td>
                  <td>${user.name}</td>
                  <td>${user.title}</td>
                  <td>${user.roles.map(role => role.name.substring(5).concat(" ")).toString().replaceAll(",", ", ")}</td>
                </tr>
                )`;

        $('#tbodyShowOneUser').append(userData);
    });
}
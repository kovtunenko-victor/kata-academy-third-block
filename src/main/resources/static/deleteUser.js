$('#modal-container-delete').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');

    showDeleteModal(id);
})

$(async function () {
    await deleteUser();
});

async function showDeleteModal(id) {
    const form = document.forms["deleteUserForm"];

    getUserById(id).then(user => {
        form.id.value = user.id;
        form.login.value = user.login;
        form.name.value = user.name;
        form.title.value = user.title;

        $('#roles_delete').empty();
        getAllRoles().then(roles => {
            roles.forEach(role => {
                let selectedRole = false;

                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].name === role.name) {
                        selectedRole = true;
                        break;
                    }
                }

                let optionElement = document.createElement("option");
                optionElement.text = role.name.substring(5);
                optionElement.value = role.id;

                if (selectedRole) {
                    optionElement.selected = true;
                }

                $('#roles_delete')[0].appendChild(optionElement);
            })
        });
    });
}

async function deleteUser() {
    const form = document.forms["deleteUserForm"];
    form.addEventListener('submit', onSubmitClick);

    function onSubmitClick(e) {
        e.preventDefault();

        function getCookie(name) {
            if (!document.cookie) {
                return null;
            }

            const xsrfCookies = document.cookie.split(';')
                .map(c => c.trim())
                .filter(c => c.startsWith(name + '='));

            if (xsrfCookies.length === 0) {
                return null;
            }

            return decodeURIComponent(xsrfCookies[0].split('=')[1]);
        }

        fetch("http://localhost:8081/api/users/delete/" + form.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': getCookie('XSRF-TOKEN')
            }
        }).then(() => {
            form.reset();
            $('#btnCloseDelete').click();
            showAllUser();
        })
    };
}
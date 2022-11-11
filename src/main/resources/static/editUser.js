$('#modal-container-edit').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');

    showEditModal(id);
})

$(async function () {
    await updateUser();
});

async function showEditModal(id) {
    const form = document.forms["editUserForm"];

    getUserById(id).then(user => {
        form.id.value = user.id;
        form.login.value = user.login;
        form.name.value = user.name;
        form.title.value = user.title;

        $('#roles_edit').empty();
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

                $('#roles_edit')[0].appendChild(optionElement);
            })
        });
    });
}

async function updateUser() {
    const form = document.forms["editUserForm"];
    form.addEventListener('submit', onSubmitClick);

    function onSubmitClick(e) {
        e.preventDefault();
        let userRoles = [];

        if (form.roleSet !== undefined) {
            for (let i = 0; i < form.roleSet.options.length; i++) {
                if (form.roleSet.options[i].selected) userRoles.push({
                    id: form.roleSet.options[i].value,
                    name: form.roleSet.options[i].text
                })
            }
        }

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

        fetch("https://localhost:8081/api/users/update/" + form.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': getCookie('XSRF-TOKEN')
            },
            body: JSON.stringify({
                id: form.id.value,
                login: form.login.value,
                name: form.name !== undefined ? form.name.value : '',
                title: form.title !== undefined ? form.title.value : '',
                password: form.password !== undefined ? form.password.value : '',
                roles: userRoles
            })
        }).then(() => {
            form.reset();
            $('#btnCloseEdit').click();
            showAllUser();
        })
    };
}
async function addUser() {
    const form = document.forms["createUserForm"];

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

        fetch("http://localhost:8081/api/users/add", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': getCookie('XSRF-TOKEN')
            },
            body: JSON.stringify({
                login: form.login.value,
                name: form.name.value,
                title: form.title.value,
                password: form.password.value,
                roles: userRoles
            })
        }).then(() => {
            form.reset();
            showAllUser();
            $('#v-pills-table-tab').click();
        })
    }
}
//--- DOMAIN
export class User {
    constructor({id, firstName, lastName, age, email, password, roles}) {
        this.id = id == null ? '' : id;
        this.firstName = firstName == null ? '' : firstName;
        this.lastName = lastName == null ? '' : lastName;
        this.age = age == null ? 0 : age;
        this.email = email == null ? '' : email;
        this.password = password == null ? '' : password;
        this.roles = roles == null ? [] : roles;
    }

    static empty() {
        return new User({});
    }
}

export class Role {
    constructor({id, name}) {
        this.id = id;
        this.name = name;
    }
}

//--- VIEWS

/**
 * Site menu
 *
 * @param {User} user
 * @returns {string}
 */
export function viewMenu(user) {
    return `
        <nav class="navbar navbar-dark bg-dark mb-4">
            <div class="container-fluid">
                <div class="navbar-text text-light">
                    <span class="fw-bold">${user.email}</span>
                    with roles:
                    ${viewRoles(user.roles)}
                </div>
                <a class="link-secondary text-capitalize text-decoration-none" href="/logout">logout</a>
            </div>
        </nav>
    `;
}

/**
 * Roles listing
 *
 * @param {Role[]} roles
 * @returns {string}
 */
function viewRoles(roles) {
    return roles
        .map(role => `<span class="ms-1">${role.name}</span>`)
        .join('');
}

/**
 * User info card
 * @param {User} user
 * @returns {string}
 */
export function viewUserInfo(user) {
    return `
        <div class="card">
            <div class="card-header fw-bold">About user</div>
            <div class="card-body">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${viewRoles(user.roles)}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    `;
}

/**
 * Users listing
 *
 * @param {User[]} users
 * @returns {string}
 */
export function viewUsersListing(users) {
    /**
     * @param {User} user
     */
    function viewUserRow(user) {
        return `
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${viewRoles(user.roles)}</td>
                <td>
                    <a type="button" class="btn btn-info text-light">Edit</a>
                <td>
                    <a type="button" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        `;
    }

    return `
        <div class="card">
            <div class="card-header fw-bold">All users</div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Email</th>
                        <th scope="col">Role</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        ${users.map(viewUserRow).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
}

/**
 * @param {Role} role
 * @returns {string}
 */
function viewRoleOption(role) {
    return `
            <option value="${role.id}">${role.name}</option>
        `;
}

/**
 * Add user form
 *
 * @param {User} user
 * @param {string} error
 * @returns {string}
 */
export function viewUserAdd(user, error) {

    /**
     * @param {string} error
     * @returns {string}
     */
    function viewError(error) {
        if (error == null || error.length === 0) return '';

        return `
            <div class="alert alert-warning text-center mb-3">
                ${error}
            </div>
        `;
    }

    return `
        <div class="card">
            <div class="card-header fw-bold">Add new user</div>
            <div class="card-body">
                <div class="row justify-content-center">
                    <div class="col-3 text-center">
                        <form>
                            <div class="mb-3">
                                <label class="form-label">First name
                                    <input type="text" 
                                           class="form-control" 
                                           name="first-name"
                                           placeholder="First name"> 
                                </label>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Last name
                                    <input type="text" 
                                           class="form-control" 
                                           name="last-name"
                                           placeholder="Last name">
                                </label>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Age
                                    <input type="number" 
                                           class="form-control" 
                                           name="age"
                                           placeholder="Age">
                                </label>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Email
                                    <input type="email" 
                                           required 
                                           class="form-control" 
                                           name="email"
                                           placeholder="Email">
                                </label>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Password
                                    <input type="password" 
                                           required
                                           class="form-control" 
                                           name="password"
                                           placeholder="Password">
                                </label>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Role
                                    <select id="roles"
                                            class="form-control"
                                            multiple
                                            name="roles"
                                            required>
                                        ${user.roles.map(viewRoleOption).join('')}
                                    </select>
                                </label>
                            </div>

                            ${viewError(error)}

                            <input class="btn btn-success" type="submit" value="Add new user">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    `;
}

/**
 * Delete user modal
 *
 * @param {User} user
 * @returns {string}
 */
function viewModalDeleteUser(user) {
    return `
        <div class="modal fade">
            <form>
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <span class="modal-title fw-bold">Delete user</span>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <label class="fw-bold mb-3">ID
                                        <input type="text"
                                               class="form-control"
                                               disabled
                                               value="${user.id}">
                                    </label>

                                    <label class="fw-bold mb-3">First name
                                        <input type="text"
                                               class="form-control"
                                               disabled
                                               value="${user.firstName}">
                                    </label>

                                    <label class="fw-bold mb-3">Last name
                                        <input type="text"
                                               class="form-control"
                                               disabled
                                               name="lastName"
                                               value="${user.lastName}">
                                    </label>

                                    <label class="fw-bold mb-3">Age
                                        <input type="number"
                                               class="form-control"
                                               disabled
                                               name="age"
                                               value="${user.age}">
                                    </label>

                                    <label class="fw-bold mb-3">Email
                                        <input type="text"
                                               class="form-control"
                                               disabled
                                               name="email"
                                               value="${user.email}">
                                    </label>

                                    <label class="fw-bold mb-3 w-100">Role
                                        <select class="form-control"
                                                multiple
                                                size="${user.roles.length}"
                                                name="roles">
                                            ${user.roles.map(viewRoleOption).join('')}
                                        </select>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    `;
}

/**
 * Edit user modal
 *
 * @param {User} user
 * @returns {string}
 */
function viewModalEditUser(user) {
    return `
        <div class="modal fade">
            <form>
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <span class="modal-title fw-bold">Delete user</span>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <label class="fw-bold mb-3">ID
                                        <input type="text" 
                                               class="form-control" 
                                               disabled 
                                               name="id" 
                                               value="${user.id}">
                                    </label>

                                    <label class="fw-bold mb-3">First name
                                        <input type="text"
                                               class="form-control"
                                               name="firstName"
                                               value="${user.firstName}">
                                    </label>

                                    <label class="fw-bold mb-3">Last name
                                        <input type="text"
                                               class="form-control"
                                               name="lastName"
                                               value="${user.lastName}">
                                    </label>

                                    <label class="fw-bold mb-3">Age
                                        <input type="number"
                                               min="0"
                                               class="form-control"
                                               name="age"
                                               value="${user.age}">
                                    </label>

                                    <label class="fw-bold mb-3">Email
                                        <input type="text"
                                               class="form-control"
                                               name="email"
                                               value="${user.email}">
                                    </label>

                                    <label class="fw-bold mb-3">Password
                                        <input type="password"
                                               class="form-control"
                                               name="password">
                                    </label>

                                    <label class="fw-bold mb-3 w-100">Role
                                        <select class="form-control"
                                                required
                                                multiple
                                                size="${user.roles.length}"
                                                name="roles">
                                            ${user.roles.map(viewRoleOption).join('')}
                                        </select>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary">Close</button>
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    `;
}

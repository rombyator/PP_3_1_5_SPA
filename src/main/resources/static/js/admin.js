import {
    viewMenu,
    viewUserInfo,
    viewUsersListing,
    viewUserAdd,
    User,
} from './common.js';

$(document).ready(function () {
    //--- Show navbar & user info
    fetch('/api/v1/iam')
        .then(response => response.json())
        .then(user => {
            $('#view--menu').html(viewMenu(user));
            $('#view--user_info').html(viewUserInfo(user));
        });

    //--- Show users listing
    fetch('/api/v1/users')
        .then(response => response.json())
        .then(users => {
            $('#view--users_listing').html(viewUsersListing(users));
        });

    //--- Show add user form
    $('#view--user_add').html(viewUserAdd(User.empty(), ''));
});

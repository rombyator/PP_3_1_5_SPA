import {viewMenu, viewUserInfo} from './common.js';

$(document).ready(function () {
    //--- Show navbar & user info
    fetch('/api/v1/iam')
        .then(response => response.json())
        .then(user => {
            $('#view--menu').html(viewMenu(user));
            $('#view--user_info').html(viewUserInfo(user));
        });
});

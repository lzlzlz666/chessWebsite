function fetchAdminList() {
    $.ajax({
        url: '/searchAdmin',
        type: 'GET',
        data: { username: '' },
        success: function(response) {
            renderAdminList(response);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function renderAdminList(admins) {
    var tbody = $('.users tbody');
    tbody.empty();
    admins.forEach(function(admin) {
        var newRow = `
            <tr>
                <td><input type="checkbox" class="checkbox"></td>
                <td>${admin.adminId}</td>
                <td>${admin.adminName}</td>
                <td>${admin.password}</td>
                <td>
                    <button class="delete-button" onclick="deleteRow(this)">删除</button>
                    <button class="update-button" onclick="openUpdateUserForm(this)">更新</button>
                </td>
            </tr>
        `;
        tbody.append(newRow);
    });
}

function deleteRow(btn) {
    var row = $(btn).closest('tr');
    var adminId = row.find('td:eq(1)').text();

    $.ajax({
        url: '/deleteAdmin/' + adminId,
        type: 'DELETE',
        success: function(response) {
            row.remove();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function selectAllCheckboxes() {
    var checkboxes = $('.users tbody .checkbox');
    var selectAllCheckbox = $('#selectAll');
    checkboxes.prop('checked', selectAllCheckbox.prop('checked'));
}

function deleteSelected() {
    $('.users tbody .checkbox:checked').each(function() {
        var row = $(this).closest('tr');
        var adminId = row.find('td:eq(1)').text();

        $.ajax({
            url: '/deleteAdmin/' + adminId,
            type: 'DELETE',
            success: function(response) {
                row.remove();
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
}

var selectedRow;

function openUpdateUserForm(row) {
    var cells = $(row).closest('tr').find('td');
    var username = $(cells[2]).text();
    var password = $(cells[3]).text();

    $('#update-username').val(username);
    $('#update-password').val(password);

    $('.update-user-form').css('display', 'block');
    selectedRow = $(row).closest('tr');
}

function updateUser() {
    var newUsername = $('#update-username').val();
    var newPassword = $('#update-password').val();

    var adminId = selectedRow.find('td:eq(1)').text();

    $.ajax({
        url: '/updateAdmin/' + adminId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ adminName: newUsername, password: newPassword }),
        success: function(response) {
            selectedRow.find('td:eq(2)').text(newUsername);
            selectedRow.find('td:eq(3)').text(newPassword);
            closeUpdateUserForm();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function closeUpdateUserForm() {
    $('.update-user-form').css('display', 'none');
}

function openSearchUserForm() {
    $('.search-user-form').css('display', 'block');
}

function closeSearchUserForm() {
    $('.search-user-form').css('display', 'none');
}

function searchUser() {
    var username = $('#search-username').val().toLowerCase();
    $.ajax({
        url: '/searchAdmin',
        type: 'GET',
        data: { username: username },
        success: function(response) {
            renderAdminList(response);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
    closeSearchUserForm();
}

function addUser() {
    var username = $('#new-username').val();
    var password = $('#new-password').val();

    $.ajax({
        url: '/addAdmin',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ adminName: username, password: password }),
        success: function(response) {
            fetchAdminList();
            closeAddUserForm();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function closeAddUserForm() {
    $('#new-username').val('');
    $('#new-password').val('');
    $('.add-user-form').css('display', 'none');
}

function openAddUserForm() {
    $('.add-user-form').css('display', 'block');
}

$(document).ready(function() {
    fetchAdminList();
    // 获取管理员等级信息，并根据等级控制操作权限
    $.get("/admin/grade", function(data){
        if(data === 1){
            // 当管理员等级为1时，显示所有操作按钮
            $(".add-button, .delete-button, .update-button, .search-button").show();
        } else {
            // 否则，隐藏所有操作按钮
            $(".add-button, .delete-button, .update-button, .search-button").hide();
        }
    });
});

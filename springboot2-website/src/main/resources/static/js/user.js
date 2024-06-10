function fetchAdminList() {
    $.ajax({
        url: '/searchUser',
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

function renderAdminList(users) {
    var tbody = $('.users tbody');
    tbody.empty();
    users.forEach(function(user) {
        var newRow = `
          <tr>
              <td><input type="checkbox" class="checkbox"></td>
              <td>${user.id}</td>
              <td>${user.username}</td>
              <td>${user.password}</td>
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
    var id = row.find('td:eq(1)').text();

    $.ajax({
        url: '/deleteUser/' + id,
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
        var id = row.find('td:eq(1)').text();

        $.ajax({
            url: '/deleteUser/' + id,
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

    var id = selectedRow.find('td:eq(1)').text();

    $.ajax({
        url: '/updateUser/' + id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ username: newUsername, password: newPassword }),
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
        url: '/searchUser',
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
        url: '/addUser',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ username: username, password: password }),
        success: function(response) {
            var newRow = `
              <tr>
                  <td><input type="checkbox" class="checkbox"></td>
                  <td>${response.id}</td>
                  <td>${username}</td>
                  <td>${password}</td>
                  <td>
                      <button class="delete-button" onclick="deleteRow(this)">删除</button>
                      <button class="update-button" onclick="openUpdateUserForm(this)">更新</button>
                  </td>
              </tr>
          `;
            $('.users tbody').append(newRow); // 将新增的用户追加到当前列表中

            closeAddUserForm(); // 关闭新增用户的界面
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
});

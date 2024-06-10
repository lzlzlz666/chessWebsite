$(document).ready(function() {
    fetchProductList(); // 获取产品列表
});

function fetchProductList() {
    $.ajax({
        url: '/products', // 产品接口地址
        type: 'GET',
        success: function(response) {
            renderProductList(response); // 渲染产品列表
        },
        error: function(xhr, status, error) {
            console.error(error); // 输出错误信息
        }
    });
}

function renderProductList(products) {
    var tbody = $('#productTableBody'); // 产品表格的tbody元素
    tbody.empty(); // 清空tbody

    products.forEach(function(product) {
        var newRow = `
          <tr data-product-id="${product.productId}">
              <td><input type="checkbox" class="checkbox"></td>
              <td>${product.productId}</td> <!-- 使用productId而不是id -->
              <td>${product.category}</td>
              <td>${product.name}</td>
              <td>${product.price}</td>
              <td>${product.description}</td>
              <td>${product.stock}</td>
              <td>${product.productImage}</td>
              <td>
                  <button class="delete-button" onclick="deleteProduct(${product.productId})">删除</button>
                  <button class="update-button" onclick="openUpdateProductForm(${product.productId})">更新</button>
              </td>
          </tr>
      `;
        tbody.append(newRow); // 添加新行到tbody
    });
}

function deleteProduct(productId) {
    $.ajax({
        url: '/products/' + productId, // 删除产品的接口地址
        type: 'DELETE',
        success: function(response) {
            console.log('产品删除成功:', response);
            // 从前端删除对应的表格行
            $(`tr[data-product-id="${productId}"]`).remove();
            fetchProductList();
        },
        error: function(xhr, status, error) {
            console.error('删除产品时出错:', error);
        }
    });
}

function openUpdateProductForm(productId) {
    var row = $(`#productTableBody tr[data-product-id="${productId}"]`);
    var cells = row.find('td');

    // 获取原始数据
    var category = $(cells[2]).text();
    var name = $(cells[3]).text();
    var price = $(cells[4]).text();
    var description = $(cells[5]).text();
    var stock = $(cells[6]).text();
    var productImage = $(cells[7]).text();

    // 将更新表单的输入字段填充为原始数据
    $('#update-category').val(category);
    $('#update-name').val(name);
    $('#update-price').val(price);
    $('#update-description').val(description);
    $('#update-stock').val(stock);
    $('#update-productImage').val(productImage);

    // 显示更新表单
    $('.update-product-form').css('display', 'block');

    // 绑定更新按钮的点击事件，并传递 productId
    $('#update-button').unbind().click(function(event) {
        console.log(productId);
        updateProduct(productId);
    });
}

function closeUpdateProductForm() {
    $('.update-product-form').css('display', 'none');
}

function addProduct() {
    var category = $('#category').val();
    var name = $('#name').val();
    var price = $('#price').val();
    var description = $('#description').val();
    var stock = $('#stock').val();
    var productImage = $('#productImage').val();

    var productData = {
        category: category,
        name: name,
        price: price,
        description: description,
        stock: stock,
        productImage: productImage
    };

    $.ajax({
        url: '/products', // 添加产品的接口地址
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(productData),
        success: function(response) {
            fetchProductList(); // 添加成功后刷新产品列表
            closeAddProductForm();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function updateProduct(productId) {
    var category = $('#update-category').val();
    var name = $('#update-name').val();
    var price = $('#update-price').val();
    var description = $('#update-description').val();
    var stock = $('#update-stock').val();
    var productImage = $('#update-productImage').val();

    var productData = {
        category: category,
        name: name,
        price: price,
        description: description,
        stock: stock,
        productImage: productImage
    };

    $.ajax({
        url: '/products/' + productId, // 更新产品的接口地址
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(productData),
        success: function(response) {
            console.log(response);
            fetchProductList();
            closeUpdateProductForm();
        },
        error: function(xhr, status, error) {
            console.error("XHR status: " + status);
            console.error("Error message: " + error);
            console.error("Server response: " + xhr.responseText);
        }
    });
}



function openAddProductForm() {
    $('.add-product-form').css('display', 'block');
}

function closeAddProductForm() {
    $('.add-product-form').css('display', 'none');
}

function openSearchProductForm() {
    $('.search-product-form').css('display', 'block');
}

function closeSearchProductForm() {
    $('.search-product-form').css('display', 'none');
}

function searchProduct() {
    var category = $('#search-category').val();
    $.ajax({
        url: '/products?category=' + category, // 搜索产品的接口地址
        type: 'GET',
        success: function(response) {
            renderProductList(response);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function selectAllCheckboxes() {
    var checkboxes = $('.checkbox');
    var selectAllCheckbox = $('#selectAll');
    checkboxes.prop('checked', selectAllCheckbox.prop('checked'));
}

function deleteSelected() {
    var productIds = []; // 存储选中的产品ID的数组

    // 遍历所有选中的复选框并收集产品ID
    $('.checkbox:checked').each(function() {
        var productId = $(this).closest('tr').find('td:eq(0)').text(); // 获取表格中第二列（产品ID）的文本内容
        productIds.push(parseInt(productId)); // 将产品ID添加到数组中
    });

    // 发送批量删除请求
    $.ajax({
        url: '/products', // 批量删除产品的接口地址
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(productIds), // 将产品ID转换为JSON格式并发送
        success: function(response) {
            fetchProductList(); // 删除成功后刷新产品列表
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

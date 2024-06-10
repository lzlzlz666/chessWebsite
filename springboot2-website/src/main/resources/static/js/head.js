document.addEventListener('DOMContentLoaded', function() {
    // 动态加载 index.html 中的 CSS
    var cssLink = document.createElement('link');
    cssLink.rel = 'stylesheet';
    cssLink.href = '../css/index.css'; // 确保路径正确
    document.head.appendChild(cssLink);

    // 创建 XMLHttpRequest 对象来加载 index.html 的内容
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'index.html', true);
    xhr.responseType = 'document';

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 请求成功，获取index.html中的 .top div
            const content = xhr.response;
            const topContent = content.querySelector('.top');

            // 将获取的内容放入当前页面的 .top div 中
            document.querySelector('.top').innerHTML = topContent.innerHTML;
        } else {
            // 如果请求失败，打印错误信息
            console.error('Failed to load content: ', xhr.statusText);
        }
    };

    // 发送请求
    xhr.send();
});
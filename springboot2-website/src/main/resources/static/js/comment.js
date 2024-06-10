function submitMessage() {
    var messageInput = document.getElementById("messageInput");
    var messageText = messageInput.value.trim();

    if (messageText !== "") {
        var messageBoard = document.getElementById("messageBoard");
        var messageElement = document.createElement("div");
        messageElement.classList.add("message");
        messageElement.textContent = messageText;
        messageBoard.appendChild(messageElement);

        // 清空输入框
        messageInput.value = "";

        // 滚动到底部
        messageBoard.scrollTop = messageBoard.scrollHeight;
    } else {
        alert("请输入留言内容！");
    }
}

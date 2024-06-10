document.addEventListener("DOMContentLoaded", function() {
    const board = document.getElementById('board');
    let currentPlayer = 'black'; // 默认开始是黑棋
    const gridState = Array(361).fill(null); // 用于记录棋盘状态
    const moveHistory = []; // 记录每一步棋的详细信息
    let gameStarted = false; // 记录对局是否已经开始，默认为 false

    let blackTimer = "02:00"; // 初始时间设置为两分钟
    let whiteTimer = "02:00";
    let timerInterval;

    // 初始化棋盘，每个格子都可以放置棋子
    for (let i = 0; i < 361; i++) {
        const cell = document.createElement('div');
        cell.classList.add('cell');
        cell.addEventListener('click', () => placeStone(i));
        board.appendChild(cell);
    }

    // 定义允许落子函数
    function allowPlacement() {
        const cells = document.querySelectorAll('.cell');
        cells.forEach(cell => cell.addEventListener('click', handlePlacement));
    }

    // 定义开始对局函数
    function startGame() {
        // 将游戏状态标记为已开始
        gameStarted = true;
        // 重置计时器
        resetTimer();
        // 允许落子
        allowPlacement();
    }

    // 获取开始对局按钮
    const startGameButton = document.getElementById('startGameButton');

    // 给开始对局按钮添加点击事件处理逻辑
    startGameButton.addEventListener('click', startGame);

    function resetTimer() {
        clearInterval(timerInterval);
        blackTimer = "02:00"; // 重置为两分钟
        whiteTimer = "02:00";
        updateTimers();
        if (gameStarted) {
            timerInterval = setInterval(function() {
                if (currentPlayer === 'black') {
                    blackTimer = decrementTime(blackTimer);
                    if (blackTimer === "00:00") {
                        alert("Black time out!");
                        clearInterval(timerInterval);
                    }
                } else {
                    whiteTimer = decrementTime(whiteTimer);
                    if (whiteTimer === "00:00") {
                        alert("White time out!");
                        clearInterval(timerInterval);
                    }
                }
                updateTimers();
            }, 1000);
        }
    }

    function decrementTime(time) {
        let [minutes, seconds] = time.split(':').map(Number);
        if (seconds === 0) {
            if (minutes === 0) {
                return "00:00";
            } else {
                minutes--;
                seconds = 59;
            }
        } else {
            seconds--;
        }
        return `${minutes < 10 ? "0" + minutes : minutes}:${seconds < 10 ? "0" + seconds : seconds}`;
    }

    function updateTimers() {
        document.getElementById('blackTimer').textContent = "Black: " + blackTimer;
        document.getElementById('whiteTimer').textContent = "White: " + whiteTimer;
    }

    function placeStone(index) {
        // 检查对局是否已经开始，如果未开始，则不允许落子
        if (!gameStarted) {
            alert("请先点击开始对局按钮开始游戏！");
            return;
        }

        const cell = board.children[index];
        if (cell.childNodes.length === 0 && gridState[index] === null) {
            const stone = document.createElement('div');
            stone.classList.add('stone', currentPlayer);
            cell.appendChild(stone);
            gridState[index] = currentPlayer;

            // 检查并处理棋子的捕获
            const capturedStones = checkCapture(index, currentPlayer);
            moveHistory.push({index, placedColor: currentPlayer, capturedStones});

            // 切换当前玩家
            currentPlayer = currentPlayer === 'black' ? 'white' : 'black';
            resetTimer();
        }
    }

    function checkCapture(index, color) {
        const oppositeColor = color === 'black' ? 'white' : 'black';
        const directions = [-1, 1, -19, 19];
        let captured = [];
        directions.forEach(dir => {
            const n = index + dir;
            if (n >= 0 && n < 361 && gridState[n] === oppositeColor && !hasLiberties(n)) {
                captured = captured.concat(removeStones(n, oppositeColor));
            }
        });
        return captured;
    }

    function hasLiberties(index) {
        const color = gridState[index];
        const visited = new Set();
        const stack = [index];
        while (stack.length > 0) {
            const pos = stack.pop();
            const directions = [];
            if (pos % 19 !== 0) directions.push(-1); // 不在左边界
            if (pos % 19 !== 18) directions.push(1); // 不在右边界
            if (pos >= 19) directions.push(-19); // 不在顶部边界
            if (pos < 342) directions.push(19); // 不在底部边界

            for (let dir of directions) {
                const n = pos + dir;
                if (gridState[n] === null) return true; // 发现气
                if (gridState[n] === color && !visited.has(n)) {
                    visited.add(n);
                    stack.push(n);
                }
            }
        }
        return false; // 无气
    }

    function removeStones(index, color) {
        const stack = [index];
        const visited = new Set(stack);
        const removed = [];

        while (stack.length > 0) {
            const pos = stack.pop();
            gridState[pos] = null;
            removed.push(pos);
            board.children[pos].innerHTML = ''; // 清除棋子

            const directions = [-1, 1, -19, 19];
            for (let dir of directions) {
                const n = pos + dir;
                if (n >= 0 && n < 361 && gridState[n] === color && !visited.has(n)) {
                    visited.add(n);
                    stack.push(n);
                }
            }
        }
        return removed;
    }

    // 添加悔棋按钮的处理逻辑
    const undoButton = document.getElementById('undoButton');
    undoButton.addEventListener('click', undoMove);

    function undoMove() {
        if (moveHistory.length > 0) {
            const lastMove = moveHistory.pop();
            const lastCell = board.children[lastMove.index];
            lastCell.innerHTML = '';
            gridState[lastMove.index] = null;

            // 恢复被提走的棋子
            lastMove.capturedStones.forEach(index => {
                const cell = board.children[index];
                const stone = document.createElement('div');
                stone.classList.add('stone', lastMove.placedColor === 'black' ? 'white' : 'black');
                cell.appendChild(stone);
                gridState[index] = lastMove.placedColor === 'black' ? 'white' : 'black';
            });

            // 切换回上一位玩家
            currentPlayer = lastMove.placedColor;
            resetTimer(); // 重新启动计时器
        }
    }

    // 新增重新对弈按钮的处理逻辑
    const restartButton = document.getElementById('restartButton');
    restartButton.addEventListener('click', restartGame);

    function restartGame() {
        const cells = document.querySelectorAll('.cell');
        cells.forEach(cell => cell.innerHTML = ''); // 清除所有棋子
        gridState.fill(null); // 重置棋盘状态数组
        currentPlayer = 'black'; // 重置当前玩家为黑棋
        moveHistory.length = 0; // 清空历史记录
        gameStarted = false; // 将游戏状态标记为未开始
        resetTimer(); // 重置计时器
    }

    // 初始化计时器
    updateTimers();
});

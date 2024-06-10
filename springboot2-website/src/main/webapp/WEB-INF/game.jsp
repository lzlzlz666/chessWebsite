<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>围棋对弈</title>
    <link rel="stylesheet" href="css/game.css">
</head>
<body>
<div class="top">
    <jsp:include flush="true" page="head.jsp"></jsp:include>
</div>
<div class="core">
    <div id="board"></div>
    <div id="step">
        <div class="id">
            <p class="first">玩家1(执黑)</p>
            <p class="with">VS</p>
            <p class="second">玩家2(执白)</p>
        </div>
        <div class="display">
            <img src="images/monkey.png" alt="">
            <img src="images/chicken.jpg" alt="">
        </div>

        <div class="time">
            <div class="left">
                <p id="blackTimer"></p>
            </div>

            <div class="center"></div>

            <div class="right">
                <p id="whiteTimer"></p>
            </div>
        </div>

        <div class="do">
            <div class="money">
                <img src="images/erweima.jpg" alt="">
            </div>
            <div class="dosth">
                <button id="startGameButton">开始对局</button>
                <button id="undoButton">悔棋</button>
                <button id="restartButton">重新对弈</button>
            </div>
        </div>

        <div class="photo">
            <img src="images/ChessPeople.jpg" alt="">
        </div>

        <div class="phone">
            <p>客服电话：18958750073</p>
        </div>
    </div>
</div>
<script src="js/game.js"></script>
</body>
</html>


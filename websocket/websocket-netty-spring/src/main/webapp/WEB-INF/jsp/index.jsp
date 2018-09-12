<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
</head>
<script type="text/javascript">
    var socket;
    //实际生产中，id可以从session里面拿用户id
    var id = Math.random().toString(36).substr(2);
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }

    if (window.WebSocket) {
        socket = new WebSocket("ws://localhost:9999");

        socket.onmessage = function (event) {
            appendln("receive:" + event.data);
        };

        socket.onopen = function (event) {
            appendln("WebSocket is opened");
            login();
        };

        socket.onclose = function (event) {
            appendln("WebSocket is closed");
        };
    } else {
        alert("WebSocket is not support");
    }


    function appendln(text) {
        var ta = document.getElementById('responseText');
        ta.value += text + "\r\n";
    }

    function login() {
        var date = {"id": id, "type": "aa"};
        var login = JSON.stringify(date);
        socket.send(login);
    }

</script>
<body>

<input type="text" id="tx"/>
<br/><br/>
<!-- 消息发送按钮 -->
<button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>

<textarea id="responseText" style="width: 800px;height: 300px;"></textarea>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    // 点击了发送消息按钮的响应事件
    $("#TXBTN").click(function () {

        // 获取消息内容
        var text = $("#tx").val();

        // 判断
        if (text == null || text == "") {
            alert(" content  can not empty!!");
            return false;
        }
        var msg = {
            "id": id,
            msgContent: text,
        };

        // 发送消息
        socket.send(JSON.stringify(msg));

    });
</script>
</body>
</html>

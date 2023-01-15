<html>
<head>
    <title>Chat WebSocket</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
    <button class="connect">Connect</button ><br>
    <button class="disconnect">Disconnect</button><br>
    <button class="subscribe">Subscribe</button><br>
    <input type="text" class="sendMessageInput"><button class="sendMessageButton">Send</button>
</body>
</html>

<script type="text/javascript">
    let stompClient = null;
    let subscription = null;

    let connect = function connect() {
        let socket = new SockJS('/cinema/websocket/handshake/movie/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log(frame);
        });
    }
    let receiveMessageCallback = function(messageDto) {
        // called when the client receives a STOMP messageDto from the server
        //function(messageOutput) { showMessageOutput(JSON.parse(messageOutput.body) }
        if (messageDto.body) {
            alert("got messageDto with body " + messageDto.body)
        } else {
            alert("got empty messageDto");
        }
    };

    let subscribe = function() {
        subscription = stompClient.subscribe("/topic/messages", receiveMessageCallback);
        console.log('Subscription object: ' + subscription)
    }

    let sendMessage = function(from, text) {
        stompClient.send("/app/chat", {}, JSON.stringify({'from':from, 'text':text}));
    }


    $('.connect').click(connect);
    $('.subscribe').click(subscribe);
    $('.sendMessageButton').click(function (){
        let msg = $('.sendMessageInput').val();
        let from = 'Oleg';
        sendMessage(from, msg);
    });
</script>



function disconnect() {
stompClient.disconnect(function() {
console.log("Disconnected");
});
}
// let subscribeCallback = function(messageDto) {
//     if (messageDto.body) {
//         console.log("Subscribe callback: " + messageDto.body);
//     } else {
//         console.log("Subscribe callback: empty body.");
//     }
// };
//
// let subscribe = function() {
//     var subscription = stompClient.subscribe("/topic/messages", subscribeCallback);
// }
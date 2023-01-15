<html>
<header>
    <meta charset="UTF-8">
    <title>Movie chat</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <style>
        body {
            display: flex;
            flex-direction: row;
            align-items: stretch;
        }
        .user_profile {
            flex-grow: 100;
            background-color: greenyellow;
            display: flex;
            flex-direction: row;
            align-items: stretch;
        }

        .user_profile_picture_with_input {
            flex-grow: 1;

            height: 60%;
            width: 40%;
            background-color: green;
            display: flex;
            flex-direction: column;
            /*justify-content: center; !*Выравнивание элементов поперек оси вдоль которой расположены элементы. У меня ось вертикльная, поэтому элементы расположены по вертикали. Это сойство сдвигает их поперек оси: влево, вправо итд*!*/
            /*align-items: center;*/
        }

        .user_profile_picture {
            flex-grow: 100;

            width: 100%;
            background-color: burlywood;
            object-fit: contain;
        }

        .user_profile_picture_input {
            flex-grow: 1;
            width: 100%;
        }

        .user_profile_info {
            flex-grow: 100;

            background-color: #4654e1;
            height: 100%;
        }
        /*.user_profile_info > * {*/
        /*    */
        /*    */
        /*}*/
        .user_profile_info_pictures {
            background-color: aqua;
            width: 100%;
            height: 50%;
            overflow: scroll;
        }
        .user_profile_info_logins {
            background-color: crimson;
            width: 100%;
            height: 50%;
            overflow: scroll;
        }
        .chat {
            flex-grow: 1;

            width: 40%;
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: stretch;
        }
        .chat_name {
            background-color: blueviolet;
            width: 100%;
            text-align: center;
            flex-grow: 1;
            margin: 0;
        }
        .helloUser {
            background-color: rgb(226, 168, 43);
            width: 100%;
            text-align: center;
            flex-grow: 1;
            margin: 0;
        }
        .chat_window {
            width: 100%;
            flex-grow: 100;
            display: flex;
            flex-direction: column;
            background-color: antiquewhite;
            overflow: scroll;
            /*padding : 10px;*/
            /*border: 2px solid #ccc;*/
        }
        .input_controls {
            width: 100%;
            height: 4%;
            flex-grow: 1;

            display: flex;
            flex-direction: row;
            align-items: stretch;
        }
        .input_controls > input {
            flex-grow: 100;
        }
        .input_controls > button {
            flex-grow: 1;
        }
        .message_holder {
            width: 100%;
            margin-bottom : 5px;
        }
        .message_holder > .user1, .user2 {
            width: 50%;
            border-color: black;
            background-color: burlywood;
        }

        .user1{
            float : left;
            border-radius : 0px 10px 10px 10px;
        }
        .user2{
            float : right;
            border-radius : 10px 0px 10px 10px;
        }

        .message_user_name {
            background-color: rgba(169, 169, 169, 0.366);
            border: 2px solid #ccc;
            border-radius : 10px 10px 10px 10px;
            width: fit-content;
        }
    </style>
</header>
<body>
<div class="user_profile">
    <div class="user_profile_picture_with_input">
        <#if userModelKey.currentUserPicture??>
            <#assign pictureId = "${userModelKey.currentUserPicture.id}">
        <#else>
            <#assign pictureId = "null">
        </#if>
        <img class="user_profile_picture" src="${getUserPicturePathKey}/${pictureId}" alt="user picture ist weg" width="600" height="400">
        <div class="user_profile_picture_input">
            <input type="file" name="userPicture" class="user_picture_input"><button class="user_picture_send_button">send</button>
        </div>
    </div>
    <div class="user_profile_info">
        <div class="user_profile_info_pictures">
            <#list userModelKey.userPictureList as userPicture>
                <div>
                    <a href="${getUserPicturePathKey}/${userPicture.id}">${userPicture.originalName}</a>
                </div>
            </#list>
        </div>
        <div class="user_profile_info_logins">
            <ol>
            <#list userAuthenticationsModelKey as userAuthentication>
                <li>address: ${userAuthentication.remoteAddress}. Date: ${userAuthentication.date}. Time:  ${userAuthentication.time}</li>
            </#list>
            </ol>
        </div>
        <div></div>
    </div>
</div>
<div class="chat">
    <h1 class="chat_name">Movie "${movieModelKey.title}" chat</h1>
    <h2 class="helloUser">Hello user name</h2>
    <div class="chat_window"></div>
    <div class="input_controls">
        <input class="chat_input" type="text" ><button class="send_message_button">send</button>
    </div>
</div>
</body>
</html>

<script>

    let scrollChatDown = function () {
        let ChatDiv = $('.chat_window');
        let height = ChatDiv[0].scrollHeight;
        ChatDiv.scrollTop(height);
    }

    let getCookie = function getCookie(cname) {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for(let i = 0; i <ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    let createChatMessageElement = function(message, messageUser, currentUser) {
        let userMessageUserNameSpanElement = document.createElement('span');
        userMessageUserNameSpanElement.className = "message_user_name";
        userMessageUserNameSpanElement.innerHTML = messageUser + ': ';

        let messageSpanElement = document.createElement('span');
        if (messageUser === currentUser) {
            messageSpanElement.className ="chat user2";
        } else {
            messageSpanElement.className = "chat user1";
        }

        let messageTextSanElement = document.createElement('span');
        messageTextSanElement.innerHTML = message;

        messageSpanElement.appendChild(userMessageUserNameSpanElement);
        messageSpanElement.appendChild(messageTextSanElement);

        let divElement = document.createElement('div');
        divElement.className = 'message_holder';
        divElement.appendChild(messageSpanElement);
        return divElement;
    }

    let showHelloUserLabel = function(currentUserName) {
        currentUserName = currentUserName !== undefined ? currentUserName : "undefined name";
        $('.helloUser').text('Hello, ' + currentUserName);
    }

    let loadAndshowChatMessages = function(currentUserName) {
        let getMessagesUrl = document.URL.toString() + "/message";
        $.getJSON(getMessagesUrl, function(data,status) {
            for (let i = 0; i < data.length; i++) {
                let messageElement = createChatMessageElement(data[i].text, data[i].userName, currentUserName);
                $('.chat_window').append(messageElement);
            }
            $(scrollChatDown);
        });
    }

    let STOMP_CLIENT = null;
    let SUBSCRIPTION = null;
    let SEND_MESSAGE_URL = "/app/movies/" + "${movieModelKey.id}" + "/chat/message";
    let SUBSCRIPTION_MESSAGE_URL = "/topic/movies/" + "${movieModelKey.id}" + "/chat/message";;
    let CURRENT_USER_NAME = getCookie('user');
    let GET_USER_PICTURE_PATH = "${getUserPicturePathKey}";

    let receiveMessageCallback = function(message) {
        // called when the client receives a STOMP messageDto from the server
        //function(messageOutput) { showMessageOutput(JSON.parse(messageOutput.body) }
        let messageBodyObject = JSON.parse(message.body);
        let messageElement = createChatMessageElement(messageBodyObject.text, messageBodyObject.userName, CURRENT_USER_NAME);
        $('.chat_window').append(messageElement);
    };

    let subscribe = function() {
        SUBSCRIPTION = STOMP_CLIENT.subscribe(SUBSCRIPTION_MESSAGE_URL, receiveMessageCallback);
        console.log('Subscription object: ' + SUBSCRIPTION)
    }

    let stompConnectionFailureCallBack = function(error) {
        alert('Failed to connect to chat: ' + error);
    }

    let connectAndSubscribe = function connect() {
        let socket = new SockJS('/cinema/websocket/handshake/movie/chat');
        STOMP_CLIENT = Stomp.over(socket);
        STOMP_CLIENT.connect({}, subscribe, stompConnectionFailureCallBack);
    }

    let sendMessage = function(url, messageObject) {
        STOMP_CLIENT.send(url, {}, JSON.stringify(messageObject));
    }

    let createUserPictureLinkListElement = function(userPictureId, userPictureName) {
        let userPicturePath = GET_USER_PICTURE_PATH + "/" + userPictureId;
        let userPictureLinkElement = document.createElement('a');
        userPictureLinkElement.href = userPicturePath;
        userPictureLinkElement.innerHTML = userPictureName;
        userPictureLinkElement.target = "_blank";
        let div = document.createElement('div');
        div.append(userPictureLinkElement);
        return div;
    }

    showHelloUserLabel(CURRENT_USER_NAME);
    loadAndshowChatMessages(CURRENT_USER_NAME);
    connectAndSubscribe();

    $('.send_message_button').click(function() {

        let messageText = $('.chat_input').val();
        let messageObject = {"text" : messageText, "userName" : CURRENT_USER_NAME};

        sendMessage(SEND_MESSAGE_URL, messageObject);

        $('.chat_input').val("");
    });

    $(".user_picture_send_button").click(function() {
        let userPicture = document.querySelector('.user_picture_input').files[0];
        let formData = new FormData();
        formData.append("userPicture", userPicture);
        let addUserPicturePath = "${addUserPicturePathKey}";

        $.ajax({
            url: addUserPicturePath,
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                let userPictureUrl = "${getUserPicturePathKey}" + "/" + response.currentPictureId;
                $('.user_profile_picture').attr('src', userPictureUrl);

                $('.user_profile_info_pictures').empty();
                for (let i = 0; i < response.pictureList.length; i++) {
                    let picture = response.pictureList[i];
                    let userPictureLinkElement = createUserPictureLinkListElement(picture.id, picture.originalName);
                    $('.user_profile_info_pictures').append(userPictureLinkElement);
                }

            },
            error: function(jqXHR, textStatus, errorMessage) {
                console.log(errorMessage); // Optional
            }
        });
    });
</script>

</html>
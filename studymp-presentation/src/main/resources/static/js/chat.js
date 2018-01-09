var stompClient = null;
var socket = null;
var whoami = null;

function connect() {
    socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect('', '', function(frame) {
        whoami = frame.headers['user-name'];
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/messages', function(message) {
            showMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/active', function(activeMembers) {
            showActive(activeMembers);
        });
    });
}

function showActive(activeMembers) {
    stompClient.send('/app/activeUsers', {}, '');
}
/*
function renderActive(activeMembers) {
    var previouslySelected = $('.user-selected').text();
    var usersWithPendingMessages = new Object();
    $.each($('.pending-messages'), function(index, value) {
        usersWithPendingMessages[value.id.substring(5)] = true; // strip the user-
    });
    var members = $.parseJSON(activeMembers);
    var userDiv = $('<div>', {id: 'users'});
    $.each(members, function(index, value) {
        if (value === whoami) {
            return true;
        }
        var userLine = $('<div>', {id: 'user-' + value});
        userLine.addClass('user-entry');
        if (previouslySelected === value) {
            userLine.addClass('user-selected');
        }
        else {
            userLine.addClass('user-unselected');
        }
        var userNameDisplay = $('<span>');
        userNameDisplay.html(value);
        userLine.append(userNameDisplay);
        userLine.click(function() {
            var foo = this;
            $('.chat-container').hide();
            $('.user-entry').removeClass('user-selected');
            $('.user-entry').addClass('user-unselected');
            userLine.removeClass('user-unselected');
            userLine.removeClass('pending-messages');
            userLine.addClass('user-selected');
            userLine.children('.newmessage').remove();
            var chatWindow = getChatWindow(value);
            chatWindow.show();
        });
        if (value in usersWithPendingMessages) {
            userLine.append(newMessageIcon());
            userLine.addClass('pending-messages');
        }
        userDiv.append(userLine);
    });
    $('#userList').html(userDiv);
}*/

function disconnect() {
    stompClient.disconnect();
    //console.log("Disconnected");
}

$('#send_message_btn').click(function () {
    sendMessageTo();
});

function sendMessageTo() {
    var chatInput = '#message_input_field';
    var user = $(chatInput).attr('destination');
    var message = $(chatInput).val();
    if (!message.length) {
        return;
    }
    stompClient.send("/app/chat", {}, JSON.stringify({
        'recipient': user,
        'message' : message
    }));
    $(chatInput).val('');
    $(chatInput).focus();
}

var Message = function (arg) {
    this.text = arg.text, this.message_side = arg.message_side;
    this.draw = function (_this) {
        return function () {
            var $message;
            $message = $($('.message_template').clone().html());
            $message.addClass(_this.message_side).find('.text').html(_this.text);
            $('.messages').append($message);
            return setTimeout(function () {
                return $message.addClass('appeared');
            }, 0);
        };
    }(this);
    return this;
};

$('.message_input').keyup(function (e) {
    if (e.which === 13) {
        return sendMessageTo();
    }
});

function showMessage(message) {
    var chatWindow = $('.messages');
    var message_side = (message.recipient === whoami) ? 'left' : 'right';
    // var chatWindowTarget = (message.recipient === whoami) ? message.sender : message.recipient;
    // var chatContainer = getChatWindow(chatWindowTarget);
    // var chatWindow = chatContainer.children('.chat');
    // var userDisplay = $('<span>', {class: (message.sender === whoami ? 'chat-sender' : 'chat-recipient')});
    // userDisplay.html(message.sender + ' says: ');
    // var messageDisplay = $('<span>');
    var resultMessage = new Message({
        text: message.message,
        message_side: message_side
    });
    resultMessage.draw();
    return chatWindow.animate({scrollTop: chatWindow.prop('scrollHeight')}, 300);
    /*messageDisplay.html(message.message);
    chatWindow.append(userDisplay).append(messageDisplay).append('<br/>');
    chatWindow.animate({ scrollTop: chatWindow[0].scrollHeight}, 1);
    if (message.sender !== whoami) {
        var sendingUser = $('#user-' + message.sender);
        if (!sendingUser.hasClass('user-selected') && !sendingUser.hasClass('pending-messages')) {
            sendingUser.append(newMessageIcon());
            sendingUser.addClass('pending-messages');
        }
    }*/
}

function newMessageIcon() {
    var newMessage = $('<span>', {class: 'newmessage'});
    newMessage.html('&#x2709;');
    return newMessage;
}

$(document).ready(function() {
    connect();
});
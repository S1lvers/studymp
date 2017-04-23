function showMessage(message) {
  var chatWindowTarget = (message.recipient === whoami) ? message.sender : message.recipient;
  var chatContainer = getChatWindow(chatWindowTarget);
  var chatWindow = chatContainer.children('.chat');
  var userDisplay = $('<span>', {class: (message.sender === whoami ? 'chat-sender' : 'chat-recipient')});
  userDisplay.html(message.sender + ' says: ');
  var messageDisplay = $('<span>');
  messageDisplay.html(message.message);
  chatWindow.append(userDisplay).append(messageDisplay).append('<br/>');
  chatWindow.animate({ scrollTop: chatWindow[0].scrollHeight}, 1);
  if (message.sender !== whoami) {
    var sendingUser = $('#user-' + message.sender);
    if (!sendingUser.hasClass('user-selected') && !sendingUser.hasClass('pending-messages')) {
      sendingUser.append(newMessageIcon());
      sendingUser.addClass('pending-messages');
    }
  }
}
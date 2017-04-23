function sendMessageTo(user) {
  var chatInput = '#input-chat-' + user;
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
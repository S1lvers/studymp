function getChatWindow(userName) {
  var existingChats = $('.chat-container');
  var elementId = 'chat-' + userName;
  var containerId = elementId + '-container';
  var selector = '#' + containerId;
  var inputId = 'input-' + elementId;
  if (!$(selector).length) {
    var chatContainer = $('<div>', {id: containerId, class: 'chat-container'});
    var chatWindow = $('<div>', {id: elementId, class: 'chat'});
    var chatInput = $('<textarea>', {id: inputId, type: 'text', class: 'chat-input', rows: '2', cols: '75', 
      placeholder: 'Enter a message.  Something deep and meaningful.  Something you can be proud of.'});
    var chatSubmit = $('<button>', {id: 'submit-' + elementId, type: 'submit', class: 'chat-submit'})
    chatSubmit.html('Send');
    
    chatInput.keypress(function(event) {
      if (event.which == 13) {
        var user = event.currentTarget.id.substring(11); // gets rid of 'input-chat-'
        event.preventDefault();
        sendMessageTo(user);
      }
    });
    
    chatSubmit.click(function(event) {
      var user = event.currentTarget.id.substring(12); // gets rid of 'submit-chat-'
      sendMessageTo(user);
    });
    
    chatContainer.append(chatWindow);
    chatContainer.append(chatInput);
    chatContainer.append(chatSubmit);
    
    if (existingChats.length) {
      chatContainer.hide();
    }
    
    $('body').append(chatContainer);
  }
  return $(selector);
}
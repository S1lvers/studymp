function showActive(activeMembers) {
  renderActive(activeMembers.body);
  stompClient.send('/app/activeUsers', {}, '');
}

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
}
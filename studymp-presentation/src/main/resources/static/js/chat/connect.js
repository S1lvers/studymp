var stompClient;
function connect() {
  var socket = new SockJS('/chat');
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
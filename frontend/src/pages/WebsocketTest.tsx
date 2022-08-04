const WebsocketPage = () => {
  function onClose(evt: any) {
    alert("연결 끊김");
  }
  function onOpen(evt:any) {
    console.log("open");
    wsocket?.send("testseho");
  };
  var wsocket: WebSocket | null = null;
  wsocket = new WebSocket("ws://i7c201.p.ssafy.io:8080/ws/notification");
  wsocket.onclose = onClose;
  wsocket.onopen = onOpen

  let msg = "receiverId,message"
  // 보내는 사람/ 받는사람/ 알림타입:포스팅좋아요,댓글..등등등
  function onSend(evt:any) {
    wsocket?.send(JSON.stringify(msg))
    console.log('send')
  }
  return (
    <div>
      <h1>WebSocket TEST</h1>
      {/* <WebSocketProvider>
      </WebSocketProvider> */}
      <button onClick={onOpen}>open</button>
      <button onClick={onSend}>send</button>
    </div>
  );
};

export default WebsocketPage;
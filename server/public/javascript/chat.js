
//might need value for things below? could cause areas 
const inputField = document.getElementById("chat-input"); 
const outputArea = document.getElementById("chat-area"); 


//how to open a websocket IMPORTANT FOR NEXT TASK
const socketRoute = document.getElementById("ws-route").value; //from hidden input in view 
//route needs to start with ws, this gets rid of the http in the route 
const socket = new WebSocket(socketRoute.replace("http", "ws"));
socket.onopen(() => socket.send("Test message")); 

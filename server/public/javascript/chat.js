

const inputField = document.getElementById("chat-input"); 
const outputArea = document.getElementById("chat-area"); 


//how to open a websocket IMPORTANT FOR NEXT TASK
const socketRoute = document.getElementById("ws-route").value; //from hidden input in view 
//route needs to start with ws, this gets rid of the http in the route 
const socket = new WebSocket(socketRoute.replace("http", "ws"));
inputField.onkeydown = (event) =>{
    if(event.key === 'Enter'){
        socket.send(inputField.value); 
        inputField.value= ''; 
    }
}

socket.onopen = (event) => socket.send("New user connected"); 

socket.onmessage = (event) => {
    outputArea.value += '\n' + event.data; 
}

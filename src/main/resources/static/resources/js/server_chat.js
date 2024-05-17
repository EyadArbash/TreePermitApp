const chatContainer = document.getElementById('chatContainer');

// Funktion zum Abrufen von URL-Parametern
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    const results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

// Hole die Anfrage-ID aus der URL
const requestId = getUrlParameter('requestId');
console.log('Request ID: ' + requestId); // Debug-Ausgabe

function displayMessage(message) {
    const messageElement = document.createElement('div');
    messageElement.textContent = `${message.sender}: ${message.text}`;
    messageElement.classList.add('message');
    if (message.sender !== 'clerk@example.com') {
        messageElement.classList.add('receiver');
    } else {
        messageElement.classList.add('sender'); // Nachrichten des Servers markieren
    }
    chatContainer.appendChild(messageElement);
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

// Abrufen der Nachrichten zwischen dem Server und dem Client
fetch(`/clientMessages?requestId=${requestId}`)
    .then(response => response.json())
    .then(messages => {
        console.log(messages); // Debug-Ausgabe
        if (messages.length === 0) {
            console.log("No messages found");
        }
        messages.forEach(displayMessage);
    })
    .catch(error => console.error('Error fetching messages:', error));

document.getElementById('sendServerMessageForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    const newMessage = {
        sender: 'clerk@example.com',
        text: formData.get('text')
    };

    console.log(newMessage); // Debug-Ausgabe

    fetch(`/sendServerMessage?requestId=${requestId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newMessage)
    })
    .then(response => response.json())
    .then(sentMessage => {
        displayMessage(sentMessage);
        this.reset();
    })
    .catch(error => console.error('Error sending message:', error));
});

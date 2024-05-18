document.addEventListener('DOMContentLoaded', (event) => {
    const chatContainer = document.getElementById('chatContainer');
    const currentUser = document.getElementById('sender').value;

    // Funktion zum Abrufen von URL-Parametern
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        const results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

    // Hole die Anfrage-ID aus der URL
    const requestId = getUrlParameter('requestId');

    function displayMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.textContent = `${message.sender}: ${message.text}`;
        messageElement.classList.add('message');

        if (message.sender === 'clerk') {
            messageElement.classList.add('sender');
        } else {
            messageElement.classList.add('receiver');
        }

        chatContainer.appendChild(messageElement);
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }

    function fetchMessages() {
        fetch(`/serverMessages?requestId=${requestId}`)
            .then(response => response.json())
            .then(messages => {
                chatContainer.innerHTML = '';
                messages.forEach(displayMessage);
            })
            .catch(error => console.error('Error fetching messages:', error));
    }

    // Initiales Abrufen der Nachrichten
    fetchMessages();

    // Nachrichten alle 500ms abrufen
    setInterval(fetchMessages, 500);

    document.getElementById('sendServerMessage').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const newMessage = {
            sender: currentUser,
            text: formData.get('text')
        };

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
});

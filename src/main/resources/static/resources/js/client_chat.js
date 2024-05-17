document.addEventListener('DOMContentLoaded', (event) => {
    const chatContainer = document.getElementById('chatContainer');
    const currentUser = document.getElementById('sender').value;

    fetch('/messages')
        .then(response => response.json())
        .then(messages => {
            if (messages.length === 0) {
                console.log("No messages found");
            }
            messages.forEach(displayMessage);
        })
        .catch(error => console.error('Error fetching messages:', error));

    document.getElementById('sendMessageForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const newMessage = {
            sender: currentUser,
            text: formData.get('text')
        };

        fetch('/send', {
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

    function displayMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.textContent = `${message.sender}: ${message.text}`;
        messageElement.classList.add('message');
        if (message.sender === currentUser) {
            messageElement.classList.add('sender');
        } else {
            messageElement.classList.add('receiver');
        }
        chatContainer.appendChild(messageElement);
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }
});

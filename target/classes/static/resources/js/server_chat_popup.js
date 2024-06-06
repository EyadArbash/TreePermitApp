document.addEventListener('DOMContentLoaded', (event) => {
    const chatContainer = document.getElementById('chatContainer');
    const senderInput = document.getElementById('sender');
    const sendServerMessageForm = document.getElementById('sendServerMessage');

    function openChatPopup(requestId) {
        document.getElementById('chatPopup').style.display = 'block';
        document.getElementById('receiver').value = requestId;
        fetchMessages(requestId); // Fetch messages for the specific request ID
    }

    function closeChatPopup() {
        document.getElementById('chatPopup').style.display = 'none';
    }

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

    function fetchMessages(requestId) {
        fetch(`/serverMessages?requestId=${requestId}`)
            .then(response => response.json())
            .then(messages => {
                chatContainer.innerHTML = '';
                messages.forEach(displayMessage);
            })
            .catch(error => console.error('Error fetching messages:', error));
    }

    sendServerMessageForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const newMessage = {
            sender: senderInput.value,
            text: formData.get('text')
        };

        fetch(`/sendServerMessage?requestId=${document.getElementById('receiver').value}`, {
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

    setInterval(() => {
        const receiver = document.getElementById('receiver').value;
        if (receiver) {
            fetchMessages(receiver);
        }
    }, 500);

    window.openChatPopup = openChatPopup;
    window.closeChatPopup = closeChatPopup;
});

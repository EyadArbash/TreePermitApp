document.addEventListener('DOMContentLoaded', (event) => {
    // Collapsible functionality
    const coll = document.getElementsByClassName('collapsible');
    for (let i = 0; i < coll.length; i++) {
        coll[i].addEventListener('click', function() {
            this.classList.toggle('active');
            const content = this.nextElementSibling;
            if (content.style.display === 'block') {
                content.style.display = 'none';
            } else {
                content.style.display = 'block';
            }
        });
    }

    // Chat functionality
    const chatContainer = document.getElementById('chatContainer');
    const senderInput = document.getElementById('sender');
    const sendClientMessageForm = document.getElementById('sendClientMessage');
    const notificationBadge = document.getElementById('notificationBadge');
    const contactButton = document.getElementById('contactButton'); // Button "KONTAKTIEREN"
    let lastMessageId = 0; // Track the ID of the last message to avoid duplicates
    let requestId = null;

    // Load notification status from sessionStorage
    if (sessionStorage.getItem('notificationHidden') === 'true') {
        notificationBadge.style.display = 'none';
    }

    function openChatPopup(id) {
        requestId = id;
        document.getElementById('chatPopup').style.display = 'block';
        document.getElementById('receiver').value = requestId;
        fetchMessages(requestId); // Fetch messages for the specific request ID
        resetNotificationBadge(); // Reset the notification badge
    }

    function closeChatPopup() {
        document.getElementById('chatPopup').style.display = 'none';
        notificationBadge.style.display = 'none'; // Hide notification badge when chat is closed
    }

    function displayMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.textContent = `${message.sender}: ${message.text}`;
        messageElement.classList.add('message');

        if (message.sender !== 'clerk') {
            messageElement.classList.add('sender');
        } else {
            messageElement.classList.add('receiver');
        }

        chatContainer.appendChild(messageElement);
    }

    function fetchMessages(id) {
        return fetch(`/clientMessages?requestId=${id}`)
            .then(response => response.json())
            .then(messages => {
                messages.forEach(message => {
                    if (message.id > lastMessageId) {
                        displayMessage(message);
                        lastMessageId = message.id;
                    }
                });

                if (messages.length > lastMessageId) {
                    notificationBadge.textContent = messages.length - lastMessageId;
                    notificationBadge.style.display = 'inline';
                    sessionStorage.setItem('notificationHidden', 'false');
                } else {
                    sessionStorage.setItem('notificationHidden', 'true');
                }
            })
            .catch(error => console.error('Error fetching messages:', error));
    }

    function resetNotificationBadge() {
        notificationBadge.style.display = 'none'; // Hide notification badge
        notificationBadge.textContent = ''; // Reset notification text
        sessionStorage.setItem('notificationHidden', 'true'); // Save the state to sessionStorage
    }

    sendClientMessageForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const newMessage = {
            sender: senderInput.value,
            text: formData.get('text')
        };

        fetch(`/sendClientMessage?requestId=${requestId}`, {
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
            resetNotificationBadge(); // Reset the notification badge when message is sent
            lastMessageId++;
            chatContainer.scrollTop = chatContainer.scrollHeight; // Scroll to bottom when a new message is sent
        })
        .catch(error => console.error('Error sending message:', error));
    });

    let isFetchingMessages = false;
    let scrollAtBottom = true;

    chatContainer.addEventListener('scroll', () => {
        scrollAtBottom = chatContainer.scrollTop + chatContainer.clientHeight >= chatContainer.scrollHeight;
    });

    setInterval(() => {
        if (requestId && !isFetchingMessages) {
            isFetchingMessages = true;
            fetchMessages(requestId).finally(() => {
                isFetchingMessages = false;
                if (scrollAtBottom) {
                    chatContainer.scrollTop = chatContainer.scrollHeight;
                }
            });
        }
    }, 500);

    contactButton.addEventListener('click', function() {
        resetNotificationBadge(); // Reset notifications on button click
    });

    window.openChatPopup = openChatPopup;
    window.closeChatPopup = closeChatPopup;
});

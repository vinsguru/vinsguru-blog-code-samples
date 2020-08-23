import { RSocketClient, JsonSerializer, IdentitySerializer } from 'rsocket-core';
import RSocketWebSocketClient from 'rsocket-websocket-client';

// backend ws endpoint
const wsURL = 'ws://localhost:6565/rsocket';

// rsocket client
const client = new RSocketClient({
    serializers: {
        data: JsonSerializer,
        metadata: IdentitySerializer
    },
    setup: {
        keepAlive: 60000,
        lifetime: 180000,
        dataMimeType: 'application/json',
        metadataMimeType: 'message/x.rsocket.routing.v0',
    },
    transport: new RSocketWebSocketClient({
        url: wsURL
    })
});

// error handler
const errorHanlder = (e) => console.log(e);
// response handler
const responseHanlder = (payload) => {
    const li = document.createElement('li');
    li.innerText = payload.data;
    li.classList.add('list-group-item', 'small')
    document.getElementById('result').appendChild(li);
}

const numberRequester = (socket, value) => {
    socket.requestStream({
        data: value,
        metadata: String.fromCharCode('number.stream'.length) + 'number.stream'
    }).subscribe({
        onError: errorHanlder,
        onNext: responseHanlder,
        onSubscribe: subscription => {
            subscription.request(100); // set it to some max value
        }
    })
}

client.connect().then(sock => {
    document.getElementById('n').addEventListener('change', ({srcElement}) => {
        numberRequester(sock, parseInt(srcElement.value));
    })
}, errorHanlder);
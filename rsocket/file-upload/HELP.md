## RSocket file upload
RSocket is a message passing protocol for multiplexed, duplex communication which supports TCP, WebSockets and Aeron (UDP). 

### Goal:
* The client will send a PDF to the server as stream of byte array with size of 4096.
* The server will write each chunk and respond back to the client with status.
* Once the client confirms that it has sent everything, The server also confirms back that file is completely written on the server side.
* If something unexpected happens, the server will respond back with Failed status.
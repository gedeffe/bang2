package com.supinfo.whatzappeuh.receivers;

import com.supinfo.whatzappeuh.Receiver.Message;
import com.supinfo.whatzappeuh.Receiver.MessageReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.io.*;
import java.net.*;

public class MessageReceiverTest {
    @Test
    public void testReceiveMessage() throws IOException {
        //Given
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        String message = "example";
        int port = 6666;

        ServerSocket serverSocketMock = Mockito.mock(ServerSocket.class);
        MessageReceiver mockMessageReceiver = new MessageReceiver(port, serverSocketMock);

        Socket clientMockSocket = Mockito.mock(Socket.class);
        InputStream inputStream = new ByteArrayInputStream(message.getBytes());
        SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);

        BDDMockito.given(serverSocketMock.accept()).willReturn(clientMockSocket);
        BDDMockito.given(clientMockSocket.getOutputStream()).willReturn(Mockito.mock(OutputStream.class));
        BDDMockito.given(clientMockSocket.getInputStream()).willReturn(inputStream);
        BDDMockito.given(clientMockSocket.getRemoteSocketAddress()).willReturn(socketAddress);

        //When
        Message newMessage = mockMessageReceiver.receive();

        //Then
        Assertions.assertNotNull(newMessage);
        Assertions.assertEquals(message, newMessage.getMessage());
        Assertions.assertEquals(inetAddress, newMessage.getAddress());
    }
}

package com.supinfo.whatzappeuh.receivers;

import com.supinfo.whatzappeuh.Receiver.Message;
import com.supinfo.whatzappeuh.Receiver.MessageReceiver;
import com.supinfo.whatzappeuh.Receiver.Receiver;
import com.supinfo.whatzappeuh.Receiver.ReceiverSubscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReceiverSubscriberTest {

    @Test
    public void testSubscribe() throws IOException {
        //Given
        ReceiverSubscriber receiverSubscriber = new MessageReceiver(6666);
        Receiver receiver = message -> {

        };

        //When
        boolean subscribed = receiverSubscriber.subscribe(receiver);

        //Then
        Assertions.assertTrue(subscribed);
    }

}

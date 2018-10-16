package com.mhp.coding.challenges.dependency.notifications;

import com.mhp.coding.challenges.dependency.inquiry.Inquiry;
import com.mhp.coding.challenges.dependency.inquiry.InquiryServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationHandler implements InquiryServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(PushNotificationHandler.class);

    public void sendNotification(final Inquiry inquiry) {
        LOG.info("Sending push notification for: {}", inquiry);
    }

    @Override
    public void send(Inquiry inquiry) {
        sendNotification(inquiry);
    }
}

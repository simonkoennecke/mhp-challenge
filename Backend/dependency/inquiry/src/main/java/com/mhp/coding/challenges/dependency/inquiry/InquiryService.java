package com.mhp.coding.challenges.dependency.inquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InquiryService {

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    private List<InquiryServiceInterface> inquiryService;

    @Autowired
    public InquiryService(List<InquiryServiceInterface> inquiryService) {
        this.inquiryService = inquiryService;
    }

    /**
     * Dispatch inquiry to all available inquiry services.
     *
     * @param inquiry
     */
    public void create(final Inquiry inquiry) {
        inquiryService.stream().forEach(service -> {
            service.send(inquiry);
            LOG.info("Transferred {} to service {}", inquiry, service);
        });
    }

}

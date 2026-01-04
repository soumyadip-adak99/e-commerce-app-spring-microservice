package com.ecommerce.notification.services;

import com.ecommerce.notification.records.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecommerce.notification.enums.EmailTemplates.ORDER_CONFIRMATION;
import static com.ecommerce.notification.enums.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${application.sender-email}")
    private String SENDER_EMAIL;

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper =
                    new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            messageHelper.setFrom(SENDER_EMAIL);

            final String templateName = PAYMENT_CONFIRMATION.getTemplate();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            Context context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to {} with the template {}", destinationEmail, templateName);
        } catch (MessagingException exp) {
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
            log.error("EXCEPTION - {}", exp.getMessage());
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper =
                    new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            messageHelper.setFrom(SENDER_EMAIL);

            final String templateName = ORDER_CONFIRMATION.getTemplate();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("totalAmount", amount);
            variables.put("orderReference", orderReference);
            variables.put("Products", products);

            Context context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to {} with the template {}", destinationEmail, templateName);
        } catch (MessagingException exp) {
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
            log.error("EXCEPTION - {}", exp.getMessage());
        }
    }
}

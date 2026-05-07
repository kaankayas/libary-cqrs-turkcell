package com.turkcell.libary_cqrs.core.mediator.pipeline.behaviors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import com.turkcell.libary_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.libary_cqrs.core.mediator.pipeline.RequestHandlerDelegate;


@Component
@Order(2)
public class LoggingBehavior implements PipelineBehavior {

    private static final Logger log = LoggerFactory.getLogger(LoggingBehavior.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();

        try {
            String requestBody = objectMapper.writeValueAsString(request);
            log.info("[REQUEST ] {} -> {}", requestName, requestBody);
        } catch (Exception e) {
            log.info("[REQUEST ] {} -> (içerik serileştirilemedi)", requestName);
        }

        R response;
        try {
            response = next.invoke();
        } catch (Exception ex) {
            log.error("[RESPONSE] {} -> HATA: {} - {}", requestName, ex.getClass().getSimpleName(), ex.getMessage());
            throw ex;
        }

        if (request instanceof Command<?>) {
            try {
                String responseBody = objectMapper.writeValueAsString(response);
                log.info("[RESPONSE] {} -> {}", requestName, responseBody);
            } catch (Exception e) {
                log.info("[RESPONSE] {} -> (cevap serileştirilemedi)", requestName);
            }
        }

        return response;
    }
}

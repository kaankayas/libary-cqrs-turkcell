package com.turkcell.libary_cqrs.core.mediator.pipeline.behaviors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.libary_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.libary_cqrs.core.mediator.pipeline.RequestHandlerDelegate;


@Component
@Order(1)
public class PerformanceMonitoringBehavior implements PipelineBehavior {

    private static final Logger log = LoggerFactory.getLogger(PerformanceMonitoringBehavior.class);

    private static final long THRESHOLD_MS = 3000L;

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        long startTime = System.currentTimeMillis();

        try {
            return next.invoke();
        } finally {
            long elapsed = System.currentTimeMillis() - startTime;
            String requestName = request.getClass().getSimpleName();

            if (elapsed > THRESHOLD_MS) {
                log.warn("[PERFORMANCE] '{}' isteği yavaş tamamlandı! Süre: {} ms (Eşik: {} ms)",
                        requestName, elapsed, THRESHOLD_MS);
            } else {
                log.debug("[PERFORMANCE] '{}' tamamlandı. Süre: {} ms", requestName, elapsed);
            }
        }
    }
}

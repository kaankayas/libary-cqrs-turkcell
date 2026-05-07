package com.turkcell.libary_cqrs.core.mediator.pipeline.behaviors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import com.turkcell.libary_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.libary_cqrs.core.mediator.pipeline.RequestHandlerDelegate;


@Component
@Order(3)
public class TransactionBehavior implements PipelineBehavior {

    private static final Logger log = LoggerFactory.getLogger(TransactionBehavior.class);

    private final PlatformTransactionManager transactionManager;

    public TransactionBehavior(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean supports(Object request) {
        return request instanceof Command<?>;
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        log.debug("[TRANSACTION] '{}' için transaction başlatıldı.", requestName);

        try {
            R result = next.invoke();
            transactionManager.commit(status);
            log.debug("[TRANSACTION] '{}' transaction başarıyla commit edildi.", requestName);
            return result;
        } catch (Exception ex) {
            transactionManager.rollback(status);
            log.error("[TRANSACTION] '{}' transaction ROLLBACK yapıldı. Sebep: {} — {}",
                    requestName, ex.getClass().getSimpleName(), ex.getMessage());
            throw ex;
        }
    }
}

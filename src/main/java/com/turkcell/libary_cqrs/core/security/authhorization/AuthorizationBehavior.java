package com.turkcell.libary_cqrs.core.security.authhorization;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.libary_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.libary_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(0)
public class AuthorizationBehavior implements PipelineBehavior {

    // ilgili handler'ın öncesi ve sonrası çalıştırabilen kodlar.
    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        // jwt kontrolü..
        System.out.println("Merhaba ben AuthorizationBehavior");
        return next.invoke(); // zincirdeki sonraki halkayı çağır..
    }

}

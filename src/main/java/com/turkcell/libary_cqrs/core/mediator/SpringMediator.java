package com.turkcell.libary_cqrs.core.mediator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;

@Component
public class SpringMediator implements Mediator {

    private final Map<Class<?>, CommandHandler<?, ?>> commandHandlers = new HashMap<>();
    private final Map<Class<?>, QueryHandler<?, ?>> queryHandlers = new HashMap<>();

    public SpringMediator(List<CommandHandler<?, ?>> cmdHandlers, List<QueryHandler<?, ?>> qryHandlers) {
        
        cmdHandlers.forEach(handler -> {
            Class<?> commandType = ResolvableType.forClass(handler.getClass())
                    .as(CommandHandler.class).getGeneric(0).resolve();
            commandHandlers.put(commandType, handler);
        });

        qryHandlers.forEach(handler -> {
            Class<?> queryType = ResolvableType.forClass(handler.getClass())
                    .as(QueryHandler.class).getGeneric(0).resolve();
            queryHandlers.put(queryType, handler);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R send(Command<R> command) {
        CommandHandler<Command<R>, R> handler = (CommandHandler<Command<R>, R>) commandHandlers.get(command.getClass());
        
        if (handler == null) {
            throw new IllegalStateException("Command handler bulunamadı: " + command.getClass().getSimpleName());
        }
        
        return handler.handle(command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R send(Query<R> query) {
        QueryHandler<Query<R>, R> handler = (QueryHandler<Query<R>, R>) queryHandlers.get(query.getClass());
        
        if (handler == null) {
            throw new IllegalStateException("Query handler bulunamadı: " + query.getClass().getSimpleName());
        }
        
        return handler.handle(query);
    }
}
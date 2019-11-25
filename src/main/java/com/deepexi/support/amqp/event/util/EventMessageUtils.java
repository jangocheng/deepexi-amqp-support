package com.deepexi.support.amqp.event.util;

import com.deepexi.support.amqp.event.EventMessage;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.function.Function;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-11-25
 */
public abstract class EventMessageUtils {
    public static String getEventCode(Class<?> clazz) {
        return computeIfAnnotationPresent(clazz, EventMessage::code);
    }

    public static String getEventExchange(Class<?> clazz) {
        return computeIfAnnotationPresent(clazz, EventMessage::exchange);
    }

    public static EventMessage findAnnotation(Class<?> clazz) {
        return AnnotationUtils.findAnnotation(clazz, EventMessage.class);
    }

    private static <R> R computeIfAnnotationPresent(Class<?> clazz, Function<EventMessage, R> func) {
        EventMessage annotation = findAnnotation(clazz);
        if (annotation == null) {
            return null;
        }
        return func.apply(annotation);
    }
}
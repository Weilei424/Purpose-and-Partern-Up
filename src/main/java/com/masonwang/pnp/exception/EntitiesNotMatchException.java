package com.masonwang.pnp.exception;

public class EntitiesNotMatchException extends RuntimeException {

    public EntitiesNotMatchException(Long id1, Class<?> entity1, Long id2, Class<?> entity2) {
        super("The " + entity1.getSimpleName().toLowerCase() + " with id '" + id1 + "' does not exist in "
                + entity2.getSimpleName().toLowerCase() + " with id '" + id2);
    }
}

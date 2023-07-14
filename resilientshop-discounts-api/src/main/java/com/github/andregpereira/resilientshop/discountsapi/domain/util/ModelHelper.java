package com.github.andregpereira.resilientshop.discountsapi.domain.util;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public interface ModelHelper<T> {

    @SuppressWarnings("unchecked")
    default T map(UnaryOperator<T> mapper) {
        return mapper.apply((T) this);
    }

    @SuppressWarnings("unchecked")
    default void map(Consumer<T> action) {
        action.accept((T) this);
    }

}

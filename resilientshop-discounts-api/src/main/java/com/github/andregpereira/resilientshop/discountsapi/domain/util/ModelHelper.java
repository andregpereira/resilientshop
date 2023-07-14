package com.github.andregpereira.resilientshop.discountsapi.domain.util;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ModelHelper<T> {

    @SuppressWarnings("unchecked")
    default <U> U map(Function<? super T, ? extends U> mapper) {
        return mapper.apply((T) this);
    }

    @SuppressWarnings("unchecked")
    default void map(Consumer<T> action) {
        action.accept((T) this);
    }

}

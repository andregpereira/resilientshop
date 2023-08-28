package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.factory;

import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface UseCaseFactory {

    <T extends UseCase> T getUseCase(Class<T> type);

}

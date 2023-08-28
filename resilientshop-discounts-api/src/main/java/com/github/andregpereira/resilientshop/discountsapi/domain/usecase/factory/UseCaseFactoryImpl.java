package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.factory;

import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Slf4j
@Component
class UseCaseFactoryImpl implements UseCaseFactory {

    private final Map<String, UseCase> map;

    public UseCaseFactoryImpl(final Collection<UseCase> useCases) {
        this.map = useCases.stream().collect(
                Collectors.toMap(u -> u.getClass().getSimpleName().replace("Impl", ""), Function.identity()));
    }

    @Override
    public <T extends UseCase> T getUseCase(Class<T> type) {
        String nome = type.getSimpleName();
        log.info("Retornando Bean {}", nome);
        //noinspection unchecked
        return (T) map.get(nome);
    }

}

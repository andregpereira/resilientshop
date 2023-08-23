package com.github.andregpereira.resilientshop.discountsapi.cross.factory;

import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
class UseCaseFactoryImpl implements UseCaseFactory {

    private final BeanFactory beanFactory;

    @Override
    public <T extends UseCase> T getUseCase(Class<T> type) {
        log.info("Retornando bean {}", type.getSimpleName());
        return beanFactory.getBean(type);
    }

}

package com.github.andregpereira.resilientshop.discountsapi.app.service.factory;

import com.github.andregpereira.resilientshop.discountsapi.app.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
class ServiceFactoryImpl implements ServiceFactory {

    private final Map<String, Service> serviceMap;

    public ServiceFactoryImpl(final Collection<Service> services) {
        System.out.print("\n\n\n" + services + "\n\n\n");
        this.serviceMap = services.stream().collect(
                Collectors.toMap(u -> u.getClass().getSimpleName().replace("Impl", ""), Function.identity()));
    }

    @Override
    public <S extends Service> S getService(Class<S> type) {
        String nome = type.getSimpleName();
        log.info("Retornando Bean {}", nome);
        //noinspection unchecked
        return (S) serviceMap.get(nome);
    }

}

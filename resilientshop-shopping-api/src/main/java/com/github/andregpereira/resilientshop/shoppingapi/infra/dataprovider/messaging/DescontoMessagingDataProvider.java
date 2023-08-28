package com.github.andregpereira.resilientshop.shoppingapi.infra.dataprovider.messaging;

import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.messaging.DescontoMessagingGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamOperations;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class DescontoMessagingDataProvider implements DescontoMessagingGateway {

    private final StreamOperations streamOperations;

    @Override
    public void send(Pedido pedido) {
        streamOperations.send("processDiscount-out-0", MessageBuilder.withPayload(pedido).build());
    }

}

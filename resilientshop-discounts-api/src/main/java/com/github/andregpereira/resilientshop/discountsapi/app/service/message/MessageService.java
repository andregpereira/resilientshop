package com.github.andregpereira.resilientshop.discountsapi.app.service.message;

import com.github.andregpereira.resilientshop.commons.dto.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.ProcessarDescontoUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessageService {

//    private final ProcessarDescontoUc processarDescontoUc;

    @Bean
    public Function<Message<PedidoDetalharDto>, Message<PedidoDetalharDto>> processDiscount() {
        return message -> {
            log.info("Pedido recebido: {}", message.getPayload());
            log.info("Processando pedido...");
//            processarDescontoUc.processDiscount();
            log.info("Enviando pedido processado: {}", message.getPayload());
            return MessageBuilder.fromMessage(message).build();
        };
    }

}

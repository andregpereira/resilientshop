package com.github.andregpereira.resilientshop.shoppingapi.app.service.message;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessageService {

    @Bean
    public Consumer<Message<PedidoDto>> receber() {
        return message -> log.info("Received message " + message.getPayload());
    }

}

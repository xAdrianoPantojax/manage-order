package com.ambev.manager_order.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomMessageConverter implements MessageConverter {
    
private final ObjectMapper objectMapper = new ObjectMapper(); // Usando o Jackson ObjectMapper
    private final SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        if (object instanceof OrderRequestDTO) {
            try {
                // Serialização personalizada do OrderRequestDTO para byte[]
                byte[] body = serializeOrderRequest((OrderRequestDTO) object);
                return MessageBuilder.withBody(body).andProperties(messageProperties).build();
            } catch (Exception e) {
                throw new MessageConversionException("Erro ao serializar OrderRequestDTO", e);
            }
        }
        return simpleMessageConverter.toMessage(object, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        // Lógica de desserialização personalizada
        byte[] body = message.getBody();
        try {
            return deserializeOrderRequest(body);
        } catch (Exception e) {
            throw new MessageConversionException("Erro ao desserializar OrderRequestDTO", e);
        }
    }

    private byte[] serializeOrderRequest(OrderRequestDTO orderRequestDTO) throws Exception {
        // Serializando OrderRequestDTO para byte[] usando Jackson
        return objectMapper.writeValueAsBytes(orderRequestDTO);
    }

    private OrderRequestDTO deserializeOrderRequest(byte[] body) throws Exception {
        // Desserializando byte[] de volta para OrderRequestDTO usando Jackson
        return objectMapper.readValue(body, OrderRequestDTO.class);
    }
}
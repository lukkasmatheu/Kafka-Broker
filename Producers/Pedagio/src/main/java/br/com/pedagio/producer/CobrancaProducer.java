package br.com.pedagio.producer;

import br.com.pedagio.model.Cobranca;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
@RequiredArgsConstructor
public class CobrancaProducer {
    @Value("TOPIC_COBRANCA_VEICULO")
    private String topicCobranca;

    private final KafkaTemplate<String, Serializable> jsonKafkaTemplate;

    public String enviar(Cobranca cobranca) {
        try {
            log.info("Enviando cobranca para topico.");
            jsonKafkaTemplate.send(topicCobranca, cobranca);
            return cobranca.getCarPlate();
        } catch (Exception exception) {
           log.error("[Producer] - Nao foi possivel enviar cobranca para o topico");
        }
        return "";
    }

}

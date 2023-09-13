package br.com.drive.thru.producer;

import br.com.drive.thru.model.NotaFiscal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaFiscalProducer {
    @Value("TOPIC_NOTA_FISCAL_ESTABELECIMENTO")
    private String topicNFEstabelecimento;

    private final KafkaTemplate<String, Serializable> jsonKafkaTemplate;

    public void enviar(NotaFiscal notaFiscal) {
        try {
            log.info("Reqpuisição de geração de NF para cliente.");
            jsonKafkaTemplate.send(topicNFEstabelecimento, notaFiscal);
        } catch (Exception exception) {
           log.error("[Producer] - Nao foi possivel enviar cobranca para o topico");
        }
    }

}

package br.com.billing.consumer;

import br.com.billing.custom.JsonKafkaListener;
import br.com.billing.model.Cobranca;
import br.com.billing.model.NotaFiscal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CobrancaConsumer {


    @JsonKafkaListener(id = "solicita_cobranca_veiculo", topics = "TOPIC_COBRANCA_VEICULO")
    public void consumirCobranca(Cobranca cobranca, ConsumerRecordMetadata metadata) {
        try {
            log.info("Objeto cobranca recebido: {}", cobranca);

        } catch (Exception exception) {
            log.error("[Error]- Error ao realizar o recebimento ");
        }
    }

    @JsonKafkaListener(id = "solicita_geracao_boleto", topics = "TOPIC_NOTA_FISCAL_ESTABELECIMENTO")
    public void consumirNotaFiscal(NotaFiscal notaFiscal, ConsumerRecordMetadata metadata) {
        try {
            log.info("Objeto nota fiscal recebida: {}", notaFiscal);

        } catch (Exception exception) {
            log.error("[Error]- Error ao realizar o recebimento ");
        }
    }

}

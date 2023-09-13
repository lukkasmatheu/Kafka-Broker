package br.com.billing.props;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.StringJoiner;

@Slf4j
public class ConsumerDefinition {

    public static final String ORDEM_COBRANCA_CANCELADA = "processarOrdemCobrancaCancelada";
    public static final String ORDEM_COBRANCA_PROCESSADA = "processarOrdemCobrancaProcessada";
    public static final String ORDEM_COBRANCA_NAO_PROCESSADA = "processarOrdemCobrancaNaoProcessada";
    public static final String ORDEM_COBRANCA_LIQUIDADA = "processarOrdemCobrancaLiquidada";
    public static final String ORDEM_COBRANCA_REJEITADA = "processarOrdemCobrancaRejeitada";
    public static final String ORDEM_FATURAMENTO = "processarOrdemFaturamento";
    public static final String ALTERACAO_FATURA = " processarPedidoAlteracaoFatura";
    public static final String CANCELAMENTO_FATURA = "processarPedidoCancelamentoFatura";
    public static final String LIQUIDACAO_FATURA = "processarSolicitacaoLiquidacaoSap-";
    public static final String CLIENTE_SAIDA = "processarSincronizaClienteSaida";
    public static final String BOLETO_AGLUTINADO = "processarSolicitacaoBoletoAglutinadoSap";
    public static final String BOLETO_AVULSO = "processarSolicitacaoBoletoAvulsoSap";
    public static final String SALDO_CREDOR_DEVOLUCAO = "processarSaldoCredorDevolucao";

    private ConsumerDefinition() { }

    public static String getDefinition() {
        try {
            StringJoiner joiner = new StringJoiner(";");
            Field[] interfaceFields = ConsumerDefinition.class.getFields();
            for (Field f : interfaceFields) {
                joiner.add((String) f.get(f));
            }
            return joiner.toString();
        } catch (IllegalAccessException e) {
            log.error("Problem get kafka consumer definition", e);
            throw new RuntimeException(e);
        }
    }
}

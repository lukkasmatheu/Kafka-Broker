package br.com.billing.props;

public final class KafkaProperties {

    public static final String SCHEMA_REGISTRY_URL = "schema.registry.url";
    public static final String SCHEMA_REGISTRY_USER_INFO = "schema.registry.basic.auth.user.info";
    public static final String AUTH_CREDENTIALS_SOURCE = "basic.auth.credentials.source";
    public static final String AVRO_READER = "specific.avro.reader";
    public static final String ENABLE_AUTO_COMMIT = "enable.auto.commit";
    public static final String AUTO_COMMIT_INTERVAL = "auto.commit.interval.ms";
    public static final String MAX_POLL_RECORDS = "max.poll.records";
    public static final String  SASL_JAAS_CONFIG = "sasl.jaas.config";
    public static final String SASL_MECHANISM = "sasl.mechanism";
    public static final String SECURITY_PROTOCOL = "security.protocol";
    public static final String JAAS_TEMPLATE = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";

    private KafkaProperties() { }
}

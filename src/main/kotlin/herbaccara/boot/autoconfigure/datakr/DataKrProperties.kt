package herbaccara.boot.autoconfigure.datakr

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "datakr")
@ConstructorBinding
data class DataKrProperties(
    val enabled: Boolean = true,
    val rootUri: String = "https://apis.data.go.kr",
    val failOnUnknownProperties: Boolean = false,
    val serviceKey: String
)

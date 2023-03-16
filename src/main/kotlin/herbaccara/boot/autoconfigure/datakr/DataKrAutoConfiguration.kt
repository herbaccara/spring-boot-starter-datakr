package herbaccara.boot.autoconfigure.datakr

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import herbaccara.datakr.DataKrService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.StringHttpMessageConverter
import java.nio.charset.StandardCharsets
import java.util.*

@AutoConfiguration
@EnableConfigurationProperties(DataKrProperties::class)
@ConditionalOnProperty(prefix = "datakr", value = ["enabled"], havingValue = "true", matchIfMissing = true)
class DataKrAutoConfiguration {

    @Bean
    fun dataKrService(properties: DataKrProperties): DataKrService {
        if (properties.serviceKey.isEmpty()) throw NullPointerException()

        val xmlMapper = XmlMapper().apply {
            findAndRegisterModules()
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, properties.failOnUnknownProperties)
        }

        val restTemplate = RestTemplateBuilder()
            .rootUri(properties.rootUri)
            .messageConverters(
                StringHttpMessageConverter(StandardCharsets.UTF_8)
            )
            .build()

        return DataKrService(restTemplate, xmlMapper, properties)
    }
}

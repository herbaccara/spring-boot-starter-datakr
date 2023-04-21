package herbaccara.boot.autoconfigure.datakr

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import herbaccara.datakr.DataKrService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
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

        val objectMapper = jacksonObjectMapper().apply {
            findAndRegisterModules()
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, properties.failOnUnknownProperties)
            configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
        }

        val restTemplate = RestTemplateBuilder()
            .rootUri(properties.rootUri)
            .messageConverters(
                StringHttpMessageConverter(StandardCharsets.UTF_8),
                MappingJackson2HttpMessageConverter(objectMapper)
            )
            .build()

        return DataKrService(restTemplate, objectMapper, xmlMapper, properties)
    }
}

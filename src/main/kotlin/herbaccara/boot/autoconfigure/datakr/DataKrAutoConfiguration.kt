package herbaccara.boot.autoconfigure.datakr

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import herbaccara.datakr.DataKrService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets
import java.util.*

@AutoConfiguration
@EnableConfigurationProperties(DataKrProperties::class)
@ConditionalOnProperty(prefix = "datakr", value = ["enabled"], havingValue = "true")
class DataKrAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            findAndRegisterModules()
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun xmlMapper(): XmlMapper {
        return XmlMapper().apply {
            findAndRegisterModules()
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Bean
    fun dataKrService(
        objectMapper: ObjectMapper,
        xmlMapper: XmlMapper,
        properties: DataKrProperties
    ): DataKrService {
        if (properties.serviceKey.isEmpty()) throw NullPointerException()

        val restTemplate = RestTemplateBuilder()
            .rootUri(properties.rootUri)
            .messageConverters(
                StringHttpMessageConverter(StandardCharsets.UTF_8)
            )
            .build()

        return DataKrService(restTemplate, xmlMapper, objectMapper, properties)
    }
}

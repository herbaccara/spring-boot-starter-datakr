package herbaccara.datakr

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import herbaccara.boot.autoconfigure.datakr.DataKrAutoConfiguration
import herbaccara.datakr.model.Hopital
import herbaccara.datakr.model.Result
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(
    classes = [
        DataKrAutoConfiguration::class
    ]
)
@TestPropertySource(locations = ["classpath:application.yml"])
class DataKrServiceTest {

    @Autowired
    lateinit var dataKrService: DataKrService

    @Test
    fun xmlMapper() {
        val xml = DataKrServiceTest::class.java.getResourceAsStream("/test.xml")!!.reader().readText()

        val xmlMapper = XmlMapper().apply {
            findAndRegisterModules()
        }
        val readValue = xmlMapper.readValue(xml, object : TypeReference<Result<Hopital>>() {})
        println()
    }

    @Test
    fun getRestDeInfo() {
        val restDeInfo = dataKrService.getRestDeInfo(2023, 3)
        println(restDeInfo)
    }

    @Test
    fun getRestDeInfo2() {
        val restDeInfo = dataKrService.getRestDeInfo(2023)
        println(restDeInfo)
    }

    @Test
    fun getHsptlMdcncFullDown() {
        val hsptlMdcncFullDown = dataKrService.getHsptlMdcncFullDown(1, 1000)
        println(hsptlMdcncFullDown)
    }
}

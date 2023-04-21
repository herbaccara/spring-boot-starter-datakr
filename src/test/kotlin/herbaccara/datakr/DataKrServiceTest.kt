package herbaccara.datakr

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import herbaccara.boot.autoconfigure.datakr.DataKrAutoConfiguration
import herbaccara.datakr.model.Hopital
import herbaccara.datakr.model.Result
import herbaccara.datakr.model.StanReginCd
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import java.io.File
import java.time.LocalDate

@SpringBootTest(
    classes = [
        DataKrAutoConfiguration::class
    ]
)
@TestPropertySource(locations = ["classpath:application.yml"])
class DataKrServiceTest {

    @Autowired
    lateinit var dataKrService: DataKrService

    private val objectMapper = jacksonObjectMapper().apply {
        findAndRegisterModules()
    }

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

    @Test
    fun getHsptlMdcncFullDowns() {
        val hsptlMdcncFullDowns = dataKrService.getHsptlMdcncFullDown(5_000)
        println()
    }

    @Test
    fun getStanReginCdList() {
        val stanReginCdList = dataKrService.getStanReginCdList(1, 1000)
        println()
    }

    @Test
    fun downloadGetStanReginCdList() {
        (1..21).forEach {
            try {
                val stanReginCdList = dataKrService.getStanReginCdList(it, 1000)
                val json = objectMapper.writeValueAsString(stanReginCdList)
                val file = File("src/test/resources/StanReginCd/$it.json")
                file.writeText(json)
            } catch (e: Exception) {
                println("error page $it")
            }
        }
    }

    @Test
    fun getStanReginCdListFull() {
        val stanReginCdList = dataKrService.getStanReginCdList(1000)
        println()
    }

    @Test
    fun getStanReginCdListFromJson() {
        val files = File("src/test/resources/StanReginCd").listFiles() ?: emptyArray()
        val rows = files.flatMap {
            val stanReginCd = objectMapper.readValue<StanReginCd>(it.readText())
            stanReginCd.row
        }
        val level1 = rows.filter { it.sggCd == "000" }.sortedBy { it.sidoCd } // 특별시, 광역시, 도
        val level2 = rows.filter { it.sggCd != "000" && it.umdCd == "000" }
            .sortedWith(compareBy({ it.sidoCd }, { it.locatOrder }))
        println()
    }

    @Test
    fun businessNoValidate() {
        val result = dataKrService.businessNoValidate(
            "120-81-47521",
            LocalDate.of(1995, 2, 16),
            "남궁훈"
        )

        println()
    }

    @Test
    fun businessNoStatus() {
        val result = dataKrService.businessNoStatus("220-81-62517")
        println()
    }
}

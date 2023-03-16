package herbaccara.datakr

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import herbaccara.boot.autoconfigure.datakr.DataKrProperties
import herbaccara.datakr.model.HolidayResult
import herbaccara.datakr.model.HopitalResult
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URLEncoder

class DataKrService(
    private val restTemplate: RestTemplate,
    private val xmlMapper: XmlMapper,
    private val properties: DataKrProperties
) {
    private fun <T> getForObject(uri: String, params: Map<String, Any>, clazz: Class<T>): T {
        val endpoint = UriComponentsBuilder
            .fromHttpUrl("${properties.rootUri}$uri")
            .queryParams(
                LinkedMultiValueMap<String, String>().apply {
                    for ((k, v) in params) {
                        add(k, v.toString())
                    }
                }
            )
            .toUriString() + "&ServiceKey=" + URLEncoder.encode(properties.serviceKey, "UTF-8")

        val body: String = restTemplate.getForObject(URI(endpoint))

        return xmlMapper.readValue(body, clazz)
    }

    /***
     * 국립중앙의료원_국립중앙의료원_전국 병·의원 찾기 서비스 - 병/의원 FullData 내려받기
     * https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15000736
     */
    fun getHsptlMdcncFullDown(pageNo: Int, numOfRows: Int): HopitalResult {
        val uri = "/B552657/HsptlAsembySearchService/getHsptlMdcncFullDown"

        return getForObject(
            uri,
            mapOf(
                "pageNo" to pageNo,
                "numOfRows" to numOfRows
            ),
            HopitalResult::class.java
        )
    }

    /***
     * @param numOfRows 1000 ~ 5000 개 정도가 적당하다.
     */
    fun getHsptlMdcncFullDown(numOfRows: Int, block: (HopitalResult) -> Unit) {
        val totalCount = getHsptlMdcncFullDown(1, 1).body.totalCount

        var totalPage = totalCount / numOfRows
        if (totalCount % numOfRows > 0) {
            totalPage++
        }

        (1..totalPage)
            .forEach { pageNo: Int -> block(getHsptlMdcncFullDown(pageNo, numOfRows)) }
    }

    fun getHsptlMdcncFullDown(numOfRows: Int): List<HopitalResult> {
        val list = mutableListOf<HopitalResult>()
        getHsptlMdcncFullDown(numOfRows, list::add)
        return list
    }

    /***
     * 한국천문연구원_특일 정보 - 공휴일 정보 조회
     * https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15012690
     */
    fun getRestDeInfo(solYear: Int, solMonth: Int): HolidayResult {
        val uri = "/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"

        return getForObject(
            uri,
            mapOf(
                "solYear" to solYear,
                "solMonth" to solMonth.toString().padStart(2, '0')
            ),
            HolidayResult::class.java
        )
    }

    fun getRestDeInfo(solYear: Int): List<HolidayResult> {
        return (1..12)
            .map { getRestDeInfo(solYear, it) }
    }
}

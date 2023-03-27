package herbaccara.datakr.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "StanReginCd")
data class StanReginCd(
    val head: Head,
    @field:JacksonXmlElementWrapper(useWrapping = false) val row: List<Row>
) {
    data class Head(
        val totalCount: Int,
        val numOfRows: Int,
        val pageNo: Int,
        val type: String,
        @field:JsonProperty("RESULT") val result: Result
    ) {
        data class Result(val resultCode: String, val resultMsg: String)
    }

    data class Row(
        /***
         * 지역코드
         */
        @field:JsonProperty("region_cd") val regionCd: String,

        /***
         * 시도코드
         */
        @field:JsonProperty("sido_cd") val sidoCd: String,

        /***
         * 시군구코드
         */
        @field:JsonProperty("sgg_cd") val sggCd: String,

        /***
         * 읍면동코드
         */
        @field:JsonProperty("umd_cd") val umdCd: String,

        /***
         * 리코드
         */
        @field:JsonProperty("ri_cd") val riCd: String,

        /***
         * 지역코드_주민
         */
        @field:JsonProperty("locatjumin_cd") val locatjuminCd: String,

        /***
         * 지역코드_지적
         */
        @field:JsonProperty("locatjijuk_cd") val locatjijukCd: String,

        /***
         * 지역주소명
         */
        @field:JsonProperty("locatadd_nm") val locataddNm: String,

        /***
         * 서열
         */
        @field:JsonProperty("locat_order") val locatOrder: Int,

        /***
         * 지역주소명
         */
        @field:JsonProperty("locat_rm") val locatRm: String,

        /***
         * 상위지역코드
         */
        @field:JsonProperty("locathigh_cd") val locathighCd: String,

        /***
         * 최하위지역명
         */
        @field:JsonProperty("locallow_nm") val locallowNm: String,

        /***
         * 생성일
         */
        @field:JsonProperty("adpt_de") val adptDe: String
    )
}

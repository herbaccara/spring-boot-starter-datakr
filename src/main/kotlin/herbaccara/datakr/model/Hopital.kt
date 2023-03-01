package herbaccara.datakr.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import herbaccara.datakr.model.Hopital.Div
import herbaccara.datakr.model.Hopital.Emcls

data class Hopital(
    /***
     * 일련번호
     */
    val rnum: Int,

    /***
     * 주소
     */
    val dutyAddr: String,

    /***
     * 병원분류 [Div]
     */
    val dutyDiv: String,

    /***
     * 병원분류명
     */
    val dutyDivNam: String,

    /***
     * 응급의료기관코드. [Emcls]
     */
    val dutyEmcls: String,

    /***
     * 응급의료기관코드명
     */
    val dutyEmclsName: String,

    /***
     * 응급실운영여부(1/2). 1 : 운영, 2 : 미운영.
     * 운영이라면 dutyTel3(응급실전화) 에 값이 있다.
     */
    @field:JsonDeserialize(using = BooleanDeserializer::class)
    val dutyEryn: Boolean,

    /***
     * 비고
     */
    val dutyEtc: String?,

    /***
     * 간이약도
     */
    val dutyMapimg: String?,

    /***
     * 기관명
     */
    val dutyName: String,

    /***
     * 대표전화1
     */
    val dutyTel1: String,

    /***
     * 응급실전화
     */
    val dutyTel3: String?,

    // 월
    val dutyTime1c: String?,
    val dutyTime1s: String?,

    // 화
    val dutyTime2c: String?,
    val dutyTime2s: String?,

    // 수
    val dutyTime3c: String?,
    val dutyTime3s: String?,

    // 목
    val dutyTime4c: String?,
    val dutyTime4s: String?,

    // 금
    val dutyTime5c: String?,
    val dutyTime5s: String?,

    // 토
    val dutyTime6c: String?,
    val dutyTime6s: String?,

    // 일
    val dutyTime7c: String?,
    val dutyTime7s: String?,

    // 공휴일
    val dutyTime8c: String?,
    val dutyTime8s: String?,

    /***
     * 기관ID : 동일테이블에 기관명을 포함하고 있음
     */
    val hpid: String,

    /***
     * 우편번호1
     */
    val postCdn1: Int,

    /***
     * 우편번호2
     */
    val postCdn2: Int,

    /***
     * 병원위도
     */
    val wgs84Lat: Double,

    /***
     * 병원경도
     */
    val wgs84Lon: Double,

    /***
     * 기관설명상세
     */
    val dutyInf: String?
) {

    /***
     * 병원 분류
     */
    enum class Div(val text: String) {
        A("종합병원"),
        B("병원"),
        C("의원"),
        D("요양병원"),
        E("한방병원"),
        G("한의원"),
        I("기타"),
        M("치과병원"),
        N("치과의원"),
        R("보건소"),
        W("기타(구급차)");
    }

    /***
     * 응급의료기관코드
     */
    enum class Emcls(val text: String) {
        G001("권역응급의료센터"),
        G006("지역응급의료센터"),
        G007("지역응급의료기관"),
        G009("응급실운영신고기관"),
        G099("응급의료기관 이외");
    }

    internal class BooleanDeserializer : StdDeserializer<Boolean?>(Boolean::class.java) {

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Boolean? {
            val text = p.text
            return if (text != null) {
                text.toInt() == 1
            } else {
                null
            }
        }
    }
}

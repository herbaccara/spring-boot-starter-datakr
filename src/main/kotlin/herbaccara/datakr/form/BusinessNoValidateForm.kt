package herbaccara.datakr.form

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class BusinessNoValidateForm @JvmOverloads constructor(
    /**
     * 사업자등록번호
     */
    @field:JsonProperty("b_no")
    val bNo: String,

    /**
     * 개업일자
     */
    @field:JsonProperty("start_dt")
    @field:JsonFormat(pattern = "yyyyMMdd")
    val startDt: LocalDate,

    /**
     * 대표자성명
     */
    @field:JsonProperty("p_nm")
    val pNm: String,

    /**
     * 대표자성명2
     */
    @field:JsonProperty("p_nm2")
    val pNm2: String = "",

    /**
     * 상호
     */
    @field:JsonProperty("b_nm")
    val bNm: String = "",

    /**
     * 법인등록번호
     */
    @field:JsonProperty("corp_no")
    val corpNo: String = "",

    /**
     * 주업태명
     */
    @field:JsonProperty("b_sector")
    val bSector: String = "",

    /**
     * 주종목명
     */
    @field:JsonProperty("b_type")
    val bType: String = ""
)

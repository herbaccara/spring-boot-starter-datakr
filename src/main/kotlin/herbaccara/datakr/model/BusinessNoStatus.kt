package herbaccara.datakr.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BusinessNoStatus(
    @field:JsonProperty("b_no") val bNo: String,
    @field:JsonProperty("b_stt") val bStt: String,
    @field:JsonProperty("b_stt_cd") val bSttCd: String,
    @field:JsonProperty("tax_type") val taxType: String,
    @field:JsonProperty("tax_type_cd") val taxTypeCd: String,
    @field:JsonProperty("end_dt") val endDt: String,
    @field:JsonProperty("utcc_yn") val utccYn: String,
    @field:JsonProperty("tax_type_change_dt") val taxTypeChangeDt: String,
    @field:JsonProperty("invoice_apply_dt") val invoiceApplyDt: String
)

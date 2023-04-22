package herbaccara.datakr.model

import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.datakr.form.BusinessNoValidateForm

data class BusinessNoValidateResult(
    @field:JsonProperty("request_cnt") val requestCnt: Int,
    @field:JsonProperty("valid_cnt") val validCnt: Int,
    @field:JsonProperty("status_code") val statusCode: String,
    @field:JsonProperty("data") val data: List<Data>
) {
    data class Data(
        @field:JsonProperty("b_no") val bNo: String,
        @field:JsonProperty("valid") val valid: String,
        @field:JsonProperty("request_param") val requestParam: BusinessNoValidateForm,
        @field:JsonProperty("status") val status: BusinessNoStatus?
    )
}

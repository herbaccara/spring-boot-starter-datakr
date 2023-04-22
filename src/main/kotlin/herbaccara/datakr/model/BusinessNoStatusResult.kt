package herbaccara.datakr.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BusinessNoStatusResult(
    @field:JsonProperty("request_cnt") val requestCnt: Int,
    @field:JsonProperty("match_cnt") val matchCnt: Int,
    @field:JsonProperty("status_code") val statusCode: String,
    @field:JsonProperty("data") val data: List<BusinessNoStatus>
)

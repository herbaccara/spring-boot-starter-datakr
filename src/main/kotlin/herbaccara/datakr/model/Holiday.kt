package herbaccara.datakr.model

data class Holiday(
    /**
     * dateKind
     */
    val dateKind: Int,

    /**
     * 명칭
     */
    val dateName: String,

    /**
     * 공공기관 휴일여부
     */
    val isHoliday: String,

    /**
     * 날짜 (yyyyMMdd)
     */
    val locdate: Int,

    /**
     * 순번
     */
    val seq: Int
)

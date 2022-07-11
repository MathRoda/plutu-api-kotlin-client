package com.mathroda.plutuApiClient.entity.adfali

data class AdfaliResult(
    val process_id: Long,
    val transaction_id: Long? = null,
    val amount: Long? = null

)
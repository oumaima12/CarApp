package com.example.carapp.data.model

import com.google.gson.annotations.SerializedName

data class MakeResponse(
    @SerializedName("Makes") val makes: List<CarMake>
)

data class CarMake(
    @SerializedName("make_id") val makeId: String,
    @SerializedName("make_display") val makeDisplay: String,
    @SerializedName("make_is_common") val makeIsCommon: String,
    @SerializedName("make_country") val makeCountry: String
)



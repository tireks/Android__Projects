package com.tirex_projs.testfocusstart.model

object CardModel {
    data class Card(
        val scheme: String,
        val brand: String,
        val type: String,
        val prepaid: String,
        val number: Number,
        val bank: Bank,
        val country: Country
    )
    data class Number(
        val length: String,
        val luhn: String
    )

    data class Bank(
        val name: String,
        val city: String,
        val url: String,
        val phone: String
    )

    data class Country(
        val name: String,
        val currency: String,
        val latitude: String,
        val longitude: String
    )
}
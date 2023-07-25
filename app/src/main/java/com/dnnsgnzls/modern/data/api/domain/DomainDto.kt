package com.dnnsgnzls.modern.data.api.domain

enum class DomainDto(val value: String) {
    Steam("store.steampowered.com"),
    PlayStationStore("store.playstation.com"),
    XboxStore("microsoft.com"),
    AppStore("apps.apple.com"),
    GOG("gog.com"),
    NintendoStore("nintendo.com"),
    Xbox360Store("marketplace.xbox.com"),
    GooglePlay("play.google.com"),
    Itch("itch.io"),
    EpicGames("epicgames.com");

    companion object {
        fun fromValue(value: String): DomainDto = when (value) {
            "store.steampowered.com" -> Steam
            "store.playstation.com" -> PlayStationStore
            "microsoft.com" -> XboxStore
            "apps.apple.com" -> AppStore
            "gog.com" -> GOG
            "nintendo.com" -> NintendoStore
            "marketplace.xbox.com" -> Xbox360Store
            "play.google.com" -> GooglePlay
            "itch.io" -> Itch
            "epicgames.com" -> EpicGames
            else -> throw IllegalArgumentException("Unknown domain: $value")
        }
    }
}
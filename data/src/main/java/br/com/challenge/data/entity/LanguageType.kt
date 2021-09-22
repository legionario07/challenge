package br.com.challenge.data.entity

const val TAG = "language:"

enum class LanguageType(var value: String) {
    KOTLIN("${TAG}kotlin"),
    JAVA("${TAG}java"),
}
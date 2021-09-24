package br.com.challenge

import okhttp3.mockwebserver.MockWebServer

class MockServer {
    companion object {
        val server = MockWebServer()
    }
}
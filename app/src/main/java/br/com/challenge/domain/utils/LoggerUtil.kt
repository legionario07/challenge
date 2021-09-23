package br.com.challenge.domain.utils

import android.util.Log
import androidx.annotation.VisibleForTesting

class LoggerUtil {

    companion object {
        private var disabledForTesting = false
        const val DEFAULT_LOG_TAG = "CHALLENGE"

        @VisibleForTesting
        @JvmStatic
        fun disableLogForTesting() {
            disabledForTesting = true
        }

        @JvmStatic
        fun logError(tag: String, logData: String) {
            if (!disabledForTesting) {
                Log.e(tag, logData)
            }
        }

        @JvmStatic
        fun log(logData: String) {
            if (!disabledForTesting) {
                Log.i(DEFAULT_LOG_TAG, logData)
            }
        }
    }
}
package com.naltynbekkz.core


object Constants {

    const val APP_VERSION = "0.1"

    const val SPLASH_SCREEN_DURATION = 100L

    const val DATABASE_NAME = "com.naltynbekkz.tanda.app_database"

    const val PAGE_SIZE = 10

    object BaseUrl {
        private const val HOST = "192.168.7.175:8080"

        const val AUTH = "http://$HOST/api/v1/auth/"
        const val SCHOOLS = "http://$HOST/api/v1/schools/"

    }
}

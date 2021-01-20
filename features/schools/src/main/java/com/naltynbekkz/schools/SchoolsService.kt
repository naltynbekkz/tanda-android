package com.naltynbekkz.schools

import retrofit2.Response
import retrofit2.http.GET

interface SchoolsService {

    @GET("schools")
    suspend fun getMyCourses(): Response<List<School>>

}
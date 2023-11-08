package com.mitocode.marketcomposeapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.auth0.android.jwt.JWT
import com.mitocode.marketcomposeapp.repositories.UserRepository
import com.mitocode.marketcomposeapp.services.TokenManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltWorker
class VerifyTokenWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val userRepository: UserRepository
) : Worker(context, workerParameters) {
    override fun doWork(): Result {

        val token = TokenManager.getToken() ?: ""

        val jwt = JWT(token)

        val expiresAt = jwt.expiresAt?.time
        val currentTimeSpam = System.currentTimeMillis()

        val diff = (expiresAt!! - currentTimeSpam)
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        if (hours <= 2) {
            GlobalScope.launch {
                userRepository.refreshToken()
            }
        }

        return Result.success()

    }
}
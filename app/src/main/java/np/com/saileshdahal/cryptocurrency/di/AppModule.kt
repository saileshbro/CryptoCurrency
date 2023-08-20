package np.com.saileshdahal.cryptocurrency.di

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import np.com.saileshdahal.cryptocurrency.common.Constants
import np.com.saileshdahal.cryptocurrency.data.remote.CoinPaprikaApi
import np.com.saileshdahal.cryptocurrency.data.repository.CoinRepositoryImpl
import np.com.saileshdahal.cryptocurrency.domain.repository.CoinRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api);
    }

}
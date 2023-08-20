package np.com.saileshdahal.cryptocurrency.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import np.com.saileshdahal.cryptocurrency.common.Resource
import np.com.saileshdahal.cryptocurrency.common.Resource.Loading
import np.com.saileshdahal.cryptocurrency.data.remote.CoinPaprikaApi
import np.com.saileshdahal.cryptocurrency.data.remote.dto.toCoin
import np.com.saileshdahal.cryptocurrency.data.remote.dto.toCoinDetail
import np.com.saileshdahal.cryptocurrency.domain.model.Coin
import np.com.saileshdahal.cryptocurrency.domain.model.CoinDetail
import np.com.saileshdahal.cryptocurrency.domain.repository.CoinRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            this.emit(Loading())
            val coins = api.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            this.emit(Loading())
            val coins = api.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
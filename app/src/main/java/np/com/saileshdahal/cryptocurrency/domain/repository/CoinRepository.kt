package np.com.saileshdahal.cryptocurrency.domain.repository

import kotlinx.coroutines.flow.Flow
import np.com.saileshdahal.cryptocurrency.common.Resource
import np.com.saileshdahal.cryptocurrency.domain.model.Coin
import np.com.saileshdahal.cryptocurrency.domain.model.CoinDetail

interface CoinRepository {
    suspend fun getCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>>
}
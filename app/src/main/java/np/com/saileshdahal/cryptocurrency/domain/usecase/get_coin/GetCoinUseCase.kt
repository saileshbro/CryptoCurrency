package np.com.saileshdahal.cryptocurrency.domain.usecase.get_coin

import np.com.saileshdahal.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String) = repository.getCoinById(coinId);
}
package np.com.saileshdahal.cryptocurrency.domain.usecase.get_coins

import np.com.saileshdahal.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.getCoins()
}
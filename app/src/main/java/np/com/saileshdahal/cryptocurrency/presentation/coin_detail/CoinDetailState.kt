package np.com.saileshdahal.cryptocurrency.presentation.coin_detail

import np.com.saileshdahal.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
) {}
package np.com.saileshdahal.cryptocurrency.presentation.coin_list

import np.com.saileshdahal.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = "",
) {}
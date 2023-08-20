package np.com.saileshdahal.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import np.com.saileshdahal.cryptocurrency.common.Constants
import np.com.saileshdahal.cryptocurrency.common.Resource
import np.com.saileshdahal.cryptocurrency.domain.usecase.get_coin.GetCoinUseCase
import javax.inject.Inject

@HiltViewModel()

class CoinDetailViewModel @Inject constructor(
    val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(CoinDetailState())
        private set

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId = coinId)
        }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            getCoinUseCase(coinId = coinId).onEach {
                state = when (it) {
                    is Resource.Error -> CoinDetailState(
                        error = it.message ?: "An unexpected error occurred"
                    )

                    is Resource.Loading -> CoinDetailState(isLoading = true)
                    is Resource.Success -> CoinDetailState(coin = it.data)
                }
            }.launchIn(viewModelScope)
        }
    }
}
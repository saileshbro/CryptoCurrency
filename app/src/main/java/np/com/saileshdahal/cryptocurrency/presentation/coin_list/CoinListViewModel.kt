package np.com.saileshdahal.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import np.com.saileshdahal.cryptocurrency.common.Resource
import np.com.saileshdahal.cryptocurrency.domain.usecase.get_coins.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private var _state by mutableStateOf(CoinListState())
    val state: CoinListState
        get() = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().onEach { result ->
                println(result.toString())
                _state = when (result) {
                    is Resource.Success -> CoinListState(coins = result.data ?: emptyList())
                    is Resource.Error -> CoinListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    is Resource.Loading -> CoinListState(isLoading = true)
                }
            }.launchIn(viewModelScope)
        }
    }
}

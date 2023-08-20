package np.com.saileshdahal.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import np.com.saileshdahal.cryptocurrency.presentation.Screen
import np.com.saileshdahal.cryptocurrency.presentation.coin_detail.CoinDetailScreen
import np.com.saileshdahal.cryptocurrency.presentation.coin_list.CoinListScreen
import np.com.saileshdahal.cryptocurrency.presentation.theme.CryptoCurrencyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CryptoCurrencyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(Screen.CoinListScreen.route) { CoinListScreen(navController = navController) }
                        composable("${Screen.CoinDetailScreen.route}/{coinId}") { CoinDetailScreen() }
                    }
                }
            }
        }
    }
}
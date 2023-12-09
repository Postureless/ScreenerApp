    package com.example.screenerapp.ui.screens

    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.size
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavController
    import com.example.screenerapp.domain.model.Stock


    @Composable
    fun ListingView (
        navController: NavController,
        viewModel: ListingViewModel = hiltViewModel()
    ) {
        var modifier = Modifier
        when(val stockListUiState = viewModel.stockListUiState) {
            is StockListUiState.Loading -> LoadingScreen(modifier.size(200.dp))
            is StockListUiState.Success -> StockListScreen(
                stocks = stockListUiState.stockList,
                modifier = modifier
            )
            else -> ErrorScreen(viewModel.retryAction, modifier)
        }
    }

    @Composable
    fun ErrorScreen(retryAction: Unit, modifier: Modifier) {

    }

    @Composable
    fun StockListScreen(stocks: List<Stock>, modifier: Modifier) {
        Column(
            modifier = modifier
        ) {
            stocks.forEach { stock -> StockRow(stock) {

            } }
        }
    }

    @Composable
    fun LoadingScreen(size: Any) {

    }

    @Composable
    fun StockRow(stock: Stock, onClick: () -> Unit) {
        Row(
            modifier = Modifier.clickable {
                onClick.invoke()
            }
        ) {
            Text(
                text = stock.ticker,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background,
                maxLines = 1
            )
            Text(
                text = stock.name,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background,
                maxLines = 1
            )
            Text(
                text = stock.mic,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background,
                maxLines = 1
            )
        }
    }
package pe.geff.gohab.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.geff.gohab.home.component.QuoteContentComponent

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopBarComponent(onClick = {
                // TODO Navigate to SettingsScreen
            })
        },
        floatingActionButton = {
            FloatingActionButtonComponent(onClick = {
                // TODO Navigate to AddNewHabit
            })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(innerPadding).padding(start = 16.dp, end = 16.dp)
            ) {

                item {
                    Spacer(Modifier.padding(top = 16.dp))
                    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                        QuoteContentComponent()
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Home", fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = onClick)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = CircleShape
                    )
                    .padding(10.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )
        }
    )
}

@Composable
fun FloatingActionButtonComponent(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        content = {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        },
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.5f
                ), shape = CircleShape
            )
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}

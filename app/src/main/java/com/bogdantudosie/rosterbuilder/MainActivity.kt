package com.bogdantudosie.rosterbuilder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bogdantudosie.rosterbuilder.data.GameUnit
import com.bogdantudosie.rosterbuilder.ui.theme.RosterBuilderTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RosterBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sampleUnit = GameUnit(
                        1,
                        "Tactical Squad",
                        "Infantry",
                        false,
                        85
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PageScaffold(gameUnit = sampleUnit,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth())
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun PageScaffold(gameUnit: GameUnit?, modifier: Modifier) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { println("Back Button Pressed") }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { println("Save button pressed") },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Check, "Localized description")
                    }
                }
            )
        },
        content = { contentPadding ->
            UnitDetailTextFields(gameUnit = gameUnit,
                modifier = modifier
                    .padding(contentPadding))
        }
    )
}

@Composable
fun UnitDetailTextFields(gameUnit: GameUnit?, modifier: Modifier) {
    var unitName by remember { mutableStateOf(gameUnit?.name) }
    var unitType by remember { mutableStateOf(gameUnit?.type) }
    var unitCost by remember { mutableStateOf(gameUnit?.pointCost) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = unitName!!,
            onValueChange = { unitName = it },
            label = { Text("Unit name") },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Ascii
            ),
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = unitType!!,
            onValueChange = { unitType = it },
            label = { Text("Unit Type") },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Ascii
            ),
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = unitCost!!.toString(),
            onValueChange = { unitCost = it.toInt() },
            label = { Text("Unit cost") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UnitOverlayPreview() {
    RosterBuilderTheme {
        val sampleUnit = GameUnit(
            1,
            "Tactical Squad",
            "Infantry",
            false,
            85
        )
        Spacer(modifier = Modifier.height(16.dp))
        PageScaffold(gameUnit = sampleUnit,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth())
    }
}
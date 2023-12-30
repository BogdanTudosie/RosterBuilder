package com.bogdantudosie.rosterbuilder

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
    var unitName by remember { mutableStateOf(gameUnit?.name) }
    var unitType by remember { mutableStateOf(gameUnit?.type) }
    var unitCost by remember { mutableStateOf(gameUnit?.pointCost) }

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
        topBar = {
            TopAppBar(
                title = {
                    Text("Unit details")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ))
        },
    ) {
        innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UnitTextField(
                value = unitName!!,
                label = "Unit name",
                icon = Icons.Filled.Info,
                contentDescription = "Use this field to adjust unit name",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Ascii
                ),
                modifier = modifier,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            UnitTextField(
                value = unitType!!,
                label = "Unit type",
                icon = Icons.Filled.Info,
                contentDescription = "Use this field to adjust the unit type",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Ascii
                ),
                modifier = modifier,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            UnitTextField(
                value = unitCost!!.toString(),
                label = "Unit cost",
                icon = Icons.Filled.Info,
                contentDescription = "Use this field to adjust the unit cost in points",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier,
            )
        }
    }
}

@Composable
fun UnitTextField(value: String,
                  label: String,
                  icon: ImageVector,
                  contentDescription: String,
                  keyboardOptions: KeyboardOptions,
                  modifier: Modifier) {
    val context = LocalContext.current
    var textValue by remember { mutableStateOf(value) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text(label) },
            maxLines = 1,
            keyboardOptions = keyboardOptions
        )
        IconButton(onClick = {
            makeInfoToast(context, contentDescription)
        }) {
            Icon(imageVector = icon,
                contentDescription = contentDescription,
                modifier = modifier
            )
        }
    }
}

fun makeInfoToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
package com.bogdantudosie.rosterbuilder

import android.os.Bundle
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bogdantudosie.rosterbuilder.data.GameUnit
import com.bogdantudosie.rosterbuilder.ui.theme.RosterBuilderTheme

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
                    Text("Unit detail view",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(24.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    UnitOverlayView(gameUnit = sampleUnit,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth())
                }
            }
        }
    }
}
@Composable
fun UnitOverlayView(gameUnit: GameUnit?, modifier: Modifier = Modifier) {
    var unitName by remember { mutableStateOf(gameUnit?.name) }
    var unitType by remember { mutableStateOf(gameUnit?.type) }
    var unitCost by remember { mutableStateOf(gameUnit?.pointCost) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column( horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)) {
            TextField(value = unitName!!,
                onValueChange = { unitName = it },
                label = {Text("Unit name") },
                keyboardOptions = KeyboardOptions(autoCorrect = false,
                                                  keyboardType = KeyboardType.Ascii),
                modifier = modifier)
            TextField(value = unitType!!,
                onValueChange = { unitType = it },
                label = { Text("Unit Type") },
                keyboardOptions = KeyboardOptions(autoCorrect = false,
                    keyboardType = KeyboardType.Ascii),
                modifier = modifier)
            TextField(value = unitCost!!.toString(),
                onValueChange = { unitCost = it.toInt() },
                label = { Text("Unit cost") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = modifier)
        }
    }
}

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
        Text("Unit detail view",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(24.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        UnitOverlayView(gameUnit = sampleUnit,
            modifier = Modifier.padding(2.dp).fillMaxWidth())
    }
}
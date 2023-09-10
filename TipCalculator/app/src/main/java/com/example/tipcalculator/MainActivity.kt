package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                TipTimeLayout()
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.96.dp)
            .verticalScroll(rememberScrollState()), // Allow the vertical scroll for the column and remember its state on the scroll
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var tipPercent by remember { mutableStateOf("15") }
        var amountInput by remember { mutableStateOf("") }
        var roundUpInput by remember { mutableStateOf(false) }
        val tip =
            calculateTip(
                amountInput.toDoubleOrNull() ?: 0.0,
                tipPercent.toDoubleOrNull() ?: 0.0,
                roundUpInput
            )

        Text(
            text = stringResource(id = R.string.calculate_tip),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 16.dp)
        )
        EditNumberField(
            amountInput,
            { amountInput = it },
            label = R.string.bill_amount,
            leadingIcon = R.drawable.money,
            modifier = Modifier
                .padding(bottom = 25.6.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Number, // Just accept numbers
                imeAction = ImeAction.Next // Action button
            ),
        )
        EditNumberField(
            tipPercent,
            { tipPercent = it },
            label = R.string.tip_percentage,
            leadingIcon = R.drawable.percent,
            modifier = Modifier
                .padding(bottom = 25.6.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Number, // Just accept numbers
                imeAction = ImeAction.Search // Action button
            ),
        )
        RoundButton(
            roundUp = roundUpInput,
            onRoundUpChanged = { roundUpInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .size(40.6.dp)
        )
        Text(
            text = stringResource(
                id = R.string.tip_amount,
                tip
            ), // The second argument is the format the %s
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class) // For available the TextField component
@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int, // @StringRes is an annotation for the well semantic
    @DrawableRes leadingIcon: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = ""
            )
        },
        label = { Text(text = stringResource(id = label)) },
        singleLine = true,
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Just accept numbers
        keyboardOptions = keyboardOptions,
        supportingText = { Text(text = stringResource(R.string.no_commas), fontSize = 12.sp) },
        isError = value.contains(','),
        modifier = modifier,
    )
}

@Composable
fun RoundButton(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = Modifier
                .fillMaxWidth() // Fill its max width
                .wrapContentWidth(Alignment.End) // Wrap the content and the alignment it to the End(Right)
                .semantics { contentDescription = "Switch button Round Up?"}
        )
    }
}

// The visibility modifier of the method is 'internal' because I need access it in the test
@VisibleForTesting // Allow the visibility for the test
internal fun calculateTip(amount: Double, tipPercent: Double = 15.0, isRoundUp: Boolean): String {
    var tip = amount * (tipPercent / 100)
    if (isRoundUp)
        tip = Math.ceil(tip)

    // 'getCurrencyInstance' return the current local instance for the format
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    MaterialTheme{
        TipTimeLayout()
    }
}
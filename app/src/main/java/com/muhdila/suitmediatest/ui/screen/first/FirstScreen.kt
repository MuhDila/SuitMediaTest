package com.muhdila.suitmediatest.ui.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.muhdila.suitmediatest.R
import com.muhdila.suitmediatest.ui.component.ButtonComponent
import com.muhdila.suitmediatest.ui.component.OutlinedTextFieldComponent
import com.muhdila.suitmediatest.ui.navigation.Screen
import com.muhdila.suitmediatest.ui.theme.myFont
import com.muhdila.suitmediatest.ui.theme.myFont2
import java.util.Locale

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var palindrome by rememberSaveable { mutableStateOf("") }

    var isPalindrome by remember { mutableStateOf(false) }
    var showDialogPalindrome by remember { mutableStateOf(false) }
    var showDialogName by remember { mutableStateOf(false) }

    fun isPalindrome(input: String): Boolean {
        val cleanedText = input.replace("[^A-Za-z0-9]".toRegex(), "").lowercase(Locale.getDefault())
        return cleanedText == cleanedText.reversed()
    }

    fun checkPalindrome() {
        isPalindrome = if (palindrome.isNotEmpty()) {
            isPalindrome(palindrome)
        } else {
            false
        }
        showDialogPalindrome = true
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = stringResource(R.string.suitmedia),
                    style = TextStyle(
                        fontSize = 28.sp, textAlign = TextAlign.Center, fontFamily = myFont2
                    ),
                )
                Text(
                    text = stringResource(R.string.desc), style = TextStyle(
                        textAlign = TextAlign.Center, fontFamily = myFont
                    ), modifier = Modifier.padding(top = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.suitmedia),
                    contentDescription = "Photo Profile",
                    modifier = Modifier
                        .size(350.dp),
                )

                OutlinedTextFieldComponent(
                    provideText = stringResource(R.string.enter_name),
                    icon = painterResource(R.drawable.ic_name),
                    value = name,
                    onValueChange = { name = it },
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextFieldComponent(
                    provideText = stringResource(R.string.enter_palindrome),
                    icon = painterResource(R.drawable.ic_text),
                    value = palindrome,
                    onValueChange = { palindrome = it },
                )

                Spacer(modifier = Modifier.height(24.dp))
                ButtonComponent(
                    provideText = stringResource(R.string.check_palindrome),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    checkPalindrome()
                }

                Spacer(modifier = Modifier.height(8.dp))
                ButtonComponent(
                    provideText = stringResource(R.string.next),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (name.isNotEmpty()) {
                        val userId = "0"
                        navController.navigate(Screen.Second.createRoute(name, userId))
                    } else {
                        showDialogName = true
                    }
                }
            }
        }
    }

    if (showDialogPalindrome) {
        AlertDialog(
            onDismissRequest = { showDialogPalindrome = false },
            title = {
                Text("Palindrome Check")
            },
            text = {
                Text(
                    if (palindrome.isNotEmpty()) {
                        if (isPalindrome) {
                            "isPalindrome"
                        } else {
                            "not palindrome"
                        }
                    } else {
                        "Please enter palindrome sentence first."
                    }
                )
            },
            confirmButton = {
                Button(onClick = { showDialogPalindrome = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showDialogName) {
        AlertDialog(
            onDismissRequest = { showDialogName = false },
            title = {
                Text("Empty Name")
            },
            text = {
                Text("Please enter your name first.")
            },
            confirmButton = {
                Button(onClick = { showDialogName = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun FirstScreenPreview() {
    FirstScreen(navController = rememberNavController())
}
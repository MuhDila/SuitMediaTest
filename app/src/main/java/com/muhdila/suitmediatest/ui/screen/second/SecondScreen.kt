package com.muhdila.suitmediatest.ui.screen.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.muhdila.suitmediatest.R
import com.muhdila.suitmediatest.data.helper.LocalViewModelFactory
import com.muhdila.suitmediatest.ui.component.ButtonComponent
import com.muhdila.suitmediatest.ui.navigation.Screen
import com.muhdila.suitmediatest.ui.theme.myFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    detailUserViewModel: DetailUserViewModel = viewModel(
        factory = LocalViewModelFactory.provide()
    ),
    navController: NavController,
    name: String,
    userId: String,
) {
    val userDetails by detailUserViewModel.myUserDetails.observeAsState()
    val isLoading by detailUserViewModel.isLoading.observeAsState()

    LaunchedEffect(detailUserViewModel) {
        launch {
            detailUserViewModel.loadMyUserDetails(userId.toInt())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.second_screen),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier.fillMaxWidth(),
                        fontFamily = myFont,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .size(40.dp)
                    ) {
                        val icon: Painter = painterResource(id = R.drawable.ic_back)
                        Icon(
                            painter = icon,
                            contentDescription = "Icon Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = myFont
                    ),
                )
                Text(
                    text = name,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = myFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    ),
                )
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (userId == "0") {
                    Text(
                        text = stringResource(R.string.selected_user),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = myFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        ),
                    )
                } else {
                    if (isLoading == true) {
                        CircularProgressIndicator()
                    } else {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape),
                                painter = rememberAsyncImagePainter(model = userDetails?.data?.avatar),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                userDetails?.data?.firstName?.let {
                                    Text(
                                        text = it,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            textAlign = TextAlign.Center,
                                            fontFamily = myFont,
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                    )
                                }
                                Text(
                                    text = " ${userDetails?.data?.lastName}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        fontFamily = myFont,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                )
                            }
                            userDetails?.data?.email?.let {
                                Text(
                                    text = it,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontFamily = myFont,
                                        fontSize = 14.sp
                                    ),
                                )
                            }
                        }
                    }
                }
                ButtonComponent(
                    provideText = stringResource(R.string.choose_user),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                ) {
                    navController.navigate(Screen.Third.createRoute(name))
                }
            }
        }
    }
}
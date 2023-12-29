package com.muhdila.suitmediatest.ui.screen.third

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.muhdila.suitmediatest.R
import com.muhdila.suitmediatest.data.remote.response.GetUsersData
import com.muhdila.suitmediatest.ui.navigation.Screen
import com.muhdila.suitmediatest.ui.theme.myFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UsersViewModel,
    name: String
) {
    val usersData: LazyPagingItems<GetUsersData> =
        viewModel.usersData.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.third_screen),
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(usersData.itemCount) { index ->
                val item = usersData[index]
                if (item != null) {
                    UsersComponent(item, navController, modifier, name = name)
                }
            }

            usersData.apply {
                when {
                    loadState.refresh is LoadState.Loading ||
                            loadState.append is LoadState.Loading ->
                        item { LoadingItem(modifier) }
                    loadState.refresh is LoadState.Error ||
                            loadState.append is LoadState.Error ->
                        item { /* handle error state here */ }
                }
            }
        }
    }
}

@Composable
fun UsersComponent(
    dataItem: GetUsersData,
    navController: NavController,
    modifier: Modifier,
    name: String
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.Second.createRoute(name, dataItem.id.toString()))
            },
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = rememberAsyncImagePainter(dataItem.avatar),
                contentDescription = "Image Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Row {
                    Text(
                        text = dataItem.firstName,
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = myFont,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                    Text(
                        text = " ${dataItem.lastName}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = myFont,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                Text(
                    text = dataItem.email,
                    style = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = myFont
                    ),
                )
            }
        }
    }
}

@Composable
fun LoadingItem(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}
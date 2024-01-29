package com.techflix.catsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.techflix.catsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                CatsAppBar()
                CatsList()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsAppBar() {
    CenterAlignedTopAppBar(
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cats App",
                    fontWeight = FontWeight.Bold,
                    color = Color.Magenta,
                )
            }
        }
    )
}

@Composable
fun CatsList() {
    val mainViewModel: MainViewModel = viewModel()
    val catsList = mainViewModel.catsList.collectAsState()
    LazyColumn(content = {
        itemsIndexed(
            catsList.value
        ) { index, item ->
            CatViewItem(
                item.url,
                "Cat Id: ${item.id}",
                if (index % 2 == 0) "This is a good cat. It should only do good things in life, and life will be awesome." else " This is a bad cat. Bad cats should learn to behave properly, or else they will be punished."
            )
        }
    })
}

@Composable
fun CatViewItem(imgUrl: String, dummyTitle: String, dummySubTitle: String) {
    val contentDescriptionImg = "Cat Image"
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = imgUrl),
                contentDescription = contentDescriptionImg,
                Modifier
                    .size(80.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dummyTitle, fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = dummySubTitle, modifier = Modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}



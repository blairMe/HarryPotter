package com.bfa.harrypotterarticle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bfa.harrypotterarticle.R
import com.bfa.harrypotterarticle.api.Characters
import com.bfa.harrypotterarticle.api.CharactersItem
import com.bfa.harrypotterarticle.utils.DataOrException
import com.bfa.harrypotterarticle.viewmodel.HarryPotterViewModel

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: HarryPotterViewModel = hiltViewModel(),
) {
    val charactersData = produceState<DataOrException<Characters, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getCharactersData()
    }.value

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Harry Potter",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                if(charactersData.loading == true) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator()
                    }
                } else if (charactersData.data != null) {

                    val characters = charactersData.data!!
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            items(characters) {
                                ActorItem(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActorItem(
    characterItem: CharactersItem) {

    Card(
        modifier = Modifier
            .padding(3.dp)
            .height(250.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterItem.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.image_thumbnail),
                modifier = Modifier.height(200.dp),
                contentDescription = characterItem.actor,
                contentScale = ContentScale.Crop
            )
            Text(text = characterItem.name)
            Text(text = characterItem.actor)
        }
    }
}

package eu.tutorials.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.superheroes.model.Hero
import eu.tutorials.superheroes.model.HeroesRepository
import eu.tutorials.superheroes.ui.theme.SuperheroesTheme


@Composable
fun SuperHeroesApp(
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            AppTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) {
                HeroItem(
                    hero = it,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}


@Composable
fun HeroItem (
    hero: Hero,
    modifier: Modifier = Modifier
){
    Card (
        modifier=modifier,
        elevation = CardDefaults.cardElevation(disabledElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
        ){
            contentHero(
                hero = hero,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

            Image(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
                painter = painterResource(hero.imageRes),
                contentDescription = stringResource(hero.descriptionRes)
            )
        }
    }
}





@Composable
fun contentHero(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
    ){
        Text(
            text = stringResource(hero.nameRes),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(hero.descriptionRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier)
}

@Preview(
    showBackground = true
    , showSystemUi = true)
@Composable
fun WhiteTheme() {
    SuperheroesTheme (darkTheme = false){
        SuperHeroesApp(modifier = Modifier.fillMaxSize())
    }
}



@Preview(
    showBackground = true
    , showSystemUi = true)
@Composable
fun DarkTheme() {
    SuperheroesTheme (darkTheme = true){
        SuperHeroesApp(modifier = Modifier.fillMaxSize())
    }
}
package com.test.recyclerviewcomposeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.test.recyclerviewcomposeexample.data.TvShowList
import com.test.recyclerviewcomposeexample.model.TvShow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            LazyColumnDemo2{
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }

            DisplayTvShows{
                Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Composable
fun ScrollableColumnScope(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)){
    for (i in 1..100){
        Text(
            "User Name $i",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(10.dp)
        )
        Divider(color = Color.Black, thickness = 5.dp)
    }
    }
}

@Composable
fun LazyColumnDemo(){
    LazyColumn{
        items(100) {
            Text(
                "User Name $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Composable
fun LazyColumnDemo2(selectedItem: (String) -> Unit){

    LazyColumn{
        items(100) {
            Text(
                "User Name $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { selectedItem("$it Selected Item") }
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Composable
fun DisplayTvShows(selectedItem: (TvShow) -> Unit) {

    val tvShows = remember { TvShowList.tvShows }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(tvShows){
                TvShowListItem(tvShow = it, selectedItem = selectedItem )
        }
    }

}

@Composable
fun TvShowListItem(tvShow: TvShow, selectedItem: (TvShow) -> Unit){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize((10.dp)))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(tvShow) },
            verticalAlignment = Alignment.CenterVertically
        ){
            TvShowImage(tvShow = tvShow)
            Column {
                Text(text = tvShow.name, style = MaterialTheme.typography.h5 )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = tvShow.overview,
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = tvShow.year.toString(), style = MaterialTheme.typography.h5)
                    Text(text = tvShow.rating.toString(), style = MaterialTheme.typography.h5)
                }

            }
        }
    }
}

//Displaying TvShow list
@Composable
private fun TvShowImage(tvShow: TvShow){
    Image(painter = painterResource(id = tvShow.imageId), contentDescription = null, contentScale = ContentScale.Crop,
    modifier = Modifier
        .padding(4.dp)
        .height(140.dp)
        .width(100.dp)
        .clip(RoundedCornerShape(corner = CornerSize(10.dp))))
}

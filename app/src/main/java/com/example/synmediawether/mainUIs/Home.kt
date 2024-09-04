import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synmediawether.R
import com.example.synmediawether.pojo.WeatherItem
import com.example.synmediawether.repository.weathers
import com.example.synmediawether.viewmodelandfactory.WetherViewmodel
import kotlinx.coroutines.delay


@Composable
fun HomeScreenUI(viewmodel: WetherViewmodel, navController: NavHostController,modifier: Modifier = Modifier) {
    val weatherData by viewmodel.weatherResponses.collectAsState()
    val clouds = remember { mutableStateOf(WeatherItem()) }

    LaunchedEffect(weatherData){
        if (weatherData.weather?.isNotEmpty() == true){
            clouds.value = weatherData.weather?.filterNotNull()?.get(0)!!
        }

    }
    Box(modifier = modifier
        .fillMaxSize()
        .background(brush = BackgroundTheme())) {
        BackBlur()
        IconButtons {
            viewmodel.logittude?.let { viewmodel.lattitude?.let { it1 -> viewmodel.fetchWeather(lattitude = it1, logittude = it) } }
        }
        Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Alignments(align = 20.dp)
            CommonText(text = "${weatherData.name?:""},${weatherData.sys?.country?:""}", style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.W300,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily =  FontFamily.SansSerif

            ), fontSize = 18.sp)
            Alignments(align = 15.dp)
            CommonImage(
                imagePainter = painterResource(id = weathers.data.filter { clouds.value.main == it.name }.getOrNull(0)?.image ?: R.drawable.sunny),
                contentDescription = "Description of the image",
                modifier = Modifier.size(160.dp)
            )
            Alignments(align = 5.dp)
            CommonText(text = clouds.value.description?:"", style = TextStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Light,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily =  FontFamily.SansSerif

            ), fontSize = 15.sp)
            Alignments(align = 10.dp)
            CommonText(text = "${ weatherData.main?.temp?.let {
               ( it - 273.15).toInt()
            } ?: "0"}°C", style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily =  FontFamily.SansSerif

            ), fontSize = 40.sp)
            Alignments(align = 5.dp)
            CommonText(text = weatherData.time?:"", style = TextStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily =  FontFamily.SansSerif

            ), fontSize = 15.sp)
            Card(
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent ,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .background(brush = SecondaryGradient())
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ImageTextLayout(
                        image = painterResource(id = weathers.data.filter { clouds.value.main == it.name }.getOrNull(0)?.image ?: R.drawable.sunny),
                        text1 = "${ weatherData.main?.feelsLike?.let {
                            ( it - 273.15).toInt()
                        } ?: "0"}°C",
                        text2 = "Feels Like",
                        modifier = Modifier.weight(1f)
                    )
                    ImageTextLayout(
                        image = painterResource(id = weathers.data.filter { clouds.value.main == it.name }.getOrNull(0)?.image ?: R.drawable.sunny),
                        text1 = "${ weatherData.main?.tempMin?.let {
                            ( it - 273.15).toInt()
                        } ?: "0"}°C",
                        text2 = "Min",
                        modifier = Modifier.weight(1f)
                    )
                    ImageTextLayout(
                        image = painterResource(id = weathers.data.filter { clouds.value.main == it.name }.getOrNull(0)?.image ?: R.drawable.sunny),
                        text1 = "${ weatherData.main?.tempMax?.let {
                            ( it - 273.15).toInt()
                        } ?: "0"}°C",
                        text2 = "Max",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Alignments(align = 15.dp)

            Card(
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x8D957DCD),
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ContentTextLayout(
                        image = painterResource(id = R.drawable.windspeed),
                        text1 = "${String.format("%.2f", weatherData.wind?.speed.let { it?.times(3.6)?.toFloat() ?: 0.0 })}km/h",
                        text2 = "Wind Speed",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )

                    ContentTextLayout(
                        image = painterResource(id = R.drawable.humidity),
                        text1 = "${weatherData.main?.humidity?:"0"}%",
                        text2 = "Humidity",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )
                }
                Alignments(align = 10.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ContentTextLayout(
                        image = painterResource(id = R.drawable.location),
                        text1 = "${weatherData.wind?.deg?:"0"}°",
                        text2 = "Direction",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )
                    ContentTextLayout(
                        image = painterResource(id = R.drawable.cl),
                        text1 = "${weatherData.clouds?.all?:"0"}%",
                        text2 = "Clouds",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )

                }
                Alignments(align = 10.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ContentTextLayout(
                        image = painterResource(id = R.drawable.visibile),
                        text1 = "${weatherData.visibility?:"0"} m",
                        text2 = "Visibility",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )
                    ContentTextLayout(
                        image = painterResource(id = R.drawable.pre),
                        text1 = "${weatherData.main?.pressure?:"0"} hPa",
                        text2 = "Pressure",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    )

                }
            }

        }

    }
}


fun kelvinToCelsius(kelvin: Double): Double {
    return kelvin - 273.15
}
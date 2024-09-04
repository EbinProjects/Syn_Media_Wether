
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synmediawether.R
import com.example.synmediawether.Utilsss.Screens
import kotlinx.coroutines.delay


@Composable
fun SplashUI(navController: NavHostController,modifier : Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(brush = BackgroundTheme())) {
        BackBlur()
        Column(modifier = modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            CommonImage(
                imagePainter = painterResource(id = R.drawable.clouds),
                contentDescription = "Description",
                modifier = Modifier.size(160.dp)
            )
            Alignments(align = 5.dp)
            CommonText(text = "SynMedia-Weather", style =TextStyle(
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            ) )
        }

    }

    LaunchedEffect(Unit) {
        delay(1400)
        navController.navigate(Screens.homeScreen.route) {
            navController.popBackStack()
        }
    }
}

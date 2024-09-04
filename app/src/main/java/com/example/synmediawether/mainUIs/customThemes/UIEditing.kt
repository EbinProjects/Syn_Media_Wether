import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.synmediawether.R


@Composable
fun BackgroundTheme(): Brush {
    val colors = listOf(
        Color(0xFF352163),
        Color(0xFF6C43C9)
    )
    val endOffset = Offset(0f, Float.POSITIVE_INFINITY)
    return Brush.linearGradient(colors = colors, Offset.Zero, endOffset)
}

@Composable
fun SecondaryGradient(): Brush {
    val colors = listOf(
        Color(0xFF957DCD),
        Color(0xFF523D7F)
    )
    val endOffset = Offset(0f,Float.POSITIVE_INFINITY)
    return Brush.linearGradient(colors = colors, Offset.Zero, endOffset)
}

@Composable
fun BackBlur(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .graphicsLayer(alpha = 0.5f),
        painter = painterResource(id = R.drawable.blur),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun IconButtons(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 15.dp, top = 15.dp), contentAlignment = Alignment.TopEnd
    ) {
        androidx.compose.material3.IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(40.dp)
                .background(
                    brush = SecondaryGradient(),
                    shape = RoundedCornerShape(10.dp)
                ),

            ) {
            Icon(
                modifier = Modifier
                    .size(35.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = "Icon",
                tint = Color.White
            )
        }

    }

}

@Composable
fun CommonText(
    text: String,
    fontSize: TextUnit = 20.sp, // Default font size is 20.sp
    modifier: Modifier = Modifier,
    style: TextStyle
) {
    Text(
        text = text,
        fontSize = fontSize,
        style = style,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun Alignments(modifier: Modifier = Modifier, align: Dp) {
    Spacer(modifier = modifier.height(align))
}

@Composable
fun CommonImage(
    imagePainter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Image(
        painter = imagePainter,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}

@Composable
fun ImageTextLayout(image: Painter, text1: String, text2: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonImage(
            imagePainter = image,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Alignments(align = 8.dp)

        CommonText(
            text = text1, style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif

            ), fontSize = 18.sp
        )

        Alignments(align = 4.dp)

        CommonText(
            text = text2, style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif

            ), fontSize = 14.sp
        )
    }
}

@Composable
fun ContentTextLayout(image: Painter, text1: String, text2: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .background(brush = SecondaryGradient())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonText(
            text = text1, style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif

            ),
            fontSize = 18.sp
        )
        Alignments(align = 4.dp)
        CommonImage(
            imagePainter = image,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Alignments(align = 4.dp)

        CommonText(
            text = text2, style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.None,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif

            ),

            fontSize = 14.sp
        )
    }
}


package br.com.engcomp.paroquiansmh.presentation.ui.componentes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel


@Composable
fun CardBiblia(tituloLivro: String, mensagens: List<String>, numero: String){

    var isExpanded by remember {mutableStateOf(false)}
    val rotateState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    Column {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable {
                isExpanded = !isExpanded
            }
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = tituloLivro)
                IconButton(onClick = {
                    isExpanded = !isExpanded
                }, modifier = Modifier
                    .weight(1f)
                    .alpha(0.2f)
                    .rotate(rotateState)) {
                    Icon(painter = painterResource(id = R.drawable.icon_arrow_down), contentDescription = "icon down")
                }
            }
        }
        if (isExpanded){
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                /*Text(text = numero)
                Text(text = "numero")*/
                mensagens.forEachIndexed { index, s ->
                    Text(text = (index+1).toString() + " - " + s, modifier = Modifier.padding(10.dp))
                    if (index % 3 == 0){
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider()
                    }
                }
            }
        }
    }

}







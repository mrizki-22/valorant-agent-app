package com.example.valorantagents.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.valorantagents.ui.ValorantAgentsViewModel


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    agentId: String,
    viewModel: ValorantAgentsViewModel
) {
    val agent = viewModel.getAgentDetail(agentId)

    Column(
        modifier = modifier
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(modifier = Modifier.height(300.dp).fillMaxWidth().align(CenterHorizontally), contentAlignment = Alignment.Center) {
            var isLoading by remember { mutableStateOf(true) }

            AsyncImage(
                model = agent.imageUrl,
                contentDescription = "${agent.name} Image",
                onSuccess = {
                    isLoading = false
                },
            )
            if (isLoading) CircularProgressIndicator()

        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            AttributeData(attribute = "Agent Name", value = agent.name)
            AttributeData(attribute = "Gender", value = agent.gender)
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            AttributeData(attribute = "Role", value = agent.role)
            AttributeData(attribute = "Race", value = agent.race)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "Abilities",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                fontStyle = Italic
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                agent.abilities.forEach {
                    AbilityItem(imageUrl = it.imageUrl, name = it.name)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "Biography",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                fontStyle = Italic
            )
            BiographyText(bio = agent.biography)

        }

    }


}

@Composable
fun AttributeData(attribute: String, value: String) {
    Column(horizontalAlignment = CenterHorizontally) {
        Text(
            text = attribute,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontStyle = Italic
        )
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun AbilityItem(imageUrl: String, name: String) {
    Column(horizontalAlignment = CenterHorizontally, modifier = Modifier.width(60.dp)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            softWrap = true
        )
    }
}

@Composable
fun BiographyText(bio: String) {
    Text(
        text = bio,
        fontSize = 16.sp,
        textAlign = TextAlign.Justify,
        softWrap = true
    )
}





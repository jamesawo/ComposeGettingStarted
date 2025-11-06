package com.james.composegettingstarted.composable

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.james.composegettingstarted.R
import com.james.composegettingstarted.model.Fixtures
import com.james.composegettingstarted.model.Message
import com.james.composegettingstarted.model.SampleData
import com.james.composegettingstarted.ui.theme.ComposeGettingStartedTheme


@Composable
fun MessageCard(msg: Message) {
    Row(
        Modifier.padding(all = 8.dp), verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = Fixtures.Image.contentDescription,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))


        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )



        Column(
            Modifier
                .padding(all = 8.dp)
                .clickable { isExpanded = !isExpanded }) {
            Text(
                text = "${Fixtures.Greeting.saluate}, ${msg.author}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(
                shape = MaterialTheme.shapes.small,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = msg.body,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }

        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode"
)
@Composable
fun PreviewMessageCardLight() {
    ComposeGettingStartedTheme {
        Surface {
            MessageCard(
                msg = Message(
                    Fixtures.Greeting.name, Fixtures.Greeting.longDescription
                )
            )
        }

    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeGettingStartedTheme {
        Conversation(SampleData.conversationSample)
    }
}
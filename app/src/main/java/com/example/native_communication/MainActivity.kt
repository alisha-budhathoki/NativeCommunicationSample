package com.example.native_communication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.native_communication.ui.theme.Native_communicationTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Native_communicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(text = "Flutter Activity")

                        MyButton(onClick = {
                            val engine = FlutterEngineCache.getInstance().get("cached_engine")
                            engine?.let {
                                val channel = MethodChannel(it.dartExecutor.binaryMessenger, "com.globalBank.module/nativeCommunication")
                                val payload = mapOf(
                                    "csId" to "R011032880",
                                    "token" to "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYjJiOTE1OTktNjI0NC00Y2EzLTg5ZTktYTczMjdjNTgzN2UxIiwidHlwZSI6IkMiLCJjb3Jwb3JhdGUiOiIwIiwiY2hhbm5lbCI6IjEiLCJpcCI6IjEwMy4xODAuMjQwLjE4MiIsImJyb3dzZXJOYW1lQyI6Ik1vYmlsZSBDaHJvbWUiLCJicm93c2VyVmVyc2lvbkMiOiIxMzMuMC4wLjAiLCJvc05hbWUiOiJBbmRyb2lkIiwib3NWZXJzaW9uIjoiNi4wIiwiZGV2aWNlSWRlbnRpZmllciI6IjZiZjFiNjE1LTVkMDktNDg1Yy05YmEyLTFkYjc2M2MyZTAwNiIsImlhdCI6MTc1NjM2NzQwNywiZXhwIjoxNzU2NjI2NjA3fQ.5B1IkTjc4zxX49gIzQzFcrx4c63J8SSmGjCtDlEg3tPPGl08cyyWcEuAqPyXPQVtS2XsG3-XmyR6fgW-KrAsog"
                                )
                                channel.invokeMethod("UserCredentials", payload)
                            }


                            startActivity(
                                FlutterActivity.withCachedEngine("cached_engine").build(this@MainActivity)
                            )
                        })


                    }
                }
            }
        }
    }
}


@Composable
fun MyButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Launch Flutter!")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Native_communicationTheme {
        Greeting("Android")
    }
}
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
                                    "csId" to "R009909889",
                                    "token" to "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiZDFmZDdiZDAtM2E0Ni00OWVhLWJmYmMtZTFhNDg0MDUwMTk3IiwidHlwZSI6IkMiLCJjb3Jwb3JhdGUiOiIwIiwiY2hhbm5lbCI6IjIiLCJpcCI6IjEwMy4xODAuMjQwLjE4MiIsImRldmljZUlkZW50aWZpZXIiOiIxMjMzNCIsImlhdCI6MTc2NzI1MDkwOSwiZXhwIjoxNzY3NTEwMTA5fQ.WGLACn-ka1SJraX2h3ptrhRghY0zDKF56wtm6LaeQMqvsYF_6y0C_uV73CJYY8Ph_no6rCui35d1gkdNQZDYRA"
                                )
                                channel.invokeMethod("UserCredentials", payload)



                            startActivity(
                                FlutterActivity.withCachedEngine("cached_engine").build(this@MainActivity)
                            )
                        }
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
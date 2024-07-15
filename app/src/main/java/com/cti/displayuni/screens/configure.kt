package com.cti.displayuni.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.showLogs
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun Configure() {
    val wd = mParameters.mWidthinPx

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp

    var widthdp = widthDP / 3f

    Log.d("dwinsize: ", wd.toString())

    val dnsty = conf.densityDpi

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        widthdp = widthDP / 3.7f

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        widthdp = widthDP / 3f

        Log.d("Desktop: ", wd.toString())
    }

    val heightDP = LocalConfiguration.current.screenHeightDp.dp
    val focusManager = LocalFocusManager.current
    val densityDpi = LocalConfiguration.current.densityDpi


    Log.d("SCREEN WIDTH", "Configure:$widthDP")
    Log.d("SCREEN HEIGHT", "Configure:$heightDP")
    Log.d("SCREEN DPI", "Configure:$densityDpi")


    var location by remember { mutableStateOf("") }
    var floorValue by remember { mutableStateOf("") }
    var lineValue by remember { mutableStateOf("") }
    var stationValue by remember { mutableStateOf("") }
    var G0F0L0S0Value by remember { mutableStateOf(" F L S") }


    /*  Row {
            Box(modifier = Modifier.width(widthdp)){
                Image(painter = painterResource(id = R.drawable.bg_background),
                    contentDescription = "Blue Background",
                    contentScale = ContentScale.Crop,
                )

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
                    .padding(MaterialTheme.dimens.padding),
                    verticalArrangement = Arrangement.SpaceBetween){

                    Column {
                        Image(
                            modifier = Modifier
                                .width(widthDP / 6f),
                            contentScale = ContentScale.FillWidth,
                            painter = painterResource(id = R.drawable.interfacelogo),
                            contentDescription = "Interface Logo"
                        )

                        Text(
                            modifier = Modifier.padding(MaterialTheme.dimens.topPadding),
                            text = "Digital Display",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                color = paleWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppinsregular
                            )
                        )
                    }

                    Text(
                        text = "Developed by Cellus Tech India",
                        style = TextStyle(fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = pureWhite,
                            fontFamily = nk)
                    )

                }
            }
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(text = "Enter Station ID",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = lightBlack,
                            fontFamily = nkbold)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LaunchedEffect(location,floorValue, lineValue, stationValue) {
                    val newG = location.takeIf { it.isNotBlank() } ?: "0"
                    val newF = floorValue.takeIf { it.isNotBlank() } ?: "0"
                    val newL = lineValue.takeIf { it.isNotBlank() } ?: "0"
                    val newS = stationValue.takeIf { it.isNotBlank() } ?: "0"
                    val newValue = "$newG F$newF L$newL S$newS"
                    G0F0L0S0Value = newValue

                }

                Text(text = G0F0L0S0Value,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = orange,
                        fontFamily = nk
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row {
                    EnterHereTextField(
                        text = location,
                        label = "Enter Location",
                        onTextChange = {  newText ->
                            location = newText} ,
                        color = pureBlack ,
                        maxLength = 3,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = floorValue,
                        label = "Enter Floor No.",
                        onTextChange = { newText ->
                            floorValue = newText.filter { it.isDigit() } } ,
                        color = pureBlack ,
                        maxLength = 2 ,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = lineValue,
                        label = "Enter line No.",
                        onTextChange = { newText ->
                            lineValue = newText.filter { it.isDigit() } } ,
                        color = pureBlack ,
                        maxLength = 2 ,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = stationValue,
                        label = "Enter Station No.",
                        onTextChange = { newText ->
                            stationValue = newText.filter { it.isDigit() } } ,
                        color = pureBlack ,
                        maxLength = 2 ,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )
                }

                Column(modifier = Modifier.fillMaxWidth(0.4f)
                    .padding(top = MaterialTheme.dimens.topPadding),
                    horizontalAlignment = Alignment.End){

                    CustomRoundedButton(onClick = {

                        mainViewModel.saveStationValue(G0F0L0S0Value)
                        Log.d("Shared Value", G0F0L0S0Value)

                        mainViewModel.floorNum = G0F0L0S0Value.split(" ").take(2).joinToString(" ")
                        Log.d("FLOOR VALUE", mainViewModel.floorNum)

                        navController.navigate(LOGIN)

                    }, text = "Continue")

                }
            }
        }*/
    /*    lateinit var webSocket: WebSocket
    val message = remember { mutableStateOf("No message received yet") }
    val url = "ws://13.233.194.200:5000"
    val eventName = "update_csp_notification_status"
    val messageToSend = "check_station_csp_status: G01 F02 L02 S01"

    LaunchedEffect(Unit) {
        val client = OkHttpClient.Builder()
            .pingInterval(15, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(url)
            .build()
        showLogs("WEBSOCKET","building")

        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                val keyValuePairMessage = "{\"check_station_csp_status\":\"G01 F02 L02 S01\"}"
                webSocket.send(keyValuePairMessage)
                showLogs("WEBSOCKET","open")

            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Assuming the message is in JSON format and has the eventName
                if (text.contains(eventName)) {
                    message.value = text
                }
                showLogs("RECEVED WEBSOCKET",text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Handle binary messages if needed
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                message.value = "Error: ${t.message}"
            }
        }

        webSocket = client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()
    }

    WebSocketMessageScreen(message.value)*/

    Row() {

    Button(modifier = Modifier.width(200.dp).height(250.dp), onClick = {
        showLogs(
            "SOCKET INFO:",
            SocketManager.isConnected().toString()
        )
    }) {

    }



        Button(modifier = Modifier.width(200.dp).height(250.dp), onClick = {
            SocketManager.connect()
        }) {

        }



}
}


/*
@Composable
fun WebSocketMessageScreen(message: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Received Message:")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message)
        }
    }
}

*/



object SocketManager {
    private lateinit var socket: Socket

    fun initSocket() {
        try {
            val opts = IO.Options()
            opts.forceNew = true
            opts.transports = arrayOf("websocket")
            socket = IO.socket("http://192.168.1.3:5000",opts) // Use your WebSocket URL
        } catch (e: URISyntaxException) {
            Log.e("SocketManager", "Socket URI Syntax Exception", e)
        }catch (e:Exception){
            showLogs("Socket error:", e.printStackTrace().toString())
        }


    }

    fun connect() {
        socket.connect()
        showLogs("SOCKET: ","its connecting")

        socket.on(Socket.EVENT_CONNECT) {
            showLogs("SOCKET:", "Connected to server")
        }.on(Socket.EVENT_CONNECT_ERROR) { args ->
            showLogs("SOCKET:", "Connection error: ${args[0]}")
            try {
            showLogs("SOCKET:", "Connection error: ${args[1]}")

            }catch (e:Exception){

            }
        }.on(Socket.EVENT_DISCONNECT) {
            showLogs("SOCKET:", "Disconnected from server")
        }
    }

    fun disconnect() {
        socket.disconnect()
        showLogs("SOCKET: ","its discconnected")

    }

    fun on(event: String, listener: Emitter.Listener) {
        socket.on(event, listener)
        showLogs("SOCKET: ","its event ")
        showLogs("SOCKET: ","${event} ")


    }

    fun emit(event: String, data: String) {
        socket.emit(event, data)
        showLogs("SOCKET: ","its emitting")
        showLogs("SOCKET: ","${event}  ${data}")

    }



    fun isConnected(): Boolean {
        return socket.connected()
    }
}

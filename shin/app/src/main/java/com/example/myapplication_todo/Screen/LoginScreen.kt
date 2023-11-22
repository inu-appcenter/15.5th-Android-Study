package com.example.myapplication_todo.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController : NavController
) {

    var id by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "login",
            fontSize = 30.sp,
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier.width(100.dp),
                text = "아이디",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.weight(1f),
                value = id,
                onValueChange = {
                    id = it
                }
            )
            Button(
                modifier = Modifier
                    .width(120.dp)
                    .padding(horizontal = 10.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color.White
                    ),
                onClick = {}
            ) {
                Text(
                    text = "중복확인",
                    textAlign = TextAlign.Center)
            }
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier.width(100.dp),
                text = "비밀번호",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier.weight(2f),
                value = passwd,
                onValueChange = {
                    passwd = it
                }
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier.width(100.dp),
                onClick={
                    navController.navigate(Screens.AddUserScreen.name)
                }
            ) {
                Text(
                    text = "로그인",
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.width(20.dp))

            Button(
                modifier = Modifier.width(100.dp),
                onClick = { /*TODO*/
                    navController.navigate(Screens.SignUPScreen.name)}

            ) {
                Text(
                    text="회원가입",
                    textAlign = TextAlign.Center
                )

            }
        }

        /*
        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 1.dp)
                    .clickable {
                        navController.navigate(Screens.AddUserScreen.name)
                    },
                text = "회원 가입",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Box(
                Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 1.dp),
                text = "아이디 찾기",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Box(
                Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 1.dp),
                text = "비밀번호 찾기",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }


         */
        Spacer(Modifier.weight(1f))
    }

}

@Preview
@Composable
fun Testlogin() {
    LoginScreen(navController = rememberNavController())
}

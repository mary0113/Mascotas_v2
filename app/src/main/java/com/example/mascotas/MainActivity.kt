package com.example.mascotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mascotas.ui.theme.MascotasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MascotasTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "principal_screen") {
                        composable("principal_screen") {
                            PrincipalScreen(navController)
                        }
                        composable("main_activity2") {
                            MainActivity2Screen(navController)
                        }
                        composable(
                            "detail_screen/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            mascotaId?.let { id ->
                                DetailScreen(navController = navController, mascotaId = id)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PrincipalScreen(navController: NavController) {
    PrincipalImage(
        title = "PET-HEALTH",
        phrase = "La Salud De Tus Mascotas",
        onButtonClick = {
            navController.navigate("main_activity2")
        }
    )
}


@Composable
fun PrincipalText(title: String, phrase: String, modifier: Modifier = Modifier, onButtonClick: () -> Unit){
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
    ){
            Spacer(modifier = Modifier.weight(3f))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge, // Usa la tipografía personalizada
                fontSize = 30.sp,
                lineHeight = 116.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Button(
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF654321)), // Color específico
                 modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                 Text(text = "Comenzar", color = Color.White)
              }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = phrase,
                style = MaterialTheme.typography.bodySmall, // Usa la tipografía personalizada
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
}

@Composable
fun PrincipalImage(title: String, phrase: String, modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    val image = painterResource(R.drawable.principalmascota)
    Box (modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        PrincipalText(
            title = title,
            phrase = phrase,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            onButtonClick = onButtonClick
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PrincipalCardPreview() {
    MascotasTheme {
        PrincipalImage(title = "PET-HEALTH",
            phrase = "La Salud De Tus Mascotas",
        onButtonClick = {}
        )
    }
}



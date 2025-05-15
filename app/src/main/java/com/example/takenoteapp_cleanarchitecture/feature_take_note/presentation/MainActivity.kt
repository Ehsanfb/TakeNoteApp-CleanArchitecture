package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.edit_notes.EditNoteScreen
import com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.take_notes.TakeNotesScreen
import com.example.takenoteapp_cleanarchitecture.ui.theme.TakeNoteAppCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeNoteAppCleanArchitectureTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.TakeNoteScreen.route
                    ) {

                        composable(route = NavigationItem.TakeNoteScreen.route) {
                            TakeNotesScreen(navController = navController)
                        }


                        composable(
                            route = NavigationItem.EditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1

                            EditNoteScreen(
                                navController = navController,
                                noteColor = color
                            )
                        }


                    }

                }
            }
        }
    }
}
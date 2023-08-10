package com.example.pronote

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pronote.db.Note
import com.example.pronote.ui.theme.Extra.AddNote
import com.example.pronote.ui.theme.Extra.NotesList
import com.example.pronote.ui.theme.model.MainViewModel
import com.example.pronote.ui.theme.model.NoteViewModel
import androidx.compose.runtime.remember

object MainDestinations {
    const val NOTES_LIST = "notes-list"
    const val EDIT_NOTE = "edit-note"
    const val NOTE_ID_KEY = "noteId"
    const val NOTE_MODEL_KEY = "note"
    const val NOTE_EDIT_DELETE = "delete-note"
}   //end of MainDestinations


@Composable


@SuppressLint("ComposableDestinationInComposeScope")
@ExperimentalAnimationApi
fun NavGraph(startDestination: String = MainDestinations.NOTES_LIST) {

    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    //NavHost is a composable that manages the navigation graph for your application.
    // It is composed of a list of composable destinations, represented by composable functions,
    // and the actions that can be taken from one destination to another. The NavHost composable
    // also manages the back stack of destinations, which is the order in which the user has
    // navigated through your application.
    //The navController argument is used to connect the NavGraph to the NavController.
    // The startDestination argument is used to specify the initial destination of the NavGraph.

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        //use of ? in kotlin
        //The safe call operator, represented by the ?.
        // symbol in Kotlin, is used to safely access properties or call methods on an object that might be null.
        // It helps prevent null pointer exceptions and provides a concise way to handle nullable values.

        //The HiltViewModelFactory is a factory class provided by the
        // Hilt library that is used to create instances of ViewModels in a Hilt-enabled application.
        // It is used in the NavGraph composable function to create an instance of the MainViewModel.

        //local context
        //The LocalContext.current is passed as the first parameter, which provides the context
        // for creating the ViewModel. The it parameter represents the current composable's
        // CompositionLocalProvider, which is used to retrieve the ViewModelStoreOwner.
        composable(MainDestinations.NOTES_LIST) {
            val vm: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            NotesList(vm = vm, actions.editNote)
            what is the
        }
         //The route parameter specifies the route for this destination.
        // It uses string interpolation to include the MainDestinations.
        // EDIT_NOTE constant and the MainDestinations.NOTE_ID_KEY constant as part of the route.
        // The {} syntax indicates a dynamic parameter in the route, and the value of MainDestinations.NOTE_ID_KEY will be substituted in the route
        composable(
            route = "${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_MODEL_KEY}}",
            arguments = listOf(navArgument((MainDestinations.NOTE_MODEL_KEY)) {type = NavType.ParcelableType(Note::class.java) })
        ) {
            val vm = hiltNavGraphViewModel<NoteViewModel>(backStackEntry = it)

            val note = it.arguments?.getParcelable<Note>(MainDestinations.NOTE_MODEL_KEY)
                ?: throw IllegalStateException("'note' shouldn't be null")

            vm.getNoteId(note.id)
            AddNote(vm, actions.upPress)
        }

        //The route parameter specifies the route for this destination.
        // It uses string interpolation to include the MainDestinations.
        // EDIT_NOTE constant and the MainDestinations.NOTE_ID_KEY constant as part of the route.
        // The {} syntax indicates a dynamic parameter in the route, and the value of MainDestinations.NOTE_ID_KEY will be substituted in the route
        composable(
            route = "${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_MODEL_KEY}}",
            arguments = listOf(navArgument((MainDestinations.NOTE_MODEL_KEY)) {type = NavType.ParcelableType(Note::class.java) })
        ) {
            val vm = hiltNavGraphViewModel<NoteViewModel>(backStackEntry = it)

            val note = it.arguments?.getParcelable<Note>(MainDestinations.NOTE_MODEL_KEY)
                ?: throw IllegalStateException("'note' shouldn't be null")

            vm.getNoteId(note.id)
            AddNote(vm, actions.upPress)
        }

        //The route parameter specifies the route for this destination.
        // It uses string interpolation to include the MainDestinations.
        // EDIT_NOTE constant and the MainDestinations.NOTE_ID_KEY constant as part of the route.
        // The {} syntax indicates a dynamic parameter in the route, and the value of MainDestinations.NOTE_ID_KEY will be substituted in the route
        composable(
            route = "${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.NOTE_ID_KEY)) {type = NavType.IntType })
        ) {
         val vm = hiltNavGraphViewModel<NoteViewModel>(backStackEntry = it)

            val noteId = it.arguments?.getInt(MainDestinations.NOTE_ID_KEY)
                ?: throw IllegalStateException("'noteId' shouldn't be null")
        // and the value of MainDestinations.NOTE_ID_KEY will be substituted in the route
        composable(
            route = "${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.NOTE_ID_KEY)) {type = NavType.IntType })
        ) {
         val vm = hiltNavGraphViewModel<NoteViewModel>(backStackEntry = it)

            val noteId = it.arguments?.getInt(MainDestinations.NOTE_ID_KEY)
                ?: throw IllegalStateException("'noteId' shouldn't be null")

            vm.getNote(noteId)
           AddNote(vm, actions.upPress)
        }

    }
}

class MainActions(navController: NavHostController) {

    val editNote: (Note) -> Unit = { note: Note ->
        navController.currentBackStackEntry?.arguments?.putInt(
            MainDestinations.NOTE_MODEL_KEY,
            note.id
        )
        navController.navigate("${MainDestinations.EDIT_NOTE}/${note.id}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

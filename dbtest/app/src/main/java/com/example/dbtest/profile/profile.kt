package com.example.dbtest.database
import android.app.Activity
import java.util.Locale

import androidx.compose.runtime.Composable
import android.content.Context
import android.content.res.Configuration
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.compose.ui.platform.LocalContext
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient

fun signOut(googleSignInClient: GoogleSignInClient, context: Context) {
    googleSignInClient.signOut()
        .addOnCompleteListener {
            // Sign-out was successful
            Toast.makeText(context, "Signed out successfully", Toast.LENGTH_SHORT).show()
            // Perform additional actions after sign-out if needed
        }
        .addOnFailureListener { e ->
            // Sign-out failed
            Toast.makeText(context, "Sign-out failed: ${e.message}", Toast.LENGTH_SHORT).show()
            // Handle failure scenario if needed
        }
}
@Composable
fun GoogleSignInButton(googleSignInClient: GoogleSignInClient) {
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity
    val signInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result of the sign-in process here
        if (result.resultCode == Activity.RESULT_OK) {

            val account = GoogleSignIn.getLastSignedInAccount(context)
            val userName = account?.displayName ?: "Unknown"
            Toast.makeText(context, "Welcome $userName", Toast.LENGTH_LONG). show()
            // Handle the account details or navigate to a new screen
        } else {


            Toast.makeText(context, "Sign-in failed", Toast.LENGTH_SHORT).show()
        }
    }

    Button(
        onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                // Add other scopes as needed
                .build()
            val mGoogleApiClient = GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
            val signInIntent = googleSignInClient.signInIntent
            signInLauncher.launch(signInIntent)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(id = R.string.googlesignin))
    }
}
@Composable
fun SignOutButton(googleSignInClient: GoogleSignInClient) {
    val context = LocalContext.current

    Button(
        onClick = {
            signOut(googleSignInClient, context)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Sign Out")
    }
}
@Composable
fun DropdownMenuSampleProfile(options:List<String>, selectedOption: MutableState<String>) {
    Box(modifier = Modifier){
        var expanded by remember { mutableStateOf(false) }
        Button(onClick = { expanded = true }) {
            Text(selectedOption.value)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    {Text(text = option)},
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    })
            }
        }

    }
}




@Composable
fun ProfileScreen(googleSignInClient: GoogleSignInClient) {






    val userName = stringResource(id = R.string.hellouser)
    val userEmail = stringResource(id = R.string.hellouser2)
    val userBio = stringResource(id = R.string.profileblurb)

    var selectedType by remember { mutableStateOf("") }

    val langOptions = listOf(
        stringResource(id = R.string.French),
        stringResource(id = R.string.Chinese)
    )

    // Your content here...

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // User Name
        Text(
            text = userName,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // User Email
        Text(
            text = userEmail,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Bio
        Text(
            text = userBio,
            color = Color.Black,
            maxLines = 30,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))
        var currentLocale by remember { mutableStateOf(Locale.getDefault()) }
        var locale: Locale = Locale.ENGLISH
        if (selectedType == "Chinese") {
            locale = Locale("zh")
        } else if (selectedType == "French") {
            locale = Locale("fr")
        } else if (selectedType == "English") {
            locale = Locale("en")
        }

        //val onLocaleSelected: (Locale) -> Unit = { selectedType ->
        //  if (locale != currentLocale) {
        //    val context = LocalContext.current
        //  updateLocale(context, selectedType)
        //currentLocale = locale
        //}
        //}

        //DropdownMenuSampleProfile(
        //  langOptions,
        // mutableStateOf(""),
        //onLocaleSelected
        //)




        Spacer(modifier = Modifier.height(16.dp))

        // Google Sign-In Button
        GoogleSignInButton(googleSignInClient)
        SignOutButton(googleSignInClient = googleSignInClient)
    }


    fun updateLocale(context: Context, locale: Locale) {
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
@Composable
fun ProfilePage(context: Context, taskViewModel: TaskViewModel, modifier: Modifier) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        // Add more scopes if needed
        .build()

    val googleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        ProfileScreen(googleSignInClient)
    }
}

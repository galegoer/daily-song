package com.mongodb.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.mongodb.app.data.RealmSyncRepository
import com.mongodb.app.presentation.home.HomeViewModel
import com.mongodb.app.ui.home.HomePage
import com.mongodb.app.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class ComposeHomeActivity : ComponentActivity() {

    private val repository = RealmSyncRepository { _, error ->
        // Sync errors come from a background thread so route the Toast through the UI thread
        lifecycleScope.launch {
            // Catch write permission errors and notify user. This is just a 2nd line of defense
            // since we prevent users from modifying someone else's tasks
            // TODO the SDK does not have an enum for this type of error yet so make sure to update this once it has been added
            if (error.message?.contains("CompensatingWrite") == true) {
                Toast.makeText(this@ComposeHomeActivity, getString(R.string.permissions_error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel.factory(repository, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            homeViewModel.event
//                .collect {
//                    Log.i(TAG(), "Tried to modify or remove a task that doesn't belong to the current user.")
//                    Toast.makeText(
//                        this@ComposeHomeActivity,
//                        getString(R.string.permissions_warning),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//        }

        setContent {
            MyApplicationTheme {
//                Text("hello")
                HomePage(homeViewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.close()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview2() {
    MyApplicationTheme {
//        HomePage()
    }
}

package com.teamoffroad.feature.explore.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExplorePermissionRejectedHandler(
    context: Context,
    navigateToHome: (String?) -> Unit,
    updatePermission: (Boolean) -> Unit,
) {
    Toast.makeText(context, stringResource(R.string.explore_location_permission_failed), Toast.LENGTH_SHORT).show()
    updatePermission(false)
    navigateToHome(null)
}
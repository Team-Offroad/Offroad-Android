package com.teamoffroad.feature.home.presentation.component.download

data class PermissionData(
    val permissionText: String,
    var isChecked: Boolean = false,
    var isGranted: Boolean = false
)

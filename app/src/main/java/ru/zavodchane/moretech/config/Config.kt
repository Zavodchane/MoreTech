package ru.zavodchane.moretech.config

// Location
const val LOCATION_RETRIEVAL_INTERVAL = 1000L

// Map
const val INITIAL_ZOOM = 17.0
const val MAX_CLUSTERING_ZOOM_LEVEL = 1 // Маленькое значение для отключения

// Request codes
const val RESOLUTION_REQUEST_CODE = 0x1

// Constants
const val AVG_SERVICE_TIME_MINS = 5.5

//val activity = LocalContext.current as Activity
//val mapsIntent = Intent(
//   Intent.ACTION_VIEW,
//   Uri.parse("https://www.google.com/maps/search/?api=1&query=${building.latitude}%2C${building.longitude}")
//)
//activity.startActivity(mapsIntent)
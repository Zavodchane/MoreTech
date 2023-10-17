package ru.zavodchane.moretech.data.util

import android.content.Context
import com.google.gson.Gson
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.vtbbuilding.VTBBuilding
import ru.zavodchane.moretech.data.vtbbuilding.VTBBuildingActualList
import java.io.InputStreamReader

fun getBuildings(ctx : Context): ArrayList<VTBBuilding> {
   val jsonStream = ctx.resources.openRawResource(R.raw.vtb_building_data)
   val reader = InputStreamReader(jsonStream)
   return Gson().fromJson(reader, VTBBuildingActualList::class.java)
}
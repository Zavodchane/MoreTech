package ru.zavodchane.moretech.data.util

import android.util.Log
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.maxNonNormWorkload
import ru.zavodchane.moretech.minNonNormWorkload

fun getMaxMinWorkload(buildings : List<VTBBuilding>) {
   for (building in buildings) {
      val workloadFloat = building.getActualNonNormalizedWorkload()
      Log.i("WorkloadFloat", "$workloadFloat")
      if (workloadFloat > maxNonNormWorkload) maxNonNormWorkload = workloadFloat
      if (workloadFloat < minNonNormWorkload) minNonNormWorkload = workloadFloat
   }
}
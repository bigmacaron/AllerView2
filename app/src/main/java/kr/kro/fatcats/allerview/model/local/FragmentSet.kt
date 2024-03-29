package kr.kro.fatcats.allerview.model.local

import androidx.annotation.IdRes
import kr.kro.fatcats.allerview.R

sealed class FragmentSet(
    @IdRes val resId: Int
) {
    object MainFragment : FragmentSet(R.id.searchFragment)
    object SplashFragment : FragmentSet(R.id.splashFragment)
    object SearchResultFragment : FragmentSet(R.id.SearchResultFragment)
    object MyAllergyCheckFragment : FragmentSet(R.id.myAllergyCheckFragment)
}
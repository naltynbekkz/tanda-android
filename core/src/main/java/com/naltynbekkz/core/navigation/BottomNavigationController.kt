package com.naltynbekkz.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationController(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int,
    private val navGraphProvider: Map<Int, Int>,
    appStartDestinationId: Int,
    private val bottomNavigationView: BottomNavigationView,
    savedInstanceState: Bundle?,
) {

    private var navigationBackStack = ArrayList<Int>().apply {
        if (savedInstanceState != null && savedInstanceState.containsKey(BOTTOM_NAV_BACKSTACK_KEY)) {
            addAll((savedInstanceState[BOTTOM_NAV_BACKSTACK_KEY] as IntArray).toTypedArray())
        }
    }

    init {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            onNavigationItemSelected(it.itemId)
        }

        bottomNavigationView.setOnNavigationItemReselectedListener {

            val navHostFragment = fragmentManager.findFragmentById(containerId)!!
            val navController = navHostFragment.findNavController()

            if (!navController.popBackStack(navController.graph.startDestination, false)) {
                val currentFragment = navHostFragment.childFragmentManager.fragments.last()
                if (currentFragment is LandingFragment) {
                    currentFragment.navigationItemReselected()
                }
            }
        }

        if (savedInstanceState == null) {
            onNavigationItemSelected(appStartDestinationId)
        }
    }

    private fun onNavigationItemSelected(itemId: Int): Boolean {

        // Replace fragment representing a navigation item
        val fragment = fragmentManager.findFragmentByTag(itemId.toString())
            ?: NavHostFragment.create(
                navGraphProvider[itemId] ?: error("No navGraph provided for $itemId")
            )

        fragmentManager.beginTransaction()
            .replace(containerId, fragment, itemId.toString())
            .addToBackStack(null)
            .commit()

        // Add to back stack
        navigationBackStack.apply {
            remove(itemId)
            add(itemId)
        }

        // Update checked icon
        bottomNavigationView.menu.findItem(itemId).isChecked = true

        return true
    }

    fun onBackPressed(): Boolean {
        val navHostFragment = fragmentManager.findFragmentById(containerId)!!

        val currentFragment = navHostFragment.childFragmentManager.fragments.last()

        return when {
            currentFragment is NestedGraphFragment && currentFragment.nestedGraphBackPress() -> true
            navHostFragment.findNavController().popBackStack() -> true
            navigationBackStack.size > 1 -> {

                navigationBackStack.removeLast()

                val itemId: Int = navigationBackStack.last()

                val fragment = fragmentManager.findFragmentByTag(itemId.toString())
                    ?: NavHostFragment.create(
                        navGraphProvider[itemId] ?: error("No navGraph provided for $itemId")
                    )

                fragmentManager.beginTransaction()
                    .remove(navHostFragment)
                    .replace(containerId, fragment, itemId.toString())
                    .setReorderingAllowed(true)
                    .commit()

                // Update checked icon
                bottomNavigationView.menu.findItem(itemId).isChecked = true

                return true
            }
            else -> false
        }
    }

    companion object {
        const val BOTTOM_NAV_BACKSTACK_KEY = "bottom_nav_backstack"
    }

    fun getNavigationBackStackIntArray() = navigationBackStack.toIntArray()

}


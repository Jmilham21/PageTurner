package com.jmilham.pageturner.activities


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jmilham.pageturner.R
import com.jmilham.pageturner.databinding.ActivityMainBinding
import com.jmilham.pageturner.helper.navigation.NavigationHelper
import com.jmilham.pageturner.helper.screen.KeyboardHelper
import com.jmilham.pageturner.helper.screen.KeyboardHelper.hideKeyboard


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Suppressing: TODO implement for accessibility
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        navView.setupWithNavController(navController)

        binding.settingImage.setOnClickListener {
            showPopup(it)
        }

        // handle the main click area
        binding.search.setOnClickListener {
            NavigationHelper.sendToActivity(this, SearchActivity(), null)
        }
        binding.search.setOnTouchListener(KeyboardHelper.noActionTouch)
    }

    /***
     * Imitate Toolbar inflation with custom menu.
     *
     * Handles selection of items inside the method
     */
    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.more_tab_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings_home -> {

                }
                R.id.settings_lists -> {

                }
                R.id.settings_new -> {

                }
            }
            true
        }
        popup.show()
    }

}
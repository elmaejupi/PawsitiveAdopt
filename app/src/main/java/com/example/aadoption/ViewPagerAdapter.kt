package com.example.aadoption


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter : FragmentStateAdapter {

    constructor(fragment: AnimalActivity) : super(fragment)


    override fun getItemCount(): Int {
        return 4
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RabbitFragment()
            1 -> DogsFragment()
            2 -> CatFragment()
            3 -> BirdFragment()
            else -> RabbitFragment()
        }
    }


}
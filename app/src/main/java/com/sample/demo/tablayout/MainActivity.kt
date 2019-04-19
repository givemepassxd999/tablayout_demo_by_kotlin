package com.sample.demo.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pager_item.view.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs.addTab(tabs.newTab().setText("Tab 1"))
        tabs.addTab(tabs.newTab().setText("Tab 2"))
        tabs.addTab(tabs.newTab().setText("Tab 3"))
        viewpager.adapter = SamplePagerAdapter()
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    class SamplePagerAdapter: PagerAdapter() {
        override fun getCount(): Int {
            return 3
        }

        override fun isViewFromObject(view: View, o: Any): Boolean {
            return o === view
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Item " + (position + 1)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context).inflate(R.layout.pager_item, container, false)
            container.addView(view)
            view.item_title.text = getPageTitle(position)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            container.removeView(any as View)
        }
    }
}

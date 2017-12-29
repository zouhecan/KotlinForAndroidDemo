package com.example.zouhecan.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity(){

    private val buildingList = ArrayList<Building>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        add_edit.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                add_button.isSelected = !add_edit.text.isEmpty()
            }
        })
        add_button.setOnClickListener{
            addBuilding()
            Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show()
            add_edit.setText("")
        }
    }

    private fun initRecyclerView(){
        building_recycler_view.layoutManager = LinearLayoutManager(this)
        building_recycler_view.adapter = BuildingListAdapter(buildingList, object : BuildingListAdapter.ItemActionListener{
            override fun onItemClick(v: View, tag: Int) {
                toast("${buildingList.get(tag).buildingNum}")
            }
            override fun onItemLongClick(v: View, tag: Int) {
                alert ("确定删除${buildingList.get(tag).buildingNum}幢吗？"){
                    positiveButton("确定"){
                        buildingList.removeAt(tag)
                        building_recycler_view.adapter.notifyDataSetChanged()
                        longToast("删除成功")
                    }
                    negativeButton("取消"){}//不用写取消dialog的逻辑
                }.show()
            }
        })
    }

    private fun addBuilding(){
        val buildingData = add_edit.text.toString()
        val building = Building(buildingData)
        buildingList.add(building)
        building_recycler_view.adapter.notifyDataSetChanged()
    }

}

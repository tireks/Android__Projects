package com.tirex_projs.testfocusstart.utilits

import android.content.SharedPreferences

class AppHistoryWorker(
    private val preferencesStorage: SharedPreferences,
    private val stringsArr: ArrayList<String>,
    private val editorStorage: SharedPreferences.Editor,
    private val adapter: AppArrayAdapter<String>
)
{
    fun importHistory() {
        if (preferencesStorage.contains("SIZE") && (preferencesStorage.getInt("SIZE", 0) != 0)) {
            var historySize: Int = preferencesStorage.getInt("SIZE", 0)
            var counter = 0
            var tempString: String = ""
            while (counter < historySize) {
                if (preferencesStorage.contains("SLOT${counter + 1}")) {
                    tempString =
                        preferencesStorage.getString("SLOT${counter + 1}", "111").toString()
                    //stringsArr.add(tempString)
                    adapter.add(tempString)
                    adapter.notifyDataSetChanged()
                }
                counter++
            }
        }
    }

    fun exportHistory(cardNum: String) {
        var currentSize: Int = 0
        if (!stringsArr.contains(cardNum)) {
            adapter.add(cardNum)
            adapter.notifyDataSetChanged()
            if (preferencesStorage.contains("SIZE")) {
                currentSize = preferencesStorage.getInt("SIZE", 0)
                currentSize++
                editorStorage.putInt("SIZE", currentSize)

            } else {
                currentSize = 1
                editorStorage.putInt("SIZE", 1)
            }
            editorStorage.putString("SLOT${currentSize}", cardNum)
            editorStorage.commit()
        }


    }

    fun deleteHistory() {
        var currentSize: Int = 0
        if (preferencesStorage.contains("SIZE")) {
            currentSize = preferencesStorage.getInt("SIZE", 0)
            editorStorage.putInt("SIZE", 0)
        }
        while (currentSize > 0) {
            if (preferencesStorage.contains("SLOT${currentSize}")) {
                editorStorage.remove("SLOT${currentSize}")
                stringsArr.remove(stringsArr[currentSize - 1])
                adapter.notifyDataSetChanged()
            }
            currentSize--
        }
        editorStorage.commit()
    }

}
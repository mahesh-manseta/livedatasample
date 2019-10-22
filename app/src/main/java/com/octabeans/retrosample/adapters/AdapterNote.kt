package com.octabeans.retrosample.adapters

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octabeans.retrosample.R
import com.octabeans.retrosample.data.ResNote
import java.text.ParseException
import java.text.SimpleDateFormat

class AdapterNote(val context: Context, val items: List<ResNote>) :
    RecyclerView.Adapter<AdapterNote.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note: ResNote = items.get(position)
        holder.note.text = note.note

        // Displaying dot from HTML character code
        holder.dot.text = Html.fromHtml("&#8226;")

        // Changing dot color to random color
        holder.dot.setTextColor(getRandomMaterialColor("400"))

        // Formatting and displaying timestamp
        if (note.timestamp != null) {
            holder.timestamp.text = formatDate(note.timestamp)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var note: TextView

        internal var dot: TextView

        internal var timestamp: TextView

        init {
            note = view.findViewById(R.id.note)
            dot = view.findViewById(R.id.dot)
            timestamp = view.findViewById(R.id.timestamp)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Chooses random color defined in res/array.xml
     */
    private fun getRandomMaterialColor(typeColor: String): Int {
        var returnColor = Color.GRAY
        val arrayId =
            context.resources.getIdentifier("mdcolor_$typeColor", "array", context.packageName)

        if (arrayId != 0) {
            val colors = context.resources.obtainTypedArray(arrayId)
            val index = (Math.random() * colors.length()).toInt()
            returnColor = colors.getColor(index, Color.GRAY)
            colors.recycle()
        }
        return returnColor
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private fun formatDate(dateStr: String): String {
        try {
            val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = fmt.parse(dateStr)
            val fmtOut = SimpleDateFormat("MMM d")
            return fmtOut.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }
}
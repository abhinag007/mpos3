package com.tech.mpos.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.tech.mpos.R
import com.tech.mpos.transactionResponse.Transaction
import kotlinx.android.synthetic.main.transaction.view.*
import java.util.*


class ListAdapter(
    private val context: Context?,
    private val transactionList: List<Transaction>,/* private val mRowLayout: Int*/
) : RecyclerView.Adapter<ListAdapter.TransactionViewHolder>() {


    class TransactionViewHolder(transactionView: View) : RecyclerView.ViewHolder(transactionView) {
        var status = transactionView.findViewById<TextView>(R.id.title_tv);
        var statusColor = transactionView.findViewById<ImageView>(R.id.transactionStatus_iv);
        var date = transactionView.findViewById<TextView>(R.id.transactionDateList_tv);
        var icon = transactionView.findViewById<ImageView>(R.id.statusImage_iv);
        var transactionAmount =
            transactionView.findViewById<TextView>(R.id.transactionAmountList_tv);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.date.text = transaction.createdAt.substring(0,transaction.createdAt.indexOf('T'))
        holder.status.text = transaction.status.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }


//            holder.transactionAmount.setTextColor(Color.BLACK)

        if (holder.status.text.equals("Failed")) {
        /*    holder.statusColor.drawable.setColorFilter(
                R.color.redColor,
                PorterDuff.Mode.SRC_ATOP
            )*/

//            val unwrappedDrawable = AppCompatResources.getDrawable(context!!, R.drawable.paying_curve_shape)
//            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
//            DrawableCompat.setTint(wrappedDrawable, Color.RED)
            holder.statusColor.setBackgroundResource(R.drawable.paying_curve_shape_red)
            holder.icon.setBackgroundResource(R.drawable.ic_close)
            holder.transactionAmount.setTextColor(Color.RED)
        }
        else {
//            val unwrappedDrawable = AppCompatResources.getDrawable(context!!, com.tech.mpos.R.drawable.paying_curve_shape)
//            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
//            DrawableCompat.setTint(wrappedDrawable, R.color.primary)
//            holder.statusColor.drawable.setColorFilter(R.color.primary, PorterDuff.Mode.SRC_ATOP)
            holder.statusColor.setBackgroundResource(R.drawable.paying_curve_shape_green)

            holder.icon.setBackgroundResource(R.drawable.check_icon)
            holder.transactionAmount.setTextColor(Color.parseColor("#ff266342"))
        }


    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
}

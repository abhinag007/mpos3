package com.tech.mpos.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tech.mpos.MainActivity
import com.tech.mpos.R
import com.tech.mpos.transactionResponse.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ListAdapter(
    private val context: Context?,
    private val transactionList: List<Transaction>,/* private val mRowLayout: Int*/
) : RecyclerView.Adapter<ListAdapter.TransactionViewHolder>() {


    class TransactionViewHolder(transactionView: View) : RecyclerView.ViewHolder(transactionView) {
        var status = transactionView.findViewById<TextView>(R.id.title_tv);
//        var statusColor = transactionView.findViewById<ImageView>(R.id.transactionStatus_iv);
        var date = transactionView.findViewById<TextView>(R.id.transactionDateList_tv);
//        var icon = transactionView.findViewById<ImageView>(R.id.statusImage_iv);
        var transactionAmount =
            transactionView.findViewById<TextView>(R.id.transactionAmountList_tv);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]

        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

        val date = LocalDateTime.parse(transaction.createdAt, secondApiFormat)

        holder.date.text = "${date.dayOfMonth} ${date.month}, ${date.format(dateTimeFormatter)}"
//        holder.date.text = transaction.createdAt/*.substring(0,transaction.createdAt.indexOf('T'))*/
        holder.transactionAmount.text = transaction.amount.toString()

        if (transaction.status.equals("success")) {
            holder.status.text = "Success From XXXX ${transaction.customer.cardNo.substring(transaction.customer.cardNo.length-4)}"
        }
        else{
            holder.status.text = "Failed From XXXX ${transaction.customer.cardNo.substring(transaction.customer.cardNo.length-4)}"
        }

        /*if (holder.status.text.equals("Failed")) {

            holder.statusColor.setBackgroundResource(R.drawable.paying_curve_shape_red)
            holder.icon.setBackgroundResource(R.drawable.ic_close)
            holder.transactionAmount.setTextColor(Color.RED)
        }

         else {
            holder.statusColor.setBackgroundResource(R.drawable.paying_curve_shape_green)

            holder.icon.setBackgroundResource(R.drawable.check_icon)
            holder.transactionAmount.setTextColor(Color.parseColor("#ff266342"))
        }*/

        holder.itemView.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("position","$position")
            context?.startActivity(intent)
//            MainActivity().replaceFragment(RecentTransactionFragment(),1)
        }

       /* holder.itemView.setOnClickListener(View.OnClickListener {
        })*/

    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
}

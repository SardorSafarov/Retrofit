package com.example.retrofit.adaptet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.databinding.ItemUserBinding
import com.example.retrofit.retrofit.models.UserModel

class Adapter(private val context: Context):RecyclerView.Adapter<Adapter.VH>() {

    private var userList:List<UserModel> = listOf()

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        private var bind = ItemUserBinding.bind(itemView)
        fun item(userModel: UserModel) {
            Glide
                .with(context)
                .load(userModel.avatar_url)
                .into(bind.userImage)
            bind.apply {
                txtLogin.text = userModel.login
                btnUrl.text = userModel.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun setData(userList:List<UserModel>){
        this.userList = userList
        notifyDataSetChanged()
    }
}
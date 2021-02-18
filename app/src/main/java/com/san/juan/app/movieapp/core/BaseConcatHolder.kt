package com.san.juan.app.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Dario Carrizo on 9/1/2021
 **/
abstract class BaseConcatHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}
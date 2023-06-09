package com.tiktok.sdk.demo.auth

/*
 *  Copyright (c)  2022 TikTok Pte. Ltd. All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE file in the root directory of this source tree.
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.tiktok.sdk.demo.auth.model.ConfigModel
import com.tiktok.sdk.demo.auth.model.DataModel
import com.tiktok.sdk.demo.auth.model.HeaderModel
import com.tiktok.sdk.demo.auth.model.LogoModel
import com.tiktok.sdk.demo.auth.model.ScopeModel
import com.tiktok.sdk.demo.auth.model.ScopeType
import com.tiktok.sdk.demo.auth.model.ViewType

class MainRecyclerAdapter(
    private var onScopeToggle: (ScopeType, Boolean) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var models: List<DataModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.typeFrom(viewType)) {
            ViewType.SCOPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.scope_item_layout, parent, false)
                return ScopeViewHolder(view)
            }
            ViewType.CONFIG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.scope_item_layout, parent, false)
                return ConfigViewHolder(view)
            }
            ViewType.HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
                HeaderViewHolder(view)
            }
            ViewType.LOGO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.logo_item, parent, false)
                return LogoViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = models[position]) {
            is ScopeModel -> {
                (holder as ScopeViewHolder).let {
                    it.title.text = model.type.value
                    it.subtitle.text = holder.itemView.context.getString(model.descRes)
                    it.toggle.isChecked = model.isOn
                    it.toggle.setOnCheckedChangeListener { _, isOn ->
                        onScopeToggle(model.type, isOn)
                    }
                }
            }
            is ConfigModel -> {
                (holder as ConfigViewHolder).let {
                    it.title.text = model.title
                    it.subtitle.text = model.desc
                    it.toggle.isChecked = model.isOn
                    it.toggle.setOnCheckedChangeListener { _, isOn ->
                        model.toggleListener(isOn)
                    }
                }
            }
            is HeaderModel -> {
                (holder as HeaderViewHolder).apply {
                    header.text = model.headerTitle
                }
            }
            is LogoModel -> {}
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is ConfigViewHolder -> {
                holder.toggle.setOnCheckedChangeListener(null)
            }
            is ScopeViewHolder -> {
                holder.toggle.setOnCheckedChangeListener(null)
            }
            else -> Unit
        }
    }

    override fun getItemCount(): Int = models.size
    override fun getItemViewType(position: Int): Int = models[position].viewType.value

    fun updateModels(models: List<DataModel>) {
        this.models = models
    }
}

class ScopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val subtitle: TextView = view.findViewById(R.id.subtitle)
    val toggle: ToggleButton = view.findViewById(R.id.toggle)
}

class ConfigViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val subtitle: TextView = view.findViewById(R.id.subtitle)
    val toggle: ToggleButton = view.findViewById(R.id.toggle)
}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val header: TextView = view.findViewById(R.id.header)
}

class LogoViewHolder(view: View) : RecyclerView.ViewHolder(view)

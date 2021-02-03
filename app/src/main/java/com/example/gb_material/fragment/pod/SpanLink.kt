package com.example.gb_material.fragment.pod

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat.startActivity


val planets = listOf("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Sun", "Moon")

fun makeSpanLinks(text: String?, context: Context?): SpannableString? {
    if (text != null) {
    val spannable = SpannableString(text)
    val planetsRegEx = Regex(pattern = planets.joinToString(separator = "|"))
    for (found in planetsRegEx.findAll(text)) {
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                context?.apply {
                    startActivity(this, Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://en.wikipedia.org/wiki/${found.value}")
                    }, null)
                }
            }
        }
        spannable.setSpan(clickableSpan,
                found.range.start,
                found.range.last + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
    }
    return spannable
    } else
        return null
}

package com.giuseppesorce.pokemonlist.utils.extensions

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView

/**
 * @author Giuseppe Sorce
 */

fun TextView.colorSpanText(fullText: String, word: String, color: Int, bold: Boolean = false) {
    val spannable = SpannableString(fullText)
    var start = fullText.indexOf(word)
    if(start  >0) {
        try {
            spannable.setSpan(
                ForegroundColorSpan(color),
                start,
                start + word.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (bold) {
                spannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    start,
                    start + word.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        } catch (ex: java.lang.Exception) {

        }
    }

    text = spannable


}
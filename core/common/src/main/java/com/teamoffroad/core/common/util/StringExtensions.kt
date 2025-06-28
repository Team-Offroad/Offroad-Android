package com.teamoffroad.core.common.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import java.util.Stack

fun String.applyBold(): AnnotatedString =
    buildAnnotatedString {
        val boldDelimiter = "**"
        var currentIndex = 0
        val boldStack = Stack<Int>()

        while (currentIndex < this@applyBold.length) {
            val boldStart = this@applyBold.indexOf(boldDelimiter, currentIndex)

            if (boldStart == -1) {
                append(this@applyBold.substring(currentIndex))
                break
            }

            append(this@applyBold.substring(currentIndex, boldStart))

            when (boldStack.isEmpty()) {
                true -> boldStack.push(length)
                false -> {
                    val start = boldStack.pop()
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = length,
                    )
                }
            }

            currentIndex = boldStart + boldDelimiter.length
        }
    }

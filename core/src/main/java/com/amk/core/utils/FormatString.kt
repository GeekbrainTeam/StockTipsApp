package com.amk.core.utils

import kotlin.math.abs

fun formatPrice(price: Double): String =
    if (abs(price) > 999) ".1f"
    else if (abs(price) > 9) ".2f"
    else if (abs(price) > 1) ".3f"
    else ".4f"
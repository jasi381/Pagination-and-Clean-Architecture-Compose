package com.jasmeet.cleanarch.domain

import com.jasmeet.cleanarch.data.Dogs

fun Dogs.toDog(): Dog {
    return Dog(url = url)
}

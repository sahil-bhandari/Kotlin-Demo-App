package com.sahilbhandari.ninjareporter.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserDetails(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var experience: Int = 0,
    var occupation: String = ""
) : RealmObject()
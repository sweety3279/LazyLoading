package com.optcrm.optreporting.app.Model

import com.optcrm.optreporting.app.others.AppEnums.Currency
import com.optcrm.optreporting.app.others.AppEnums.Currency.AED
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory
import com.optcrm.optreporting.app.others.AppEnums.ItemType
import java.util.UUID

data class ItemDetail(val id: String = UUID.randomUUID().toString(), var itemName: String,
    var itemWeight: Double,
    var itemPrice: ArrayList<ItemPrice>, var currency: Currency = AED,
    var itemCategory: ItemCategory,
    var isVeg: Boolean = true,
    var subItems: ArrayList<ItemDetail> = ArrayList())

data class ItemPrice(val id: String = UUID.randomUUID().toString(), var itemPrice: Int,
    var discountedPrice: Int, var isDiscountAvailable: Boolean = true, var itemType: ItemType, var isSelected: Boolean = false)

package com.optcrm.optreporting.app.others

/**
 * Created by swati on 24/9/2018.
 */
class AppEnums {
  enum class Currency(val id: Int, val dataName: String) {
    AED(1001, "AED"), USD(1002, "USD"), INR(1003, "INR");

    companion object {
      fun getDataType(id: Int?): Currency? {
        var dataType: Currency? = null
        when (id) {
          AED.id -> dataType = AED
          USD.id -> dataType = USD
          INR.id -> dataType = INR
        }
        return dataType
      }
    }
  }

  enum class ItemCategory(val id: Int, val dataName: String) {
    COMBO(2001, "Combo"), DRINKS(2002, "Drinks"), CREPES(2003, "Crepes"),
    BURGER(2004, "Burger");

    companion object {
      fun getDataType(id: Int?): ItemCategory? {
        var dataType: ItemCategory? = null
        when (id) {
          COMBO.id -> dataType = COMBO
          DRINKS.id -> dataType = DRINKS
          CREPES.id -> dataType = CREPES
          BURGER.id -> dataType = BURGER
        }
        return dataType
      }
    }
  }

  enum class ItemType(val id: Int, val dataName: String) {
    NORMAL(3001, "Normal"), REGULAR(3002, "Regular"), LARGE(3003, "Large");

    companion object {
      fun getDataType(id: Int?): ItemType? {
        var dataType: ItemType? = null
        when (id) {
          NORMAL.id -> dataType = NORMAL
          REGULAR.id -> dataType = REGULAR
          LARGE.id -> dataType = LARGE
        }
        return dataType
      }
    }
  }
}

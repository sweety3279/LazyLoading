package com.optcrm.optreporting.app.Model

import com.optcrm.optreporting.app.others.AppEnums
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory.BURGER
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory.COMBO
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory.CREPES
import com.optcrm.optreporting.app.others.AppEnums.ItemCategory.DRINKS
import java.util.Random

class AppData {

  companion object {
    fun initAppData() {
      setHomeTabsData()
      setFoodItems()
    }

    private fun setHomeTabsData() {
      homeTabs.add(AppEnums.ItemCategory.COMBO)
      homeTabs.add(AppEnums.ItemCategory.DRINKS)
      homeTabs.add(AppEnums.ItemCategory.CREPES)
      homeTabs.add(AppEnums.ItemCategory.BURGER)
    }

    private fun setFoodItems() {
      foodItems.add(itemMaxicanBurger)
      foodItems.add(itemAmericanoBurger)
      foodItems.add(itemChilliBurger)
      foodItems.add(itemCoke)
      foodItems.add(itemPepsi)
      foodItems.add(itemMangoStrike)
      foodItems.add(itemSweetCreep)
      foodItems.add(itemNachosCreep)
      foodItems.add(itemComboFiesta)
      foodItems.add(itemMaxicanBurgerSummarCombo)
      foodItems.add(itemComboLust)
    }
    
    fun getAllHomeTabs(): ArrayList<ItemCategory> = homeTabs

    fun getAllFoodItems(): List<ItemDetail> = foodItems

    fun getFoodItemsByCategory(itemCategory: ItemCategory) : List<ItemDetail>{
      return foodItems.filter { it.itemCategory == itemCategory }
          .toMutableList()
    }

    private fun getPriceList(): ArrayList<ItemPrice> {
      val itemPrices = ArrayList<ItemPrice>()
      AppEnums.ItemType.values().forEach { itemType ->
        itemPrices.add(ItemPrice(itemPrice = Random().nextInt(99),
            discountedPrice = Random().nextInt(99), itemType = itemType))
      }

      return itemPrices
    }

    private val homeTabs = ArrayList<ItemCategory>()
    private val foodItems = ArrayList<ItemDetail>()

    private const val NAME_COMBO_FIESTA = "Combo Fiesta"
    private const val NAME_COMBO_MAXICAN_WITHCOKE = "Maxican Burger with Coke"
    private const val NAME_COMBO_LUST = "Combo Lust"

    private val itemMaxicanBurger = ItemDetail(itemName = "Maxican Burger",
        itemPrice = getPriceList(), itemWeight = Random(100).nextDouble(), itemCategory = BURGER)
    private val itemAmericanoBurger = ItemDetail(itemName = "Americano Flux Burger",
        itemPrice = getPriceList(), itemWeight = Random(100).nextDouble(), itemCategory = BURGER)
    private val itemChilliBurger = ItemDetail(itemName = "Chilli Burger",
        itemPrice = getPriceList(), itemWeight = Random(100).nextDouble(), itemCategory = BURGER)
    private val itemCoke = ItemDetail(itemName = "Coke", itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = DRINKS)
    private val itemPepsi = ItemDetail(itemName = "Pepsi (Diet)", itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = DRINKS)
    private val itemMangoStrike = ItemDetail(itemName = "Mango Summar Break",
        itemPrice = getPriceList(), itemWeight = Random(100).nextDouble(), itemCategory = DRINKS)
    private val itemSweetCreep = ItemDetail(itemName = "Sweet Creep", itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = CREPES)
    private val itemNachosCreep = ItemDetail(itemName = "Nachos Creep", itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = CREPES)
    private val itemComboFiesta = ItemDetail(itemName = NAME_COMBO_FIESTA,
        itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = COMBO, 
        subItems = getComboFiesta(NAME_COMBO_FIESTA))
    private val itemMaxicanBurgerSummarCombo = ItemDetail(itemName = NAME_COMBO_MAXICAN_WITHCOKE,
        itemPrice = getPriceList(), itemWeight = Random(100).nextDouble(), itemCategory = COMBO,
        subItems = getComboFiesta(NAME_COMBO_MAXICAN_WITHCOKE))
    private val itemComboLust = ItemDetail(itemName = NAME_COMBO_LUST, itemPrice = getPriceList(),
        itemWeight = Random(100).nextDouble(), itemCategory = COMBO,
        subItems = getComboFiesta(NAME_COMBO_LUST))

    private fun getComboFiesta(comboName: String): ArrayList<ItemDetail> {
      val itemDetail = ArrayList<ItemDetail>()

      when (comboName) {
        NAME_COMBO_FIESTA -> {
          itemDetail.add(itemChilliBurger)
          itemDetail.add(itemMangoStrike)
          itemDetail.add(itemNachosCreep)
        }
        NAME_COMBO_MAXICAN_WITHCOKE -> {
          itemDetail.add(itemMaxicanBurger)
          itemDetail.add(itemCoke)
        }
        NAME_COMBO_LUST -> {
          itemDetail.add(itemAmericanoBurger)
          itemDetail.add(itemChilliBurger)
          itemDetail.add(itemPepsi)
          itemDetail.add(itemSweetCreep)
        }
      }
      return itemDetail
    }
  }
}
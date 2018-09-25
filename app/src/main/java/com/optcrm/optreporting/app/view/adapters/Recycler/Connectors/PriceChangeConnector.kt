package com.optcrm.optreporting.app.view.adapters.Recycler.Connectors

import com.optcrm.optreporting.app.Model.ItemPrice

/**
 * Created by swati on 24/9/2018.
 */
interface PriceChangeConnector {
    fun itemPriceChanged(newPrice: ItemPrice)
}
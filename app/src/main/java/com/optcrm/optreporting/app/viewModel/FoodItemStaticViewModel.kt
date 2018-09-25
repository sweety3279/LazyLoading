/*
* Copyright (C) 2017 The Munish/BrighterBrain Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.optcrm.optreporting.app.viewModel

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.FragmentActivity
import com.optcrm.optreporting.app.Model.ItemPrice
import com.optcrm.optreporting.app.persistence.db.MasterDB

/**
 * Created by swati on 24/9/2018.
 */
class FoodItemStaticViewModel(private val activity: FragmentActivity, private val database: MasterDB)
    : BaseViewModel(activity){


    /** ProductViewState Mutable Live Data */
    val selec: MutableLiveData<ItemPrice> = MutableLiveData()
}
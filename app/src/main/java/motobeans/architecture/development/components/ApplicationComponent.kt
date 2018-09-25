package motobeans.architecture.development.components

import android.app.Application
import com.optcrm.optreporting.AppModule
import com.optcrm.optreporting.app.presenter.presenter.MainPresenter
import com.optcrm.optreporting.app.presenter.presenter.TempSyncPresenter
import com.optcrm.optreporting.app.presenter.presenter.MainViewPresenter
import com.optcrm.optreporting.app.view.activity.MainActivity
import com.optcrm.optreporting.app.view.adapters.Recycler.Adapter.ItemRecyclerAdapter
import com.optcrm.optreporting.app.view.adapters.Recycler.Holder.ItemHolder
import com.optcrm.optreporting.app.viewModel.TempViewModel
import com.optcrm.optreporting.app.workers.UtilWorkersTask
import dagger.Component
import motobeans.architecture.customAppComponents.activity.BaseAppCompatActivity
import motobeans.architecture.customAppComponents.jetpack.SuperWorker
import motobeans.architecture.development.modules.NetworkModule
import motobeans.architecture.development.modules.PrimitivesModule
import motobeans.architecture.development.modules.UtilityModule
import javax.inject.Singleton

/**
 * Created by swati on 24/9/2018.
 */
@Singleton
@Component(modules = arrayOf(
    AppModule::class, NetworkModule::class, UtilityModule::class, PrimitivesModule::class
))
interface ApplicationComponent {

    fun inject(app: Application)

    /**
     * Activities
     */
    fun inject(activity: MainActivity)
    fun inject(activity: BaseAppCompatActivity)



    /**
     * Presenters
     */
    fun inject(presenter: MainViewPresenter)
    fun inject(presenter: TempSyncPresenter)
    fun inject(presenter: MainPresenter)


    /**
     * View Model
     */
    fun inject(viewModel: TempViewModel)

    /**
     * Adapters
     */
    fun inject(adapter: ItemRecyclerAdapter)


    /**
     * Holders
     */
    fun inject(other: ItemHolder)

    /**
     * Others
     */
    fun inject(other: SuperWorker)
    fun inject(other: UtilWorkersTask)
}
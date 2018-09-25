package motobeans.architecture.customAppComponents.jetpack

import androidx.work.Worker
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.development.interfaces.ApiProject
import motobeans.architecture.development.interfaces.DataBaseUtil
import javax.inject.Inject

abstract class SuperWorker : Worker() {
    @Inject
    lateinit var dataBase: DataBaseUtil
    @Inject
    lateinit var api: ApiProject

    init {
        ArchitectureApp.instance.component.inject(this)
    }
}
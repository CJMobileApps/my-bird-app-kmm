package di

import MyViewModel
import dependencies.MyRepository
import dependencies.MyRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    //TODO another way single { MyRepositoryImpl(get()) }.bind<MyRepository>()
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)
}

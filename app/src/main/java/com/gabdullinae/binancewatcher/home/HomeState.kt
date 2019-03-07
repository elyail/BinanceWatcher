package com.gabdullinae.binancewatcher.home

sealed class HomeState
object HomeProgress : HomeState()
object HomeShowContent : HomeState()
class HomeError(val errorMessage:String) : HomeState()
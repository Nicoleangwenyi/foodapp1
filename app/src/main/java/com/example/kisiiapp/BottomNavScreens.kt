package com.example.kisiiapp

sealed class BottomNavScreens(
    val title: String,
    val icon: Int = R.drawable.homee,
    val route: String,
){

    object Home: BottomNavScreens(title = "home", icon = R.drawable.homee, route = BottomNavRoutes.HOME.route)
    object Search: BottomNavScreens(title = "search", icon = R.drawable.searchh, route = BottomNavRoutes.SEARCH.route)
    object MyCart: BottomNavScreens(title = "myCart", icon = R.drawable.cart, route = BottomNavRoutes.MYCART.route)
    object Orders:
        BottomNavScreens(title = "orders", icon = R.drawable.bag, route = BottomNavRoutes.ORDERS.route)
    object Account: BottomNavScreens(title = "account",icon = R.drawable.user, route = BottomNavRoutes.ACCOUNT.route)
}


enum class BottomNavRoutes(val route: String){
    HOME("home"),
    SEARCH("search"),
    MYCART("myCart"),
    ORDERS("orders"),
    ACCOUNT("account"),
}
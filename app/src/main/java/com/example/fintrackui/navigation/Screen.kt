package com.example.fintrackui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.fintrackui.R


sealed class Screen(val route: String, @StringRes val resourceId: Int? = R.string.account,
    @DrawableRes val drawableId: Int? = R.drawable.icon_person, ) {
    data object Splash : Screen("splash_screen")
    data object CheckEmail : Screen("check_email")
    data object SignIn : Screen("sign_in_screen")
    data object SignUp : Screen("sign_up_screen")
    data object CheckEmailOtp : Screen("check_email_otp")
    data object PinInput : Screen("pin_input")
    data object AccountSetup : Screen("account_setup")
    data object CreatePassword : Screen("create_password")
    data object Start : Screen("get_started_screen")
    data object CreateBudgetFirst : Screen("create_budget_first_screen")
    data object CreateBudgetSecond : Screen("create_budget_second_screen")
    data object CreateBudgetThird : Screen("create_budget_third_screen")

    data object Home : Screen("home", R.string.home, R.drawable.iconly_home)
    data object Budget : Screen("budget", R.string.budget, R.drawable.icon_pie_menu)
    data object Savings : Screen("savings", R.string.savings, R.drawable.iconly_wallet)
    data object Expenses : Screen("expenses", R.string.expenses, R.drawable.iconly_in_out)
    data object Account : Screen("account", R.string.account, R.drawable.icon_person)

}

package com.example.fintrackui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fintrackui.R
import com.example.fintrackui.presentation.screens.AccountSetupScreen
import com.example.fintrackui.presentation.screens.CheckEmailOtpScreen
import com.example.fintrackui.presentation.screens.OnBoardingScreen
import com.example.fintrackui.presentation.screens.SignInScreen
import com.example.fintrackui.presentation.screens.CheckEmailScreen
import com.example.fintrackui.presentation.screens.CreatePasswordScreen
import com.example.fintrackui.presentation.screens.PinInputScreen
import com.example.fintrackui.presentation.screens.SignUpScreen
import com.example.fintrackui.presentation.screens.SplashScreen
import com.example.fintrackui.presentation.screens.bottom_nav_screens.AccountScreen
import com.example.fintrackui.presentation.screens.bottom_nav_screens.BudgetScreen
import com.example.fintrackui.presentation.screens.bottom_nav_screens.ExpensesScreen
import com.example.fintrackui.presentation.screens.bottom_nav_screens.HomeScreen
import com.example.fintrackui.presentation.screens.bottom_nav_screens.SavingsScreen
import com.example.fintrackui.presentation.screens.create_budget_sequence.CreateBudgetOne
import com.example.fintrackui.presentation.screens.create_budget_sequence.CreateBudgetThree
import com.example.fintrackui.presentation.screens.create_budget_sequence.CreateBudgetTwo
import com.example.fintrackui.ui.theme.Gray
import com.example.fintrackui.ui.theme.Primary


@SuppressLint("NewApi")
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    Scaffold(bottomBar = { MyBottomNav(navController = navController) }, content = { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController, startDestination = Screen.Splash.route) {
            composable(route = Screen.Splash.route) { SplashScreen(navController = navController) }
            composable(route = Screen.SignIn.route) { SignInScreen(navController = navController) }
            composable(route = Screen.Start.route) { OnBoardingScreen(navController = navController) }
            composable(route = Screen.Account.route) { AccountScreen(navController) }
            composable(route = Screen.Expenses.route) { ExpensesScreen(navController) }
            composable(route = Screen.Savings.route) { SavingsScreen(navController) }
            composable(route = Screen.Budget.route) { BudgetScreen(navController) }
            composable(route = Screen.Home.route) { HomeScreen(navController) }
            composable(route = Screen.CreatePassword.route) { CreatePasswordScreen(navController) }
            composable(route = Screen.AccountSetup.route) { AccountSetupScreen(navController) }
            composable(route = Screen.PinInput.route) { PinInputScreen(navController = navController) }
            composable(route = Screen.CheckEmailOtp.route) { CheckEmailOtpScreen(navController = navController) }
            composable(route = Screen.CheckEmail.route) { CheckEmailScreen(navController = navController) }
            composable(route = Screen.SignUp.route) { SignUpScreen(navController = navController) }
            composable(route = Screen.CreateBudgetFirst.route) { CreateBudgetOne(navController = navController) }
            composable(route = Screen.CreateBudgetSecond.route) { CreateBudgetTwo(navController = navController) }
            composable(route = Screen.CreateBudgetThird.route) { CreateBudgetThree(navController = navController) }

        }
    })
}


@Composable
fun MyBottomNav(navController: NavHostController) {
    val bottomItems = listOf(
        Screen.Home,
        Screen.Budget,
        Screen.Savings,
        Screen.Expenses,
        Screen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route in bottomItems.map { item -> item.route }
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier.height(62.dp)
        ) {

            bottomItems.forEach { screen ->
                if (currentDestination != null) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(screen.drawableId!!),
                                contentDescription = stringResource(R.string.screen_label),
                                modifier = Modifier.size(24.dp)
                            )
                        },

                        selected = currentDestination.hierarchy.any { it.route == screen.route },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = false,
                        selectedContentColor = Primary,
                        unselectedContentColor = Gray,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    } else {
        Spacer(modifier = Modifier.width(0.dp))
    }
}
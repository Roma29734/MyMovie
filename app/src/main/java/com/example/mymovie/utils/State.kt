package com.example.mymovie.utils

sealed class State {
    class defaultState : State()
    class compleetState : State()
    class sendingState: State()
    class errorState<T>(val message: T): State()

}
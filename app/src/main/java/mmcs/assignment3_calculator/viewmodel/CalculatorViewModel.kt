package mmcs.assignment3_calculator.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import mmcs.assignment3_calculator.R

class CalculatorViewModel: BaseObservable(), Calculator {
    override var display = ObservableField<String>()

    //#0F4ADF - синий цвет
    //#403C3C - тёмный цвет
    //private var mainText = "" // Основная строка вывода
    //private var subText = "" // Промежуточная строка вывода
    //private val digits: MutableList<String> = mutableListOf()

    override fun addDigit(dig: Int) {
        if(display.get() != null)
            display.set(display.get() + dig.toString())
        else
            display.set("" + dig.toString())
    }

    override fun addPoint() {
        TODO("Not yet implemented")
    }

    override fun addOperation(op: Operation) {
        TODO("Not yet implemented")
    }

    override fun compute() {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}
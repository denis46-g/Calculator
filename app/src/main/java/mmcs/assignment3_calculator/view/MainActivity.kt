package mmcs.assignment3_calculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import mmcs.assignment3_calculator.R
import mmcs.assignment3_calculator.databinding.ActivityMainBinding
import mmcs.assignment3_calculator.viewmodel.Calculator
import mmcs.assignment3_calculator.viewmodel.CalculatorViewModel
import mmcs.assignment3_calculator.viewmodel.Operation
import mmcs.assignment3_calculator.viewmodel.subDisplay

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = CalculatorViewModel()

        mainBinding.viewModel = viewModel

        mainBinding.button0.setOnClickListener{
            viewModel.addDigit(0)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button1.setOnClickListener{
            viewModel.addDigit(1)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button2.setOnClickListener{
            viewModel.addDigit(2)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button3.setOnClickListener{
            viewModel.addDigit(3)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button4.setOnClickListener{
            viewModel.addDigit(4)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button5.setOnClickListener{
            viewModel.addDigit(5)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button6.setOnClickListener{
            viewModel.addDigit(6)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button7.setOnClickListener{
            viewModel.addDigit(7)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button8.setOnClickListener{
            viewModel.addDigit(8)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.button9.setOnClickListener{
            viewModel.addDigit(9)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonPoint.setOnClickListener(){
            viewModel.addPoint()
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonPlus.setOnClickListener(){
            viewModel.addOperation(Operation.ADD)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonMinus.setOnClickListener(){
            viewModel.addOperation(Operation.SUB)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonProd.setOnClickListener(){
            viewModel.addOperation(Operation.MUL)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonDiv.setOnClickListener(){
            viewModel.addOperation(Operation.DIV)
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonEqual.setOnClickListener(){
            viewModel.compute()
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonClear.setOnClickListener(){
            viewModel.clear()
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
        mainBinding.buttonReset.setOnClickListener(){
            viewModel.reset()
            mainBinding.textView0.text = viewModel.display.get()
            mainBinding.textView1.text = subDisplay
        }
    }
}

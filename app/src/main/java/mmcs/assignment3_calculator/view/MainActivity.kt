package mmcs.assignment3_calculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import mmcs.assignment3_calculator.R
import mmcs.assignment3_calculator.databinding.ActivityMainBinding
import mmcs.assignment3_calculator.viewmodel.Calculator
import mmcs.assignment3_calculator.viewmodel.CalculatorViewModel

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
        }
        mainBinding.button1.setOnClickListener{
            viewModel.addDigit(1)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button2.setOnClickListener{
            viewModel.addDigit(2)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button3.setOnClickListener{
            viewModel.addDigit(3)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button4.setOnClickListener{
            viewModel.addDigit(4)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button5.setOnClickListener{
            viewModel.addDigit(5)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button6.setOnClickListener{
            viewModel.addDigit(6)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button7.setOnClickListener{
            viewModel.addDigit(7)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button8.setOnClickListener{
            viewModel.addDigit(8)
            mainBinding.textView0.text = viewModel.display.get()
        }
        mainBinding.button9.setOnClickListener{
            viewModel.addDigit(9)
            mainBinding.textView0.text = viewModel.display.get()
        }
    }
}

private fun <Button> Button.setOnClickListener(addDigit: Unit) {

}

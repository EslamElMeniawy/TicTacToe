package elmeniawy.eslam.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val player1 = ArrayList<Int>()
    private val player2 = ArrayList<Int>()
    private var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btClicked(view: View) {
        val btSelected: Button = view as Button
        var cellId = 0

        when (btSelected.id) {
            R.id.bt1 -> cellId = 1
            R.id.bt2 -> cellId = 2
            R.id.bt3 -> cellId = 3
            R.id.bt4 -> cellId = 4
            R.id.bt5 -> cellId = 5
            R.id.bt6 -> cellId = 6
            R.id.bt7 -> cellId = 7
            R.id.bt8 -> cellId = 8
            R.id.bt9 -> cellId = 9
        }

        playGame(cellId, btSelected)
    }

    private fun playGame(celId: Int, btSelected: Button) {
        // Check the active player
        // Set text and background of button based on active player
        // Add the cell id to the corresponding player list
        // Change active player
        if (activePlayer == 1) {
            btSelected.text = getText(R.string.x)
            btSelected.setBackgroundColor(Color.GREEN)
            player1.add(celId)
            activePlayer = 2
        } else {
            btSelected.text = getText(R.string.o)
            btSelected.setBackgroundColor(Color.YELLOW)
            player2.add(celId)
            activePlayer = 1
        }

        // Disable selected button
        btSelected.isEnabled = false

        // Check winner
        checkWinner()
    }

    private fun checkWinner() {
        var winner = 0

        // Check if player 1 has complete row or column then mark as winner
        if (checkRow(1, player1)
                || checkRow(2, player1)
                || checkRow(3, player1)
                || checkColumn(1, player1)
                || checkColumn(2, player1)
                || checkColumn(3, player1)) {
            winner = 1
        }

        // Check if player 2 has complete row or column then mark as winner
        if (checkRow(1, player2)
                || checkRow(2, player2)
                || checkRow(3, player2)
                || checkColumn(1, player2)
                || checkColumn(2, player2)
                || checkColumn(3, player2)) {
            winner = 2
        }

        // Check if there is a winner
        if (winner != 0) {
            showWinner(winner)
        }
    }

    private fun checkRow(row: Int, list: ArrayList<Int>): Boolean {
        var startIndex = 0

        when (row) {
            1 -> startIndex = 1
            2 -> startIndex = 4
            3 -> startIndex = 7
        }

        if (list.contains(startIndex)
                && list.contains(startIndex + 1)
                && list.contains(startIndex + 2)) {
            return true
        }

        return false
    }

    private fun checkColumn(column: Int, list: ArrayList<Int>): Boolean {
        if (list.contains(column)
                && list.contains(column + 3)
                && list.contains(column + 6)) {
            return true
        }

        return false
    }

    private fun showWinner(id: Int) {
        Toast.makeText(this, getString(R.string.winner, id), Toast.LENGTH_LONG).show()
    }
}

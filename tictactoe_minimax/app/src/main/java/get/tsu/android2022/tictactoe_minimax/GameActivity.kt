package get.tsu.android2022.tictactoe_minimax

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import get.tsu.android2022.tictactoe_minimax.databinding.ActivityGameBinding
import get.tsu.android2022.tictactoe_minimax.utils.MiniMax

class GameActivity : AppCompatActivity() {

    private var currentTurn = 1
    private var gameIsOver = false
    private var isAI = false

    private var firstPlayerScore = 0
    private var secondPlayerScore = 0

    private lateinit var firstPlayerName: String
    private lateinit var secondPlayerName: String

    private var boardList = mutableListOf<Button>()

    private lateinit var minimax: MiniMax;
    private lateinit var sp: SharedPreferences
    private lateinit var activityGameBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityGameBinding = ActivityGameBinding.inflate(layoutInflater)
        activityGameBinding.goBack.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        activityGameBinding.resetBoard.setOnClickListener{
            resetBoard()
        }
        sp = getSharedPreferences("PLAYERS_INFO", Context.MODE_PRIVATE)
        firstPlayerName = sp.getString("YOUR_NAME","ZAURI").toString()
        secondPlayerName = sp.getString("FRIENDS_NAME","LAMARA").toString()
        activityGameBinding.firstPlayerName.text = firstPlayerName
        activityGameBinding.secondPlayerName.text = secondPlayerName
        if(secondPlayerName == "AGENT") {
            isAI = true
            minimax = MiniMax()
        }
        sp.edit().apply{
            remove("YOUR_NAME")
            remove("FRIENDS_NAME")
        }.apply()
        initBoard()
        initLabels()
        setContentView(activityGameBinding.root)
    }


    private fun initLabels(){
        setScoreBoard()
        setLabel()
    }
    private fun initBoard()
    {
        boardList.add(activityGameBinding.a1)
        boardList.add(activityGameBinding.a2)
        boardList.add(activityGameBinding.a3)
        boardList.add(activityGameBinding.b1)
        boardList.add(activityGameBinding.b2)
        boardList.add(activityGameBinding.b3)
        boardList.add(activityGameBinding.c1)
        boardList.add(activityGameBinding.c2)
        boardList.add(activityGameBinding.c3)
    }

    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        if(!gameIsOver) {
            addToBoard(view)
            checkForVictoryAndSetLables()
            if(!gameIsOver && isAI && currentTurn == 0) {
                var buttonAiClicked: Button = addToBoardAI()
                addToBoard(buttonAiClicked)
                checkForVictoryAndSetLables()
            }
        }
    }

    private fun checkForVictoryAndSetLables(){
        var isNoughtVictorious = checkForVictory("O")
        var isCrossVictorious = checkForVictory("X")

        if (isCrossVictorious) {
            gameIsOver = true
            firstPlayerScore++
            setLabel("$firstPlayerName has won")
            setScoreBoard()
        } else if (isNoughtVictorious) {
            gameIsOver = true
            secondPlayerScore++
            setLabel("$secondPlayerName has won")
            setScoreBoard()
        }

        if (!isCrossVictorious && !isNoughtVictorious && fullBoard()) {
            gameIsOver = true
            setLabel("Draw")
        }
    }

    private fun checkForVictory(s: String): Boolean
    {
        if(match(activityGameBinding.a1,s) && match(activityGameBinding.a2,s) && match(activityGameBinding.a3,s))
            return true
        if(match(activityGameBinding.b1,s) && match(activityGameBinding.b2,s) && match(activityGameBinding.b3,s))
            return true
        if(match(activityGameBinding.c1,s) && match(activityGameBinding.c2,s) && match(activityGameBinding.c3,s))
            return true
        if(match(activityGameBinding.a1,s) && match(activityGameBinding.b1,s) && match(activityGameBinding.c1,s))
            return true
        if(match(activityGameBinding.a2,s) && match(activityGameBinding.b2,s) && match(activityGameBinding.c2,s))
            return true
        if(match(activityGameBinding.a3,s) && match(activityGameBinding.b3,s) && match(activityGameBinding.c3,s))
            return true
        if(match(activityGameBinding.a1,s) && match(activityGameBinding.b2,s) && match(activityGameBinding.c3,s))
            return true
        if(match(activityGameBinding.a3,s) && match(activityGameBinding.b2,s) && match(activityGameBinding.c1,s))
            return true

        return false
    }

    private fun match(button: Button, symbol : String): Boolean = button.text == symbol


    private fun resetBoard()
    {
        for(button in boardList)
        {
            button.text = ""
        }
        currentTurn = 1
        setLabel()
    }

    private fun fullBoard(): Boolean
    {
        for(button in boardList)
        {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currentTurn == 1)
        {
            button.text = "X"
            currentTurn = 0
        }
        else{
            currentTurn = 1
            button.text = "O"
        }
        setLabel()
    }

    private fun addToBoardAI(): Button {
        var board = arrayOf(
            charArrayOf('_', '_', '_'),
            charArrayOf('_', '_', '_'),
            charArrayOf('_', '_', '_')
        )

        for (i in 0..8) {
            if(boardList[i].text != "")
            board[i / 3][i % 3] = boardList[i].text.single()
        }
        var agentsMove: MiniMax.Move = minimax.findBestMove(board)!!
        var index = agentsMove.row * 3 + agentsMove.col
        return boardList[index]
    }

    private fun setScoreBoard(){
        activityGameBinding.firstPlayerScore.text = firstPlayerScore.toString()
        activityGameBinding.secondPlayerScore.text = secondPlayerScore.toString()
    }

    private fun setLabel(winner: String)
    {
        activityGameBinding.mainLabel.text = winner
        Handler(Looper.getMainLooper()).postDelayed({
            resetBoard()
            gameIsOver = false
        }, 2000)
    }
    private fun setLabel()
    {
        var turnText = ""
        if(currentTurn == 1)
            turnText = "$firstPlayerName's turn"
        else
            turnText = "$secondPlayerName's turn"

        activityGameBinding.mainLabel.text = turnText
    }


}
/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main() {
    Play()
}

class PlayShow(){
    fun show_game_number(num:String, name:String){
        println()
        println("$num Game")
        println("$name guesses")
    }
    fun show_game_step(step:Int, try_number:Int){               
    	println("Step $step - try number $try_number")        
    }
    fun show_game_win(step:Int, win_number:Int){     
        println("On step $step you got win number $win_number")   
    }
}

class Play(){
    init{
        var Player_1 = Player("P_1")
        var Player_2 = Player("P_2")
        PlayShow().show_game_number("FIRST", Player_2.name)
        play(Player_1, Player_2)
        PlayShow().show_game_number("SECOND", Player_1.name)
        play(Player_2, Player_1)
    }
    fun play (p1:Player, p2:Player){
        var step = 1
        var temp_number = -1
        var check = false
        while(!check){
            temp_number = p2.guess()
            var c = p1.check_think(temp_number)
            if (c == '=')
            	check = true
            else{
                p2.decrease_range(temp_number, c) 
                PlayShow().show_game_step(step, temp_number)
            	step++
            }
        }        
        PlayShow().show_game_win(step, temp_number)
	}
}

class Player(n: String){
	var name = n
    var min = 0
    var max = 100
    var think = (0..100).random()
    
    fun check_think(g:Int):Char{
        if (think != g){
            if (think < g)
        		return '<'
        	else (think > g)
        		return '>'
        }
        else{
            return '='
        }        
    }     
    
    fun guess():Int{
        var g = (min + max)/2
        if (min == 99)
        	g ++
        return g
    }
    
    fun decrease_range(num:Int, state:Char){
        if (state == '>')
			min = num
        if (state == '<')
        	max = num
    }
}

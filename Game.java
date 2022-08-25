package com.board.oops;

public class Game {
    private WorkFlow workFlow;
    private Box[][] board = new Box[10][10];
    public boolean isOver = false;

    public Game(){
        System.out.println("created game");
    }

    public Game(String name1, String name2){
        Util.makeBoard(board);
        Util.makeSnakes(board);
        Util.makeLadders(board);
        workFlow = Util.intializeWorkFlow(name1, name2);
    }

    public void proceed() {
        if(this.isOver){
            System.out.println("GAME IS OVER");
            return;
        }
        int roll = Util.diceRoll(workFlow);
        Util.log(Constraints.ROLL, this.workFlow);
        makeMove(workFlow);

        if(workFlow.newPosition.x == 9 && workFlow.newPosition.y == 9){
            this.isOver  = true;
            Util.log(Constraints.WIN, workFlow);
        }
        System.out.println();
        Util.changeTurn(workFlow);
    }

    public void makeMove(WorkFlow workFlow) {
        Util.log(Constraints.BEFORE_MOVE, this.workFlow);
        if(Util.initialCheck(workFlow)){
            if(workFlow.roll != 6)return;
            int roll = Util.diceRoll(workFlow);
            System.out.println("You got a 6, you're good to go!!!");
            Util.log(Constraints.ROLL, this.workFlow);
            roll = Util.diceRoll(workFlow);
        }
        // if(Util.diceRoll(workFlow) == 6){
        //     Util.log(Constraints.ROLL, this.workFlow);
        //      int roll = Util.diceRoll(workFlow);
        //      System.out.println("u get another chance to roll");
        //     System.out.println("move from "+workFlow.newPosition.x+", "+workFlow.newPosition.y);
        // }
        // if (Util.diceRoll(workFlow) == 6) {
        //     System.out.println("Passing the chance to another player");
        //     workFlow.turnFirstPlayer = !workFlow.turnFirstPlayer;
        // }
        
        Box newPosition = Util.calculateNewPosition(workFlow);
        workFlow.newPosition = newPosition;
        if(Util.isOverShooting(workFlow))return;
        Util.log(Constraints.SHOULD_GO, workFlow);
        Util.checkSnake(board, workFlow);
        Util.checkLadder(board, workFlow);
        if(workFlow.turnFirstPlayer)workFlow.player1.position = workFlow.newPosition;
        else workFlow.player2.position = workFlow.newPosition;
    }
}
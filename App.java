package com.board.oops;

 class App {
    public static void main(String[] args) {

        Game game = new Game("Sakshi", "Shreya");

        while (!game.isOver){
            game.proceed();
        }
    }
}
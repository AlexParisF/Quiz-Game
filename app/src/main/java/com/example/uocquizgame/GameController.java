package com.example.uocquizgame;

import java.util.ArrayList;


public class GameController {
    //unique object in the system
    private static GameController instance;

    //ENUM for Unit states
    public enum UnitType{
        NOT_ANSWERED, FAILED, PASSED
    }

    //arrays of observers
    private ArrayList<GameControllerUnitObserver> unitObservers = new ArrayList<>();
    private ArrayList<GameControllerQuestionObserver> questionObservers = new ArrayList<>();
    private ArrayList<GameControllerScoreObserver> scoreObservers = new ArrayList<>();

    // Instance variables
    private int score;
    private int level;
    private int currentUnit=0; //correct answers in the current test
    private int correctAnswersInCurrentTest=0; //correct answers in the current test
    private int currentQuestion=0;
    private String player;

    //array with the states of the 6 units: initially all the units are "NOT_ANSWERED"
    public UnitType [] unitsPassed = new UnitType[]{UnitType.NOT_ANSWERED,UnitType.NOT_ANSWERED,UnitType.NOT_ANSWERED,UnitType.NOT_ANSWERED,UnitType.NOT_ANSWERED,UnitType.NOT_ANSWERED};

    //SETTERS AND GETTERS
    public int getCorrectAnswersInCurrentTest() {
        return correctAnswersInCurrentTest;
    }

    public void setCorrectAnswersInCurrentTest(int correct) {
        this.correctAnswersInCurrentTest = correct;
    }

    public int getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(int currentUnit) {
        this.currentUnit = currentUnit;
    }

    public void initTest(){
        correctAnswersInCurrentTest=0;
        currentQuestion=0;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        notifyQuestionObservers();
    }


    public void changeUnitState(UnitType newState,int quiz){
        unitsPassed[quiz]=newState;
        notifyUnitObservers();
    }

    private GameController() {
        score = 0;
        level = 0;
    }

    // get the only instance in the system
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void updateScore(int points) {
        score += points;
        notifyScoreObservers();
    }

    public void setScore(int points){
        score = points;
        notifyScoreObservers();
    }
    public void updateLevel() {
        level++;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    public void setPlayer(String player){
        this.player = player;
    }
    public String getPlayer(){
        return player;
    }

    public void addUnitObserver(GameControllerUnitObserver observer) {
        unitObservers.add(observer);
    }

    public void removeUnitObserver(GameControllerUnitObserver observer) {
        unitObservers.add(observer);
    }

    private void notifyUnitObservers() {
        for (GameControllerUnitObserver observer : unitObservers) {
            observer.onQuizStateChanged();
        }
    }

    private void notifyQuestionObservers() {
        for (GameControllerQuestionObserver observer : questionObservers) {
            observer.onQuestionChanged();
        }
    }

    public void addQuestionObserver(GameControllerQuestionObserver observer) {
        questionObservers.add(observer);
    }

    public void removeQuestionObserver(GameControllerQuestionObserver observer) {
        questionObservers.remove(observer);
    }

    private void notifyScoreObservers() {
        for (GameControllerScoreObserver observer : scoreObservers) {
            observer.onScoreChanged();
        }
    }
    public void addScoreObserver(GameControllerScoreObserver observer) {
        scoreObservers.add(observer);
    }

    public void removeScoreObserver(GameControllerScoreObserver observer) {
        scoreObservers.remove(observer);
    }

    public interface GameControllerUnitObserver {
        public void onQuizStateChanged();
    }

    public interface GameControllerQuestionObserver {
        public void onQuestionChanged();
    }

    public interface GameControllerScoreObserver {
        public void onScoreChanged();
    }
}

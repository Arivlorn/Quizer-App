package edu.andrews.cptr252.aisensee.quizer;

import java.util.UUID;

/**
 * Model for a true/false question.
 */
public class Question {

    /** Unique ID for question **/
    private UUID mID;

    /** Question */
    private String mQuestion;

    /** Answer to question */
    private boolean mAnswer;

    /**
     * Create a new question.
     */
    public Question() {
        mID = UUID.randomUUID();    // a constructor
    }

    // Getters & Setters

    /**
     * Returns the ID
     * @return ID
     */
    public UUID getID() { return mID; }

    /**
     * Returns the question.
     * @return Question
     */
    public String getQuestion() { return mQuestion; }

    /**
     * Sets the question.
     * @param question
     */
    public void setQuestion(String question) { mQuestion = question; }

    /**
     * Set the question and answer at the same time.
     * @param question The question.
     * @param answer The true/false answer.
     */
    public void setQuestionAnswer(String question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    /**
     * Returns the true/false answer.
     * @return Answer
     */
    public boolean getAnswer() { return mAnswer; }

    /**
     * Sets the true/false answer.
     * @param answer
     */
    public void setAnswer(boolean answer) { mAnswer = answer; }

}
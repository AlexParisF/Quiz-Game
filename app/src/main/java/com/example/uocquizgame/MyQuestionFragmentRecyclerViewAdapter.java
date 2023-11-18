package com.example.uocquizgame;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uocquizgame.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.uocquizgame.databinding.FragmentItemBinding;
import com.example.uocquizgame.QuizContent.Question;
import com.example.uocquizgame.QuizContent.Answer;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyQuestionFragmentRecyclerViewAdapter extends RecyclerView.Adapter<MyQuestionFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<Question> mValues;
    private GameController controller;
    private GameController.GameControllerQuestionObserver observer;

    public MyQuestionFragmentRecyclerViewAdapter(List<Question> items) {
        mValues = items;
        controller=GameController.getInstance();
        observer=new GameController.GameControllerQuestionObserver() {
            @Override public void onQuestionChanged() {
                notifyDataSetChanged();
            }
        };
        controller.addQuestionObserver(observer);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(controller.getCurrentQuestion() >= QuizContent.ITEMS.size()){
            return;
        }
        QuizContent.Answer answer = QuizContent.ITEMS.get(
                GameController.getInstance().getCurrentQuestion())
                .getPossibleAnswers().get(position);
        holder.mItem = answer;
        holder.mContentView.setText(answer.description);
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                GameController controller = GameController.getInstance();
                if (answer.isRightAnswer) {
                    controller.setCorrectAnswersInCurrentTest(controller.getCorrectAnswersInCurrentTest() + 1);
                    controller.updateScore(1);
                }
                if (controller.getCurrentQuestion() != QuizContent.ITEMS.size()) {
                    controller.setCurrentQuestion(controller.getCurrentQuestion() + 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final TextView mIdView;
        public final TextView mContentView;
        public Answer mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
           // mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
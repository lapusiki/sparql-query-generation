package net.lapusiki.core.impl;

import com.google.common.collect.Lists;
import net.lapusiki.core.Parser;
import net.lapusiki.core.QuestionType;
import net.lapusiki.core.model.Holder;
import net.lapusiki.core.model.Question;

import java.util.List;

/**
 * Created by blvp on 12.05.15.
 */
public class QuestionParser implements Parser {

    private List<String> questionWordsType1 = Lists.newArrayList("кто", "что");
    private List<String> questionWordsType2 = Lists.newArrayList("сколько");

    public Holder<Question, String> parse(String sentence) throws Exception {

        Holder<Question, String> holder = new Holder<>();
        String[] parsedQuestion = new PrepositionsParser().parse(sentence);

        // TODO: refactor it
        if (questionWordsType1.contains(parsedQuestion[0].toLowerCase())) {
            holder.setObject1(new Question(QuestionType.SIMPLE_QUESTION));
            String object2 = "";
            for (int i = 1; i < parsedQuestion.length; i++) {
                object2 += new StringBuilder().append(parsedQuestion[i]).append(" ");
            }
            holder.setObject2(object2);
        } else if (questionWordsType2.contains(parsedQuestion[0].toLowerCase())) {
            holder.setObject1(new Question(QuestionType.CUSTOM_QUESTION));
            String object2 = "";
            for (int i = 2; i < parsedQuestion.length; i++) {
                object2 += new StringBuilder().append(parsedQuestion[i]).append(" ");
            }
            holder.setObject2(object2);
        } else {
            throw new Exception("Wrong question word");
        }

        return holder;

    }
}

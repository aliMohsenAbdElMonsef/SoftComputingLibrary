package org.example.FuzzyLogic.Defuzzification;

import org.example.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.FuzzyLogic.MembershipFunctions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefuzziferFactory {

    private DefuzzifyType type;
    private defuzzify defuzzifier;
    public DefuzziferFactory(DefuzzifyType type) {
        this.type = type;
    }
    public void setType(DefuzzifyType type) {
        this.type = type;
    }
    public defuzzify defuzzify() {
        return switch (type) {
            case WEIGHTED_AVERAGE -> defuzzifier = new AverageWeightedDefuzzifier();
            case MEAN_OF_MAXIMA -> defuzzifier = new MeanofMaximaDefuzzifier();
            case SUGENO_AVERAGE -> defuzzifier = new AverageSugeno();
        };
    }

}

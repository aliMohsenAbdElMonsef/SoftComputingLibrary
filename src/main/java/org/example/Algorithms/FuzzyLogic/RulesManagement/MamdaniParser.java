package org.example.Algorithms.FuzzyLogic.RulesManagement;
import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.Operations.Operation;
import org.example.Algorithms.FuzzyLogic.Operations.OperationsFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MamdaniParser extends RulesParser {
    public MamdaniParser(String orType, String andType) {
        super(orType, andType);
    }
    @Override
    public Rule parseRule(String ruleText, Map<String, Linguistic_Variable> variables) {
        String[] parts = ruleText.trim().split("(?i)THEN");
        if (parts.length != 2) throw new RuntimeException("Invalid rule syntax: " + ruleText);
        String antecedentStr = parts[0].replaceFirst("(?i)IF", "").trim();
        String consequentStr = parts[1].trim();
        List<String> conds = extractConditions(antecedentStr);
        List<String> ops = extractOperators(antecedentStr);
        List<Portion> antecedents = new ArrayList<>();
        for (String c : conds)
        {
            antecedents.add(parsePortion(c, variables));
        }
        List<Operation> operators = new ArrayList<>();
        for (String op : ops)
        {
            operators.add(OperationsFactory.create(op.equals("AND") ? "AND" : "OR", op.equals("AND") ? andType : orType));
        }
        if (antecedents.size()-1 != operators.size())
        {
            throw new RuntimeException("Invalid rule syntax: " + ruleText);
        }

        Portion consequent = parsePortion(consequentStr, variables);
        MamdaniRule r = new MamdaniRule(antecedents, operators, consequent);
        r.Representation = ruleText;
        return r;
    }
}

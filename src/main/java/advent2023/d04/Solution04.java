package advent2023.d04;

import lombok.Getter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;


public class Solution04 {

    private static final Pattern pattern = Pattern.compile("Card +(\\d+): +([\\d ]+)\\| +([\\d ]+)");

    public int step1(List<String> input) {
        return input.stream()
                .map(l -> pattern.matcher(l))
                .filter(Matcher::matches)
                .map(Card::new)
                .map(Card::getPoints)
                .map(count -> (int) Math.pow(2, count - 1))
                .reduce(Math::addExact)
                .get();
    }

    public int step2(List<String> input) {
        Graphics graphics = createVisualisation();
        List<Card> originalCards = input.stream()
                .map(l -> pattern.matcher(l))
                .filter(Matcher::matches)
                .map(Card::new)
                .toList();
        int total = 0;
        int[] extra = new int[originalCards.size()];
        for(Card c : originalCards){
            int bonus = extra[c.getId()-1];
            total= total + 1 + bonus;
            for(int i = 0; i<c.getPoints() && i+c.getId()<extra.length;i++){
                int current = extra[i+c.getId()];
                extra[c.getId()+i] = current + (1+bonus);
            }
            visualise(extra, graphics, total);
        }
        pause(10000);
        return total;
    }

    private static final int HEIGHT = 500;
    private static final int WIDTH = 1000;
    private Graphics createVisualisation(){
        JFrame frmMain = new JFrame();
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setSize(WIDTH, HEIGHT);
        Canvas cnvs = new Canvas();
        cnvs.setSize(WIDTH, HEIGHT);
        frmMain.add(cnvs);
        frmMain.setVisible(true);
        Graphics g = cnvs.getGraphics();
        g.setColor(new Color(255, 255, 255));
        return g;
    }

    private void visualise(int[] extra, Graphics graphics, int total){
        int w = 50;
        int h = 2;
        for(int i=0;i<extra.length;i++){
            graphics.setColor(new Color((i*13)%255, (i*19)%255, (i*31)%255));
            graphics.fillRect(0,i*h,  ((int)Math.log(1+extra[i]))*w, h);
        }
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0,HEIGHT-100, WIDTH, 100);
        graphics.setColor(new Color(0, 0, 255));
        graphics.drawString("Total "+total, 100, HEIGHT-50);
        pause(100);
    }

    private void pause(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Getter
@ToString
class Card {
    private final int id;
    private final Set<String> winning;
    private final Set<String> numbers;

    public Card(Matcher m) {
        id = Integer.parseInt(m.group(1));
        winning = new HashSet<>(asList(m.group(2).split(" +")));
        numbers = new HashSet<>(asList(m.group(3).split(" +")));
    }

    public int getPoints() {
        return (int) numbers.stream()
                .filter(n -> winning.contains(n))
                .count();
    }

    public Map<Integer, Integer> getExtraCards(int cap) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int i = id + 1; i < id + 1 + getPoints() && i<=cap; i++) {
            set.put(i, 1);
        }
        return set;
    }
}
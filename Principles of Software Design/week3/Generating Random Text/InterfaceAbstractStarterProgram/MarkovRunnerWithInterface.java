
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 100;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 531;
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        //runModel(mThree, st, size, seed);
        mThree.setTraining(st);
        mThree.setRandom(seed);
        mThree.printHashMapInfo();

    }

    public void compareMethods(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
        
        MarkovModel mTwo = new MarkovModel(2);
        long start = System.currentTimeMillis();
        runModel(mTwo, st, size, seed);
        long end = System.currentTimeMillis();
        System.out.println("运行时间：" + (end - start) + "毫秒");//应该是end - start
    
        start = System.currentTimeMillis();
        EfficientMarkovModel mThree = new EfficientMarkovModel(2);
        end = System.currentTimeMillis();
        runModel(mThree, st, size, seed);
        System.out.println("运行时间：" + (end - start) + "毫秒");//应该是end - start
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}

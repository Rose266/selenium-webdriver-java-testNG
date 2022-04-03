package javaTester;

public class Topic_02_And_Or {
	public static void main(String[] args) {
		boolean answerPersonA;
		boolean answerPersonB;
		boolean resultC;
		
		// AND 1 trong 2 mà sai thì kết quả sai
		answerPersonA = true;
		answerPersonB = false;
		resultC=answerPersonA && answerPersonB;
		System.out.println("1. A && B = " + resultC );
		
		answerPersonA = false;
		answerPersonB = true;
		resultC = answerPersonA && answerPersonB;
		System.out.println("2. A && B = " + resultC);
		
		answerPersonA = false;
		answerPersonB = false;
		resultC = answerPersonA && answerPersonB;
		System.out.println("3. A && B = " + resultC);
		
		answerPersonA = true;
		answerPersonB = true;
		resultC = answerPersonA && answerPersonB;
		System.out.println("4. A && B = " + resultC);
		System.out.println(!answerPersonA);
		
		// OR 1 trong 2 mà đúng thì kết quả đúng
		answerPersonA = true;
		answerPersonB = false;
		System.out.println("1. A || B = " + (answerPersonA || answerPersonB));
		
		answerPersonA = false;
		answerPersonB = true;
		System.out.println("2. A || B = " + (answerPersonA || answerPersonB));
		
		answerPersonA = false;
		answerPersonB = false;
		System.out.println("3. A || B = " + (answerPersonA || answerPersonB));
		
		answerPersonA = true;
		answerPersonB = true;
		System.out.println("4. A || B = " + (answerPersonA || answerPersonB));
	}

}

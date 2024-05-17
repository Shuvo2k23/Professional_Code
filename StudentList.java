import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Please write right argument(a | r | c | +WORD | ?WORD).");
			return;
		}
		if(args[0].equals("a")) {
			System.out.println("Loading data ...");
			try {
				BufferedReader fileReader =	new BufferedReader(
											new InputStreamReader(
											new FileInputStream("students.txt")));
				String readLine = fileReader.readLine();
				String words[] = readLine.split(", ");
				for(String word : words) {
					System.out.println(word);
				}
			} catch (Exception e){}
			System.out.println("Data Loaded.");
		}
		// return random student
		else if(args[0].equals("r")) {
			System.out.println("Loading data ...");
			try {
				BufferedReader fileReader = new BufferedReader(
											new InputStreamReader(
											new FileInputStream("students.txt")));
				String readLine = fileReader.readLine();
				String words[] = readLine.split(", ");
				Random random = new Random();
				int randomNumber = random.nextInt(words.length);
				System.out.println(words[randomNumber]);
			} catch (Exception e){}
			System.out.println("Data Loaded.");
		}
		else if(args[0].contains("+")){
			System.out.println("Loading data ...");
			try {
				BufferedWriter fileReader = new BufferedWriter(
											new FileWriter("students.txt", true));
				String newWord = args[0].substring(1);
				Date date = new Date();
				String dateType = "dd/mm/yyyy-hh:mm:ss a";
				DateFormat dateFormat = new SimpleDateFormat(dateType);
				String fd= dateFormat.format(date);
				fileReader.write(", "+newWord+"\nList last updated on "+fd);
				fileReader.close();
			} catch (Exception e){}
			System.out.println("Data Loaded.");
		}
		else if(args[0].contains("?")) {
			System.out.println("Loading data ...");
			try {
				BufferedReader fileReader = new BufferedReader(
											new InputStreamReader(
											new FileInputStream("students.txt")));
				String readLine = fileReader.readLine();
				String words[] = readLine.split(", ");
				boolean done = false;
				String newWord = args[0].substring(1);
				for(int idx = 0; idx<words.length && !done; idx++) {
					if(words[idx].equals(newWord)) {
						System.out.println("We found it!");
						done=true;
					}
				}
			} catch (Exception e){}
			System.out.println("Data Loaded.");
		}
		else if(args[0].contains("c")) {
			System.out.println("Loading data ...");
			try {
				BufferedReader fileReader = new BufferedReader(
											new InputStreamReader(
											new FileInputStream("students.txt")));
				String readLine = fileReader.readLine();
				char charArray[] = readLine.toCharArray();
				boolean in_word = false;
				int count=0;
				for(char c:charArray) {
					if(c ==' ') {
						if (!in_word) {
							count++;
							in_word =true;
						}
						else {
							in_word=false;
						}
					}
				}
				System.out.println(count +" word(s) found " + charArray.length);
			} catch (Exception e){}
			System.out.println("Data Loaded.");
		}
	}
}
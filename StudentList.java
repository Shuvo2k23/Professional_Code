import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
		if(args.length != 1){
			wrongArgument();
			return;
		}
		String readLine = LoadData(Constants.STUDENT_LIST);
		System.out.println(Constants.Loading);
		if(args[0].equals(Constants.ShowAll)) {
			try {
				String words[] = readLine.split(Constants.SplitAt);
				for(String word : words) {
					System.out.println(word);
				}
			} catch (Exception e){}
		}
		// return random student
		else if(args[0].equals(Constants.ShowRandom)) {
			try {
				String words[] = readLine.split(Constants.SplitAt);
				Random random = new Random();
				int randomNumber = random.nextInt(words.length);
				System.out.println(words[randomNumber]);
			} catch (Exception e){}
		}
		else if(args[0].contains(Constants.AddEntry)){
			try {
				BufferedWriter fileReader = new BufferedWriter(
											new FileWriter(Constants.STUDENT_LIST, true));
				String newWord = args[0].substring(1);
				DateFormat dateFormat = new SimpleDateFormat(Constants.Date_Format);
				String fullDate = dateFormat.format(new Date());
				fileReader.write(", "+newWord+"\nList last updated on "+fullDate);
				fileReader.close();
			} catch (Exception e){}
		}
		else if(args[0].contains(Constants.FindEntry)) {
			try {
				String words[] = readLine.split(Constants.SplitAt);
				boolean done = false;
				String newWord = args[0].substring(1);
				for(int idx = 0; idx<words.length && !done; idx++) {
					if(words[idx].equals(newWord)) {
						System.out.println(Constants.Found_Massage);
						done=true;
					}
				}
			} catch (Exception e){}
		}
		else if(args[0].contains(Constants.ShowCount)) {
			try {
				String words[] = readLine.split(Constants.SplitAt);
				System.out.println(words.length +" word(s) found ");
			} catch (Exception e){}
		}
		else{
			wrongArgument();
		}
		System.out.println(Constants.Loaded);
	}
	public static String LoadData(String fileName){
		try{
			BufferedReader fileReader = new BufferedReader(
										new InputStreamReader(
										new FileInputStream(fileName)));
			return fileReader.readLine();
		}catch (Exception e){
			return null;
		}
	}
	public static void wrongArgument(){
		System.out.println(Constants.Wrong_Argument);
	}
}
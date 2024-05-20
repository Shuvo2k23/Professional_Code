import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	//main method
	public static void main(String[] args) {
		//Check argument
		if(args.length != 1){
			wrongArgument();
			return;	//exit early
		}
		//Read StudentList
		String studentList = loadStudents(Constants.STUDENT_LIST);
		System.out.println(Constants.Loading);
		//print all student
		if(args[0].equals(Constants.ShowAll)) {
			String students[] = studentList.split(Constants.SplitAt);
			for(String student : students) {
				System.out.println(student);
			}
		}
		// return random student
		else if(args[0].equals(Constants.ShowRandom)) {
			String students[] = studentList.split(Constants.SplitAt);
			Random random = new Random();
			//generate random index of students
			int randomIndex = random.nextInt(students.length);
			System.out.println(students[randomIndex]);
		}
		//add new student
		else if(args[0].contains(Constants.AddEntry)){
			try {
				BufferedWriter students = new BufferedWriter(
											new FileWriter(Constants.STUDENT_LIST, true));
				String newWord = args[0].substring(1);
				DateFormat dateFormat = new SimpleDateFormat(Constants.Date_Format);
				String fullDate = dateFormat.format(new Date());
				students.write(", "+newWord+"\nList last updated on "+fullDate);
				students.close();
			} catch (Exception e){}
		}
		//find any student
		else if(args[0].contains(Constants.FindEntry)) {
			String students[] = studentList.split(Constants.SplitAt);
			boolean done = false;
			String targetStudent = args[0].substring(1);
			for(int idx = 0; idx<students.length && !done; idx++) {
				if(students[idx].equals(targetStudent)) {
					System.out.println(Constants.Found_Massage);
					done=true;
				}
			}
		}
		//count number of students
		else if(args[0].contains(Constants.ShowCount)) {
			String students[] = studentList.split(Constants.SplitAt);
			System.out.println(students.length +" student(s) found ");
		}
		//for wrong argument
		else{
			wrongArgument();
		}
		System.out.println(Constants.Loaded);
	}
	public static String loadStudents(String fileName){
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
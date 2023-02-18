package retrio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QuestionReader {
	
	private int questionSize;
	private static XSSFWorkbook questionFile;
	
	//Storing questions, choices and answers
	ArrayList <String> question = new ArrayList<>();
	ArrayList <String> choiceA = new ArrayList<>();
	ArrayList <String> choiceB = new ArrayList<>();
	ArrayList <String> choiceC = new ArrayList<>();
	ArrayList <String> choiceD = new ArrayList<>();
	ArrayList <String> answer = new ArrayList<>();
	
	/*Reads the file containing the questions*/
	public void questionReader() {                        

		try { 
			FileInputStream fileInput = new FileInputStream("MCQ/MultipleChoiceQuestions.xlsx"); //importing the file
		    questionFile = new XSSFWorkbook(fileInput);
			XSSFSheet sheet = questionFile.getSheetAt(0); //get spreadsheet of the index zero
					
			Iterator <Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {                //gets the values in the spreadsheet by row
				Row row = rowIterator.next();
				
				Iterator <Cell> cellIterator = row.cellIterator();			
				while(cellIterator.hasNext()) {           //stores the cell values into their corresponding ArrayLists
					
					Cell cell = cellIterator.next();
					if(row.getRowNum() > 0) { 
						switch(cell.getColumnIndex()) {
							case 2:
								question.add(cell.getStringCellValue());
								break;
							case 3:
								choiceA.add(cell.getStringCellValue());
								break;
							case 4:
								choiceB.add(cell.getStringCellValue());
								break;
							case 5:
								choiceC.add(cell.getStringCellValue());
								break;
							case 6:
								choiceD.add(cell.getStringCellValue());
								break;
							case 7:
								answer.add(cell.getStringCellValue());
								break;
						}
					}
				}
			}
					
			fileInput.close();
			setQuestionSize(question.size());
			
		} catch(IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void setQuestionSize(int quesSize) {
		this.questionSize = quesSize;
	}
	
	public int getQuestionSize() {
		return this.questionSize;
	}

}

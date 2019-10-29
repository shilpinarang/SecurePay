package com.securepay.test.SpayProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
class readCSV {
	 public String readheader(String csvFile, int line_no) throws IOException {
		 String line = "";
		 BufferedReader br = null;
		 int i=0;
		 try {
			 br = new BufferedReader(new FileReader(csvFile)); 
			 while ((line = br.readLine()) != null) {
				 if((i+1)==line_no) {
					 return line;
				 }
				i++;
			 }
		  }
		 catch (FileNotFoundException e){
			 e.printStackTrace();
		 } 
		 catch (IOException e) {
			e.printStackTrace();
		} 
		 finally {
			 br.close();
		 }
		 return "";
	 }
}

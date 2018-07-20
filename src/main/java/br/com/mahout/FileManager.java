package br.com.mahout;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

/**
 * Class to manager files
 * 
 * @author Paulo Henrique Maia Soares
 *
 */
public class FileManager {

	private static final String FILE = "training.data";

	/* Utilities class can't be instantiate */
	private FileManager() {
		
	}

	/**
	 * Return the training data to mahout 
	 * 
	 * @return
	 * @throws IOException
	 */
	public static FileDataModel getFileDataModel() throws IOException {
		return new FileDataModel(new File(FileManager.class.getClassLoader().getResource(FILE).getFile()));
	}

}

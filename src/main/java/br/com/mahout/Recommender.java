package br.com.mahout;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Class to do the recommendation using the collaborative filter
 * 
 * @author Paulo Henrique Maia Soares
 *
 */
public class Recommender {

	/**
	 *
	 * Recommender based on user
	 * 
	 * @param userId
	 * @param neighbors
	 * @param qtdRecommendations
	 * @throws IOException
	 * @throws TasteException
	 */
	public void userBased(long userId, int neighbors, int qtdRecommendations) throws IOException, TasteException {

		FileDataModel dataModel = FileManager.getFileDataModel();

		/* get the similarity using the pearson correlation */
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(neighbors, similarity, dataModel);

		GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

		List<RecommendedItem> recommendations = recommender.recommend(userId, qtdRecommendations);
		for (RecommendedItem item : recommendations) {
			System.out.println(item);
		}

	}

	/**
	 * 
	 * Recommender based on itens
	 * 
	 * @param userId
	 * @param qtdRecommendations
	 * @throws IOException
	 * @throws TasteException
	 */
	public void itemBased(long userId, int qtdRecommendations) throws IOException, TasteException {
		FileDataModel dataModel = FileManager.getFileDataModel();

		/* get the similarity using the jaccard coefficient */
		ItemSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);

		GenericBooleanPrefItemBasedRecommender recommender = new GenericBooleanPrefItemBasedRecommender(dataModel, similarity);

		List<RecommendedItem> recommendations = recommender.recommend(userId, qtdRecommendations);
		for (RecommendedItem item : recommendations) {
			System.out.println(item);
		}

	}

}

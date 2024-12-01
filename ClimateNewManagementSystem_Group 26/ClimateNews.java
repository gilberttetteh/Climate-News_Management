import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ClimateNews {
    private List<NewsArticle> articles = new ArrayList<>();   //ArrayList to store new articles
    private static final String JSON_FILE_NAME = "Climate News File.json";    // name of file for json sotrage

    public ClimateNews() {
        loadArticlesFromJson();
    }   //loads the articles from the jason file when the code runs or is initialized

    private void loadArticlesFromJson() {  //method to load the articles
        List<Map<String, String>> jsonArticles = JsonHandler.loadFromJson();  //load the articles from the json handler class
        for (Map<String, String> jsonArticle : jsonArticles) {    //loops through each articles
            try {
                addArticle(jsonArticle.get("title"), jsonArticle.get("content"), jsonArticle.get("date"));  //adds an article to the list
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveArticlesToJson() { //method to save json file
        List<Map<String, String>> jsonArticles = new ArrayList<>();  // arraylist to stroe json articles
        for (NewsArticle article : articles) {
            Map<String, String> jsonArticle = new HashMap<>();   //hashmap which stores the articles attributes
            jsonArticle.put("title", article.getTitle());   //adds the title to the map
            jsonArticle.put("content", article.getContent());   //puts the content in the map
            jsonArticle.put("date", new SimpleDateFormat("dd-MM-yyyy").format(article.getDate()));
            jsonArticles.add(jsonArticle);
        }
        JsonHandler.saveToJson(jsonArticles); //uses the json handler to save the json to the list
    }

    public void addArticle(String title, String content, String date) throws ParseException { //method add an article to the list

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  //puts the date in the required format
        Date parseDate = formatter.parse(date);
        articles.add(new NewsArticle(title, content, parseDate)); //adds a news article to the list
        saveArticlesToJson();
        System.out.println("Article added successfully.");
    }

    public String searchByKeyword(String keyword) {     //method to search through the articles by a particular keyword
        StringBuilder result = new StringBuilder("****************   Search for Article By Keyword   ****************\n");
        if (articles.isEmpty()) {  // Check if there are no article
            return result.append("There are no articles in here at the moment. Try adding one with the 'add_article' command.").toString();
        }
        if (Objects.equals(keyword, "")) {   // Check if the keyword is empty
            return result.append("Pass in a keyword before searching").toString();
        }

        boolean found = false;
        for (NewsArticle article : articles) {  // Loop through each article
            if (article.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    article.getContent().toLowerCase().contains(keyword.toLowerCase())) {  // Check if the keyword is in the title or content
                result.append("Title: ").append(article.getTitle()).append("\n");
                result.append("Summary: ").append(article.getContent().substring(0, (article.getContent().length() / 2))).append("...\n");
                result.append("Date: ").append(article.getDate()).append("\n\n");
                found = true;   // Set found flag to true
            }
        }
        if (!found) {  // If no article is found
            result.append("No article found with keyword: ").append(keyword);
        }
        return result.toString();
    }

    public String sortByDate() {  // Sort articles by date in descending order
        StringBuilder result = new StringBuilder("****************   Articles Sorted By Date   ****************\n");
        if (articles.isEmpty()) {  // Check if there are no articles
            return result.append("There are no articles in here at the moment. Try adding one with the 'add_article' command.").toString();
        }

        articles.sort((a, b) -> b.getDate().compareTo(a.getDate()));   // Sort articles by date in descending order

        for (NewsArticle article : articles) {    // Loop through each article
            result.append("Title: ").append(article.getTitle()).append("\n");
            result.append("Summary: ").append(article.getContent().substring(0, (article.getContent().length() / 2))).append("...\n");
            result.append("Date: ").append(article.getDate()).append("\n");
            result.append("--------------------------------------------------\n");
        }
        return result.toString();
    }

    public String displaySummaries() {   // Display summaries of all articles
        StringBuilder result = new StringBuilder("****************   Article Summaries   ****************\n");
        if (articles.isEmpty()) {  // Check if there are no articles
            return result.append("There are no articles in here at the moment. Try adding one with the 'add_article' command.").toString();
        }
        for (NewsArticle article : articles) {  // Loop through each article
            result.append("Title: ").append(article.getTitle()).append("\n");
            result.append("Summary: ").append(article.getContent().substring(0, (article.getContent().length() / 2))).append("...\n");
            result.append("Date: ").append(article.getDate()).append("\n");
            result.append("--------------------------------------------------\n");
        }
        return result.toString();
    }
    public boolean deleteArticle(String title) {  //method to delete an article by its title
        for (Iterator<NewsArticle> iterator = articles.iterator(); iterator.hasNext();) { //allows to iterate through each article
            NewsArticle article = iterator.next();
            if (article.getTitle().equalsIgnoreCase(title)) {  //checks if the title macthes
                iterator.remove();    // Remove the article
                saveArticlesToJson();   // Save the updated list to JSON
                return true;
            }
        }
        return false;  // Return false if no article is found with the given title
    }

    public String displayFullArticle(String title) {  //method to display the full content of the articles
        for (NewsArticle article : articles) {
            if (article.getTitle().equalsIgnoreCase(title)) {  // Check if the title matches
                return String.format("Title: %s\nDate: %s\nContent: %s",
                        article.getTitle(),
                        new SimpleDateFormat("dd-MM-yyyy").format(article.getDate()),
                        article.getContent());
            }
        }
        return "Article not found.";  // Return a message if no article is found with the given title
    }
}

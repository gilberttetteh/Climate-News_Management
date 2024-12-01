import java.util.Date;

public class NewsArticle {
    String title;    //stores the title of the articles
    String content;   //stores the article content
    Date date;    //stores the date

    public String getTitle() {   //getter method for the title
        return title;
    }

    public void setTitle(String title) {   //setter method for the title

        this.title = title;
    }

    public String getContent() {       //getter method for content

        return content;
    }

    public void setContent(String content) {   //setter method for content

        this.content = content;
    }

    public Date getDate() {     //getter method for date
        return date;
    }

    public void setDate(Date date) {    //setter method date
        this.date = date;
    }

    public NewsArticle(String title, String content, Date date) {  //constructor to initialize a newsarticle object
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
